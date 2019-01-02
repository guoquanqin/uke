<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${goods.goodsName}</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/client/simpleGoods/goodsDetail.css"/>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.fly.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/vue.js"></script>
<script src="${pageContext.request.contextPath}/js/vue-resource.js"></script>
</head>
<body>
<nav id="navbar" class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <p class="navbar-text">您好，${USER.userName }</p>
        <ul  class="nav navbar-nav" @mouseover="flag=true" @mouseout="flag=false">
            <li ><a href="${pageContext.request.contextPath}/client/userCenter/userHome.action"> 个人中心<span class="caret"></span></a></li>
            <transition mode="out-in">
                <ul id="navlist" @mouseover="flag=true" v-if="flag" style="display: block">
                    <li><a href="${pageContext.request.contextPath}/client/userCenter/userAllOrder.action?pageNum=1">我的订单</a> </li>
                    <li><a href="${pageContext.request.contextPath}/client/userCenter/userCollect.action">我的收藏</a> </li>
                    <li><a href="${pageContext.request.contextPath}/client/userCenter/userComment.action?pageNum=1">我的评价</a> </li>
                    <li><a href="${pageContext.request.contextPath}/client/userCenter/userSetting.action">账号设置</a> </li>
                    <li><a href="${pageContext.request.contextPath}/client/userCenter/userBasket.action">购物车</a> </li>
                </ul>
            </transition>
        </ul>

        <div class="navbar-right">
            <ul class="nav navbar-nav" >
                <li><a href="${pageContext.request.contextPath}/client/home.action">首页</a> </li>
                <li><a href="${pageContext.request.contextPath}/jsp/client/register.jsp">注册账号</a> </li>
                <li><a href="${pageContext.request.contextPath}/client/exit.action">退出登录</a> </li>
                <li><a href="#" data-toggle="modal" data-target="#aboutUs">关于我们</a> </li>
            </ul>
        </div>
        
        <div class="modal fade" role="dialog" id="aboutUs">
             <div class="modal-dialog" role="document">
                 <div class="modal-content">
                     <div class="modal-header">
                         <h4>
                             关于我们
                             <button class="close" data-dismiss="modal">&times;</button>
                         </h4>
                     </div>

                     <div class="modal-body">
                         <p>优客站奶茶店网上订餐系统</p>
                         <p>Copyright by gjguke.top</p>
                         <p>备案号：粤ICP备18138200号-1</p>
                     </div>
                     <div class="modal-footer">
                         <button class="btn btn-primary" data-dismiss="modal">关闭</button>
                     </div>
                 </div>
             </div>
         </div>
    </div>
</nav>

<div class="search">
    <div class="container" >
        <a href="${pageContext.request.contextPath}/client/home.action" ><img id="logo" src="${pageContext.request.contextPath}/images/icon/logo.jpg"></a>
        <form role="form" class="form-inline" action="${pageContext.request.contextPath}/client/search.action" method="post">
            <label>
                <input type="text" name="search" placeholder="请输入套餐或产品名称" size="42" class="form-control">
                <button type="submit" class="btn btn-info">
                    <span class="glyphicon glyphicon-search"></span>
                </button>
            </label>
            <ul>
                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=104" >香辣鸡腿堡</a> </li>
                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=9" >鸳鸯奶茶</a> </li>
                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=81" >鲜榨芒果</a> </li>
                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=102" >脆皮全鸡</a> </li>
                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=155" >全家桶套餐</a> </li>
            </ul>
        </form>


    </div>
</div>

<div class="main">
    <div class="container">
    
    	<c:set value="${goods }" var="i" />
        <div class="leftPart">
          <img src="${pageContext.request.contextPath}/images/${type }/${i.goodsPicture}">
            <div class="detail">
                <h4>${i.goodsName}</h4>
                <div id="part1">
                    <span>促销量 : ${i.goodsSalesVolume } 件</span>
                    <c:if test="${i.goodsMiddlePrice==null }">
	                    <span>价格： ￥<font style="color:red;font-size:28px;">${i.goodsPrice }</font>
	                        &emsp;<font style="text-decoration:line-through">￥${i.goodsPrice+2 }</font></span>
	                    <span>好评率: <font style="color:red;">${praiseRate }%</font> </span>
                     </c:if>
                     <c:if test="${i.goodsMiddlePrice!=null }">
	                    <span>价格： 大 ￥<font style="color:red;font-size:28px;">${i.goodsPrice }</font>
	                        &emsp;中 ￥<font style="color:red;font-size:28px;">${i.goodsMiddlePrice }</font></span>
	                    <span>好评率: 
		                    <font style="color:red;">
		                    <c:if test="${size==0 }">
		                    	暂无
		                    </c:if>
		                    <c:if test="${size>0 }">
			                    ${praiseRate }%
		                    </c:if>
		                    </font> 
	                    </span>
                     </c:if>
                </div>
                <div id="part2">
                	<form action="${pageContext.request.contextPath}/client/payment.action?goodsId=${i.goodsId}" method="post">
	               		 <c:if test="${i.goodsMiddlePrice==null }">
	                    <span>数量：<input type="number" min="1" name="number" v-model="number" value="1"/>件 </span>
	                    <input type="hidden" name="size" v-model="size" value="0"/>
	                    </c:if>
	                    <c:if test="${i.goodsMiddlePrice!=null }">
		                    <span>数量：<input type="number" name="number" min="1" v-model="number" value="1"/>件
		                    	&emsp;&emsp;规格：&emsp;
			                    	<select v-model="size" name="size" style="width:50px">
			                    		<option value="1">大</option>
			                    		<option value="2">中</option>
			                    	</select> 
		                     </span>
	                    </c:if>
	                    <a class="icon1" @click="saveToBasket"><img src="${pageContext.request.contextPath}/images/icon/购物车b.png"/>加入购物车</a>
	                    <c:if test="${i.goodsInventory==0 }">
		                    <input id="submit" type="submit" value="已售罄" disabled="disabled"/>
	                    </c:if>
	                    <c:if test="${i.goodsInventory!=0 }">
		                    <input id="submit" type="submit" value="立即下单"/>
	                    </c:if>
	                    <a class="icon2" @click="saveCollect">收藏商品<img id="collect" src="${pageContext.request.contextPath}/images/icon/收藏c.png" ></a>
                    </form>
                    <input type="hidden" v-model="goodsId" value="${i.goodsId }"/>
                </div>
                <h5><img id="icon" src="${pageContext.request.contextPath}/images/icon/温馨提示.png"/> 温馨提示：本产品不宜放置太长时间，请尽量在24小时内食用</h5>

            </div>
        </div>
        <div class="rightPart">
            <h5>· · · · · · · 猜你喜欢· · · · · · · </h5>
            <ul>
            	<c:forEach items="${maxList }" var="i" varStatus="status">
            		<li>
	                    <a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=${i.goodsId}">
	                    <img src="${pageContext.request.contextPath}/images/${recommendType.get(status.index) }/${i.goodsPicture}"/>
	                    <span style="font-size:16px">${i.goodsName }</span>
	                    <span>促销价： ￥<font style="color:red">${i.goodsPrice }</font> </span>
	                    <span>销售量：<font style="color:red">${i.goodsSalesVolume }</font>件 </span>
	                    </a>
               		</li>
            	
            	</c:forEach>
            </ul>
        </div>
        <div class="evaluate">
            <ul class="nav nav-tabs">
                <li><a href="#home" class="alert-danger" data-toggle="tab">评价中心</a> </li>
            </ul>
            <c:if test="${size==0 }">
            	<img id="empty" src="${pageContext.request.contextPath}/images/icon/空.png " />
            </c:if>
            <c:if test="${size>0 }">
            <div class="tab-content">
                <div class="tab-pane active fade in" id="home">
                    <ul class="evaluateCenter" v-if="flag==false">
                    	<c:forEach items="${pageBean.commentAndUser }" var="i" varStatus="status">
	                    	<c:if test="${status.index<=6 }">
		                        <li>
		                            <div class="evaluateText">
		                                <span>${i.comment.commentContents }</span>
		                                <div>
			                                <c:forTokens items="${i.comment.commentPhoto }" delims="/" var="s">
				                                <img src="${pageContext.request.contextPath}/images/评价图片/${s}"/>
			                                </c:forTokens>
		                                </div>
		                            </div>
		                            <div class="evaluatePerson">
		                                <span>${i.phone }</span>(匿名)
		                                <p style="margin-top:-40px"><fmt:formatDate value="${i.comment.commentTime }" pattern="yyyy-MM-dd HH:mm:ss"/></p>
		                            </div>
		                        </li>
	                        </c:if>
	                        <c:if test="${status.index>6 }">
	                        </c:if>
                    	</c:forEach>
                    </ul>
                    <ul class="evaluateCenter"  v-else>
                        <li v-for="pageBeans in pageBean.commentAndUser">
                            <div class="evaluateText">
                                <span>{{pageBeans.comment.commentContents }}</span>
                                <div>
	                                 <span  v-for="photo in pageBeans.photoList">
		                                <img :src="photo"/>
	                                </span>
                                </div>
                            </div>
                            <div class="evaluatePerson">
                                <span>{{pageBeans.phone }}</span>(匿名)
                                <p style="margin-top:-40px">{{pageBeans.comment.commentTime}}</p>
                            </div>
                        </li>
                    </ul>
                    
                    <ul class="pagination">
                   		<p v-if="flag==false">共${pageBean.totalRecord }条数据，${pageBean.totalPage }页，当前页为第${pageBean.pageNum }页</p>
                   		<p v-if="flag">共${pageBean.totalRecord }条数据，${pageBean.totalPage }页，当前页为第{{pageBean.pageNum}}页</p>
                   		<li><a class="btn" ref="firstPage" @click="getFirstPage">首页</a> </li>
                    	<c:if test="${pageBean.pageNum==1 }">
                    		<c:forEach begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
                    			<c:if test="${pageBean.pageNum==i }">
			                        <li><a>${i }</a></li>
                    			</c:if>
                    			<c:if test="${pageBean.pageNum!=i}">
			                        <li><a ref="page" @click="getPage">${i }</a> </li>
                    			</c:if>
                    		</c:forEach>
	                        <li><a @click="getNextPage">下一页</a> </li>
                        </c:if>
                      
                    </ul>
                </div>
            </div>
            </c:if>
        </div>
    </div>
</div>
<div id="sidebar">
    <a href="${pageContext.request.contextPath}/client/userCenter/userBasket.action" ><img id="shopcarbar" title="我的购物车" src="${pageContext.request.contextPath}/images/icon/购物车a.png"></a>
    <a href="${pageContext.request.contextPath}/client/userCenter/userCollect.action" ><img id="collectbar" title="我的收藏" src="${pageContext.request.contextPath}/images/icon/收藏c.png"></a>
</div>
<div id="msg">已成功加入购物车！</div>
<div id="msg2">已收藏！</div>


<div class="footer">
    <p>copyright by gjguke.top</p>
    <p style="margin-top:-30px;">粤ICP备18138200号-1</p>
</div>
<script>
    Vue.component('personal',{
        template:'#personal'
    })
    var vm1=new Vue({
        el:'#navbar',
        data:{
            flag:false
        },
        methods:{

        }
    })
    var vm2=new Vue({
        el:'.search',
        data:{
            flag:false
        },
        methods:{

        }
    })
    var vm3=new Vue({
        el:'.main',
        data:{
        	flag:false,
        	pageBean:{
        		pageNum:'',
        		pageSize:7,
        		totalRecord:'',
        		commentAndUser:{},
        		totalPage:'',
        		startIndex:'',
        		start:'',
        		end:'',
        	},
        	goodsId:'${goods.goodsId}',
        	number:'1',
        	size:'1'
        	
        },
        methods:{
        	getPage(){
        		var pageNum=this.$refs.page.innerText;
	        	this.$http.post('${pageContext.request.contextPath}/client/simpleGoods/findPage.action?id=${goods.goodsId}',pageNum,{
	             	'headers':{
							'Content-Type':'application/json;charset=UTF-8'
							}
	             }).then(function(data){
	            	this.flag=true;
	             	this.pageBean=data.body;
	             	for(var i=0;i<this.pageBean.commentAndUser.length;i++){
	        			 /* 时间转换 */
	        			var date = new Date(this.pageBean.commentAndUser[i].comment.commentTime);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
	        	        Y = date.getFullYear() + '-';
	        	        M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
	        	        D = date.getDate() + ' ';
	        	        h = date.getHours() + ':';
	        	        m = date.getMinutes() + ':';
	        	        s = date.getSeconds();
	        	        if(date.getHours()<10){
	        	        	h='0'+h;
	        	        }
	        	        if(date.getMinutes()<10){
	        	        	m='0'+m;
	        	        }
	        	        if(s<10){
	        	        	s='0'+s;
	        	        }
	        	        this.pageBean.commentAndUser[i].comment.commentTime=Y+M+D+h+m+s;
	        		}
	             });
        	},
        	getFirstPage(){
        		var pageNum=1;
	        	this.$http.post('${pageContext.request.contextPath}/client/simpleGoods/findPage.action?id=${goods.goodsId}',pageNum,{
	             	'headers':{
							'Content-Type':'application/json;charset=UTF-8'
							}
	             }).then(function(data){
	            	 this.flag=true;
		             	this.pageBean=data.body;
		             	for(var i=0;i<this.pageBean.commentAndUser.length;i++){
		        			 /* 时间转换 */
		        			var date = new Date(this.pageBean.commentAndUser[i].comment.commentTime);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
		        	        Y = date.getFullYear() + '-';
		        	        M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
		        	        D = date.getDate() + ' ';
		        	        h = date.getHours() + ':';
		        	        m = date.getMinutes() + ':';
		        	        s = date.getSeconds();
		        	        if(date.getHours()<10){
		        	        	h='0'+h;
		        	        }
		        	        if(date.getMinutes()<10){
		        	        	m='0'+m;
		        	        }
		        	        if(s<10){
		        	        	s='0'+s;
		        	        }
		        	        this.pageBean.commentAndUser[i].comment.commentTime=Y+M+D+h+m+s;
		        		}
	             });
        	},
        	getNextPage(){
        		var pageNum=${pageBean.pageNum}+1;
	        	this.$http.post('${pageContext.request.contextPath}/client/simpleGoods/findPage.action?id=${goods.goodsId}',pageNum,{
	             	'headers':{
							'Content-Type':'application/json;charset=UTF-8'
							}
	             }).then(function(data){
	            	 this.flag=true;
		             	this.pageBean=data.body;
		             	for(var i=0;i<this.pageBean.commentAndUser.length;i++){
		        			 /* 时间转换 */
		        			var date = new Date(this.pageBean.commentAndUser[i].comment.commentTime);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
		        	        Y = date.getFullYear() + '-';
		        	        M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
		        	        D = date.getDate() + ' ';
		        	        h = date.getHours() + ':';
		        	        m = date.getMinutes() + ':';
		        	        s = date.getSeconds();
		        	        if(date.getHours()<10){
		        	        	h='0'+h;
		        	        }
		        	        if(date.getMinutes()<10){
		        	        	m='0'+m;
		        	        }
		        	        if(s<10){
		        	        	s='0'+s;
		        	        }
		        	        this.pageBean.commentAndUser[i].comment.commentTime=Y+M+D+h+m+s;
		        		}
	             });
        	},
        	getPreviousPage(){
        		var pageNum=${pageBean.pageNum}-1;
	        	this.$http.post('${pageContext.request.contextPath}/client/simpleGoods/findPage.action?id=${goods.goodsId}',pageNum,{
	             	'headers':{
							'Content-Type':'application/json;charset=UTF-8'
							}
	             }).then(function(data){
	            	 this.flag=true;
		             	this.pageBean=data.body;
		             	for(var i=0;i<this.pageBean.commentAndUser.length;i++){
		        			 /* 时间转换 */
		        			var date = new Date(this.pageBean.commentAndUser[i].comment.commentTime);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
		        	        Y = date.getFullYear() + '-';
		        	        M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
		        	        D = date.getDate() + ' ';
		        	        h = date.getHours() + ':';
		        	        m = date.getMinutes() + ':';
		        	        s = date.getSeconds();
		        	        if(date.getHours()<10){
		        	        	h='0'+h;
		        	        }
		        	        if(date.getMinutes()<10){
		        	        	m='0'+m;
		        	        }
		        	        if(s<10){
		        	        	s='0'+s;
		        	        }
		        	        this.pageBean.commentAndUser[i].comment.commentTime=Y+M+D+h+m+s;
		        		}
	             });
        	},
        	saveCollect() {
                this.$http.post('${pageContext.request.contextPath}/client/collectGoods.action',this.goodsId,{
                	'headers':{
						'Content-Type':'application/json;charset=UTF-8'
						}
                }).then(function(data){
                		return data.body;
                });
           },
           saveToBasket() {
        	   var sendData=new Array(this.goodsId,this.size,this.number)
        		
               	this.$http.post('${pageContext.request.contextPath}/client/saveBasketGoods.action',sendData,{
                	'headers':{
						'Content-Type':'application/json;charset=UTF-8'
						}
                }).then(function(data){
                		return data.body;
                });
           }
        }
    })
</script>
<script>
    $(function() {
        var offset = $("#shopcarbar").offset();
        var offset2 = $("#collectbar").offset();
        $(".icon1").click(function(e){
            var addcar = $(this);
            var img = "${pageContext.request.contextPath}/images/icon/购物车a.png";
            var flyer = $('<img class="u-flyer" src="'+img+'">');
            flyer.fly({
                start: {
                    left: e.pageX-$(document).scrollLeft(),  //开始位置（必填）#fly元素会被设置成position: fixed
                    top:  e.pageY-$(document).scrollTop()   //开始位置（必填）$(window).scrollTop()
                },
                end: {
                    left: offset.left+10, //结束位置（必填）
                    top: offset.top+10, //结束位置（必填）
                    width: 0, //结束时宽度
                    height: 0 //结束时高度
                },
                onEnd: function(){ //结束回调
                    $("#msg").show().animate({width: '250px'}, 200).fadeOut(1000); //提示信息
                    //addcar.css("cursor","default").removeClass('orange').unbind('click');

                }
            });
        });

        $(".icon2").click(function(e){
            var addcar = $(this);
            var img = "${pageContext.request.contextPath}/images/icon/收藏c.png";
            var flyer = $('<img class="u-flyer" src="'+img+'">');
            flyer.fly({
                start: {
                    left: e.pageX-$(document).scrollLeft(),  //开始位置（必填）#fly元素会被设置成position: fixed
                    top:  e.pageY-$(document).scrollTop()   //开始位置（必填）$(window).scrollTop()
                },
                end: {
                    left: offset2.left+10, //结束位置（必填）
                    top: offset2.top+10, //结束位置（必填）
                    width: 0, //结束时宽度
                    height: 0 //结束时高度
                },
                onEnd: function(){ //结束回调
                    $("#msg2").show().animate({width: '250px'}, 200).fadeOut(1000); //提示信息
                    addcar.css("cursor","default").removeClass('orange').unbind('click');

                }
            });
        });
        
        
    });
</script>
</body>
</html>