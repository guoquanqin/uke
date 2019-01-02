<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的评论</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/client/userIntegralComment.css"/>
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
                    <a href="${pageContext.request.contextPath}/client/userCenter/userSetting.action"><img id="userHead" src="${pageContext.request.contextPath}/images/用户头像/${USER.userPhoto}"></a>
                    <span id="signbtn" data-toggle="modal" data-target="#signin" @click="getSign"><p>签到</p></span>
                    <div class="modal fade" role="dialog" id="signin">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h4>
                                        签到中心
                                        <button class="btn btn-info" @click="sign">签到</button>
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
                        <li><a href="${pageContext.request.contextPath}/client/userCenter/userComment.action?pageNum=1" style="color:red;">我的评价</a> </li>
                        <li><a href="${pageContext.request.contextPath}/client/userCenter/userIntegral.action">我的积分</a> </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="rightsidebar">
        	 <div class="rightTop">
                 <a href="${pageContext.request.contextPath}/client/userCenter/userComment.action?pageNum=1" ><div id="part1">商品评价</div></a>
                <a href="${pageContext.request.contextPath}/client/userCenter/userIntegralComment.action?pageNum=1" ><div id="part2">积分评价</div></a>
            </div>
            <div class="commentTitle">
                <ul>
                    <li>宝贝信息</li>
                    <li>我的评价</li>
                    <li>商家反馈</li>
                </ul>
            </div>
            <c:if test="${size==0 }">
            	<img id="notFound" src="${pageContext.request.contextPath}/images/icon/找不到.png" />
            </c:if>
            <c:if test="${size>0 }">
            <ul>
            	<c:forEach items="${integralOrderListForPage }" var="i" varStatus="status">
                <li class="comment">
                    <div class="commentTop"><fmt:formatDate  pattern="yyyy-MM-dd HH:mm:ss" value="${integralComment.get(status.index).commentTime }"/> &emsp;&emsp;订单号：${i.integralOrderId }</div>
                    <div class="commentContent">
                        <div class="leftContent">
                            <span><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsShopDetail.action?id=${goodsList.get(status.index).goodsId}"><img src="${pageContext.request.contextPath}/images/${goodsTypeList.get(status.index).gtName }/${goodsList.get(status.index).goodsPicture}"></a>&emsp;&emsp;${goodsList.get(status.index).goodsName }</span>
                        </div>
                        <div class="centerContent">
                           		${integralComment.get(status.index).commentContents }
                            <span>
	                            <c:forTokens items="${integralComment.get(status.index).commentPhoto }" var="i" delims="/">
	                            <img src="${pageContext.request.contextPath}/images/评价图片/${i}">
	                            </c:forTokens>
                            </span>
                        </div>
                        <div class="rightContent">
                        ${fbCommentListForPage.get(status.index).integralFeedbackComment.feedbackContent }
                        </div>
                    </div>
                </li>
                </c:forEach>
            </ul>
            </c:if>
            <c:if test="${size>7 }">
	            <ul class="pagination">
	                <p>共${size }条数据，${pageSize }页</p>
	                <li><a href="${pageContext.request.contextPath}/client/userCenter/userIntegralComment.action?pageNum=1">首页</a> </li>
	                <c:if test="${pageBean.pageNum==1 }">
		                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
		                	<li><a href="${pageContext.request.contextPath}/client/userCenter/userIntegralComment.action?pageNum=${i}">${i }</a> </li>
		                </c:forEach>
		                <li><a href="${pageContext.request.contextPath}/client/userCenter/userIntegralComment.action?pageNum=${pageBean.pageNum+1}">下一页</a> </li>
	                </c:if>
	                 <c:if test="${pageBean.pageNum>1&&pageBean.pageNum<pageBean.totalPage }">
	                 	<li><a href="${pageContext.request.contextPath}/client/userCenter/userIntegralComment.action?pageNum=${pageBean.pageNum-1}">上一页</a> </li>
		                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
		                	<li><a href="${pageContext.request.contextPath}/client/userCenter/userIntegralComment.action?pageNum=${i}">${i }</a> </li>
		                </c:forEach>
		                <li><a href="${pageContext.request.contextPath}/client/userCenter/userIntegralComment.action?pageNum=${pageBean.pageNum+1}">下一页</a> </li>
	                </c:if>
	                 <c:if test="${pageBean.pageNum==pageBean.totalPage}">
	                 	<li><a href="${pageContext.request.contextPath}/client/userCenter/userIntegralComment.action?pageNum=${pageBean.pageNum-1}">上一页</a> </li>
		                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
		                	<li><a href="${pageContext.request.contextPath}/client/userCenter/userIntegralComment.action?pageNum=${i}">${i }</a> </li>
		                </c:forEach>
	                </c:if>
	            </ul>
            </c:if>
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