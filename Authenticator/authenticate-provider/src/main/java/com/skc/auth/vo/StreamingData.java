package com.skc.auth.vo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;

@Scope(scopeName="prototype")
public class StreamingData {
	
	private Map<String,String> dataMap;
	
	
	public StreamingData(String data) {
		dataMap = new HashMap<String, String>();
		dataMap.put("data", data);
	}
	

	public Map<String,String> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String,String> dataMap) {
		this.dataMap = dataMap;
	}
	
	@Override
	public String toString() {
		return "StreamingData#"+dataMap;
	}
}
