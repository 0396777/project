<template>
  <div class="container">
    <div
      :style="{
        display: 'flex',
        marginBottom: '20px',
      }"
    >
      缴纳金额：<input type="text" v-model="price" />
    </div>
    <button @click="pay">缴纳</button>
  </div>
</template>

<script>
import request from "../../request/server";
export default {
  data() {
    return {
      price: "",
      userId: "",
      token: "",
      role: "",
    };
  },
  onLoad(options) {
    this.userId = uni.getStorageSync("userId");
    this.token = uni.getStorageSync("token");
    this.role = uni.getStorageSync("role");
  },
  methods: {
    pay() {
      request({
        method: "post",
        url: "/financialrecords/pay",
        data: {
          user: {
            userID: this.userId,
          },
          financialrecords: {
            amount: this.price,
          },
        },
      }).then((res) => {
        console.log(res);
        uni.showToast({
          title: "缴纳支付成功!",
        });
      });
    },
  },
};
</script>

<style lang="scss" scoped>
$fontColor: #b3b3b3;

.container {
  height: 100vw;
  min-height: 100vh;
  padding: 28rpx;
  background-image: linear-gradient(#51c8c8 25%, #f9f9f9 30%);

  .content {
    margin-bottom: 50rpx;

    .title {
      font-size: 36rpx;
      font-weight: bold;
      margin: 40rpx 0;
    }

    .desc {
      font-size: 28rpx;
      color: #6b572b;
    }
  }

  .contract {
    width: 650rpx;
    margin: 0 auto;
    background-color: white;
    border-radius: 8rpx;
    padding: 20rpx;
    box-shadow: 0px 0px 15rpx 0px rgba(0, 0, 0, 0.3);
    image {
      width: 100%;
    }
  }

  .selling-crown {
    margin-top: 30rpx;
    width: 694rpx;
    padding: 25rpx 40rpx;
    display: flex;
    background-color: white;

    .img {
      width: 140rpx;
      height: 260rpx;
    }

    .info {
      display: flex;
      flex-direction: column;
      margin-left: 40rpx;
      justify-content: space-between;

      .top {
        display: flex;
        flex-direction: column;

        .name {
          font-weight: bold;
        }

        .score {
          display: flex;
          margin-top: 15rpx;
          margin-left: -8rpx;

          .num {
            margin-left: 10rpx;
            color: #e03811;
          }
        }

        .count {
          font-size: 28rpx;
          margin-top: 10rpx;

          .num {
            color: #e03811;
          }
        }
      }

      .call-phone {
        background-color: #e03811;
        width: 350rpx;
        text-align: center;
        line-height: 50rpx;
        color: white;
        border-radius: 4rpx;
        height: 50rpx;
      }
    }
  }
}
</style>
