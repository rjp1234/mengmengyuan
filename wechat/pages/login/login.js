var Api = require('../../utils/api.js');
var util = require('../../utils/util.js');
var app = getApp()
Page({
  data: {
    loading: false,
    loginname: "",
    password: "",
    msg1: "",
    msg2: "",
    error: "",
    userInfo: {}
  },

  onLoad: function () {

  },

  bindLoginname: function (e) {
    this.setData({
      loginname: e.detail.value
    })
  },

  bindPassword: function (e) {
    this.setData({
      password: e.detail.value
    })
  },



  // 验证token(登录)
  login: function () {
    var that = this;
    var loginname = that.data.loginname;
    var password = that.data.password;
    var ApiUrl = Api.login;
    that.setData({ msg1: "" });
    that.setData({ msg2: "" });
    if (loginname === "") {
      that.setData({ msg1: "账户名不得为空" });
      return;
    }

    if (password === "")
    {
      that.setData({msg2:"密码不得为空"});
      return;
    }
    if (loginname.length < 3) {
      that.setData({ msg1: "账户名输入错误" });
    }

    if(password.length<6){
      that.setData({ msg2: "密码错误" });
    }

    //调用应用实例的方法获取全局数据
    app.getUserInfo(function (userInfo) {
      //更新数据
      that.setData({
        userInfo: userInfo
      })
    })
    var userInfo=that.data.userInfo;

    that.setData({ loading: true });
    var dataparam = "loginname=" + loginname + "&password=" + password + "&nickname=" + userInfo.nickName + "&image=" + userInfo.avatarUrl;
    console.log(dataparam);
      Api.fetchPost(ApiUrl, dataparam, (err, res) => {

      if (res.success) {
        var CuserInfo = {
          accesstoken: accesstoken,
          id: res.id,
          loginname: res.loginname,
          avatar_url: res.loginname
        };
        console.log(CuserInfo)
        wx.setStorageSync('CuserInfo', CuserInfo);

        setTimeout(function () {
          that.setData({ loading: false });
          // wx.navigateTo({
          //   url: '/pages/index/index'
          // })
          wx.navigateBack();
        }, 3000);

      } else {
        that.setData({ error: res.error_msg });
        that.setData({ loading: false });
        setTimeout(function () {
          that.setData({ error: "" });
        }, 2000);
      }

    })


  }
})
