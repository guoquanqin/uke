<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>积分订单</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/client/userAllIntegralOrder.css"/>
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
                        <span><a href="${pageContext.request.contextPath}/client/userCenter/userSetting.action"><img src="${pageContext.request.contextPath}/images/icon	/用户a.png">用户中心</a></span>
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
                        <li><a href="${pageContext.request.contextPath}/client/userCenter/userAllIntegralOrder.action?pageNum=1" style="color:red;">积分订单</a></li>
                        <li><a href="${pageContext.request.contextPath}/client/userCenter/userBasket.action">我的购物车</a> </li>
                        <li><a href="${pageContext.request.contextPath}/client/userCenter/userCollect.action">我的收藏夹</a> </li>
                        <li><a href="${pageContext.request.contextPath}/client/userCenter/userComment.action?pageNum=1">我的评价</a> </li>
                        <li><a href="${pageContext.request.contextPath}/client/userCenter/userIntegral.action">我的积分</a> </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="rightsidebar">
            <div class="orderTop">
                <button id="allOrder" style="border-bottom: 2px solid red;color:red">所有订单</button>
                <button id="pay">待付款</button>
                <button id="getGoods">待收货</button>
                <button id="comment">待评价</button>
                <form role="form"  class="input-group" action="${pageContext.request.contextPath}/client/userCenter/userIntegralOrderBySearch.action?pageNum=1" method="post">
                    <input type="text" class="form-control" name="search" placeholder="按商品关键字或订单号搜索订单"/>
                    <input class="btn btn-info" type="submit" id="submit" value="搜索"/>
                </form>
            </div>
            <div class="orderTitle">
                <button disabled="disabled">宝贝</button>
                <button disabled="disabled">时间</button>
                <button disabled="disabled">消费积分</button>
                <button disabled="disabled">订单状态</button>
                <button disabled="disabled">交易操作</button>
            </div>
            
            <c:if test="${size==0 }">
            	<img id="notFound" src="${pageContext.request.contextPath}/images/icon/找不到.png" />
            </c:if>
            
            <c:if test="${size>0 }">
            <c:forEach items="${integralOrder}" var="i" varStatus="status">
            <input type="hidden" value="${i.integralOrderId }" class="integralOrderId" />
            <div class="order">
                <div class="orderHead">
                    	订单号：${i.integralOrderId }
                </div>
                <div class="orderDetail">
                    <div class="orderGoods">
                        <a href="${pageContext.request.contextPath}/client/simpleGoods/goodsShopDetail.action?id=${shopList.get(status.index).goodsId}" ><img src="${pageContext.request.contextPath}/images/${goodsType.get(status.index).gtName }/${goods.get(status.index).goodsPicture}"></a>&emsp;&emsp;${goods.get(status.index).goodsName }
                    </div>
                    <div class="orderPrice">
                        ${i.orderTime }
                    </div>
                    <div class="orderNum">
                   		 ${shopList.get(status.index).integral }
                    </div>
                    <div class="orderStatus">
                         <c:choose>
                         	<c:when test="${i.orderStatus==1 }">
                         		<font style="color:red;">待支付</font>
                         		<a href="#" style="color:red;">去支付</a>
                         	</c:when>
                         	<c:when test="${i.orderStatus==2 }">
                         		<font style="color:green;">已支付</font>
                         	</c:when>
                         	<c:when test="${i.orderStatus==3 }">
                         		<font style="color:#CD950C;">正在配送</font>
                         		<a href="#" style="color:red;">确认收货</a>
                         	</c:when>
                         	<c:when test="${i.orderStatus==4 }">
                         		<font style="color:#ADFF2F;">已收货</font>
                         	</c:when>
                         	<c:when test="${i.orderStatus==5 }">
                         		<font style="color:red;">待评价</font>
                         		<a href="${pageContext.request.contextPath}/client/integralOrderComment.action?integralOrderId=${i.integralOrderId}" style="color:red;">去评价</a>
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
                    </div>
                    <div class="orderOperator">
                   			<c:if test="${i.orderStatus==2||i.orderStatus==3 }">
	                    	</c:if>
	                    	<c:if test="${i.orderStatus!=2&&i.orderStatus!=3 }">
	                    		<a class="delete">删除订单</a>	
	                    	</c:if>
                    </div>
                </div>

            </div>
            </c:forEach>
			</c:if>
			<c:if test="${size>7 }">
	            <ul class="pagination">
	                <p>共${size }条数据，${pageSize }页</p>
	                <li><a href="${pageContext.request.contextPath}/client/userCenter/userAllIntegralOrder.action?pageNum=1">首页</a> </li>
	                <c:if test="${pageBean.pageNum==1 }">
		                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
		                	<li><a href="${pageContext.request.contextPath}/client/userCenter/userAllIntegralOrder.action?pageNum=${i}">${i }</a> </li>
		                </c:forEach>
		                <li><a href="${pageContext.request.contextPath}/client/userCenter/userAllIntegralOrder.action?pageNum=${pageBean.pageNum+1}">下一页</a> </li>
	                </c:if>
	                 <c:if test="${pageBean.pageNum>1&&pageBean.pageNum<pageBean.totalPage }">
	                 	<li><a href="${pageContext.request.contextPath}/client/userCenter/userAllIntegralOrder.action?pageNum=${pageBean.pageNum-1}">上一页</a> </li>
		                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
		                	<li><a href="${pageContext.request.contextPath}/client/userCenter/userAllIntegralOrder.action?pageNum=${i}">${i }</a> </li>
		                </c:forEach>
		                <li><a href="${pageContext.request.contextPath}/client/userCenter/userAllIntegralOrder.action?pageNum=${pageBean.pageNum+1}">下一页</a> </li>
	                </c:if>
	                 <c:if test="${pageBean.pageNum==pageBean.totalPage}">
	                 	<li><a href="${pageContext.request.contextPath}/client/userCenter/userAllIntegralOrder.action?pageNum=${pageBean.pageNum-1}">上一页</a> </li>
		                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
		                	<li><a href="${pageContext.request.contextPath}/client/userCenter/userAllIntegralOrder.action?pageNum=${i}">${i }</a> </li>
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
	$(function(){
		$("#allOrder").click(function(){
			window.location.href="${pageContext.request.contextPath}/client/userCenter/userAllIntegralOrder.action?pageNum=1";
		})
		$("#pay").click(function(){
			window.location.href="${pageContext.request.contextPath}/client/userCenter/userIntegralOrderWaitForPayment.action?pageNum=1";
		})
		$("#getGoods").click(function(){
			window.location.href="${pageContext.request.contextPath}/client/userCenter/userIntegralOrderWaitForGet.action?pageNum=1";
		})
		$("#comment").click(function(){
			window.location.href="${pageContext.request.contextPath}/client/userCenter/userIntegralOrderWaitForComment.action?pageNum=1";
		})
		
		$(".delete").click(function(){
			var con=confirm("确定删除此订单吗?");
			if(con==true){
				var index=$(".delete").index(this);
				var id=$(".integralOrderId").eq(index).val();
				alert("删除成功")
				window.location.href="${pageContext.request.contextPath}/client/deleteIntegralOrder.action?integralOrderId="+id;
			}else{
				alert("取消删除");
			}
		});
	})
</script>
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