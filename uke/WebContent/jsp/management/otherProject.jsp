<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>其他业务</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min2.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap-datetimepicker.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/management/otherProject.css"/>
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
                    <img src="${pageContext.request.contextPath}/images/icon/其他业务.png">
                    <p>其他业务</p>
                </div>
                <form class="form form-inline" action="${pageContext.request.contextPath}/management/addProject.action" method="post">
                    <label>业务名称：<input type="text" name="name" id="name" class="form-control" required/> </label>
                    <label>时间：<input type="text" id="datetimepicker" name="time" class="form-control" size="20" type="text" required readonly /> </label>
                    <label>消费数额：<input type="text" id="amount" name="amount" class="form-control" size="10" required/> </label>
                    <label><input type="submit" id="submit" class="btn btn-info" value="添加"/> </label>
                </form>
                <div id="dateSearch" class="form-inline">
                    <span>选择日期搜索&emsp;&emsp;</span>
                    <input id="datetimepicker1" class="form-control"  name="deliveryTime1" size="20" type="text" readonly>至
                    <div class="input-group">
                        <input id="datetimepicker2" class="form-control"  name="deliveryTime2" size="20" type="text" readonly>
                        <span class="input-group-addon"><a id="submit1"><i class="glyphicon glyphicon-search"> <span >搜索   </span></i></a></span>
                    </div>
                </div>
                <c:if test="${size!=0 }">
                 <div id="table">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th width="28%">日期</th>
                        <th width="28%">业务名称</th>
                        <th width="28%">消费数额</th>
                        <th width="16%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    	<c:forEach items="${otherProjectForPage }" var="i">
                    	 <tr >
	                        <td><fmt:formatDate value="${i.projectDate }" pattern="yyyy-MM-dd"/></td>
	                        <td>
	                        	<c:forEach items="${i.otherProject }" var="j">
	                        		<span>${j.projectName }</span>
	                        	</c:forEach>
	                        </td>
	                        <td>
	                        	<c:forEach items="${i.otherProject }" var="j">
	                 		        <span>${j.projectAmount }</span>
	                        	</c:forEach>
	                        </td>
	                        <td>
	                        	<c:forEach items="${i.otherProject }" var="j">
		                        	<span>
		                        		<a data-toggle="modal" data-target="#alter${j.opId }">修改</a>
		                        		<a class="delete">删除</a>
		                        		<input type="hidden" value="${j.opId }" class="id"/>
		                        		
		                        		<div class="modal fade" role="dialog" id="alter${j.opId }">
				                            <div class="modal-dialog" role="document">
				                                <div class="modal-content">
				                                    <div class="modal-header">
				                                        	修改业务信息
				                                        <button class="close" data-dismiss="modal">&times;</button>
				                                    </div>
				                                    <div class="modal-body">
				                                        <form id="alterForm" class="form form-inline" action="${pageContext.request.contextPath}/management/alterProject.action" method="post">
				                                       		<input type="hidden" value="${j.opId }" name="id"/>
				                                       		<label>业务名称:<input type="text" name="name" class="form-control" value="${j.projectName }" required/></label>
				                                       		<label>业务时间:<input type="text" id="datetimepicker3" name="time" class="form-control" readonly required value="<fmt:formatDate value='${j.projectDate }' pattern='yyyy-MM-dd'/>" /></label>
				                                       		<label>消费数额:<input type="number" name="amount" size="8" min="0" class="form-control" value="${j.projectAmount }" required/></label>
				                                       		<label><input type="submit" id="submit2" class="submit btn btn-info" value="提交"/></label>
				                                        </form>
				                                    </div>
				                                    <div class="modal-footer">
				                                        <button class="btn btn-primary" data-dismiss="modal">关闭</button>
				                                    </div>
				                                </div>
				                            </div>
                        				</div>	
		                        	</span>
	                        	</c:forEach>
	                        </td>
                    	</tr>
                        </c:forEach>
                    </tbody>
                </table>
                </div>
                </c:if>
                	<c:if test="${size==0 }">
                		<img id="icon" src="${pageContext.request.contextPath}/images/icon/暂无订单.png"/>
	                    <p id="info">暂无业务</p>
                	</c:if>
                   <c:if test="${size>6 }">
                   		<c:choose>
                   			<c:when test="${search1!=null }">
	                   			<ul class="pagination">
				                <span>共${size }条数据，${pageSize }页，当前第${pageBean.pageNum}页</span>
				                <li><a href="${pageContext.request.contextPath}/management/findProject.action?pageNum=1&datetimepicker1=${datetimepicker1}&datetimepicker2=">首页</a> </li>
				                <c:if test="${pageBean.pageNum==1 }">
					                <c:forEach  begin="${pageBean.start+1 }" end="${pageBean.end+1 }" step="1" var="i">
					                	<li><a href="${pageContext.request.contextPath}/management/findProject.action?pageNum=${i}&datetimepicker1=${datetimepicker1}&datetimepicker2=">${i }</a> </li>
					                </c:forEach>
					                <li><a href="${pageContext.request.contextPath}/management/findProject.action?pageNum=${pageBean.pageNum+1}&datetimepicker1=${datetimepicker1}&datetimepicker2=">下一页</a> </li>
				                </c:if>
				                 <c:if test="${pageBean.pageNum>1&&pageBean.pageNum<pageBean.totalPage }">
				                 	<li><a href="${pageContext.request.contextPath}/management/findProject.action?pageNum=${pageBean.pageNum-1}&datetimepicker1=${datetimepicker1}&datetimepicker2=">上一页</a> </li>
					                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
					                	<li><a href="${pageContext.request.contextPath}/management/findProject.action?pageNum=${i}&datetimepicker1=${datetimepicker1}&datetimepicker2=">${i }</a> </li>
					                </c:forEach>
					                <li><a href="${pageContext.request.contextPath}/management/findProject.action?pageNum=${pageBean.pageNum+1}&datetimepicker1=${datetimepicker1}&datetimepicker2=">下一页</a> </li>
				                </c:if>
				                 <c:if test="${pageBean.pageNum==pageBean.totalPage}">
				                 	<li><a href="${pageContext.request.contextPath}/management/findProject.action?pageNum=${pageBean.pageNum-1}&datetimepicker1=${datetimepicker1}&datetimepicker2=">上一页</a> </li>
					                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
					                	<li><a href="${pageContext.request.contextPath}/management/findProject.action?pageNum=${i}&datetimepicker1=${datetimepicker1}&datetimepicker2=">${i }</a> </li>
					                </c:forEach>
				                </c:if>
				                <li><a href="${pageContext.request.contextPath}/management/findProject.action?pageNum=${pageSize }&datetimepicker1=${datetimepicker1}&datetimepicker2=">尾页</a> </li>
				           		</ul>
                   			</c:when>
                   			<c:when test="${search2!=null }">
	                   			<ul class="pagination">
				                <span>共${size }条数据，${pageSize }页，当前第${pageBean.pageNum}页</span>
				                <li><a href="${pageContext.request.contextPath}/management/findProject.action?pageNum=1&datetimepicker1=&datetimepicker2=${datetimepicker2}">首页</a> </li>
				                <c:if test="${pageBean.pageNum==1 }">
					                <c:forEach  begin="${pageBean.start+1 }" end="${pageBean.end+1 }" step="1" var="i">
					                	<li><a href="${pageContext.request.contextPath}/management/findProject.action?pageNum=${i}&datetimepicker1=&datetimepicker2=${datetimepicker2}">${i }</a> </li>
					                </c:forEach>
					                <li><a href="${pageContext.request.contextPath}/management/findProject.action?pageNum=${pageBean.pageNum+1}&datetimepicker1=&datetimepicker2=${datetimepicker2}">下一页</a> </li>
				                </c:if>
				                 <c:if test="${pageBean.pageNum>1&&pageBean.pageNum<pageBean.totalPage }">
				                 	<li><a href="${pageContext.request.contextPath}/management/findProject.action?pageNum=${pageBean.pageNum-1}&datetimepicker1=&datetimepicker2=${datetimepicker2}">上一页</a> </li>
					                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
					                	<li><a href="${pageContext.request.contextPath}/management/findProject.action?pageNum=${i}&datetimepicker1=&datetimepicker2=${datetimepicker2}">${i }</a> </li>
					                </c:forEach>
					                <li><a href="${pageContext.request.contextPath}/management/findProject.action?pageNum=${pageBean.pageNum+1}&datetimepicker1=''&datetimepicker2=${datetimepicker2}">下一页</a> </li>
				                </c:if>
				                 <c:if test="${pageBean.pageNum==pageBean.totalPage}">
				                 	<li><a href="${pageContext.request.contextPath}/management/findProject.action?pageNum=${pageBean.pageNum-1}&datetimepicker1=&datetimepicker2=${datetimepicker2}">上一页</a> </li>
					                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
					                	<li><a href="${pageContext.request.contextPath}/management/findProject.action?pageNum=${i}&datetimepicker1=&datetimepicker2=${datetimepicker2}">${i }</a> </li>
					                </c:forEach>
				                </c:if>
				                <li><a href="${pageContext.request.contextPath}/management/findProject.action?pageNum=${pageSize }&datetimepicker1=&datetimepicker2=${datetimepicker2}">尾页</a> </li>
				           		</ul>
                   			</c:when>
                   			<c:when test="${search3!=null }">
	                   			<ul class="pagination">
				                <span>共${size }条数据，${pageSize }页，当前第${pageBean.pageNum}页</span>
				                <li><a href="${pageContext.request.contextPath}/management/findProject.action?pageNum=1&datetimepicker1=${datetimepicker1}&datetimepicker2=${datetimepicker2}">首页</a> </li>
				                <c:if test="${pageBean.pageNum==1 }">
					                <c:forEach  begin="${pageBean.start+1 }" end="${pageBean.end+1 }" step="1" var="i">
					                	<li><a href="${pageContext.request.contextPath}/management/findProject.action?pageNum=${i}&datetimepicker1=${datetimepicker1}&datetimepicker2=${datetimepicker2}">${i }</a> </li>
					                </c:forEach>
					                <li><a href="${pageContext.request.contextPath}/management/findProject.action?pageNum=${pageBean.pageNum+1}&datetimepicker1=${datetimepicker1}&datetimepicker2=${datetimepicker2}">下一页</a> </li>
				                </c:if>
				                 <c:if test="${pageBean.pageNum>1&&pageBean.pageNum<pageBean.totalPage }">
				                 	<li><a href="${pageContext.request.contextPath}/management/findProject.action?pageNum=${pageBean.pageNum-1}&datetimepicker1=${datetimepicker1}&datetimepicker2=${datetimepicker2}">上一页</a> </li>
					                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
					                	<li><a href="${pageContext.request.contextPath}/management/findProject.action?pageNum=${i}&datetimepicker1=${datetimepicker1}&datetimepicker2=${datetimepicker2}">${i }</a> </li>
					                </c:forEach>
					                <li><a href="${pageContext.request.contextPath}/management/findProject.action?pageNum=${pageBean.pageNum+1}&datetimepicker1=${datetimepicker1}&datetimepicker2=${datetimepicker2}">下一页</a> </li>
				                </c:if>
				                 <c:if test="${pageBean.pageNum==pageBean.totalPage}">
				                 	<li><a href="${pageContext.request.contextPath}/management/findProject.action?pageNum=${pageBean.pageNum-1}&datetimepicker1=${datetimepicker1}&datetimepicker2=${datetimepicker2}">上一页</a> </li>
					                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
					                	<li><a href="${pageContext.request.contextPath}/management/findProject.action?pageNum=${i}&datetimepicker1=${datetimepicker1}&datetimepicker2=${datetimepicker2}">${i }</a> </li>
					                </c:forEach>
				                </c:if>
				                <li><a href="${pageContext.request.contextPath}/management/findProject.action?pageNum=${pageSize }&datetimepicker1=${datetimepicker1}&datetimepicker2=${datetimepicker2}">尾页</a> </li>
				           		</ul>
                   			</c:when>
                   			<c:otherwise>
                   				<ul class="pagination">
					                <span>共${size }条数据，${pageSize }页，当前第${pageBean.pageNum}页</span>
					                <li><a href="${pageContext.request.contextPath}/management/otherProject.action?pageNum=1">首页</a> </li>
					                <c:if test="${pageBean.pageNum==1 }">
						                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
						                	<li><a href="${pageContext.request.contextPath}/management/otherProject.action?pageNum=${i}">${i }</a> </li>
						                </c:forEach>
						                <li><a href="${pageContext.request.contextPath}/management/otherProject.action?pageNum=${pageBean.pageNum+1}">下一页</a> </li>
					                </c:if>
					                 <c:if test="${pageBean.pageNum>1&&pageBean.pageNum<pageBean.totalPage }">
					                 	<li><a href="${pageContext.request.contextPath}/management/otherProject.action?pageNum=${pageBean.pageNum-1}">上一页</a> </li>
						                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
						                	<li><a href="${pageContext.request.contextPath}/management/otherProject.action?pageNum=${i}">${i }</a> </li>
						                </c:forEach>
						                <li><a href="${pageContext.request.contextPath}/management/otherProject.action?pageNum=${pageBean.pageNum+1}">下一页</a> </li>
					                </c:if>
					                 <c:if test="${pageBean.pageNum==pageBean.totalPage}">
					                 	<li><a href="${pageContext.request.contextPath}/management/otherProject.action?pageNum=${pageBean.pageNum-1}">上一页</a> </li>
						                <c:forEach  begin="${pageBean.start }" end="${pageBean.end }" step="1" var="i">
						                	<li><a href="${pageContext.request.contextPath}/management/otherProject.action?pageNum=${i}">${i }</a> </li>
						                </c:forEach>
					                </c:if>
					                <li><a href="${pageContext.request.contextPath}/management/otherProject.action?pageNum=${pageSize }">尾页</a> </li>
					            </ul>
                   			</c:otherwise>
                   		</c:choose>
                   	</c:if>
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
					window.location.href="${pageContext.request.contextPath}/management/otherProject.action?pageNum=1";
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
                 startView:3,
                 minView:2,
                 maxView:2,
                 todayBtn: true,
                 language:'zh-CN',
                 pickerPosition:"bottom-left"
             };
    	 $('#datetimepicker').datetimepicker(dateOptions);
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
    	 
         $("#submit").click(function (e) {
             var time=$("#datetimepicker").val();
             var name=$("#name").val();
             var amount=$("#amount").val();
             if(name==''){
                 alert("请输入业务名称");
                 e.preventDefault();
                 return;
             }
             if(time==''){
                 alert("请输入时间");
                 e.preventDefault();
                 return;
             }
             if(amount==''){
                 alert("请输入消费金额");
                 e.preventDefault();
                 return;
             }
             if(amount<0){
                 alert("消费金额不能小于0");
                 e.preventDefault();
                 return;
             }else{
            	 alert("添加成功")
             }
         })
         
          $("#submit1").click(function(){
        	var datetimepicker1=$("#datetimepicker1").val();
        	var datetimepicker2=$("#datetimepicker2").val();
        	if(datetimepicker1==""&&datetimepicker2==""){
	        	window.location.href="${pageContext.request.contextPath}/management/otherProject.action?pageNum=1";
        	}else{
       			window.location.href="${pageContext.request.contextPath}/management/findProject.action?pageNum=1&datetimepicker1="+datetimepicker1+"&datetimepicker2="+datetimepicker2;
        	}
        })
        
        $(".delete").click(function(){
        	
        	var index=$(".delete").index(this);
        	var id=$(".id").eq(index).val();
        	alert(id)
        	var con=confirm("确定删除吗？");
 			if(con==true){
 				alert("删除成功")
 				window.location.href="${pageContext.request.contextPath}/management/deleteProject.action?id="+id;
 			}else{
 				 e.preventDefault();
 			}
        })
        $("#submit2").click(function(){
        	var con=confirm("确定修改此业务吗？");
 			if(con==true){
 				alert("修改成功")
 				//window.location.href="${pageContext.request.contextPath}/management/deleteProject.action?id="+id;
 			}else{
 				 e.preventDefault();
 			}
        })
    });
			
</script>
</body>
</html>