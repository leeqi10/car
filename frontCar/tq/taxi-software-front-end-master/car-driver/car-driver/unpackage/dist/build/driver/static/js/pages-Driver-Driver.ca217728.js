(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["pages-Driver-Driver"],{"07f1":function(t,e,a){var s=a("24fb");e=s(!1),e.push([t.i,'@charset "UTF-8";\n/**\n * 这里是uni-app内置的常用样式变量\n *\n * uni-app 官方扩展插件及插件市场（https://ext.dcloud.net.cn）上很多三方插件均使用了这些样式变量\n * 如果你是插件开发者，建议你使用scss预处理，并在插件代码中直接使用这些变量（无需 import 这个文件），方便用户通过搭积木的方式开发整体风格一致的App\n *\n */\n/**\n * 如果你是App开发者（插件使用者），你可以通过修改这些变量来定制自己的插件主题，实现自定义主题功能\n *\n * 如果你的项目同样使用了scss预处理，你也可以直接在你的 scss 代码中使用如下变量，同时无需 import 这个文件\n */\n/* 颜色变量 */\n/* 行为相关颜色 */\n/* 文字基本颜色 */\n/* 背景颜色 */\n/* 边框颜色 */\n/* 尺寸变量 */\n/* 文字尺寸 */\n/* 图片尺寸 */\n/* Border Radius */\n/* 水平间距 */\n/* 垂直间距 */\n/* 透明度 */\n/* 文章场景相关 */.index[data-v-471c1bd4]{width:100vw;height:100vh}.index #myMap[data-v-471c1bd4]{width:100vw;height:100vh}.index .mask[data-v-471c1bd4]{width:98vw;background-color:#fff;position:fixed;left:1vw;bottom:14vw;box-shadow:1px 1px 4px 4px #ccc}.index .mask .maskItem[data-v-471c1bd4]{height:%?120?%;border:2px solid #eee;display:flex;padding:0 %?40?%;align-items:center}.index .mask .maskItem .pic[data-v-471c1bd4]{width:%?50?%;height:%?50?%;margin-right:%?40?%}.index .mask .maskItem .place[data-v-471c1bd4]{flex:1;color:#999}.index .mask .maskItem .now[data-v-471c1bd4]{background-color:#5f9ea0;color:#fff;font-size:%?20?%;padding:%?8?%}',""]),t.exports=e},"0f15":function(t,e,a){function s(t){this.key=t.key,this.requestConfig={key:t.key,s:"rsx",platform:"WXJS",appname:t.key,sdkversion:"1.2.0",logversion:"2.0"},this.MeRequestConfig={key:t.key,serviceName:"https://restapi.amap.com/rest/me"}}a("4de4"),a("d3b7"),a("acd8"),a("14d9"),s.prototype.getWxLocation=function(t,e){wx.getLocation({type:"gcj02",success:function(t){t=t.longitude+","+t.latitude,wx.setStorage({key:"userLocation",data:t}),e(t)},fail:function(a){wx.getStorage({key:"userLocation",success:function(t){t.data&&e(t.data)}}),t.fail({errCode:"0",errMsg:a.errMsg||""})}})},s.prototype.getMEKeywordsSearch=function(t){if(!t.options)return t.fail({errCode:"0",errMsg:"缺少必要参数"});var e=t.options,a=this.MeRequestConfig,s={key:a.key,s:"rsx",platform:"WXJS",appname:t.key,sdkversion:"1.2.0",logversion:"2.0"};e.layerId&&(s.layerId=e.layerId),e.keywords&&(s.keywords=e.keywords),e.city&&(s.city=e.city),e.filter&&(s.filter=e.filter),e.sortrule&&(s.sortrule=e.sortrule),e.pageNum&&(s.pageNum=e.pageNum),e.pageSize&&(s.pageSize=e.pageSize),e.sig&&(s.sig=e.sig),wx.request({url:a.serviceName+"/cpoint/datasearch/local",data:s,method:"GET",header:{"content-type":"application/json"},success:function(e){(e=e.data)&&e.status&&"1"===e.status&&0===e.code?t.success(e.data):t.fail({errCode:"0",errMsg:e})},fail:function(e){t.fail({errCode:"0",errMsg:e.errMsg||""})}})},s.prototype.getMEIdSearch=function(t){if(!t.options)return t.fail({errCode:"0",errMsg:"缺少必要参数"});var e=t.options,a=this.MeRequestConfig,s={key:a.key,s:"rsx",platform:"WXJS",appname:t.key,sdkversion:"1.2.0",logversion:"2.0"};e.layerId&&(s.layerId=e.layerId),e.id&&(s.id=e.id),e.sig&&(s.sig=e.sig),wx.request({url:a.serviceName+"/cpoint/datasearch/id",data:s,method:"GET",header:{"content-type":"application/json"},success:function(e){(e=e.data)&&e.status&&"1"===e.status&&0===e.code?t.success(e.data):t.fail({errCode:"0",errMsg:e})},fail:function(e){t.fail({errCode:"0",errMsg:e.errMsg||""})}})},s.prototype.getMEPolygonSearch=function(t){if(!t.options)return t.fail({errCode:"0",errMsg:"缺少必要参数"});var e=t.options,a=this.MeRequestConfig,s={key:a.key,s:"rsx",platform:"WXJS",appname:t.key,sdkversion:"1.2.0",logversion:"2.0"};e.layerId&&(s.layerId=e.layerId),e.keywords&&(s.keywords=e.keywords),e.polygon&&(s.polygon=e.polygon),e.filter&&(s.filter=e.filter),e.sortrule&&(s.sortrule=e.sortrule),e.pageNum&&(s.pageNum=e.pageNum),e.pageSize&&(s.pageSize=e.pageSize),e.sig&&(s.sig=e.sig),wx.request({url:a.serviceName+"/cpoint/datasearch/polygon",data:s,method:"GET",header:{"content-type":"application/json"},success:function(e){(e=e.data)&&e.status&&"1"===e.status&&0===e.code?t.success(e.data):t.fail({errCode:"0",errMsg:e})},fail:function(e){t.fail({errCode:"0",errMsg:e.errMsg||""})}})},s.prototype.getMEaroundSearch=function(t){if(!t.options)return t.fail({errCode:"0",errMsg:"缺少必要参数"});var e=t.options,a=this.MeRequestConfig,s={key:a.key,s:"rsx",platform:"WXJS",appname:t.key,sdkversion:"1.2.0",logversion:"2.0"};e.layerId&&(s.layerId=e.layerId),e.keywords&&(s.keywords=e.keywords),e.center&&(s.center=e.center),e.radius&&(s.radius=e.radius),e.filter&&(s.filter=e.filter),e.sortrule&&(s.sortrule=e.sortrule),e.pageNum&&(s.pageNum=e.pageNum),e.pageSize&&(s.pageSize=e.pageSize),e.sig&&(s.sig=e.sig),wx.request({url:a.serviceName+"/cpoint/datasearch/around",data:s,method:"GET",header:{"content-type":"application/json"},success:function(e){(e=e.data)&&e.status&&"1"===e.status&&0===e.code?t.success(e.data):t.fail({errCode:"0",errMsg:e})},fail:function(e){t.fail({errCode:"0",errMsg:e.errMsg||""})}})},s.prototype.getGeo=function(t){var e=this.requestConfig,a=t.options;e={key:this.key,extensions:"all",s:e.s,platform:e.platform,appname:this.key,sdkversion:e.sdkversion,logversion:e.logversion},a.address&&(e.address=a.address),a.city&&(e.city=a.city),a.batch&&(e.batch=a.batch),a.sig&&(e.sig=a.sig),wx.request({url:"https://restapi.amap.com/v3/geocode/geo",data:e,method:"GET",header:{"content-type":"application/json"},success:function(e){(e=e.data)&&e.status&&"1"===e.status?t.success(e):t.fail({errCode:"0",errMsg:e})},fail:function(e){t.fail({errCode:"0",errMsg:e.errMsg||""})}})},s.prototype.getRegeo=function(t){function e(e){var s=a.requestConfig;wx.request({url:"https://restapi.amap.com/v3/geocode/regeo",data:{key:a.key,location:e,extensions:"all",s:s.s,platform:s.platform,appname:a.key,sdkversion:s.sdkversion,logversion:s.logversion},method:"GET",header:{"content-type":"application/json"},success:function(a){if(a.data.status&&"1"==a.data.status){a=a.data.regeocode;var s=a.addressComponent,i=[],o=a.roads[0].name+"附近",r=e.split(",")[0],n=e.split(",")[1];if(a.pois&&a.pois[0]){o=a.pois[0].name+"附近";var d=a.pois[0].location;d&&(r=parseFloat(d.split(",")[0]),n=parseFloat(d.split(",")[1]))}s.provice&&i.push(s.provice),s.city&&i.push(s.city),s.district&&i.push(s.district),s.streetNumber&&s.streetNumber.street&&s.streetNumber.number?(i.push(s.streetNumber.street),i.push(s.streetNumber.number)):i.push(a.roads[0].name),i=i.join(""),t.success([{iconPath:t.iconPath,width:t.iconWidth,height:t.iconHeight,name:i,desc:o,longitude:r,latitude:n,id:0,regeocodeData:a}])}else t.fail({errCode:a.data.infocode,errMsg:a.data.info})},fail:function(e){t.fail({errCode:"0",errMsg:e.errMsg||""})}})}var a=this;t.location?e(t.location):a.getWxLocation(t,(function(t){e(t)}))},s.prototype.getWeather=function(t){function e(e){var i="base";t.type&&"forecast"==t.type&&(i="all"),wx.request({url:"https://restapi.amap.com/v3/weather/weatherInfo",data:{key:a.key,city:e,extensions:i,s:s.s,platform:s.platform,appname:a.key,sdkversion:s.sdkversion,logversion:s.logversion},method:"GET",header:{"content-type":"application/json"},success:function(e){if(e.data.status&&"1"==e.data.status)if(e.data.lives){if((e=e.data.lives)&&0<e.length){e=e[0];var a={city:{text:"城市",data:e.city},weather:{text:"天气",data:e.weather},temperature:{text:"温度",data:e.temperature},winddirection:{text:"风向",data:e.winddirection+"风"},windpower:{text:"风力",data:e.windpower+"级"},humidity:{text:"湿度",data:e.humidity+"%"}};a.liveData=e,t.success(a)}}else e.data.forecasts&&e.data.forecasts[0]&&t.success({forecast:e.data.forecasts[0]});else t.fail({errCode:e.data.infocode,errMsg:e.data.info})},fail:function(e){t.fail({errCode:"0",errMsg:e.errMsg||""})}})}var a=this,s=a.requestConfig;t.city?e(t.city):a.getWxLocation(t,(function(i){(function(i){wx.request({url:"https://restapi.amap.com/v3/geocode/regeo",data:{key:a.key,location:i,extensions:"all",s:s.s,platform:s.platform,appname:a.key,sdkversion:s.sdkversion,logversion:s.logversion},method:"GET",header:{"content-type":"application/json"},success:function(a){if(a.data.status&&"1"==a.data.status){if(a=a.data.regeocode,a.addressComponent)var s=a.addressComponent.adcode;else a.aois&&0<a.aois.length&&(s=a.aois[0].adcode);e(s)}else t.fail({errCode:a.data.infocode,errMsg:a.data.info})},fail:function(e){t.fail({errCode:"0",errMsg:e.errMsg||""})}})})(i)}))},s.prototype.getPoiAround=function(t){function e(e){e={key:a.key,location:e,s:s.s,platform:s.platform,appname:a.key,sdkversion:s.sdkversion,logversion:s.logversion},t.querytypes&&(e.types=t.querytypes),t.querykeywords&&(e.keywords=t.querykeywords),wx.request({url:"https://restapi.amap.com/v3/place/around",data:e,method:"GET",header:{"content-type":"application/json"},success:function(e){if(e.data.status&&"1"==e.data.status){if((e=e.data)&&e.pois){for(var a=[],s=0;s<e.pois.length;s++){var i=0==s?t.iconPathSelected:t.iconPath;a.push({latitude:parseFloat(e.pois[s].location.split(",")[1]),longitude:parseFloat(e.pois[s].location.split(",")[0]),iconPath:i,width:22,height:32,id:s,name:e.pois[s].name,address:e.pois[s].address})}t.success({markers:a,poisData:e.pois})}}else t.fail({errCode:e.data.infocode,errMsg:e.data.info})},fail:function(e){t.fail({errCode:"0",errMsg:e.errMsg||""})}})}var a=this,s=a.requestConfig;t.location?e(t.location):a.getWxLocation(t,(function(t){e(t)}))},s.prototype.getStaticmap=function(t){function e(e){a.push("location="+e),t.zoom&&a.push("zoom="+t.zoom),t.size&&a.push("size="+t.size),t.scale&&a.push("scale="+t.scale),t.markers&&a.push("markers="+t.markers),t.labels&&a.push("labels="+t.labels),t.paths&&a.push("paths="+t.paths),t.traffic&&a.push("traffic="+t.traffic),e="https://restapi.amap.com/v3/staticmap?"+a.join("&"),t.success({url:e})}var a=[];a.push("key="+this.key);var s=this.requestConfig;a.push("s="+s.s),a.push("platform="+s.platform),a.push("appname="+s.appname),a.push("sdkversion="+s.sdkversion),a.push("logversion="+s.logversion),t.location?e(t.location):this.getWxLocation(t,(function(t){e(t)}))},s.prototype.getInputtips=function(t){var e=Object.assign({},this.requestConfig);t.location&&(e.location=t.location),t.keywords&&(e.keywords=t.keywords),t.type&&(e.type=t.type),t.city&&(e.city=t.city),t.citylimit&&(e.citylimit=t.citylimit),wx.request({url:"https://restapi.amap.com/v3/assistant/inputtips",data:e,method:"GET",header:{"content-type":"application/json"},success:function(e){e&&e.data&&e.data.tips&&t.success({tips:e.data.tips})},fail:function(e){t.fail({errCode:"0",errMsg:e.errMsg||""})}})},s.prototype.getDrivingRoute=function(t){var e=Object.assign({},this.requestConfig);t.origin&&(e.origin=t.origin),t.destination&&(e.destination=t.destination),t.strategy&&(e.strategy=t.strategy),t.waypoints&&(e.waypoints=t.waypoints),t.avoidpolygons&&(e.avoidpolygons=t.avoidpolygons),t.avoidroad&&(e.avoidroad=t.avoidroad),wx.request({url:"https://restapi.amap.com/v3/direction/driving",data:e,method:"GET",header:{"content-type":"application/json"},success:function(e){e&&e.data&&e.data.route&&t.success({paths:e.data.route.paths,taxi_cost:e.data.route.taxi_cost||""})},fail:function(e){t.fail({errCode:"0",errMsg:e.errMsg||""})}})},s.prototype.getWalkingRoute=function(t){var e=Object.assign({},this.requestConfig);t.origin&&(e.origin=t.origin),t.destination&&(e.destination=t.destination),wx.request({url:"https://restapi.amap.com/v3/direction/walking",data:e,method:"GET",header:{"content-type":"application/json"},success:function(e){e&&e.data&&e.data.route&&t.success({paths:e.data.route.paths})},fail:function(e){t.fail({errCode:"0",errMsg:e.errMsg||""})}})},s.prototype.getTransitRoute=function(t){var e=Object.assign({},this.requestConfig);t.origin&&(e.origin=t.origin),t.destination&&(e.destination=t.destination),t.strategy&&(e.strategy=t.strategy),t.city&&(e.city=t.city),t.cityd&&(e.cityd=t.cityd),wx.request({url:"https://restapi.amap.com/v3/direction/transit/integrated",data:e,method:"GET",header:{"content-type":"application/json"},success:function(e){e&&e.data&&e.data.route&&(e=e.data.route,t.success({distance:e.distance||"",taxi_cost:e.taxi_cost||"",transits:e.transits}))},fail:function(e){t.fail({errCode:"0",errMsg:e.errMsg||""})}})},s.prototype.getRidingRoute=function(t){var e=Object.assign({},this.requestConfig);t.origin&&(e.origin=t.origin),t.destination&&(e.destination=t.destination),wx.request({url:"https://restapi.amap.com/v3/direction/riding",data:e,method:"GET",header:{"content-type":"application/json"},success:function(e){e&&e.data&&e.data.route&&t.success({paths:e.data.route.paths})},fail:function(e){t.fail({errCode:"0",errMsg:e.errMsg||""})}})},t.exports.AMapWX=s},"11bc":function(t,e,a){"use strict";var s=a("2264"),i=a.n(s);i.a},2264:function(t,e,a){var s=a("07f1");s.__esModule&&(s=s.default),"string"===typeof s&&(s=[[t.i,s,""]]),s.locals&&(t.exports=s.locals);var i=a("4f06").default;i("dbdcfa04",s,!0,{sourceMap:!1,shadowMode:!1})},ab139:function(t,e,a){"use strict";a.r(e);var s=a("db4c"),i=a.n(s);for(var o in s)["default"].indexOf(o)<0&&function(t){a.d(e,t,(function(){return s[t]}))}(o);e["default"]=i.a},db4c:function(t,e,a){"use strict";a("7a82");var s=a("4ea4").default;Object.defineProperty(e,"__esModule",{value:!0}),e.default=void 0,a("14d9"),a("d3b7"),a("c740");var i=s(a("0f15")),o={data:function(){return{myAmapFun:void 0,start:"",longitude:"",latitude:"",markers:[],Dlongitude:"103.96083",Dlatitude:"30.77896",Dendaddress:"西华大学",Order:"",token:""}},onLoad:function(){this.myAmapFun=new i.default.AMapWX({key:"ceb72741060af13228ee8c0eb730b40d"}),this.getLocation()},onShow:function(){this.getAddress()},methods:{getLocation:function(){var t=this;this.myAmapFun.getRegeo({success:function(e){console.log("当前位置信息",e),t.start=e[0].name,t.longitude=e[0].longitude,t.latitude=e[0].latitude;var a={id:1,latitude:t.latitude,longitude:t.longitude,iconPath:"../../static/start.png",width:50,height:50};t.markers.push(a)},fail:function(t){console.log("获取位置失败",t)}})},getAddress:function(){this.Order=JSON.parse(uni.getStorageSync("Order")),console.log(this.Order),this.Dlongitude=this.Order["cally"],this.Dlatitude=this.Order["callx"],this.Dendaddress=this.Order["endaddr"];var t=this.markers.some((function(t){return 2===t.id}));if(t){var e=this.markers.findIndex((function(t){return 2===t.id}));this.markers[e].longitude=this.lng,this.markers[e].latitude=this.lat}else{var a={id:2,longitude:this.Dlongitude,latitude:this.Dlatitude,iconPath:"../../static/endaddress.png",width:50,height:50};this.markers.push(a)}console.log("this.markers",this.markers)}}};e.default=o},e07f:function(t,e,a){t.exports=a.p+"static/img/redaddress.d0f06c91.png"},f203:function(t,e,a){"use strict";a.d(e,"b",(function(){return s})),a.d(e,"c",(function(){return i})),a.d(e,"a",(function(){}));var s=function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("v-uni-view",{staticClass:"index"},[s("v-uni-map",{attrs:{id:"myMap",longitude:t.longitude,latitude:t.latitude,markers:t.markers}}),s("v-uni-view",{staticClass:"mask"},[s("v-uni-view",{staticClass:"maskItem"},[s("v-uni-image",{staticClass:"pic",attrs:{src:a("e07f"),mode:""}}),s("v-uni-view",{staticClass:"place"},[t._v(t._s(this.Dendaddress))]),s("v-uni-text",{staticClass:"now"},[t._v("目的地")])],1),s("v-uni-view",{staticClass:"maskItem"},[s("v-uni-image",{staticClass:"pic",attrs:{src:a("f3f2"),mode:""}}),s("v-uni-view",{staticClass:"place"},[t._v(t._s(t.start))]),s("v-uni-text",{staticClass:"now"},[t._v("当前位置")])],1)],1)],1)},i=[]},f22f:function(t,e,a){"use strict";a.r(e);var s=a("f203"),i=a("ab139");for(var o in i)["default"].indexOf(o)<0&&function(t){a.d(e,t,(function(){return i[t]}))}(o);a("11bc");var r=a("f0c5"),n=Object(r["a"])(i["default"],s["b"],s["c"],!1,null,"471c1bd4",null,!1,s["a"],void 0);e["default"]=n.exports},f3f2:function(t,e,a){t.exports=a.p+"static/img/myaddress.aec85c83.png"}}]);