// 全局请求封装
const base_url = "http://47.99.126.19:7090/api";
// 请求超出时间
const timeout = 50000;

// 需要修改token，和根据实际修改请求头
export default (params) => {
  let url = params.url;
  let method = params.method || "get";
  let data = params.data || {};
  let header = {
    token: uni.getStorageSync("user").token || "",
    "Content-Type": "application/json;charset=UTF-8",
  };
  if (method == "post") {
    header = {
      "Content-Type": "application/json",
    };
  }
  return new Promise((resolve, reject) => {
    uni.request({
      url: base_url + url,
      method: method,
      header: header,
      data: data,
      timeout,
      success(response) {
        const res = response;
        // 根据返回的状态码做出对应的操作
        //获取成功
        // console.log(res.statusCode);
        resolve(res.data);
      },
      fail(err) {
        console.log(err);
  
        reject(err);
      },
      complete() {
        // // 不管成功还是失败都会执行
        // uni.hideLoading();
        // uni.hideToast();
      },
    });
  }).catch(() => {});
};
