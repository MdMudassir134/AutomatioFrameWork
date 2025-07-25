package com.mystore.utility;

import org.apache.log4j.Logger;

public class Log {
	
	public static Logger log = Logger.getLogger(Log.class.getName());
	
	public static void startTestCase(String sTartTestCaseName) {
		log.info("====================================="+sTartTestCaseName+ " TEST START=========================================");
	}
	public static void endTestCase(String sTestCaseName){
		log.info("====================================="+sTestCaseName+" TEST END=========================================");
		 }
	
	// Need to create below methods, so that they can be called  

	 public static void info(String message) {

		 log.info(message);

			}

	 public static void warn(String message) {

		 log.warn(message);

		}

	 public static void error(String message) {

		 log.error(message);

		}

	 public static void fatal(String message) {

		 log.fatal(message);

		}

	 public static void debug(String message) {

		 log.debug(message);

		}
	
}


