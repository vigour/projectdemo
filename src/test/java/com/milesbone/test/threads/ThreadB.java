package com.milesbone.test.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadB implements Runnable{
	
	private static final Logger log = LoggerFactory.getLogger(ThreadB.class);

	@Override
	public void run() {
		log.debug("Syncronized test!!!");
		
		log.debug("Syncronized test!!!");
	}

}
