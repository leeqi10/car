
<template>
	<view class="login">
		
		<view class="title">用户登录</view>
		
		<view>
			<input class="info" placeholder="请输入账号" v-model="username"/>
			<input class="info" password placeholder="请输入密码" v-model="password"/>					
		</view>
			
		
		<view class="code-img-wrapper" @click="updateImageCode">
			<input class="info" style="width: 70%;" placeholder="请输入验证码" v-model="graphicVerifyCode"/>
			<canvas style="width:30%; height:100rpx ; background-color: azure;" canvas-id="canvas"></canvas>
		</view>
			
		<view>
			<button type="primary" class="bot" v-on:click="sub">登录</button>
		</view>
		
		<view class="tips">
			<navigator style="color: skyblue;" url="/pages/tellogin/tellogin">手机验证码登录</navigator><text class="text">|</text>
			<navigator  url="/pages/lookpsw/lookpsw">忘记密码?</navigator>
		</view>	

		
	</view>
</template>

<script>

	import { Mcaptcha  } from '@/utils/mcaptcha'	

	export default {
		data() {
			return {
				graphicVerifyCode:"",
				username:"",
				password:""
			}
		},
		
		onLoad(){
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
				if(!this.graphicVerifyCode) {
					uni.showToast({ title: '请输入图形验证码',icon:'error'})
					return true
				}
				let validate = this.mcaptcha.validate(this.graphicVerifyCode)
				if(!validate) {
					uni.showToast({ title: '图形验证码错误',icon:'error' })
					return true
				}
				return false

			},

			//登录验证
			sub(){
				if(this.submit()){
					return
				}
				
				if(this.username&&this.password){
					uni.request({
						url:"http://localhost:8023/login",
						method:'POST',
						data:{
							user:this.username,
							password:this.password
						},
						success:(res)=>{
							console.log("响应结果:",res.data.data)
							if(res.data.status==200){
								//跳转到主页
								uni.setStorageSync('userinfo',JSON.stringify(res.data.data))
								uni.setStorageSync('Token',res.data.data.token);
								console.log('token:',res.data.data.token)
								uni.switchTab({
									url:"/pages/index/index"
								})
							}else{
								
								//
								uni.showToast({
									title:'登录失败!',
									icon:'error'
								})
							}
							
						}
					})
				}else{
					uni.showToast({
						title:"不能为空",
						icon:'error'
					})
				}
			}
			
			
		}
	}
</script>

<style lang="scss">
	.login{
		padding: 0 40rpx;
		
		.title{
			margin-top: 120rpx;
			margin-bottom:50rpx;
			font-size: 30px;
			color: blue;			
			text-align: center;
		}
		
		.info{
				
				margin-bottom:20rpx ;
				height: 100rpx;
				background-color: #f7f7f7;
				border-radius: 50rpx;		
				letter-spacing: 5rpx;	//字间距离
				text-indent: 50rpx;
		}
		
		.bot{
			border-radius: 60rpx;
			margin-top: 15px;
		}
		
		.code-img-wrapper{
			display: flex;
			// justify-content: center;
		}
		.tips{
			margin-top: 15px;
			font-size: 14px;
			color: #aaa;
			text-align: center;
			display: flex;
			justify-content: center;
			
			.text{
				margin: 0 10px;
			}
		}
	}
	

</style>
