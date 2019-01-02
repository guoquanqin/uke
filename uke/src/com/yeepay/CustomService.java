package com.yeepay;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class CustomService {
	
	/**
	 * formatString() : 字符串格式化方法
	 */
	public static String formatString(String text) {
		return (text == null) ? "" : text.trim();
	}

	/**
	 * getPayURL() : 生成支付链接
	 */
	public static String getPayURL(Map<String, String> params) {
		String p0_Cmd			= formatString(params.get("p0_Cmd"));
		String p1_MerId         = formatString(params.get("p1_MerId"));
		String p2_Order         = formatString(params.get("p2_Order"));
		String p3_Amt           = formatString(params.get("p3_Amt"));
		String p4_Cur           = formatString(params.get("p4_Cur"));
		String p5_Pid           = formatString(params.get("p5_Pid"));
		String p6_Pcat          = formatString(params.get("p6_Pcat"));
		String p7_Pdesc         = formatString(params.get("p7_Pdesc"));
		String p8_Url           = formatString(params.get("p8_Url"));
		String p9_SAF           = formatString(params.get("p9_SAF"));
		String pa_MP            = formatString(params.get("pa_MP"));
		String pd_FrpId         = formatString(params.get("pd_FrpId"));
		String pr_NeedResponse  = formatString(params.get("pr_NeedResponse"));
		String keyValue			= formatString(params.get("key"));

		String[] strArr			= new String[] {p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, 
													p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse};
		String hmac				= DigestUtil.getHmac(strArr, keyValue);

		Map<String, String> requestParams = new HashMap<String, String>();

		try {
			p0_Cmd			= URLEncoder.encode(p0_Cmd,		"GBK");
			p1_MerId		= URLEncoder.encode(p1_MerId, 	"GBK");
			p2_Order		= URLEncoder.encode(p2_Order, 	"GBK");
			p3_Amt			= URLEncoder.encode(p3_Amt, 	"GBK");
			p4_Cur			= URLEncoder.encode(p4_Cur, 	"GBK");
			p5_Pid			= URLEncoder.encode(p5_Pid, 	"GBK");
			p6_Pcat			= URLEncoder.encode(p6_Pcat, 	"GBK");
			p7_Pdesc		= URLEncoder.encode(p7_Pdesc, 	"GBK");
			p8_Url 			= URLEncoder.encode(p8_Url, 	"GBK");
			p9_SAF 			= URLEncoder.encode(p9_SAF, 	"GBK");
			pa_MP 			= URLEncoder.encode(pa_MP, 		"GBK");
			pd_FrpId		= URLEncoder.encode(pd_FrpId, 	"GBK");
			pr_NeedResponse	= URLEncoder.encode(pr_NeedResponse, "GBK");
			hmac			= URLEncoder.encode(hmac, 		"GBK");
		} catch(Exception e) {
			e.printStackTrace();
		}

		String requestURL	= "https://www.yeepay.com/app-merchant-proxy/node";
		StringBuffer payURL = new StringBuffer();

		payURL.append(requestURL).append("?");
		payURL.append("p0_Cmd=").append(p0_Cmd);
        payURL.append("&p1_MerId=").append(p1_MerId);
        payURL.append("&p2_Order=").append(p2_Order);
        payURL.append("&p3_Amt=").append(p3_Amt);
        payURL.append("&p4_Cur=").append(p4_Cur);
        payURL.append("&p5_Pid=").append(p5_Pid);
        payURL.append("&p6_Pcat=").append(p6_Pcat);
        payURL.append("&p7_Pdesc=").append(p7_Pdesc);
        payURL.append("&p8_Url=").append(p8_Url);
        payURL.append("&p9_SAF=").append(p9_SAF);
        payURL.append("&pa_MP=").append(pa_MP);
        payURL.append("&pd_FrpId=").append(pd_FrpId);
        payURL.append("&pr_NeedResponse=").append(pr_NeedResponse);
        payURL.append("&hmac=").append(hmac);                  

		System.out.println("payURL : " + payURL);
		
		return (payURL.toString());
	}

		/**
	 * getQAPayURL() : 生成测试环境的支付链接
	 */
	public static String getQAPayURL(Map<String, String> params) {
		String p0_Cmd			= formatString(params.get("p0_Cmd"));
		String p1_MerId         = formatString(params.get("p1_MerId"));
		String p2_Order         = formatString(params.get("p2_Order"));
		String p3_Amt           = formatString(params.get("p3_Amt"));
		String p4_Cur           = formatString(params.get("p4_Cur"));
		String p5_Pid           = formatString(params.get("p5_Pid"));
		String p6_Pcat          = formatString(params.get("p6_Pcat"));
		String p7_Pdesc         = formatString(params.get("p7_Pdesc"));
		String p8_Url           = formatString(params.get("p8_Url"));
		String p9_SAF           = formatString(params.get("p9_SAF"));
		String pa_MP            = formatString(params.get("pa_MP"));
		String pd_FrpId         = formatString(params.get("pd_FrpId"));
		String pr_NeedResponse  = formatString(params.get("pr_NeedResponse"));
		String keyValue			= formatString(params.get("key"));

		String[] strArr			= new String[] {p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, 
													p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse};
		String hmac				= DigestUtil.getHmac(strArr, keyValue);

		Map<String, String> requestParams = new HashMap<String, String>();

		try {
			p0_Cmd			= URLEncoder.encode(p0_Cmd,		"GBK");
			p1_MerId		= URLEncoder.encode(p1_MerId, 	"GBK");
			p2_Order		= URLEncoder.encode(p2_Order, 	"GBK");
			p3_Amt			= URLEncoder.encode(p3_Amt, 	"GBK");
			p4_Cur			= URLEncoder.encode(p4_Cur, 	"GBK");
			p5_Pid			= URLEncoder.encode(p5_Pid, 	"GBK");
			p6_Pcat			= URLEncoder.encode(p6_Pcat, 	"GBK");
			p7_Pdesc		= URLEncoder.encode(p7_Pdesc, 	"GBK");
			p8_Url 			= URLEncoder.encode(p8_Url, 	"GBK");
			p9_SAF 			= URLEncoder.encode(p9_SAF, 	"GBK");
			pa_MP 			= URLEncoder.encode(pa_MP, 		"GBK");
			pd_FrpId		= URLEncoder.encode(pd_FrpId, 	"GBK");
			pr_NeedResponse	= URLEncoder.encode(pr_NeedResponse, "GBK");
			hmac			= URLEncoder.encode(hmac, 		"GBK");
		} catch(Exception e) {
			e.printStackTrace();
		}

		String requestURL	= "http://qa.yeepay.com/app-merchant-proxy/node";
		StringBuffer payURL = new StringBuffer();

		payURL.append(requestURL).append("?");
		payURL.append("p0_Cmd=").append(p0_Cmd);
        payURL.append("&p1_MerId=").append(p1_MerId);
        payURL.append("&p2_Order=").append(p2_Order);
        payURL.append("&p3_Amt=").append(p3_Amt);
        payURL.append("&p4_Cur=").append(p4_Cur);
        payURL.append("&p5_Pid=").append(p5_Pid);
        payURL.append("&p6_Pcat=").append(p6_Pcat);
        payURL.append("&p7_Pdesc=").append(p7_Pdesc);
        payURL.append("&p8_Url=").append(p8_Url);
        payURL.append("&p9_SAF=").append(p9_SAF);
        payURL.append("&pa_MP=").append(pa_MP);
        payURL.append("&pd_FrpId=").append(pd_FrpId);
        payURL.append("&pr_NeedResponse=").append(pr_NeedResponse);
        payURL.append("&hmac=").append(hmac);                  

		System.out.println("payURL : " + payURL);
		
		return (payURL.toString());
	}
}
