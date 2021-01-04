package com.milesbone.test;

import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JsonMapTest {
	
	@Test
	public void testFastJsonMap() {
		Map<String, Object> itemMap = new HashedMap<String, Object>();
		Map<String, Object> tempMap = new HashedMap<String, Object>();
		tempMap.put("a", "va");
		tempMap.put("b", 1);
		itemMap.put("d", null);
		itemMap.put("e", "");
		tempMap.put("testMapB", "vB");
		itemMap.putAll(tempMap);
		itemMap.put("c", 12L);
		itemMap.put("testMapB", "testVB");
		JSONObject itemJSONObj = JSONObject.parseObject(JSON.toJSONString(itemMap));
		System.out.println(itemJSONObj.toJSONString());
		System.out.println(itemJSONObj.toString());
	}
	
	
	@Test
	public void testFastJsonMerge() {
		JSONObject jsona = new JSONObject();
		jsona.put("a", 1);
		jsona.put("b", "2");
		
		JSONObject jsonb = new JSONObject();
		jsonb.put("a", 1);
		jsonb.put("c", "123");
		jsonb.put("d", "1234");
		
		
		JSONObject jsonc = new JSONObject();
		jsonc.putAll(jsona);
		jsonc.putAll(jsonb);
		System.out.println(jsonc.toString());
	}

}
