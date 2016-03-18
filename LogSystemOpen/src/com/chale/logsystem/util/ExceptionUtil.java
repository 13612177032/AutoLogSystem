package com.chale.logsystem.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtil {
	
	public static String getPath(Exception e){
		StringBuffer sb=new StringBuffer(e.getStackTrace()[0].getClassName()).append(".");
		return   sb.append(e.getStackTrace()[0].getMethodName()).append(":")
				   .append(e.getStackTrace()[0].getLineNumber()).toString();

	}
	
	public static String getContent(Exception e){
		StringWriter sw = new StringWriter();
	     e.printStackTrace(new PrintWriter(sw, true));
	    return sw.toString();

	}
	
 
	 
 
}
