<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员登录</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/management/login.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/animate.min.css"/>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/vue.js"></script>
<script src="${pageContext.request.contextPath}/js/vue-resource.js"></script>
</head>
<body>
<div class="box">
    <div class="container">
        <div class="panel panel-title">
            <img src="${pageContext.request.contextPath}/images/icon/用户c.jpg">
        </div>
        <div class="panel panel-body">
            <form action="${pageContext.request.contextPath}/management/login.action" method="post" role="form" class="form-inline">
                <label for="username">
                    <img src="${pageContext.request.contextPath}/images/icon/用户.png">
                    <input type="text" class="form-control" v-model="username" name="managerPhone" id="username" size="26" placeholder="管理员手机号">
                </label>
                <label for="password">
                    <img src="${pageContext.request.contextPath}/images/icon/密码.png">
                    <input type="password" class="form-control" name="managerPassword" v-model="password" id="password" size="26" placeholder="密码">
              		  <p style="color:red;margin:20px 0 0 20px;">${requestScope.mess }</p>
                </label>
                <a href="${pageContext.request.contextPath}/jsp/management/forgetPass1.jsp">忘记密码</a>
                <input type="submit" id="submit" @click="submit" class="btn btn-primary" value="登录" >
                <%-- 还没有账号？<a href="${pageContext.request.contextPath}/management/register.jsp">免费注册</a> --%>
            </form>
        </div>

    </div>

</div>

<script>
    var vm=new Vue({
        el:'.container',
        data:{
            username:'',
            password:''
        },
        methods:{
            submit(e){
                if (this.username==''){
                    alert("账号不能为空")
                    e.preventDefault();
                }
                if (this.password==''){
                    alert("密码不能为空")
                    e.preventDefault();
                }
            }
        }
    })
</script>
</body>
</html>