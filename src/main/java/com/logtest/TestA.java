package com.logtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestA {
	
	private static Logger log = LoggerFactory.getLogger(TestA.class);
	
	public TestA(){
		log.debug("TestA-debug：{}",11211111);
		log.info("TestA-info:{}", 222222222);
		log.warn("TestA-warn");
		log.error("TestA-debug");
	}
}
