package com.milesbone.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

import junit.framework.TestCase;

public class ListTest extends TestCase{

	@Test
	public void testList() {
		List<JSONObject> list = new ArrayList<JSONObject>();
		System.out.println(list.isEmpty());
	}
	
	
	@Test
	public void testSubList() {
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0;i<10; i++) {
			list.add(i);
		}
		int totalsize = list.size();
		int pagesize = 3;
		int pageNum = totalsize / pagesize + 1;
		System.out.println();
		int fromIndex = 0;
		int toIndex = 0;
		List<Integer> tempList = new ArrayList<Integer>();
		for(int j=0;j<pageNum; j++) {
			if(!tempList.isEmpty()) {
				tempList.clear();
			}
			fromIndex = j*pagesize;
			toIndex = (j*pagesize + pagesize)<=totalsize?((j+1)*pagesize):totalsize;

			tempList.addAll(list.subList(fromIndex, toIndex));
			System.out.println(tempList.size());
		}
			
		
	}
}
