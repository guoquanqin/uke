<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评价管理</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min2.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap-datetimepicker.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/management/commentManagement.css"/>
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
                    <img src="${pageContext.request.contextPath}/images/icon/评价管理.png">
                    <p>评价管理</p>
                </div>
                <div id="dateSearch" class="form-inline">
                    <span>选择日期搜索&emsp;&emsp;</span>
                    <input id="datetimepicker1" class="form-control"  name="deliveryTime1" size="20" type="text" readonly>至
                    <div class="input-group">
                        <input id="datetimepicker2" class="form-control"  name="deliveryTime2" size="20" type="text" readonly>
                        <span class="input-group-addon"><a id="submit"><i class="glyphicon glyphicon-search"> <span >搜索   </span></i></a></span>
                    </div>
                </div>
                <c:if test="${size!=0}">
                <div class="comment">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th width="7%">时间</th>
                        <th width="9%">订单号</th>
                        <th width="7%">评价客户</th>
                        <th width="7%">评价等级</th>
                        <th width="15%">商品信息</th>
                        <th width="21%">图片</th>
                        <th width="14%">评语</th>
                        <th width="13%">商家反馈</th>
                        <th width="9%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    	<c:forEach items="${commentListForPage }" var="i" varStatus="status">
                    	<c:if test="${i.isShow==3||i.isShow==0}">
                    	<tr class="danger">
                    	</c:if>
                    	<c:if test="${i.isShow==1||i.isShow==2}">
                    	<tr>
                    	</c:if>
                        <td><fmt:formatDate value="${i.commentTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td>
                        	<c:if test="${i.isShow==2||i.isShow==3}">
		                        ${i.orderUserId }(积分订单)
                        	</c:if>
                        	<c:if test="${i.isShow==1||i.isShow==0}">
		                        ${i.orderUserId }
                        	</c:if>
                        </td>
                        <td>${i.user.userName }</td>
                        <td>${i.commentLevel }</td>
                        <td>
                        	<c:forEach items="${i.goodsList }" var="j">
                        		${j.goodsName }&emsp;
                        	</c:forEach>
                        </td>
                        <td>
                        	<c:forTokens items="${i.commentPhoto }" delims="/" var="j">
		                        <img src="${pageContext.request.contextPath}/images/评价图片/${j}">
                        	</c:forTokens>
                        </td>
                        <td>${i.commentContents }</td>
                        <td><font style="color:orange;">${i.feedbackComment }</font></td>
                        <td>
                        	<c:if test="${i.feedbackComment==null }">
		                        <a data-toggle="modal" data-target="#infoModal${status.index }">添加反馈</a>
                        	</c:if>
                        	<c:if test="${i.feedbackComment!=null }">
		                        <a href="${pageContext.request.contextPath}/management/deleteFeedbackComment.action?orderId=${i.orderUserId}&isShow=${i.isShow}">删除反馈</a>
                        	</c:if>
                        	<c:if test="${i.isShow==1||i.isShow==2}">
	                            <a href="${pageContext.request.contextPath}/management/deleteComment.action?commentId=${i.commentId}&isShow=${i.isShow}">删除评价</a>
                        	</c:if>
                        	<c:if test="${i.isShow==0||i.isShow==3 }">
	                            <a href="${pageContext.request.contextPath}/management/backComment.action?commentId=${i.commentId}&isShow=${i.isShow}">还原评价</a> 
                        	</c:if>
                        </td>
                        <div class="modal fade" role="dialog" id="infoModal${status.index }">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        添加反馈
                                        <button class="close" data-dismiss="modal">&times;</button>
                                    </div>
                                    <div class="modal-body">
                                        <form class="form-inline" action="${pageContext.request.contextPath}/management/addFeedbackComment.action?userId=${i.userId }&isShow=${i.isShow}&orderId=${i.orderUserId}" method="post">
                                            <textarea type="text" rows="6" size="8" name="comment" class="form-control" placeholder="写下你对客户的反馈吧~"></textarea>
                                            <input type="submit" id="submit" class="btn btn-info" value="提交"/>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button class="btn btn-primary" data-dismiss="modal">关闭</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
                </div>
                </c:if>
                
                <!-- 如果没有评论 -->
                <c:if test="${size==0 }">
                 <img id="icon" src="${pageContext.request.contextPath}/images/icon/暂无订单.png"/>
                 <p id="info">暂无评论</p>
                </c:if>
                    
                    
                <c:if test="${size>4 }">
                   		<c:choose>
                   		<c:when test="${search1!=null }">
			            <ul class="pagination">
			                <span>共${size }条数据，${pageSize }页，当前第${pageBean.pageNum}页</span>
			                <li><a href="${pageContext.request.contextPath}/management/findComment.action?pageNum=1&datetimepicker1=${datetimepicker1}&datetimepicker2=">首页</a> </li>
			                <c:if test="${pageBean.pageNum==1 }">
				                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
				                	<li><a href="${pageContext.request.contextPath}/management/findComment.action.action?pageNum=${i}&datetimepicker1=${datetimepicker1}&datetimepicker2=">${i }</a> </li>
				                </c:forEach>
				                <li><a href="${pageContext.request.contextPath}/management/findComment.action?pageNum=${pageBean.pageNum+1}&datetimepicker1=${datetimepicker1}&datetimepicker2=">下一页</a> </li>
			                </c:if>
			                 <c:if test="${pageBean.pageNum>1&&pageBean.pageNum<pageBean.totalPage }">
			                 	<li><a href="${pageContext.request.contextPath}/management/findComment.action?pageNum=${pageBean.pageNum-1}&datetimepicker1=${datetimepicker1}&datetimepicker2=">上一页</a> </li>
				                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
				                	<li><a href="${pageContext.request.contextPath}/management/findComment.action?pageNum=${i}&datetimepicker1=${datetimepicker1}&datetimepicker2=">${i }</a> </li>
				                </c:forEach>
				                <li><a href="${pageContext.request.contextPath}/management/findComment.action?pageNum=${pageBean.pageNum+1}&datetimepicker1=${datetimepicker1}&datetimepicker2=">下一页</a> </li>
			                </c:if>
			                 <c:if test="${pageBean.pageNum==pageBean.totalPage}">
			                 	<li><a href="${pageContext.request.contextPath}/management/findComment.action?pageNum=${pageBean.pageNum-1}&datetimepicker1=${datetimepicker1}&datetimepicker2=">上一页</a> </li>
				                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
				                	<li><a href="${pageContext.request.contextPath}/management/findComment.action?pageNum=${i}&datetimepicker1=${datetimepicker1}&datetimepicker2=">${i }</a> </li>
				                </c:forEach>
			                </c:if>
			            	</ul>
			            </c:when>
                   		<c:when test="${search2!=null }">
			            <ul class="pagination">
			                <span>共${size }条数据，${pageSize }页，当前第${pageBean.pageNum}页</span>
			                <li><a href="${pageContext.request.contextPath}/management/findComment.action?pageNum=1&datetimepicker1=&datetimepicker2=${datetimepicker2}">首页</a> </li>
			                <c:if test="${pageBean.pageNum==1 }">
				                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
				                	<li><a href="${pageContext.request.contextPath}/management/findComment.action.action?pageNum=${i}&datetimepicker1=&datetimepicker2=${datetimepicker2}">${i }</a> </li>
				                </c:forEach>
				                <li><a href="${pageContext.request.contextPath}/management/findComment.action?pageNum=${pageBean.pageNum+1}&datetimepicker1=&datetimepicker2=${datetimepicker2}">下一页</a> </li>
			                </c:if>
			                 <c:if test="${pageBean.pageNum>1&&pageBean.pageNum<pageBean.totalPage }">
			                 	<li><a href="${pageContext.request.contextPath}/management/findComment.action?pageNum=${pageBean.pageNum-1}&datetimepicker1=&datetimepicker2=${datetimepicker2}">上一页</a> </li>
				                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
				                	<li><a href="${pageContext.request.contextPath}/management/findComment.action?pageNum=${i}&datetimepicker1=&datetimepicker2=${datetimepicker2}">${i }</a> </li>
				                </c:forEach>
				                <li><a href="${pageContext.request.contextPath}/management/findComment.action?pageNum=${pageBean.pageNum+1}&datetimepicker1=&datetimepicker2=${datetimepicker2}">下一页</a> </li>
			                </c:if>
			                 <c:if test="${pageBean.pageNum==pageBean.totalPage}">
			                 	<li><a href="${pageContext.request.contextPath}/management/findComment.action?pageNum=${pageBean.pageNum-1}&datetimepicker1=&datetimepicker2=${datetimepicker2}">上一页</a> </li>
				                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
				                	<li><a href="${pageContext.request.contextPath}/management/findComment.action?pageNum=${i}&datetimepicker1=&datetimepicker2=${datetimepicker2}">${i }</a> </li>
				                </c:forEach>
			                </c:if>
			            	</ul>
			            </c:when>
                   		<c:when test="${search3!=null }">
			            <ul class="pagination">
			                <span>共${size }条数据，${pageSize }页，当前第${pageBean.pageNum}页</span>
			                <li><a href="${pageContext.request.contextPath}/management/findComment.action?pageNum=1&datetimepicker1=${datetimepicker1}&datetimepicker2=${datetimepicker2}">首页</a> </li>
			                <c:if test="${pageBean.pageNum==1 }">
				                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
				                	<li><a href="${pageContext.request.contextPath}/management/findComment.action.action?pageNum=${i}&datetimepicker1=${datetimepicker1}&datetimepicker2=${datetimepicker2}">${i }</a> </li>
				                </c:forEach>
				                <li><a href="${pageContext.request.contextPath}/management/findComment.action?pageNum=${pageBean.pageNum+1}&datetimepicker1=${datetimepicker1}&datetimepicker2=${datetimepicker2}">下一页</a> </li>
			                </c:if>
			                 <c:if test="${pageBean.pageNum>1&&pageBean.pageNum<pageBean.totalPage }">
			                 	<li><a href="${pageContext.request.contextPath}/management/findComment.action?pageNum=${pageBean.pageNum-1}&datetimepicker1=${datetimepicker1}&datetimepicker2=${datetimepicker2}">上一页</a> </li>
				                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
				                	<li><a href="${pageContext.request.contextPath}/management/findComment.action?pageNum=${i}&datetimepicker1=${datetimepicker1}&datetimepicker2=${datetimepicker2}">${i }</a> </li>
				                </c:forEach>
				                <li><a href="${pageContext.request.contextPath}/management/findComment.action?pageNum=${pageBean.pageNum+1}&datetimepicker1=${datetimepicker1}&datetimepicker2=${datetimepicker2}">下一页</a> </li>
			                </c:if>
			                 <c:if test="${pageBean.pageNum==pageBean.totalPage}">
			                 	<li><a href="${pageContext.request.contextPath}/management/findComment.action?pageNum=${pageBean.pageNum-1}&datetimepicker1=${datetimepicker1}&datetimepicker2=${datetimepicker2}">上一页</a> </li>
				                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
				                	<li><a href="${pageContext.request.contextPath}/management/findComment.action?pageNum=${i}&datetimepicker1=${datetimepicker1}&datetimepicker2=${datetimepicker2}">${i }</a> </li>
				                </c:forEach>
			                </c:if>
			            	</ul>
			            </c:when>
                   		
			            	<c:otherwise>
                  				<ul class="pagination">
				                <span>共${size }条数据，${pageSize }页，当前第${pageBean.pageNum}页</span>
				                <li><a href="${pageContext.request.contextPath}/management/commentManagement.action?pageNum=1">首页</a> </li>
				                <c:if test="${pageBean.pageNum==1 }">
					                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
					                	<li><a  href="${pageContext.request.contextPath}/management/commentManagement.action?pageNum=${i}">${i }</a> </li>
					                </c:forEach>
					                <li><a href="${pageContext.request.contextPath}/management/commentManagement.action?pageNum=${pageBean.pageNum+1}">下一页</a> </li>
				                </c:if>
				                 <c:if test="${pageBean.pageNum>1&&pageBean.pageNum<pageBean.totalPage }">
				                 	<li><a href="${pageContext.request.contextPath}/management/commentManagement.action?pageNum=${pageBean.pageNum-1}">上一页</a> </li>
					                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
					                	<li><a href="${pageContext.request.contextPath}/management/commentManagement.action?pageNum=${i}">${i }</a> </li>
					                </c:forEach>
					                <li><a href="${pageContext.request.contextPath}/management/commentManagement.action?pageNum=${pageBean.pageNum+1}">下一页</a> </li>
				                </c:if>
				                 <c:if test="${pageBean.pageNum==pageBean.totalPage}">
				                 	<li><a href="${pageContext.request.contextPath}/management/commentManagement.action?pageNum=${pageBean.pageNum-1}">上一页</a> </li>
					                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
					                	<li><a href="${pageContext.request.contextPath}/management/commentManagement.action?pageNum=${i}">${i }</a> </li>
					                </c:forEach>
				                </c:if>
				                <li><a href="${pageContext.request.contextPath}/management/commentManagement.action?pageNum=${pageSize }">尾页</a> </li>
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
					window.location.href="${pageContext.request.contextPath}/management/commentManagement.action?pageNum=1";
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
    	 
        $("#submit").click(function(){
        	var datetimepicker1=$("#datetimepicker1").val();
        	var datetimepicker2=$("#datetimepicker2").val();
        	if(datetimepicker1==""&&datetimepicker2==""){
	        	window.location.href="${pageContext.request.contextPath}/management/commentManagement.action?pageNum=1";
        	}else{
       			window.location.href="${pageContext.request.contextPath}/management/findComment.action?pageNum=1&datetimepicker1="+datetimepicker1+"&datetimepicker2="+datetimepicker2;
        	}
        })
    });
			
</script>
</body>
</html>