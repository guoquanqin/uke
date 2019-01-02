<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>忘记密码</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/client/forgetPass2.css">
<script src="${pageContext.request.contextPath}/js/vue.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
</head>
<body>
  <div class="title">
        <div class="container">
            <a id="logo" href="index.html"><img src="${pageContext.request.contextPath}/images/icon/logo.jpg" title="点我返回首页"/></a>
            <span id="text"><h4>忘记密码&emsp;>>&emsp;设置新密码</h4></span>
            <span id="login"><a href="${pageContext.request.contextPath}/jsp/client/login.jsp">登录</a> | <a href="${pageContext.request.contextPath}/jsp/client/register.jsp">注册</a>
            | <a href="${pageContext.request.contextPath}/client/home.action">优客站首页</a></span>
        </div>
    </div>
    <div id="content" class="container">
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
                    <img src="${pageContext.request.contextPath}/images/icon/打钩a.png">
                    <p>&ensp;&nbsp;完成</p>
                </span>
            </div>
            <div class="panel-body">
                <form role="form" class="form form-inline" action="${pageContext.request.contextPath}/client/setNewPassword.action" method="post">
                    
                    <table cellpadding="8" cellspacing="18">
                        <tr>
                            <td>设置新密码&emsp;&ensp;</td>
                            <td><input type="password" name="password" v-model="password"  class="form-control" required/></td>
                            <td><font class="mess" ref="passMess">请输入8到16位有效字符</font></td>
                        </tr>
                        <tr>
                            <td>重复密码&emsp;&emsp;&ensp;</td>
                            <td><input type="password"  @focus="checkpd" @blur="checkpd" v-model="confirm_password"
                                       class="form-control" required/>&emsp;&emsp;&ensp;</td>
                            <td><font class="mess" ref="confirmpassMess"></font></td>
                            <input type="hidden" name="phone" v-model="phone"/>
                        </tr>
                        <tr>
                            <td><input id="submit" @click="submit" type="submit" class="btn btn-info"></td>
                        </tr>
                    </table>
					
                </div>
            </div>
        </div>
    </div>
    <div class="footer">
        <p>copyright by gjguke.top</p>
        <p style="margin-top:-30px;">粤ICP备18138200号-1</p>
    </div>
    
    <script>
    var vm=new Vue({
        el:"#content",
        data:{
        	phone:'${phone}',
            password:'',
            confirm_password:'',
        },
        methods:{
            /* 确认密码是否一致 */
            checkpd(){
                if(this.password!=this.confirm_password){
                    this.$refs.confirmpassMess.style.color='red';
                    this.$refs.confirmpassMess.innerText='密码不一致，请重新输入';
                    return false;
                }
                else
                {
                    this.$refs.confirmpassMess.innerText='';
                    return true;
                }
            },
            /*检查不能为空 */
            checkIsNull(value,mess,text){
                if(value==""){
                    mess.style.color='red';
                    mess.innerText=text;
                    return false;
                }
                else {
                    mess.innerText='';
                    return true;
                }
            },
            /* 检查长度*/
            checkLength(o,n,min,max,r){
                if(o.length>max||o.length<min){
                    r.style.color='red';
                    r.innerText=""+n+"的长度在"+min+"和"+max+"之间";
                    return false;
                }
                else{
                    r.innerText='';
                    return true;
                }
            },
            submit(e){
                var check=true;

                check=check&&this.checkIsNull(this.password,this.$refs.passMess,"密码不能为空");
                check=check&&this.checkLength(this.password,"密码",8,16,this.$refs.passMess);
                check=check&&this.checkIsNull(this.confirm_password,this.$refs.confirmpassMess,"确认密码不能为空");
                check=check&&this.checkpd();
                if(check){
                    alert("提交成功");
                    window.location.href="${pageContext.request.contextPath}/client/setNewPassword.action";
                }
                else
                    e.preventDefault();
            },
        }
    })


    </script>
</body>
</html>