package com.chale.logsystem.api;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
 import com.chale.logsystem.annotation.ServiceLog;
 
public class LogAspect {
	
	
	/**
	 * 方法拦截
	 * @param point
	 * @throws Throwable
	 */
	public void doMethodLog(ProceedingJoinPoint  point) throws Throwable { 

	
		String paramStringIn=null;
		Long beginTime=null;
		//前流程，获取�?始时间，入参
		try {
			beginTime=System.currentTimeMillis();
			Object[] paramIn = point.getArgs();
			paramStringIn = JSONArray.toJSONString(paramIn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object result = point.proceed();
		//后流程，获取出参，返回，等信�?
		try {
			Object[] paramOut = point.getArgs();		

			Class[] parameterTypes = ((MethodSignature)point.getSignature()).getMethod().getParameterTypes();  
			 
			String methodName = point.getSignature().getName(); 
			Class targetClass = point.getTarget().getClass();   
			Method method = targetClass.getMethod(methodName,parameterTypes);
			 
			System.out.println(method.toString());
			    if (method != null) { 
			        boolean hasAnnotation = method.isAnnotationPresent(ServiceLog.class); 
			        if (hasAnnotation) { //判断是否为log的注�?
			        	ServiceLog annotation = method.getAnnotation(ServiceLog.class);   
			         	
			         	String path=method.toString();
			         	String remark = annotation.remark();
			         	String keyword = annotation.keyword();
			         	String paramStringOut=JSONArray.toJSONString(paramOut);
			         	String returnResult=JSONArray.toJSONString(result);
 			         	
			         	HkaLog.method(path,keyword,remark,beginTime,paramStringIn,paramStringOut,returnResult);
			        	
			         }   
			    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	}
	 
		
	
	

	/**
	 * 由于动�?�代理为jdk自带的，切不可随意变动，故无法拦截controller
	 * 未实�?
	 * @param point
	 * @throws Throwable
	 */
	@Deprecated
	public void doInterfaceLog(ProceedingJoinPoint  point) throws Throwable { 
//		
//		Date beginDate=new Date();
//		
//		System.out.println("已进入接口log管理系统�?"+new Date().toLocaleString());
//		
//		Object[] paramIn = point.getArgs();
//		String paramStringIn=JSONArray.toJSONString(paramIn);
//		String path=MethodSignature.class.cast(point.getSignature()).getMethod().getName();		
//		Object result = point.proceed();
//		Object[] paramOut = point.getArgs();		
//
//		Class[] parameterTypes = ((MethodSignature)point.getSignature()).getMethod().getParameterTypes();  
//         
//        String methodName = point.getSignature().getName(); 
//        Class targetClass = point.getTarget().getClass();   
//        Method method = targetClass.getMethod(methodName,parameterTypes); 
//		boolean is = ((MethodSignature)point.getSignature()).getMethod().isAnnotationPresent(ControllerLog.class);
//		ControllerLog aaa = ((MethodSignature)point.getSignature()).getMethod().getAnnotation(ControllerLog.class);
//        
//            if (method != null) { 
//                boolean hasAnnotation = method.isAnnotationPresent(ControllerLog.class); 
//                if (hasAnnotation) { //判断是否为log的注�?
//                	ControllerLog annotation = method.getAnnotation(ControllerLog.class);   
//                	String keyword = "";
//                	String remark = annotation.remark();
//                	String module = "";
//                	
//                	System.out.println("方法路径�?"+path);
//                	System.out.println("入参�?"+paramStringIn);
//                	System.out.println("出参�?"+JSONArray.toJSONString(paramOut));
//                	System.out.println("返回�?"+JSONObject.toJSONString(result));
//                	System.out.println("注解参数�?"+keyword+"|"+module+"|"+remark);
//                  }   
//            }   
// 		System.out.println("已出接口log管理系统�?"+new Date().toLocaleString());
//

	}
		
	}
