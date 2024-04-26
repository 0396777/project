<template>
  <div class="timer">
    <div class="time">{{ formatTime }}</div>
    <div class="controls">
      <div class="button">
        <u-button type="primary" @click="startTimer" :disabled="isRunning"
          >开始</u-button
        >
      </div>
      <div class="button">
        <u-button type="primary" @click="pauseTimer" :disabled="!isRunning"
          >暂停</u-button
        >
      </div>
      <div class="button">
        <u-button type="primary" @click="resetTimer">结束</u-button>
      </div>
    </div>
  </div>
</template>

<script>
import request from "../../../../request/index";

export default {
  data() {
    return {
      startTime: 0,
      currentTime: 0,
      isRunning: false,
      formattedDate: "",
	  userId:""
    };
  },
  computed: {
    formatTime() {
      let minutes = Math.floor(this.currentTime / 60);
      let seconds = this.currentTime % 60;
      return `${this.padTime(minutes)}:${this.padTime(seconds)}`;
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
  },
  methods: {
    padTime(time) {
      return (time < 10 ? "0" : "") + time;
    },
    startTimer() {
      if (!this.isRunning) {
        this.startTime = Math.floor(Date.now() / 1000);
        this.timer = setInterval(this.updateTime, 1000);
        this.isRunning = true;
      }
    },
    pauseTimer() {
      clearInterval(this.timer);

      this.isRunning = false;
    },
    resetTimer() {
      clearInterval(this.timer);

      if (this.formatTime != "00:00") {
        request({
          method: "post",
          url: "/counting/add",
          data: {
			  userId:this.userId,
            createTime: this.formattedDate,
            time: this.formatTime,
          },
        }).then((res) => {
          console.log(res);
        });
      }
      this.currentTime = 0;

      this.isRunning = false;
    },
    updateTime() {
      this.currentTime++;
    },
  },
};
</script>

<style lang="scss" scoped>
.timer {
  text-align: center;
}
.time {
  font-size: 36px;
  margin-bottom: 20px;
  margin: 0 auto;
  width: 400rpx;
  height: 400rpx;
  border: 3px solid #2979ff;
  color: #2979ff;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 24rpx;
}
.controls {
  padding: 0 24rpx;
  .button + .button {
    margin-top: 24rpx;
  }
}
</style>
