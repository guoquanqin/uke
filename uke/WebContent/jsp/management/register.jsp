<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员注册</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/management/register.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/animate.min.css"/>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/vue.js"></script>
<script src="${pageContext.request.contextPath}/js/vue-resource.js"></script>
</head>
<body>
 <div class="title">
        <div class="container">
        <a id="logo" href="${pageContext.request.contextPath}/management/home.action"><img src="${pageContext.request.contextPath}/images/icon/logo.jpg" title="点我返回首页"/></a>
        <span id="text"><h4>管理员注册</h4></span>
        <span id="login">已有账号，请&ensp;&ensp;<a href="${pageContext.request.contextPath}/jsp/management/login.jsp" class="btn btn-info">登录</a></span>
        </div>
    </div>
    <div id="container" class="container">
        <form class="form form-inline" role="form" action="${pageContext.request.contextPath}/management/registerMana.action" method="post">
            <h4>注册账号</h4>
            <label>
                <span style="color:red">*</span>&ensp;真实姓名
                <input type="text" name="managerName" v-model="username" class="form-control"  required minlength="2"/>
                <font ref="nameMess">
                	<c:choose>
                		<c:when test="${nameMess!=null}">
                			<font style="color:red;">${nameMess }</font>
                		</c:when>
                	</c:choose>
                </font>
            </label>
            <label>
                <span style="color:red">*</span>&ensp;密码&ensp;&ensp;&ensp;&ensp;
                <input type="password" name="managerPassword" v-model="password" class="form-control" required minlength="8" maxlength="16"/>
                <font ref="passMess">请输入8到16位有效字符</font>
            </label>
            <label>
                <span style="color:red">*</span>&ensp;确认密码
                <input type="password" @focus="checkpd" @blur="checkpd" v-model="confirm_password"  class="form-control" required />
                <font ref="confirmpassMess"></font>
            </label>
            <label>
                <span style="color:red">*</span>&ensp;手机号码
                <input type="tel" name="managerPhone" v-model="phone" class="form-control" required />
                <font ref="phoneMess">
        			<c:choose>
                		<c:when test="${phoneMess==null}">
                			    请输入有效的手机号码
                		</c:when>
                		<c:otherwise>
                			<font style="color:red;">${phoneMess }</font>
                		</c:otherwise>
                	</c:choose>         
               </font>
            </label>
            <label>
                <span style="color:red">*</span>&ensp;验证码&ensp;&ensp;
                <input type="text" name="code" v-model="codeNum" size="10" class="form-control" required />
                <span><button class="btn btn-info" @click="sendMessages" ref="getCode">点击获取验证码</button>
                    <font ref="codeMess"style="color:red">${codeMess }</font></span>
                    <input type="hidden" name="checkCode" v-model="checkCode" />
            </label>
            <label>
                <input id="submit" class="btn btn-info" @click="submit" @keyup.enter="submit" type="submit" >
            </label>
        </form>
    </div>
    <div class="footer">
        <p>copyright by yku.com</p>
    </div>
<script>
    var vm=new Vue({
        el:"#container",
        data:{
            username:'${manaInfo.name}',
            password:'${manaInfo.password}',
            confirm_password:'${manaInfo.password}',
            phone:'${manaInfo.phone}',
            codeNum:'',
            checkCode:'',
            InterValObj:'', //timer变量，控制时间
            code:'',
            curCount:'', //当前剩余秒数
            count:60, //间隔函数，1秒执行
        },
        methods:{
            /* 检查格式*/
            checkReg(o,reg,n,r){
                if(!(reg.test(o))) {
                    r.style.color='red';
                    r.innerText=n;
                    return false;
                }
                else{
                        r.innerText='';
                        return true;
                    }
                },
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
                check=check&&this.checkIsNull(this.username,this.$refs.nameMess,"用户名不能为空");


                check=check&&this.checkIsNull(this.password,this.$refs.passMess,"密码不能为空");
                check=check&&this.checkLength(this.password,"密码",8,16,this.$refs.passMess);
                check=check&&this.checkIsNull(this.confirm_password,this.$refs.confirmpassMess,"确认密码不能为空");
                check=check&&this.checkpd();

                check=check&&this.checkIsNull(this.phone,this.$refs.phoneMess,"手机号码不能为空");
                check=check&&this.checkReg(this.phone,/^1(3[0-9]|5[189]|8[6789])[0-9]{8}$/,
                    "手机号格式不准确",this.$refs.phoneMess);

                check=check&&this.checkIsNull(this.codeNum,this.$refs.codeMess,"验证码不能为空");


                if(check){
                    window.location.href="${pageContext.request.contextPath}/management/registerMana.action";
                }
                else
                    e.preventDefault();
            },
            //timer处理函数
            SetRemainTimes() {
                if(this.curCount == 0) {
                    window.clearInterval(this.InterValObj); //停止计时器
                    this.$refs.getCode.disabled=false //启用按钮
                    this.$refs.getCode.innerText="重新发送验证码";
                    this.code = ""; //清除验证码。如果不清除，过时间后，输入收到的验证码依然有效
                } else {
                    this.curCount--;
                    this.$refs.getCode.innerText="请在" + this.curCount + "秒内输入";
                }
            },
            sendMessages() {
                this.curCount = this.count;
                var check=true;
                check=check&&this.checkIsNull(this.phone,this.$refs.phoneMess,"手机号码不能为空");
                check=check&&this.checkReg(this.phone,/^1(3[0-9]|5[189]|8[6789])[0-9]{8}$/,
                    "手机号格式不准确",this.$refs.phoneMess);
                if(check) {
                    //设置button效果，开始计时
                    this.$refs.getCode.disabled=true;
                    this.$refs.getCode.innerText="请在" + this.curCount + "秒内输入";
                    this.InterValObj = window.setInterval(this.SetRemainTimes, 1000); //启动计时器，1秒执行一次
                    this.$http.post('${pageContext.request.contextPath}/management/verification.action',this.phone,{
                    	'headers':{
    						'Content-Type':'application/json;charset=UTF-8'
    						}
                    }).then(function(data){
                    	this.checkCode=data.body
                    });
                }
            },
        }
    })


</script>
</body>
</html>