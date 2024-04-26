<template>
  <view class="page">
    <u-button type="primary" @click="active = true">新增笔记</u-button>

    <u-card
      v-for="item in list"
      :key="item.id"
      :title="item.title"
      :sub-title="item.createdTime"
      class="card"
    >
      <view slot="body"> {{ item.content }}</view>
      <view slot="foot" @click="deleteData(item)"> 删除</view>
    </u-card>

    <u-popup v-model="active" mode="center" border-radius="14">
      <div class="popup">
        <view class="create-content-box">
          <view class="create-input">
            <input
              type="text"
              value=""
              placeholder="请输入标题"
              v-model="form.title"
            />
          </view>
        </view>
        <view class="create-content-box">
          <view class="create-textarea">
            <input
              type="textarea"
              value=""
              placeholder="请输入笔记"
              v-model="form.content"
            />
          </view>
        </view>

        <div class="create">
          <u-button type="primary" @click="handleSubmit">创建笔记</u-button>
        </div>
      </div>
    </u-popup>
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
      list: [],
      active: false,
      form: {
        title: "",
        content: "",
      },
      userId: "",
    };
  },
  mounted() {
    this.userId = uni.getStorageSync("userId");

    this.getList();
  },
  methods: {
    // 打开动画
    open() {
      this.active = true;
    },
    // 关闭动画
    close() {
      this.active = false;
    },
    getList() {
      request({
        method: "get",
        url: "/notes/loadDataList",
        data: {
          page: 1,
          pageSize: 999,
          userId: this.userId,
        },
      }).then((res) => {
        console.log(res);
        this.list = res.data.list;
      });
    },
    handleSubmit() {
      if (this.form.title == "") {
        uni.showToast({
          title: "请输入标题",
          icon: "none",
        });

        return false;
      }
      if (this.form.content == "") {
        uni.showToast({
          title: "请输入内容",
          icon: "none",
        });

        return false;
      }
      request({
        method: "post",
        url: "/notes/add",
        data: {
          ...this.form,
          userId: this.userId,

        },
      }).then((res) => {
		  
		  this.close();
		  this.getList();
		  this.form = {
		    title: "",
		    content: "",
		  };
        console.log(res);
        // this.list = res.data.list;
      });

    //   setTimeout(() => {
    //     this.getList();

    //     this.close();
    //     this.form = {
    //       title: "",
    //       content: "",
    //     };
    //   });
    },
    deleteData(item) {
      request({
        method: "get",
        url: "/notes/deleteNotesById",
        data: {
          id: item.id,
        },
      }).then((res) => {
        console.log(res);
        this.getList();
        // this.list = res.data.list;
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.page {
  width: 100vw;
  min-height: 100vh;
  padding: 24rpx;
  display: flex;
  flex-direction: column;
  background-color: #f2f2f2;

  .popup {
    width: 80vw;
    padding: 24rpx;
    background: #fff;
    min-height: 50vh;
    position: relative;
    .create {
      position: absolute;
      bottom: 24rpx;
      left: 50%;
      margin: 0 auto;
      width: 200px;
      transform: translateX(-50%);
    }
  }
  .create-content-box {
    display: flex;
    align-items: center;
    padding: 24rpx;
    border-radius: 24rpx;
    background: #deeff5;

    z-index: 2;
    margin-bottom: 24rpx;
  }

  .create-input {
    width: 100%;
    padding-left: 15px;
    color: #1a1a1a;
  }
  .create-textarea {
    width: 100%;
    min-height: 400rpx;
    padding-left: 15px;
    color: #1a1a1a;
  }

  .create-button {
    display: flex;
    /* 水平居中 */
    justify-content: center;
    /* 垂直居中 */
    align-items: center;
    /* 元素不希望被挤压 */
    flex-shrink: 0;
    width: 80px;
    height: 50px;
    border-radius: 50px;
    font-size: 16px;
    color: #88d4ec;
    /* 按钮的凸出效果 */
    // box-shadow: -2px 0px 2px 1px rgba(0, 0, 0, 0.1);
  }
}
</style>
