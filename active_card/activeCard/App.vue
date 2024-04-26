<script>
export default {
  globalData: {
		timer: ''
	},
  onLaunch: function () {
    console.log("App Launch");
  },
  onShow: function () {
    console.log("App Show");
  },
  onHide: function () {
    console.log("App Hide");
  },
};
</script>

<style lang="scss">

/* 注意要写在第一行，同时给style标签加入lang="scss"属性 */
@import "uview-ui/index.scss";
@import "./static//scss//iconfont.css";


/*每个页面公共css */
view {
  box-sizing: border-box;
}
</style>
