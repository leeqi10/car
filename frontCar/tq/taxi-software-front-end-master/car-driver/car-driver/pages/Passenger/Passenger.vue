<template>
	<view class="index">
		<!-- 1.地图 -->
		<!-- v-bind: 是动态绑定，后面是变量，不是字符串-->
		<!-- 	<map id="myMap" v-bind:longitude="longitude" v-bind:latitude="latitude" v-bind:markers="markers">
		</map> -->
		<map id="myMap" :longitude="longitude" :latitude="latitude" :markers="markers">
			<!-- 使用 v-for 遍历 markers 数组 -->
			<map-marker v-for="(item, index) in markers" :key="index" :longitude="item.longitude"
				:latitude="item.latitude" :iconPath="item.iconPath" :width="item.width"
				:height="item.height"></map-marker>
		</map>

		<view class="mask">
<!-- 先使用手动更新 -->
			<view class="status-bar" @click="upDate">
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
	//1.导入插件
	import amapFile from '../../libs/amap-wx.130.js'


	export default {
		data() {
			return {
				myAmapFun: undefined,
				//用于存放开始位置
				start: '',
				longitude: '',
				latitude: '',
				//编辑点相关的信息
				markers: [],
				//存放目的地
				target: '',
				targetcopy: '',
				//目的地的经纬度和地址
				lng: '',
				lat: '',
				endadress: '',

				
				token: '',
				//存放用户信息
				userinfo: '',
				//存放订单信息：
				orderId: '',
				


				dest: '',
				driverStatusInfo: '请稍候...', // 司机状态
			}
		},

		onLoad() {
			//2.创建实例对象以便于后面可以调用它的方法
			this.myAmapFun = new amapFile.AMapWX({
				key: 'ceb72741060af13228ee8c0eb730b40d'
			});

			//3.调用调用方法获取位置信息
			//setInterval(() => {
			this.getLocation();
			//}, 5000);



		},
		onShow() { //只要页面显示
			this.token=uni.getStorageSync('Token');
			console.log(this.token);
			
			this.target = uni.getStorageSync('target') ? JSON.parse(uni.getStorageSync('target')) : '请选择目的地';
			console.log('this.target', this.target)
			this.targetcopy = uni.getStorageSync('targetcopy') ? JSON.parse(uni.getStorageSync('target')) : '请选择目的地';
			this.dest = this.targetcopy.name;
			if (!this.dest || this.dest === '请选择目的地') {
				this.dest = '';
			}

			//根据需求，是否要保留上次所选的目的地，这里不保留
			uni.removeStorageSync('targetcopy');
			uni.removeStorageSync('target');

			//获取打车目的地的经纬度
			let location = this.target["location"].split(',')
			this.lng = location[0]
			this.lat = location[1]
			this.endadress = this.target["name"]
			console.log("目的地经度是:", this.lng)
			console.log("目的地纬度是:", this.lat)
			console.log("目的地名称是:", this.endadress)


			//获取位置信息之后，添加目的地标记点	
			const endMarkerExists = this.markers.some((marker) => marker.id === 2);
			if (endMarkerExists) {
				const endMarkerIndex = this.markers.findIndex((marker) => marker.id === 2);
				this.markers[endMarkerIndex].longitude = this.lng;
				this.markers[endMarkerIndex].latitude = this.lat;
			} else {

				const newEndMarker = {
					id: 2,
					longitude: this.lng,
					latitude: this.lat,
					//title: this.endadress,
					iconPath: '../../static/endaddress.png',
					width: 50,
					height: 50
				};
				this.markers.push(newEndMarker);
			}

			console.log('this.markers', this.markers)
		},

		methods: {

			getLocation() { //获取位置信息		

				this.myAmapFun.getRegeo({
					success: (res) => {
						console.log('当前位置信息', res);
						this.start = res[0].name;
						this.longitude = res[0].longitude;
						this.latitude = res[0].latitude;

						//获取位置信息之后，添加起始标记点	
						let obj = {
							id: 1,
							latitude: this.latitude,
							longitude: this.longitude,
							iconPath: '../../static/start.png',
							width: 50,
							height: 50
						}
						//添加到markers数组里面
						this.markers.push(obj);



						console.log('this.markers', this.markers)

					},
					//获取位置信息失败
					fail: (err) => {
						console.log('获取位置失败', err)
					}
				})
			},


			getDriverStatus() {
				uni.request({
					url: "http://localhost:8023/driver/alterOrder",
					method: 'POST',
					header: {
						'Content-Type': 'application/json',
						'token': this.token
					},
					data: {
						"userid": this.userinfo['user'],
						"callx": this.longitude,
						"cally": this.latitude,
						"staddr": this.start,
						"endx": this.lng,
						"endy": this.lat,
						"endaddr": this.dest,
						"melige": ""
					},
					success: (res) => {
						console.log(res); //返回订单对象
						if (res.statusCode == 200) {
							uni.showToast({
								icon: 'success',
								title: '下单成功！'
							});
							this.orderId = res.data.data.id;
							console.log('orderid', this.orderId);
						} else {
							console.log('下单失败');
							uni.showToast({
								icon: 'error',
								title: '下单失败'
							})
						}
						//upDate();
					},
					fail: (err) => {
						console.log('下单失败', err);
						uni.showToast({
							icon: 'error',
							title: '下单失败'
						})
					}
				})
			},
			upDate() {
				uni.request({
					url: 'http://localhost:8023/driver/getOrderPlus?id=' + this.orderId,
					method: 'GET',
					success:(res)=> {
						console.log(res);
						if (res.statusCode == 200) {
							if (res.data.data.code != 0) {
								this.driverStatusInfo = '叮！司机正在赶来的路上~';
							} else {
								console.log('暂无司机接单', res.data.data.code);
								this.driverStatusInfo='暂无司机接单';
							}
						}
					},
					fail: (err) => {
						console.log('订单更新失败', err);
						uni.showToast({
							icon: 'error',
							title: '订单更新失败'
						})
					}

				})
			},


			//跳转到搜索目的地页面
			toDestination() {
				uni.navigateTo({ //跳转后 保留页面
					url: "/pages/destination/destination"
				})
			}


		}


	}
</script>

<style lang="scss">
	.index {
		width: 100vw;
		height: 100vh;

		#myMap {
			width: 100vw;
			height: 100vh;
		}

		//
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

	}
</style>