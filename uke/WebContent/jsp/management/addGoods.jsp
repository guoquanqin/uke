<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加商品</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/management/addGoods.css"/>
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
                    <img src="${pageContext.request.contextPath}/images/icon/商品.png">
                    <p>添加商品</p>
                </div>
                <form role="form" class="form-inline" action="${pageContext.request.contextPath}/management/addGoods.action" method="post" enctype="multipart/form-data">
                    <label>
                        <span><font style="color: red;">*</font>商品名称</span>
                        <input type="text" id="name" name="goodsName" class="form-control"/>
                        <c:if test="${mess!=null }">
	                        <font class="mess1" style="color:red;">商品名字已存在，请重新输入</font>
                        </c:if>
                        <c:if test="${mess==null }">
	                        <font class="mess1" style="color:red;"></font>
                        </c:if>
                    </label>
                    <label>
                        <span><font style="color: red;">*</font>商品分类</span>
                        <select class="form-control" name="goodsType">
                        	<c:forEach items="${allGoodsType }" var="i">
                        		<c:if test="${i.gtName==type.gtName }">
	                            	<option value="${i.gtId }" selected>${i.gtName }</option>
	                            </c:if>
                        		<c:if test="${i.gtName!=type.gtName }">
	                            	<option value="${i.gtId }">${i.gtName }</option>
	                            </c:if>
                        	</c:forEach>
                        </select>
                    </label>
                    <label>
                        <span>上传图片</span>
                        <span>
                            <img id="pic1" for="file" src="${pageContext.request.contextPath}/images/icon/添加图片.png" />
                             <img id="pic" src="${pageContext.request.contextPath}/images/用户头像/头像.jpg" />
                            <input id="file" type="file" onchange="changepic(this)"  accept="image/png,image/jpeg,image/jpg" name="pictrueFile" class="inputfile"/>
                        </span>
                    </label>
                    <label>
                        <span>商品规格</span>
                        <span>
                            <select id="size" class="form-control" name="size">
                            	<option value="0" selected>无</option>
                                <option value="1" >大/中杯</option>
                            </select>
                        </span>
                    </label>
                    <label >
                        <span>商品价格</span>
	                        <span class="input-group" id="span2">
                            	<div><span class="input-group-addon">￥</span><input type="number" min="1"  name="price1" id="price" class="form-control" value="1" style="width: 120px;" required /></div>
                            	<input type="hidden" value="0" name="price2"/>
                        	</span>
                        	<span class="input-group" id="span1"  style="display:none;">
	                            <div><font>大&emsp;&emsp;</font><span class="input-group-addon">￥</span><input type="number" min="1" name="price1" id="price1" value="1" style="width: 120px;" class="form-control" required/></div>
	                            <div><font>中&emsp;&emsp;</font><span class="input-group-addon">￥</span><input type="number" min="1" name="price2" id="price2" value="1" style="width: 120px;" class="form-control" required/></div>
                       		</span>
                    </label>
                    <label>
                        <span id="describe">商品描述</span>
                        <span>
                        	<textarea cols="35" rows="3" name="describe" placeholder="介绍一下你的产品吧，50字以内就可以哦~"></textarea>
                        </span>
                    </label>
                    <label>
                         <input type="submit" id="submit" class="btn btn-info" value="提交"/>
                    </label>
                </form>
                
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
                    <a href="${pageContext.request.contextPath}/management/goodsSet1.action?goodsType=1"><img src="${pageContext.request.contextPath}/images/icon/刷新.png" title="刷新数据"></a>
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

<script>

	function getTimeOrder(){
		$.ajax({
			url:"${pageContext.request.contextPath}/management/getAsyncInfo.action",
			method:"post",
			success:function(data){
				if(data=='1'){
					console.log(data);
				}else{
					window.location.href="${pageContext.request.contextPath}/management/toAddGoods.action";
					console.log(data);
				}
				getTimeOrder();
			}
		});
	}
	function changepic() {
	    var reads= new FileReader();
	    f=document.getElementById('file').files[0];
	    reads.readAsDataURL(f);
	    reads.onload=function (e) {
	        document.getElementById('pic').src=this.result;
	    };
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
    	
    	 $("#size").click(function () {
             if($("#size").val()=="0"){
                 $("#span2").css("display","block");
                 $("#span1").css("display","none");
                 
             }else{
                 $("#span1").css("display","block");
                 $("#span2").css("display","none");
             }
         });

         $("#submit").click(function (e) {
             var name=$("#name").val().length;
             if(name>10){
                 $(".mess1").text("商品名称字数不可以超过10个")
                 e.preventDefault();
             }if(name==0){
                 $(".mess1").text("商品名称字数不可以为空")
                 e.preventDefault();
             }
             else{
           		var con=confirm("确定添加商品吗？");
    			if(con==true){
    				alert("添加成功")
    				window.location.href="${pageContext.request.contextPath}/management/addGoods.action";
    			}else{
    				 e.preventDefault();
    			}
             }
         });
        
    });
			
</script>
</body>
</html>