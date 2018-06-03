//index.js
//获取应用实例
var app = getApp()
Page({
  data: {
    motto: 'Hello ! WelCome to WxApp',
    islogin: false,
    userInfo: {}
  },
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {  
    var that = this;
    var CuserInfo = wx.getStorageSync('mUserInfo');
    //当前账户已经登录
    if (CuserInfo){
      that.setData({ islogin: true, userInfo: CuserInfo});
      
    }

    //调用应用实例的方法获取全局数据
    // app.getUserInfo(function(userInfo){
    //   //更新数据
    //   that.setData({
    //     userInfo:userInfo
    //   })
    // })
  },
  
  onGotUserInfo:function (e) {
    app.globalData.userInfo=e.detail.userInfo;
  }
})
