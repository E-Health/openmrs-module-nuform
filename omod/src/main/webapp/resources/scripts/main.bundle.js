webpackJsonp([2],{579:function(t,e){e.HmrState=function(){}},541:function(t,e,n){"use strict";var r=n(1),o=function(){function t(){this.name="Angular2 NuForm",this.url="http://nuchange.ca/nuform",this.bootstrap=!0,this.nuform=NUFORM}return t.prototype.canvasEnter=function(){this._fabric=new fabric.Canvas("canvas"),this.bootstrap&&(this._fabric.setBackgroundImage(this.nuform.image,this._fabric.renderAll.bind(this._fabric)),this._fabric.loadFromJSON(this.nuform.nuform_in,this._fabric.renderAll.bind(this._fabric)),this.bootstrap=!1),this._fabric.isDrawingMode=!0,console.log("Canvas Initialized")},t.prototype.canvasLeave=function(){},t.prototype.saveClicked=function(){this.nuform.nuform_out=JSON.stringify(this._fabric),NUFORM=this.nuform,this._fabric.isDrawingMode=!1,console.log(NUFORM.nuform_out)},t.prototype.clearClicked=function(){this._fabric.clear(),this._fabric.isDrawingMode=!0},t=__decorate([r.Component({selector:"nuform-app",styles:[n(763)],template:'\n   <canvas id="canvas" (mouseover)="canvasEnter()" (mouseleave)="canvasLeave()" #myCanvas [width]="nuform.width" [height]="nuform.height"></canvas>\n   <button (click)="saveClicked()">Save</button><button (click)="clearClicked()">Clear</button>\n  '}),__metadata("design:paramtypes",[])],t)}();e.App=o},542:function(t,e){"use strict";e.routes=[],e.asyncRoutes={},e.prefetchRouteCallbacks=[]},371:function(t,e,n){"use strict";var r=n(1),o=n(579),a=function(){function t(){this._state={}}return Object.defineProperty(t.prototype,"state",{get:function(){return this._state=this._clone(this._state)},set:function(t){throw new Error("do not mutate the `.state` directly")},enumerable:!0,configurable:!0}),t.prototype.get=function(t){var e=this.state;return e.hasOwnProperty(t)?e[t]:e},t.prototype.set=function(t,e){return this._state[t]=e},t.prototype._clone=function(t){return JSON.parse(JSON.stringify(t))},__decorate([o.HmrState(),__metadata("design:type",Object)],t.prototype,"_state",void 0),t=__decorate([r.Injectable(),__metadata("design:paramtypes",[])],t)}();e.AppState=a},763:function(t,e){t.exports="body,html{height:100%;font-family:Arial,Helvetica,sans-serif}span.active{background-color:gray}button{background:#00a0d1;background:-webkit-gradient(linear,left top,left bottom,color-stop(0,#00a0d1),color-stop(100%,#008db8));background:-webkit-linear-gradient(top,#00a0d1,#008db8);background:linear-gradient(top,#00a0d1,#008db8);filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#00A0D1',endColorstr='#008DB8',GradientType=0);padding:8px 20px;color:#cfebf3;font-family:Helvetica Neue,sans-serif;font-size:13px;border-radius:40px;-moz-border-radius:40px;-webkit-border-radius:40px;border:1px solid #095b7e}"},543:function(t,e,n){"use strict";function r(t){for(var n in t)e.hasOwnProperty(n)||(e[n]=t[n])}r(n(541)),r(n(371)),r(n(542));var o=n(371);e.APP_PROVIDERS=[o.AppState]},0:function(t,e,n){"use strict";function r(t){return o.bootstrap(i.App).then(a.decorateComponentRef).catch(function(t){return console.error(t)})}var o=n(356),a=n(544),i=n(543);e.main=r;document.addEventListener("DOMContentLoaded",function(){return r()})},544:function(t,e,n){"use strict";var r=n(115),o=n(1),a=[],i=function(t){return t};r.disableDebugTools(),o.enableProdMode(),a=a.slice(),e.decorateComponentRef=i,e.ENV_PROVIDERS=a.slice()}});
//# sourceMappingURL=main.2a920b66fde54a0a173d.bundle.map