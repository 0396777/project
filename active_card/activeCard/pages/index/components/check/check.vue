<template>
  <div
    class="container"
    :style="{
      paddingBottom: `${bottomHeight}px`,
    }"
  >
    <u-button type="primary" @click="open">新增打卡目标</u-button>

    <view class="todo-header" v-if="false">
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
          >全部</view
        >
        <view
          class="todo-header__right-item"
          :class="{ 'active-tab': activeTab === 1 }"
          @click="tab(1)"
          >待办</view
        >
        <view
          class="todo-header__right-item"
          :class="{ 'active-tab': activeTab === 2 }"
          @click="tab(2)"
          >已完成</view
        >
      </view>
    </view>

    <view v-if="list.length == 0" class="default">
      <view class="image-default"> </view>
      <view class="default-info">
        <view class="default-info__text">点击下方+号创建一个打卡吧</view>
      </view>
    </view>

    <view v-else class="todo-content">
      <view
        class="todo-list"
        :class="{ 'todo--finish': item.isOk }"
        v-for="(item, index) in listData"
        :key="index"
      >
        <view
          :style="{
            display: 'flex',
            alignItems: 'center',
          }"
        >
          <view class="todo-list__content">
            <view> 打卡目标： {{ item.itemName }}</view>
            <view>
              上次打卡时间：{{ item.lastCheckedDate || "没有打卡过" }}
            </view>
            <view> 开始时间：{{ item.startDate }} </view>
            <view> 截至时间：{{ item.endDate }} </view>
            <view>
              打卡规则：
              {{ item.completedTimes ? "每周打卡一次" : "每天打卡一次" }}
            </view>
          </view>
        </view>
        <u-tag text="已打卡" v-if="isChecked(item)" mode="light" />
        <u-tag type="error" v-else text="未打卡" mode="light" />
        <div class="delete" @click="finish(item)">打卡</div>
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
              placeholder="请输入你要创建的打卡"
              v-model="form.itemName"
            />
          </view>
        </view>
        <u-radio-group v-model="form.completedTimes">
          <u-radio :name="0"> 每天打卡 </u-radio>
          <u-radio :name="1"> 每周打卡 </u-radio>
        </u-radio-group>
        <u-button type="primary" @click="timePicker = true">开始时间</u-button>
        开始时间：{{ form.startDate }}
        <u-picker
          mode="time"
          v-model="timePicker"
          @confirm="pickerConfirm"
          :params="params"
        ></u-picker>
        <div
          :style="{
            marginTop: '12px',
          }"
        >
          <u-button type="primary" @click="timePicker2 = true"
            >结束时间</u-button
          >
          结束时间：{{ form.endDate }}
          <u-picker
            mode="time"
            v-model="timePicker2"
            @confirm="pickerConfirm2"
            :params="params"
          ></u-picker>
        </div>
        <div class="create">
          <u-button type="primary" @click="handleSubmit">创建打卡目标</u-button>
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
        // hour: true,
        // minute: true,
        // second: true,
      },
      title: "to-do",
      activeText: "全部",
      list: [],
      active: false,
      timePicker: false,
      timePicker2: false,
      form: {
        itemName: "",
        startDate: "",
        endDate: "",
        completedTimes: 0,
      },
      activeTab: 0,
      textShow: false,
      //今天的时间
      formattedDate: "",
      userId: "",
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
  watch: {
    pageShow: {
      handler(newValue, oldValue) {
        if (newValue) {
          this.getList(true);
        }
      },
      deep: true,
      immediate: true,
    },
  },
  methods: {
    dateToTimestamp(dateString) {
      // 将日期字符串拆分为年、月、日
      let parts = dateString.split("-");

      // 构建日期对象
      let date = new Date(parts[0], parts[1] - 1, parts[2]); // 月份需要减去 1，因为 JavaScript 中的月份是从 0 开始的

      // 获取时间戳并返回
      return date.getTime();
    },
    isChecked(item) {
      if (!item.lastCheckedDate) return false;
      if (item.lastCheckedDate === this.formattedDate) {
        return true;
      } else if (item.completedTimes) {
        let today = new Date();
        let currentDayOfWeek = today.getDay();
        currentDayOfWeek = currentDayOfWeek - 1;
        // 每周打卡
        let lastTime = this.dateToTimestamp(item.lastCheckedDate);
        let nowTime = this.dateToTimestamp(this.formattedDate);
        let countTime =
          nowTime -
          lastTime -
          86400000 * (currentDayOfWeek === -1 ? 7 : currentDayOfWeek);
        console.log(currentDayOfWeek, "currentDayOfWeek");
        console.log(countTime, "COUNTtIME");
        console.log(nowTime, "nowTime");
        console.log(lastTime, "lastTime");
        if (countTime > 0) {
          // 一周内未打卡
          return false;
        } else {
          return true;
        }
      } else {
        // 每日打卡
        return false;
      }
    },
    passedTimes(timeArray) {
      var currentTime = new Date();
      var passed = [];

      for (var i = 0; i < timeArray.length; i++) {
        var targetTime = new Date(timeArray[i].okTime);
        if (currentTime > targetTime) {
          passed.push(timeArray[i]);
        }
      }

      return passed;
    },

    pickerConfirm(data) {
      console.log(data);
      this.form.startDate = `${data.year}-${data.month}-${data.day}`;
    },
    pickerConfirm2(data) {
      console.log(data);
      this.form.endDate = `${data.year}-${data.month}-${data.day}`;
    },
    getList(isChecked) {
      request({
        method: "get",
        url: "/habitCheckItems/loadDataList",
        data: {
          page: 1,
          pageSize: 999,
          userId: this.userId,
        },
      }).then((res) => {
        console.log(res);
        this.list = res.data.list;

        setTimeout(() => {
          if (isChecked) {
            let filterList = this.list.filter((item) => {
              console.log(this.isChecked(item));
              return !this.isChecked(item);
              // console.log(this.isChecked(item),'jjjj');
              // return true;
            });
            filterList = filterList.map((item) => item.itemName);
            console.log(filterList, "kjjj");
            if (filterList.length) {
              uni.showModal({
                title: "打卡",
                showCancel: false,
                confirmText: "关闭",
                content: `${filterList.join(",")} 待打卡`,
                success: function (res) {
                  if (res.confirm) {
                    console.log("用户点击确定");
                  } else if (res.cancel) {
                    console.log("用户点击取消");
                  }
                },
              });
            }
          }
        }, 200);

        // this.checkTime();
      });
    },
    checkTime() {
      let noOkList = this.passedTimes(this.list);
      noOkList = noOkList.filter((item) => !item.isOk);
      let noNameList = noOkList.map((item) => item.itemName);
      if (noNameList.length) {
        uni.showToast({
          title: `${noNameList.join(",")} 已经超时未完成`,
          icon: "none",
        });
      }
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
      if (this.form.itemName == "") {
        uni.showToast({
          title: "请输入内容",
          icon: "none",
        });

        return false;
      }
      if (this.form.startDate == "") {
        uni.showToast({
          title: "请输入开始时间",
          icon: "none",
        });

        return false;
      }
      if (this.form.endDate == "") {
        uni.showToast({
          title: "请输入结束时间",
          icon: "none",
        });

        return false;
      }
      let startTime1 = new Date(this.form.startDate);
      let endTime1 = new Date(this.form.endDate);

      if (startTime1 > endTime1) {
        uni.showToast({
          title: "开始时间不能比结束时间晚",
          icon: "none",
        });

        return false;
      }
      // this.list.unshift({
      //   id: "id_" + new Date().getTime(),
      //   content: this.itemName,
      //   checked: false,
      // });

      request({
        method: "post",
        url: "/habitCheckItems/add",
        data: {
          ...this.form,
          userId: this.userId,
        },
      }).then((res) => {
        console.log(res);
		this.close();
		this.getList();
		
		this.form = {
		  itemName: "",
		  startDate: "",
		  endDate: "",
		  completedTimes: 0,
		};
        // this.list = res.data.list;
      });

      // setTimeout(() => {
      //   this.getList();
      //   this.close();

      //   this.form = {
      //     itemName: "",
      //     startDate: "",
      //     endDate: "",
      //     completedTimes: 0,
      //   };
      // }, 1000);
    },
    finish(item) {
      request({
        method: "get",
        url: "/habitCheckItems/updateHabitCheckItemsById",
        data: {
          id: item.id,
          lastCheckedDate: this.formattedDate,
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
        url: "/habitCheckItems/deleteHabitCheckItemsById",
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
  mounted() {
    this.userId = uni.getStorageSync("userId");
    let today = new Date();

    // 获取年、月、日
    let year = today.getFullYear();
    let month = today.getMonth() + 1; // 月份是从 0 开始的，所以要加 1
    let day = today.getDate();

    // 将月份和日期格式化为两位数
    month = month < 10 ? "0" + month : month;
    day = day < 10 ? "0" + day : day;

    // 构建 yyyy-mm-dd 格式的日期字符串
    this.formattedDate = year + "-" + month + "-" + day;
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
