<template>
<view class="index">
		<!-- 1.地图 -->
		<!-- v-bind: 是动态绑定，后面是变量，不是字符串-->
		<map id="myMap" v-bind:longitude="longitude" v-bind:latitude="latitude" v-bind:markers="markers">
		</map>

		<view class="mask">



			<view class="maskItem">
				<image class="pic" src="../../static/redaddress.png" mode=""></image>
				<view class="place">
					{{this.Dendaddress}}
				</view>
				<text class="now">目的地</text>
			</view>


			<view class="maskItem">
				<image class="pic" src="../../static/myaddress.png" mode=""></image>
				<view class="place">
					{{start}}
				</view>
				<text class="now">当前位置</text>
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
				//用于存放司机开始位置
				start: '',
				longitude: '',
				latitude: '',
				//编辑点相关的信息
				markers: [],
				//订单中的目的地
				Dlongitude: '103.96083',
                Dlatitude: '30.77896',
				Dendaddress:'西华大学',

				Order:'',
				token: '',
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
		onShow() {
			this.getAddress();
		},
		methods: {


			getLocation() {
				//调用方法获取位置信息
				this.myAmapFun.getRegeo({
					//获取位置信息成功
					success: (res) => {
						console.log('当前位置信息', res);
						this.start = res[0].name;
						this.longitude = res[0].longitude;
						this.latitude = res[0].latitude;

						//获取位置信息之后，添加标记点
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
					},
					//获取位置信息失败
					fail: (err) => {
						console.log('获取位置失败', err)
					}
				})
			},


			getAddress(){   //获取目的地信息
				this.Order = JSON.parse(uni.getStorageSync('Order'));
				console.log(this.Order);                         //等待 ordinfo写完
				this.Dlongitude = this.Order['cally'];   //目的地经度
				this.Dlatitude = this.Order['callx'];     //目的地维度
				this.Dendaddress = this.Order['endaddr'];            //目的地名字
				//alert(JSON.parse(this.Order));
				//获取位置信息之后，添加目的地标记点
				const endMarkerExists = this.markers.some((marker) => marker.id === 2);
				if (endMarkerExists) {
					const endMarkerIndex = this.markers.findIndex((marker) => marker.id === 2);
					this.markers[endMarkerIndex].longitude = this.lng;
					this.markers[endMarkerIndex].latitude = this.lat;
				} else {
				
					const newEndMarker = {
						id: 2,
						longitude: this.Dlongitude,
						latitude: this.Dlatitude,
						//title: this.Dendaddress,
						iconPath: '../../static/endaddress.png',
						width: 50,
						height: 50
					};
					this.markers.push(newEndMarker);
				}
				
				console.log('this.markers', this.markers)
				
				
				
				
				
				
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
			bottom: 14vw; //偏移值
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

	}
</style>
