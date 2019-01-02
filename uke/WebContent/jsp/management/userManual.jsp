<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户手册</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/management/userManual.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/jquery-ui.min.css"/>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/vue.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/js/vue-resource.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
</head>
<body>
<div class="top">
    <div class="title">
        gjguke  优客站后台管理
    </div>
    <div class="icon">
        <a href="${pageContext.request.contextPath}/management/home.action"><img  src="${pageContext.request.contextPath}/images/icon/首页2.png" title="首页"></a>
        <a href="${pageContext.request.contextPath}/management/orderSearch.action?pageNum=1"><img  src="${pageContext.request.contextPath}/images/icon/公告管理.png" title="订单查看"></a>
        <a href="${pageContext.request.contextPath}/management/storeSetting.action"><img  src="${pageContext.request.contextPath}/images/icon/设置a.png" title="设置中心"></a>
        <a href="${pageContext.request.contextPath}/jsp/management/userManual.jsp"><img  src="${pageContext.request.contextPath}/images/icon/问号.png" title="帮助中心"></a>
        <a href="${pageContext.request.contextPath}/management/exit.action"><img  src="${pageContext.request.contextPath}/images/icon/退出账号.png" title="退出登录"></a>
    </div>
   	<c:if test="${orderUserPayment==0 }">
   	</c:if>
   	<c:if test="${orderUserPayment!=0 }">
    	<span id="prompt">
    	${orderUserPayment }
    	 </span>
   	</c:if>
</div>
<div class="main">
    <div class="mainLeft">
        <div class="user">
            <a href="${pageContext.request.contextPath}/jsp/management/personalMessage.jsp"><img src="${pageContext.request.contextPath}/images/管理员头像/${MANAGER.managerPhoto}"/></a>
            <span>
                <p>欢迎您</p>
                <p>${MANAGER.managerName }</p>
            </span>
        </div>
        <div class="subnav">
            <div class="sortable-accordion">
                <h3>订单管理&emsp;<span class="glyphicon glyphicon-menu-down"></span></h3>
                <div class="inner">
                    <ul class="sortable ">
                    	<a href="${pageContext.request.contextPath}/management/orderSearch.action?pageNum=1"><li class="ui-state-default">订单查询</li></a>
                    	<a href="${pageContext.request.contextPath}/management/historicalOrder.action?pageNum=1"><li class="ui-state-default">历史订单</li></a>
                    </ul>
                </div>
                <h3>商品管理&emsp;<span class="glyphicon glyphicon-menu-down"></span></h3>
                <div class="inner">
                    <ul class="sortable">
                        <a href="${pageContext.request.contextPath}/management/goodsSet1.action?goodsType=1"><li class="ui-state-default">商品设置</li></a>
                        <a href="${pageContext.request.contextPath}/management/toAddGoods.action"><li class="ui-state-default">添加商品</li></a>
                        <a href="${pageContext.request.contextPath}/management/recommend.action"><li class="ui-state-default">店长推荐</li></a>
                        <a href="${pageContext.request.contextPath}/management/integralSetting.action"><li class="ui-state-default">积分设置</li></a>
                    </ul>
                </div>
                <h3>财政统计&emsp;<span class="glyphicon glyphicon-menu-down"></span></h3>
                <div class="inner">
                    <ul class="sortable">
                        <a href="${pageContext.request.contextPath}/management/orderStatistics.action?pageNum=1"><li class="ui-state-default">订单统计</li></a>
                        <a href="${pageContext.request.contextPath}/management/otherProject.action?pageNum=1"><li class="ui-state-default">其他业务</li></a>
                        <a href="${pageContext.request.contextPath}/management/termSettlement.action?pageNum=1"><li class="ui-state-default">定期结算</li></a>
                    </ul>
                </div>
                <h3>用户管理&emsp;<span class="glyphicon glyphicon-menu-down"></span></h3>
                <div class="inner">
                    <ul class="sortable">
                        <a href="${pageContext.request.contextPath}/management/commentManagement.action?pageNum=1"><li class="ui-state-default">评价管理</li></a>
                        <a href="${pageContext.request.contextPath}/management/permissionSetting.action"><li class="ui-state-default">权限设置</li></a>
                    </ul>
                </div>
                <h3>系统管理&emsp;<span class="glyphicon glyphicon-menu-down"></span></h3>
                <div class="inner">
                    <ul class="sortable">
                        <a href="${pageContext.request.contextPath}/jsp/management/addManager.jsp"><li class="ui-state-default">添加账户</li></a>
                        <a href="${pageContext.request.contextPath}/management/getAllManager.action"><li class="ui-state-default">删除账户</li></a>
                        <a href="${pageContext.request.contextPath}/management/storeSetting.action"><li class="ui-state-default">店铺设置</li></a>
                    </ul>
                </div>
                <h3>个人中心&emsp;<span class="glyphicon glyphicon-menu-down"></span></h3>
                <div class="inner">
                    <ul class="sortable">
                        <a href="${pageContext.request.contextPath}/jsp/management/personalMessage.jsp"><li class="ui-state-default">个人信息</li></a>
                        <a href="${pageContext.request.contextPath}/jsp/management/alterPassword.jsp"><li class="ui-state-default">修改密码</li></a>
                        <a href="${pageContext.request.contextPath}/jsp/management/alterManaInfo.jsp"><li class="ui-state-default">修改资料</li></a>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="mainContent">
        <div class="content">
            <div class="contentleft">
                <div id="leftTitle">
                    <img src="${pageContext.request.contextPath}/images/icon/用户手册.png">
                    <p>用户手册</p>
                </div>
                <ul id="myTab" class="nav nav-tabs">
                    <li class="active"><a href="#tab1" data-toggle="tab">订单管理</a></li>
                    <li><a href="#tab2" data-toggle="tab">商品管理</a></li>
                    <li><a href="#tab3" data-toggle="tab">财政统计</a></li>
                    <li><a href="#tab4" data-toggle="tab">用户管理</a></li>
                    <li><a href="#tab5" data-toggle="tab">系统管理</a></li>
                    <li><a href="#tab6" data-toggle="tab">个人中心</a></li>
                    <li><a href="#tab7" data-toggle="tab">其他</a></li>
                </ul>
                <div id="myTabContent" class="tab-content">
                    <div class="tab-pane fade in active" id="tab1">
                        <h4>订单查询</h4>
                        <p>包括接收新订单、已接收订单、正在配送和已收货状态，实时接收新订单并设置每个10秒刷新一次，已接收订单、正在配送和已收货状态均由管理员设置。</p>
                        <h4>历史订单</h4>
                        <p>管理员可以通过订单号、用户名或手机号进行订单查询，并提供按日期搜索订单。</p>
                    </div>
                    <div class="tab-pane fade" id="tab2">
                        <h4>商品设置</h4>
                        <p>管理员可以对商品进行编辑，包括商品的名称、图片、价格等，同时可以设置商品的上架下架情况。</p>
                        <h4>店长推荐</h4>
                        <p>管理员可以添加商品加入推荐列表，也可以移除推荐列表的商品。</p>
                        <h4>积分设置</h4>
                        <p>管理员可以添加积分商品并设置其积分数额，也可以修改积分商品的兑换积分数额。</p>
                    </div>
                    <div class="tab-pane fade" id="tab3">
                        <h4>财政统计</h4>
                        <p>显示每天的订单情况和销售额等，管理员可以按日期搜索相应的订单统计情况。</p>
                        <h4>其他业务</h4>
                        <p>管理员可以根据业务名称、时间和消费数额添加业务到业务表中，同时可以按日期搜索业务。</p>
                        <h4>定期结算</h4>
                        <p>分为按月结算和按年结算，管理员可以对总财政情况进行统计并显示出来，也可以搜索每天的结算情况。</p>
                    </div>
                    <div class="tab-pane fade" id="tab4">
                        <h4>评价管理</h4>
                        <p>提供增删商家反馈和增删评价功能，管理员可以查看用户的评价信息。</p>
                        <h4>权限管理</h4>
                        <p>管理员可以设置顾客的权限，被拉入黑名单的顾客将无法访问网站。</p>
                    </div>
                    <div class="tab-pane fade" id="tab5">
                        <h4>添加账户</h4>
                        <p>管理员可以根据真实姓名，手机号码等信息添加账户。</p>
                        <h4>删除账户</h4>
                        <p>若想删除账户，必须输入改账户的手机号和接收的验证码，否则无法删除。</p>
                        <h4>店铺设置</h4>
                        <p>管理员可以设置店铺的热线电话、店铺地址、营业时间和店铺简介，最终信息将显示在客户端供顾客浏览。</p>
                    </div>
                    <div class="tab-pane fade" id="tab6">
                        <h4>个人信息</h4>
                        <p>管理员可以对自己的个人信息进行修改查看。</p>
                        <h4>修改密码</h4>
                        <p>输入正确的旧密码可以修改密码。</p>
                        <h4>修改信息</h4>
                        <p>提供修改账户的用户名和手机号码的功能。</p>
                    </div>
                    <div class="tab-pane fade" id="tab7">
                        <h4>优客站客户端登录链接</h4>
                        <p>http://gjguke.top/uke/jsp/client/login.jsp</p>
                        <p>Copyright by gjguke.top</p>
                        <p>备案号：粤ICP备18138200号-1</p>
                    </div>
                </div>
        	</div>
        <div class="contentright">
                <div class="rightpart1">
                    <h4>常用工具</h4>
                     <a href="${pageContext.request.contextPath}/management/orderSearch.action?pageNum=1">
                        <img src="${pageContext.request.contextPath}/images/icon/订单查询.png">
                        <p>订单查询</p>
                    </a>
                    <a href="${pageContext.request.contextPath}/management/goodsSet1.action?goodsType=1">
                        <img src="${pageContext.request.contextPath}/images/icon/商品.png">
                        <p>商品设置</p>
                    </a>
                    <a href="${pageContext.request.contextPath}/management/recommend.action">
                        <img src="${pageContext.request.contextPath}/images/icon/推荐a.png">
                        <p>店长推荐</p>
                    </a>
                    <a href="${pageContext.request.contextPath}/management/orderStatistics.action?pageNum=1">
                        <img src="${pageContext.request.contextPath}/images/icon/统计a.png">
                        <p>订单统计</p>
                    </a>
                    <a href="${pageContext.request.contextPath}/management/termSettlement.action?pageNum=1">
                        <img src="${pageContext.request.contextPath}/images/icon/结算.png">
                        <p>定期结算</p>
                    </a>
                    <a href="${pageContext.request.contextPath}/jsp/management/personalMessage.jsp">
                        <img src="${pageContext.request.contextPath}/images/icon/个人信息.png">
                        <p>个人信息</p>
                    </a>
                </div>
                
                <div class="rightpart2">
                    <a href="${pageContext.request.contextPath}/jsp/management/userManual.jsp"><img src="${pageContext.request.contextPath}/images/icon/刷新.png" title="刷新数据"></a>
                    <h4>统计</h4>
                    <div>
                        <p>今日订单数：
                        	<c:choose>
                        		<c:when test="${orderUserSize==0&&allOrderSize==0}">
                        		0
                        		</c:when>
                        		<c:otherwise>
                        		${orderNum.get(0) }
                        		</c:otherwise>
                        	</c:choose>
                        </p>
                        <p>今日交易金额：
                       		<c:choose>
                        		<c:when test="${orderUserSize==0&&allOrderSize==0}">
                        		0
                        		</c:when>
                        		<c:otherwise>
                        		${amount.get(0) }
                        		</c:otherwise>
                        	</c:choose>
                        </p>
                        <p>好评率：<font color="red">
                        	<c:if test="${commentLevel=='0%' }">
                        		今日暂无评论
                        	</c:if>
                        	<c:if test="${commentLevel!='0%' }">
                        		${commentLevel}
                        	</c:if>
                        </font></p>
                    </div>
                    <br>

                    <h4>店铺管理</h4>
                    <div>
                        <p>营业时间：${store.businessHours }</p>
                    </div>
                </div>
            </div>
    </div>

</div>
<div class="foot"></div>

<script>
	function getTimeOrder(){
		$.ajax({
			url:"${pageContext.request.contextPath}/management/getAsyncInfo.action",
			method:"post",
			success:function(data){
				if(data=='1'){
					console.log(data);
				}else{
					window.location.href="${pageContext.request.contextPath}/jsp/management/userManual.jsp";
					console.log(data);
				}
				getTimeOrder();
			}
		});
	}

    $(function () {
    	getTimeOrder();
        $('.sortable-accordion div').show();
        $('.sortable-accordion div').slideToggle('slow');
        $('.sortable-accordion h3').click(function () {
            $(this).next('.inner').slideToggle().siblings('.inner:visible').slideUp();
            $(this).toggleClass('current');
            $(this).siblings('h3').removeClass('current');
        });
    	 $('.sortable').sortable({placeholder: 'ui-sortable-placeholder'}).find('li').append('<span class=\'options\'></span>');
    	 
    	 
    });
			
</script>
</body>
</html>