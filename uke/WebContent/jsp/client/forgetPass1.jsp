<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>忘记密码</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/client/forgetPass1.css">
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/vue.min.js"></script>
<script src="${pageContext.request.contextPath}/js/vue-resource.js"></script>

</head>
<body>
   <div class="title">
        <div class="container">
            <a id="logo" href="index.html"><img src="${pageContext.request.contextPath}/images/icon/logo.jpg" title="点我返回首页"/></a>
            <span id="text"><h4>忘记密码&emsp;>>&emsp;验证身份</h4></span>
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
                    <img src="${pageContext.request.contextPath}/images/icon/2a.png">
                    <p>设置新密码</p>
                </span>
                <span></span>
                <span>
                    <img src="${pageContext.request.contextPath}/images/icon/打钩a.png">
                    <p>&ensp;&nbsp;完成</p>
                </span>
            </div>
            <div class="panel-body">
                <form role="form" class="form form-inline" action="${pageContext.request.contextPath }/client/checkCode.action" method="post">
                    <table cellpadding="8" cellspacing="18">
                        <tr>
                            <td>手机号码&emsp;&ensp;</td>
                            <td><input type="tel" name="phone" size="26" v-model="phone" class="form-control" required/></td>
                            <td><font class="mess" ref="phoneMess">
			                     <c:choose>
			                		<c:when test="${codeMess!=null}">
			                			   
			                		</c:when>
			                		<c:when test="${phoneMess==null}">
			                			    &emsp;&emsp;&emsp;请输入有效的手机号码
			                		</c:when>
			                		<c:otherwise>
			                			<font style="color:red;">${phoneMess }</font>
			                		</c:otherwise>
			                	</c:choose> 
                         </font></td>
                        </tr>
                        <tr>
                            <td>验证码&emsp;&emsp;&ensp;</td>
                            <td><input type="text" size="8" name="code" v-model="codeNum"
                                       class="form-control" required/>&emsp;&emsp;&ensp;</td>
                            <td><button id="getCode" class="btn btn-info" @click="sendMessages"
                                        ref="getCode">获取验证码</button></td>
                            <td><font class="mess" ref="codeMess" style="color:red">${codeMess }</font></td>
                        </tr>
                        <tr>
                            <td><input id="submit" type="submit" @click="submit" class="btn btn-info" value="下一步"></td>
                        </tr>
                    </table>
						<input type="hidden" name="checkCode" v-model="checkCode"/>
                </form>
            </div>
        </div>
    </div>
    <div class="footer">
        <p>copyright by gjguke.top</p>
        <p style="margin-top:-30px;">粤ICP备18138200号-1</p>
    </div>
    
    <script >
    var vm=new Vue({
        el:"#content",
        data:{
            phone:'${phone}',
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
            submit(e){
                var check=true;

                check=check&&this.checkIsNull(this.phone,this.$refs.phoneMess,"手机号码不能为空");
                check=check&&this.checkReg(this.phone,/^1(3[0-9]|5[189]|8[6789])[0-9]{8}$/,
                    "手机号格式不准确",this.$refs.phoneMess);

                check=check&&this.checkIsNull(this.codeNum,this.$refs.codeMess,"验证码不能为空");


                if(check){
                    window.location.href="${pageContext.request.contextPath }/client/checkCode.action";
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
                    this.$http.post('${pageContext.request.contextPath}/client/verification.action',this.phone,{
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