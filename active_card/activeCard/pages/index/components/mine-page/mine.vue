<template>
  <view class="page">
    <view class="user-info">
      <view class="info">
        <image src="../../../../static/软件图标.png" />
        <view class="user-desc">
          <text>昵称：{{ data.userName }}</text>

          <text class="name">ID：{{ data.id }}</text>
          <text class="name">邮箱：{{ data.email }}</text>
        </view>
      </view>
    </view>
    <view class="user-list">
   

      <view class="van-hairline--bottom">
        <text>用户资料</text>
        <text>></text>
      </view>
      <view class="van-hairline--bottom" @click="loginOut">
        <text>退出登录</text>
        <text>></text>
      </view>
    </view>
  </view>
</template>

<script>
import request from "../../../../request/index";

export default {
  props: {
    bottomHeight: {
      type: Number,
    },
  },
  data() {
    return {
      data: {},
    };
  },
  mounted() {
    this.userId = uni.getStorageSync("userId");

    this.getUserInfo();
  },
  methods: {
    loginOut() {
      uni.removeStorageSync("userId");

      uni.reLaunch({
        url: "/pages/login/login",
      });
    },
    getUserInfo() {
      request({
        method: "get",
        url: "/users/getUsersById",
        data: {
          id: this.userId, // id 必须
        },
      }).then((res) => {
        console.log(res);
        this.data = res.data;
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.page {
  width: 100vw;
  height: 100vh;
  padding: 24rpx;
  display: flex;
  flex-direction: column;
  background-color: #f2f2f2;
}
.user-info {
  background: linear-gradient(90deg, #51c7c7, #51c7c7);
  box-shadow: 0 2rpx 5rpx #269090;
  border-radius: 24rpx;
  .info {
    position: relative;
    display: flex;
    padding: 24rpx;
    box-sizing: border-box;
    image {
      width: 200rpx;
      height: 200rpx;
      border-radius: 50%;
    }
    .user-desc {
      display: flex;
      flex-direction: column;
      margin-left: 24rpx;
      font-size: 28rpx;
      color: #fff;
      justify-content: space-around;
      text {
        color: #fff;
        font-size: 28rpx;
        padding: 2rpx 0;
      }
    }
  }
}
.user-list {
  margin-top: 24rpx;
  background-color: #fff;
  border-radius: 24rrpx;
  box-shadow: rgba(0, 0, 0, 0.15) 2rpx 2rpx 2rpx;
  .van-hairline--bottom {
    padding: 24rpx;
    display: flex;
    justify-content: space-between;
    font-size: 28rpx;

    .van-icon-arrow {
      margin-top: 13rpx;
    }
  }
}
</style>
