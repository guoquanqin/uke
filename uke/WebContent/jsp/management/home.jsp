<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>优客站后台管理主页</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/management/home.css"/>
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
                <div class="part1">
                    <div class="lefttop">
                         <span><img src="${pageContext.request.contextPath}/images/icon/订单查询.png">订单查询</span>
                        <span><a href="${pageContext.request.contextPath}/management/orderSearch.action?pageNum=1">查看更多订单 >></a></span>
                    </div>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th width="300px">订单号</th>
                            <th>下单时间</th>
                            <th>送达时间</th>
                            <th width="200px">客户名</th>
                            <th>联系电话</th>
                            <th>地址</th>
                            <th>商品</th>
                            <th>总价(含配送费)</th>
                        </tr>
                        </thead>
                        <tbody>
                        <!-- orderUserSize不为0  的情况 -->
                        <c:if test="${orderUserSize!=0}">
                        	<c:if test="${orderUserSize>=3 }">
		                        <c:forEach items="${orderUser }" var="i" varStatus="status" begin="0" end="2">
			                        <tr class="danger">
			                            <td width="9%">${i.orderUserId }</td>
			                            <td width="12%"><fmt:formatDate value="${i.orderTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			                            <td width="9%">
			                            	<c:if test="${i.orderStatus==2 }">
			                            		未确认
			                            	</c:if>
			                            	<c:if test="${i.orderStatus==3 }">
			                            		正在配送
			                            	</c:if>
			                            </td>
			                            <td width="9%">${userInfoList1.get(status.index).userName }
			                            	<c:if test="${userInfoList1.get(status.index).userSex==1 }">
		                            		(先生)
			                            	</c:if>
			                            	<c:if test="${userInfoList1.get(status.index).userSex==0 }">
			                            		(女士)
			                            	</c:if>
			                            </td>
			                            <td width="12%">${userInfoList1.get(status.index).userPhone }</td>
			                            <td class="address1" width="15%">${ userInfoList1.get(status.index).userAddress}</td>
			                            <td class="goods1" width="16%">${goodsNameList1.get(status.index) }</td>
			                            <td width="13%">${i.orderPrice }</td>
			                        </tr>
		                        </c:forEach>
	                        </c:if>
	                        
	                        <%-- <c:if test="${orderUserSize==3 }">
		                        <c:forEach items="${orderUser }" var="i" varStatus="status" begin="0" end="2">
			                        <tr class="danger">
			                            <td width="9%">${i.orderUserId }</td>
			                            <td width="12%"><fmt:formatDate value="${i.orderTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			                            <td width="9%">
			                            	<c:if test="${i.orderStatus==2 }">
			                            		未确认
			                            	</c:if>
			                            	<c:if test="${i.orderStatus==3 }">
			                            		正在配送
			                            	</c:if>
			                            </td>
			                            <td width="9%">${userInfoList1.get(status.index).userName }
			                            	<c:if test="${userInfoList1.get(status.index).userSex==1 }">
		                            		(先生)
			                            	</c:if>
			                            	<c:if test="${userInfoList1.get(status.index).userSex==0 }">
			                            		(女士)
			                            	</c:if>
			                            </td>
			                            <td width="12%">${userInfoList1.get(status.index).userPhone }</td>
			                            <td class="address1" width="15%">${ userInfoList1.get(status.index).userAddress}</td>
			                            <td class="goods1" width="16%">${goodsNameList1.get(status.index) }</td>
			                            <td width="13%">${i.orderPrice }</td>
			                        </tr>
		                        </c:forEach>
	                        </c:if> --%>
	                        
	                        <c:if test="${orderUserSize<3&&orderUserSize>0&&allOrderSize!=0 }">
		                        <c:forEach items="${orderUser }" var="i" varStatus="status" begin="0" end="${orderUserSize }">
			                        <tr class="danger">
			                            <td width="9%">${i.orderUserId }</td>
			                            <td width="12%"><fmt:formatDate value="${i.orderTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			                            <td width="9%">
			                            	<c:if test="${i.orderStatus==2 }">
			                            		未确认
			                            	</c:if>
			                            	<c:if test="${i.orderStatus==3 }">
			                            		正在配送
			                            	</c:if>
			                            </td>
			                            <td width="9%">${userInfoList1.get(status.index).userName }
			                            	<c:if test="${userInfoList1.get(status.index).userSex==1 }">
		                            		(先生)
			                            	</c:if>
			                            	<c:if test="${userInfoList1.get(status.index).userSex==0 }">
			                            		(女士)
			                            	</c:if>
			                            </td>
			                            <td width="12%">${userInfoList1.get(status.index).userPhone }</td>
			                            <td class="address1" width="15%">${ userInfoList1.get(status.index).userAddress}</td>
			                            <td class="goods1" width="16%">${goodsNameList1.get(status.index) }</td>
			                            <td width="13%">${i.orderPrice }</td>
			                        </tr>
		                        </c:forEach>
		                         <c:forEach items="${allOtherOrderUserInToday }" var="i" varStatus="status" begin="${0 }" end="${2-orderUserSize }">
			                        <tr>
			                            <td width="9%">${i.orderUserId }</td>
			                            <td width="12%"><fmt:formatDate value="${i.orderTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			                            <td width="9%">
			                            	<c:if test="${i.orderStatus==4 }">
		                            		已收货
		                            	</c:if>
		                            	<c:if test="${i.orderStatus==5 }">
		                            		待评价
		                            	</c:if>
		                            	<c:if test="${i.orderStatus==7 }">
		                            		已完成
		                            	</c:if>
			                            </td>
			                            <td width="9%">${userInfoList2.get(status.index).userName }
			                            	<c:if test="${userInfoList2.get(status.index).userSex==1 }">
		                            		(先生)
			                            	</c:if>
			                            	<c:if test="${userInfoList2.get(status.index).userSex==0 }">
			                            		(女士)
			                            	</c:if>
			                            </td>
			                            <td width="12%">${userInfoList2.get(status.index).userPhone }</td>
			                            <td class="address1" width="15%">${ userInfoList2.get(status.index).userAddress}</td>
			                            <td class="goods1" width="16%">${goodsNameList2.get(status.index) }</td>
			                            <td width="13%">${i.orderPrice }</td>
			                        </tr>
		                        </c:forEach>
	                        </c:if>
                        </c:if>
                        
                        <!-- allOrderSize不为0并且orderUserSize为0  的情况 -->
                        <c:if test="${orderUserSize==0&&allOrderSize!=0}">
                        	<c:forEach items="${allOtherOrderUserInToday }" var="i" varStatus="status" begin="0" end="${allOtherOrderUserInTodaySize }">
		                        <tr>
		                            <td width="9%">${i.orderUserId }</td>
		                            <td width="12%"><fmt:formatDate value="${i.orderTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		                            <td width="9%">
		                            	<c:if test="${i.orderStatus==4 }">
		                            		已收货
		                            	</c:if>
		                            	<c:if test="${i.orderStatus==5 }">
		                            		待评价
		                            	</c:if>
		                            	<c:if test="${i.orderStatus==7 }">
		                            		已完成
		                            	</c:if>
		                            </td>
		                            <td width="9%">${userInfoList2.get(status.index).userName }
		                            	<c:if test="${userInfoList2.get(status.index).userSex==1 }">
		                            		(先生)
		                            	</c:if>
		                            	<c:if test="${userInfoList2.get(status.index).userSex==0 }">
		                            		(女士)
		                            	</c:if>
		                            </td>
		                            <td width="12%">${userInfoList2.get(status.index).userPhone }</td>
		                            <td class="address1" width="15%">${ userInfoList2.get(status.index).userAddress}</td>
		                            <td class="goods1" width="16%">${goodsNameList2.get(status.index) }</td>
		                            <td width="13%">${i.orderPrice }</td>
		                        </tr>
	                        </c:forEach>
	                     </c:if>
	                      
	                      
	                       <c:if test="${orderUserSize==0&&allOrderSize==0}">
	                       		<td width="9%">暂无订单</td>
	                       		<td width="12%"></td>
	                       		<td width="9%"></td>
	                       		<td width="9%"></td>
	                       		<td width="12%"></td>
	                       		<td width="15%"></td>
	                       		<td width="16%"></td>
	                       		<td width="13%"></td>
	                       </c:if>
	                      
                        </tbody>
                    </table>
                    </div>
                <div class="part2">
                    <div class="lefttop">
                        <span><img src="${pageContext.request.contextPath}/images/icon/统计.png">订单统计</span>
                        <span><a href="${pageContext.request.contextPath}/management/orderStatistics.action?pageNum=1">查看更多信息 >></a></span>
                    </div>
                    <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>日期</th>
                        <th>总订单数</th>
                        <th>成功订单</th>
                        <th>失败订单</th>
                        <th>交易数额</th>
                    </tr>
                    </thead>
                    <tbody>
	                    <c:forEach items="${dateList }" var="i" varStatus="status" begin="0" end="2">
		                    <tr>
		                        <td>${i }</td>
		                        <td>${orderNum.get(status.index) }</td>
		                        <td>${successOrder.get(status.index) }</td>
		                        <td>${failOrder.get(status.index) }</td>
		                        <td>${amount.get(status.index) }</td>
		                    </tr>
	                    </c:forEach>
                    </tbody>
                </table>
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
                    <a href="${pageContext.request.contextPath}/management/home.action"><img src="${pageContext.request.contextPath}/images/icon/刷新.png" title="刷新数据"></a>
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

</div>
<div class="foot"></div>
</div>

<script>
	function getTimeOrder(){
		$.ajax({
			url:"${pageContext.request.contextPath}/management/getAsyncInfo.action",
			method:"post",
			success:function(data){
				if(data=='1'){
					console.log(data);
				}else{
					window.location.href="${pageContext.request.contextPath}/management/home.action";
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
    	 
    	 var length1=$(".address1").length;
    	 var length2=$(".goods1").length;
    	 for(var i=0;i<length1;i++){
    		 var text=$(".address1").eq(i).text();
    		 $(".address1").eq(i).text(text.substring(0,6)+'...');	
    	 }
    	 for(var i=0;i<length2;i++){
    		 var text=$(".goods1").eq(i).text();
    		 $(".goods1").eq(i).text(text.substring(0,7)+'...');
    	 }
    
    });
			
</script>
</body>
</html>