<template>
	<view class="login">

		<view class="title">用户登录</view>

		<view>
			<input class="info" placeholder="请输入账号" v-model="username" />
			<input class="info" password placeholder="请输入密码" v-model="password" />
		</view>


		<view class="code-img-wrapper" @click="updateImageCode">
			<input class="info" style="width: 70%;" placeholder="请输入验证码" v-model="graphicVerifyCode" />
			<canvas style="width:30%; height:100rpx ; background-color: azure; border-radius: 50rpx;"
				canvas-id="canvas"></canvas>
		</view>

		<view>
			<radio-group style="margin: 26rpx 0rpx;" class="userselect" name="uc" @change="radioChange">
				<image src="../../static/passger.png" class="usercalss"></image>
				<radio value="0">乘客</radio>
				<text class="dis"></text>
				<image src="../../static/driver.png" class="usercalss"></image>
				<radio value="1">司机</radio>
			</radio-group>
		</view>

		<view>
			<button type="primary" class="bot" v-on:click="sub">登录</button>
		</view>

		<view class="tips">
			<navigator style="color: skyblue;" url="/pages/tellogin/tellogin">手机验证码登录</navigator><text
				class="text">|</text>
			<navigator url="/pages/sign-in/sign-in">没有账号?点击注册</navigator>
		</view>


	</view>
</template>

<script>
	import {
		Mcaptcha
	} from '@/utils/mcaptcha'

	export default {
		data() {
			return {
				graphicVerifyCode: "", //前端简单验证
				username: "",
				password: "",
				userclass: "" //登录时选择的用户类型：0代表乘客，1代表司机
			}
		},

		onLoad() {
			this.mcaptcha = new Mcaptcha({
				el: 'canvas',
				width: 80,
				height: 35,
				createCodeImg: ""
			});
		},

		methods: {

			// 刷新验证码
			updateImageCode() {
				this.mcaptcha.refresh()
			},

			// 提交前校验图形验证码
			submit() {
				if (!this.graphicVerifyCode) {
					uni.showToast({
						title: '请输入图形验证码',
						icon: 'error'
					})
					return true
				}
				let validate = this.mcaptcha.validate(this.graphicVerifyCode)
				if (!validate) {
					uni.showToast({
						title: '图形验证码错误',
						icon: 'error'
					})
					return true
				}
				return false

			},

			//登录验证
			sub() {
				if (this.submit()) {
					return
				}

				if (this.username && this.password) {
					uni.request({
						url: " http://localhost:8023/user/login/0",
						method: 'POST',
						data: {
							user: this.username,
							password: this.password,
						},
						success: (res) => {
							console.log("响应结果:", res.data.data)
							if (res.data.status == 0) {
								//跳转到主页
								uni.setStorageSync('userinfo', JSON.stringify(res.data.data))
								uni.setStorageSync('Token', res.data.data.token);
								uni.setStorageSync('Type',this.userclass);
								console.log('token:', res.data.data.token);
								if( this.userclass==0)
								uni.switchTab({
									url:"/pages/index/index"
								})
								else {
									uni.navigateTo({
										url:"/pages/orderinfo/orderinfo"
									})
								}
							} else {
								//
								uni.showToast({
									title: '登录失败!',
									icon: 'error'
								})
							}

						}
					})
				} else {
					uni.showToast({
						title: "不能为空",
						icon: 'error'
					})
				}
			},

			//用户类型选择
			radioChange(evt) {
				this.userclass = evt.detail.value
			}


		}
	}
</script>

<style lang="scss">
	.login {
		padding: 0 40rpx;

		.title {
			margin-top: 120rpx;
			margin-bottom: 50rpx;
			font-size: 30px;
			color: blue;
			text-align: center;
		}

		.info {

			margin-bottom: 20rpx;
			height: 100rpx;
			background-color: #f7f7f7;
			border-radius: 50rpx;
			letter-spacing: 5rpx; //字间距离
			text-indent: 50rpx;
		}

		.bot {
			border-radius: 60rpx;
			margin-top: 15px;
		}

		.code-img-wrapper {
			display: flex;
			// justify-content: center;
		}

		.tips {
			margin-top: 15px;
			font-size: 14px;
			color: #aaa;
			text-align: center;
			display: flex;
			justify-content: center;

			.text {
				margin: 0 10px;
			}
		}

		.userselect {
			display: flex;
			justify-content: center;

			.usercalss {
				height: 50rpx;
				width: 50rpx;
			}

			.dis {
				margin: 0rpx 65rpx;
			}
		}
	}
</style>