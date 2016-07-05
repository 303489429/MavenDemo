package com.logtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestD {
	private static Logger log = LoggerFactory.getLogger(TestD.class);
	public TestD(){
		log.debug("TestD-debug");
		log.info("TestD-info");
		log.warn("TestD-warn");
		log.error("TestD-debug");
	}
}
