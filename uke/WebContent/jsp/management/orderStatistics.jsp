<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单统计</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap-datetimepicker.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/management/orderStatistics.css"/>
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
                    <img src="${pageContext.request.contextPath}/images/icon/统计a.png">
                    <p>订单统计</p>
                    </div>
               	<div id="today">
                    <span>${date }</span>
                    <span>今日销售额：${orderAmount}</span>
                    <span>订单数：${orderCount }</span>
                </div>
                <div id="dateSearch" class="form-inline">
                    <span>选择日期搜索&emsp;&emsp;</span>
                    <input id="datetimepicker1" class="form-control"  name="deliveryTime1" size="20" type="text" readonly>&emsp;至&emsp;
                    <div class="input-group">
                        <input id="datetimepicker2" class="form-control"  name="deliveryTime2" size="20" type="text" readonly>
                        <span class="input-group-addon"><a id="submit1"><i class="glyphicon glyphicon-search"> <span >搜索   </span></i></a></span>
                    </div>
                </div>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th width="24%">日期</th>
                        <th width="17%">总订单数</th>
                        <th width="17%">交易成功订单</th>
                        <th width="17%">未交易成功订单</th>
                        <th width="17%">销售额</th>
                        <th width="17%">好评率</th>
                    </tr>
                    </thead>
                    <tbody>
                    	<c:forEach items="${dateListForPage }" var="i" varStatus="status">
		                    <tr >
			                        <td>${i }</td>
			                        <td>${orderNumList.get(status.index) }</td>
			                        <td>${successOrderList.get(status.index) }</td>
			                        <td>${failOrderList.get(status.index) }</td>
			                        <td>${amountList.get(status.index) }</td>
			                        <td>${commentList.get(status.index) }</td>
		                    </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <c:if test="${size>7 }">
                	<c:choose>
                   		<c:when test="${search1!=null }">
			            <ul class="pagination">
			                <span>共${size }条数据，${pageSize }页，当前第${pageBean.pageNum}页</span>
			                <li><a href="${pageContext.request.contextPath}/management/findOrderStatistics.action?pageNum=1&datetimepicker1=${datetimepicker1}&datetimepicker2=">首页</a> </li>
			                <c:if test="${pageBean.pageNum==1 }">
				                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
				                	<li><a href="${pageContext.request.contextPath}/management/findOrderStatistics.action.action?pageNum=${i}&datetimepicker1=${datetimepicker1}&datetimepicker2=">${i }</a> </li>
				                </c:forEach>
				                <li><a href="${pageContext.request.contextPath}/management/findOrderStatistics.action?pageNum=${pageBean.pageNum+1}&datetimepicker1=${datetimepicker1}&datetimepicker2=">下一页</a> </li>
			                </c:if>
			                 <c:if test="${pageBean.pageNum>1&&pageBean.pageNum<pageBean.totalPage }">
			                 	<li><a href="${pageContext.request.contextPath}/management/findOrderStatistics.action?pageNum=${pageBean.pageNum-1}&datetimepicker1=${datetimepicker1}&datetimepicker2=">上一页</a> </li>
				                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
				                	<li><a href="${pageContext.request.contextPath}/management/findOrderStatistics.action?pageNum=${i}&datetimepicker1=${datetimepicker1}&datetimepicker2=">${i }</a> </li>
				                </c:forEach>
				                <li><a href="${pageContext.request.contextPath}/management/findOrderStatistics.action?pageNum=${pageBean.pageNum+1}&datetimepicker1=${datetimepicker1}&datetimepicker2=">下一页</a> </li>
			                </c:if>
			                 <c:if test="${pageBean.pageNum==pageBean.totalPage}">
			                 	<li><a href="${pageContext.request.contextPath}/management/findOrderStatistics.action?pageNum=${pageBean.pageNum-1}&datetimepicker1=${datetimepicker1}&datetimepicker2=">上一页</a> </li>
				                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
				                	<li><a href="${pageContext.request.contextPath}/management/findOrderStatistics.action?pageNum=${i}&datetimepicker1=${datetimepicker1}&datetimepicker2=">${i }</a> </li>
				                </c:forEach>
			                </c:if>
			            	</ul>
			            </c:when>
                   		<c:when test="${search2!=null }">
			            <ul class="pagination">
			                <span>共${size }条数据，${pageSize }页，当前第${pageBean.pageNum}页</span>
			                <li><a href="${pageContext.request.contextPath}/management/findOrderStatistics.action?pageNum=1&datetimepicker1=&datetimepicker2=${datetimepicker2}">首页</a> </li>
			                <c:if test="${pageBean.pageNum==1 }">
				                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
				                	<li><a href="${pageContext.request.contextPath}/management/findOrderStatistics.action.action?pageNum=${i}&datetimepicker1=&datetimepicker2=${datetimepicker2}">${i }</a> </li>
				                </c:forEach>
				                <li><a href="${pageContext.request.contextPath}/management/findOrderStatistics.action?pageNum=${pageBean.pageNum+1}&datetimepicker1=&datetimepicker2=${datetimepicker2}">下一页</a> </li>
			                </c:if>
			                 <c:if test="${pageBean.pageNum>1&&pageBean.pageNum<pageBean.totalPage }">
			                 	<li><a href="${pageContext.request.contextPath}/management/findOrderStatistics.action?pageNum=${pageBean.pageNum-1}&datetimepicker1=&datetimepicker2=${datetimepicker2}">上一页</a> </li>
				                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
				                	<li><a href="${pageContext.request.contextPath}/management/findOrderStatistics.action?pageNum=${i}&datetimepicker1=&datetimepicker2=${datetimepicker2}">${i }</a> </li>
				                </c:forEach>
				                <li><a href="${pageContext.request.contextPath}/management/findOrderStatistics.action?pageNum=${pageBean.pageNum+1}&datetimepicker1=&datetimepicker2=${datetimepicker2}">下一页</a> </li>
			                </c:if>
			                 <c:if test="${pageBean.pageNum==pageBean.totalPage}">
			                 	<li><a href="${pageContext.request.contextPath}/management/findOrderStatistics.action?pageNum=${pageBean.pageNum-1}&datetimepicker1=&datetimepicker2=${datetimepicker2}">上一页</a> </li>
				                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
				                	<li><a href="${pageContext.request.contextPath}/management/findOrderStatistics.action?pageNum=${i}&datetimepicker1=&datetimepicker2=${datetimepicker2}">${i }</a> </li>
				                </c:forEach>
			                </c:if>
			            	</ul>
			            </c:when>
                   		<c:when test="${search3!=null }">
			            <ul class="pagination">
			                <span>共${size }条数据，${pageSize }页，当前第${pageBean.pageNum}页</span>
			                <li><a href="${pageContext.request.contextPath}/management/findOrderStatistics.action?pageNum=1&datetimepicker1=${datetimepicker1}&datetimepicker2=${datetimepicker2}">首页</a> </li>
			                <c:if test="${pageBean.pageNum==1 }">
				                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
				                	<li><a href="${pageContext.request.contextPath}/management/findOrderStatistics.action.action?pageNum=${i}&datetimepicker1=${datetimepicker1}&datetimepicker2=${datetimepicker2}">${i }</a> </li>
				                </c:forEach>
				                <li><a href="${pageContext.request.contextPath}/management/findOrderStatistics.action?pageNum=${pageBean.pageNum+1}&datetimepicker1=${datetimepicker1}&datetimepicker2=${datetimepicker2}">下一页</a> </li>
			                </c:if>
			                 <c:if test="${pageBean.pageNum>1&&pageBean.pageNum<pageBean.totalPage }">
			                 	<li><a href="${pageContext.request.contextPath}/management/findOrderStatistics.action?pageNum=${pageBean.pageNum-1}&datetimepicker1=${datetimepicker1}&datetimepicker2=${datetimepicker2}">上一页</a> </li>
				                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
				                	<li><a href="${pageContext.request.contextPath}/management/findOrderStatistics.action?pageNum=${i}&datetimepicker1=${datetimepicker1}&datetimepicker2=${datetimepicker2}">${i }</a> </li>
				                </c:forEach>
				                <li><a href="${pageContext.request.contextPath}/management/findOrderStatistics.action?pageNum=${pageBean.pageNum+1}&datetimepicker1=${datetimepicker1}&datetimepicker2=${datetimepicker2}">下一页</a> </li>
			                </c:if>
			                 <c:if test="${pageBean.pageNum==pageBean.totalPage}">
			                 	<li><a href="${pageContext.request.contextPath}/management/findOrderStatistics.action?pageNum=${pageBean.pageNum-1}&datetimepicker1=${datetimepicker1}&datetimepicker2=${datetimepicker2}">上一页</a> </li>
				                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
				                	<li><a href="${pageContext.request.contextPath}/management/findOrderStatistics.action?pageNum=${i}&datetimepicker1=${datetimepicker1}&datetimepicker2=${datetimepicker2}">${i }</a> </li>
				                </c:forEach>
			                </c:if>
			            	</ul>
			            </c:when>
                   		
			            <c:otherwise>
			            	<ul class="pagination">
			                <span>共${size }条数据，${pageSize }页，当前第${pageBean.pageNum}页</span>
			                <li><a href="${pageContext.request.contextPath}/management/orderStatistics.action?pageNum=1">首页</a> </li>
			                <c:if test="${pageBean.pageNum==1 }">
				                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
				                	<li><a href="${pageContext.request.contextPath}/management/orderStatistics.action?pageNum=${i}">${i }</a> </li>
				                </c:forEach>
				                <li><a href="${pageContext.request.contextPath}/management/orderStatistics.action?pageNum=${pageBean.pageNum+1}">下一页</a> </li>
			                </c:if>
			                 <c:if test="${pageBean.pageNum>1&&pageBean.pageNum<pageBean.totalPage }">
			                 	<li><a href="${pageContext.request.contextPath}/management/orderStatistics.action?pageNum=${pageBean.pageNum-1}">上一页</a> </li>
				                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
				                	<li><a href="${pageContext.request.contextPath}/management/orderStatistics.action?pageNum=${i}">${i }</a> </li>
				                </c:forEach>
				                <li><a href="${pageContext.request.contextPath}/management/orderStatistics.action?pageNum=${pageBean.pageNum+1}">下一页</a> </li>
			                </c:if>
			                 <c:if test="${pageBean.pageNum==pageBean.totalPage}">
			                 	<li><a href="${pageContext.request.contextPath}/management/orderStatistics.action?pageNum=${pageBean.pageNum-1}">上一页</a> </li>
				                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
				                	<li><a href="${pageContext.request.contextPath}/management/orderStatistics.action?pageNum=${i}">${i }</a> </li>
				                </c:forEach>
			                </c:if>
			            	</ul>
			            </c:otherwise>
			           </c:choose>
		            </c:if>
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
                    <a href="${pageContext.request.contextPath}/management/orderStatistics.action?pageNum=1"><img src="${pageContext.request.contextPath}/images/icon/刷新.png" title="刷新数据"></a>
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
					window.location.href="${pageContext.request.contextPath}/management/orderStatistics.action?pageNum=1";
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
        	var datetimepicker1=$("#datetimepicker1").val();
        	var datetimepicker2=$("#datetimepicker2").val();
        	if(datetimepicker1==""&&datetimepicker2==""){
	        	window.location.href="${pageContext.request.contextPath}/management/orderStatistics.action?pageNum=1";
        	}else{
       			window.location.href="${pageContext.request.contextPath}/management/findOrderStatistics.action?pageNum=1&datetimepicker1="+datetimepicker1+"&datetimepicker2="+datetimepicker2;
        	}
        })
    });
			
</script>
</body>
</html>