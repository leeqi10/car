(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["pages-sign-in-sign-in"],{"318e":function(t,i,e){"use strict";e("7a82"),Object.defineProperty(i,"__esModule",{value:!0}),i.default=void 0,e("ac1f"),e("00b4");var s={data:function(){return{user:"",pwd:"",compwd:"",driverid:"",carid:"",carclass:"",btndisabled:!1,botstr:"获取验证码",phone:"",code:"",userclass:0}},methods:{timewait:function(t){var i=this;setTimeout((function(){t>=0?(i.botstr=t+"秒后点击",t--,i.timewait(t)):(i.botstr="获取验证码",t=60,i.btndisabled=!1)}),900)},radioChange:function(t){this.userclass=t.detail.value},getcode:function(){/^1[13456789]\d{9}$/.test(this.phone)?(uni.request({url:"http://localhost:8023/user/code",method:"POST",data:{tel:this.phone},success:function(t){console.log("响应结果:",t.data.data),200==t.data.status||uni.showToast({title:"获取验证码失败!",icon:"error"})}}),this.timewait(60),this.btndisabled=!0):uni.showToast({title:"手机输入不合法！",icon:"error"})},sgin_in:function(){if(0==this.userclass){if(!(this.user&&this.pwd&&this.compwd&&this.phone&&this.code))return void uni.showToast({title:"请把信息填写完整!",icon:"error"});if(this.pwd!=this.compwd)return void uni.showToast({title:"两次输入的密码不对!",icon:"error"});uni.request({url:"http://localhost:8023/user/login/"+this.code,method:"POST",data:{user:this.user,password:this.pwd,tel:this.phone},success:function(t){console.log("响应结果:",t.data.data),0==t.data.status?(uni.setStorageSync("Token",t.data.data.token),console.log("token:",t.data.data.token),uni.showToast({icon:"success",title:"注册成功！请稍后"}),uni.navigateTo({url:"/pages/login/login"})):uni.showToast({title:"注册失败!",icon:"error"})}})}else{if(!(this.user&&this.pwd&&this.compwd&&this.phone&&this.code&&this.driverid&&this.carid&&this.carclass))return void uni.showToast({title:"请把信息填写完整!",icon:"error"});if(this.pwd!=this.compwd)return void uni.showToast({title:"两次输入的密码不对!",icon:"error"});uni.request({url:"http://localhost:8023/driver/login/"+this.code,method:"POST",data:{user:this.user,password:this.pwd,tel:this.phone,jsz:this.driverid,carno:this.carid,catype:this.carclass},success:function(t){console.log("响应结果:",t.data.data),0==t.data.status?(uni.setStorageSync("Token",t.data.data.token),console.log("token:",t.data.data.token),uni.showToast({icon:"success",title:"注册成功！请稍后"}),setTimeout("console.log('即将跳转到登录页面')",3e3),uni.navigateTo({url:"/pages/login/login"})):uni.showToast({title:"注册失败!",icon:"error"})}})}}}};i.default=s},"39d8":function(t,i,e){"use strict";e.r(i);var s=e("5175"),n=e("90d2");for(var a in n)["default"].indexOf(a)<0&&function(t){e.d(i,t,(function(){return n[t]}))}(a);e("4b48");var o=e("f0c5"),r=Object(o["a"])(n["default"],s["b"],s["c"],!1,null,"63b0206a",null,!1,s["a"],void 0);i["default"]=r.exports},"4b48":function(t,i,e){"use strict";var s=e("56f8"),n=e.n(s);n.a},5175:function(t,i,e){"use strict";e.d(i,"b",(function(){return s})),e.d(i,"c",(function(){return n})),e.d(i,"a",(function(){}));var s=function(){var t=this,i=t.$createElement,s=t._self._c||i;return s("v-uni-view",{staticClass:"login"},[s("v-uni-view",{staticClass:"content-wrapper"},[s("v-uni-view",{staticClass:"title"},[s("h1",[t._v("账号注册")])]),s("v-uni-view",{staticClass:"login-form"},[s("v-uni-view",{staticClass:"login-form-items"},[s("v-uni-view",{staticClass:"login-form-items-title"},[t._v("账号")]),s("v-uni-input",{staticClass:"login-input",attrs:{type:"text",placeholder:"请输入账号"},model:{value:t.user,callback:function(i){t.user=i},expression:"user"}})],1),s("v-uni-view",{staticClass:"login-form-items"},[s("v-uni-view",{staticClass:"login-form-items-title"},[t._v("密码")]),s("v-uni-input",{staticClass:"login-input",attrs:{type:"text",placeholder:"请输入密码"},model:{value:t.pwd,callback:function(i){t.pwd=i},expression:"pwd"}})],1),s("v-uni-view",{staticClass:"login-form-items"},[s("v-uni-view",{staticClass:"login-form-items-title"},[t._v("确认密码")]),s("v-uni-input",{staticClass:"login-input",attrs:{type:"text",placeholder:"请再次输入密码"},model:{value:t.compwd,callback:function(i){t.compwd=i},expression:"compwd"}})],1),1==t.userclass?s("v-uni-view",[s("v-uni-view",{staticClass:"login-form-items"},[s("v-uni-view",{staticClass:"login-form-items-title"},[t._v("驾驶证号")]),s("v-uni-input",{staticClass:"login-input",attrs:{type:"text",placeholder:"请输入驾驶证号"},model:{value:t.driverid,callback:function(i){t.driverid=i},expression:"driverid"}})],1),s("v-uni-view",{staticClass:"login-form-items"},[s("v-uni-view",{staticClass:"login-form-items-title"},[t._v("车牌号")]),s("v-uni-input",{staticClass:"login-input",attrs:{type:"text",placeholder:"请输入车牌号"},model:{value:t.carid,callback:function(i){t.carid=i},expression:"carid"}})],1),s("v-uni-view",{staticClass:"login-form-items"},[s("v-uni-view",{staticClass:"login-form-items-title"},[t._v("车型")]),s("v-uni-input",{staticClass:"login-input",attrs:{type:"text",placeholder:"请输入车型"},model:{value:t.carclass,callback:function(i){t.carclass=i},expression:"carclass"}})],1)],1):t._e(),s("v-uni-view",{staticClass:"login-form-items"},[s("v-uni-view",{staticClass:"login-form-items-title"},[t._v("手机号")]),s("v-uni-input",{staticClass:"login-input",attrs:{type:"text",placeholder:"请输入手机号"},model:{value:t.phone,callback:function(i){t.phone=i},expression:"phone"}})],1),s("v-uni-view",{staticClass:"login-form-items"},[s("v-uni-view",{staticClass:"login-form-items-title"},[t._v("验证码")]),s("v-uni-input",{staticClass:"login-input",attrs:{type:"text",placeholder:"请输入验证码"},model:{value:t.code,callback:function(i){t.code=i},expression:"code"}}),s("v-uni-view",{staticClass:"login-form-items-title"},[s("v-uni-button",{staticClass:"yanz",attrs:{type:"primary",disabled:t.btndisabled},on:{click:function(i){arguments[0]=i=t.$handleEvent(i),t.getcode.apply(void 0,arguments)}}},[t._v(t._s(t.botstr))])],1)],1)],1)],1),s("v-uni-view",[s("v-uni-radio-group",{staticClass:"userselect",staticStyle:{margin:"26rpx 0rpx"},attrs:{name:"uc"},on:{change:function(i){arguments[0]=i=t.$handleEvent(i),t.radioChange.apply(void 0,arguments)}}},[s("v-uni-image",{staticClass:"userclass",attrs:{src:e("e0d4"),mode:""}}),s("v-uni-radio",{attrs:{value:"0"}},[t._v("乘客")]),s("v-uni-text",{staticClass:"dis"}),s("v-uni-image",{staticClass:"userclass",attrs:{src:e("7215"),mode:""}}),s("v-uni-radio",{attrs:{value:"1"}},[t._v("司机")])],1)],1),s("div",{staticClass:"submit-wrapper"},[s("v-uni-button",{staticClass:"login-btn",attrs:{type:"primary"},on:{click:function(i){arguments[0]=i=t.$handleEvent(i),t.sgin_in.apply(void 0,arguments)}}},[t._v("注册")])],1)],1)},n=[]},"56f8":function(t,i,e){var s=e("fb4d");s.__esModule&&(s=s.default),"string"===typeof s&&(s=[[t.i,s,""]]),s.locals&&(t.exports=s.locals);var n=e("4f06").default;n("41918635",s,!0,{sourceMap:!1,shadowMode:!1})},7215:function(t,i,e){t.exports=e.p+"static/img/driver.b0033ced.png"},"90d2":function(t,i,e){"use strict";e.r(i);var s=e("318e"),n=e.n(s);for(var a in s)["default"].indexOf(a)<0&&function(t){e.d(i,t,(function(){return s[t]}))}(a);i["default"]=n.a},e0d4:function(t,i,e){t.exports=e.p+"static/img/passger.b09ad535.png"},fb4d:function(t,i,e){var s=e("24fb");i=s(!1),i.push([t.i,'@charset "UTF-8";\n/**\n * 这里是uni-app内置的常用样式变量\n *\n * uni-app 官方扩展插件及插件市场（https://ext.dcloud.net.cn）上很多三方插件均使用了这些样式变量\n * 如果你是插件开发者，建议你使用scss预处理，并在插件代码中直接使用这些变量（无需 import 这个文件），方便用户通过搭积木的方式开发整体风格一致的App\n *\n */\n/**\n * 如果你是App开发者（插件使用者），你可以通过修改这些变量来定制自己的插件主题，实现自定义主题功能\n *\n * 如果你的项目同样使用了scss预处理，你也可以直接在你的 scss 代码中使用如下变量，同时无需 import 这个文件\n */\n/* 颜色变量 */\n/* 行为相关颜色 */\n/* 文字基本颜色 */\n/* 背景颜色 */\n/* 边框颜色 */\n/* 尺寸变量 */\n/* 文字尺寸 */\n/* 图片尺寸 */\n/* Border Radius */\n/* 水平间距 */\n/* 垂直间距 */\n/* 透明度 */\n/* 文章场景相关 */uni-page-body[data-v-63b0206a]{background:#f4f5f6}body.?%PAGE?%[data-v-63b0206a]{background:#f4f5f6}.login .userselect[data-v-63b0206a]{display:flex;justify-content:center}.login .userselect .userclass[data-v-63b0206a]{height:%?50?%;width:%?50?%}.login .userselect .dis[data-v-63b0206a]{margin:%?0?% %?65?%}.login .content-wrapper[data-v-63b0206a]{width:100%}.login .content-wrapper .title[data-v-63b0206a]{margin-top:%?35?%;width:100%;margin-bottom:10px}.login .content-wrapper .title h1[data-v-63b0206a]{border:0;width:50%;margin:0 auto;text-align:center;border-bottom:1px solid #e3e3e3;height:50px;line-height:50px;overflow:hidden;font-weight:400;font-size:%?38?%}.login .content-wrapper .login-form[data-v-63b0206a]{margin:20px 10px 20px 15px;background:#fff}.login .content-wrapper .login-form .login-form-items[data-v-63b0206a]{padding:15px 10px;border-bottom:1px solid #f3f4f5;position:relative;display:-webkit-flex;display:flex}.login .content-wrapper .login-form .login-form-items .login-form-items-title[data-v-63b0206a]{width:30%;line-height:22px;height:22px;flex-shrink:0}.login .content-wrapper .login-form .login-form-items .login-form-items-title .yanz[data-v-63b0206a]{font-size:%?22?%}.login .content-wrapper .login-form .login-form-items .login-input[data-v-63b0206a]{width:100%}.login .submit-wrapper[data-v-63b0206a]{padding:10px;padding-top:10px}',""]),t.exports=i}}]);