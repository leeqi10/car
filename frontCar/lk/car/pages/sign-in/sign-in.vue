<template>
	<view class="login">
		<view class="content-wrapper">
			<view class="title">
				<h1 >账号注册</h1>
			</view>
	
			<view class="login-form">
				
				
				<view class="login-form-items">
					<view class="login-form-items-title">账号</view>
					<input type="text" class="login-input"  placeholder="请输入账号" v-model="user" />
				</view>
				
				
				<view class="login-form-items">
					<view class="login-form-items-title">密码</view>
					<input type="text" class="login-input"  placeholder="请输入密码" v-model="pwd" />
				</view>
				
				
				<view class="login-form-items">
					<view class="login-form-items-title">确认密码</view>
					<input type="text" class="login-input"  placeholder="请再次输入密码" v-model="compwd" />
				</view>
					
				<view v-if="userclass==1">
					<view class="login-form-items">
						<view class="login-form-items-title">驾驶证号</view>
						<input type="text" class="login-input"  placeholder="请输入驾驶证号" v-model="driverid" />
					</view>
					
					<view class="login-form-items">
						<view class="login-form-items-title">车牌号</view>
						<input type="text" class="login-input"  placeholder="请输入车牌号" v-model="carid" />
					</view>
					
					<view class="login-form-items">
						<view class="login-form-items-title">车型</view>
						<input type="text" class="login-input"  placeholder="请输入车型" v-model="carclass" />
					</view>
				</view>
				
				
				<view class="login-form-items">
					<view class="login-form-items-title">手机号</view>
					<input type="text" class="login-input"  placeholder="请输入手机号" v-model="phone" />
				</view>

				<view class="login-form-items" >
					<view class="login-form-items-title">验证码</view>
					<input type="text" class="login-input" placeholder="请输入验证码" v-model="code" />
					<view class="login-form-items-title"><button class="yanz" type="primary" v-bind:disabled="btndisabled" v-on:click="getcode">{{botstr}}</button></view>
				</view>
					
			</view>
				
		</view>
			
		<view>
			<radio-group style="margin: 26rpx 0rpx;" class="userselect" name="uc"  @change="radioChange">
				<image src="../../static/passger.png" mode="" class="userclass"></image><radio  value="0">乘客</radio>
				<text class="dis"></text>
				<image src="../../static/driver.png" mode="" class="userclass"></image><radio  value="1">司机</radio>
			</radio-group>
		</view>
			
		<div class="submit-wrapper">
			<button type="primary" class="login-btn" v-on:click="sgin_in">注册</button>
		</div>
		
	</view>


</template>

<script>
	export default {
		data() {
			return {
				user:"",			//账号
				pwd:"",				//密码
				compwd:"",			//确认密码
				driverid:"",		//驾驶证号
				carid:"",			//车牌号
				carclass:"",		//车型
				btndisabled:false,
				botstr:"获取验证码",
				phone:"",
				code:"",
				userclass:0,	// 登录时选择的用户类型：0代表乘客，1代表司机
				// uuid:""
			}
		},
		methods: {	
			
			//获取验证码后，60s不能再获取
			timewait(t){
				var _this=this;
				setTimeout(function(){
					if(t>=0){
						_this.botstr=t+'秒后点击';
						t--;
						_this.timewait(t);
					}else{
						_this.botstr='获取验证码';
						t=60;
						_this.btndisabled=false
					}
				},900)
			},
			
			
			//用户类型选择
			radioChange(evt){
				this.userclass=evt.detail.value
			},
			
			//获取验证码
			getcode(){
				if(!(/^1[13456789]\d{9}$/.test(this.phone))){
					uni.showToast({
						title:"手机输入不合法！",
						icon:'error'
					})
					return 
				}else{
					uni.request({
						url:"http://localhost:8023/user/code",//后端验证码接口
						method:'GET',
						data:{
							tel:this.phone
						},
						success:(res)=>{
							console.log("响应结果:",res.data.data)
							if(res.data.status==200){
								//获取验证码
								 this.code =  res.data.data.code
								
							}else{
					
								uni.showToast({
									title:'获取验证码失败!',
									icon:'error'
								})
							}
									
						},
					})
					this.timewait(60)		//等待60s
					this.btndisabled=true	//按钮不能点击
				}

			},
			
			//注册
			sgin_in(){
				if(this.userclass==0){	//表示注册时用户是乘客
					//判断是否有空的输入项
					if(this.user&&this.pwd&&this.compwd&&this.phone&&this.code){	
						//判断两次密码是否一致
						if(this.pwd==this.compwd){
							uni.request({
								url:"http://localhost:8023/user/login/"+this.code,				//乘客后端注册接口
								method:'POST',
								data:{
									user:this.user,
									password:this.pwd,
								//	compwd:this.compwd,			//确认密码
									tel:this.phone,			//电话
									//code:this.code,				//验证码
								//	userclass:this.userclass	//乘客：0；司机1
								},
								success:(res)=>{
									console.log("响应结果:",res.data.data)
									if(res.data.status==200){
										//跳转到登录页面
										// uni.setStorageSync('userinfo',JSON.stringify(res.data.data))
										 uni.setStorageSync('Token',res.data.data.token);
										 console.log('token:',res.data.data.token)
										uni.showToast({
											title:'恭喜你,注册成功!',
											icon:'error'
										})
										//3s跳转到登录页面
										setTimeout("console.log('即将跳转到登录页面')", 3000);	
										uni.switchTab({
											url:"/pages/login/login"	
										})
									}else{
										//
										uni.showToast({
											title:'注册失败!',
											icon:'error'
										})
									}
										
								}
							})
								
						}else{
							uni.showToast({
								title:'两次输入的密码不对!',
								icon:'error'
							})
							return
						}
						
					}else{
						uni.showToast({
							title:'请把信息填写完整!',
							icon:'error'
						})
						return
					}
					
					
				}else{					//表示注册是用户是司机
				
					//判断是否有空的输入项
					if(this.user&&this.pwd&&this.compwd&&this.phone&&this.code&&this.driverid&&this.carid&&this.carclass){	
						//判断两次密码是否一致
						if(this.pwd==this.compwd){
							uni.request({
								url:"http://localhost:8023/driver/login/"+this.code,				//司机后端注册接口
								method:'POST',
								data:{
									user:this.user,
									password:this.pwd,
									jsz:this.driverid,		//驾驶证号
									carno:this.carid,			//车牌号
									cartype:this.carclass,		//车类型
									tel:this.phone,			//电话
								//	code:this.code,				//验证码
								//	userclass:this.userclass	//乘客：0；司机1
								},
								success:(res)=>{
									console.log("响应结果:",res.data.data)
									if(res.data.status==200){
										//跳转到登录页面
										// uni.setStorageSync('userinfo',JSON.stringify(res.data.data))
										uni.setStorageSync('Token',res.data.data.token);
										console.log('token:',res.data.data.token)
										
										uni.showToast({
											title:'恭喜你,注册成功!',
											icon:'error'
										})
										
										//3s跳转到登录页面
										setTimeout("console.log('即将跳转到登录页面')", 3000);	
											
										uni.switchTab({
											url:"/pages/login//login"	
										})
									}else{
										//
										uni.showToast({
											title:'注册失败!',
											icon:'error'
										})
										return
									}
										
								}
							})
								
						}else{
							uni.showToast({
								title:'两次输入的密码不对!',
								icon:'error'
							})
							return
						}
						
					}else{
						uni.showToast({
							title:'请把信息填写完整!',
							icon:'error'
						})
						return
					}					
					
					
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
		.userselect{
			display: flex;
			justify-content: center;
			.userclass{
				height: 50rpx;
				width: 50rpx;
			}
			.dis{
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
						.yanz{
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
