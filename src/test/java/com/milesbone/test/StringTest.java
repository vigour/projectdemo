package com.milesbone.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class StringTest {

	
	@Test
	public void testArryJoin() {
		String[] arr = {"a","b","c","D","e","1"};
		System.out.println(StringUtils.join(arr, "_"));
	}
	
	
	@Test
	public void testMapJoin() {
		Map<String, String> map = new HashMap<String, String>();
//		map.put("haha", "1");
		map.put("haha_", "1");
		System.out.println(map.containsKey("haha_"));
	}
}
