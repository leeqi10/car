(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["pages-login-login"],{"285c":function(t,i,e){"use strict";e("7a82");var a=e("4ea4").default;Object.defineProperty(i,"__esModule",{value:!0}),i.Mcaptcha=void 0,e("d3b7"),e("159b"),e("cb29");var n=a(e("d4ec")),s=a(e("bee2")),o=function(){function t(i){(0,n.default)(this,t),this.options=i,this.fontSize=3*i.height/6,this.init(),this.refresh()}return(0,s.default)(t,[{key:"init",value:function(){this.ctx=uni.createCanvasContext(this.options.el),this.ctx.setTextBaseline("middle"),this.ctx.setFillStyle(this.randomColor(180,240))}},{key:"refresh",value:function(){for(var t=this,i="",e=["a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",0,1,2,3,4,5,6,7,8,9],a=0;a<4;a++)i+=e[this.randomNum(0,e.length)];this.options.createCodeImg=i;var n=(i+"").split("");0===n.length&&(n=["e","r","r","o","r"]);var s=.6*this.options.width/(n.length-1),o=.2*this.options.width;n.forEach((function(i,e){t.ctx.setFillStyle(t.randomColor(0,180));var a=t.randomNum(24,t.fontSize);t.ctx.setFontSize(a);var n=s*e+o-.3*a,r=t.randomNum(-30,30);t.ctx.translate(n,.5*t.options.height),t.ctx.rotate(r*Math.PI/180),t.ctx.fillText(i,0,0),t.ctx.rotate(-r*Math.PI/180),t.ctx.translate(-n,.5*-t.options.height)}));for(a=0;a<4;a++)this.ctx.strokeStyle=this.randomColor(40,180),this.ctx.beginPath(),this.ctx.moveTo(this.randomNum(0,this.options.width),this.randomNum(0,this.options.height)),this.ctx.lineTo(this.randomNum(0,this.options.width),this.randomNum(0,this.options.height)),this.ctx.stroke();for(a=0;a<this.options.width/4;a++)this.ctx.fillStyle=this.randomColor(0,255),this.ctx.beginPath(),this.ctx.arc(this.randomNum(0,this.options.width),this.randomNum(0,this.options.height),1,0,2*Math.PI),this.ctx.fill();this.ctx.draw()}},{key:"validate",value:function(t){t=t.toLowerCase();var i=this.options.createCodeImg.toLowerCase();return console.log(t),console.log(i.substring(i.length-4)),t==i.substring(i.length-4)}},{key:"randomNum",value:function(t,i){return Math.floor(Math.random()*(i-t)+t)}},{key:"randomColor",value:function(t,i){var e=this.randomNum(t,i),a=this.randomNum(t,i),n=this.randomNum(t,i);return"rgb("+e+","+a+","+n+")"}}]),t}();i.Mcaptcha=o},"4bd0":function(t,i,e){"use strict";var a=e("e01e"),n=e.n(a);n.a},6785:function(t,i,e){"use strict";e.r(i);var a=e("e551"),n=e("e77f");for(var s in n)["default"].indexOf(s)<0&&function(t){e.d(i,t,(function(){return n[t]}))}(s);e("4bd0");var o=e("f0c5"),r=Object(o["a"])(n["default"],a["b"],a["c"],!1,null,"c3f97002",null,!1,a["a"],void 0);i["default"]=r.exports},"692b":function(t,i,e){t.exports=e.p+"static/img/driver.b0033ced.png"},a8dd:function(t,i,e){"use strict";e("7a82"),Object.defineProperty(i,"__esModule",{value:!0}),i.default=void 0,e("e9c4");var a=e("285c"),n={data:function(){return{url:"",graphicVerifyCode:"",username:"",password:"",userclass:""}},onLoad:function(){this.mcaptcha=new a.Mcaptcha({el:"canvas",width:80,height:35,createCodeImg:""})},methods:{updateImageCode:function(){this.mcaptcha.refresh()},submit:function(){if(!this.graphicVerifyCode)return uni.showToast({title:"请输入图形验证码",icon:"error"}),!0;var t=this.mcaptcha.validate(this.graphicVerifyCode);return!t&&(uni.showToast({title:"图形验证码错误",icon:"error"}),!0)},sub:function(){var t=this;this.submit()||(this.username&&this.password?uni.request({url:this.url,method:"POST",data:{user:this.username,password:this.password},success:function(i){console.log("响应结果:",i.data.data),0==i.data.status?(uni.setStorageSync("userinfo",JSON.stringify(i.data.data)),uni.setStorageSync("Token",i.data.data.token),uni.setStorageSync("Type",t.userclass),console.log("token:",i.data.data.token),0==t.userclass?uni.switchTab({url:"/pages/Passenger/Passenger"}):1==t.userclass?uni.switchTab({url:"/pages/Driver/Driver"}):uni.navigateTo({url:"/pages/orderinfo/orderinfo"})):uni.showToast({title:"登录失败!",icon:"error"})}}):uni.showToast({title:"不能为空",icon:"error"}))},radioChange:function(t){this.userclass=t.detail.value,0==this.userclass?this.url="http://localhost:8023/user/login/0":this.url="http://localhost:8023/driver/login/0"}}};i.default=n},c4b4:function(t,i,e){t.exports=e.p+"static/img/passger.b09ad535.png"},cb29:function(t,i,e){var a=e("23e7"),n=e("81d5"),s=e("44d2");a({target:"Array",proto:!0},{fill:n}),s("fill")},e01e:function(t,i,e){var a=e("fa6b");a.__esModule&&(a=a.default),"string"===typeof a&&(a=[[t.i,a,""]]),a.locals&&(t.exports=a.locals);var n=e("4f06").default;n("0b413c5d",a,!0,{sourceMap:!1,shadowMode:!1})},e551:function(t,i,e){"use strict";e.d(i,"b",(function(){return a})),e.d(i,"c",(function(){return n})),e.d(i,"a",(function(){}));var a=function(){var t=this,i=t.$createElement,a=t._self._c||i;return a("v-uni-view",{staticClass:"login"},[a("v-uni-view",{staticClass:"title"},[t._v("用户登录")]),a("v-uni-view",[a("v-uni-input",{staticClass:"info",attrs:{placeholder:"请输入账号"},model:{value:t.username,callback:function(i){t.username=i},expression:"username"}}),a("v-uni-input",{staticClass:"info",attrs:{password:!0,placeholder:"请输入密码"},model:{value:t.password,callback:function(i){t.password=i},expression:"password"}})],1),a("v-uni-view",{staticClass:"code-img-wrapper",on:{click:function(i){arguments[0]=i=t.$handleEvent(i),t.updateImageCode.apply(void 0,arguments)}}},[a("v-uni-input",{staticClass:"info",staticStyle:{width:"70%"},attrs:{placeholder:"请输入验证码"},model:{value:t.graphicVerifyCode,callback:function(i){t.graphicVerifyCode=i},expression:"graphicVerifyCode"}}),a("v-uni-canvas",{staticStyle:{width:"30%",height:"100rpx","background-color":"azure","border-radius":"50rpx"},attrs:{"canvas-id":"canvas"}})],1),a("v-uni-view",[a("v-uni-radio-group",{staticClass:"userselect",staticStyle:{margin:"26rpx 0rpx"},attrs:{name:"uc"},on:{change:function(i){arguments[0]=i=t.$handleEvent(i),t.radioChange.apply(void 0,arguments)}}},[a("v-uni-image",{staticClass:"usercalss",attrs:{src:e("c4b4")}}),a("v-uni-radio",{attrs:{value:"0"}},[t._v("乘客")]),a("v-uni-text",{staticClass:"dis"}),a("v-uni-image",{staticClass:"usercalss",attrs:{src:e("692b")}}),a("v-uni-radio",{attrs:{value:"1"}},[t._v("司机")])],1)],1),a("v-uni-view",[a("v-uni-button",{staticClass:"bot",attrs:{type:"primary"},on:{click:function(i){arguments[0]=i=t.$handleEvent(i),t.sub.apply(void 0,arguments)}}},[t._v("登录")])],1),a("v-uni-view",{staticClass:"tips"},[a("v-uni-navigator",{staticStyle:{color:"skyblue"},attrs:{url:"/pages/tellogin/tellogin"}},[t._v("手机验证码登录")]),a("v-uni-text",{staticClass:"text"},[t._v("|")]),a("v-uni-navigator",{attrs:{url:"/pages/sign-in/sign-in"}},[t._v("没有账号?点击注册")])],1)],1)},n=[]},e77f:function(t,i,e){"use strict";e.r(i);var a=e("a8dd"),n=e.n(a);for(var s in a)["default"].indexOf(s)<0&&function(t){e.d(i,t,(function(){return a[t]}))}(s);i["default"]=n.a},fa6b:function(t,i,e){var a=e("24fb");i=a(!1),i.push([t.i,'@charset "UTF-8";\n/**\n * 这里是uni-app内置的常用样式变量\n *\n * uni-app 官方扩展插件及插件市场（https://ext.dcloud.net.cn）上很多三方插件均使用了这些样式变量\n * 如果你是插件开发者，建议你使用scss预处理，并在插件代码中直接使用这些变量（无需 import 这个文件），方便用户通过搭积木的方式开发整体风格一致的App\n *\n */\n/**\n * 如果你是App开发者（插件使用者），你可以通过修改这些变量来定制自己的插件主题，实现自定义主题功能\n *\n * 如果你的项目同样使用了scss预处理，你也可以直接在你的 scss 代码中使用如下变量，同时无需 import 这个文件\n */\n/* 颜色变量 */\n/* 行为相关颜色 */\n/* 文字基本颜色 */\n/* 背景颜色 */\n/* 边框颜色 */\n/* 尺寸变量 */\n/* 文字尺寸 */\n/* 图片尺寸 */\n/* Border Radius */\n/* 水平间距 */\n/* 垂直间距 */\n/* 透明度 */\n/* 文章场景相关 */.login[data-v-c3f97002]{padding:0 %?40?%}.login .title[data-v-c3f97002]{margin-top:%?120?%;margin-bottom:%?50?%;font-size:30px;color:#00f;text-align:center}.login .info[data-v-c3f97002]{margin-bottom:%?20?%;height:%?100?%;background-color:#f7f7f7;border-radius:%?50?%;letter-spacing:%?5?%;text-indent:%?50?%}.login .bot[data-v-c3f97002]{border-radius:%?60?%;margin-top:15px}.login .code-img-wrapper[data-v-c3f97002]{display:flex}.login .tips[data-v-c3f97002]{margin-top:15px;font-size:14px;color:#aaa;text-align:center;display:flex;justify-content:center}.login .tips .text[data-v-c3f97002]{margin:0 10px}.login .userselect[data-v-c3f97002]{display:flex;justify-content:center}.login .userselect .usercalss[data-v-c3f97002]{height:%?50?%;width:%?50?%}.login .userselect .dis[data-v-c3f97002]{margin:%?0?% %?65?%}',""]),t.exports=i}}]);