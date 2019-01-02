<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/management/personalMessage.css"/>
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
                    <img src="${pageContext.request.contextPath}/images/icon/个人资料.png">
                    <p>个人信息</p>
                </div>
                <div id="info" class="form-inline">
                    <label>
                        <img id="head" src="${pageContext.request.contextPath}/images/管理员头像/${MANAGER.managerPhoto}"/>
                        <form id="picForm" action="${pageContext.request.contextPath}/management/personalMessage.action" method="post" enctype="multipart/form-data">
		                    <input type="file" onchange="changepic(this)"  accept="image/png,image/jpeg,image/jpg" name="pictrueFile" id="file" class="inputfile"/></label>
		                    <input id="picSubmit" type="submit" class="btn btn-info" value="保存头像"/>
		                    <font id="picMess" style="color:red;">${picMess }</font>
                    	</form>
                    </label>
                    <label>
                      	  用户姓名：<span>${MANAGER.managerName }</span>
                    </label>
                    <label>
                                                  手机号码：<span>${MANAGER.managerPhone }</span>
                    </label>
                    <label>
                        	注册时间：<span><fmt:formatDate value="${MANAGER.registerTime }" pattern="yyyy-MM-dd"/></span>
                    </label>
                    <label>
                        	上次登录时间：<span><fmt:formatDate value="${MANAGER.loginTime }" pattern="yyyy-MM-dd"/></span>
                    </label>
                </div>
                <div id="alter">
                    <label for="file" id="changehead">修改</label>
                    
                    <a href="${pageContext.request.contextPath}/jsp/management/alterManaInfo.jsp">修改</a>
                    <a href="${pageContext.request.contextPath}/jsp/management/alterManaInfo.jsp">修改</a>
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
                    <a href="${pageContext.request.contextPath}/jsp/management/personalMessage.jsp"><img src="${pageContext.request.contextPath}/images/icon/刷新.png" title="刷新数据"></a>
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
					window.location.href="${pageContext.request.contextPath}/jsp/management/personalMessage.jsp";
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
	        document.getElementById('head').src=this.result;
	        document.getElementById('picSubmit').style.opacity=1;
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
    	 
    	 /* 检查格式*/
         function checkReg(o,reg,n,r){
             if(!(reg.test(o))) {
                 r.css("color","red");
                 r.text(n);
                 return false;
             }
             else{
                 r.innerText='';
                 return true;
             }
         };
         /* 确认密码是否一致 */
         function checkpd(){
             if($("#password").val()!=$("#confirmPass").val()){
                 $("#mess5").css("color","red");
                 $("#mess5").text('密码不一致，请重新输入');
                 return false;
             }
             else
             {
                 $("#mess5").text('');
                 return true;
             }
         };
         /*检查不能为空 */
         function checkIsNull(value,mess,text){
             if(value==""){
                 mess.css("color","red");
                 mess.text(text);
                 return false;
             }
             else {
                 mess.text('');
                 return true;
             }
         };
         /* 检查长度*/
         function checkLength(o,n,min,max,r){
             if(o.length>max||o.length<min){
                 r.css("color","red");
                 r.text(n+"的长度在"+min+"和"+max+"之间");
                 return false;
             }
             else{
                 r.text('');
                 return true;
             }
         };
         $("#submit").click(function (e) {
             var check = true;
             check = check && checkIsNull($("#name").val(), $("#mess1"), "用户名不能为空");
             check = check && checkLength($("#name").val(), "用户名", 2, 10, $("#mess1"));

             check = check && checkIsNull($("#phone").val(), $("#mess2"), "手机号码不能为空");
             check = check && checkReg($("#phone").val(), /^1(3[0-9]|5[189]|8[6789])[0-9]{8}$/,
                 "手机号格式不准确", $("#mess2"));
             check = check && checkIsNull($("#code").val(), $("#mess3"), "验证码不能为空");

             check = check && checkIsNull($("#password").val(), $("#mess4"), "密码不能为空");
             check = check && checkLength($("#password").val(), "密码", 8, 16, $("#mess4"));
             check = check && checkIsNull($("#confirmPass").val()   , $("#mess5"), "确认密码不能为空");
             check = check && checkpd();

             if (check) {
                 alert("提交成功");
                 window.location.href = "#";
             }
             else
                 e.preventDefault();
         });

         var count=60;
         var curCount='';
         var InterValObj='';
         //timer处理函数
         function SetRemainTimes() {
             if(curCount == 0) {
                 window.clearInterval(InterValObj); //停止计时器
                 $("#getCode").removeAttr("disabled");//启用按钮
                 $("#getCode").text("重新发送验证码");
                 $("#code").text(''); //清除验证码。如果不清除，过时间后，输入收到的验证码依然有效
             } else {
                 curCount--;
                 $("#getCode").text("请在" + curCount + "秒内输入");
             }
         };
         $("#getCode").click(function(){

             curCount = count;
             var check=true;
             check = check && checkIsNull($("#phone").val(), $("#mess2"), "手机号码不能为空");
             check=check&&checkReg($("#phone").val(),/^1(3[0-9]|5[189]|8[6789])[0-9]{8}$/,
                 "手机号格式不准确",$("#mess2"));
             if(check) {
            	 
                 //设置button效果，开始计时
                 $("#getCode").attr("disabled","disabled") ;
                 $("#getCode").text("请在" + curCount + "秒内输入");
                 $.ajax({
 					url:"${pageContext.request.contextPath}/management/verification.action",
 					data:$("#phone").val(),
 					contentType:"application/json;charset=UTF-8",
 					type:"post",
 					dataType:"json",  //回调数据类型
 					success:function(data){
 						$("#realCode").val(data);
 					}
 				});
                 
                 InterValObj = window.setInterval(SetRemainTimes, 1000); //启动计时器，1秒执行一次

             }
         });
    });
			
</script>
</body>
</html>