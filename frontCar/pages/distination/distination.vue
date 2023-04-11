<template>
	<view class="destination">
		<!-- 主内容 -->
		<view class="content">
      <!--    无目的地时  -->
			<!-- 第一个部分-->
			<view class="item">
				<image src="../../static/redaddress.png" mode=""></image>
				<input type="text" @input="handleInput"/>
			</view>

			<!-- 第二部分-->
			<view class="list" >
				<!-- 列表每一项-->

				<view class="item" @click="toIndex(item)" v-for="item in list":key="item.id" >
					<image src="../../static/address.png" mode=""></image>
					<!-- 文本内容-->

					<view class="text">
						<view class="title">
							{{item.name}}
						</view>
						<view class="desc">
							{{item.district}}
						</view>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import amapFile from "../../libs/amap-wx.js"
	export default {
		data() {
			return {
				myAmapFun:undefined,
				list:''
			}
		},
		onLoad() {
			this.myAmapFun = new amapFile.AMapWX({
				key: '293291e5b982755b78f967ecc18bce6c'
			})
		},
		methods: {
			//当你在输入框输入的时候就会触发函数
			handleInput(e){
				//拿去关键字
				let words = e.detail.value
			    //根据关键词发起请求
				 this.myAmapFun.getInputtips({
				      keywords: words,
				      location: '',
				      success:(res)=>{
						  console.log(res);
						  this.list = res.tips
					  }
				})
				//
			},

			toIndex(item){
				console.log(item)

				uni.setStorageSync('target',JSON.stringify(item))
				uni.redirectTo({
					url:"/pages/index/index"
				})
			}

		}
	}
</script>

<style lang="scss">
	.destination{
		width: 100vw;
		height: 100vh;
		overflow: scroll;
		padding: 40rpx 20rpx;
		box-sizing: border-box;
	}
	.destination .content{
		box-shadow: 1px 1px 2px 2px #eee;
		height: 100%;

	}
	.destination .content image{
		width: 70rpx;
		height: 70rpx;
		margin-right: 40rpx;
	}
	.destination .content .item{
		height: 120rpx;
		border-bottom: 1px solid #eee;
		display: flex;
		align-items: center;
	}
	.destination .content .item .text{

	}
	.destination .content .item .text .desc{
		font-size: 24rpx;
		color: #aaa;
		margin-top: 8rpx;
	}
	.destination .content .list{
		height: calc(100%-120rpx);
		overflow: scroll;
	}

</style>
