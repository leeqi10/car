<template>
	<view class="login">
		<!-- 标题 -->
		<view class="title">
			账号密码登录
		</view>
		
		<!-- 两个输入框 -->
		<!-- 用户名 -->
		<input type="text" class="inp" v-model="username" placeholder="请输入用户名">
		<!-- 密码 -->
		<input type="text" class="inp" v-model="password" placeholder="请输入密码" password>
		<!-- 按钮 -->
		<button type="primary" class="btn" v-on:click="login">登录</button>
		
		<!-- 一些提示文本 -->
		<view class="tips">
			<text class="item blue">验证码登录</text>
			<text class="item">忘记密码？</text>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				username:"",
				password:""
			}
		},
		methods: {
			login(){
				// 登录
				// 1. 在前端做一个简单的验证
				// 2. 验证通过：发请求
				// 3. 验证不通过：提示用户
				
				if(this.username && this.password){
					// 通过
					uni.request({
						url:"http://localhost:8023/sayHi",
						method:"POST",
						header:{
							'Content-Type':'application/json'
						},
						data:{
							username:this.username,
							password:this.password
						},
						success:(res)=>{
							console.log("响应的结果", res)
							if(res.status==0){
								uni.showToast({
									title:res.data.msg,
									icon:"success"
								})
							}else{
								uni.showToast({
									title:res.data.msg,
									icon:"error"
								})
							}
							// 根据响应的结果判断是否登录成功
							// 成功的话页面跳转+存储数据
							
							// 不成功的话 就提示用户
						}
					})
					
					
				}else{
					// 不通过
					uni.showToast({
						title:"不能为空",
						icon:"error"
					})
				}
				
			}
		}
	}
</script>

<style lang="scss">
	.login{
		padding: 0 60rpx;
		
		.title{
			font-size: 60rpx;
			color: #0f80ff;
			margin: 100rpx 0;
		}
		
		
		.inp{
			background-color: #f7f7f7;
			height: 100rpx;
			border-radius: 60rpx;
			padding-left: 40rpx;
			margin-bottom: 40rpx;
		}
		
		.btn{
			border-radius: 60rpx;
			margin-top: 100rpx;
		}
		
		.tips{
			margin-top: 40rpx;
			font-size: 14px;
			color: #aaa;
			text-align: center;
			
			.item{
				padding: 0 30rpx;
				line-height: 1 !important;
			}
			
			.blue{
				color: #0f80ff;
				// 竖线
				border-right: 1px solid #aaa;
			}
		}
	}
</style>
