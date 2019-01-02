<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改资料</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/management/alterManaInfo.css"/>
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
                    <img src="${pageContext.request.contextPath}/images/icon/修改资料.png">
                    <p>修改资料</p>
                </div>
                <div id="info" class="form-inline">
                    <label>
                        <img src="${pageContext.request.contextPath}/images/管理员头像/${MANAGER.managerPhoto}"/>
                        <span>注册时间：<fmt:formatDate value="${MANAGER.registerTime }" pattern="yyyy-MM-dd"/></span>
                        <span>上次登录时间：<fmt:formatDate value="${MANAGER.loginTime }" pattern="yyyy-MM-dd"/></span>
                    </label>
                    <form action="${pageContext.request.contextPath}/management/alterManaName.action" method="post">
                        <h4>修改用户名</h4>
                        <span>新用户名：<input type="text" size="10" name="newName" class="form-control" required/></span>
                        <span>验证密码：<input type="pasword" size="17" name="password" class="form-control" required/> <font id="mess1">${mess1 }</font></span>
                        <span><input type="submit" id="submit1" class="btn btn-info" value="提交"/></span>
                    </form>
                    <form action="${pageContext.request.contextPath}/management/alterManaPhone.action" method="post">
                        <h4>修改手机号码</h4>
                        <span>原始手机号码：<input type="text" id="oldPhone" name="oldPhone" size="15" class="form-control" value="${param.oldPhone }" required/><font id="mess2">${mess2 }</font></span>
                        <span>新手机号码：<input type="text" id="newPhone" name="newPhone" size="15" class="form-control" value="${param.newPhone }" required/><font id="mess3">${mess3}</font></span>
                        <span>验证码：<input type="text" size="7" id="code" name="code" class="form-control" required/><button id="getCode" class="btn btn-info">获取验证码</button>
                            <font  id="mess4">${mess4 }</font>
                            <input type="hidden" value="" id="realCode" name="realCode" />
                            </span>
                        <span><input type="submit" id="submit2" class="btn btn-info" value="提交"/></span>
                    </form>
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
                    <a href="${pageContext.request.contextPath}/jsp/management/alterManaInfo.jsp"><img src="${pageContext.request.contextPath}/images/icon/刷新.png" title="刷新数据"></a>
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
					window.location.href="${pageContext.request.contextPath}/jsp/management/alterManaInfo.jsp";
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
         $("#submit2").click(function (e) {
             var check = true;
             check = check && checkIsNull($("#oldPhone").val(), $("#mess2"), "手机号码不能为空");
             check = check && checkReg($("#oldPhone").val(), /^1(3[0-9]|5[189]|8[6789])[0-9]{8}$/,
                 "手机号格式不准确", $("#mess2"));
             check = check && checkIsNull($("#newPhone").val(), $("#mess3"), "手机号码不能为空");
             check = check && checkReg($("#newPhone").val(), /^1(3[0-9]|5[189]|8[6789])[0-9]{8}$/,
                 "手机号格式不准确", $("#mess3"));
             check = check && checkIsNull($("#code").val(), $("#mess4"), "验证码不能为空");

             if (check) {
                 var con=confirm("确定要修改该用户的手机号吗")
                 if(con){

                 }
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
             check = check && checkIsNull($("#newPhone").val(), $("#mess3"), "手机号码不能为空");
             check=check&&checkReg($("#newPhone").val(),/^1(3[0-9]|5[189]|8[6789])[0-9]{8}$/,
                 "手机号格式不准确",$("#mess3"));
             if(check) {
            	 
                 //设置button效果，开始计时
                 $("#getCode").attr("disabled","disabled") ;
                 $("#getCode").text("请在" + curCount + "秒内输入");
                 $.ajax({
 					url:"${pageContext.request.contextPath}/management/verification.action",
 					data:$("#oldPhone").val(),
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