(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["pages-orderinfo-orderinfo"],{"0951":function(t,n,e){var i=e("cd72");i.__esModule&&(i=i.default),"string"===typeof i&&(i=[[t.i,i,""]]),i.locals&&(t.exports=i.locals);var o=e("4f06").default;o("195c9fa0",i,!0,{sourceMap:!1,shadowMode:!1})},"62f4":function(t,n,e){"use strict";e.r(n);var i=e("de63"),o=e.n(i);for(var a in i)["default"].indexOf(a)<0&&function(t){e.d(n,t,(function(){return i[t]}))}(a);n["default"]=o.a},"6c8e":function(t,n,e){"use strict";e.r(n);var i=e("9b79"),o=e("62f4");for(var a in o)["default"].indexOf(a)<0&&function(t){e.d(n,t,(function(){return o[t]}))}(a);e("f93f");var s=e("f0c5"),c=Object(s["a"])(o["default"],i["b"],i["c"],!1,null,"d3c07fc0",null,!1,i["a"],void 0);n["default"]=c.exports},"9b79":function(t,n,e){"use strict";e.d(n,"b",(function(){return i})),e.d(n,"c",(function(){return o})),e.d(n,"a",(function(){}));var i=function(){var t=this,n=t.$createElement,i=t._self._c||n;return i("v-uni-view",{staticClass:"destination"},[i("v-uni-view",{staticClass:"content"},t._l(t.list,(function(n){return i("v-uni-view",{key:n.id,staticClass:"item",on:{click:function(e){arguments[0]=e=t.$handleEvent(e),t.toIndex(n)}}},[i("v-uni-image",{attrs:{src:e("9f45"),mode:""}}),i("v-uni-view",{staticClass:"text"},[i("v-uni-view",{staticClass:"title"},[i("v-uni-view",[t._v("用户名："+t._s(n.name))]),i("v-uni-view",[t._v("距离："+t._s(n.phone))]),i("v-uni-view",[t._v("起始地址："+t._s(n.staddr))]),i("v-uni-view",[t._v("目的地："+t._s(n.endaddr))])],1),i("v-uni-button",{staticClass:"button"},[t._v("接单")])],1)],1)})),1)],1)},o=[]},"9f45":function(t,n,e){t.exports=e.p+"static/img/address.f16d750f.png"},cd72:function(t,n,e){var i=e("24fb");n=i(!1),n.push([t.i,'@charset "UTF-8";\n/**\n * 这里是uni-app内置的常用样式变量\n *\n * uni-app 官方扩展插件及插件市场（https://ext.dcloud.net.cn）上很多三方插件均使用了这些样式变量\n * 如果你是插件开发者，建议你使用scss预处理，并在插件代码中直接使用这些变量（无需 import 这个文件），方便用户通过搭积木的方式开发整体风格一致的App\n *\n */\n/**\n * 如果你是App开发者（插件使用者），你可以通过修改这些变量来定制自己的插件主题，实现自定义主题功能\n *\n * 如果你的项目同样使用了scss预处理，你也可以直接在你的 scss 代码中使用如下变量，同时无需 import 这个文件\n */\n/* 颜色变量 */\n/* 行为相关颜色 */\n/* 文字基本颜色 */\n/* 背景颜色 */\n/* 边框颜色 */\n/* 尺寸变量 */\n/* 文字尺寸 */\n/* 图片尺寸 */\n/* Border Radius */\n/* 水平间距 */\n/* 垂直间距 */\n/* 透明度 */\n/* 文章场景相关 */.destination[data-v-d3c07fc0]{width:100vw;height:100vh;overflow:scroll;padding:%?40?% %?20?%;box-sizing:border-box}.destination .content[data-v-d3c07fc0]{box-shadow:1px 1px 2px 2px #eee;height:100%}.destination .content uni-image[data-v-d3c07fc0]{width:%?70?%;height:%?70?%;margin:%?40?%}.destination .content .item[data-v-d3c07fc0]{height:%?200?%;border-bottom:2.5px solid #eee;display:flex;align-items:center}.destination .content .item .input[data-v-d3c07fc0]{flex:1}.destination .content .item .text[data-v-d3c07fc0]{display:flex;flex-grow:1}.destination .content .item .text .title[data-v-d3c07fc0]{text-align:left;font-size:%?24?%;color:#aaa;margin-top:%?8?%;flex-grow:1;margin-right:10px}.destination .content .item .text .button[data-v-d3c07fc0]{display:inline-block;padding:12px 24px;font-size:10px;font-weight:700;text-align:center;text-decoration:none;color:#fff;background-color:#37c;box-shadow:0 2px 4px rgba(0,0,0,.2);transition:all .3s ease-in-out;border:2px solid #ccc;border-radius:50px}.destination .content .item .text .button[data-v-d3c07fc0]:hover{background-color:#259;box-shadow:0 4px 8px rgba(0,0,0,.3);-webkit-transform:translateY(-2px);transform:translateY(-2px)}.destination .content .item .cancel[data-v-d3c07fc0]{width:%?120?%;height:%?60?%;line-height:%?60?%;text-align:center;background-color:#fff;color:#333;font-size:%?30?%;border-radius:%?30?%;box-shadow:0 0 %?2?% %?2?% #ddd;margin-right:%?20?%}.destination .content .list[data-v-d3c07fc0]{height:calc(100% - %?120?%);overflow:scroll}',""]),t.exports=n},de63:function(t,n,e){"use strict";e("7a82"),Object.defineProperty(n,"__esModule",{value:!0}),n.default=void 0,e("e9c4");var i={data:function(){return{myAmapFun:void 0,list:[],token:""}},onLoad:function(){var t=this;this.token=uni.getStorageSync("Token"),""!==this.token?uni.request({url:"http://localhost:8023/driver/selectAllOrders",method:"POST",data:{longitudeform:"30.646991",latitudefrom:"104.180789"},success:function(n){console.log(n),t.list=n.data.data,console.log(t.list),console.log(t.list[0].staddr)},fail:function(t){console.log("获取订单信息失败"),uni.showToast({title:"获取订单信息失败",icon:"error"})}}):uni.navigateTo({url:"/pages/login/login"})},methods:{toIndex:function(t){console.log("当前点击的是",t),uni.request({url:"",method:"POST",data:{orderId:t.id},success:function(n){uni.setStorageSync("Order",JSON.stringify(t)),uni.switchTab({url:"/pages/Driver/Driver"})},fail:function(t){uni.showToast({title:"抢单失败",icon:"error"})}})}}};n.default=i},f93f:function(t,n,e){"use strict";var i=e("0951"),o=e.n(i);o.a}}]);