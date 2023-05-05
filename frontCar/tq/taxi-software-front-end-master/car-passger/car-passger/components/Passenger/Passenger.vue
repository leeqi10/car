<template>
	<view>
		<view class="mask">
			<view class="status-bar">
				<image class="trumpetpic" src="../../static/212喇叭.png"></image>
				{{driverStatusInfo}}
			</view>

			<!-- 第一部分 -->
			<view class="maskItem">
				<image class="pic" src="../../static/myaddress.png" mode=""></image>
				<view class="place">
					{{start}}
				</view>
				<text class="now">当前</text>
			</view>


			<!-- 第二部分 -->
			<view class="maskItem" @click="toDestination">
				<image class="pic" src="../../static/redaddress.png" mode=""></image>
				<view v-model="dest" class="place">
					{{this.targetcopy.name || '请选择目的地'}}
				</view>
			</view>
			<!-- 第三部分 -->
			<view class="maskItem" v-if="dest">
				<button type="primary" style="width: 100%;" @click="getDriverStatus">一键叫车</button>
			</view>


		</view>
	</view>
</template>

<script>
	export default {
		props: {

			start: String, //父组件的出发地地址
			driverStatusInfo: String,
			longitude: String, //父组件的出发地经度
			latitude: String, //父组件的出发地纬度


		},


		name: "Passenger",
		data() {
			return {
				target: '', //目的地
				targetcopy: '', //
				dest: '',
				driverStatusInfo: '请稍候...', // 司机状态
				timer: null //定时器的返回结果



			};
		},

		methods: {

			show() {
				this.target = uni.getStorageSync('target') ? JSON.parse(uni.getStorageSync('target')) : '请选择目的地';
				this.targetcopy = uni.getStorageSync('targetcopy') ? JSON.parse(uni.getStorageSync('target')) : '请选择目的地';
				this.dest = this.targetcopy.name;
				console.log("this.dest" + this.dest);
				if (!this.dest || this.dest === '请选择目的地') {
					this.dest = '';
				}

				//根据需求，是否要保留上次所选的目的地，这里不保留
				uni.removeStorageSync('targetcopy');
			},






			// 把乘客的数据传给后端，同时定时请求，检测是否有司机接单，并获取更新司机状态
			getDriverStatus() {
				//要传给后端订单表中，关于乘客的相关的信息
				let storage = uni.getStorageSync('userinfo');
				if (storage) {
					let userinfo = JSON.parse(storage);

					console.log(userinfo.user);
					let userid = userinfo.user.id;

					// let userid = userinfo.user.id;
					// let userid = userinfo.user.id;
				}

				this.driverStatusInfo = '正在为您匹配司机...';
				// 定义超时时间
				const timeout = 10000; // 10秒钟
				//let timer = null; // 先声明 timer 变量

				this.timer = setInterval(() => {
					timer = setTimeout(() => {
						uni.showToast({
							title: '请求超时，请稍后再试',
							icon: 'none'
						});


						// 清除定时器和超时计时器
						clearInterval(this.timer);
						clearTimeout(timer);
					}, timeout);

					uni.request({
						url: '', // 您的后端接口地址
						method: 'GET',
						success: function(res) {
							// 如果返回成功，清除计时器
							clearTimeout(timer);
							console.log(JSON.stringify(res.data));

							// 判断后端返回的数据是否是司机接单状态
							this.driverStatusInfo = res.data.driverStatusInfo; // 
							if (res.data.driverStatusInfo === '1') {
								// 如果是司机接单状态，改变 driverStatusInfo 的值
								this.driverStatusInfo = '司机已接单';
								clearInterval(this.timer); // 清除定时器
							} else if (res.data.driverStatusInfo === '0') {
								this.driverStatusInfo = '请稍候...';
							} else if (res.data.driverStatusInfo === '2') {
								this.driverStatusInfo = '无司机接单，可重新尝试！'
								clearInterval(this.timer); // 清除定时器
							}
						},
						fail: (error) => {
							uni.showToast({
								icon: 'none',
								title: '叫车失败,请稍后重试'
							});

							// 清除定时器和超时计时器
							clearInterval(this.timer);
							clearTimeout(timer);
						}
					});
				}, 3000); // 每隔5秒钟发起一次请求
			},



			toDestination() {

				uni.navigateTo({ //跳转后 保留页面
					url: "/pages/destination/destination"
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

		.status-bar {

			position: relative;
			/* 相对定位 */
			width: 100vw;
			/* 宽度和页面一致 */
			height: 10vw;
			/* 设置合适的高度 */
			line-height: 10vw;
			/* 将文本垂直居中 */
			text-align: center;
			/* 文本居中 */
			bottom: 0;
			/* 距离底部0 */
			background-color: #fff;
			/* 设置背景色 */
			box-shadow: 2px 2px 4px 4px #ccc;
			/* 添加阴影效果 */
			font-size: 4vw;
			/* 设置合适的字体大小 */
			color: #333;
			/* 设置文本颜色 */

			.trumpetpic {
				position: absolute;
				/* 绝对定位 */
				left: 40rpx;
				/* 将喇叭固定在左侧 */
				top: 50%;
				/* 将喇叭垂直居中 */
				transform: translateY(-50%);
				/* 调整喇叭的垂直位置 */
				width: 42rpx;
				/* 设置喇叭图片的宽度 */
				height: 42rpx;
				/* 设置喇叭图片的高度 */
			}
		}

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