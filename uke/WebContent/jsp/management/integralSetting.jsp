<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>积分设置</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/management/integralSetting.css"/>
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
                    <img src="${pageContext.request.contextPath}/images/icon/积分2.png">
                    <p>积分设置</p>
                </div>
                <form  action="${pageContext.request.contextPath}/management/addIntegralGoods.action" method="post" class="form form-inline">
                    <label>添加积分商品：</label>
                    <label>
                        <span>商品类型</span>
                        <select id="type" name="gtId" onChange="change()" class="form-control">
                        	<c:forEach items="${allGoodsType }" var="i" varStatus="status">
                        		<c:if test="${status.index==0 }">
	                           	 	<option selected value="${i.gtId }">${i.gtName }</option>
	                            </c:if>
                        		<c:if test="${status.index!=0 }">
	                           	 	<option value="${i.gtId }">${i.gtName }</option>
	                            </c:if>
                        	</c:forEach>
                        </select>
                    </label>
                    <label>
                        <span>商品名称</span>
                        <select id="name" name="goodsId" class="form-control">
                            <c:forEach items="${jdnc }" var="i">
	                            <option>${i.goodsName }</option>
                        	</c:forEach>
                        </select>
                    </label>
                    <label>
                        <span>兑换积分</span>
                        <input type="text" id="integral" name="integral" class="form-control" size="8">
                    </label>
                    <label>
                        <input type="submit" id="submit1" class="btn btn-info" value="添加" />
                    </label>
                </form>
                <form  action="${pageContext.request.contextPath}/management/alterIntegral.action" method="post" class="form form-inline">
                    <label>修改商品积分：</label>
                    <label>
                        <span>积分商品</span>
                        <select id="integralId" name="integralId" class="form-control">
                       		<c:forEach items="${goodsList }" var="i">
	                            <option value="${i.goodsId }">${i.goodsName }</option>
                        	</c:forEach>
                        </select>
                    </label>
                    <label>
                        <span>可兑换积分</span>
                        <input type="text" id="alterIntegral" name="integral" class="form-control" size="8">
                    </label>
                    <label>
                        <input type="submit" id="submit2" class="btn btn-info" value="修改" />
                    </label>
                </form>
                <div class="goods">
                    <ul>
                    	<c:forEach items="${goodsList }" var="i" varStatus="status">
	                        <li>
	                            <img src="${pageContext.request.contextPath}/images/${typeList.get(status.index).gtName }/${i.goodsPicture}"/>
	                            <div>${shop.get(status.index).integral }积分</div>
	                            <div class="goodsName">${i.goodsName }</div>
	                            <a href="${pageContext.request.contextPath}/management/removeIntegralGoods.action?goodsId=${i.goodsId}" class="btn btn-info">移除 ></a>
	                        </li>
                        </c:forEach>
                    </ul>
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
					window.location.href="${pageContext.request.contextPath}/management/integralSetting.action";
					console.log(data);
				}
				getTimeOrder();
			}
		});
	}

	function change(){
		 //清空
		 $("#name").html("");
		 var gtId=$("#type").val();
			 $.ajax({
					url:"${pageContext.request.contextPath }/management/findGoodsNameByTypeInIntegral.action",
					data:gtId,
					contentType:"application/json;charset=UTF-8",
					type:"post",
					dataType:"json",  //回调数据类型
					success:function(data){
						for(var i in data){
							$("#name").append("<option value='"+data[i].goodsId+"'>"+data[i].goodsName+"</option>");
						}
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
    	 
    	 var length=$(".goodsName").length;
    	 for(var i=0;i<length;i++){
    		 var text=$(".goodsName").eq(i).text().trim();
    		 if(text.length>8){
	    		 $(".goodsName").eq(i).text(text.substring(0,7)+'...');	
    		 }
    	 }

        $("#submit1").click(function (e) {
        	var gtId=$("#type").val();
        	var integral=$("#integral").val();
        	var name=$("#name").val().trim();
        	if(integral==""){
        		alert("积分不能为空")
  				 e.preventDefault();
        	}else{
	           	var con=confirm("确定添加本商品吗？");
	    			if(con==true){
	    				alert("添加成功")
	    				window.location.href="${pageContext.request.contextPath}/management/addIntegralGoods.action?gtId="+gtId+"&goodsId="+name+"&integral="+integral;
	    			}else{
	    				 e.preventDefault();
	    		}
        	}
         });
        
        $("#submit2").click(function (e) {
        	var integralId=$("#integralId").val();
        	var alterIntegral=$("#alterIntegral").val();
        	if(alterIntegral==""){
        		alert("积分不能为空")
  				 e.preventDefault();
        	}else{
	           	var con=confirm("确定修改本商品的积分吗？");
	    			if(con==true){
	    				alert("修改成功")
	    				window.location.href="${pageContext.request.contextPath}/management/addIntegralGoods.action?gtId="+gtId+"&goodsId="+name+"&integral="+integral;
	    			}else{
	    				 e.preventDefault();
	    		}
        	}
         });
    	 
        
    });
			
</script>
</body>
</html>