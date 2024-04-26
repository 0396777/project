<template>
  <div
    class="container"
    :style="{
      paddingBottom: `${bottomHeight}px`,
    }"
  >
    <!-- <div class="todo-list">
      <div class="list-item" v-for="item in list">
        <div class="check"></div>
        <div class="name"></div>
      </div>
    </div> -->

    <!-- <view class="create-todo" "> -->
    <!-- <view class="iconfont icon-add">新增待办</view> -->
    <u-button type="primary" @click="open">新增待办</u-button>
    <!-- </view> -->

    <view class="todo-header">
      <!-- 状态栏左侧 -->
      <view class="todo-header__left">
        <text class="active-text">{{ activeText }}</text>
        <text>{{ listData.length }}条</text>
      </view>
      <!-- 状态栏右侧 -->
      <view class="todo-header__right">
        <view
          class="todo-header__right-item"
          :class="{ 'active-tab': activeTab === 0 }"
          @click="tab(0)"
          >全部
        </view>
        <view
          class="todo-header__right-item"
          :class="{ 'active-tab': activeTab === 1 }"
          @click="tab(1)"
          >待办
        </view>
        <view
          class="todo-header__right-item"
          :class="{ 'active-tab': activeTab === 2 }"
          @click="tab(2)"
          >已完成
        </view>
      </view>
    </view>

    <view v-if="list.length == 0" class="default">
      <view class="image-default"> </view>
      <view class="default-info">
        <!-- <view class="default-info__text">您还没有任何待办事项</view> -->
        <view class="default-info__text">点击下方+号创建一个吧</view>
      </view>
    </view>

    <view v-else class="todo-content">
      <view
        class="todo-list"
        :class="{ 'todo--finish': item.isOk }"
        v-for="(item, index) in listData"
        :key="index"
        @click="finish(item)"
      >
        <view
          :style="{
            display: 'flex',
            alignItems: 'center',
          }"
        >
          <view class="todo-list__checkbox">
            <view class="checkbox"></view>
          </view>
          <view class="todo-list__content">
            {{ item.eventInfo }}
            <view
              :style="{
                marginBottom: '8px',
              }"
            >
              截至时间：{{ item.okTime }}
            </view>
            <u-tag
              type="error"
              v-if="passedItem(item) && !item.isOk"
              text="已超时"
              mode="light"
            />
          </view>
        </view>
        <div class="delete" @click="deleteData(item)">删除</div>
      </view>
    </view>

    <u-popup v-model="active" mode="center" border-radius="14">
      <div class="popup">
        <view class="create-content-box">
          <view class="create-input">
            <input
              type="text"
              value=""
              placeholder="请输入你要创建的todo"
              v-model="form.eventInfo"
            />
          </view>
        </view>
        <u-button type="primary" @click="timePicker = true">设置时间</u-button>
        提醒时间：{{ form.okTime }}
        <u-picker
          mode="time"
          v-model="timePicker"
          @confirm="pickerConfirm"
          :params="params"
        ></u-picker>
        <div class="create">
          <u-button type="primary" @click="handleSubmit">创建待办</u-button>
        </div>
      </div>
    </u-popup>
  </div>
</template>

<script>
import request from "../../../../request/index";
export default {
  props: {
    bottomHeight: {
      type: Number,
    },
    isShow: {
      type: Boolean,
      default: false,
    },
  },
  data() {
    return {
      params: {
        year: true,
        month: true,
        day: true,
        hour: true,
        minute: true,
        second: true,
      },
      title: "to-do",
      activeText: "全部",
      list: [],
      active: false,
      timePicker: false,
      form: {
        eventInfo: "",
        okTime: "",
      },
      activeTab: 0,
      textShow: false,
      userId: "",
      isInit: false,
      timer: "",
      filterIdList: [],
    };
  },
  computed: {
    listData() {
      let list = JSON.parse(JSON.stringify(this.list));
      let newList = [];

      // 全部
      if (this.activeTab == 0) {
        return list;
      }

      // 待办
      if (this.activeTab == 1) {
        list.forEach((item) => {
          if (!item.isOk) {
            newList.push(item);
          }
        });

        return newList;
      }

      // 已完成
      if (this.activeTab == 2) {
        list.forEach((item) => {
          if (item.isOk) {
            newList.push(item);
          }
        });

        return newList;
      }
    },
    pageShow() {
      return this.$props.isShow;
    },
  },
  methods: {
    passedTimes(timeArray) {
      var currentTime = new Date();
      var passed = [];

      for (var i = 0; i < timeArray.length; i++) {
        var targetTime = new Date(timeArray[i].okTime);
        // console.log(currentTime.getTime(), targetTime.getTime(), "kkkk");

        // currentTime = currentTime.getTime();
        // targetTime = targetTime.getTime();

        // 误差1000毫秒
        let edd = currentTime.getTime() - targetTime.getTime();

        if (
          currentTime.getTime() == targetTime.getTime() ||
          (edd < 1000 && edd >= 0)
        ) {
          console.log(
            currentTime.getTime() - targetTime.getTime(),
            "currentTime.getTime() - targetTime.getTime()"
          );
          passed.push(timeArray[i]);
        }
      }

      return passed;
    },
    passedItem(item) {
      var targetTime = new Date(item.okTime);
      var currentTime = new Date();

      if (currentTime > targetTime) {
        return true;
      } else {
        false;
      }
    },

    pickerConfirm(data) {
      console.log(data);
      this.form.okTime = `${data.year}-${data.month}-${data.day} ${data.hour}:${data.minute}:${data.second}`;
    },
    getList() {
      console.log(getApp().globalData.timer, "this.timer");

      this.userId = uni.getStorageSync("userId");
      console.log(this.userId, "123123");
      request({
        method: "get",
        url: "/wait/loadDataList",
        data: {
          page: 1,
          pageSize: 999,
          userName: this.userId,
        },
      }).then((res) => {
        console.log(res);
        this.list = res.data.list;
        // 监听
        clearInterval(getApp().globalData.timer);
        getApp().globalData.timer = setInterval(() => {
          console.log("time---------");
          this.checkTime();
        }, 1000);
      });
    },

    checkTime() {
      let that = this;
      let noOkList = this.passedTimes(this.list);
      noOkList = noOkList.filter((item) => !item.isOk);
      let noNameList = noOkList.map((item) => item.eventInfo);

      if (noNameList.length) {
        uni.showModal({
          title: "待办",
          showCancel: false,
          confirmText: "关闭",
          content: `${noNameList.join(",")} 已经超时未完成`,
          success: function (res) {
            // that.getList();
          },
        });
      }

      this.filterIdList = noOkList.map((item) => item.id);
    },
    addData() {},

    // 打开动画
    open() {
      this.active = true;
    },
    // 关闭动画
    close() {
      this.active = false;
    },
    handleSubmit() {
      if (this.form.eventInfo == "") {
        uni.showToast({
          title: "请输入内容",
          icon: "none",
        });

        return false;
      }
      if (this.form.okTime == "") {
        uni.showToast({
          title: "时间",
          icon: "none",
        });

        return false;
      }

      // this.list.unshift({
      //   id: "id_" + new Date().getTime(),
      //   content: this.eventInfo,
      //   checked: false,
      // });

      request({
        method: "post",
        url: "/wait/add",
        data: {
          ...this.form,
          isOk: 0,
          userName: this.userId,
        },
      }).then((res) => {
        console.log(res);
        // this.list = res.data.list;
      });

      this.form.eventInfo = "";

      setTimeout(() => {
        this.getList();
        this.close();
        this.form.eventInfo = "";
      }, 1000);
    },
    finish(item) {
      request({
        method: "get",
        url: "/wait/updateWaitById",
        data: {
          id: item.id,
          isOk: item.isOk ? 0 : 1,
        },
      }).then((res) => {
        console.log(res);
        this.getList();
        // this.list = res.data.list;
      });
    },
    tab(index) {
      this.activeTab = index;
      this.activeText = index == 0 ? "全部" : index == 1 ? "待办" : "已完成";
    },
    deleteData(item) {
      request({
        method: "get",
        url: "/wait/deleteWaitById",
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
  watch: {
    pageShow: {
      handler(newValue, oldValue) {
        if (newValue) {
          clearInterval(getApp().globalData.timer);

          this.getList();
        }
      },
      deep: true,
      immediate: true,
    },
  },
  mounted() {
    // console.log("onShow1");
    console.log(this.pageShow);
    // this.getList();
  },
  // methods: {
};
</script>

<style lang="scss" scoped>
$width: 702rpx;
$fontColor: #b3b3b3;
$heightColor: #eb5546;

.safe {
  padding-bottom: constant(safe-area-inset-bottom);
  /* 兼容 iOS < 11.2 */
  padding-bottom: env(safe-area-inset-bottom);
  /* 兼容 iOS >= 11.2 */
}

.container {
  background-color: #eeeeee;
  display: flex;
  flex-direction: column;
}

.todo-header {
  display: flex;
  width: 100%;
  height: 45px;
  padding: 0 15px;
  align-items: center;
  font-size: 12px;
  color: #333333;
  /* 不撑开 */
  box-sizing: border-box;
  // box-shadow: -1px -1px 5px 0 rgba(0, 0, 0, 0.1);
  background-color: #ffffff;
  z-index: 10;
}

.todo-header__left {
  width: 100%;
}

.active-text {
  font-size: 14px;
  color: #279abf;
  padding-right: 10px;
}

.todo-header__right {
  flex-shrink: 0;
  display: flex;
}

.todo-header__right-item {
  padding: 0 5px;
}

.active-tab {
  color: #279abf;
}

.todo-content {
  position: relative;
  padding-top: 24rpx;
}

.todo-list {
  position: relative;
  display: flex;
  justify-content: space-between;
  padding: 15px;
  margin: 15px;
  /* 字体 */
  color: #0c3854;
  font-size: 14px;
  /* 圆角 */
  border-radius: 10px;
  background: #cefbfd;
  /* 阴影 */
  // box-shadow: -1px 1px 5px 1px rgba(0, 0, 0, 0.1),
  // -1px 2px 1px 0 rgba(255, 255, 255) inset;
  overflow: hidden;
}

.todo--finish .checkbox {
  position: relative;
  background: #eee;
}

/* 伪类实现checkbox的选中效果 */
.todo--finish .checkbox:after {
  content: "";
  position: absolute;
  width: 10px;
  height: 10px;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  margin: auto;
  border-radius: 50%;
  background: #cfebfd;
  box-shadow: 0 0 2px 0px rgba(0, 0, 0, 0.2) inset;
}

.todo--finish .todo-list__content {
  color: #999999;
}

/* 伪类实现删除线的效果 */
.todo--finish.todo-list:before {
  content: "";
  position: absolute;
  top: 0;
  bottom: 0;
  left: 40px;
  right: 10px;
  height: 2px;
  margin: auto 0;
  background: #bdcdd8;
}

/* 小竖线置灰 */
.todo--finish.todo-list:after {
  background: #cccccc;
}

/* 伪类实现item左侧的小竖线 */
.todo-list:after {
  position: absolute;
  content: "";
  top: 0;
  bottom: 0;
  left: 0;
  width: 5px;
  background: #91d1e8;
}

.todo-list__checkbox {
  padding-right: 15px;
}

.checkbox {
  width: 20px;
  height: 20px;
  border-radius: 50%;
  border-radius: 10px;
  background: #ffffff;
  box-shadow: 0 0 5px 1px rgba(0, 0, 0, 0.1);
}

.create-todo {
  display: flex;
  justify-content: center;
  align-items: center;

  height: 50px;
  border-radius: 24rpx;
  background: #deeff5;
  box-shadow: -1px 1px 5px 2px rgba(0, 0, 0, 0.1);
}

.icon-add {
  font-size: 24rpxpx;
  color: #1a1a1a;
  transition: transform 0.3s;
}

.create-todo-active {
  transform: rotate(135deg);
}

.create-content {
  position: fixed;
  bottom: 95px;
  left: 20px;
  right: 20px;
  /* 以下动画效果 */
  transition: all 0.3s;
  opacity: 0;
  transform: scale(0) translateY(200%);
}

.create--show {
  opacity: 1;
  transform: scale(1) translateY(0);
}

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

/* 缺省显示 */
.default {
  padding-top: 100px;
}

.image-default {
  display: flex;
  justify-content: center;
  padding-left: 80px;
}

.image-default image {
  width: 100%;
}

.default-info {
  padding-top: 20px;
  font-size: 14px;
  color: #cccccc;
  text-align: center;
}
</style>
