<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>历史订单</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap-datetimepicker.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/management/historicalOrder.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/jquery-ui.min.css"/>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/vue.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/js/vue-resource.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap-datetimepicker.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap-datetimepicker.zh-CN.js"></script>
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
                    <img src="${pageContext.request.contextPath}/images/icon/历史订单.png">
                    <p>历史订单</p>
                </div>
                <div class="order">
                    <div class="orderTop">
                        <div class="form-inline">
                            <div class="input-group">
                                <input type="text" id="search1" size="30" class="form-control" placeholder="输入订单号/用户名/手机号 搜索"/>
                                <span class="input-group-addon"><a id="submit1"><i class="glyphicon glyphicon-search"> <span >搜索   </span></i></a></span>
                            </div>
                        </div>

                        <div id="dateSearch" class="form-inline">
                            <span>选择日期搜索&emsp;&emsp;</span>
                                <input id="datetimepicker1" class="form-control"  name="deliveryTime1" size="20" type="text" readonly>&emsp;至&emsp;
                            <div class="input-group">
                                <input id="datetimepicker2" class="form-control"  name="deliveryTime2" size="20" type="text" readonly>
                                <span class="input-group-addon"><a id="submit2"><i class="glyphicon glyphicon-search"> <span >搜索   </span></i></a></span>
                            </div>
                        </div>
                    </div>
                    <!-- 如果有订单 -->
                    <c:if test="${size!=0 }">
                    <div id="table">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th width="110px">订单号</th>
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
                   		<c:choose>
                   			<c:when test="${search1!=null }">
	                   			<ul class="pagination">
				                <span>共${size }条数据，${pageSize }页，当前第${pageBean.pageNum}页</span>
				                <li><a href="${pageContext.request.contextPath}/management/findHistoricalOrderByFirstCondition.action?pageNum=1&content=${content}">首页</a> </li>
				                <c:if test="${pageBean.pageNum==1 }">
					                <c:forEach  begin="${pageBean.start+1 }" end="${pageBean.end+1 }" step="1" var="i">
					                	<li><a href="${pageContext.request.contextPath}/management/findHistoricalOrderByFirstCondition.action?pageNum=${i}&content=${content}">${i }</a> </li>
					                </c:forEach>
					                <li><a href="${pageContext.request.contextPath}/management/findHistoricalOrderByFirstCondition.action?pageNum=${pageBean.pageNum+1}&content=${content}">下一页</a> </li>
				                </c:if>
				                 <c:if test="${pageBean.pageNum>1&&pageBean.pageNum<pageBean.totalPage }">
				                 	<li><a href="${pageContext.request.contextPath}/management/findHistoricalOrderByFirstCondition.action?pageNum=${pageBean.pageNum-1}&content=${content}">上一页</a> </li>
					                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
					                	<li><a href="${pageContext.request.contextPath}/management/findHistoricalOrderByFirstCondition.action?pageNum=${i}&content=${content}">${i }</a> </li>
					                </c:forEach>
					                <li><a href="${pageContext.request.contextPath}/management/findHistoricalOrderByFirstCondition.action?pageNum=${pageBean.pageNum+1}&content=${content}">下一页</a> </li>
				                </c:if>
				                 <c:if test="${pageBean.pageNum==pageBean.totalPage}">
				                 	<li><a href="${pageContext.request.contextPath}/management/findHistoricalOrderByFirstCondition.action?pageNum=${pageBean.pageNum-1}&content=${content}">上一页</a> </li>
					                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
					                	<li><a href="${pageContext.request.contextPath}/management/findHistoricalOrderByFirstCondition.action?pageNum=${i}&content=${content}">${i }</a> </li>
					                </c:forEach>
				                </c:if>
				                <li><a href="${pageContext.request.contextPath}/management/findHistoricalOrderByFirstCondition.action?pageNum=${pageSize }&content=${content}">尾页</a> </li>
				           		</ul>
                   			</c:when>
                   			<c:when test="${search2!=null }">
	                   			<ul class="pagination">
				                <span>共${size }条数据，${pageSize }页，当前第${pageBean.pageNum}页</span>
				                <li><a href="${pageContext.request.contextPath}/management/findHistoricalOrderBySecondCondition.action?pageNum=1&datetimepicker1=${datetimepicker1}&datetimepicker2=">首页</a> </li>
				                <c:if test="${pageBean.pageNum==1 }">
					                <c:forEach  begin="${pageBean.start+1 }" end="${pageBean.end+1 }" step="1" var="i">
					                	<li><a href="${pageContext.request.contextPath}/management/findHistoricalOrderBySecondCondition.action?pageNum=${i}&datetimepicker1=${datetimepicker1}&datetimepicker2=">${i }</a> </li>
					                </c:forEach>
					                <li><a href="${pageContext.request.contextPath}/management/findHistoricalOrderBySecondCondition.action?pageNum=${pageBean.pageNum+1}&datetimepicker1=${datetimepicker1}&datetimepicker2=">下一页</a> </li>
				                </c:if>
				                 <c:if test="${pageBean.pageNum>1&&pageBean.pageNum<pageBean.totalPage }">
				                 	<li><a href="${pageContext.request.contextPath}/management/findHistoricalOrderBySecondCondition?pageNum=${pageBean.pageNum-1}&datetimepicker1=${datetimepicker1}&datetimepicker2=">上一页</a> </li>
					                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
					                	<li><a href="${pageContext.request.contextPath}/management/findHistoricalOrderBySecondCondition.action?pageNum=${i}&datetimepicker1=${datetimepicker1}&datetimepicker2=">${i }</a> </li>
					                </c:forEach>
					                <li><a href="${pageContext.request.contextPath}/management/findHistoricalOrderBySecondCondition.action?pageNum=${pageBean.pageNum+1}&datetimepicker1=${datetimepicker1}&datetimepicker2=">下一页</a> </li>
				                </c:if>
				                 <c:if test="${pageBean.pageNum==pageBean.totalPage}">
				                 	<li><a href="${pageContext.request.contextPath}/management/findHistoricalOrderBySecondCondition.action?pageNum=${pageBean.pageNum-1}&datetimepicker1=${datetimepicker1}&datetimepicker2=">上一页</a> </li>
					                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
					                	<li><a href="${pageContext.request.contextPath}/management/findHistoricalOrderBySecondCondition.action?pageNum=${i}&datetimepicker1=${datetimepicker1}&datetimepicker2=">${i }</a> </li>
					                </c:forEach>
				                </c:if>
				                <li><a href="${pageContext.request.contextPath}/management/findHistoricalOrderBySecondCondition.action?pageNum=${pageSize }&datetimepicker1=${datetimepicker1}&datetimepicker2=">尾页</a> </li>
				           		</ul>
                   			</c:when>
                   			<c:when test="${search3!=null }">
	                   			<ul class="pagination">
				                <span>共${size }条数据，${pageSize }页，当前第${pageBean.pageNum}页</span>
				                <li><a href="${pageContext.request.contextPath}/management/findHistoricalOrderBySecondCondition.action?pageNum=1&datetimepicker1=&datetimepicker2=${datetimepicker2}">首页</a> </li>
				                <c:if test="${pageBean.pageNum==1 }">
					                <c:forEach  begin="${pageBean.start+1 }" end="${pageBean.end+1 }" step="1" var="i">
					                	<li><a href="${pageContext.request.contextPath}/management/findHistoricalOrderBySecondCondition.action?pageNum=${i}&datetimepicker1=&datetimepicker2=${datetimepicker2}">${i }</a> </li>
					                </c:forEach>
					                <li><a href="${pageContext.request.contextPath}/management/findHistoricalOrderBySecondCondition.action?pageNum=${pageBean.pageNum+1}&datetimepicker1=&datetimepicker2=${datetimepicker2}">下一页</a> </li>
				                </c:if>
				                 <c:if test="${pageBean.pageNum>1&&pageBean.pageNum<pageBean.totalPage }">
				                 	<li><a href="${pageContext.request.contextPath}/management/findHistoricalOrderBySecondCondition.action?pageNum=${pageBean.pageNum-1}&datetimepicker1=&datetimepicker2=${datetimepicker2}">上一页</a> </li>
					                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
					                	<li><a href="${pageContext.request.contextPath}/management/findHistoricalOrderBySecondCondition.action?pageNum=${i}&datetimepicker1=&datetimepicker2=${datetimepicker2}">${i }</a> </li>
					                </c:forEach>
					                <li><a href="${pageContext.request.contextPath}/management/findHistoricalOrderBySecondCondition.action?pageNum=${pageBean.pageNum+1}&datetimepicker1=''&datetimepicker2=${datetimepicker2}">下一页</a> </li>
				                </c:if>
				                 <c:if test="${pageBean.pageNum==pageBean.totalPage}">
				                 	<li><a href="${pageContext.request.contextPath}/management/findHistoricalOrderBySecondCondition.action?pageNum=${pageBean.pageNum-1}&datetimepicker1=&datetimepicker2=${datetimepicker2}">上一页</a> </li>
					                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
					                	<li><a href="${pageContext.request.contextPath}/management/findHistoricalOrderBySecondCondition.action?pageNum=${i}&datetimepicker1=&datetimepicker2=${datetimepicker2}">${i }</a> </li>
					                </c:forEach>
				                </c:if>
				                <li><a href="${pageContext.request.contextPath}/management/findHistoricalOrderBySecondCondition.action?pageNum=${pageSize }&datetimepicker1=&datetimepicker2=${datetimepicker2}">尾页</a> </li>
				           		</ul>
                   			</c:when>
                   			<c:when test="${search4!=null }">
	                   			<ul class="pagination">
				                <span>共${size }条数据，${pageSize }页，当前第${pageBean.pageNum}页</span>
				                <li><a href="${pageContext.request.contextPath}/management/findHistoricalOrderBySecondCondition.action?pageNum=1&datetimepicker1=${datetimepicker1}&datetimepicker2=${datetimepicker2}">首页</a> </li>
				                <c:if test="${pageBean.pageNum==1 }">
					                <c:forEach  begin="${pageBean.start+1 }" end="${pageBean.end+1 }" step="1" var="i">
					                	<li><a href="${pageContext.request.contextPath}/management/findHistoricalOrderBySecondCondition.action?pageNum=${i}&datetimepicker1=${datetimepicker1}&datetimepicker2=${datetimepicker2}">${i }</a> </li>
					                </c:forEach>
					                <li><a href="${pageContext.request.contextPath}/management/findHistoricalOrderBySecondCondition.action?pageNum=${pageBean.pageNum+1}&datetimepicker1=${datetimepicker1}&datetimepicker2=${datetimepicker2}">下一页</a> </li>
				                </c:if>
				                 <c:if test="${pageBean.pageNum>1&&pageBean.pageNum<pageBean.totalPage }">
				                 	<li><a href="${pageContext.request.contextPath}/management/findHistoricalOrderBySecondCondition.action?pageNum=${pageBean.pageNum-1}&datetimepicker1=${datetimepicker1}&datetimepicker2=${datetimepicker2}">上一页</a> </li>
					                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
					                	<li><a href="${pageContext.request.contextPath}/management/findHistoricalOrderBySecondCondition.action?pageNum=${i}&datetimepicker1=${datetimepicker1}&datetimepicker2=${datetimepicker2}">${i }</a> </li>
					                </c:forEach>
					                <li><a href="${pageContext.request.contextPath}/management/findHistoricalOrderBySecondCondition.action?pageNum=${pageBean.pageNum+1}&datetimepicker1=${datetimepicker1}&datetimepicker2=${datetimepicker2}">下一页</a> </li>
				                </c:if>
				                 <c:if test="${pageBean.pageNum==pageBean.totalPage}">
				                 	<li><a href="${pageContext.request.contextPath}/management/findHistoricalOrderBySecondCondition.action?pageNum=${pageBean.pageNum-1}&datetimepicker1=${datetimepicker1}&datetimepicker2=${datetimepicker2}">上一页</a> </li>
					                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
					                	<li><a href="${pageContext.request.contextPath}/management/findHistoricalOrderBySecondCondition.action?pageNum=${i}&datetimepicker1=${datetimepicker1}&datetimepicker2=${datetimepicker2}">${i }</a> </li>
					                </c:forEach>
				                </c:if>
				                <li><a href="${pageContext.request.contextPath}/management/findHistoricalOrderBySecondCondition.action?pageNum=${pageSize }&datetimepicker1=${datetimepicker1}&datetimepicker2=${datetimepicker2}">尾页</a> </li>
				           		</ul>
                   			</c:when>
                   			<c:otherwise>
                   				<ul class="pagination">
					                <span>共${size }条数据，${pageSize }页，当前第${pageBean.pageNum}页</span>
					                <li><a href="${pageContext.request.contextPath}/management/historicalOrder.action?pageNum=1">首页</a> </li>
					                <c:if test="${pageBean.pageNum==1 }">
						                <c:forEach  begin="${pageBean.start+1 }" end="${pageBean.end+1 }" step="1" var="i">
						                	<li><a href="${pageContext.request.contextPath}/management/historicalOrder.action?pageNum=${i}">${i }</a> </li>
						                </c:forEach>
						                <li><a href="${pageContext.request.contextPath}/management/historicalOrder.action?pageNum=${pageBean.pageNum+1}">下一页</a> </li>
					                </c:if>
					                 <c:if test="${pageBean.pageNum>1&&pageBean.pageNum<pageBean.totalPage }">
					                 	<li><a href="${pageContext.request.contextPath}/management/historicalOrder.action?pageNum=${pageBean.pageNum-1}">上一页</a> </li>
						                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
						                	<li><a href="${pageContext.request.contextPath}/management/historicalOrder.action?pageNum=${i}">${i }</a> </li>
						                </c:forEach>
						                <li><a href="${pageContext.request.contextPath}/management/historicalOrder.action?pageNum=${pageBean.pageNum+1}">下一页</a> </li>
					                </c:if>
					                 <c:if test="${pageBean.pageNum==pageBean.totalPage}">
					                 	<li><a href="${pageContext.request.contextPath}/management/historicalOrder.action?pageNum=${pageBean.pageNum-1}">上一页</a> </li>
						                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
						                	<li><a href="${pageContext.request.contextPath}/management/historicalOrder.action?pageNum=${i}">${i }</a> </li>
						                </c:forEach>
					                </c:if>
					                <li><a href="${pageContext.request.contextPath}/management/historicalOrder.action?pageNum=${pageSize }">尾页</a> </li>
					            </ul>
                   			</c:otherwise>
                   		</c:choose>
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
					window.location.href="${pageContext.request.contextPath}/management/historicalOrder.action?pageNum=1";
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
    	 
    	 var dateOptions = {
                 format: 'yyyy-mm-dd',
                 autoclose:true,
                 startView:2,
                 minView:2,
                 maxView:2,
                 todayBtn: true,
                 language:'zh-CN',
                 pickerPosition:"bottom-left"
             };
         $('#datetimepicker1').datetimepicker(dateOptions).on('show', function () {
             var endTime = $('#datetimepicker2').val();
             if (endTime !== '') {
                 $(this).datetimepicker('setEndDate', endTime);
             }else{
                 $(this).datetimepicker('setEndDate', null);
             }
         })
         $('#datetimepicker2').datetimepicker(dateOptions).on('show', function () {
             var startTime = $('#datetimepicker1').val();
             if (startTime !== '') {
                 $(this).datetimepicker('setStartDate', startTime);
             }else{
             $(this).datetimepicker('setStartDate', null);
         }
         });
    	 
        $("#submit1").click(function(){
        	var content=$("#search1").val();
        	if(content==""){
	        	window.location.href="${pageContext.request.contextPath}/management/historicalOrder.action?pageNum=1";
        	}else{
        		window.location.href="${pageContext.request.contextPath}/management/findHistoricalOrderByFirstCondition.action?pageNum=1&content="+content;
        	}
        })
        $("#submit2").click(function(){
        	var datetimepicker1=$("#datetimepicker1").val();
        	var datetimepicker2=$("#datetimepicker2").val();
        	if(datetimepicker1==""&&datetimepicker2==""){
	        	window.location.href="${pageContext.request.contextPath}/management/historicalOrder.action?pageNum=1";
        	}else{
       			window.location.href="${pageContext.request.contextPath}/management/findHistoricalOrderBySecondCondition.action?pageNum=1&datetimepicker1="+datetimepicker1+"&datetimepicker2="+datetimepicker2;
        	}
        })
    });
			
</script>
</body>
</html>