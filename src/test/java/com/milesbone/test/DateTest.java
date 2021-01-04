package com.milesbone.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import org.junit.Test;

public class DateTest {
	
	
	@Test
	public void testDateStart() {
		Long zero = System.currentTimeMillis()/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();
		System.out.println(zero);
		Calendar c = Calendar.getInstance();
		c.set(2019, 4, 6,0,0,0);
		c.set(Calendar.MILLISECOND, 0);
		System.out.println(c.getTimeInMillis());
	
		
		
	}
}
