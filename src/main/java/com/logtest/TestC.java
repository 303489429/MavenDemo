package com.logtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestC {

	private static Logger log = LoggerFactory.getLogger(TestC.class);
	public TestC(){
		log.debug("TestC-debug");
		log.info("TestC-info");
		log.warn("TestC-warn");
		log.error("TestC-debug");
	}
}
