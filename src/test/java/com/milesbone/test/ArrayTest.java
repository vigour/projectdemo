package com.milesbone.test;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

public class ArrayTest {

	
	@Test
	public void testArrayContain() {
		String[] arrSrc = new String[]{"a_1","b_2","a"};
		
		System.out.println(ArrayUtils.contains(arrSrc, "b"));
		System.out.println(ArrayUtils.contains(arrSrc, "a_2"));
		System.out.println(ArrayUtils.contains(arrSrc, "a_3"));
	}
	@Test
	public void testArrayremove() {
		String[] arrSrc = new String[]{"a_1","b_2","a"};
//		String[] result = ArrayUtils.removeElements(arrSrc, "a");
//		result = ArrayUtils.removeElements(result, "b_2");
//		for(int i=0;i<result.length;i++)
//		System.out.println(result[i]);
		
		String[] tempArr = new String[]{"a","b_2"};
		String[] result = ArrayUtils.removeElement(arrSrc, tempArr);
		for(int i=0;i<result.length;i++)
			System.out.println(result[i]);
		
//		System.out.println(ArrayUtils.contains(arrSrc, "b"));
//	    ArrayUtils.removeElements(["a", "b", "a"], "a")      = ["b", "a"]
//	    ArrayUtils.removeElements(["a", "b", "a"], "a", "a") = ["b"]
//		System.out.println(ArrayUtils.contains(arrSrc, "a_2"));
//		System.out.println(ArrayUtils.contains(arrSrc, "a_3"));
	}
}
