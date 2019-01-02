package com.yeepay;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.yeepay.DigestUtil;
import com.yeepay.HttpUtils;
import com.yeepay.Configuration;

public class YeepayService {

	/**
	 * getP1_MerId() : 取得商户编号方法
	 */
	public static String getP1_MerId() {
		return Configuration.getInstance().getValue("p1_MerId");
	}

	/**
	 * getKeyValue() : 取得商户密钥方法
	 */
	public static String getKeyValue() {
		return Configuration.getInstance().getValue("keyValue");
	}

	/**
	 * getRequestURL() : 取得下单地址方法
	 */
	public static String getRequestURL() {
		return Configuration.getInstance().getValue("requestURL");
	}

	/**
	 * getQueryURL() : 取得订单查询地址
	 */
	public static String getQueryURL() {
		return Configuration.getInstance().getValue("queryURL");
	}

	/**
	 * getRefundURL() : 取得退款请求地址
	 */
	public static String getRefundURL() {
		return Configuration.getInstance().getValue("refundURL");
	}

	/**
	 * getRefundQueryURL() : 取得退款查询请求地址
	 */
	public static String getRefundQueryURL() {
		return Configuration.getInstance().getValue("refundQueryURL");
	}

	/**
	 * getRefundQueryURL() : 取得订单取消地址
	 */
	public static String getCancelOrderURL() {
		return Configuration.getInstance().getValue("cancelOrderURL");
	}

	/**
	 * formatString(String text) : 字符串格式化方法
	 */
	public static String formatString(String text) {
		return (text == null ? "" : text.trim());
	}


	/**
	 * getPayURL() : 生成支付链接
	 */
	public static String getPayURL(Map<String, String> params) {

		System.out.println("##### getPayURL() #####");

		String p0_Cmd			= formatString(params.get("p0_Cmd"));
		String p1_MerId         = getP1_MerId();
		String p2_Order         = formatString(params.get("p2_Order"));
		String p3_Amt           = formatString(params.get("p3_Amt"));
		String p4_Cur           = formatString(params.get("p4_Cur"));
		String p5_Pid           = formatString(params.get("p5_Pid"));
		String p6_Pcat          = formatString(params.get("p6_Pcat"));
		String p7_Pdesc         = formatString(params.get("p7_Pdesc"));
		String p8_Url           = formatString(params.get("p8_Url"));
		String p9_SAF           = formatString(params.get("p9_SAF"));
		String pa_MP            = formatString(params.get("pa_MP"));
		String pb_ServerNotifyUrl = formatString(params.get("pb_ServerNotifyUrl"));
		String pd_FrpId         = formatString(params.get("pd_FrpId"));
		String pm_Period        = formatString(params.get("pm_Period"));
		String pn_Unit         	= formatString(params.get("pn_Unit"));
		String pr_NeedResponse  = formatString(params.get("pr_NeedResponse"));
		String pt_UserName  	= formatString(params.get("pt_UserName"));
		String pt_PostalCode  	= formatString(params.get("pt_PostalCode"));
		String pt_Address  		= formatString(params.get("pt_Address"));
		String pt_TeleNo  		= formatString(params.get("pt_TeleNo"));
		String pt_Mobile  		= formatString(params.get("pt_Mobile"));
		String pt_Email  		= formatString(params.get("pt_Email"));
		String pt_LeaveMessage  = formatString(params.get("pt_LeaveMessage"));
		String keyValue			= getKeyValue();

		String[] strArr			= new String[] {p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, 
												p8_Url, p9_SAF, pa_MP,pb_ServerNotifyUrl, pd_FrpId, pm_Period, pn_Unit, pr_NeedResponse,
												pt_UserName, pt_PostalCode, pt_Address, pt_TeleNo, pt_Mobile, pt_Email,pt_LeaveMessage};

		
		System.out.println("params=" + params);
		for(int i = 0; i < strArr.length; i++) {
			System.out.print(strArr[i]);
		}
		System.out.println();

		String hmac				= DigestUtil.getHmac(strArr, keyValue);
//hmac_safe
		String hmac_safe=  DigestUtil.getHmac_safe(strArr,keyValue);
		try {
			p0_Cmd			= URLEncoder.encode(p0_Cmd, 	"GBK");
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
			pb_ServerNotifyUrl=URLEncoder.encode(pb_ServerNotifyUrl,"GBK");
			pd_FrpId		= URLEncoder.encode(pd_FrpId, 	"GBK");
			pm_Period		= URLEncoder.encode(pm_Period, 	"GBK");
			pn_Unit			= URLEncoder.encode(pn_Unit, 	"GBK");
			pr_NeedResponse	= URLEncoder.encode(pr_NeedResponse,"GBK");
			pt_UserName		= URLEncoder.encode(pt_UserName, 	"GBK");
			pt_PostalCode	= URLEncoder.encode(pt_PostalCode, 	"GBK");
			pt_Address		= URLEncoder.encode(pt_Address, "GBK");
			pt_TeleNo		= URLEncoder.encode(pt_TeleNo, 	"GBK");
			pt_Mobile		= URLEncoder.encode(pt_Mobile, 	"GBK");
			pt_Email		= URLEncoder.encode(pt_Email, 	"GBK");
			pt_LeaveMessage	= URLEncoder.encode(pt_LeaveMessage, 	"GBK");
			hmac			= URLEncoder.encode(hmac, 		"GBK");
			hmac_safe  =URLEncoder.encode(hmac_safe,"GBK");
		} catch(Exception e) {
			e.printStackTrace();
		}

		String requestURL	= getRequestURL();
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
        payURL.append("&pb_ServerNotifyUrl=").append(pb_ServerNotifyUrl);
        payURL.append("&pd_FrpId=").append(pd_FrpId);
        payURL.append("&pm_Period=").append(pm_Period);
        payURL.append("&pn_Unit=").append(pn_Unit);
        payURL.append("&pr_NeedResponse=").append(pr_NeedResponse);
        payURL.append("&pt_UserName=").append(pt_UserName);
        payURL.append("&pt_PostalCode=").append(pt_PostalCode);
        payURL.append("&pt_Address=").append(pt_Address);
        payURL.append("&pt_TeleNo=").append(pt_TeleNo);
        payURL.append("&pt_Mobile=").append(pt_Mobile);
        payURL.append("&pt_Email=").append(pt_Email);
        payURL.append("&pt_LeaveMessage=").append(pt_LeaveMessage);
        payURL.append("&hmac=").append(hmac);                  
        payURL.append("&hmac_safe=").append(hmac_safe);
		System.out.println("payURL : " + payURL);
		
		return (payURL.toString());
	}

	
	/**
	 * queryByOrder() : 订单查询方法
	 * @throws UnsupportedEncodingException 
	 */
	public static Map<String, String> queryByOrder (Map<String, String> params) throws UnsupportedEncodingException {

		System.out.println("##### queryByOrder() #####");

		String p0_Cmd			= "QueryOrdDetail";
		String p1_MerId			= getP1_MerId();
		String p2_Order			= params.get("p2_Order");
		String keyValue			= getKeyValue();
		String pv_Ver			= "3.0";
		String p3_ServiceType	= params.get("p3_ServiceType");

		String[] strArr			= {p0_Cmd, p1_MerId, p2_Order, pv_Ver, p3_ServiceType};

		String hmac				= DigestUtil.getHmac(strArr, keyValue);
		String hmac_safe				= DigestUtil.getHmac_safe(strArr, keyValue);

		Map<String, String> queryParams	= new HashMap<String, String>();
		queryParams.put("p0_Cmd", p0_Cmd);
		queryParams.put("p1_MerId", p1_MerId);
		queryParams.put("p2_Order", p2_Order);
		queryParams.put("pv_Ver", pv_Ver);
		queryParams.put("p3_ServiceType", p3_ServiceType);
		queryParams.put("hmac_safe", hmac_safe);
		queryParams.put("hmac", hmac);
		
		String queryURL			= getQueryURL();

		System.out.println("queryParams : " + queryParams);
		System.out.println("queryURL : " + queryURL);

		Map<String, String> queryResult = new HashMap<String, String>();
		String r0_Cmd					= "";
		String r1_Code              	= "";
		String r2_TrxId             	= "";
		String r3_Amt               	= "";
		String r4_Cur               	= "";
		String r5_Pid               	= "";
		String r6_Order             	= "";
		String r8_MP                	= "";
		String rw_RefundRequestID   	= "";
		String rx_CreateTime        	= "";
		String ry_FinshTime        		= "";
		String rz_RefundAmount      	= "";
		String rb_PayStatus         	= "";
		String rc_RefundCount       	= "";
		String rd_RefundAmt         	= "";
		String hmacFromYeepay			= "";
		String hmac_safeFromYeepay		= "";
       	String hmacError		   		= ""; //自定义，非接口返回。
		String errorMsg					= ""; //自定义，非接口返回。

		List responseList		= null;

		try {
			responseList		= HttpUtils.URLGet(queryURL, queryParams);
			System.out.println("responseList : " + responseList);
		} catch(Exception e) {
			e.printStackTrace();
		}

		if(responseList == null) {
			errorMsg	= "No data returned!";
		} else {
			Iterator iter	= responseList.iterator();
			while(iter.hasNext()) {
				String temp = formatString((String)iter.next());
				if(temp.equals("")) {
					continue;
				}
				int i = temp.indexOf("=");
				int j = temp.length();
				if(i >= 0) {
					String tempKey		= temp.substring(0, i);
					String tempValue	= URLDecoder.decode(temp.substring(i+1, j),"GBK");
					if("r0_Cmd".equals(tempKey)) {
						r0_Cmd		= tempValue;
					} else if("r1_Code".equals(tempKey)) {
						r1_Code		= tempValue;
					} else if("r2_TrxId".equals(tempKey)) {
						r2_TrxId	= tempValue;
					} else if("r3_Amt".equals(tempKey)) {
						r3_Amt		= tempValue;
					} else if("r4_Cur".equals(tempKey)) {
						r4_Cur		= tempValue;
					} else if("r5_Pid".equals(tempKey)) {
						r5_Pid		= tempValue;						
					} else if("r6_Order".equals(tempKey)) {
						r6_Order	= tempValue;
					} else if("r8_MP".equals(tempKey)) {
						r8_MP		= tempValue;
					} else if("rw_RefundRequestID".equals(tempKey)) {
						rw_RefundRequestID	= tempValue;
					} else if("rx_CreateTime".equals(tempKey)) {
						rx_CreateTime		= tempValue;
					} else if("ry_FinshTime".equals(tempKey)) {
						ry_FinshTime		= tempValue;
					} else if("rz_RefundAmount".equals(tempKey)) {
						rz_RefundAmount		= tempValue;
					} else if("rb_PayStatus".equals(tempKey)) {
						rb_PayStatus		= tempValue;
					} else if("rc_RefundCount".equals(tempKey)) {
						rc_RefundCount		= tempValue;
					} else if("rd_RefundAmt".equals(tempKey)) {
						rd_RefundAmt		= tempValue;
					} else if("hmac".equals(tempKey)) {
						hmacFromYeepay		= tempValue;
					} else if("hmac_safe".equals(tempKey)){
						hmac_safeFromYeepay	= tempValue;
					}
				}
			}
			
			String[] stringArr	= {r0_Cmd, r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r8_MP, 
									rw_RefundRequestID, rx_CreateTime, ry_FinshTime, rz_RefundAmount, rb_PayStatus,
									rc_RefundCount, rd_RefundAmt};
			String localHmac	= DigestUtil.getHmac(stringArr, keyValue);
			boolean ishmac_safe = verifyCallbackHmac_safe(stringArr, hmac_safeFromYeepay);

			if(!localHmac.equals(hmacFromYeepay) || !ishmac_safe) {
//				hmacError	= "Hmac 不匹配！ hmacFromYeepay : "
//							+ hmacFromYeepay 
//							+ "; localHmac : "
//							+ localHmac;
				StringBuffer temp = new StringBuffer();
				for(int i = 0; i < stringArr.length; i++) {
					temp.append(stringArr[i]);
				}
			}
		}

		queryResult.put("r0_Cmd", r0_Cmd);
		queryResult.put("r1_Code", r1_Code);
		queryResult.put("r2_TrxId", r2_TrxId);
		queryResult.put("r3_Amt", r3_Amt);
		queryResult.put("r4_Cur", r4_Cur);
		queryResult.put("r5_Pid", r5_Pid);
		queryResult.put("r6_Order", r6_Order);
		queryResult.put("r8_MP", r8_MP);
		queryResult.put("rw_RefundRequestID", rw_RefundRequestID);
		queryResult.put("rx_CreateTime", rx_CreateTime);
		queryResult.put("ry_FinshTime", ry_FinshTime);
		queryResult.put("rz_RefundAmount", rz_RefundAmount);
		queryResult.put("rb_PayStatus", rb_PayStatus);
		queryResult.put("rc_RefundCount", rc_RefundCount);
		queryResult.put("rd_RefundAmt", rd_RefundAmt);
		queryResult.put("hamcError", hmacError);
		queryResult.put("errorMsg", errorMsg);

		System.out.println("queryResult : " + queryResult);

		return(queryResult);
	}

	
	/**
	 * refundByTrxId() : 单笔订单退款
	 * @throws UnsupportedEncodingException 
	 */
	public static Map<String, String> refundByTrxId(Map<String, String> params) throws UnsupportedEncodingException {

		System.out.println("##### refundByTrxId() #####");
		System.out.println("params : " + params);

		String p0_Cmd		= params.get("p0_Cmd");
		String p1_MerId		= getP1_MerId();
		String p2_Order		= params.get("p2_Order");
		String pb_TrxId		= params.get("pb_TrxId");
		String p3_Amt		= params.get("p3_Amt");
		String p4_Cur		= params.get("p4_Cur");
		String p5_Desc		= params.get("p5_Desc");
		String keyValue		= getKeyValue();

		String[] strArr 	= {p0_Cmd, p1_MerId, p2_Order, pb_TrxId, p3_Amt, p4_Cur, p5_Desc};
		String hmac			= DigestUtil.getHmac(strArr, keyValue);
		String hmac_safe			= DigestUtil.getHmac_safe(strArr, keyValue);

		Map<String, String> refundParams	= new HashMap<String, String>();
		refundParams.put("p0_Cmd", p0_Cmd);
		refundParams.put("p1_MerId", p1_MerId);
		refundParams.put("p2_Order", p2_Order);
		refundParams.put("pb_TrxId", pb_TrxId);
		refundParams.put("p3_Amt", p3_Amt);
		refundParams.put("p4_Cur", p4_Cur);
		refundParams.put("p5_Desc", p5_Desc);
		refundParams.put("hmac_safe", hmac_safe);
		refundParams.put("hmac", hmac);

		System.out.println("refundParams :" + refundParams);

		Map<String, String> refundResult	= new HashMap<String, String>();
		String r0_Cmd			= "";
		String r1_Code			= "";
		String r2_TrxId			= "";
		String r3_Amt			= "";
		String r4_Cur			= "";
		String r4_Order			= "";
		String rf_fee			= "";
		String hmacFromYeepay	= "";
		String hmac_safeFromYeepay	= "";
		String hmacError		= "";
		String errorMsg			= "";

		String refundURL		= getRefundURL();
		List responseList		= null;

		try {
			responseList	= HttpUtils.URLGet(refundURL, refundParams);
			System.out.println("responseList : " + responseList);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		StringBuffer payURL = new StringBuffer();

		payURL.append(refundURL).append("?");
		payURL.append("p0_Cmd=").append(p0_Cmd);
        payURL.append("&p1_MerId=").append(p1_MerId);
        payURL.append("&p2_Order=").append(p2_Order);
        payURL.append("&pb_TrxId=").append(pb_TrxId);
        payURL.append("&p3_Amt=").append(p3_Amt);
        payURL.append("&p4_Cur=").append(p4_Cur);
        payURL.append("&p5_Desc=").append(p5_Desc);
        payURL.append("&hmac_safe=").append(hmac_safe);
        payURL.append("&hmac=").append(hmac);

        System.out.println("payURL="+payURL);
        
		if(responseList.size() == 0 ) {
			errorMsg	= "No Data Returned!";
		} else {
			Iterator iter			= responseList.iterator();
			while(iter.hasNext()) {
				String temp		= formatString((String)iter.next());
				if(temp.equals("")) {
					continue;
				}
				int i 	= temp.indexOf("=");
				int j 	= temp.length();
				if(i >= 0) {
					String tempKey		= temp.substring(0, i);
					String tempValue	= temp.substring(i + 1, j);
					if("r0_Cmd".equals(tempKey)) {
						r0_Cmd			= tempValue;
					} else if("r1_Code".equals(tempKey)) {
						r1_Code			= tempValue;
					} else if("r2_TrxId".equals(tempKey)) {
						r2_TrxId		= tempValue;
					} else if("r3_Amt".equals(tempKey)) {
						r3_Amt			= tempValue;
					} else if("r4_Cur".equals(tempKey)) {
						r4_Cur			= tempValue;
					} else if("r4_Order".equals(tempKey)) {
						r4_Order		= tempValue;
					} else if("rf_fee".equals(tempKey)) {
						rf_fee			= tempValue;
					} else if("hmac".equals(tempKey)) {
						hmacFromYeepay	= tempValue;
					}else if("hmac_safe".equals(tempKey)) {
						hmac_safeFromYeepay		= tempValue;
					}
				}
			}
			String[] stringArr	= {r0_Cmd, r1_Code, r2_TrxId, r3_Amt, r4_Cur};
			String localHmac 	= DigestUtil.getHmac(stringArr, keyValue);
			boolean ishmac_safe = verifyCallbackHmac_safe(stringArr, hmac_safeFromYeepay);
			System.out.println("验证hmac_safe："+ishmac_safe);
			if(!localHmac.equals(hmacFromYeepay) || !ishmac_safe) {
				hmacError	= "Hmac 不匹配！ hmacFromYeepay : "
							+ hmacFromYeepay 
							+ "; localHmac : "
							+ localHmac;
				StringBuffer temp = new StringBuffer();
				for(int i = 0; i < stringArr.length; i++) {
					temp.append(stringArr[i]);
				}
			}

		}

		refundResult.put("r0_Cmd", 	r0_Cmd);
		refundResult.put("r1_Code", r1_Code);
		refundResult.put("r2_TrxId",r2_TrxId);
		refundResult.put("r3_Amt", 	r3_Amt);
		refundResult.put("r4_Cur", 	r4_Cur);
		refundResult.put("r4_Order",r4_Order);
		refundResult.put("rf_fee", 	rf_fee);
		refundResult.put("hmac", 	hmacFromYeepay);
		refundResult.put("hmac_safe", 	hmac_safeFromYeepay);
		refundResult.put("hmacError", hmacError);
		refundResult.put("errorMsg",  errorMsg);

		System.out.println("refundResult : " + refundResult);

		return (refundResult);
	}

	/**
	 * refundQuery() : 退款订单查询方法
	 *
	 * 请求参数说明
	 * p0_Cmd  			- 业务类型，固定值"RefundResults"
	 * p1_MerId			- 商户编号
	 * p2_Order			- 退款请求号
	 * pb_TrxId			- 易宝流水号
	 * keyValue			- 商户密钥
	 *
	 * 返回结果result说明
	 * r0_Cmd			- 业务类型，固定值”RefundResults"
	 * r1_Code          - 查询结果
	 * r2_TrxId         - 易宝流水号
	 * r4_Order         - 退款请求号
	 * refundStatus     - 退款申请结果
	 * refundFrpStatus  - 退至银行状态
	 * hmac				- 签名数据
	 * hmacError		- 自定义，非接口返回
	 * errorMsg			- 自定义，非接口返回
	 * @throws UnsupportedEncodingException 
	 */
	public static Map<String, String> refundQuery(Map<String, String> params) throws UnsupportedEncodingException {

		System.out.println("##### refundQuery() #####");

		String p0_Cmd		= "RefundResults";
		String p1_MerId		= getP1_MerId();
		String p2_Order		= formatString(params.get("p2_Order"));
		String pb_TrxId		= formatString(params.get("pb_TrxId"));
		String keyValue		= getKeyValue();
		String hmac			= DigestUtil.getHmac(new String[] {p0_Cmd, p1_MerId, p2_Order, pb_TrxId}, keyValue);
		String hmac_safe			= DigestUtil.getHmac_safe(new String[] {p0_Cmd, p1_MerId, p2_Order, pb_TrxId}, keyValue);

		Map<String, String> refundQueryParams = new HashMap<String, String>();
		refundQueryParams.put("p0_Cmd",p0_Cmd);
		refundQueryParams.put("p1_MerId",p1_MerId);
		refundQueryParams.put("p2_Order",p2_Order);
		refundQueryParams.put("pb_TrxId",pb_TrxId);
		refundQueryParams.put("hmac_safe", hmac_safe);
		refundQueryParams.put("hmac",hmac);
		String refundQueryURL	= getRefundQueryURL();

		Map<String, String> refundQueryResult	= new HashMap<String, String>();
		String r0_Cmd			   	= ""; 
        String r1_Code             	= ""; 
        String r2_TrxId            	= ""; 
        String r4_Order            	= ""; 
        String refundStatus        	= ""; 
        String refundFrpStatus     	= ""; 
		String hmacFromYeepay		= "";
		String hmac_safeFromYeepay	= "";
        String hmacError		   	= ""; //自定义，非接口返回。
		String errorMsg				= ""; //自定义，非接口返回。
		
		List responseList			= null;

		try {
			responseList		= HttpUtils.URLGet(refundQueryURL, refundQueryParams);
			System.out.println("responseList : " + responseList);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		if(responseList.size() == 0 ) {
			errorMsg	= "No Data Returned!";
		} else {
			Iterator iter			= responseList.iterator();
			while(iter.hasNext()) {
				String temp		= formatString((String)iter.next());
				if(temp.equals("")) {
					continue;
				}
				int i 	= temp.indexOf("=");
				int j 	= temp.length();
				if(i >= 0) {
					String tempKey		= temp.substring(0, i);
					String tempValue	= temp.substring(i + 1, j);
					if("r0_Cmd".equals(tempKey)) {
						r0_Cmd			= tempValue;
					} else if("r1_Code".equals(tempKey)) {
						r1_Code			= tempValue;
					} else if("r2_TrxId".equals(tempKey)) {
						r2_TrxId		= tempValue;
					} else if("r4_Order".equals(tempKey)) {
						r4_Order		= tempValue;
					} else if("refundStatus".equals(tempKey)) {
						refundStatus	= tempValue;
					} else if("refundFrpStatus".equals(tempKey)) {
						refundFrpStatus	= tempValue;
					} else if("hmac".equals(tempKey)) {
						hmacFromYeepay	= tempValue;
					} else if("hmac_safe".equals(tempKey)) {
						hmac_safeFromYeepay	= tempValue;
					}
				}
			}
			String[] stringArr	= {r0_Cmd, r1_Code, r2_TrxId, r4_Order, refundStatus, refundFrpStatus};
			String localHmac 	= DigestUtil.getHmac(stringArr, keyValue);
			boolean ishmac_safe = verifyCallbackHmac_safe(stringArr, hmac_safeFromYeepay);
			
			if(!localHmac.equals(hmacFromYeepay) || !ishmac_safe) {
				hmacError	= "Hmac 不匹配！ hmacFromYeepay : "
							+ hmacFromYeepay 
							+ "; localHmac : "
							+ localHmac;
				StringBuffer temp = new StringBuffer();
				for(int i = 0; i < stringArr.length; i++) {
					temp.append(stringArr[i]);
				}
			}

		}

		refundQueryResult.put("r0_Cmd",r0_Cmd);
		refundQueryResult.put("r1_Code",r1_Code);
		refundQueryResult.put("r2_TrxId",r2_TrxId);
		refundQueryResult.put("r4_Order",r4_Order);
		refundQueryResult.put("refundStatus",refundStatus);
		refundQueryResult.put("refundFrpStatus",refundFrpStatus);
		refundQueryResult.put("hmac",hmacFromYeepay);
		refundQueryResult.put("hmac_safe",hmac_safeFromYeepay);
		refundQueryResult.put("hmacError",hmacError);
		refundQueryResult.put("errorMsg",errorMsg);

		System.out.println("refundQueryResult : " + refundQueryResult);

		return (refundQueryResult);
	}

	
	/**
	 * verifyCallbackHmac() : 验证回调参数是否有效
	 * @throws UnsupportedEncodingException 
	 */
	public static boolean verifyCallbackHmac(String[] stringValue, String hmacFromYeepay) throws UnsupportedEncodingException {

		System.out.println("##### verifyCallbackHmac() #####");

		String keyValue			= getKeyValue();

		StringBuffer sourceData	= new StringBuffer();
		for(int i = 0; i < stringValue.length; i++) {
			
//			stringValue[i] = URLDecoder.decode(stringValue[i],"GBK");
//			stringValue[i] = new String(stringValue[i].getBytes("8859_1"),"GB2312");
			sourceData.append(stringValue[i]);
			System.out.println("stringValue ～～～～: " + stringValue[i]);
			
		}
		System.out.println("sourceData ～～～～: " + sourceData);

		String localHmac		= DigestUtil.getHmac(stringValue, keyValue);

		StringBuffer hmacSource	= new StringBuffer();
		for(int i = 0; i < stringValue.length; i++) {
			hmacSource.append(stringValue[i]);
		}

		return (localHmac.equals(hmacFromYeepay) ? true : false);
	}

	/**
	 * verifyCallbackHmac_safe() : 验证回调安全签名数据是否有效
	 * @throws UnsupportedEncodingException 
	 */
	public static boolean verifyCallbackHmac_safe(String[] stringValue, String hmac_safeFromYeepay) throws UnsupportedEncodingException {
		
		System.out.println("##### verifyCallbackHmac_safe() #####");
		
		String keyValue			= getKeyValue();
		
		StringBuffer sourceData	= new StringBuffer();
		for(int i = 0; i < stringValue.length; i++) {
			if(!"".equals(stringValue[i])){
				sourceData.append(stringValue[i]+"#");
			}
			
			System.out.println("stringValue ～～～～: " + stringValue[i]);
		}

		sourceData = sourceData.deleteCharAt(sourceData.length()-1);
		System.out.println("sourceData ～～～～: " + sourceData.toString());
		
		String localHmac_safe		= DigestUtil.hmacSign(sourceData.toString(), keyValue);
		System.out.println("localHmac_safe:"+localHmac_safe);
		StringBuffer hmacSource	= new StringBuffer();
		for(int i = 0; i < stringValue.length; i++) {
			hmacSource.append(stringValue[i]);
		}
		
		return (localHmac_safe.equals(hmac_safeFromYeepay) ? true : false);
	}
	


	/**
	 * cancelOrder() : 订单撤销方法
	 * @throws UnsupportedEncodingException 
	 */
	public static Map<String, String> cancelOrder(Map<String, String> params) throws UnsupportedEncodingException {

		System.out.println("##### cancelOrder() #####");

		String p0_Cmd		= formatString(params.get("p0_Cmd"));
		String p1_MerId		= getP1_MerId();
		String pb_TrxId		= formatString(params.get("pb_TrxId"));
		String keyValue		= getKeyValue();

		String[] strArr		= {p0_Cmd, p1_MerId, pb_TrxId};
		String hmac			= DigestUtil.getHmac(strArr, keyValue);
		String hmac_safe			= DigestUtil.getHmac_safe(strArr, keyValue);

		Map<String, String> cancelParams	= new HashMap<String, String>();
		cancelParams.put("p0_Cmd", p0_Cmd);
		cancelParams.put("p1_MerId", p1_MerId);
		cancelParams.put("pb_TrxId", pb_TrxId);
		cancelParams.put("hmac_safe", hmac_safe);
		cancelParams.put("hmac", hmac);

		String cancelOrderURL	= getCancelOrderURL();

		System.out.println("cancelParams : " + cancelParams);
		System.out.println("cancelOrderURL : " + cancelOrderURL);

		Map<String, String> cancelResult	= new HashMap<String, String>();
		String r0_Cmd			= "";
		String r1_Code			= "";
		String hmacFromYeepay	= "";
		String hmac_safeFromYeepay	= "";
		String errorMsg			= "";
		String hmacError		= "";
		
		List responseList		= null;

		try {
			responseList		= HttpUtils.URLGet(cancelOrderURL, cancelParams);
			System.out.println("responseList : " + responseList);
		} catch(Exception e) {
			e.printStackTrace();
		}

		if(responseList.size() == 0 ) {
			errorMsg	= "No Data Returned!";
		} else {
			Iterator iter			= responseList.iterator();
			while(iter.hasNext()) {
				String temp		= formatString((String)iter.next());
				if(temp.equals("")) {
					continue;
				}
				int i 	= temp.indexOf("=");
				int j 	= temp.length();
				if(i >= 0) {
					String tempKey		= temp.substring(0, i);
					String tempValue	= temp.substring(i + 1, j);
					if("r0_Cmd".equals(tempKey)) {
						r0_Cmd			= tempValue;
					} else if("r1_Code".equals(tempKey)) {
						r1_Code			= tempValue;
					} else if("hmac".equals(tempKey)) {
						hmacFromYeepay	= tempValue;
					} else if("hmac_safe".equals(tempKey)) {
						hmac_safeFromYeepay	= tempValue;
				}
				}
			}
			String[] stringArr	= {r0_Cmd, r1_Code};
			String localHmac 	= DigestUtil.getHmac(stringArr, keyValue);
			boolean ishmac_safe = verifyCallbackHmac_safe(stringArr, hmac_safeFromYeepay);

			if(!localHmac.equals(hmacFromYeepay) || !ishmac_safe) {
				hmacError	= "Hmac 不匹配！ hmacFromYeepay : "
							+ hmacFromYeepay 
							+ "; localHmac : "
							+ localHmac;
				StringBuffer temp = new StringBuffer();
				for(int i = 0; i < stringArr.length; i++) {
					temp.append(stringArr[i]);
				}
			}
		}

		cancelResult.put("r0_Cmd",	  r0_Cmd);
		cancelResult.put("r1_Code",   r1_Code);
		cancelResult.put("errorMsg",  errorMsg);
		cancelResult.put("hmacError", hmacError);

		System.out.println("cancelResult : " + cancelResult);

		return (cancelResult);
	}
	
}
