<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册成功</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/client/forgetPass3.css">
<script src="${pageContext.request.contextPath}/js/vue.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
</head>
<body>
   <div class="title">
        <div class="container">
            <a id="logo" href="${pageContext.request.contextPath}/client/home.action"><img src="${pageContext.request.contextPath}/images/icon/logo.jpg" title="点我返回首页"/></a>
            <span id="text"><h4>欢迎光临 优客站</h4></span>
            <span id="login"><a href="${pageContext.request.contextPath }/jsp/client/login.jsp">登录</a> | <a href="${pageContext.request.contextPath }/jsp/client/register.jsp">注册</a>
            | <a href="${pageContext.request.contextPath}/client/home.action">优客站首页</a></span>
        </div>
    </div>
    <div  class="container">
        <div class="panel">
            <div class="panel-body">
                <div id="content">
                    <span><img src="${pageContext.request.contextPath}/images/icon/修改成功.png"></span>
                    <span>
                        <p>注册成功</p>
                        <a href="${pageContext.request.contextPath }/jsp/client/login.jsp">返回登录页面</a>
                    </span>
                </div>
            </div>
        </div>
    </div>
    <div class="footer">
        <p>copyright by gjguke.top</p>
        <p style="margin-top:-30px;">粤ICP备18138200号-1</p>
    </div>

</body>
</html>