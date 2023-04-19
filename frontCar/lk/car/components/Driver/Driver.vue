<template>
	<view>
		<view class="mask">

			<view class="maskItem">
				<image class="pic" src="../../static/myaddress.png" mode=""></image>
				<view class="place">
					{{start}}
				</view>
				<text class="now">当前</text>
			</view>



		</view>
	</view>

</template>

<script>
	export default {
		props: {
			start:String
		},
		name: "Driver",
		data() {
			return {

			};
		},

		created() {
			console.log("this.start" + this.start);
		},
		methods: {

		}
	}
</script>

<style lang="scss">
.mask {
		width: 98vw;
		background-color: white;
		position: fixed; //固定地位
		left: 1vw; //偏移值
		bottom: 15vw; //偏移值
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

</style>