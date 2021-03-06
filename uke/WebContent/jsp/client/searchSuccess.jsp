<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>搜索</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/client/searchSuccess.css"/>
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

<div class="search" id="myScrollspy">
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

        <div class="bottombar">
            <label @mouseover="flag=true" @mouseout="flag=false">
                <p>搜索内容</p>
            </label>
            <ul id="rightbar">
                <li><a href="${pageContext.request.contextPath}/client/home.action"><img src="${pageContext.request.contextPath}/images/icon/首页.png"> 首页</a> </li>
                <li><a href="${pageContext.request.contextPath}/client/recommend.action"><img src="${pageContext.request.contextPath}/images/icon/推荐.png"> 店长推荐</a> </li>
                <li><a href="${pageContext.request.contextPath}/client/shop.action"><img src="${pageContext.request.contextPath}/images/icon/积分.png"> 积分商场</a> </li>
                <li><a href="${pageContext.request.contextPath}/client/drink.action"><img src="${pageContext.request.contextPath}/images/icon/饮料.png"> 饮料系列</a> </li>
                <li><a href="${pageContext.request.contextPath}/client/snack.action"><img src="${pageContext.request.contextPath}/images/icon/小吃.png"> 休闲小吃</a> </li>
                <li><a href="${pageContext.request.contextPath}/client/menu.action"><img src="${pageContext.request.contextPath}/images/icon/套餐.png"> 优惠套餐</a> </li>
            </ul>
        </div>
    </div>
</div>

<div class="main">
    <div class="container">
    	<p>找到  ${searchCount } 个结果</p>
        <div class="series">
            <div class="content">
                <ul>
                	<c:forEach items="${searchList }" var="i" varStatus="status">
	                	<li>
	                        <a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=${i.goodsId}">
	                            <img src="${pageContext.request.contextPath}/images/${type.get(status.index) }/${i.goodsPicture}">
	                            <div class="explain">
	                                <span style="font-size:17px">${i.goodsName }</span>
	                                <c:choose>
	                                	<c:when test="${i.goodsMiddlePrice!=null }">
		                                	<span>大 <font style="color:red;">￥<font style="font-size: 21px"> ${i.goodsPrice }</font></font>
		                                    &emsp;中 <font style="color:red;">￥<font style="font-size: 21px"> ${i.goodsMiddlePrice }</font></font>
		                                	</span>
	                                	</c:when>
	                                	<c:otherwise>
	                                		<span><font style="color:red;">￥<font style="font-size: 21px"> ${i.goodsPrice }</font></font>&emsp;
		                               		 </span>
	                                	</c:otherwise>
                                	</c:choose>
	                                <span>库存 <font style="color:red;">${i.goodsInventory }</font>件 </span>
	                                <span>已售 <font style="color:red;">${i.goodsSalesVolume }</font>件
	                                    <a class="icon1 saveToBasket" title="加入购物车" @click="shopcar"><img src="${pageContext.request.contextPath}/images/icon/购物车.png"></a>
	                                    <a class="icon2 saveCollect" title="收藏" @click="collect"><img src="${pageContext.request.contextPath}/images/icon/收藏b.png"></a>
	                                </span>
	                                <input type="hidden" value="${i.goodsId }" class="id"/>
	                            </div>
	                        </a>
	                    </li>
                	</c:forEach>
                </ul>
            </div>
        </div>
    </div>
</div>

<div id="sidebar">
    <a href="${pageContext.request.contextPath}/client/userCenter/userBasket.action" ><img id="shopcarbar" title="我的购物车" src="${pageContext.request.contextPath}/images/icon/购物车a.png"></a>
    <a href="${pageContext.request.contextPath}/client/userCenter/userCollect.action" ><img id="collectbar" title="我的收藏" src="${pageContext.request.contextPath}/images/icon/收藏c.png"></a>
</div>
<div id="msg">已成功加入购物车！</div>
<div id="msg2">已收藏！</div>
<img id="end" src="${pageContext.request.contextPath}/images/icon/敬请期待.png">
<a href="#myScrollspy" data-offset="200" id="return">
    <img src="${pageContext.request.contextPath}/images/icon/顶部.png"/>
    <p>顶部</p>
</a>

<div class="footer">
    <p>copyright by gjguke.top</p>
    <p style="margin-top:-30px;">粤ICP备18138200号-1</p>
</div>
<script>
    Vue.component('personal',{
        template:'#personal'
    })
    var vm=new Vue({
        el:'#navbar',
        data:{
            flag:false
        },
        methods:{

        }
    })
    var vm=new Vue({
        el:'.search',
        data:{
            flag:false
        },
        methods:{

        }
    })

    var vm=new Vue({
        el:'.main',
        data:{
            flag:false
        },
        methods:{
            shopcar(e){
                e.target.src='${pageContext.request.contextPath}/images/icon/购物车a.png'
                e.preventDefault();
            },
            collect(e){
                e.target.src='${pageContext.request.contextPath}/images/icon/收藏c.png'
                e.preventDefault();
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
            var img = addcar.parent().find('img').attr('src');
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
                    addcar.css("cursor","default").removeClass('orange').unbind('click');

                }
            });
        });

        $(".icon2").click(function(e){
            var addcar = $(this);
            var img = addcar.parent().find('img').attr('src');
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
        
        $(".saveToBasket").click(function(){
        	var index=$(".saveToBasket").index(this);
            var	id=$(".id").eq(index).val();
            var sendData=new Array(id,"1","1");
    		$.ajax({
    			url:"${pageContext.request.contextPath}/client/saveBasketGoods.action",
    			data:JSON.stringify(sendData),
    			contentType:"application/json;charset=UTF-8",
    			type:"post",
    			dataType:"json",  //回调数据类型
    			success:function(data){
    			}
    		});
        });
        
        $(".saveCollect").click(function() {
        	var index=$(".saveCollect").index(this);
            var	id=$(".id").eq(index).val();
    		$.ajax({
    			url:"${pageContext.request.contextPath}/client/collectGoods.action",
    			data:id,
    			contentType:"application/json;charset=UTF-8",
    			type:"post",
    			dataType:"json",  //回调数据类型
    			success:function(data){
    			}
    		});
       });
    });
</script>
</body>
</html>