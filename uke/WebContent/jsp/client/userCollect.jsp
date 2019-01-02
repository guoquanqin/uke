<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>收藏夹</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/client/userCollect.css"/>
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
                    <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=1">我的订单</a> </li>
                    <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=1">我的收藏</a> </li>
                    <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=1">我的评价</a> </li>
                    <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=1">账号设置</a> </li>
                    <li><a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=1">购物中心</a> </li>
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
                    <a href="${pageContext.request.contextPath}/client/userCenter/userSetting.action"><img id="userHead" src="${pageContext.request.contextPath}/images/用户头像/${USER.userPhoto}"></a>
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
                        <li><a href="${pageContext.request.contextPath}/client/userCenter/userCollect.action" style="color:red;">我的收藏夹</a> </li>
                        <li><a href="${pageContext.request.contextPath}/client/userCenter/userComment.action?pageNum=1">我的评价</a> </li>
                        <li><a href="${pageContext.request.contextPath}/client/userCenter/userIntegral.action">我的积分</a> </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="rightsidebar">
        <div class="orderTop">
                <font id="collect">我的收藏夹</font>
                <form role="form"  class="input-group" action="${pageContext.request.contextPath}/client/userCenter/userCollectBySearch.action" method="post">
                    <input type="text" class="form-control" name="search" placeholder="按商品关键字或订单号搜索订单"/>
                    <span class="input-group-addon"><i class="glyphicon glyphicon-search"></i><input id="submit" type="submit" value="搜索"/></span>
                </form>
            </div>
            <div class="orderTitle">
                <input type="checkbox" id="checkbox" name="checkbox"/>&nbsp;全选
                <button id="delete" class="btn" disabled="disabled">删除</button>
                <button id="batchDelete" class="btn btn-info">批量管理</button>
            </div>
            <c:if test="${size==0 }">
            	<img id="notFound" src="${pageContext.request.contextPath}/images/icon/找不到.png" />
            </c:if>
            <c:if test="${size>0 }">
            <div class="order">
                <ul>
                	<c:forEach items="${goods }" var="i" varStatus="status">
               			<input type="hidden" value="${collect.get(status.index).collectId }" class="collectId"/>
	                    <li>
	                        <a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=${i.goodsId}" ><img class="goodsPicture" src="${pageContext.request.contextPath}/images/${goodsType.get(status.index).gtName }/${i.goodsPicture}"></a>
	                        <a class="deleteGoods"><img class="delete" src="${pageContext.request.contextPath}/images/icon/删除.png"/></a>
	                        <div></div>
	                        <img class="choose" src="${pageContext.request.contextPath}/images/icon/打钩a.png"/>
	                        <span>${i.goodsName }</span>
	                        <span>促销价：<font style="color:red;">￥${i.goodsPrice }</font></span>
	                    </li>
                    </c:forEach>
                </ul>
            </div>
            </c:if>
        </div>
    </div>
</div>
<div class="footer">
    <p>copyright by gjguke.top</p>
    <p style="margin-top:-30px;">粤ICP备18138200号-1</p>
</div>

<script>
    $(function () {
    	$(".deleteGoods").click(function(){
			var con=confirm("确定删除此收藏吗?");
			if(con==true){
				var index=$(".deleteGoods").index(this);
				var id=$(".collectId").eq(index).val();
				alert("删除成功")
				window.location.href="${pageContext.request.contextPath}/client/deleteCollect.action?collectId="+id;
			}else{
				alert("取消删除");
			}
		});
    	
        $(".goodsPicture").mouseenter(function () {
            var a=$(".goodsPicture").index(this);
            $(".delete").eq(a).css("display","block");
        }).mouseout(function () {
            var a=$(".goodsPicture").index(this);
            $(".delete").eq(a).css("display","none");
        });
        $(".delete").mouseenter(function () {
            $(this).css("display","block");
        }).mouseout(function () {
            $(this).css("display","none");
        })


        var isSelect=true;
        /*选中数  */
        var checkNum=0;
        var flag=true;
        var length=$(".order").find("li").length;
        function select(isSelect,index){
            if(isSelect){
                $(".choose").eq(index).attr("src","${pageContext.request.contextPath}/images/icon/打钩b.png");
                checkNum++;
            }else{
                $(".choose").eq(index).attr("src","${pageContext.request.contextPath}/images/icon/打钩a.png");
                checkNum--;
            }
        }

        function batchDelete(flag){
            if(flag){
                $(".order").find("li").css("border","2px solid indianred");
                $(".order").find("li").css("cursor","pointer");
                $(".order").find("div").css("display","block");
                $("#batchDelete").text("取消批量管理");
                $("#delete").attr("disabled","disabled");
                $(".choose").css("display","block");
            }else{
                $(".order").find("li").css("border","2px solid white");
                $(".order").find("div").css("display","none");
                $("#batchDelete").text("批量管理");
                $("#delete").attr("disabled","disabled");
                $(".choose").css("display","none");
                $("#checkbox").removeAttr("checked");
                checkNum=0;
            }
        }

        $(".order").find("li").click(function () {
            if($(".order").find("div").attr("display")!="block") {
                var index = $(".order").find("li").index(this);
                if ($(".choose").eq(index).attr("src") == "${pageContext.request.contextPath}/images/icon/打钩b.png") {
                    isSelect = false
                }
                if ($(".choose").eq(index).attr("src") == "${pageContext.request.contextPath}/images/icon/打钩a.png") {
                    isSelect = true
                }
                select(isSelect, index);

                /* 如果全部选中全选框选中 */
                if(checkNum==0){
                    $("#delete").attr("disabled","disabled");
                }else {
                    $("#delete").removeAttr("disabled");
                    if (checkNum == length) {
                        $("#checkbox").prop("checked", true);
                    }
                    else {
                        $("#checkbox").removeAttr("checked");
                    }
                }
            }

        })

        $("#batchDelete").click(function (){
            batchDelete(flag)
            if(flag){
                flag=false;

            }else{
                flag=true;
                $(".choose").attr("src","${pageContext.request.contextPath}/images/icon/打钩a.png");
            }
        })
        /* 全选按钮  */
        $("#checkbox").click(function () {

            if($(this).is(":checked")){

                batchDelete(true);
                $("#delete").removeAttr("disabled");
                checkNum=3;
                $(".choose").attr("src","${pageContext.request.contextPath}/images/icon/打钩b.png");

            }else{
                $("#delete").attr("disabled","disabled");
                batchDelete(false);
                $(".choose").attr("src","${pageContext.request.contextPath}/images/icon/打钩a.png");
                return;
            }
        })

        $("#delete").click(function () {
            var collectId=new Array();
            for(var i=0;i<length;i++){
                if ($(".choose").eq(i).attr("src")=="${pageContext.request.contextPath}/images/icon/打钩b.png"){
                    var id=$(".collectId").eq(i).val();
                    collectId.push(id)
                }
            }
            
            var con=confirm("确定删除所选收藏吗?");
			if(con==true){
				
				alert("删除成功")
				window.location.href="${pageContext.request.contextPath}/client/deletePartCollect.action?collectList="+collectId;
			}else{
				alert("取消删除");
			}
			
        })
    })

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
        }
    })

</script>


</body>
</html>