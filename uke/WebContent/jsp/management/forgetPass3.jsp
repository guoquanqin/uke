<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改成功</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/management/forgetPass3.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/animate.min.css"/>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/vue.js"></script>
<script src="${pageContext.request.contextPath}/js/vue-resource.js"></script>
</head>
<body>
 <div class="title">
        <div class="container">
            <a id="logo" href="${pageContext.request.contextPath}/management/home.action"><img src="${pageContext.request.contextPath}/images/icon/logo.jpg" title="点我返回首页"/></a>
            <span id="text"><h4>忘记密码&emsp;>>&emsp;验证身份</h4></span>
            <span id="login"><a href="${pageContext.request.contextPath}/jsp/management/login.jsp">登录</a> 
            | <a href="${pageContext.request.contextPath}/management/home.action">管理员首页</a></span>
        </div>
    </div>
    <div  class="container">
        <div class="panel">
            <div class="panel-heading">
                <span>
                    <img src="${pageContext.request.contextPath}/images/icon/1.png">
                    <p>验证身份 </p>
                </span>
                <span></span>
                <span>
                    <img src="${pageContext.request.contextPath}/images/icon/2.png">
                    <p>设置新密码</p>
                </span>
                <span></span>
                <span>
                    <img src="${pageContext.request.contextPath}/images/icon/打钩.png">
                    <p>&ensp;&nbsp;完成</p>
                </span>
            </div>
            <div class="panel-body">
                <div id="content">
                    <span><img src="${pageContext.request.contextPath}/images/icon/修改成功.png"></span>
                    <span>
                        <p>修改密码成功</p>
                        <a href="${pageContext.request.contextPath}/jsp/management/login.jsp">返回登录页面</a>
                    </span>
                </div>
            </div>
        </div>
    </div>
    <div class="footer">
        <p>copyright by gjguke.top</p>
        <p style="margin-top:-20px;">备案号：粤ICP备18138200号-1</p>
    </div>
</body>
</html>