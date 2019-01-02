package com.management.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

@Service
public class PushService {
	
	private DeferredResult<String> deferredResult;
	
	public DeferredResult<String> getAsyncUpdate(){
		deferredResult=new DeferredResult<String>();
		return deferredResult;
	}
	
	/*
	 * 10秒更新一次
	 */
	
	@Scheduled(fixedDelay=8000)
	public void refreshOrder() {
		if(deferredResult!=null) {
			deferredResult.setResult("1");
		}
	}
	
}
