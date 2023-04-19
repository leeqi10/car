<template>
	<view class="index">
		<!-- 1.地图 -->
		<!-- v-bind: 是动态绑定，后面是变量，不是字符串-->
		<map id="myMap" v-bind:longitude="longitude" v-bind:latitude="latitude" v-bind:markers="markers">
		</map>

		<Driver  :start="start" ref="driver" v-if="Type === '1'"></Driver>

		<Passenger ref="passenger" :start="start" :driver-status-info="driverStatusInfo"
			@get-driver-status="get_Driver_Status" v-if="Type === '0'">

		</Passenger>

			
	</view>
</template>

<script>
	//1.导入插件
	import amapFile from '../../libs/amap-wx.130.js'
	//导入子组件
	import Passenger from '../../components/Passenger/Passenger.vue'
	import Driver from '../../components/Driver/Driver.vue'


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
				driverStatusInfo: '请稍候...', // 司机状态
				driverStatus: '', //司机状态码
				timer: null, // 定时器  模拟司机  测试效果，后端写好后 删除
				Type: '' //用户的类型

			}
		},

		onLoad() {
			//验证token 未登录用户进行拦截
			this.token = uni.getStorageSync('Token');
			if (this.token === '') {
				uni.navigateTo({
					url: '/pages/login/login'
				})
				return
			}
			//2.创建实例对象以便于后面可以调用它的方法
			this.myAmapFun = new amapFile.AMapWX({
				key: 'ceb72741060af13228ee8c0eb730b40d'
			});

			//3.调用调用方法获取位置信息
			this.getLocation();
			//获取storage中Type的值，判断用户的类型
			this.Type = uni.getStorageSync('Type');
			console.log("this.Type:" + this.Type);

		},
		onShow() { //只要页面显示
			
			const passenger = this.$refs.passenger; // 引用子组件 A
			this.$nextTick(() => {
			if (passenger.show) { 
				passenger.show(); // 调用子组件  的 show 方法
			}
			});
			
			const driver = this.$refs.driver; // 引用子组件 B
			this.$nextTick(() => {
				if (driver.show) { 
					driver.show(); // 调用子组件  的 show 方法
				}
			});
			
			
			
		},
		methods: {

			getLocation() {
				//调用方法获取位置信息
				this.myAmapFun.getRegeo({
					//获取位置信息成功
					success: (res) => {
						console.log('当前位置信息', res);
						this.start = res[0].name;
						console.log(this.start)
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



	}
</style>