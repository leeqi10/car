<template>
	<view class="destination">
		<!-- 内容 -->
		<view class="content">

			<!-- 两部分 -->
			<!-- 1.输入框部分 -->
			<view class="item">
				<image src="../../static/redaddress.png" mode=""></image>
				<input class="input" type="text" @input="handleInput">

				<!-- *************************添加了取消按钮***************************************************************************************** -->
				<view class="cancel" @click="handleCancel">取消</view>


			</view>
			<!-- 2.列表部分 -->
			<view class="list">

				<!-- *************************添加了以下代码***************************************************************************************** -->
				<!-- 如果列表为空，则显示 “无目的地叫车” 的文本提示 -->
				<view class="item" v-if="!list.length">
					<image src="../../static/address.png" mode=""></image>
					<view class="text">
						<view class="title">
							无目的地叫车
						</view>
						<view class="desc">
							请在输入框中输入目的地
						</view>
					</view>
				</view>
				<!-- ********************************************************************************************** -->




				<!-- 列表里的每一项，循环遍历生成 -->
				<view class="item" @click="toIndex(item)" v-for="item in list" :key="item.id">
					<!-- item 是存放到的变量  list是来源 key是为了渲染 -->
					<image src="../../static/address.png" mode=""></image>
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
	//1. 在输入框里输入关键字 根据关键字获取对应的提示列表，把列表展示到页面上
	//2.点击想要去往的目的地，目的地相关的内容，回显到首页中的目的地位置

	//导入插件
	import amapFile from '../../libs/amap-wx.130.js'
	export default {
		data() {
			return {
				myAmapFun: undefined,
				//用于存放提示列表
				list: []
			}
		},
		methods: {
			//当输入框里输入内容时，触发函数
			handleInput(e) {
				console.log(e.detail.value);
				//1.拿到输入的关键字
				let words = e.detail.value;


				//2.根据关键字发请求获取相关的数据
				this.myAmapFun.getInputtips({
					keywords: words,
					success: (res) => {
						console.log('关键字相关的提示列表', res);
						//把相关的提示列表存到list
						this.list = res.tips;
					}
				})
				//3.把获取到的列表渲染到页面
				//v-for
			},

			//存到本地，方便其他页面使用
			toIndex(item) {
				console.log("当前点击的是", item);
				uni.setStorageSync('target', JSON.stringify(item));
				uni.redirectTo({
					url: "/pages/index/index"
				})
			},
			//点击取消，跳转到主页
			//*****************************取消按钮，跳转到index*************************************************************************************************
			handleCancel() {
				uni.redirectTo({
					url: '/pages/index/index'
				})
			}
		},
		//*************************************************************************************************************************8

		onLoad() {
			this.myAmapFun = new amapFile.AMapWX({
				key: 'ceb72741060af13228ee8c0eb730b40d'
			});
		}
	}
</script>

<style lang="scss">
	.destination {
		width: 100vw;
		height: 100vh;
		overflow: scroll;
		padding: 40rpx 20rpx;
		box-sizing: border-box;

		.content {
			box-shadow: 1px 1px 2px 2px #eee;
			height: 100%;


			image {
				width: 70rpx;
				height: 70rpx;
				margin: 40rpx;
			}

			.item {
				height: 120rpx;
				border-bottom: 2.5px solid #eee;
				display: flex;
				align-items: center;

				// *************************调整，搜索框占满剩下位置********************************************************
				.input {
					flex: 1;
				}

				// *************************调********************************************************

				.text {
					.desc {
						font-size: 24rpx;
						color: #aaa;
						margin-top: 8rpx;
					}
				}

				// *************************取消按钮的样式********************************************************
				.cancel {
					width: 120rpx;
					height: 60rpx;
					line-height: 60rpx;
					text-align: center;
					background-color: #fff;
					color: #333;
					font-size: 30rpx;
					border-radius: 30rpx;
					box-shadow: 0 0 2rpx 2rpx #ddd;
					margin-right: 20rpx;

				}

			}

			.list {
				height: calc(100% - 120rpx);
				overflow: scroll;
			}
		}



	}
</style>