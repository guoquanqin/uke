# 基于JSP的“优客站”奶茶店网上点餐管理系统

**摘  要**：    随着快节奏社会的来临，针对各式各样的快餐外卖越来越大众化和普及化，为了提高“优客站”奶茶店的工作效率，免除不必要的电话订餐或短信订餐，特开发本系统。本系统主要面向“优客站”奶茶店的顾客和奶茶店管理员。本系统是使用了JavaEE 的jsp技术和Mysql数据库，设计了基于B/S模式的点餐系统。系统客户端提供顾客网上点餐功能，顾客可以通过本系统提前点餐，系统将自动提醒后厨人员进行制作并进行配送，同时还提供个人信息的相关功能以及积分兑换机制。管理端提供管理员对订单的接收和统计，店长在月底可进行结算统计。本系统的所有数据来源于“优客站”奶茶店。

## 功能描述
本系统名为基于JSP的“优客站”奶茶店网上点餐管理系统设计与实现，是采用Java语言、基于JSP动态页面技术的进行设计与实现的B/S模式的网站系统。主要面向“优客站”周边顾客和店铺管理员。系统分为客户端和管理端，客户端主要给顾客提供登录、注册、个人信息的管理、外卖的订餐以及历史记录的查看等功能，管理端作为后台部分，主要对订单进行确认和管理，加上对订单统计和店铺收入支出等流水账的统计，管理员还可以对商品的价格、图片信息等进行修改，同时将数据更新到客户端。
###1、客户端模块
客户端模块面向顾客，主要提供浏览商品、下单点餐、购物车查看、积分查看、历史订单浏览、商品收藏等功能。顾客下单支付成功会通知给后台管理员，同时可以查看订单派送情况；顾客可以进入个人中心查看个人信息和其他业务。
验证模块功能
顾客进入登录页面，输入账户密码通过与数据库匹配成功方可登录。没有账号可进行注册，忘记密码可点击进入修改密码页面，通过短信验证通过，进入主界面可退出登录进行退出操作或切换账户功能。
#### 个人中心模块功能

- 我的收藏：用于存储浏览过程中收藏的商品，可进行商品的批量删除或添加。
- 我的评价：给用户提供浏览往期订单评价的内容，以及商家的评价反馈。
- 我的购物车：用户浏览商品过程中可对喜欢的商品加入购物车处理，最后可在购物车进行统一结算，也可删除购物车商品。
- 我的订单：显示用户的所有订单，并提供查询订单功能。订单状态分为待支付、已支付、已接收、正在配送、已收货、待评价、已评价、已完成和废单九个状态，用户可以查看订单的状态进行对应的处理请求。
- 设置中心：用户可以设置自己的个人信息，可以修改头像、修改密码、修改收货地址等。
- 我的积分：用户可以查看积分、兑换规则和兑换记录。
- 签到中心：用户签到满20天获得100积分用于兑换商品。

#### 商品分类模块功能
商品分类主要分为饮料系列、休闲小吃、优惠套餐、积分商城和店长推荐五个系列商品，用户可根据个人需求进行挑选，其中积分商城商品仅用于积分兑换。

#### 订单模块功能

- 商品订单：用户进行购物挑选时，可直接点击购物，并填写好规格大小和数量，进入支付界面确认信息和设置送达时间，也可在购物车直接合并汇总，如果用户需退款，可在“我的订单”界面点击退款处理。每消费一定数额可获得相应积分。
- 积分订单：积分订单仅限积分兑换使用，用户在积分商城挑选商品直接下单，进入结账页面填写信息和送达时间完成下单，但积分订单不提供设置数额和退款处理，一旦下单完成将不可逆。

### 2、管理端模块
管理端模块面向店铺管理员，主要提供订单管理功能，负责实时接收订单和查看订单详情；商品管理功能，提供客户端页面的商品信息设置和积分商品设置；财务统计为管理员提供各个时间的订单统计和其他业务	的财务统计情况；用户管理负责管理用户的订单评价以及权限设置；系统管理和个人中心负责管理端账户的状态设置以及个人信息的修改等。

#### 订单管理模块功能

- 订单查询：包括接收新订单、已接收订单、正在配送和已收货状态，实时接收新订单并设置每个10秒刷新一次，已接收订单、正在配送和已收货状态均由管理员设置。
- 历史订单：管理员可以通过订单号、用户名或手机号进行订单查询，并提供按日期搜索订单。

#### 商品管理模块功能

- 商品设置：管理员可以对商品进行编辑，包括商品的名称、图片、价格等，同时可以设置商品的上架下架情况。
- 添加商品：管理员可以设置商品信息添加新的产品。
- 店长推荐：管理员可以添加商品加入推荐列表，也可以移除推荐列表的商品。
- 积分设置：管理员可以添加积分商品并设置其积分数额，也可以修改积分商品的兑换积分数额。

#### 财务统计模块功能

- 订单统计：显示每天的订单情况和销售额等，管理员可以按日期搜索相应的订单统计情况。
- 其他业务：管理员可以根据业务名称、时间和消费数额添加业务到业务表中，同时可以按日期搜索业务（如水费、物管费、房租、进货支出等等）。
- 定期结算：分为按月结算和按年结算，管理员可以对总财务情况进行统计并显示出来，也可以搜索每天的结算情况。

#### 用户管理模块功能

- 评价管理：提供增删商家反馈和增删评价功能，管理员可以查看用户的评价信息。
- 权限管理：管理员可以设置顾客的权限，被拉入黑名单的顾客将无法访问网站。

#### 系统管理模块功能

- 添加账户：管理员可以根据真实姓名，手机号码等信息添加账户。
- 删除账户：若想删除账户，必须输入改账户的手机号和接收的验证码，否则无法删除。
- 店铺设置：管理员可以设置店铺的热线电话、店铺地址、营业时间和店铺简介，最终信息将显示在客户端供顾客浏览。

#### 个人中心模块功能

- 个人信息：管理员可以对自己的个人信息进行修改查看。
- 修改密码：输入正确的旧密码可以修改密码。
- 修改信息：提供修改账户的用户名和手机号码的功能。

------

## 备注
本系统是基于SSM框架&JSP开发的单机个人项目，内部还集成了短信服务以及阿里云沙箱支付功能，支持下单以及退款
，代码相对比较容易阅读，可用于个人项目练手

_注：本项目已申请国家软件著作权，如用于商用产品将追究法律责任_
