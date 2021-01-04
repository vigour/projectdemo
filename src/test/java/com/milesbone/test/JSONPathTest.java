package com.milesbone.test;

import org.junit.Test;

import com.jayway.jsonpath.JsonPath;

public class JSONPathTest {

	@Test
	public void testJsonPathValue() {
		String json = "{\n" + 
				"    \"newsPosts\": {\n" + 
				"        \"orientation\": \"horizontal\",\n" + 
				"        \"background\": \"http://img01.ptsharp.gitv.tv/fsk/pic/photo/2.5/advertise/pic/3H8AS4A78AG2FKKLX9EM.jpg\",\n" + 
				"        \"displayMode\": \"text\",\n" + 
				"        \"textDefine\": [\n" + 
				"            {\n" + 
				"                \"textType\": \"title\",\n" + 
				"                \"textColor\": \"#000000\",\n" + 
				"                \"textSize\": 24,\n" + 
				"                \"textLine\": 1,\n" + 
				"                \"textWidth\": 240,\n" + 
				"                \"textStyle\": \"bpld\",\n" + 
				"                \"paddingTop\": 24,\n" + 
				"                \"paddingLeft\": 10\n" + 
				"            },\n" + 
				"            {\n" + 
				"                \"textType\": \"caption\",\n" + 
				"                \"textColor\": \"#000000\",\n" + 
				"                \"textSize\": 20,\n" + 
				"                \"textWidth\": 240,\n" + 
				"                \"textLine\": 3,\n" + 
				"                \"textStyle\": \"normal\",\n" + 
				"                \"paddingTop\": 64,\n" + 
				"                \"paddingLeft\": 10\n" + 
				"            }\n" + 
				"        ],\n" + 
				"        \"eventTopicUUID\": \"6D6653666C7A6C6EE394E4\",\n" + 
				"        \"eventCount\": 8,\n" + 
				"        \"runJson\": {\n" + 
				"            \"runType\": \"actionName\",\n" + 
				"            \"setFlags\": 0,\n" + 
				"            \"actionName\": \"com.sharp.fxc.intent.action.TopicUI\",\n" + 
				"            \"bundle\": [\n" + 
				"                {\n" + 
				"                    \"extra\": \"template,topID,uuid,pageNo,count,version\",\n" + 
				"                    \"param\": \"8,530,6D6653666C7A6C6EE394E4,1,20,3300\",\n" + 
				"                    \"paramType\": \"String\"\n" + 
				"                }\n" + 
				"            ]\n" + 
				"        }\n" + 
				"    }\n" + 
				"}";
		String result = readValUsingJsonPath(json, "newsPosts.eventCount");
		System.out.println("result:" + result);
	}
	
	public static String readValUsingJsonPath(String json, String path) {
	    if (json == null || path == null) {
	      return null;
	    }
	    try {
	      Object val = JsonPath.read(json, "$." + path);
	      return val == null ? null : val.toString();
	    } catch (Exception ex) {
	      return null;
	    }
	  }
}
