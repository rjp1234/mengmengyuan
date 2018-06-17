'use strict';
// api 路径
var HOST = 'http://127.0.0.1:8090/mengmengyuan/';
//用户模块
var user_module = 'user'
var login = HOST + user_module +'/login';
var changePassword = HOST + user_module +"/changePassword"
//课文模块
var lession_module='lession'
// get /topics 主题首页
var lessionList = HOST + lession_module+'/lessionList';
//get /topic/:id 主题详情
var topic = HOST + '/topic';
// post /accesstoken 验证 accessToken 的正确性
var accesstoken = HOST + '/accesstoken';
// post /topic_collect/collect 收藏主题
var collect = HOST + '/topic_collect/collect';
// post /topic_collect/de_collect 取消主题
var de_collect = HOST + '/topic_collect/de_collect';
// post /reply/:reply_id/ups 为评论点赞
function reply (id) {
  return HOST + "/reply/"+ id +"/ups"
}

// get请求方法
function fetchGet(url, callback) {
  // return callback(null, top250)
  wx.request({
    url: url,
    header: { 'Content-Type': 'application/json' },
    success (res) {
      callback(null, res.data)
    },
    fail (e) {
      console.error(e)
      callback(e)
    }
  })
}

// post请求方法
function fetchPost(url, data, callback) {
  wx.request({
    method: 'POST',
    url: url,
    header: { 'Content-Type': 'application/x-www-form-urlencoded' },
    data: data,
    success (res) {
      callback(null, res.data)
    },
    fail (e) {
      console.error(e)
      callback(e)
    }
  })
}

module.exports = {
  // API
  lessionList: lessionList,
  topic: topic,
  accesstoken: accesstoken,
  collect: collect,
  de_collect: de_collect,
  reply: reply,
  login:login,
  changePassword: changePassword,

  // METHOD
  fetchGet: fetchGet,
  fetchPost: fetchPost


}
