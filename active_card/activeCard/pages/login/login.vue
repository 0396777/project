<template>
  <view class="page">
    <Image class="logo" src="/static/公司图像.png" alt="" />
    <view class="login-body login">
      <view class="label-item">
        账号：
        <input v-model="form.userName" type="text" placeholder="请输入账号" />
      </view>

      <view class="label-item">
        密码：
        <input v-model="form.password" type="text" placeholder="请输入账号" />
      </view>
      <view class="label-item" v-if="!isLogin">
        邮箱：
        <input v-model="form.email" type="text" placeholder="请输入邮箱" />
      </view>

      <u-radio-group v-model="isLogin">
        <u-radio :name="1"> 登录 </u-radio>
        <u-radio :name="0"> 注册 </u-radio>
      </u-radio-group>

      <Button
        v-if="isLogin"
        :style="{
          marginTop: '24rpx',
        }"
        type="primary"
        @click="goLogin"
      >
        登录
      </Button>
      <Button
        v-if="!isLogin"
        :style="{
          marginTop: '24rpx',
        }"
        @click="register"
      >
        注册
      </Button>
      <Button
        v-if="isLogin"
        :style="{
          marginTop: '24rpx',
        }"
      >
        忘记密码
      </Button>
    </view>
  </view>
</template>

<script>
import request from "../../request/index";
export default {
  data() {
    return {
      form: {
        userName: "",
        password: "",
      },
      isLogin: 1,
    };
  },
  methods: {
    goLogin() {
      request({
        method: "post",
        url: "/users/login",
        data: {
          userName: this.form.userName,
          password: this.form.password,
        },
      })
        .then((res) => {
          console.log(res);

          uni.setStorageSync("userId", res.data.id);
          uni.navigateTo({
            url: "/pages/index/index",
          });
        })
        .catch((err) => {
          uni.showToast({
            title: "账号或者密码错误",
            icon: "none",
          });
        });
    },
    register() {
      request({
        method: "post",
        url: "/users/add",
        data: this.form,
      }).then((res) => {
        console.log(res);
        setTimeout(() => {
          this.goLogin();
        }, 1000);
        // uni.setStorageSync("userId", res.data.id);
        // uni.navigateTo({
        //   url: "/pages/index/index",
        // });
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.page {
  width: 100vw;
  height: 100vh;
  padding: 24px;
  display: flex;
  flex-direction: column;
  background-color: #fafafa;

  .logo {
    width: 240rpx;
    height: 240rpx;
    display: block;
    margin: 80rpx auto 80rpx;
  }

  .login-body {
    padding: 0 20rpx;
    .label-item {
      display: flex;
      align-items: center;
      padding: 24rpx;
      font-size: 28rpx;
      input {
        flex: 1;
      }
    }
    .login-btn {
      margin-top: 24rpx;
    }
  }
}
</style>
