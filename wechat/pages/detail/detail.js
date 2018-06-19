// posts.js
var Api = require('../../utils/api.js');
var util = require('../../utils/util.js');
var app = getApp();
Page({
  data: {
    studioSrc:'',//学生录音的url
    lessionId: '',
    hidden: false,
    detail: null,
    author: '',
    contentHidden: false,
    exampleHidden: false,
    tContentHidden: false,
    tStudioHidden: false,
    readHidden: false,
    reciteHidden: false,
    read: "朗读模式",
    recite: "背诵模式",
    readState: true,
    reciteState: true,
    studioHidden: true,
    studio_logo: '/images/icon/studio1.jpg',
    voice_logo:'/images/icon/voice1.jpg',
    studioState: false,//录音状态:false、开始录音 。true、结束录音。
    modalHidden:true//确认录音上传的弹窗提示
  },

  onLoad: function (options) {
    console.log("onload")
    //navigator 跳转传递的参数传送到这里
    this.fetchData(options.id);
    this.setData({
      lessionId:options.id
    })
  },
  /**
   * 页面调整函数 start
   */

  //点击朗读按钮是触发
  read: function () {
    var that = this;
    if (that.data.readState) {
      //第一次点击时，进入朗读模式，触发以下事件
      that.setData({
        exampleHidden: true,
        tStudioHidden: true,
        reciteHidden: true,
        studioHidden: false,
        read: "朗读模式结束",
        readState: false
      })
    } else {
      //第二次点击时，取消朗读
      that.setData({
        contentHidden: false,
        exampleHidden: false,
        tStudioHidden: false,
        reciteHidden: false,
        studioHidden: true,
        read: "朗读模式",
        readState: true
     
      })
    }
  },
  /**
   * 点击背诵按钮触发
   */
  recite: function () {
    var that = this;
    if (that.data.reciteState) {
      that.setData({
        exampleHidden: true,
        contentHidden: true,
        tContentHidden: true,
        tStudioHidden: true,
        readHidden: true,
        studioHidden: false,
        recite: "背诵模式结束",
        reciteState: false
      })
    } else {
      //第二次点击时，取消朗读
      that.setData({
        exampleHidden: false,
        tStudioHidden: false,
        readHidden: false,
        contentHidden: false,
        tContentHidden: false,
        tStudioHidden: false,
        studioHidden: true,
        recite: "背诵模式",
        reciteState: true
      })

    }
  },
  studioRecord:function(){
    var that=this;
    var state = that.data.studioState;
    if(!state){
        //开始录音
        that.setData({
          studioState:true,
          studio_logo: '/images/icon/studio2.jpg'
        })
        if (that.data.read== "朗读模式结束"){
          //朗读模式
          that.setData({
            readHidden: true
          })

        }else{
          //背诵模式，隐藏朗读模式按钮
          that.setData({
            reciteHidden: true
          })
        }
        /**
         * 
         * 这里填写录音开始的代码
         * 
         */
      //初始化全局录音
        that.recorderManager = wx.getRecorderManager();
        that.recorderManager.onError(function () {
          // 录音失败的回调处理
        });
        that.recorderManager.onStop(function (res) {
          // 停止录音之后，把录取到的音频放在res.tempFilePath
          that.setData({
            studioSrc: res.tempFilePath
          })
          console.log(res.tempFilePath)
        });
        this.recorderManager.start({
          format: 'mp3' // 如果录制acc类型音频则改成aac
        });




      
    }else{
        //结束录音
        that.setData({
          studioState: false,
          studio_logo: '/images/icon/studio1.jpg',
          modalHidden: false
        })
        if (that.data.read == "朗读模式结束") {
          //朗读模式
          that.setData({
            readHidden: false
          })

        } else {
          //背诵模式，隐藏朗读模式按钮
          that.setData({
            reciteHidden: false
          })
        }
      /**
       * 这里填写录音结束 并存储的代码
       * 
       */
      this.recorderManager.stop()

    }



  },
  /**
   * 上传当前录音
   */
  upload:function(){
    var that = this;
    that.setData({
      modalHidden: true
    });
    console.log("开始上传录音")
    //上传录音伴随着状态改变，需要重新加载页面
    that.fetchData(that.data.lesssionId);

  },
  /**
   * 取消上传当前录音
   */
  cancelUpload:function(){
  var that=this;
  that.setData({
    modalHidden: true
  });
  console.log("取消上传录音")


  },


  /**
   * 页面调整函数end
   * 
   * 
   */

  // 获取数据
  fetchData: function (id) {
    var that = this;
    var ApiUrl = Api.lessionForm;
    console.log(id)
    /**
     * 测试使用
     */
    id = 'eb7d73d421a94b8a81c9ff0fbcd81385'
    that.setData({
      hidden: false,
      lessionId: id
    });
    var mUserInfo = wx.getStorageSync("mUserInfo");
    app.globalData.userInfo = mUserInfo;
    if (!mUserInfo) {
      wx.switchTab({
        url: '../index/index'
      })
    }

    var dataparam = 'userId=' + mUserInfo.userId + '&accToken=' + mUserInfo.accToken + '&lessionId=' + that.data.lessionId;
    Api.fetchPost(ApiUrl, dataparam, (err, res) => {

      if (res.code == '0') {
        //请求成功
        var detail = res.data.detail;
        that.setData({
          detail: detail,
          //隐藏转圈圈
          hidden: true
        });
        console.log(that.data.detail)

      }
    })
  },
  audioPlay: function () {
    this.audioCtx.play()
  },
  audioPause: function () {
    this.audioCtx.pause()
  },
  audio14: function () {
    this.audioCtx.seek(14)
  },
  audioStart: function () {
    this.audioCtx.seek(0)
  },
  funplay: function () {
    console.log("audio play");
  },
  funpause: function () {
    console.log("audio pause");
  },
  funtimeupdate: function (u) {
    console.log(u.detail.currentTime);
    console.log(u.detail.duration);
  },
  funended: function () {
    console.log("audio end");
  },
  funerror: function (u) {
    console.log(u.detail.errMsg);
    var that = this;
    var msg = '';
    console.log(u.detail.errMsg == 'MEDIA_ERR_SRC_NOT_SUPPORTED');
    if (u.detail.errMsg == 'MEDIA_ERR_NETWORD') {
      msg = '网络出错无法播放';
    } else if (u.detail.errMsg == 'MEDIA_ERR_DECODE' || u.detail.errMsg == 'MEDIA_ERR_SRC_NOT_SUPPORTED') {
      msg = '无法播放，请联系教师更换音频文件'
    }
    that.setData({
      author: msg
    })
  }

})