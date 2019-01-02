<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>优客站主页</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/client/index.css"/>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.fly.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/vue.js"></script>
<script src="${pageContext.request.contextPath}/js/vue-resource.js"></script>

</head>
<body>
<nav id="navbar" class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
    	<c:if test="${USER!=null }">
        	<p class="navbar-text">您好，${USER.userName }</p>
        </c:if>
    	<c:if test="${USER==null }">
        	<p class="navbar-text">您好，<a href="${pageContext.request.contextPath}/jsp/client/login.jsp">请登录</a></p>
        </c:if>
        <ul  class="nav navbar-nav" @mouseover="flag=true" @mouseout="flag=false">
            <li ><a href="${pageContext.request.contextPath}/client/userCenter/userHome.action"> 个人中心<span class="caret"></span></a></li>
            <transition mode="out-in">
                <ul id="navlist" @mouseover="flag=true" v-if="flag" style="display: block">
                    <li><a href="${pageContext.request.contextPath}/client/userCenter/userAllOrder.action?pageNum=1">我的订单</a> </li>
                    <li><a href="${pageContext.request.contextPath}/client/userCenter/userCollect.action">我的收藏</a> </li>
                    <li><a href="${pageContext.request.contextPath}/client/userCenter/userComment.action?pageNum=1">我的评价</a> </li>
                    <li><a href="${pageContext.request.contextPath}/client/userCenter/userSetting.action">账号设置</a> </li>
                    <li><a href="${pageContext.request.contextPath}/client/userCenter/userBasket.action"/>购物车</a> </li>
                </ul>
            </transition>
        </ul>

        <div class="navbar-right">
            <ul class="nav navbar-nav" >
                <li><a href="${pageContext.request.contextPath}/client/home.action">首页</a> </li>
                <li><a href="${pageContext.request.contextPath}/jsp/client/register.jsp">注册账号</a> </li>
                <c:if test="${USER==null }">
	                <li><a href="${pageContext.request.contextPath}/jsp/client/login.jsp">登录账号</a> </li>
        		</c:if>
        		<c:if test="${USER!=null }">
               		<li><a href="${pageContext.request.contextPath}/client/exit.action">退出登录</a> </li>
                </c:if>
                <li><a href="#" data-toggle="modal" data-target="#aboutUs">关于我们</a> </li>
            </ul>
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



        <div class="bottombar">
            <label>
                <p>产品分类</p>
            </label>
            <ul>
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

<div id="mainnav" class="mainnav" >
    <div class="container">
        <div class="row">
            <div class="leftsidebar col-md-3" >
                <ul id="classify">
                    <li @mousemove="toShowPan1.flag=true" @mouseout="toShowPan1.flag=false" ><a href="#">经典奶茶<span class="glyphicon glyphicon-chevron-right"></span></a></li>
                    <li @mouseover="toShowPan2.flag=true" @mouseout="toShowPan2.flag=false"><a href="#">特调果汁<span class="glyphicon glyphicon-chevron-right"></span></a></li>
                    <li @mousemove="toShowPan3.flag=true" @mouseout="toShowPan3.flag=false"><a href="#">奶盖现萃茶<span class="glyphicon glyphicon-chevron-right"></span></a></li>
                    <li @mousemove="toShowPan4.flag=true" @mouseout="toShowPan4.flag=false"><a href="#">果味奶茶<span class="glyphicon glyphicon-chevron-right"></span></a></li>
                    <li @mousemove="toShowPan5.flag=true" @mouseout="toShowPan5.flag=false"><a href="#">缤纷茶园<span class="glyphicon glyphicon-chevron-right"></span></a></li>
                    <li @mousemove="toShowPan6.flag=true" @mouseout="toShowPan6.flag=false"><a href="#">益力多优酸乳<span class="glyphicon glyphicon-chevron-right"></span></a></li>
                    <li @mousemove="toShowPan7.flag=true" @mouseout="toShowPan7.flag=false"><a href="#">香浓奶昔<span class="glyphicon glyphicon-chevron-right"></span></a></li>
                    <li @mousemove="toShowPan8.flag=true" @mouseout="toShowPan8.flag=false"><a href="#">沙冰· 粒粒冰爽<span class="glyphicon glyphicon-chevron-right"></span></a></li>
                    <li @mousemove="toShowPan9.flag=true" @mouseout="toShowPan9.flag=false"><a href="#">鲜榨果汁<span class="glyphicon glyphicon-chevron-right"></span></a></li>
                    <li @mousemove="toShowPan10.flag=true" @mouseout="toShowPan10.flag=false"><a href="#">浓情咖啡<span class="glyphicon glyphicon-chevron-right"></span></a></li>
                    <li @mousemove="toShowPan11.flag=true" @mouseout="toShowPan11.flag=false"><a href="#">丝滑果奶<span class="glyphicon glyphicon-chevron-right"></span></a></li>
                    <li @mousemove="toShowPan12.flag=true" @mouseout="toShowPan12.flag=false"><a href="#">特色甜品<span class="glyphicon glyphicon-chevron-right"></span></a></li>
                    <li @mousemove="toShowPan13.flag=true" @mouseout="toShowPan13.flag=false"><a href="#">休闲小吃系列<span class="glyphicon glyphicon-chevron-right"></span></a></li>
                    <li @mousemove="toShowPan14.flag=true" @mouseout="toShowPan14.flag=false"><a href="#">优惠套餐系列<span class="glyphicon glyphicon-chevron-right"></span></a></li>

                </ul>
                <div class="allpanel">
                    <transition name="pan1" mode="out-in">
                        <div class="panel"  @mouseover="toShowPan1.flag=true" @mouseout="toShowPan1.flag=false"  v-if="toShowPan1.flag" style="display:block" >
                            <div class="panel-heading  alert-info">
                                <h2 class="panel-title">经典奶茶</h2>
                                <a href="${pageContext.request.contextPath}/client/drink.action">更多<span class="glyphicon glyphicon-arrow-right"></span></a>
                            </div>
                            <div class="panel-body">
                                <ul>
                                    <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=1">招牌原味奶茶</a> </li>
                                    <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=2">珍珠奶茶</a> </li>
                                    <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=3">椰果奶茶</a> </li>
                                    <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=4">西米奶茶</a> </li>
                                    <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=5">奶茶粒粒爽</a> </li>
                                    <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=6">布丁奶茶</a> </li>
                                    <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=7">红豆奶茶</a> </li>
                                    <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=8">焦糖奶茶</a> </li>
                                    <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=9">鸳鸯奶茶</a> </li>
                                    <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=10">红豆抹茶</a> </li>
                                    <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=11">草莓爆珠奶茶</a> </li>
                                    <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=12">芒果爆珠奶茶</a> </li>
                                    <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=13">太妃巧克力奶茶</a> </li>
                                    <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=14">芒椰奶昔</a> </li>
                                    <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=15">黑钻水晶</a> </li>
                                    <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=16">奶茶烧仙草</a> </li>
                                    <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=17">红豆芋治抹茶</a> </li>
                                </ul>
                            </div>
                        </div>
                    </transition>
                    <transition name="pan1" mode="out-in">
                         <div class="panel" v-if="toShowPan2.flag" @mouseout="toShowPan2.flag=false" @mousemove="toShowPan2.flag=true" style="display: block" >
                        <div class="panel-heading alert-info">
                            <h2 class="panel-title">特调果汁</h2>
                            <a href="${pageContext.request.contextPath}/client/drink.action">更多<span class="glyphicon glyphicon-arrow-right"></span></a>
                        </div>
                        <div class="panel-body">
                            <ul>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=18">梦幻之恋</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=19">极度诱惑</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=20">浪漫之夜</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=21">绿野仙踪</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=22">梦醒时分</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=23">夏威夷黄昏</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=24">三色恋人</a> </li>
                            </ul>
                        </div>
                    </div>
                    </transition>
                    <transition name="pan1" mode="out-in">
                        <div class="panel" v-if="toShowPan3.flag" @mouseout="toShowPan3.flag=false" @mousemove="toShowPan3.flag=true" style="display: block">
                        <div class="panel-heading  alert-info">
                            <h2 class="panel-title">奶盖现萃茶</h2>
                            <a href="${pageContext.request.contextPath}/client/drink.action">更多<span class="glyphicon glyphicon-arrow-right"></span></a>
                        </div>
                        <div class="panel-body">
                            <ul>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=25">芝士新樱清风</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=26">芝士白桃乌龙</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=27">芝士玫瑰乌龙</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=28">芝士桂花乌龙</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=29">奶盐新樱清风</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=30">奶盐白桃乌龙</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=31">奶盐玫瑰乌龙</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=32">奶盐桂花乌龙</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=33">抹茶奶盖茶</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=34">芝士奥利奥奶茶</a> </li>
                            </ul>
                        </div>
                    </div>
                    </transition>
                    <transition name="pan1" mode="out-in">
                         <div class="panel" v-if="toShowPan4.flag" @mouseout="toShowPan4.flag=false" @mousemove="toShowPan4.flag=true" style="display: block">
                        <div class="panel-heading  alert-info">
                            <h2 class="panel-title">果味奶茶</h2>
                            <a href="${pageContext.request.contextPath}/client/drink.action">更多<span class="glyphicon glyphicon-arrow-right"></span></a>
                        </div>
                        <div class="panel-body">
                            <ul>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=35">草莓奶茶</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=36">芋香奶茶</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=37">芒果奶茶</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=38">木瓜奶茶</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=39">柠檬奶茶</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=40">蓝莓奶茶</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=41">椰香奶茶</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=42">玫瑰奶茶</a> </li>
                            </ul>
                        </div>
                    </div>
                    </transition>
                    <transition name="pan1" mode="out-in">
                        <div class="panel" v-if="toShowPan5.flag" @mouseout="toShowPan5.flag=false" @mousemove="toShowPan5.flag=true" style="display: block">
                        <div class="panel-heading  alert-info">
                            <h2 class="panel-title">缤纷茶园</h2>
                            <a href="${pageContext.request.contextPath}/client/drink.action">更多<span class="glyphicon glyphicon-arrow-right"></span></a>
                        </div>
                        <div class="panel-body">
                            <ul>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=43">柠檬可乐</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=44">金桔柠檬</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=45">芒果冰茶</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=46">柠檬芦荟茶</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=47">桂圆红枣茶</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=48">鲜柠柚子茶</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=49">洛神之恋</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=50">寒天爱玉</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=51">冰霜红茶</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=52">百香红茶</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=53">柠檬绿茶</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=54">爆柠百香果</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=55">缤纷绿茶</a> </li>
                            </ul>
                        </div>
                    </div>
                    </transition>
                    <transition name="pan1" mode="out-in">
                        <div class="panel" v-if="toShowPan6.flag" @mouseout="toShowPan6.flag=false" @mousemove="toShowPan6.flag=true" style="display: block">
                        <div class="panel-heading  alert-info">
                            <h2 class="panel-title">益力多优酸乳</h2>
                            <a href="${pageContext.request.contextPath}/client/drink.action">更多<span class="glyphicon glyphicon-arrow-right"></span></a>
                        </div>
                        <div class="panel-body">
                            <ul>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=56">草莓益力多</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=57">芒果益力多</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=58">柠檬益力多</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=59">百香果益力多</a> </li>
                            </ul>
                        </div>
                    </div>
                    </transition>
                    <transition name="pan1" mode="out-in">
                        <div class="panel" v-if="toShowPan7.flag" @mouseout="toShowPan7.flag=false" @mousemove="toShowPan7.flag=true" style="display: block">
                        <div class="panel-heading  alert-info">
                            <h2 class="panel-title">香浓奶昔</h2>
                            <a href="${pageContext.request.contextPath}/client/drink.action">更多<span class="glyphicon glyphicon-arrow-right"></span></a>
                        </div>
                        <div class="panel-body">
                            <ul>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=60">葡萄奶昔</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=61">草莓奶昔</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=62">芒果奶昔</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=63">芋香奶昔</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=64">柠檬奶昔</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=65">蓝莓奶昔</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=66">巧克力奶昔</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=67">哈密瓜奶昔</a> </li>
                            </ul>
                        </div>
                    </div>
                    </transition>
                    <transition name="pan1" mode="out-in">
                        <div class="panel" v-if="toShowPan8.flag" @mouseout="toShowPan8.flag=false" @mousemove="toShowPan8.flag=true" style="display: block">
                        <div class="panel-heading  alert-info">
                            <h2 class="panel-title">沙冰· 粒粒冰爽</h2>
                            <a href="${pageContext.request.contextPath}/client/drink.action">更多<span class="glyphicon glyphicon-arrow-right"></span></a>
                        </div>
                        <div class="panel-body">
                            <ul>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=68">芒果粒粒冰</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=69">草莓粒粒冰</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=70">凤梨粒粒冰</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=71">柳橙粒粒冰</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=72">蓝莓粒粒冰</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=73">葡萄粒粒冰</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=74">西瓜粒粒冰</a> </li>
                            </ul>
                        </div>
                    </div>
                    </transition>
                    <transition name="pan1" mode="out-in">
                        <div class="panel" v-if="toShowPan9.flag" @mouseout="toShowPan9.flag=false" @mousemove="toShowPan9.flag=true" style="display: block">
                        <div class="panel-heading  alert-info">
                            <h2 class="panel-title">鲜榨果汁</h2>
                            <a href="${pageContext.request.contextPath}/client/drink.action">更多<span class="glyphicon glyphicon-arrow-right"></span></a>
                        </div>
                        <div class="panel-body">
                            <ul>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=75">鲜榨凤梨</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=76">鲜榨哈密瓜</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=77">鲜榨奇异果</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=78">鲜榨橙汁</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=79">鲜榨火龙果</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=80">鲜榨百香果</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=81">鲜榨芒果</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=82">鲜榨苹果</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=83">鲜榨西瓜</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=84">鲜榨雪梨</a> </li>
                            </ul>
                        </div>
                    </div>
                    </transition>
                    <transition name="pan1" mode="out-in">
                        <div class="panel" v-if="toShowPan10.flag" @mouseout="toShowPan10.flag=false" @mousemove="toShowPan10.flag=true" style="display: block">
                        <div class="panel-heading  alert-info">
                            <h2 class="panel-title">浓情咖啡</h2>
                            <a href="${pageContext.request.contextPath}/client/drink.action">更多<span class="glyphicon glyphicon-arrow-right"></span></a>
                        </div>
                        <div class="panel-body">
                            <ul>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=85">雀巢咖啡</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=86">丝滑拿铁</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=87">蓝山咖啡</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=88">摩卡咖啡</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=89">卡布奇诺</a> </li>
                            </ul>
                        </div>
                    </div>
                    </transition>
                    <transition name="pan1" mode="out-in">
                        <div class="panel" v-if="toShowPan11.flag" @mouseout="toShowPan11.flag=false" @mousemove="toShowPan11.flag=true" style="display: block">
                        <div class="panel-heading alert-info">
                            <h2 class="panel-title">丝滑果奶</h2>
                            <a href="${pageContext.request.contextPath}/client/drink.action">更多<span class="glyphicon glyphicon-arrow-right"></span></a>
                        </div>
                        <div class="panel-body">
                            <ul>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=90">凤梨丝滑果奶</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=91">哈密瓜丝滑果奶</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=92">巧克力丝滑果奶</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=93">芒果丝滑果奶</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=94">草莓丝滑果奶</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=95">葡萄丝滑果奶</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=96">香芋丝滑果奶</a> </li>
                            </ul>
                        </div>
                    </div>
                    </transition>
                    <transition name="pan1" mode="out-in">
                        <div class="panel" v-if="toShowPan12.flag" @mouseout="toShowPan12.flag=false" @mousemove="toShowPan12.flag=true" style="display: block">
                        <div class="panel-heading alert-info">
                            <h2 class="panel-title">特色甜品</h2>
                            <a href="${pageContext.request.contextPath}/client/drink.action">更多<span class="glyphicon glyphicon-arrow-right"></span></a>
                        </div>
                        <div class="panel-body">
                            <ul>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=97">原味双皮奶</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=98">草莓双皮奶</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=99">芒果双皮奶</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=100">芋香双皮奶</a> </li>
                            </ul>
                        </div>
                    </div>
                    </transition>
                    <transition name="pan1" mode="out-in">
                        <div class="panel" v-if="toShowPan13.flag" @mouseout="toShowPan13.flag=false" @mousemove="toShowPan13.flag=true" style="display: block">
                        <div class="panel-heading  alert-info">
                            <h2 class="panel-title">休闲小吃系列</h2>
                            <a href="${pageContext.request.contextPath}/client/snack.action">更多<span class="glyphicon glyphicon-arrow-right"></span></a>
                        </div>
                        <div class="panel-body">
                            <ul>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=101">蜜汁手扒鸡</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=102">脆皮全鸡</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=103">秘制板烧堡</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=104">香辣鸡腿堡</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=105">猪扒堡</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=106">双层猪扒堡</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=107">双层牛肉堡</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=108">双层鸡腿堡</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=109">香酥奥尔良鸡腿堡</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=110">至珍七虾堡</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=111">墨西哥鸡肉卷</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=112">薯条</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=113">奥尔良烤翅</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=114">香辣鸡腿</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=115">香酥鸡腿</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=116">秘制烤全腿</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=117">爆浆芝士鸡排</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=118">劲辣鸡排</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=119">鳕鱼棒</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=120">蝴蝶虾</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=121">海洋鸡块</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=122">黑椒鸡块</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=123">鸡米花</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=124">无骨鸡柳</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=125">金丝香芋酥</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=126">玉米布丁酥</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=127">香芋地瓜丸</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=128">脆皮香蕉</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=129">南瓜饼</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=130">鱿鱼串</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=131">川香鸡柳</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=132">骨肉相连</a> </li>
                            </ul>
                        </div>
                    </div>
                    </transition>
                    <transition name="pan1" mode="out-in">
                        <div class="panel" v-if="toShowPan14.flag" @mouseout="toShowPan14.flag=false" @mousemove="toShowPan14.flag=true" style="display: block">
                        <div class="panel-heading alert-info">
                            <h2 class="panel-title">优惠套餐系列</h2>
                            <a href="${pageContext.request.contextPath}/client/menu.action">更多<span class="glyphicon glyphicon-arrow-right"></span></a>
                        </div>
                        <div class="panel-body">
                            <ul>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=133">薯条两份</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=134">汉堡+橙汁</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=135">鸡肉卷+草莓汁</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=136">汉堡+薯条</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=137">鸡肉卷+黑椒鸡块</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=138">板烧堡+薯条</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=139">汉堡两份</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=140">鸡肉卷两份</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=141">汉堡+鸡肉卷</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=142">手扒鸡+可乐</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=143">脆皮鸡+可乐</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=144">手扒鸡+薯条+可乐</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=145">汉堡+薯条+可乐</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=146">鸡肉卷+薯条+可乐</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=147">辣翅+烤翅+可乐</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=148">劲辣鸡排+薯条+可乐</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=149">汉堡+辣翅+可乐</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=150">鸡肉卷+烤翅+可乐</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=151" title="2小腿+2骨肉相连+3黑椒鸡块+薯条+3香芋地瓜丸">小吃拼盘</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=152" title="3辣翅+3烤翅">鸡翅套餐</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=153" title="2汉堡+2骨肉相连+1份南瓜饼+薯条+2可乐">超值朋友套餐</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=154" title="3汉堡+草莓汁+3鸡柳+薯条+香芋地瓜丸+2可乐+2小腿">超值家庭套餐</a> </li>
                                <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=155" title="手扒鸡+3汉堡+4小腿+薯条+2可乐+橙汁">超值全家福</a> </li>
                            </ul>
                        </div>
                    </div>
                    </transition>
                </div>
            </div>

            <div class="centersidebar col-md-6">
                <div class="carousel slide" ref="myCarousel"  id="myCarousel">
                        <!--  标识符部分  -->
                        <ul class="carousel-indicators">
                            <li class="active" data-target="#myCarousel" data-slide-to="0"></li>
                            <li data-target="#myCarousel" data-slide-to="1"></li>
                            <li data-target="#myCarousel" data-slide-to="2"></li>
                        </ul>

                        <div class="carousel-inner">
                            <div class="item active">
                                <a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=151"><img src="${pageContext.request.contextPath}/images/优惠套餐/小吃拼盘.jpg"></a>
                            </div>
                            <div class="item">
                                <a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=142"><img src="${pageContext.request.contextPath}/images/优惠套餐/手扒鸡+可乐.jpg"></a>
                            </div>
                            <div class="item">
                                <a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=153"><img src="${pageContext.request.contextPath}/images/优惠套餐/超值朋友套餐.jpg"></a>
                            </div>
                        </div>

                        <a class="carousel-control left" href="#myCarousel" data-slide="prev">
                            <span class="glyphicon glyphicon-chevron-left"></span>
                        </a>
                        <a class="carousel-control right" href="#myCarousel" data-slide="next">
                            <span class="glyphicon glyphicon-chevron-right"></span>
                        </a>


                    </div>

            </div>

            <div class="rightsidebar ">
                <div class="panel">
                	<c:if test="${USER!=null }">
	                    <div class="panel-heading">
	                        <a href="${pageContext.request.contextPath}/client/userCenter/userSetting.action"><img src="${pageContext.request.contextPath}/images/用户头像/${USER.userPhoto}" /></a>
	                        <p>${USER.userName }</p>
	                    </div>
	                    <div class="panel-body">
	                        <ul>
	                            <li><a href="${pageContext.request.contextPath}/client/userCenter/userAllOrder.action?pageNum=1"><img src="${pageContext.request.contextPath}/images/icon/订单.png" />&nbsp;&nbsp; 我的订单</a> </li>
	                            <li><a href="${pageContext.request.contextPath}/client/userCenter/userCollect.action"><img src="${pageContext.request.contextPath}/images/icon/收藏.png" />&nbsp;&nbsp; 我的收藏</a> </li>
	                            <li><a href="${pageContext.request.contextPath}/client/userCenter/userComment.action?pageNum=1"><img src="${pageContext.request.contextPath}/images/icon/评价.png" />&nbsp;&nbsp; 我的评价</a> </li>
	                            <li><a href="${pageContext.request.contextPath}/client/userCenter/userSetting.action"><img src="${pageContext.request.contextPath}/images/icon/设置.png" />&nbsp;&nbsp; 账号设置</a> </li>
	                        </ul>
	                    </div>
					</c:if>
					
					<c:if test="${USER==null }">
						<div class="panel-heading">
	                        <img src="${pageContext.request.contextPath}/images/icon/用户b.png" />
	                    </div>
	                    <div class="panel-body">
	                    	<a style="margin-left:100px;" class="btn btn-info" href="${pageContext.request.contextPath}/jsp/client/login.jsp">去登录</a>
	                    </div>
					</c:if>
                </div>
                <div class="record">
                    <span class="col-md-3"><img src="${pageContext.request.contextPath}/images/icon/logo2.png" data-toggle="modal" data-target="#infoModal" title="优客站奶茶店"/></span>
                    <span class="col-md-7">
                        <p>地址：${store.storeAddress }</p>
                        <p>热线电话：${store.storePhone }</p>
                        <p id="showInfo" data-toggle="modal" data-target="#infoModal" style="color:deepskyblue;">详情</p>
                    </span>
                </div>
                <div class="modal fade" role="dialog" id="infoModal">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4>
                                    优客站奶茶店
                                    <button class="close" data-dismiss="modal">&times;</button>
                                </h4>
                            </div>

                            <div class="modal-body">
                                <div class="carousel slide" ref="myCarouse2"  id="myCarouse2">
                                    <!--  标识符部分  -->
                                    <ul class="carousel-indicators">
                                        <li class="active" data-target="#myCarouse2" data-slide-to="0"></li>
                                        <li data-target="#myCarouse2" data-slide-to="1"></li>
                                        <li data-target="#myCarouse2" data-slide-to="2"></li>
                                        <li data-target="#myCarouse2" data-slide-to="3"></li>
                                    </ul>

                                    <div class="carousel-inner">
                                        <div class="item active">
                                            <img src="${pageContext.request.contextPath}/images/门店/1.jpg">
                                        </div>
                                        <div class="item">
                                            <img src="${pageContext.request.contextPath}/images/门店/2.jpg">
                                        </div>
                                        <div class="item">
                                            <img src="${pageContext.request.contextPath}/images/门店/3.jpg">
                                        </div>
                                        <div class="item">
                                            <img src="${pageContext.request.contextPath}/images/门店/4.jpg">
                                        </div>
                                    </div>

                                    <a class="carousel-control left" href="#myCarouse2" data-slide="prev">
                                        <span class="glyphicon glyphicon-chevron-left"></span>
                                    </a>
                                    <a class="carousel-control right" href="#myCarouse2" data-slide="next">
                                        <span class="glyphicon glyphicon-chevron-right"></span>
                                    </a>


                                </div>
                                <div class="summary">
                                    <span id="introduce"><p>店铺简介：</p>
                                        <p>${store.storeIntroduction }</p></span>
                                    <span>地址：${store.storeAddress }</span>
                                    <span>电话：${store.storePhone }</span>
                                    <span>营业时间：${store.businessHours }</span>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button class="btn btn-primary" data-dismiss="modal">关闭</button>
                            </div>
                        </div>
                    </div>
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
        </div>
		
        <div class="shop">
            <div class="panel">
                <div class="panel-heading alert-danger">
                    <h4>热门产品</h4>
                </div>

                <div class="panel-body">
                    <ul>
                       
                       	<c:forEach items="${maxList }" var="i"  varStatus="status">
                        	<li>
	                            <a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=${i.goodsId}"><img class="icon" src="${pageContext.request.contextPath}/images/${type.get(status.index)}/${i.goodsPicture}" title="${i.goodsName }"/></a>
	                            <div class="detail">
	                               <span><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=${i.goodsId}">${i.goodsName }</a></span>
	                               <c:choose>
	                               		<c:when test="${i.goodsMiddlePrice!=null }">
	                               			<p>大<span style="color: red">￥<font style="font-size:24px"> ${i.goodsPrice }</font></span>&nbsp;&nbsp;&nbsp;
	                               				中<span style="color: red">￥<font style="font-size:24px"> ${i.goodsMiddlePrice }</font></span>&emsp;&emsp;已售 <span style="color: red">${i.goodsSalesVolume }</span>件</p>
	                               		</c:when>
	                               		<c:otherwise>
	                               			<p><span style="color: red">￥<font style="font-size:24px"> ${i.goodsPrice }</font></span>&nbsp;&nbsp;&nbsp;
	                                    &nbsp;&nbsp;已售 <span style="color: red">${i.goodsSalesVolume }</span>件</p>
	                               		</c:otherwise>
	                               </c:choose>
	                                <span>库存&ensp;<span style="color: red">${i.goodsInventory }&emsp;</span>件</span>
	                                <span ><a class="saveToBasket"><img class="shopcar" @click="shopcar" src="${pageContext.request.contextPath}/images/icon/购物车.png" title="加入购物车"></a>
	                                   <a class="saveCollect"><img class="collect" @click="collect" src="${pageContext.request.contextPath}/images/icon/收藏b.png" title="收藏"></a>
	                                 </span>
	                               	<input type="hidden" value="${i.goodsId }" class="id"/>
	                            </div>
	                          </li>
	                          
                        </c:forEach>
                      
                    </ul>
                </div>
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
<div class="footer">
    <p>copyright by gjguke.top</p>
    <p style="margin-top:-30px;">粤ICP备18138200号-1</p>
</div>
<script>
        $(function(){
            $("#myCarousel").carousel({
                interval:2500  //每隔3秒切换一次
            });
            $("#myCarouse2").carousel({
                interval:2500  //每隔3秒切换一次
            });
            $("#showInfo").click(function () {
                $("#infoModal").modal({
                    backdrop:'static',  //表示点击背景幕的时候模态框不消失
                    show:true          //表示一上来就显示
                });
            });
            var offset = $("#shopcarbar").offset();
            var offset2 = $("#collectbar").offset();
            $(".shopcar").click(function(e){
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

            $(".collect").click(function(e){
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
            
            
        })
    </script>

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

    var vm2=new Vue({
        el:'#mainnav',
        data:{
            toShowPan1:{
                flag:false
            },
            toShowPan2:{
                flag:false
            },
            toShowPan3:{
                flag:false
            },
            toShowPan4:{
                flag:false
            },
            toShowPan5:{
                flag:false
            },
            toShowPan6:{
                flag:false
            },
            toShowPan7:{
                flag:false
            },
            toShowPan8:{
                flag:false
            },
            toShowPan9:{
                flag:false
            },
            toShowPan10:{
                flag:false
            },
            toShowPan11:{
                flag:false
            },
            toShowPan12:{
                flag:false
            },
            toShowPan13:{
                flag:false
            },
            toShowPan14:{
                flag:false
            },
            goodsId:'',
        	number:'1',
        	size:'1'

        },
        methods:{
            shopcar(e){
                e.target.src='${pageContext.request.contextPath}/images/icon/购物车a.png'
                e.preventDefault();
            },
            collect(e){
                e.target.src='${pageContext.request.contextPath}/images/icon/收藏c.png'
                e.preventDefault();
            },
            saveCollect() {
                /* this.$http.post('${pageContext.request.contextPath}/client/collectGoods.action',this.goodsId,{
                	'headers':{
						'Content-Type':'application/json;charset=UTF-8'
						}
                }).then(function(data){
                		return data.body;
                }); */
           },
           saveToBasket() {
        	   var id="";
        	   var index="";
        	   $(".saveToBasket").click(function(){
	        	   index=$(".saveToBasket").index(this);
	               id=$(".id").eq(index).val();
        	   })
        	   var sendData=new Array(this.goodsId,this.size,this.number)
        	   alert(index)
        	   alert(id)
               	/* this.$http.post('${pageContext.request.contextPath}/client/saveBasketGoods.action',sendData,{
                	'headers':{
						'Content-Type':'application/json;charset=UTF-8'
						}
                }).then(function(data){
                		return data.body;
                }); */
           }
            
        }
    })

</script>


</body>
</html>