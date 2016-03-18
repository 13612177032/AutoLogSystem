package com.chale.logsystem.util;

public class MethodUtil {
	public static String getFatherPath(){
        StackTraceElement[] eles = Thread.currentThread().getStackTrace();
         
        
		StringBuffer sb=new StringBuffer(eles[3].getClassName()).append(".");
		return   sb.append(eles[3].getMethodName()).append(":")
				   .append(eles[3].getLineNumber()).toString();

    }
	
	public static String getPath(){
        StackTraceElement[] eles = Thread.currentThread().getStackTrace();
        StringBuffer sb=new StringBuffer(eles[2].getClassName()).append(".");
		return   sb.append(eles[2].getMethodName()).append(":")
				   .append(eles[2].getLineNumber()).toString();

    }
	
	public static void main(String[] args) {
		log();
	}

	private static void log() {
		// TODO Auto-generated method stub
		System.out.println(getPath());
		System.out.println(getFatherPath());
	}
}
