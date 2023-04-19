<template>
	<view class="destination">
		<!-- 内容 -->
		<view class="content">
			<!-- 列表里的每一项，循环遍历生成 -->
			<view class="item" @click="toIndex(item)" v-for="item in 10" :key="item.id">
				<!-- item 是存放到的变量  list是来源 key是为了渲染 -->
				<image src="../../static/address.png" mode=""></image>
				<view class="text">
					<view class="title">
						<view>用户名：{{username}}</view>
						<view>电话：{{phonenum}}</view>
						<view>起始地址：{{startadd}}</view>
						<view>目的地：{{endadd}}</view>
					</view>

					<button class="button">接单</button>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				myAmapFun: undefined,
				//用于存放提示列表
				list: [],
				username: '晓明',
				phonenum: '4162572',
				startadd: '南',
				endadd: '北',
				driverInfo:{},
				token:''
			}
		},
		onLoad() { 
			//校验token
			this.token=uni.getStorageSync('Token');
			if(this.token===''){
				uni.navigateTo({
					url:'/pages/login/login'
				})
				return
			}
			uni.request({
				url:"http://localhost:8023/user/info",
				method:'GET',
			 header:{
				 'Content-Type':'application/json',
				'token':this.token.toString()
			            },
				success: (res) => {
					console.log(res.data);
				 	this.driverInfo=data.data;
				},
				fail: (err) => {
					console.log("获取个人信息失败",err)
				}
			})
			//加载订单
			uni.request({
				url: "",
				method: 'GET',
				success: (res) => {
					console.log(res);
					if (res.data.status == 200) {
						this.list = res.data.data.orderList;
					} else {
						uni.showToast({
							title: '获取订单信息失败',
							icon: 'error'
						})
					}
				},
				fail: (err) => {
					console.log("获取订单信息失败");
					uni.showToast({
						title: '获取订单信息失败',
						icon: 'error'
					})
				}
			})
		},
		methods: {
			//存到本地，方便其他页面使用
			
			toIndex(item) {
				console.log("当前点击的是", item);
				uni.request({ //接单请求，改变订单状态
					url: "",
					method: 'POST',
					data: {
						orderId: item.id //订单ID
					    //driverId: uni.getStorageInfoSync()//司机的ID
					},
					success: (res) => {
						
						uni.setStorageSync('Order', JSON.stringify(item));//存放当前订单
						uni.switchTab({
							url: "/pages/index/index"
						});
					},
					fail: (err) => {
						uni.showToast({
							title:'抢单失败',
							icon:'error'
						})
					}
				})
				
				
			},
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
				height: 200rpx;
				border-bottom: 2.5px solid #eee;
				display: flex;
				align-items: center;

				// *************************调整，搜索框占满剩下位置********************************************************
				.input {
					flex: 1;
				}

				// *************************调********************************************************

				.text {
					display: flex;
					flex-grow: 1;

					.title {
						text-align: left;
						font-size: 24rpx;
						color: #aaa;
						margin-top: 8rpx;
						flex-grow: 1;
						margin-right: 10px;
					}

					.button {
						display: inline-block;
						padding: 12px 24px;
						font-size: 10px;
						font-weight: bold;
						text-align: center;
						text-decoration: none;
						color: #fff;
						background-color: #37c;
						box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
						transition: all 0.3s ease-in-out;
						border: 2px solid #ccc;
						border-radius: 50px;

					}

					.button:hover {
						background-color: #259;
						box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
						transform: translateY(-2px);
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