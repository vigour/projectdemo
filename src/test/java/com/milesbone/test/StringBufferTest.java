package com.milesbone.test;

import org.junit.Test;

import junit.framework.TestCase;

public class StringBufferTest {

	@Test
	public void replaceTest() {

		StringBuffer buff = new StringBuffer("Java Util Package");
		System.out.println("buffer = " + buff);

		// replace substring from index 5 to index 9
		buff.replace(5, 9, "Lang");
		// prints the stringbuffer after replacing
		System.out.println("After replacing: " + buff);
	}
}
