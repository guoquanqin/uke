<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单详情-已收货</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/management/orderSearch.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/jquery-ui.min.css"/>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/vue.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/js/vue-resource.js"></script>
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
                    <img src="${pageContext.request.contextPath}/images/icon/订单查询.png">
                    <p>订单查询</p>
                </div>
                <div class="order">
                    <div class="orderTop">
                        <a href="${pageContext.request.contextPath}/management/orderSearch.action?pageNum=1">新订单</a>
                        <a href="${pageContext.request.contextPath}/management/orderSearchForRecevied.action?pageNum=1" >已接收订单</a>
                        <a href="${pageContext.request.contextPath}/management/orderSearchForDelivery.action?pageNum=1">正在配送</a>
                        <a style="border-bottom:1px solid green;color:green;" href="${pageContext.request.contextPath}/management/orderSearchForFinish.action?pageNum=1">已收货</a>
                        <span>${today }</span>
                        <c:if test="${size!=0 }">
                        	<button id="comment" class="btn btn-info">查看评论</button>
                        </c:if>
                    </div>
                    <!-- 如果有订单 -->
                    <c:if test="${size!=0 }">
                    <div id="table">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>订单号</th>
                            <th width="70px">下单时间</th>
                            <th width="70px">送达时间</th>
                            <th width="70px">客户名</th>
                            <th width="75px">联系电话</th>
                            <th width="170px">地址</th>
                            <th width="150px">商品</th>
                            <th width="50px">数量</th>
                            <th width="50px">小计</th>
                            <th width="100px">备注</th>
                            <th width="30px">总价</th>
                        </tr>
                        </thead>
                        <tbody>
                       	<c:forEach items="${orderUserListForPage }" var="i" varStatus="status">
                       		<!-- 证明是积分订单 -->
                           	<c:if test="${i.isExit=='2' }">
                           		<tr class="alert-info">
                           	</c:if>
                           	<c:if test="${i.isExit!='2' }">
                           		<tr>
                           	</c:if>
	                        	<input class="orderUserId" type="hidden" value="${i.orderUserId }"/>
	                            <td>
	                            <c:if test="${i.isExit=='2' }">
	                          		${i.orderUserId }(积分订单)
	                          	</c:if>
	                          	<c:if test="${i.isExit!='2' }">
	                          		${i.orderUserId }
	                          	</c:if>
	                           
	                            </td>
	                            <td><fmt:formatDate value="${i.orderTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                            <td><fmt:formatDate value="${i.deliveryTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                            <td>${userInfoListForPage.get(status.index).userName }
	                            <c:if test="${userInfoListForPage.get(status.index).userSex=='1' }">
	                            	(先生)
	                            </c:if>
	                            <c:if test="${userInfoListForPage.get(status.index).userSex=='0' }">
	                            	(女士)
	                            </c:if>
	                            </td>
	                            <td>${userInfoListForPage.get(status.index).userPhone }</td>
	                            <td>
	                            	<c:if test="${i.isExit!='2' }">
	                            		${i.address }
	                            	</c:if>
	                            	<c:if test="${i.isExit=='2' }">
	                            		<c:forTokens items="${i.address }" delims="/" begin="0" end="0" var="o">
	                            			${o }
	                            		</c:forTokens>
		                          	</c:if>
	                            </td>
	                            <td>
	                            	<c:if test="${i.isExit=='2' }">
	                            		<c:forTokens items="${i.address }" delims="/" begin="1" var="o">
	                            			<span>${o }</span>
	                            		</c:forTokens>
		                          	</c:if>
		                          	<c:if test="${i.isExit!='2' }">
		                            	<c:forEach items="${i.orderGoodsList }" var="j">
		                               		<span>${j.goods.goodsName }</span>
	                          	  		</c:forEach>
		                          	</c:if>
	                            	
	                            	
	                            </td>
	                            <td>
                                	<c:if test="${i.isExit=='2' }">1</c:if>
		                          	<c:if test="${i.isExit!='2' }">
		                                <c:forEach items="${i.orderGoodsList }" var="k">
			                          		<span>${k.goodsCount }</span>
		                                </c:forEach>
		                          	</c:if>
	                               		
	                            </td>
	                            <td>
                            		<c:if test="${i.isExit=='2' }">
		                          		${i.orderPrice }(积分)
		                          	</c:if>
		                          	<c:if test="${i.isExit!='2' }">
		                            	<c:forEach items="${i.orderGoodsList }" var="q">
		                               		<span>${q.goodsTotalPrice }</span>
		                                </c:forEach>
		                          	</c:if>
	                            </td>
	                            <td>
                            		<c:if test="${i.isExit=='2' }">
		                          		无
		                          	</c:if>
		                          	<c:if test="${i.isExit!='2' }">
		                            	<c:forEach items="${i.orderGoodsList }" var="k">
		                               		<span>${k.goodsRemarks }</span>
		                                </c:forEach>
		                          	</c:if>
	                            </td>
	                            <td>
	                            	<c:if test="${i.isExit=='2' }">
		                          		${i.orderPrice }(积分)
		                          	</c:if>
		                          	<c:if test="${i.isExit!='2' }">
		                            	${i.orderPrice }
			                        </c:if>
	                            </td>
	                            
	                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    </div>
                    </c:if>
                    
                    <!-- 如果没有订单 -->
                    <c:if test="${size==0 }">
	                    <img src="${pageContext.request.contextPath}/images/icon/暂无订单.png"/>
	                    <p>暂无订单</p>
                    </c:if>
                    
                    
                   <c:if test="${size>6 }">
			            <ul class="pagination">
			                <p>共${size }条数据，${pageSize }页，当前第${pageBean.pageNum}页</p>
			                <li><a href="${pageContext.request.contextPath}/management/orderSearch.action?pageNum=1">首页</a> </li>
			                <c:if test="${pageBean.pageNum==1 }">
				                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
				                	<li><a href="${pageContext.request.contextPath}/management/orderSearch.action?pageNum=${i}">${i }</a> </li>
				                </c:forEach>
				                <li><a href="${pageContext.request.contextPath}/management/orderSearch.action?pageNum=${pageBean.pageNum+1}">下一页</a> </li>
			                </c:if>
			                 <c:if test="${pageBean.pageNum>1&&pageBean.pageNum<pageBean.totalPage }">
			                 	<li><a href="${pageContext.request.contextPath}/management/orderSearch.action?pageNum=${pageBean.pageNum-1}">上一页</a> </li>
				                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
				                	<li><a href="${pageContext.request.contextPath}/management/orderSearch.action?pageNum=${i}">${i }</a> </li>
				                </c:forEach>
				                <li><a href="${pageContext.request.contextPath}/management/orderSearch.action?pageNum=${pageBean.pageNum+1}">下一页</a> </li>
			                </c:if>
			                 <c:if test="${pageBean.pageNum==pageBean.totalPage}">
			                 	<li><a href="${pageContext.request.contextPath}/management/orderSearch.action?pageNum=${pageBean.pageNum-1}">上一页</a> </li>
				                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
				                	<li><a href="${pageContext.request.contextPath}/management/orderSearch.action?pageNum=${i}">${i }</a> </li>
				                </c:forEach>
			                </c:if>
			            </ul>
		            </c:if>
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
					window.location.href="${pageContext.request.contextPath}/management/orderSearchForFinish.action?pageNum=1";
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
    	 

        $("#comment").click(function (e) {
			  //window.location.href="${pageContext.request.contextPath}/management/deliveryAllOrder.action";
         });
        
    	 
        
    });
			
</script>
</body>
</html>