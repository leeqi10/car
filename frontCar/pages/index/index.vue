<template>
	<view class="index">
		<!-- 地图 -->
		<map v-bind:longitude="longitude" v-bind:latitude="latitude" v-bind:markers="markers" id="myMap">
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
			<!-- 第二部分 -->
			<view class="maskItem" @click="toDestination">
				<image class="pic" src="../../static/redaddress.png" mode=""></image>
				<view class="place">
					{{target.name || "请选择目的地"}}
				</view>
			</view>
			<!-- 第三部分 -->
			<view class="maskItem">
				<button type="primary" style="width: 100%;">一键叫车</button>
			</view>
		</view>
	</view>

</template>

<script>
	import amapFile from "../../libs/amap-wx.js"
	export default {
		data() {
			return {
				myAmapFun: undefined,
				start: '',
				longitude: '',
				latitude: '',
				markers: [],
				target:''
			}
		},
		onLoad() {
			this.myAmapFun = new amapFile.AMapWX({
				key: '293291e5b982755b78f967ecc18bce6c'
			})

			//调用方法
			this.getLocaltion();

		},
		onShow() {
			this.target=uni.getStorage('target')?JSON.parse(uni.getStorageSync('target')):''
		},
		methods: {
			//获取当前函数
			getLocaltion() {
				//获取位置信息
				this.myAmapFun.getRegeo({
					success: (res) => {
						console.log('当前位置的信息', res);
						this.start = res[0].name;
						this.latitude = res[0].latitude;
						this.longitude = res[0].longitude;
						//添加标记点
						let obj = {
							id: 1,
							latitude: this.latitude,
							longitude: this.longitude,
							iconPath: '../../static/myaddress.png',
							width: 30,
							height: 30
						}
						this.markers.push(obj);
					},
					fail: (err) => {
						console.log('获取当前位置失败', err);
					}

				})
			},
			//to
			toDestination(){
				uni.navigateTo({
					url:'../distination/distination'
				})
			}
		}



	}
</script>

<style lang="scss">
	.index {
		width: 100vw;
		height: 100vh;
	}

	.index #myMap {
		width: 100vw;
		height: 100vh;
	}

	.index .mask {
		width: 98vw;
		background-color: #fff;
		position: fixed;
		left: 1vw;
		bottom: 1vw;
		box-shadow: 2px 2px 9px 9px #ccc;
	}

	.index .mask .maskItem {
		heigh: 120rpx;
		border: 1px solid #ccc;
		display: flex;
		padding: 0 40rpx;
		align-items: center;
	}

	.index .mask .maskItem .pic {
		width: 50rpx;
		height: 50rpx;
		margin-right: 40rpx;
	}

	.index .mask .maskItem .place {
		flex: 1;
		color: #999;
	}

	.index .mask .maskItem .now {
		background-color: cadetblue;
		color: #fff;
		font-size: 20rpx;
		padding: 5rpx;
	}
</style>