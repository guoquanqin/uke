<%@ page language="java" contentType="text/html; charset=gbk"%>
<%@ page import="com.yeepay.*" import="java.util.Map, java.util.HashMap"%>
<%! String formatString(String text) {
		return text == null ? "" : text.trim();
	}
%>
<%
	request.setCharacterEncoding("GBK");

	String p0_Cmd           = formatString(request.getParameter("p0_Cmd"));
	String p2_Order         = formatString(request.getParameter("p2_Order"));
	String p3_Amt           = formatString(request.getParameter("p3_Amt"));
	String p4_Cur           = formatString(request.getParameter("p4_Cur"));
	String p5_Pid           = formatString(request.getParameter("p5_Pid"));
	String p6_Pcat          = formatString(request.getParameter("p6_Pcat"));
	String p7_Pdesc         = formatString(request.getParameter("p7_Pdesc"));
	String p8_Url           = formatString(request.getParameter("p8_Url"));
	String p9_SAF           = formatString(request.getParameter("p9_SAF"));
	String pa_MP            = formatString(request.getParameter("pa_MP"));
	String pb_ServerNotifyUrl=formatString(request.getParameter("pb_ServerNotifyUrl"));
	String pa_Ext         	= formatString(request.getParameter("pa_Ext"));
	String pb_Oper        	= formatString(request.getParameter("pb_Oper"));
	String pd_FrpId         = formatString(request.getParameter("pd_FrpId"));
	String pd_BankBranch  	= formatString(request.getParameter("pd_BankBranch"));
	String pt_ActId  		= formatString(request.getParameter("pt_ActId"));
	String pv_Ver  			= formatString(request.getParameter("pv_Ver"));
	String pm_Period  		= formatString(request.getParameter("pm_Period"));
	String pn_Unit  		= formatString(request.getParameter("pn_Unit"));
	String pr_NeedResponse  = formatString(request.getParameter("pr_NeedResponse"));
	String pt_UserName  	= formatString(request.getParameter("pt_UserName"));
	String pt_PostalCode 	= formatString(request.getParameter("pt_PostalCode"));
	String pt_Address		= formatString(request.getParameter("pt_Address"));
	String pt_TeleNo  		= formatString(request.getParameter("pt_TeleNo"));
	String pt_Mobile  		= formatString(request.getParameter("pt_Mobile"));
	String pt_Email  		= formatString(request.getParameter("pt_Email"));
	String pt_LeaveMessage	= formatString(request.getParameter("pt_LeaveMessage"));

	Map<String, String> params = new HashMap<String, String>();
	params.put("p0_Cmd", 	p0_Cmd);
	params.put("p2_Order",  "222");
	params.put("p3_Amt",	p3_Amt);
	params.put("p4_Cur",	p4_Cur);
	params.put("p5_Pid",	p5_Pid);
	params.put("p6_Pcat",	p6_Pcat);
	params.put("p7_Pdesc",	p7_Pdesc);
	params.put("p8_Url",	p8_Url);
	params.put("p9_SAF",	p9_SAF);
	params.put("pa_MP",		pa_MP);
	params.put("pb_ServerNotifyUrl",		pb_ServerNotifyUrl);
	params.put("pd_FrpId",	pd_FrpId);
	params.put("pm_Period",	pm_Period);
	params.put("pn_Unit",	pn_Unit);
	params.put("pr_NeedResponse",pr_NeedResponse);
	params.put("pt_UserName","郭权钦");
	params.put("pt_PostalCode",pt_PostalCode);
	params.put("pt_Address",pt_Address);
	params.put("pt_TeleNo","6215582005000229388");
	params.put("pt_Mobile","13653020847");
	params.put("pt_Email",pt_Email);
	params.put("pt_LeaveMessage",pt_LeaveMessage);

	out.println("params : " + params);

	String payURL		= YeepayService.getPayURL(params);

	if("".equals(payURL)) {
		out.println("生成链接失败！");
	} else {
		response.sendRedirect(payURL);
	}
%>
