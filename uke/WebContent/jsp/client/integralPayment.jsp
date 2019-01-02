<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>支付中心-积分兑换</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap-datetimepicker.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/client/integralPayment.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/client/sign.css"/>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.fly.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap-datetimepicker.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap-datetimepicker.zh-CN.js"></script>
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
       <div class="title">
            <img src="${pageContext.request.contextPath}/images/icon/支付中心.jpg">
            <img src="${pageContext.request.contextPath}/images/icon/积分付款1.jpg">
        </div>
        <div class="order">
            <div class="orderTitle">
                <label>宝贝信息</label>
                <label>规格</label>
                <label>所需积分</label>
                <label>数量</label>
                <label>小计</label>
            </div>
            <div class="orderDetail">
            	<form action="${pageContext.request.contextPath}/client/integralPaymentComplete.action?shopId=${shop.shopId}" method="post" class="form form-inline">
                <div class="goods">
                    <label><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsShopDetail.action?id=${shop.goodsId}"><img src="${pageContext.request.contextPath}/images/${goodsType.gtName }/${goodsById.goodsPicture}"></a>${goodsById.goodsName }</label>
                    <label>
                   		<c:if test="${goodsById.goodsMiddlePrice!=''}">
                        	无
                        </c:if>
                        <c:if test="${goodsById.goodsMiddlePrice==''}">
                        	大
                        </c:if>
                    </label>
                    
                    <label class="price">
                    	${shop.integral }积分
                    </label>
                    <label >1</label>
                    <label class="simplePrice">
	                 	${shop.integral }积分
                    </label>
                </div>
                <div class="other">
                 <label>配送时间：<input id="datetimepicker" class="form-control"  name="deliveryTime" size="16" type="text" required></label>
                    <label>配送地址：
                        <select name="address" class="form-control">
                            <option>${userinfo.userAddress }</option>
                            <option>${userinfo.userAddress2 }</option>
                            <option>${userinfo.userAddress3 }</option>
                        </select>
                    </label>
                    <label>配送费:&emsp;&emsp;免配送费</label>
                    <label>订单合计:
                   		<font id="allPrice">
                   			${shop.integral }积分
                    	</font>
                    	</label>
                    <label>收货人：${userinfo.userName }&emsp;${userinfo.userPhone }</label>
                    <c:if test="${USER.userIntegral>=shop.integral }">
	                    <input id="submit" type="submit" class="btn btn-danger" value="提交订单">
                    </c:if>
                    <c:if test="${USER.userIntegral<shop.integral }">
	                    <input id="submit" type="submit" class="btn btn-danger" value="积分不足" disabled>
                    </c:if>
                </div>
				</form>
            </div>

        </div>
    </div>
</div>
<div class="footer">
    <p>copyright by gjguke.top</p>
    <p style="margin-top:-30px;">粤ICP备18138200号-1</p>
</div>
<script>
	function p(s) {
	    return s < 10 ? '0' + s: s;
	}
	$(function () {
		var storeTime="${store.businessHours}"
		var storeStartTime=storeTime.substring(0,2)
		var storeEndTime=storeTime.substring(6,8)
        var myDate=new Date();
        //获取当前年
        var year=myDate.getFullYear();
        //获取当前月
        var month=myDate.getMonth()+1;
        //获取当前日
        var date=myDate.getDate();
        var h=myDate.getHours();       //获取当前小时数(0-23)
        var m=myDate.getMinutes();     //获取当前分钟数(0-59)
        var s=myDate.getSeconds();
        var endhour=h+2;
        var starthour=h+1;
        if(endhour>storeEndTime){
        	endhour=storeEndTime;
        }
        if(starthour<storeStartTime){
        	starthour=storeStartTime;
        }
        var now=year+'-'+p(month)+"-"+p(date)+" "+p(starthour)+':'+p(m)+":"+p(s);
        var end=year+'-'+p(month)+"-"+p(date)+" "+p(endhour)+':'+p(m)+":"+p(s);
        $('#datetimepicker').datetimepicker('setStartDate',now);
        $('#datetimepicker').datetimepicker('setEndDate', end);
	});
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
            	alert("签到成功")
                var myDate=new Date();
                var today=myDate.getDate();
                this.signList.push({"signDay":today})
                
                this.$http.post('${pageContext.request.contextPath}/client/userCenter/sign.action?userId=1',today,{
	             	'headers':{
							'Content-Type':'application/json;charset=UTF-8'
							}
	             }).then(function(data){
	             	 alert(data.body); 
	             });

            }
        },
        mounted(){
            var list="${signin.signDate}";
            var dayList=list.split("/");
            for(var i=0;i<dayList.length-1;i++){
            	this.signList.push({"signDay": dayList[i]});
            }
        }
    })

</script>


</body>
</html>