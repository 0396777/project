<template>
  <div class="content-page">
    <div class="position-box">
      <div class="flex-box">
        <scroll-view
          scroll-y
          :style="{
            height: '100%',
          }"
        >
          <!-- 首页 -->
          <IndexPage
            :bottomHeight="bottomHeight"
            v-if="currentIndex === 0"
            :isShow="currentIndex === 0"
          ></IndexPage>
          <!-- 笔记 -->
          <Note
            :bottomHeight="bottomHeight"
            v-if="currentIndex === 1"
            :isShow="currentIndex === 1"
          ></Note>
          <!-- 打卡 -->
          <Check
            :bottomHeight="bottomHeight"
            v-if="currentIndex === 2"
            :isShow="currentIndex === 2"
          ></Check>
          <!-- 计时间 -->
          <Time
            :bottomHeight="bottomHeight"
            v-if="currentIndex === 3"
            :isShow="currentIndex === 3"
          ></Time>
          <!-- 番茄钟 -->
          <TimeDown
            :bottomHeight="bottomHeight"
            v-if="currentIndex === 4"
            :isShow="currentIndex === 4"
          ></TimeDown>
          <!-- 个人中心 -->
          <MinePage
            :bottomHeight="bottomHeight"
            v-else-if="currentIndex === 5"
            :isShow="currentIndex === 5"
          ></MinePage>
        </scroll-view>
      </div>
    </div>

    <div class="tabbar" id="tabbar">
      <div class="nav-list">
        <view
          class="nav-item"
          :class="[currentIndex === item.value ? 'active' : '']"
          v-for="(item, index) in bottomNavBarList"
          :key="index"
          @click="changeIndex(item)"
        >
          <i
            v-if="item.value === 0"
            class="iconfont icon-Homehomepagemenu1 icon"
          ></i>
          <i v-if="item.value === 1" class="iconfont icon-hezuo icon"></i>
          <i v-if="item.value === 2" class="iconfont icon-shumiao icon"></i>
          <i v-if="item.value === 3" class="iconfont icon-shumiao icon"></i>
          <i v-if="item.value === 4" class="iconfont icon-shumiao icon"></i>
          <i v-if="item.value === 5" class="iconfont icon-my icon"></i>
          <text>{{ item.title }}</text>
        </view>
      </div>
      <div class="safe"></div>
    </div>
  </div>
</template>

<script>
import IndexPage from "./components/index-page/index";
import MinePage from "./components/mine-page/mine";
import Note from "./components/note/note.vue";
import Check from "./components/check/check.vue";
import Time from "./components/time/time.vue";
import TimeDown from "./components/time/timeDown.vue";
import request from "../../request/index";

export default {
  components: {
    IndexPage,
    MinePage,
    Note,
    Check,
    Time,
    TimeDown,
  },
  data() {
    return {
      bottomNavBarList: [
        {
          title: "待办",
          value: 0,
        },
        {
          title: "笔记",
          value: 1,
        },
        {
          title: "打卡",
          value: 2,
        },

        {
          title: "正记时",
          value: 3,
        },
        {
          title: "番茄钟",
          value: 4,
        },
        {
          title: "我的",
          value: 5,
        },
      ],
      currentIndex: 0,
      // 底部栏高度
      bottomHeight: 0,
      userId: "",
      //今天的时间
      formattedDate: "",
    };
  },
  onLoad() {
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
    this.getBottomHeight();
    this.checkTime();
  },
  methods: {
    changeIndex(item) {
      let that = this;
      // 相同
      if (item.value === that.currentIndex) return;
      if ([3, 4].includes(this.currentIndex)) {
        // 当前页面是计算时间的页面
        uni.showModal({
          title: "提示",
          content: "是否退出计算时间页面？",
          success: function (res) {
            console.log(res);
            if (res.confirm) {
              that.currentIndex = item.value;
            } else if (res.cancel) {
              console.log("用户点击取消");
            }
          },
        });
      } else {
        this.currentIndex = item.value;
      }
      console.log(this.currentIndex, item);
    },
    getBottomHeight() {
      const query = uni.createSelectorQuery().in(this);
      query
        .select("#tabbar")
        .boundingClientRect((data) => {
          console.log(data, "tabbar-height");
          this.bottomHeight = data.height;
        })
        .exec();
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
    checkTime() {
      console.log("checkTime");

      let PromiseList = [];
      let p1 = request({
        method: "get",
        url: "/wait/loadDataList",
        data: {
          page: 1,
          pageSize: 999,
          userName: this.userId,
        },
      }).then((res) => {
        console.log(res, "res");

        let noOkList = this.passedTimes(res.data.list);
        noOkList = noOkList.filter((item) => !item.isOk);
        let noNameList = noOkList.map((item) => item.eventInfo);

        if (noNameList.length) {
          uni.showModal({
            title: "待办",
            showCancel: false,
            confirmText: "关闭",
            content: `${noNameList.join(",")} 已经超时未完成`,
            success: function (res) {
              if (res.confirm) {
                console.log("用户点击确定");
              } else if (res.cancel) {
                console.log("用户点击取消");
              }
            },
          });
        }
      });

      let p2 = request({
        method: "get",
        url: "/habitCheckItems/loadDataList",
        data: {
          page: 1,
          pageSize: 999,
          userId: this.userId,
        },
      }).then((res) => {
        console.log(res.data.list);
        let filterList = res.data.list.filter((item) => {
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

        // this.checkTime();
      });

      // if (noNameList.length) {
      //   uni.showToast({
      //     title: `${noNameList.join(",")} 已经超时未完成`,
      //     icon: "none",
      //   });
      // }
    },
  },
  onReachBottom() {
    /* 通过ref调用子组件获取数据函数 */
    console.log("onReachBottom");
  },
};
</script>

<style lang="scss" scoped>
$fontColor: #b3b3b3;
$heightColor: #eb5546;

.safe {
  padding-bottom: constant(safe-area-inset-bottom);
  /* 兼容 iOS < 11.2 */
  padding-bottom: env(safe-area-inset-bottom);
  /* 兼容 iOS >= 11.2 */
}

.content-page {
  display: flex;
  flex-direction: column;
  height: 100vh;
  .position-box {
    flex: 1;
    height: 100%;
    position: relative;
    background-color: #f2f2f2;
    .flex-box {
      position: absolute;
      left: 0;
      top: 0;
      width: 100%;
      height: 100%;
    }
  }
  .tabbar {
    z-index: 999;
    background-color: #fff;
    // position: fixed;
    // bottom: 0;
    // left: 0;
    width: 100%;
    padding: 24rpx 20rpx 0;
    // background-color: pink;

    .nav-list {
      display: flex;

      .nav-item {
        flex: 1;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;

        .icon {
          // background-color: $font-color;
          // width: 40px;
          // height: 40px;
          font-size: 40rpx;
          color: $fontColor;
        }

        text {
          font-size: 24rpx;
          color: $fontColor;
          margin-top: 12rpx;
        }

        &.active {
          .icon,
          text {
            color: #2979ff;
          }
        }
      }
    }
  }
}
</style>
