<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品设置</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/bootstrap/css/bootstrap.min2.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/management/goodsSet1.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/jquery-ui.min.css"/>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/vue.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/js/vue-resource.js"></script>
</head>
<body>
<div class="top">
    <div class="title">
        gjguke  优客站后台管理
    </div>
    <div class="icon">
        <a href="${pageContext.request.contextPath}/management/home.action"><img  src="${pageContext.request.contextPath}/images/icon/首页2.png" title="首页"></a>
        <a href="${pageContext.request.contextPath}/management/orderSearch.action?pageNum=1"><img  src="${pageContext.request.contextPath}/images/icon/公告管理.png" title="订单查看"></a>
        <a href="${pageContext.request.contextPath}/management/storeSetting.action"><img  src="${pageContext.request.contextPath}/images/icon/设置a.png" title="设置中心"></a>
        <a href="${pageContext.request.contextPath}/jsp/management/userManual.jsp"><img  src="${pageContext.request.contextPath}/images/icon/问号.png" title="帮助中心"></a>
        <a href="${pageContext.request.contextPath}/management/exit.action"><img  src="${pageContext.request.contextPath}/images/icon/退出账号.png" title="退出登录"></a>
    </div>
   	<c:if test="${orderUserPayment==0 }">
   	</c:if>
   	<c:if test="${orderUserPayment!=0 }">
    	<span id="prompt">
    	${orderUserPayment }
    	 </span>
   	</c:if>
</div>
<div class="main">
    <div class="mainLeft">
        <div class="user">
            <a href="${pageContext.request.contextPath}/jsp/management/personalMessage.jsp"><img src="${pageContext.request.contextPath}/images/管理员头像/${MANAGER.managerPhoto}"/></a>
            <span>
                <p>欢迎您</p>
                <p>${MANAGER.managerName }</p>
            </span>
        </div>
        <div class="subnav">
            <div class="sortable-accordion">
                <h3>订单管理&emsp;<span class="glyphicon glyphicon-menu-down"></span></h3>
                <div class="inner">
                    <ul class="sortable ">
                    	<a href="${pageContext.request.contextPath}/management/orderSearch.action?pageNum=1"><li class="ui-state-default">订单查询</li></a>
                    	<a href="${pageContext.request.contextPath}/management/historicalOrder.action?pageNum=1"><li class="ui-state-default">历史订单</li></a>
                    </ul>
                </div>
                <h3>商品管理&emsp;<span class="glyphicon glyphicon-menu-down"></span></h3>
                <div class="inner">
                    <ul class="sortable">
                        <a href="${pageContext.request.contextPath}/management/goodsSet1.action?goodsType=1"><li class="ui-state-default">商品设置</li></a>
                        <a href="${pageContext.request.contextPath}/management/toAddGoods.action"><li class="ui-state-default">添加商品</li></a>
                        <a href="${pageContext.request.contextPath}/management/recommend.action"><li class="ui-state-default">店长推荐</li></a>
                        <a href="${pageContext.request.contextPath}/management/integralSetting.action"><li class="ui-state-default">积分设置</li></a>
                    </ul>
                </div>
                <h3>财政统计&emsp;<span class="glyphicon glyphicon-menu-down"></span></h3>
                <div class="inner">
                    <ul class="sortable">
                        <a href="${pageContext.request.contextPath}/management/orderStatistics.action?pageNum=1"><li class="ui-state-default">订单统计</li></a>
                        <a href="${pageContext.request.contextPath}/management/otherProject.action?pageNum=1"><li class="ui-state-default">其他业务</li></a>
                        <a href="${pageContext.request.contextPath}/management/termSettlement.action?pageNum=1"><li class="ui-state-default">定期结算</li></a>
                    </ul>
                </div>
                <h3>用户管理&emsp;<span class="glyphicon glyphicon-menu-down"></span></h3>
                <div class="inner">
                    <ul class="sortable">
                        <a href="${pageContext.request.contextPath}/management/commentManagement.action?pageNum=1"><li class="ui-state-default">评价管理</li></a>
                        <a href="${pageContext.request.contextPath}/management/permissionSetting.action"><li class="ui-state-default">权限设置</li></a>
                    </ul>
                </div>
                <h3>系统管理&emsp;<span class="glyphicon glyphicon-menu-down"></span></h3>
                <div class="inner">
                    <ul class="sortable">
                        <a href="${pageContext.request.contextPath}/jsp/management/addManager.jsp"><li class="ui-state-default">添加账户</li></a>
                        <a href="${pageContext.request.contextPath}/management/getAllManager.action"><li class="ui-state-default">删除账户</li></a>
                        <a href="${pageContext.request.contextPath}/management/storeSetting.action"><li class="ui-state-default">店铺设置</li></a>
                    </ul>
                </div>
                <h3>个人中心&emsp;<span class="glyphicon glyphicon-menu-down"></span></h3>
                <div class="inner">
                    <ul class="sortable">
                        <a href="${pageContext.request.contextPath}/jsp/management/personalMessage.jsp"><li class="ui-state-default">个人信息</li></a>
                        <a href="${pageContext.request.contextPath}/jsp/management/alterPassword.jsp"><li class="ui-state-default">修改密码</li></a>
                        <a href="${pageContext.request.contextPath}/jsp/management/alterManaInfo.jsp"><li class="ui-state-default">修改资料</li></a>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="mainContent">
        <div class="content">
            <div class="contentleft">
                <div id="leftTitle">
                    <img src="${pageContext.request.contextPath}/images/icon/商品.png">
                    <p>商品设置</p>
                </div>
                <div class="leftbar">
                    <a href="${pageContext.request.contextPath}/management/goodsSet1.action?goodsType=1" ><label>经典奶茶</label></a>
                    <a href="${pageContext.request.contextPath}/management/goodsSet1.action?goodsType=2" ><label>特调果汁</label></a>
                    <a href="${pageContext.request.contextPath}/management/goodsSet1.action?goodsType=3" ><label>奶盖现萃茶</label></a>
                    <a href="${pageContext.request.contextPath}/management/goodsSet1.action?goodsType=4" ><label>果味奶茶</label></a>
                    <a href="${pageContext.request.contextPath}/management/goodsSet1.action?goodsType=5" ><label>缤纷茶园</label></a>
                    <a href="${pageContext.request.contextPath}/management/goodsSet1.action?goodsType=6" ><label>益力多优酸乳</label></a>
                    <a href="${pageContext.request.contextPath}/management/goodsSet1.action?goodsType=7" ><label>香浓奶昔</label></a>
                    <a href="${pageContext.request.contextPath}/management/goodsSet1.action?goodsType=8" ><label>沙冰· 粒粒冰爽</label></a>
                    <a href="${pageContext.request.contextPath}/management/goodsSet1.action?goodsType=9" ><label>鲜榨果汁</label></a>
                    <a href="${pageContext.request.contextPath}/management/goodsSet1.action?goodsType=10" ><label>浓情咖啡</label></a>
                    <a href="${pageContext.request.contextPath}/management/goodsSet1.action?goodsType=11" ><label>丝滑果奶</label></a>
                    <a href="${pageContext.request.contextPath}/management/goodsSet1.action?goodsType=12"><label>特色甜品</label></a>
                    <a href="${pageContext.request.contextPath}/management/goodsSet1.action?goodsType=13" ><label>休闲小吃系列</label></a>
                    <a href="${pageContext.request.contextPath}/management/goodsSet1.action?goodsType=14" ><label>优惠套餐系列</label></a>
                </div>
                <div class="rightbar">
                    <h4>${type.gtName }</h4>
                    <ul>
                    	<c:forEach items="${goodsList }" var="i" varStatus="status">
                    		<c:if test="${i.goodsIsSale=='0' }">
		                        <li>
		                        	<img class="lower" src="${pageContext.request.contextPath}/images/icon/已下架2.png" >
		                            <img  src="${pageContext.request.contextPath}/images/${type.gtName }/${i.goodsPicture}"/>
		                            <span>
		                                <p>${i.goodsName }</p>
		                                <p>
		                                	<c:if test="${i.goodsMiddlePrice==null }">
		                                		￥${i.goodsPrice }
		                                	</c:if>
		                                	<c:if test="${i.goodsMiddlePrice!=null }">
		                                		大￥${i.goodsPrice }&emsp;中￥${i.goodsMiddlePrice }
		                                	</c:if>
		                                </p>
		                                <a href="${pageContext.request.contextPath}/management/goodsSet2.action?id=${i.goodsId}">编辑 ></a>
		                                <a style="margin-left:10px;color:red;" data-toggle="modal" data-target="#infoModal${status.index }">上架 ></a>
		                            </span>
		                        </li>
		                        <div class="modal fade" role="dialog" id="infoModal${status.index }">
		                            <div class="modal-dialog" role="document">
		                                <div class="modal-content">
		                                    <div class="modal-header">
		                                        	上架设置
		                                        <button class="close" data-dismiss="modal">&times;</button>
		                                    </div>
		                                    <div class="modal-body">
		                                        <form class="form-inline" action="${pageContext.request.contextPath}/management/saleGoods.action" method="post">
		                                       	 库存量<input type="text" name="inventory"  size="8" class="inventory form-control"/>
		                                       	 <input type="hidden" name="id" value="${i.goodsId }"/>
		                                        <input type="submit" class="submit btn btn-info" value="提交"/>
		                                        </form>
		                                    </div>
		                                    <div class="modal-footer">
		                                        <button class="btn btn-primary" data-dismiss="modal">关闭</button>
		                                    </div>
		                                </div>
		                            </div>
                        		</div>
	                        </c:if>
	                        <c:if test="${i.goodsIsSale!='0' }">
		                        <li>
		                            <img  src="${pageContext.request.contextPath}/images/${type.gtName }/${i.goodsPicture}"/>
		                            <span>
		                                <p>${i.goodsName }</p>
		                                <p>
		                                	<c:if test="${i.goodsMiddlePrice==null }">
		                                		￥${i.goodsPrice }
		                                	</c:if>
		                                	<c:if test="${i.goodsMiddlePrice!=null }">
		                                		大￥${i.goodsPrice }&emsp;中￥${i.goodsMiddlePrice }
		                                	</c:if>
		                                </p>
		                                <a href="${pageContext.request.contextPath}/management/goodsSet2.action?id=${i.goodsId}">编辑 ></a>
		                                <a style="margin-left:10px;" href="${pageContext.request.contextPath}/management/lowerFrame.action?id=${i.goodsId}">下架 ></a>
		                            </span>
		                        </li>
	                        </c:if>
                        </c:forEach>
                    </ul>

                </div>
            </div>
            <div class="contentright">
                <div class="rightpart1">
                    <h4>常用工具</h4>
                    <a href="${pageContext.request.contextPath}/management/orderSearch.action?pageNum=1">
                        <img src="${pageContext.request.contextPath}/images/icon/订单查询.png">
                        <p>订单查询</p>
                    </a>
                    <a href="${pageContext.request.contextPath}/management/goodsSet1.action?goodsType=1">
                        <img src="${pageContext.request.contextPath}/images/icon/商品.png">
                        <p>商品设置</p>
                    </a>
                    <a href="${pageContext.request.contextPath}/management/recommend.action">
                        <img src="${pageContext.request.contextPath}/images/icon/推荐a.png">
                        <p>店长推荐</p>
                    </a>
                    <a href="${pageContext.request.contextPath}/management/orderStatistics.action?pageNum=1">
                        <img src="${pageContext.request.contextPath}/images/icon/统计a.png">
                        <p>订单统计</p>
                    </a>
                    <a href="${pageContext.request.contextPath}/management/termSettlement.action?pageNum=1">
                        <img src="${pageContext.request.contextPath}/images/icon/结算.png">
                        <p>定期结算</p>
                    </a>
                    <a href="${pageContext.request.contextPath}/jsp/management/personalMessage.jsp">
                        <img src="${pageContext.request.contextPath}/images/icon/个人信息.png">
                        <p>个人信息</p>
                    </a>
                </div>
                
                <div class="rightpart2">
                    <a href="${pageContext.request.contextPath}management/goodsSet1.action?goodsType=1"><img src="${pageContext.request.contextPath}/images/icon/刷新.png" title="刷新数据"></a>
                    <h4>统计</h4>
                    <div>
                        <p>今日订单数：
                        	<c:choose>
                        		<c:when test="${orderUserSize==0&&allOrderSize==0}">
                        		0
                        		</c:when>
                        		<c:otherwise>
                        		${orderNum.get(0) }
                        		</c:otherwise>
                        	</c:choose>
                        </p>
                        <p>今日交易金额：
                       		<c:choose>
                        		<c:when test="${orderUserSize==0&&allOrderSize==0}">
                        		0
                        		</c:when>
                        		<c:otherwise>
                        		${amount.get(0) }
                        		</c:otherwise>
                        	</c:choose>
                        </p>
                        <p>好评率：<font color="red">
                        	<c:if test="${commentLevel=='0%' }">
                        		今日暂无评论
                        	</c:if>
                        	<c:if test="${commentLevel!='0%' }">
                        		${commentLevel}
                        	</c:if>
                        </font></p>
                    </div>
                    <br>

                    <h4>店铺管理</h4>
                    <div>
                       <p>营业时间：${store.businessHours }</p>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<div class="foot"></div>
</div>

<script>
	function getTimeOrder(){
		$.ajax({
			url:"${pageContext.request.contextPath}/management/getAsyncInfo.action",
			method:"post",
			success:function(data){
				if(data=='1'){
					console.log(data);
				}else{
					window.location.href="${pageContext.request.contextPath}/management/goodsSet1.action?goodsType=1";
					console.log(data);
				}
				getTimeOrder();
			}
		});
	}
	
    $(function () {
    	getTimeOrder();
        $('.sortable-accordion div').show();
        $('.sortable-accordion div').slideToggle('slow');
        $('.sortable-accordion h3').click(function () {
            $(this).next('.inner').slideToggle().siblings('.inner:visible').slideUp();
            $(this).toggleClass('current');
            $(this).siblings('h3').removeClass('current');
        });
    	 $('.sortable').sortable({placeholder: 'ui-sortable-placeholder'}).find('li').append('<span class=\'options\'></span>');
    	
    	 $(".submit").click(function(e){
    		var index=$(".submit").index(this);
    		var inventory=$(".inventory").eq(index).val();
        	if(inventory==""){
        		 alert("库存量不能为空")
  				 e.preventDefault();
        	}else{
	    		window.location.href="${pageContext.request.contextPath}/management/addIntegralGoods.action?gtId="+gtId+"&goodsId="+name+"&integral="+integral;
        	}
    	})
    });
			
</script>
</body>
</html>