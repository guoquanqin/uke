<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户中心</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/client/userHome.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/client/sign.css"/>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.fly.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/vue.js"></script>
<script src="${pageContext.request.contextPath}/js/vue-resource.js"></script>
<script src="${pageContext.request.contextPath}/js/calendar.js"></script>
</head>
<body>
<nav id="navbar" class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <p class="navbar-text">您好，${USER.userName } </p>
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

<div class="content">
    <div class="container">
        <div class="leftsidebar">
            <div id="userInfo">
                <div id="user">
                    <a href="${pageContext.request.contextPath}/client/userCenter/userSetting.action"><img id="userHead" src="${pageContext.request.contextPath}/images/用户头像/${userinfo.userPhoto}"></a>
                    <span id="signbtn" data-toggle="modal" data-target="#signin" @click="getSign"><p>签到</p></span>
                    <div class="modal fade" role="dialog" id="signin">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h4>
                                        签到中心
                                        <button class="btn btn-info " @click="sign">签到</button>
                                        <button class="close" data-dismiss="modal">&times;</button>
                                    </h4>
                                </div>

                                <div class="modal-body">
                                    <div  id="calendar"></div>

                                    <div id="sign_note">
                                        <p style="color:red">*规则：本月签到20天即可领取积分</p>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button class="btn btn-primary" data-dismiss="modal">关闭</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="userbox">
                        <span>上次登录：<fmt:formatDate value="${USER.lastLoginTime }" pattern="yyyy-MM-dd"/></span>
                        <span>我的积分：<font style="color:red;">${USER.userIntegral }</font> </span>
                        <span><a href="${pageContext.request.contextPath}/client/userCenter/userAllOrder.action?pageNum=1"><img src="${pageContext.request.contextPath}/images/icon/订单.png">历史订单</a></span>
                        <span><a href="${pageContext.request.contextPath}/client/userCenter/userSetting.action"><img src="${pageContext.request.contextPath}/images/icon/用户a.png">用户中心</a></span>
                    </div>
                </div>
            </div>
            <div id="list">
                <div class="listhead">
                    <span><img src="${pageContext.request.contextPath}/images/icon/用户b.png">个人中心</span>
                </div>
                <div class="listcontent">
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/client/userCenter/userAllOrder.action?pageNum=1" >商品订单</a> </li>
                        <li><a href="${pageContext.request.contextPath}/client/userCenter/userAllIntegralOrder.action?pageNum=1">积分订单</a></li>
                        <li><a href="${pageContext.request.contextPath}/client/userCenter/userBasket.action">我的购物车</a> </li>
                        <li><a href="${pageContext.request.contextPath}/client/userCenter/userCollect.action">我的收藏夹</a> </li>
                        <li><a href="${pageContext.request.contextPath}/client/userCenter/userComment.action?pageNum=1">我的评价</a> </li>
                        <li><a href="${pageContext.request.contextPath}/client/userCenter/userIntegral.action">我的积分</a> </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="rightsidebar">
            <div id="domain">
                <div id="leftdomain">
                    <span><img src="${pageContext.request.contextPath}/images/用户头像/${USER.userPhoto}"></span>
                    <span>
                        <p>${USER.userName }</p>
                        <p>默认地址：${USER.userAddress }</p>
                        <p>注册时间: <fmt:formatDate value="${USER.registerTime }" pattern="yyyy-MM-dd HH:mm:ss"/>  </p>
                        <a href="${pageContext.request.contextPath}/client/userCenter/userSetting.action">设置中心 >></a>
                    </span>
                </div>
                <div id="rightdomain">
                    <span>
                        <a href="${pageContext.request.contextPath}/client/userCenter/userOrderWaitForPayment.action?pageNum=1">
                            <img src="${pageContext.request.contextPath}/images/icon/待付款.png">
                            <p>待付款 <font style="color:red;">${count1 }</font> </p>
                        </a>
                    </span>
                    <span>
                        <a href="${pageContext.request.contextPath}/client/userCenter/userOrderWaitForGet.action?pageNum=1">
                            <img src="${pageContext.request.contextPath}/images/icon/待收货.png">
                            <p>待收货 <font style="color:red;">${count2 }</font> </p>
                        </a>
                    </span>
                    <span>
                        <a href="${pageContext.request.contextPath}/client/userCenter/userOrderWaitForComment.action?pageNum=1">
                            <img src="${pageContext.request.contextPath}/images/icon/待评价.png">
                            <p>待评价 <font style="color:red;">${count3 }</font> </p>
                        </a>
                    </span>
                </div>
            </div>
            <div id="order">
                <div id="ordertop">
                    <span><img src="${pageContext.request.contextPath}/images/icon/订单a.png">我的订单</span>
                    <span><a href="${pageContext.request.contextPath}/client/userCenter/userAllOrder.action?pageNum=1">查看全部订单 >></a> </span>
                </div>
                <div id="ordercontent">
                	<c:forEach items="${orderDetail.orderUserList }" begin="0" end="1" var="i">
	                    <div class="part">
	                        <div class="leftpart" ref="leftpart">
			                    <c:forEach items="${i.orderGoodsList }" varStatus="status" begin="0" end="2" var="j">
	                            <img src="${pageContext.request.contextPath}/images/${j.goodsType.gtName }/${j.goods.goodsPicture}">
	                            </c:forEach>
	                        </div>
	                        <div class="rightpart">
	                            <span><font ref="text1">
				                    <c:forEach items="${i.orderGoodsList }" varStatus="status" begin="0" end="2" var="j">
	                           		 	<c:if test="${!status.last }">
	                           		 		${j.goods.goodsName }+
	                           		 	</c:if>
	                           		 	<c:if test="${status.last }">
	                           		 		${j.goods.goodsName }...
	                           		 	</c:if>
	                           		 	
		                            </c:forEach>
	                           	</font></span>
	                            <span>总价格： ￥${i.orderPrice }</span>
	                            <c:choose>
	                            	<c:when test="${i.orderStatus==1 }">
			                            <span ref="pay_situation1">待支付</span>
	                            	</c:when>
	                            	<c:when test="${i.orderStatus==2 }">
			                            <span ref="pay_situation1">已支付</span>
	                            	</c:when>
	                            	<c:when test="${i.orderStatus==3 }">
			                            <span ref="pay_situation1">正在配送</span>
	                            	</c:when>
	                            	<c:when test="${i.orderStatus==4 }">
			                            <span ref="pay_situation1">已收货</span>
	                            	</c:when>
	                            	<c:when test="${i.orderStatus==5 }">
			                            <span ref="pay_situation1">待评价</span>
	                            	</c:when>
	                            	<c:when test="${i.orderStatus==6 }">
			                            <span ref="pay_situation1">已评价</span>
	                            	</c:when>
	                            	<c:when test="${i.orderStatus==7 }">
			                            <span ref="pay_situation1">已完成</span>
	                            	</c:when>
	                            </c:choose>
			                    <span><a href="${pageContext.request.contextPath}/client/userCenter/userAllOrder.action?pageNum=1" class="btn btn-info">查看详情</a></span>
	                        </div>
	                    </div>
                    </c:forEach>
                </div>
            </div>
            <div id="collection">
                <div id="colltop">
                    <span><img src="${pageContext.request.contextPath}/images/icon/收藏a.png">我的收藏</span>
                    <span><a href="${pageContext.request.contextPath}/client/userCenter/userCollect.action">查看全部收藏 >></a> </span>
                </div>
                <div class="collectlist">
                    <ul>
                   		<c:forEach items="${goodsInCollectList }" var="i" begin="0" end="5" varStatus="status">
	                        <li>
	                            <a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=${i.goodsId}"><img src="${pageContext.request.contextPath}/images/${typeInCollectGoods.get(status.index).gtName }/${i.goodsPicture}"></a>
	                            <div class="detail">
	                                <span><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=${i.goodsId}">${i.goodsName }</a></span>
	                                <c:if test="${i.goodsMiddlePrice!=null }">
	                               		<p>大<span style="color: red">￥${i.goodsPrice }</span>&nbsp;&nbsp;&nbsp;
	                               		      中<span style="color: red">￥${i.goodsMiddlePrice }</span></p>
	                                </c:if>
	                               	<c:if test="${i.goodsMiddlePrice==null }">
		                                <p><span style="color: red">￥${i.goodsPrice }</span>&nbsp;&nbsp;&nbsp;
		                                <span style="text-decoration:line-through">￥${i.goodsPrice+2 }</span></p>
	                               	</c:if>
	                            </div>
	                        </li>
                   		</c:forEach>
                    </ul>
                </div>
            </div>
            <div id="recommend">
                <div id="recomtop">
                    <span><img src="${pageContext.request.contextPath}/images/icon/收藏a.png">新品推荐</span>
                    <span><a href="${pageContext.request.contextPath}/client/recommend.action">查看更多 >></a> </span>
                </div>
                <div class="recommendlist">
                    <ul>
                   	    <c:forEach items="${goodsInRecommend }" var="i" begin="0" end="5" varStatus="status">
	                        <li>
	                            <a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=${i.goodsId}"><img src="${pageContext.request.contextPath}/images/${typeInReommendGoods.get(status.index).gtName }/${i.goodsPicture}"></a>
	                            <div class="detail">
	                                <span><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=${i.goodsId}">${i.goodsName }</a></span>
	                                <c:if test="${i.goodsMiddlePrice!=null }">
	                               		<p>大<span style="color: red">￥${i.goodsPrice }</span>&nbsp;&nbsp;&nbsp;
	                               		      中<span style="color: red">￥${i.goodsMiddlePrice }</span></p>
	                                </c:if>
	                               	<c:if test="${i.goodsMiddlePrice==null }">
		                                <p><span style="color: red">￥${i.goodsPrice }</span>&nbsp;&nbsp;&nbsp;
		                                <span style="text-decoration:line-through">￥${i.goodsPrice+2 }</span></p>
	                               	</c:if>
	                            </div>
	                        </li>
                        </c:forEach>
                        
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="footer">
    <p>copyright by gjguke.top</p>
    <p style="margin-top:-30px;">粤ICP备18138200号-1</p>
</div>

<script>
    var vm=new Vue({
        el:'#navbar',
        data:{
            flag:false,
        },
        methods:{

        }
    })

    var vm2=new Vue({
        el:'.content',
        data: {
            signList: []

        },
        methods: {
            getSign() {
                calUtil.init(this.signList);
            },
            sign(){
                var myDate=new Date();
                var today=myDate.getDate();
                
                this.$http.post('${pageContext.request.contextPath}/client/userCenter/sign.action',today,{
	             	'headers':{
							'Content-Type':'application/json;charset=UTF-8'
							}
	             }).then(function(data){
	            	 var str=data.body;
	            	 if(str=="1"){
		             	 alert("今日已签到"); 
	            	 }else if(str=="2"){
	            		 this.signList.push({"signDay":today})
	            		 alert("本月已达标，获得50积分"); 
	            	 }else if(str=="3"){
	            		 this.signList.push({"signDay":today})
	            		 alert("本月已达标，获得50积分"); 
	            	 }else{
	            		 alert("签到成功"); 
	            		 this.signList.push({"signDay":today})
	            	 }
	             });
                
            }
        },
        mounted(){
            if( this.$refs.text1.innerText.length>10){
                this.$refs.text1.innerText=this.$refs.text1.innerText.substring(0,10)+'...'
            }
            if(this.$refs.pay_situation1.innerText=='已支付'){
                this.$refs.pay_situation1.style.color='#98FB98'
            }
            if(this.$refs.pay_situation1.innerText=='待支付'){
                this.$refs.pay_situation1.style.color='#EE0000'
            }
           
            
            var list="${signin.signDate}".substring(3);
            var dayList=list.split("/");
            for(var i=0;i<dayList.length-1;i++){
            	this.signList.push({"signDay": dayList[i]});
            }
        }
    })

</script>


</body>
</html>