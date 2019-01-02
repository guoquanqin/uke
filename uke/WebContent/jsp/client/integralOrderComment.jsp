<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>积分订单评价</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/client/orderComment.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/client/sign.css"/>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.fly.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/vue.js"></script>
<script src="${pageContext.request.contextPath}/js/vue-resource.js"></script>
<script src="${pageContext.request.contextPath}/js/calendar.js"></script>
</head>
<body>
<nav id="navbar" class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <p class="navbar-text">您好，${USER.userName } </p>
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
                    <a href="${pageContext.request.contextPath}/client/userCenter/userSetting.action"><img id="userHead" src="${pageContext.request.contextPath}/images/icon/${USER.userPhoto}"></a>
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
                        <span>上次登录：<fmt:formatDate value="${USER.lastLoginTime }" pattern="yyyy-MM-dd"/></span>
                        <span>我的积分：<font style="color:red;">${USER.userIntegral }</font> </span>
                        <span><a href="${pageContext.request.contextPath}/client/userCenter/userAllOrder.action?pageNum=1""><img src="${pageContext.request.contextPath}/images/icon/订单.png">历史订单</a></span>
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
       		<div class="rightTop">
               <i><img src="${pageContext.request.contextPath}/images/icon/评价1.png"/></i>积分订单评价
            </div>
            <div class="rightContent">
                <div class="order">
                    <div class="orderTitle">
                        <label>宝贝信息</label>
                        <label>积分</label>
                        <label>数量</label>
                        <label>总价</label>
                    </div>
                    <div class="orderDetail">
                        <div class="goods">
                            <label><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=${shop.goodsId}"><img src="${pageContext.request.contextPath}/images/${goodsType.gtName }/${goodsById.goodsPicture}"></a>${goodsById.goodsName }</label>
                            <label>${shop.integral }积分</label>
                            <label>1</label>
                            <label>
                            	${shop.integral }积分
                            </label>
                        </div>
                    </div>
                </div>
                <form action="${pageContext.request.contextPath}/client/saveIntegralOrderComment.action?integralOrder=${integralOrderById.integralOrderId}" method="post" enctype="multipart/form-data">
                <div class="comment">
                    <label>质量与服务</label>
	                    <img src="${pageContext.request.contextPath}/images/icon/星星.png"/>
	                    <img src="${pageContext.request.contextPath}/images/icon/星星.png"/>
	                    <img src="${pageContext.request.contextPath}/images/icon/星星.png"/>
	                    <img src="${pageContext.request.contextPath}/images/icon/星星.png"/>
	                    <img src="${pageContext.request.contextPath}/images/icon/星星.png"/>
                    <span>满意的话就猛戳5颗星吧</span>
					<input type="hidden" id="count" name="count" value="">
                </div>
                <div class="text">
                    <textarea cols="90" name="comment" rows="4" placeholder="如有任何意见或建议可以反馈给我们哦~"></textarea>
                    <i>
                    	<label for="file1">
		                    <img id="pic1" title="添加图片" src="${pageContext.request.contextPath}/images/icon/添加图片.png">
	                    </label>
                    	<label for="file2">
		                    <img id="pic2" title="添加图片" src="${pageContext.request.contextPath}/images/icon/添加图片.png">
	                    </label>
                    	<label for="file3">
		                    <img id="pic3" title="添加图片" src="${pageContext.request.contextPath}/images/icon/添加图片.png">
	                    </label>
	                    <input id="file1" type="file" onchange="changepic1(this)"  accept="image/png,image/jpeg,image/jpg" name="pictrueFile1" class="inputfile"/>
	                    <input id="file2" type="file" onchange="changepic2(this)"  accept="image/png,image/jpeg,image/jpg" name="pictrueFile2" class="inputfile"/>
	                    <input id="file3" type="file" onchange="changepic3(this)"  accept="image/png,image/jpeg,image/jpg" name="pictrueFile3" class="inputfile"/>
                    </i>
                </div>
                <button class="btn btn-danger">提交评论</button>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="footer">
    <p>copyright by gjguke.top</p>
    <p style="margin-top:-30px;">粤ICP备18138200号-1</p>
</div>
<script>
	function changepic1() {
	    var reads= new FileReader();
	    f=document.getElementById('file1').files[0];
	    reads.readAsDataURL(f);
	    reads.onload=function (e) {
	        document.getElementById('pic1').src=this.result;
	    };
	}
	function changepic2() {
	    var reads= new FileReader();
	    f=document.getElementById('file2').files[0];
	    reads.readAsDataURL(f);
	    reads.onload=function (e) {
	        document.getElementById('pic2').src=this.result;
	    };
	}
	function changepic3() {
	    var reads= new FileReader();
	    f=document.getElementById('file3').files[0];
	    reads.readAsDataURL(f);
	    reads.onload=function (e) {
	        document.getElementById('pic3').src=this.result;
	    };
	}
    $(document).ready(function(){
        var shixin = "${pageContext.request.contextPath}/images/icon/星评.png";
        var kongxin = "${pageContext.request.contextPath}/images/icon/星星.png";
        var length=$(".comment").find("img").length;
        //统计星星数
        var count=0;
        var comment=$("textarea").eq(0).val();

        $(".comment").find("img").mouseenter(function(){
            $(this).attr("src",shixin).prevAll().attr("src",shixin).end().nextAll().attr("src",kongxin);
        });
        $(".comment").mouseleave(function(){
            $(".comment").find("img").attr("src",kongxin);
            $(".clicked").attr("src",shixin).prevAll().attr("src",shixin);
        });
        $(".comment").find("img").on("click",function(){
        	count=0;
            $(this).addClass("clicked").siblings().removeClass("clicked");
            
            for(var i=0;i<length;i++){
                if($(".comment").find("img").eq(i).attr("src")=="${pageContext.request.contextPath}/images/icon/星评.png"){
                    count++
                }
            }
            $("#count").val(count);
            
        });
        
        $("button").click(function (e) {
            for(var i=0;i<length;i++){
                if($(".comment").find("img").eq(i).attr("src")=="${pageContext.request.contextPath}/images/icon/星评.png"){
                    count++
                }
            }
            if(count==0){
                alert("好评至少要一颗星哦~")
                return false;
            }
            else{
            	var con=confirm("确定提交评论吗");
    			if(con==true){
    				alert("提交成功")
    			}else{
    				alert("取消提交");
    				return false;
    			}

            }
        })
    });
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
            signList: []

        },
        methods: {
            getSign() {
                calUtil.init(this.signList);
            },
            sign(){
            	alert("签到成功")
                var myDate=new Date();
                var today=myDate.getDate();
                this.signList.push({"signDay":today})
                
                this.$http.post('${pageContext.request.contextPath}/client/userCenter/sign.action?userId=1',today,{
	             	'headers':{
							'Content-Type':'application/json;charset=UTF-8'
							}
	             }).then(function(data){
	             	 alert(data.body); 
	             });

            }
        },
        mounted(){
            var list="${signin.signDate}";
            var dayList=list.split("/");
            for(var i=0;i<dayList.length-1;i++){
            	this.signList.push({"signDay": dayList[i]});
            }
        }
    })

</script>


</body>
</html>