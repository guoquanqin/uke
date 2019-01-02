<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物车</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/client/userBasket.css"/>
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
            <li ><a href="${pageContext.request.contextPath}"> 个人中心<span class="caret"></span></a></li>
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
                        <li><a href="${pageContext.request.contextPath}/client/userCenter/userBasket.action" style="color:red;">我的购物车</a> </li>
                        <li><a href="${pageContext.request.contextPath}/client/userCenter/userCollect.action">我的收藏夹</a> </li>
                        <li><a href="${pageContext.request.contextPath}/client/userCenter/userComment.action?pageNum=1">我的评价</a> </li>
                        <li><a href="${pageContext.request.contextPath}/client/userCenter/userIntegral.action">我的积分</a> </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="rightsidebar">
           <div class="orderTop">
                <font id="basket">我的购物车</font>
            </div>
            <div class="orderTitle">
                <button disabled="disabled">宝贝</button>
                <button disabled="disabled">单价</button>
                <button disabled="disabled">数量</button>
                <button disabled="disabled">规格</button>
                <button disabled="disabled">总价</button>
                <button disabled="disabled">购物车操作</button>
            </div>
            <c:if test="${size==0 }">
            	<img id="notFound" src="${pageContext.request.contextPath}/images/icon/找不到.png" />
            </c:if>
            <c:if test="${size>0 }">
           		 <form role="form" action="${pageContext.request.contextPath}/client/paymentForBasket.action" method="post">
	            	<c:forEach items="${basketList }" var="i" varStatus="status">
	            	<input type="hidden" value="${i.basketId }" class="basketId" />
	                <div class="order">
	                    <div class="orderHead">
	                    	<c:if test="${goods.get(status.index).goodsInventory!=0 }">
	                      	  	<input type="checkbox" class="checkbox" id="checkbox${status.index }" value="${i.basketId }" name="checkbox" /> <fmt:formatDate pattern="yyyy-MM-dd"  value="${i.basketTime }"/>
	            		  	</c:if>
	                    	<c:if test="${goods.get(status.index).goodsInventory==0 }">
	                      	  	 <fmt:formatDate pattern="yyyy-MM-dd"  value="${i.basketTime }"/>&emsp;&emsp;(已售罄)
	            		  	</c:if>
	                    </div>
	                    <div class="orderDetail">
	                        <div class="orderGoods">
	                            <a href="${pageContext.request.contextPath}/client/simpleGoods/goodsDetail.action?id=${goods.get(status.index).goodsId}"><img src="${pageContext.request.contextPath}/images/${goodsType.get(status.index).gtName }/${goods.get(status.index).goodsPicture}"></a>&emsp;&emsp;${goods.get(status.index).goodsName }
	                        </div>
	                        <div class="orderPrice" >￥
	                        	<c:choose>
	                        		<c:when test="${i.goodsSize=='1' }">
	                        			${goods.get(status.index).goodsPrice }
	                        		</c:when>
	                        		<c:when test="${i.goodsSize=='0' }">
	                        			${goods.get(status.index).goodsMiddlePrice }
	                        		</c:when>
	                        		<c:otherwise>
	                        			${goods.get(status.index).goodsPrice }
	                        		</c:otherwise>
	                        	</c:choose>
	                        </div>
	                        <div class="orderNum">${i.goodsCount }</div>
	                        <div class="orderSize">
	                        	<c:if test="${i.goodsSize==1 }">
	                        		大
	                        	</c:if>
	                        	<c:if test="${i.goodsSize==2 }">
	                        		中
	                        	</c:if>
	                        	<c:if test="${i.goodsSize==0 }">
	                        		无
	                        	</c:if>
	                        </div>
	                        <div class="orderAllPrice" id="price${status.index }">￥${i.goodsTotalPrice }</div>
	                        <div class="orderOperator">
	                            <a class="delete">删除购物车</a>
	                        </div>
	                    </div>
	                </div>
	                </c:forEach>
	            
			          <div id="accounts">
			              <div id="text">
			                  <input type="checkbox"  id="all"/>全部
			                  <a class="deletePart">删除购物车</a>
			                  <span>合计：<font id="price" style="font-size:20px;color:firebrick">￥0.00</font></span>
			              </div>
			              <input id="compute" type="submit" class="btn btn-danger" value="结算"/>
			          </div>
		           </form>
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
    	$(".delete").click(function(){
			var con=confirm("确定删除此购物车吗?");
			if(con==true){
				var index=$(".delete").index(this);
				var id=$(".basketId").eq(index).val();
				alert("删除成功")
				window.location.href="${pageContext.request.contextPath}/client/deleteBasket.action?basketId="+id;
			}else{
				alert("取消删除");
			}
		});
    	$(".deletePart").click(function(){
    		var i=0;
    		var arr=$(".checkbox").length;
			$(".checkbox").each(function () {
	    		if($(this).is(":checked")){
	                i++;
	    		}
    		})
    		//如果没有选择
    		if(i==0){
    			alert("未选择购物车")
    		}else{
				var con=confirm("确定删除这"+i+"个购物车吗?");
				var basketId=new Array();
				if(con==true){
					$(".checkbox").each(function () {
			    		if($(this).is(":checked")){
			    			var index=$(".checkbox").index(this);
			    			basketId.push($(".basketId").eq(index).val());
			    		}
		    		})
					alert("删除成功")
					window.location.href="${pageContext.request.contextPath}/client/deletePartBasket.action?basketId="+basketId;
				}else{
					alert("取消删除");
				}
    		}
		});
    	
        $(".checkbox").click(function () {
            var i=0;
            var arr=$(".checkbox").length;
            var goodsPrice=0;
            $(".checkbox").each(function () {
                if($(this).is(":checked")){
                    i++;
                    var objs=$(this).attr("id")
                    //截取后面的数字

                    var getId="#price"+objs.substring(8);
                    var str=$(getId).text();
                    var price=str.substring(1);
                    goodsPrice+=parseFloat(price);
                };
            });
            if($("#all").prop("checked")==false) {
                    if (this.checked == true) {
                        $("#compute").addClass("btn-danger");
                    } else {
                        $("#compute").removeClass("btn-danger");
                    }
            }
            if(i==arr){
                $("#all").prop("checked",true);
                var size=$(".checkbox").length;
                var arr=0;
                for(var i=0;i<size;i++){
                    var id="#"+"price"+i;
                    var str=$(id).text();
                    var price=str.substring(1);

                    arr+=parseFloat(price);
                }
                $("#price").text("￥"+arr);
            }else if(i<arr&&i>0){
                $("#all").prop("checked",false);

                $("#price").text("￥"+goodsPrice);
                $("#compute").addClass("btn-danger");
             }else{
                $("#all").prop("checked",false);
                $("#price").text("￥0.00");
                $("#compute").removeClass("btn-danger");
            }
        });

        $("#all").click(function () {
            //如果全选的话
            if($(this).prop("checked") == true){

                $("input[name='checkbox']").prop("checked", true);
                $("#compute").addClass("btn-danger");
                //获取checkbox长度
                var size=$(".checkbox").length;
                var arr=0;
                for(var i=0;i<size;i++){
                    var id="#"+"price"+i;
                    var str=$(id).text();
                    var price=str.substring(1);
					
                    arr+=parseFloat(price);
                }
                $("#price").text("￥"+arr);
            }else{
                $("input[name='checkbox']").prop("checked", false);
                $("#compute").removeClass("btn-danger");
                $("#price").text("￥0.00");
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