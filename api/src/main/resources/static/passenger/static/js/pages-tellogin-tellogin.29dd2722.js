(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["pages-tellogin-tellogin"],{"17a9":function(t,n,i){"use strict";i.r(n);var e=i("9c4f"),a=i("1e3e");for(var o in a)["default"].indexOf(o)<0&&function(t){i.d(n,t,(function(){return a[t]}))}(o);i("7611");var s=i("f0c5"),r=Object(s["a"])(a["default"],e["b"],e["c"],!1,null,"7f01fc88",null,!1,e["a"],void 0);n["default"]=r.exports},"1e3e":function(t,n,i){"use strict";i.r(n);var e=i("e077"),a=i.n(e);for(var o in e)["default"].indexOf(o)<0&&function(t){i.d(n,t,(function(){return e[t]}))}(o);n["default"]=a.a},7215:function(t,n,i){t.exports=i.p+"static/img/driver.b0033ced.png"},7611:function(t,n,i){"use strict";var e=i("f77f"),a=i.n(e);a.a},"9c4f":function(t,n,i){"use strict";i.d(n,"b",(function(){return e})),i.d(n,"c",(function(){return a})),i.d(n,"a",(function(){}));var e=function(){var t=this,n=t.$createElement,e=t._self._c||n;return e("v-uni-view",{staticClass:"login"},[e("v-uni-view",{staticClass:"content-wrapper"},[e("v-uni-view",{staticClass:"title"},[e("h1",[t._v("手机验证登录")])]),e("v-uni-view",{staticClass:"login-form"},[e("v-uni-view",{staticClass:"login-form-items"},[e("v-uni-view",{staticClass:"login-form-items-title"},[t._v("手机号")]),e("v-uni-input",{staticClass:"login-input",attrs:{type:"text",placeholder:"请输入手机号"},model:{value:t.phone,callback:function(n){t.phone=n},expression:"phone"}})],1),e("v-uni-view",{staticClass:"login-form-items"},[e("v-uni-view",{staticClass:"login-form-items-title"},[t._v("验证码")]),e("v-uni-input",{staticClass:"login-input",attrs:{type:"text",placeholder:"请输入验证码"},model:{value:t.code,callback:function(n){t.code=n},expression:"code"}}),e("v-uni-view",{staticClass:"login-form-items-title"},[e("v-uni-button",{staticClass:"yanz",attrs:{type:"primary",disabled:t.btndisabled},on:{click:function(n){arguments[0]=n=t.$handleEvent(n),t.getcode.apply(void 0,arguments)}}},[t._v(t._s(t.botstr))])],1)],1)],1)],1),e("v-uni-view",[e("v-uni-radio-group",{staticClass:"userselect",staticStyle:{margin:"26rpx 0rpx"},attrs:{name:"uc"},on:{change:function(n){arguments[0]=n=t.$handleEvent(n),t.radioChange.apply(void 0,arguments)}}},[e("v-uni-image",{staticClass:"userclass",attrs:{src:i("e0d4"),mode:""}}),e("v-uni-radio",{attrs:{value:"0"}},[t._v("乘客")]),e("v-uni-text",{staticClass:"dis"}),e("v-uni-image",{staticClass:"userclass",attrs:{src:i("7215"),mode:""}}),e("v-uni-radio",{attrs:{value:"1"}},[t._v("司机")])],1)],1),e("div",{staticClass:"submit-wrapper"},[e("v-uni-button",{staticClass:"login-btn",attrs:{type:"primary"},on:{click:function(n){arguments[0]=n=t.$handleEvent(n),t.sub.apply(void 0,arguments)}}},[t._v("登录")])],1)],1)},a=[]},e077:function(t,n,i){"use strict";i("7a82"),Object.defineProperty(n,"__esModule",{value:!0}),n.default=void 0,i("ac1f"),i("00b4"),i("e9c4");var e={data:function(){return{btndisabled:!1,botstr:"获取验证码",phone:"",code:"",userclass:0}},methods:{timewait:function(t){var n=this;setTimeout((function(){t>=0?(n.botstr=t+"秒后点击",t--,n.timewait(t)):(n.botstr="获取验证码",t=60,n.btndisabled=!1)}),900)},radioChange:function(t){this.userclass=t.detail.value},getcode:function(){var t=this;/^1[13456789]\d{9}$/.test(this.phone)?(uni.request({url:"",method:"GET",data:{tel:this.phone},success:function(n){console.log("响应结果:",n.data.data),200==n.data.status?(t.code=n.data.code,console.log(n)):uni.showToast({title:"获取验证码失败!",icon:"error"})},fail:function(t){console.log(t),uni.showToast({title:"获取验证码失败!",icon:"error"})}}),this.timewait(60),this.btndisabled=!0):uni.showToast({title:"手机输入不合法！",icon:"error"})},sub:function(){var t=this;this.phone&&this.code?uni.request({url:"",method:"POST",data:{},success:function(n){console.log("响应结果:",n.data.data),200==n.data.status?(uni.setStorageSync("userinfo",JSON.stringify(n.data.data)),uni.setStorageSync("Token",n.data.data.token),uni.setStorageSync("Identity",t.userclass),console.log("token:",n.data.data.token),uni.switchTab({url:0==t.userclass?"/pages/index/index":"/pages/orderinfo/orderinfo"})):uni.showToast({title:"登录失败!",icon:"error"})},fail:function(t){console.log("登录失败",t),uni.showToast({title:"登录失败!",icon:"error"})}}):uni.showToast({title:"请把信息填写完整!",icon:"error"})}}};n.default=e},e0d4:function(t,n,i){t.exports=i.p+"static/img/passger.b09ad535.png"},e751:function(t,n,i){var e=i("24fb");n=e(!1),n.push([t.i,'@charset "UTF-8";\n/**\n * 这里是uni-app内置的常用样式变量\n *\n * uni-app 官方扩展插件及插件市场（https://ext.dcloud.net.cn）上很多三方插件均使用了这些样式变量\n * 如果你是插件开发者，建议你使用scss预处理，并在插件代码中直接使用这些变量（无需 import 这个文件），方便用户通过搭积木的方式开发整体风格一致的App\n *\n */\n/**\n * 如果你是App开发者（插件使用者），你可以通过修改这些变量来定制自己的插件主题，实现自定义主题功能\n *\n * 如果你的项目同样使用了scss预处理，你也可以直接在你的 scss 代码中使用如下变量，同时无需 import 这个文件\n */\n/* 颜色变量 */\n/* 行为相关颜色 */\n/* 文字基本颜色 */\n/* 背景颜色 */\n/* 边框颜色 */\n/* 尺寸变量 */\n/* 文字尺寸 */\n/* 图片尺寸 */\n/* Border Radius */\n/* 水平间距 */\n/* 垂直间距 */\n/* 透明度 */\n/* 文章场景相关 */uni-page-body[data-v-7f01fc88]{background:#f4f5f6}body.?%PAGE?%[data-v-7f01fc88]{background:#f4f5f6}.login .userselect[data-v-7f01fc88]{display:flex;justify-content:center}.login .userselect .userclass[data-v-7f01fc88]{height:%?50?%;width:%?50?%}.login .userselect .dis[data-v-7f01fc88]{margin:%?0?% %?65?%}.login .content-wrapper[data-v-7f01fc88]{width:100%}.login .content-wrapper .title[data-v-7f01fc88]{margin-top:%?35?%;width:100%;margin-bottom:10px}.login .content-wrapper .title h1[data-v-7f01fc88]{border:0;width:50%;margin:0 auto;text-align:center;border-bottom:1px solid #e3e3e3;height:50px;line-height:50px;overflow:hidden;font-weight:400;font-size:%?38?%}.login .content-wrapper .login-form[data-v-7f01fc88]{margin:20px 10px 20px 15px;background:#fff}.login .content-wrapper .login-form .login-form-items[data-v-7f01fc88]{padding:15px 10px;border-bottom:1px solid #f3f4f5;position:relative;display:-webkit-flex;display:flex}.login .content-wrapper .login-form .login-form-items .login-form-items-title[data-v-7f01fc88]{width:30%;line-height:22px;height:22px;flex-shrink:0}.login .content-wrapper .login-form .login-form-items .login-form-items-title .yanz[data-v-7f01fc88]{font-size:%?22?%}.login .content-wrapper .login-form .login-form-items .login-input[data-v-7f01fc88]{width:100%}.login .submit-wrapper[data-v-7f01fc88]{padding:10px;padding-top:10px}',""]),t.exports=n},f77f:function(t,n,i){var e=i("e751");e.__esModule&&(e=e.default),"string"===typeof e&&(e=[[t.i,e,""]]),e.locals&&(t.exports=e.locals);var a=i("4f06").default;a("e19448b6",e,!0,{sourceMap:!1,shadowMode:!1})}}]);