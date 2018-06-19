'use strict';
// api 路径
var HOST = 'http://127.0.0.1:8090/mengmengyuan/';
//用户模块
var user_module = 'user'
var login = HOST + user_module + '/login';
var changePassword = HOST + user_module + "/changePassword"
//课文模块
var lession_module = 'lession'
// get /topics 主题首页
var lessionList = HOST + lession_module + '/lessionList';
var lessionForm = HOST + lession_module + '/lessionForm';


// get请求方法
function fetchGet(url, callback) {
  // return callback(null, top250)
  wx.request({
    url: url,
    header: { 'Content-Type': 'application/json' },
    success(res) {
      callback(null, res.data)
    },
    fail(e) {
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
    success(res) {
      callback(null, res.data)
    },
    fail(e) {
      console.error(e)
      callback(e)
    }
  })
}

module.exports = {
  // API
  lessionList: lessionList,
  login: login,
  changePassword: changePassword,
  lessionForm: lessionForm,
  // METHOD
  fetchGet: fetchGet,
  fetchPost: fetchPost


}
