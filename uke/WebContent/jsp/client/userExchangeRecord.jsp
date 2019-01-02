<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的积分</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/client/userExchangeRecord.css"/>
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
                        <li><a href="${pageContext.request.contextPath}/client/userCenter/userComment.action?pageNum=1">我的评价</a> </li>
                        <li><a href="${pageContext.request.contextPath}/client/userCenter/userIntegral.action" style="color:red;">我的积分</a> </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="rightsidebar">
        	<div class="rightTop">
                <ul>
                    <li>
                        <span class="model"><img src="${pageContext.request.contextPath}/images/icon/积分1.png" /></span>
                        <span class="model"><h2>${USER.userIntegral }</h2>
                        <p>我的积分</p></span>
                    </li>
                    <li>
                        <span class="model"><img src="${pageContext.request.contextPath}/images/icon/规则.png" /></span>
                        <span class="model">
                            <h3>兑换规则</h3>
                            <button id="lookButton" data-toggle="modal" data-target="#look">点击查看</button>
                        </span>
                        <div class="modal fade" role="dialog" id="look">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4>
                                            兑换规则
                                            <button class="close" data-dismiss="modal">&times;</button>
                                        </h4>
                                    </div>
                                    <div class="modal-body">
                                        <div>
                                            <span style="width:10px;"></span>什么是积分？
                                            <p>优客站积分是针对优客站注册用户的奖励体系，当您通过账户密码登录优客站后，
                                                即可开启您的优客站之旅</p>
                                        </div>
                                        <div>
                                            <span></span>如何获取积分？
                                            <p>每日签到、购买商品都可以获取积分，获得的积分可以在积分商城中兑换商品，具体获取
                                                方式如下：</p>
                                        </div>
                                        <div>
                                            <span></span>每日签到
                                            <p>连续签到20天可获得100积分</p>
                                        </div>
                                        <div>
                                            <span></span>购买商品
                                            <p>在本店购买商品可获得额定积分</p>
                                            <p>消费满15元，获得10积分奖励</p>
                                            <p>消费满30元，获得20积分奖励</p>
                                            <p>消费满50元，获得30积分奖励</p>
                                            <p>消费满80元，获得40积分奖励</p>
                                            <p>消费满100元或100元以上，获得50积分奖励</p>
                                        </div>
                                        <div>
                                        	<h4 style="color:red;">注意：积分商品一个订单只限兑换一个</h4>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button class="btn btn-primary" data-dismiss="modal">关闭</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </li>
                    <li>
                        <span class="model"><img src="${pageContext.request.contextPath}/images/icon/购物.png" /></span>
                        <span class="model"><h3>兑换记录</h3>
                        <a href="${pageContext.request.contextPath}/client/userCenter/userExchangeRecord.action?pageNum=1">点击查看</a></span>
                    </li>
                </ul>
            </div>
            <div class="rightContent">
                <div><img src="${pageContext.request.contextPath}/images/icon/兑换记录.png">我的兑换记录</div>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>时间</th>
                        <th>订单号</th>
                        <th>商品名</th>
                        <th>消费积分</th>
                        <th>订单状态</th>
                    </tr>
                    </thead>
                    <c:if test="${size==0 }">
                  	  <img id="notFound" src="${pageContext.request.contextPath}/images/icon/找不到.png" />
                    </c:if>
                    <c:if test="${size>0 }">
	                    <tbody>
	                    <c:forEach items="${integralOrder }" var="i" varStatus="status" begin="0" end="7">
	                    <tr>
	                        <td>${i.orderTime }</td>
	                        <td>${i.integralOrderId } </td>
	                        <td>${goods.get(status.index).goodsName }</td>
	                        <td>${shopList.get(status.index).integral }</td>
	                        <td>
	                        	<c:choose>
		                        	<c:when test="${i.orderStatus==1 }">
		                        		<font style="color:red;">待支付</font>
		                        	</c:when>
		                        	<c:when test="${i.orderStatus==2 }">
		                        		<font style="color:green;">已支付</font>
		                        	</c:when>
		                        	<c:when test="${i.orderStatus==3 }">
		                        		<font style="color:#CD950C;">正在配送</font>
		                        	</c:when>
		                        	<c:when test="${i.orderStatus==4 }">
		                        		<font style="color:#ADFF2F;">已收货</font>
		                        	</c:when>
		                        	<c:when test="${i.orderStatus==5 }">
		                        		<a href="${pageContext.request.contextPath}/client/integralOrderComment.action?integralOrderId=${i.integralOrderId}"><font style="color:red;">待评价</font></a>
		                        	</c:when>
		                        	<c:when test="${i.orderStatus==6 }">
		                        		<font style="color:green;">已评价</font>
		                        	</c:when>
		                        	<c:when test="${i.orderStatus==7 }">
		                        		<font style="color:#9AFF9A;">已完成</font>
		                        	</c:when>
		                        	<c:when test="${i.orderStatus==8 }">
		                         		<font style="color:#EEAD0E;">商家已接收</font>
		                         	</c:when>
		                         	<c:when test="${i.orderStatus==9 }">
	                         			<font style="color:#ABABAB;">订单已失效（或被商家拒接）</font>
	                         		</c:when>
	                        	</c:choose>
	                        </td>
	                    </tr>
	                    </c:forEach>
	                    </tbody>
                    </c:if>
                </table>
            </div>
            <c:if test="${size>7 }">
	            <ul class="pagination">
	                <p>共${size }条数据，${pageSize }页</p>
	                <li><a href="${pageContext.request.contextPath}/client/userCenter/userExchangeRecord.action?pageNum=1">首页</a> </li>
	                <c:if test="${pageBean.pageNum==1 }">
		                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
		                	<li><a href="${pageContext.request.contextPath}/client/userCenter/userExchangeRecord.action?pageNum=${i}">${i }</a> </li>
		                </c:forEach>
		                <li><a href="${pageContext.request.contextPath}/client/userCenter/userExchangeRecord.action?pageNum=${pageBean.pageNum+1}">下一页</a> </li>
	                </c:if>
	                 <c:if test="${pageBean.pageNum>1&&pageBean.pageNum<pageBean.totalPage }">
	                 	<li><a href="${pageContext.request.contextPath}/client/userCenter/userExchangeRecord.action?pageNum=${pageBean.pageNum-1}">上一页</a> </li>
		                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
		                	<li><a href="${pageContext.request.contextPath}/client/userCenter/userExchangeRecord.action?pageNum=${i}">${i }</a> </li>
		                </c:forEach>
		                <li><a href="${pageContext.request.contextPath}/client/userCenter/userExchangeRecord.action?pageNum=${pageBean.pageNum+1}">下一页</a> </li>
	                </c:if>
	                 <c:if test="${pageBean.pageNum==pageBean.totalPage}">
	                 	<li><a href="${pageContext.request.contextPath}/client/userCenter/userExchangeRecord.action?pageNum=${pageBean.pageNum-1}">上一页</a> </li>
		                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
		                	<li><a href="${pageContext.request.contextPath}/client/userCenter/userExchangeRecord.action?pageNum=${i}">${i }</a> </li>
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