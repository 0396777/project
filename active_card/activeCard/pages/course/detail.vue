<template>
  <div class="container">
    <div class="content">
      <div class="title">{{ options.title }}</div>
      <div class="desc">{{ options.title }} 开始时间： {{ options.time }}</div>
    </div>
    <div class="contract">
      <image src="@/static/cover.jpg"></image>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      options: {
        title: "",
        time: "",
      },
    };
  },
  onLoad(options) {
    this.options = options;
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
