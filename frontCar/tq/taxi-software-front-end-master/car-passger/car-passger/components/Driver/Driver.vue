<template>
	<view>
		<view class="mask">
			<!-- 第二部分 -->

			<view class="maskItem">
				<image class="pic" src="../../static/redaddress.png" mode=""></image>
				<view class="place">
					{{'目的地'}}
				</view>
			</view>


			<view class="maskItem">
				<image class="pic" src="../../static/myaddress.png" mode=""></image>
				<view class="place">
					{{start}}
				</view>
				<text class="now">当前</text>
			</view>





		</view>
	</view>

</template>

<script>
	export default {
		props: {
			start: String
		},
		name: "Driver",
		data() {
			return {
				Dlongitude: '20', //目的地经度     
				Dlatitude: '110', //目的地维度      经度:103.96083    纬度:30.77896         HK:  22.317187   114.174719
                                        
				Dendaddr: '', //目的地名字

				//编辑点相关的信息
				markers: [],

			};
		},


		methods: {

			get_destination() {
				uni.request({
					url: "", //API
					method: 'GET',
					success: (res) => {
						console.log(res);
						if (res.data.status == 200) {


							this.Dlongitude = res.data.data.endx;
							this.Dlatitude = res.data.data.endy;
							this.Dendaddr = res.data.data.endaddr;



						} else {

						}
					},
					fail: (err) => {
						console.log("获取目的地失败");
						uni.showToast({
							title: '获取订目的地失败',
							icon: 'error'
						})
					}
				})
			}




		}
	}
</script>

<style lang="scss">
	.mask {
		width: 98vw;
		background-color: white;
		position: fixed; //固定地位
		left: 1vw; //偏移值
		bottom: 15vw; //偏移值
		box-shadow: 1px 1px 4px 4px #ccc;



		.maskItem {
			height: 120rpx;
			border: 2px solid #eee;
			display: flex; //内容浮动
			padding: 0 40rpx;
			align-items: center; //内容垂直居中

			.pic {
				width: 50rpx;
				height: 50rpx;
				margin-right: 40rpx;
			}

			.place {
				flex: 1; //占满剩下的位置
				color: #999;
			}

			.now {
				background-color: cadetblue;
				color: #fff;
				font-size: 20rpx;
				padding: 8rpx;
			}
		}
	}
</style>