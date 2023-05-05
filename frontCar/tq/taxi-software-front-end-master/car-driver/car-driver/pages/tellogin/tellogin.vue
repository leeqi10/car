<template>
	<view class="login">
		<view class="content-wrapper">
			<view class="title">
				<h1>手机验证登录</h1>
			</view>

			<view class="login-form">
				<view class="login-form-items">
					<view class="login-form-items-title">手机号</view>
					<input type="text" class="login-input" placeholder="请输入手机号" v-model="phone" />
				</view>

				<view class="login-form-items">
					<view class="login-form-items-title">验证码</view>
					<input type="text" class="login-input" placeholder="请输入验证码" v-model="code" />
					<view class="login-form-items-title"><button class="yanz" type="primary"
							v-bind:disabled="btndisabled" v-on:click="getcode">{{botstr}}</button></view>
				</view>

			</view>

		</view>

		<view>
			<radio-group style="margin: 26rpx 0rpx;" class="userselect" name="uc" @change="radioChange">
				<image src="../../static/passger.png" mode="" class="userclass"></image>
				<radio value="0">乘客</radio>
				<text class="dis"></text>
				<image src="../../static/driver.png" mode="" class="userclass"></image>
				<radio value="1">司机</radio>
			</radio-group>
		</view>

		<div class="submit-wrapper">
			<button type="primary" class="login-btn" v-on:click="sub">登录</button>
		</div>

	</view>


</template>

<script>
	export default {
		data() {
			return {
				btndisabled: false,
				botstr: "获取验证码",
				phone: "",
				code: "",
				userclass: 0, // 登录时选择的用户类型：0代表乘客，1代表司机
				// uuid:""
			}
		},
		methods: {

			//获取验证码后，60s不能再获取
			timewait(t) {
				var _this = this;
				setTimeout(function() {
					if (t >= 0) {
						_this.botstr = t + '秒后点击';
						t--;
						_this.timewait(t);
					} else {
						_this.botstr = '获取验证码';
						t = 60;
						_this.btndisabled = false
					}
				}, 900)
			},


			//用户类型选择
			radioChange(evt) {
				this.userclass = evt.detail.value
			},

			//获取验证码
			getcode() {
				if (!(/^1[13456789]\d{9}$/.test(this.phone))) {
					uni.showToast({
						title: "手机输入不合法！",
						icon: 'error'
					})
					return
				} else {
					uni.request({
						url: "", //后端验证码接口
						method: 'GET',
						data: {
							tel: this.phone
						},
						success: (res) => {
							console.log("响应结果:", res.data.data)
							if (res.data.status == 200) {
								//获取验证码
								this.code = res.data.code
								console.log(res);
							} else {

								uni.showToast({
									title: '获取验证码失败!',
									icon: 'error'
								})
							}

						},
						fail: (err) => {
							console.log(err);
							uni.showToast({
								title: '获取验证码失败!',
								icon: 'error'
							})
						}
					})
					this.timewait(60) //等待60s
					this.btndisabled = true //按钮不能点击
				}

			},

			//登录
			sub() {
				if (this.phone && this.code) {
					uni.request({
						url: "", //后端手机登录接口
						method: 'POST',
						data: {
							//是否需要向后端发送司机/乘客选项？
							//这个只需要通过电话登录，若没找到，则显示未注册，不用自动注册。
						},
						success: (res) => {
							console.log("响应结果:", res.data.data)
							if (res.data.status == 200) {
								//跳转到主页
								//！！！需要根据用户选择的类型进行跳转！！！
								uni.setStorageSync('userinfo', JSON.stringify(res.data.data))
								uni.setStorageSync('Token', res.data.data.token);
								uni.setStorageSync('Identity', this.userclass); //用户身份，其他页面可能需要校验
								console.log('token:', res.data.data.token)
								//存储token
								uni.switchTab({
									url: this.userclass == 0 ? "/pages/index/index":"/pages/orderinfo/orderinfo"
								})
							} else {
								//
								uni.showToast({
									title: '登录失败!',
									icon: 'error'
								})
							}

						},
						fail: (err) => {
							console.log("登录失败", err);
							uni.showToast({
								title: '登录失败!',
								icon: 'error'
							})
						}
					})
				} else {
					uni.showToast({
						title: '请把信息填写完整!',
						icon: 'error'
					})
					return
				}
			}



		}
	}
</script>

<style lang="scss">
	page {
		background: #F4F5F6;

	}

	.login {
		.userselect {
			display: flex;
			justify-content: center;

			.userclass {
				height: 50rpx;
				width: 50rpx;
			}

			.dis {
				margin: 0rpx 65rpx;
			}
		}

		.content-wrapper {
			width: 100%;

			.title {
				margin-top: 35rpx;
				width: 100%;
				margin-bottom: 10px;

				h1 {
					border: 0px;
					width: 50%;
					margin: 0 auto;
					text-align: center;
					border-bottom: 1px solid #E3E3E3;
					height: 50px;
					line-height: 50px;
					overflow: hidden;
					font-weight: 400;
					font-size: 38rpx;

				}
			}

			.login-form {
				margin: 20px 10px 20px 15px;
				background: #FFFFFF;

				.login-form-items {
					padding: 15px 10px;
					border-bottom: 1px solid #F3F4F5;
					position: relative;
					display: -webkit-flex;
					display: flex;


					.login-form-items-title {
						width: 30%;
						line-height: 22px;
						height: 22px;
						flex-shrink: 0;

						.yanz {
							font-size: 22rpx;
						}
					}

					.login-input {
						width: 100%
					}

				}
			}
		}

		.submit-wrapper {
			padding: 10px;
			padding-top: 10px;
		}

	}
</style>