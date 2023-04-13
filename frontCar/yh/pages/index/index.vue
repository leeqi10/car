<template>
	<view class="index">
		<!-- 1.地图 -->
		<!-- v-bind: 是动态绑定，后面是变量，不是字符串-->
		<map id="myMap" v-bind:longitude="longitude" v-bind:latitude="latitude" v-bind:markers="markers">
		</map>

		<view class="mask">
			<!-- 第一部分 -->
			<view class="maskItem">
				<image class="pic" src="../../static/myaddress.png" mode=""></image>
				<view class="place">
					{{start}}
				</view>
				<text class="now">当前</text>
			</view>

			<!-- *******************************下面两个部分做了修改，加了v-model  v-if******************************************* -->
			<!-- 第二部分 -->
			<view class="maskItem" @click="toDestination">
				<image class="pic" src="../../static/redaddress.png" mode=""></image>
				<view v-model="dest" class="place">
					{{target.name || '请选择目的地'}}
				</view>
			</view>
			<!-- 第三部分 -->
			<view class="maskItem" v-if="dest">
				<button type="primary" style="width: 100%;">一键叫车</button>
			</view>
			<!-- ************************************************************************** -->

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
				dest: ''
// *********************************添加了dest: ''*****************************************************************************************************
			}
		},

		onLoad() {
			//2.创建实例对象以便于后面可以调用它的方法
			this.myAmapFun = new amapFile.AMapWX({
				key: 'ceb72741060af13228ee8c0eb730b40d'
			});

			//3.调用调用方法获取位置信息
			this.getLocation();

		},
		onShow() { //只要页面显示
		// *********************************onShow()这个函数里做了修改*****************************************************************************************************
			this.target = uni.getStorageSync('target') ? JSON.parse(uni.getStorageSync('target')) : '请选择目的地';
			this.dest = this.target;
			if (!this.dest || this.dest === '请选择目的地') {
				this.dest = '';
			}

			//根据需求，是否要保留上次所选的目的地，这里不保留
			uni.removeStorageSync('target');
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


			//跳转到toDestination
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
			bottom: 1vw; //偏移值
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