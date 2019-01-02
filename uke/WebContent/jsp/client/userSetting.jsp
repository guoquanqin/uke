<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>设置中心</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/client/userSetting.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/client/sign.css"/>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.fly.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/vue.js"></script>
<script src="${pageContext.request.contextPath}/js/vue-resource.js"></script>
<script src="${pageContext.request.contextPath}/js/calendar.js"></script>
</head>
<body>        
<c:set value="${userinfo }" var="i"></c:set>
<nav id="navbar" class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <p class="navbar-text">您好，${i.userName } </p>
        <ul  class="nav navbar-nav" @mouseover="flag=true" @mouseout="flag=false">
            <li ><a href="${pageContext.request.contextPath}/client/userCenter/userHome.action"> 个人中心<span class="caret"></span></a></li>
            <transition mode="out-in">
                <ul id="navlist" @mouseover="flag=true" v-if="flag" style="display: block">
                    <li><a href="${pageContext.request.contextPath}/client/userCenter/userAllOrder.action?pageNum=1">我的订单</a> </li>
                    <li><a href="${pageContext.request.contextPath}/client/userCenter/userCollect.action">我的收藏</a> </li>
                    <li><a href="${pageContext.request.contextPath}/client/userCenter/userComment.action?pageNum=1">我的评价</a> </li>
                    <li><a href="${pageContext.request.contextPath}/client/userCenter/userSetting.action">账号设置</a> </li>
                    <li><a href="${pageContext.request.contextPath}/client/userCenter/userBasket.action">购物车</a> </li>
                </ul>
            </transition>
        </ul>

        <div class="navbar-right">
            <ul class="nav navbar-nav" >
                 <li><a href="${pageContext.request.contextPath}/client/home.action">首页</a> </li>
                <li><a href="${pageContext.request.contextPath}/jsp/client/register.jsp">注册账号</a> </li>
                <li><a href="${pageContext.request.contextPath}/client/exit.action">退出登录</a> </li>
                <li><a href="#" data-toggle="modal" data-target="#aboutUs">关于我们</a> </li>
            </ul>
        </div>
        <div class="modal fade" role="dialog" id="aboutUs">
             <div class="modal-dialog" role="document">
                 <div class="modal-content">
                     <div class="modal-header">
                         <h4>
                             关于我们
                             <button class="close" data-dismiss="modal">&times;</button>
                         </h4>
                     </div>

                     <div class="modal-body">
                         <p>优客站奶茶店网上订餐系统</p>
                         <p>Copyright by gjguke.top</p>
                         <p>备案号：粤ICP备18138200号-1</p>
                     </div>
                     <div class="modal-footer">
                         <button class="btn btn-primary" data-dismiss="modal">关闭</button>
                     </div>
                 </div>
             </div>
         </div>
    </div>
</nav>

<div class="content">
    <div class="container">
        <div class="leftsidebar">
            <div id="userInfo">
                <div id="user">
                    <a href="${pageContext.request.contextPath}/client/userCenter/userSetting.action"><img id="userHead" src="${pageContext.request.contextPath}/images/用户头像/${i.userPhoto}"></a>
                    <span id="signbtn" data-toggle="modal" data-target="#signin" @click="getSign"><p>签到</p></span>
                    <div class="modal fade" role="dialog" id="signin">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h4>
                                        签到中心
                                        <button class="btn btn-info" @click="sign">签到</button>
                                        <button class="close" data-dismiss="modal">&times;</button>
                                    </h4>
                                </div>

                                <div class="modal-body">
                                    <div  id="calendar"></div>

                                    <div id="sign_note">
                                        <p style="color:red">*规则：本月签到20天即可领取积分</p>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button class="btn btn-primary" data-dismiss="modal">关闭</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="userbox">
                        <span>上次登录：<fmt:formatDate value="${i.lastLoginTime }" pattern="yyyy-MM-dd"/></span>
                        <span>我的积分：<font style="color:red;">${i.userIntegral }</font> </span>
                        <span><a href="${pageContext.request.contextPath}/client/userCenter/userAllOrder.action?pageNum=1"><img src="${pageContext.request.contextPath}/images/icon/订单.png">历史订单</a></span>
                        <span><a href="${pageContext.request.contextPath}/client/userCenter/userSetting.action"><img src="${pageContext.request.contextPath}/images/icon/用户a.png">用户中心</a></span>
                    </div>
                </div>
            </div>
            <div id="list">
                <div class="listhead">
                    <span><img src="${pageContext.request.contextPath}/images/icon/用户b.png">个人中心</span>
                </div>
                <div class="listcontent">
                    <ul>
                    	<li><a href="${pageContext.request.contextPath}/client/userCenter/userAllOrder.action?pageNum=1" >商品订单</a> </li>
                        <li><a href="${pageContext.request.contextPath}/client/userCenter/userAllIntegralOrder.action?pageNum=1">积分订单</a></li>
                        <li><a href="${pageContext.request.contextPath}/client/userCenter/userBasket.action">我的购物车</a> </li>
                        <li><a href="${pageContext.request.contextPath}/client/userCenter/userCollect.action">我的收藏夹</a> </li>
                        <li><a href="${pageContext.request.contextPath}/client/userCenter/userComment.action?pageNum=1">我的评价</a> </li>
                        <li><a href="${pageContext.request.contextPath}/client/userCenter/userIntegral.action">我的积分</a> </li>
                    </ul>
                </div>
            </div>

        </div>
        <div class="rightsidebar">
        	 <div class="topbar">
                <div>
                    <h4>我的yku账号</h4>
                    <img id="head" src="${pageContext.request.contextPath}/images/用户头像/${i.userPhoto}" />
                    <p>${i.userName }</p>
                    <form id="picForm" action="${pageContext.request.contextPath}/client/userCenter/userSavePicture.action" method="post" enctype="multipart/form-data">
	                    <label for="file"><img id="changehead"  src="${pageContext.request.contextPath}/images/icon/修改头像.png" title="修改头像">
	                    <input type="file" onchange="changepic(this)"  accept="image/png,image/jpeg,image/jpg" name="pictrueFile" id="file" class="inputfile"/></label>
	                    <input id="picSubmit" type="submit" class="btn btn-info" value="保存头像"/>
	                    <font id="picMess" style="color:red;">${picMess }</font>
                    </form>
                </div>
            </div>
            <div class="bottombar">
                <div class="part1">
                    <ul>
                        <li>个人信息<span class="edit"><a href="${pageContext.request.contextPath}/client/userCenter/userSettingInfo.action"><img src="${pageContext.request.contextPath}/images/icon/编辑.png"/>编辑</a></span></li>
                        <li><div>用户名</div>${i.userName }</li>
                        <li><div>性别</div>
                        <c:if test="${i.userSex==1 }">
                       		 男
                        </c:if>
                        <c:if test="${i.userSex==0 }">
                       		 女
                        </c:if></li>
                        <li><div>电子邮箱</div><span ref="email">${i.userEmail }</span></li>
                        <li><div>注册时间</div><fmt:formatDate value="${i.registerTime }" pattern="yyyy-MM-dd HH:mm:ss"/></li>
                    </ul>
                </div>
                <div class="part2">
                    <ul>
                        <li>收货地址<span class="edit"><a href="${pageContext.request.contextPath}/client/userCenter/userSettingAddress.action"><img src="${pageContext.request.contextPath}/images/icon/编辑.png"/>编辑</a></span></li>
                        <li><div>地址1</div>${i.userAddress }</li>
                        <li><div>地址2</div>${i.userAddress2 }</li>
                        <li><div>地址3</div>${i.userAddress3 }</li>
                    </ul>
                </div>
                <div class="part3">
                    <ul>
                        <li>安全中心</li>
                        <li><div>手机号码</div><span ref="phone">${i.userPhone }</span><a href="${pageContext.request.contextPath}/client/userCenter/userSettingPhone.action" style="margin-left: 50px;"><img src="${pageContext.request.contextPath}/images/icon/编辑.png"/>修改</a> </li>
                        <li><div>密码设置</div><a href="${pageContext.request.contextPath}/client/userCenter/userSettingPass.action"><img src="${pageContext.request.contextPath}/images/icon/编辑.png"/>修改</a> </li>
                    </ul>
                </div>
                </div>
        </div>
    </div>
</div>
<div class="footer">
    <p>copyright by gjguke.top</p>
    <p style="margin-top:-30px;">粤ICP备18138200号-1</p>
</div>
<script type="text/javascript">
function changepic() {
    var reads= new FileReader();
    f=document.getElementById('file').files[0];
    reads.readAsDataURL(f);
    reads.onload=function (e) {
        document.getElementById('head').src=this.result;
        document.getElementById('picSubmit').style.opacity=1;
    };
}
</script>
<script>
    var vm=new Vue({
        el:'#navbar',
        data:{
            flag:false,
        },
    })

    var vm2=new Vue({
        el:'.content',
        data: {
            signList: [],
            email:'',
            phone:'',
			
        },
        methods: {
            getSign() {
                calUtil.init(this.signList);
            },
            sign(){
                var myDate=new Date();
                var today=myDate.getDate();
                
                this.$http.post('${pageContext.request.contextPath}/client/userCenter/sign.action',today,{
	             	'headers':{
							'Content-Type':'application/json;charset=UTF-8'
							}
	             }).then(function(data){
	            	 var str=data.body;
	            	 if(str=="1"){
		             	 alert("今日已签到"); 
	            	 }else if(str=="2"){
	            		 this.signList.push({"signDay":today})
	            		 alert("本月已达标，获得50积分"); 
	            	 }else if(str=="3"){
	            		 this.signList.push({"signDay":today})
	            		 alert("本月已达标，获得50积分"); 
	            	 }else{
	            		 alert("签到成功"); 
	            		 this.signList.push({"signDay":today})
	            	 }
	             });
                
            }
        },
        mounted(){
        	var list="${signin.signDate}".substring(3);
            var dayList=list.split("/");
            for(var i=0;i<dayList.length-1;i++){
            	this.signList.push({"signDay": dayList[i]});
            }
            this.email=this.$refs.email.innerText;
            this.phone=this.$refs.phone.innerText;
            this.email=this.email.substring(0,5)+'****'+this.email.substring(this.email.indexOf('@'));
            this.phone=this.phone.substring(0,3)+'****'+this.phone.substring(7);
            this.$refs.email.innerText=this.email;
            this.$refs.phone.innerText=this.phone;
        }
    })

</script>


</body>
</html>