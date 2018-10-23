package com.milesbone.test.http;

import org.junit.Test;

import com.milesbone.util.MACUtil;

public class MacUtilTest {
	
	
	@Test
	public void randomMacTest() {
		MACUtil mac = new MACUtil();
		for (int i = 0; i < 5; i++) {
			System.out.println("randomMac:       " + mac.randomMac());
		}
		System.out.println("------------------->");
		for (int i = 0; i < 5; i++) {
			Integer[] oui = { 0x32, 0x21, 0xa2 };
			System.out.println("randomMacWithOui:" + mac.randomMacWithOui(oui));
		}
		System.out.println("------------------->");
		for (int i = 0; i < 5; i++) {
			System.out.println("randomMac4Xen:   " + mac.randomMac4Xen());
		}
		System.out.println("------------------->");
		for (int i = 0; i < 5; i++) {
			System.out.println("randomMac4Qemu:  " + mac.randomMac4Qemu());
		}
	}
}
