<template>
	<view class="info">
		<view class="header">
			<image @click="back" class="backTo" src="../../static/back.png"></image>
			<p class="per">个人中心</p>
			</view>
		<view class="detail">
			
			<view class="profile">
			<p class="text">头像</p>
			<image  class="img" src="../../static/icon.png"></image>
			<image style="height: 50rpx; width: 50rpx; " src="../../static/right.png"></image>
			</view>
			<view class="profile">
			<p class="text">昵称</p>
			<p class="message">{{this.nickname}}</p>
			<image class="modify" src="../../static/right.png"></image></view>
			<view class="profile">
			<p class="text">生日</p>
			<p class="message">{{this.birth}}</p>
			<image class="modify" src="../../static/right.png"></image></view>
			<view class="profile">
			<p class="text">ID</p>
			<p style="margin-left: 330rpx;
			width: 250rpx;text-align: right;">{{this.id}}</p>
			<image style="height: 50rpx; width: 50rpx;" src="../../static/right.png"></image></view>
			<view class="profile">
			<p class="text">年龄</p>
			<p class="message">{{this.age}}</p>
			<image class="modify" src="../../static/right.png"></image></view>
			<view class="profile">
			<p class="text">性别</p>
			<p class="message">{{this.sex}}</p>
			<image class="modify" src="../../static/right.png"></image></view>
			
			<view class="profile">
				<p class="text">余额</p>
				<p class="message">{{this.money}}</p>
				<image class="modify" src="../../static/right.png"></image></view>
		</view>
		<view class="logout">
			<button type="warn" class="btn1">退出登录</button>
			<button type="primary" class="btn2">注销账号</button>
		</view>
		<view class="tip">遇到问题? 点击联系客服</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
			nickname:"晓明",
			birth:"3.12",
			id:"56789",
			age: 20,
			sex:"男",
			money: 200
			}
		},
		methods: {
			back(){
				uni.navigateTo({
					url: '../index/index',
				});
			}
		}
	}
</script>

<style lang="scss">
	.info{
		.header{
			display:flex;
			align-items: center;
			background-color: #0f80ff;
			height: 100rpx;
			
			.backTo{
					height: 50rpx;
					width: 50rpx;
					vertical-align: middle;
					margin-left: 20rpx;
			}
			.per{
				flex: 1;
				font-size: 50rpx;
				text-align: center;
				color: white;
			}
		}
		.detail{
			.profile{
				display: flex;
				align-items: center; 
				height: 100rpx;
				border-width: 1px;
				border-color: #ccc;
				border-bottom-style:solid;
				font-size: 30rpx;
				.img{
				 margin-left: 495rpx; 
				   margin-right: 0;
					height: 60rpx;
					width: 60rpx;
				}
				.modify{
					/* margin-left: 550rpx; */
					height: 50rpx;
					width: 50rpx;
				}
				.text{
					margin-left: 50rpx;
					
				}
				.message{
					margin-left: 300rpx;
					width: 250rpx;
					text-align: right;
				}
		}
		
		
			
		}
		.logout{
			.btn1{
				width: 80%;
				margin-top: 200rpx;
				border-radius: 30rpx;
			}
			.btn2{
				width: 80%;
				margin-top: 50rpx;
				border-radius: 30rpx;
				background-color: darkgray;
			}
			
		}
		.tip{
			margin-top: 30rpx;
			text-align: center;
			color: grey;
		}
	}
	

</style>
