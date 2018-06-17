var Api = require('../../utils/api.js');
var util = require('../../utils/util.js');
var app = getApp()
var navList = [{

    title: "全部"
  }

];

Page({
  data: {
    activeIndex: 0,
    navList: navList,
    postsList: [],
    hidden: false,
    pageNo: 1,
    pageSize: 2,
    all: false

  },

  onLoad: function() {
    this.getData();
  },

  onPullDownRefresh: function() {
    this.setData({
      pageNo: 1,
      pageSize: 2,
      postsList: [],
      all: false

    })

    this.getData();
    console.log('下拉刷新', new Date());
  },


  onReachBottom: function() {
    this.lower();
    console.log('上拉刷新', new Date());
  },

  // 点击获取对应分类的数据
  onTapTag: function(e) {
    var that = this;
    var tab = e.currentTarget.id;
    var index = e.currentTarget.dataset.index;
    that.setData({
      activeIndex: index,
      tab: tab,
      page: 1
    });
    if (tab !== 'all') {
      that.getData({
        tab: tab
      });
    } else {
      that.getData();
    }
  },

  //获取文章列表数据
  getData: function() {
    //获取前，先进行accToken和userId的判断，若为空，跳转登陆页面
    var mUserInfo = wx.getStorageSync("mUserInfo");
    app.globalData.userInfo = mUserInfo;

    var that = this;
    if (!mUserInfo) {
      wx.redirectTo({
        url: '../index/index ',
      })
    }
    //向服务器发送请求，获取列表

    var ApiUrl = Api.lessionList;
    var lessionList = null;
    if (that.data.all) {
      console.log('已经到底了')
      return;
    }

    var dataparam = 'userId=' + mUserInfo.userId + '&accToken=' + mUserInfo.accToken + '&pageNo=' + that.data.pageNo + "&pageSize=" + that.data.pageSize;
    Api.fetchPost(ApiUrl, dataparam, (err, res) => {

      if (res.code == '0') {
        //请求成功
        lessionList = res.data.lessionList;
        lessionList = that.data.postsList.concat(lessionList);
        that.setData({
          postsList: lessionList,
          hidden: true
        });

        if (res.data.total <= that.data.pageNo * that.data.pageSize) {
          that.setData({
            all: true

          })


        } else {
          that.setData({

            pageNo: that.data.pageNo + 1




          })

        }




      } else {
        //请求失败，可能是用户登陆失效，跳转用户登陆界面
        wx.redirectTo({
          url: '../index/index ',
        })

      }

    })












  },

  // 滑动底部加载
  lower: function() {
    console.log('滑动底部加载', new Date());
    this.getData()
  }

})