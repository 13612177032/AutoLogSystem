package com.chale.logsystem.api;

   import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.chale.logsystem.mq.LMMSActiveMQConfigUtil;
import com.chale.logsystem.util.ExceptionUtil;
import com.chale.logsystem.util.LogThreadUtil;
import com.chale.logsystem.util.MethodUtil;
import com.chale.logsystem.util.ModuleUtil;
import com.chale.logsystem.util.PropertiesUtil;
import com.chale.logsystem.util.ThreadMessage;
import com.chale.logsystem.annotation.ServiceLog;
import com.chale.logsystem.bean.ModuleInterface;
import com.chale.logsystem.bean.log.LogData;
import com.chale.logsystem.bean.log.LogDataApi;
import com.chale.logsystem.bean.log.LogDataCommon;
import com.chale.logsystem.bean.log.LogDataException;
import com.chale.logsystem.bean.log.LogDataInterface;
import com.chale.logsystem.bean.log.LogDataMethod;
import com.chale.logsystem.bean.log.LogRecord;
 
  
 
 

public class HkaLog{
	
	 
	private static final String controllerKeyword="CONTROLLER_KEYWORD";
	private static final String controllerRemark="CONTROLLER_REMARK";
	private static final String PROJECT=PropertiesUtil.getProperties("project");
	private static final String SERVER=PropertiesUtil.getProperties("server");

//	protected transient static final Log log = LogFactory.getLog(getClass());

 	/**
 	 *  改方法放置在 filter 执行后，beginTime放置执行前的参数 <br>
 	 *    该方法不写在过滤器中，则接口数据不会打印 <br>
 	 *  eg:<br>  
 	 * 
 	 *  {@code
 	 *  //�?始时�? 
	 *	long before = System.currentTimeMillis(); 
	 *  //执行过滤�? 
	 *	chain.doFilter(request, response);	 
	 *  //记录日志 
	 *	HkaLog.filter(before,request,response);}
	 *   
 	 */
 	public static void filter(Long beginTime, ServletRequest request,
			ServletResponse response) {
		// TODO Auto-generated method stub
		try {
			Long endTime=System.currentTimeMillis();
			ThreadMessage info = LogThreadUtil.get();			
			//如果记录日志
			if (info.isLog()) {
				HttpServletRequest httpRequest=(HttpServletRequest) request;
				HttpServletResponse httpResponse=(HttpServletResponse) response;
				
				String url=httpRequest.getPathInfo();
				String threadCode=info.getCode();
				ModuleInterface module=info.getModule();
				String keyword=info.get(controllerKeyword).toString();
				String remark=info.get(controllerRemark).toString();
			
				Map<String, String[]> requestParams = request.getParameterMap();
				String requestJson=null;
				if (requestParams!=null) {
					try {
						requestJson=JSONObject.toJSONString(requestParams);
					} catch (Exception e) {
						requestJson=requestParams.toString();
					}
				}						
				LogData data=new LogDataInterface(
						threadCode, 
						ModuleUtil.change(module), 
						keyword, 
						endTime, 
						url, 
						requestJson, 
						null, 
						beginTime);
				data.setRemark(remark);
				
				LogRecord record=new LogRecord(PROJECT,SERVER, data);
				
				LMMSActiveMQConfigUtil.sendMQMsg(record);				 
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
 	
 	/**
 	 * 在controller之前确定是否记录该接口的日志和确定该线程的模块信息和关键字，包含同一线程下的 接口日志，方法日志，缺省模块字段的异常日志�?�自定义日志
 	 * @param module 模块
 	 * @param keyword  关键�?
 	 * @param remark 备注
 	 */
 	public static void controller(ModuleInterface module,String keyword,String remark){
 		controller(module, keyword, remark,true);
 	}
 	/**
 	 * 在controller之前确定是否记录该接口的日志和确定该线程的模块信息和关键字，包含同一线程下的 接口日志，方法日志，缺省模块字段的异常日志�?�自定义日志
 	 * @param module 模块
 	 * @param keyword  关键�?
 	 * @param remark 备注
 	 * @param isOpen 是否�?启，缺省默认为ture
 	 */
 	public static void controller(ModuleInterface module,String keyword,String remark,boolean isOpen){
 		try {
			LogThreadUtil.get().getMessageNotNull().put(controllerKeyword, keyword);
			LogThreadUtil.get().getMessageNotNull().put(controllerRemark, remark);
			LogThreadUtil.get().setModule(module);
			LogThreadUtil.get().setLog(isOpen);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}
 	 	
 
 	/**
 	 * 第三方接口调用日�?
 	 * @param module   模块
 	 * @param keyword  关键�?
 	 * @param url      地址
 	 * @param beginTime  �?始时间，请在api调用之前获取时间  System.currentTimeMillis();
 	 * @param paramString  参数
 	 * @param returnResult  返回
 	 * @param remark   备注
 	 */
 	public static void api(ModuleInterface module, String keyword, String url, Long beginTime, String paramString,  String returnResult,String remark){
		try {
			Long endTime=System.currentTimeMillis();
			ThreadMessage info = LogThreadUtil.get();		
			//如果记录日志
				String threadCode=info.getCode();
				if (keyword==null || keyword.trim().equals("")) {
					keyword=info.get(controllerKeyword).toString();
				} 			
				LogData data=new LogDataApi(
						threadCode, 
						ModuleUtil.change(module), 
						keyword, 
						endTime, 
						url, 
						MethodUtil.getFatherPath(), 
						remark, 
						beginTime, 
						paramString, returnResult);
				LogRecord record=new LogRecord(PROJECT,SERVER, data);
				
				LMMSActiveMQConfigUtil.sendMQMsg(record);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				 
 	}
 	 
 	
 	
  	/**
 	 * 使用controller的开关和模块管理 ，没有记�? controller则不进行日志打印
 	 */
 	public static void exception(Exception e,String remark){
 		try {
			ThreadMessage info = LogThreadUtil.get();		
			if (info.isLog()) {
				exception(null,   LogThreadUtil.get().get(controllerKeyword).toString(),e,null);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
 	/**
 	 * @param e
 	 * 系统级异常捕获，�?要在在一场统�?管理中进行埋�?
 	 */
 	public static void systemException(Exception e){
		try {
			ThreadMessage info = LogThreadUtil.get();		
			if (info.isLog()) {

			exception(null, e,  info.get(controllerKeyword).toString(),null,LogDataException.EXCEPTION_FLAG_SYSTEM);

			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
 	/**
 	 * 与controller的开关独�?
 	 */
	public static void exception(ModuleInterface module, String keyword, Exception e, String remark){
		exception(module, e, keyword, remark, LogDataException.EXCEPTION_FLAG_USER);
	}
	
	
	/**
	 * 自定义日�? info级别，与controller配置无关
	 * @param module
	 * @param keyword
	 * @param data
	 * @param remark
	 */
	public static void info(ModuleInterface module, String keyword, String data, String remark){
		commonLog(module, keyword, remark, data, MethodUtil.getFatherPath(), LogDataCommon.LEVEL_INFO);
	}
	/**
	 * 自定义日�? dubug级别，与controller配置无关
	 * @param module
	 * @param keyword
	 * @param data
	 * @param remark
	 */
	public static void dubug(ModuleInterface module, String keyword, String data, String remark){
		commonLog(module, keyword, remark, data, MethodUtil.getFatherPath(), LogDataCommon.LEVEL_DUBUG);
	}
	/**
	 * 自定义日�? error级别，与controller配置无关
	 * @param module
	 * @param keyword
	 * @param data
	 * @param remark
	 */
	public static void error(ModuleInterface module, String keyword, String data, String remark){
		commonLog(module, keyword, remark, data, MethodUtil.getFatherPath(), LogDataCommon.LEVEL_ERROR);
	}
	/**
	 * 自定义日�? warn级别，与controller配置无关
	 * @param module
	 * @param keyword
	 * @param data
	 * @param remark
	 */
	public static void warn(ModuleInterface module, String keyword, String data, String remark){
		commonLog(module, keyword, remark, data, MethodUtil.getFatherPath(), LogDataCommon.LEVEL_WARN);
	}
	/**
	 * 自定义日�? fatal级别，与controller配置无关
	 * @param module
	 * @param keyword
	 * @param data
	 * @param remark
	 */
	public static void fatal(ModuleInterface module, String keyword, String data, String remark){
		commonLog(module, keyword, remark, data, MethodUtil.getFatherPath(), LogDataCommon.LEVEL_FETAL);
	}
	/**
	 * 自定义日�? info级别，使用controller配置
	 * @param module
	 * @param keyword
	 * @param data
	 * @param remark
	 */
	public static void info( String data, String remark){
		commonLog(remark, data, MethodUtil.getFatherPath(), LogDataCommon.LEVEL_INFO);
	}
	/**
	 * 自定义日�? dubug级别，使用controller配置
	 * @param module
	 * @param keyword
	 * @param data
	 * @param remark
	 */
	public static void dubug(  String data, String remark){
		commonLog(remark, data, MethodUtil.getFatherPath(), LogDataCommon.LEVEL_DUBUG);
	}
	/**
	 * 自定义日�? error级别，使用controller配置
	 * @param module
	 * @param keyword
	 * @param data
	 * @param remark
	 */
	public static void error( String data, String remark){
		commonLog(remark, data, MethodUtil.getFatherPath(), LogDataCommon.LEVEL_ERROR);
	}
	/**
	 * 自定义日�? warn级别，使用controller配置
	 * @param module
	 * @param keyword
	 * @param data
	 * @param remark
	 */
	public static void warn( String data, String remark){
		commonLog(remark, data, MethodUtil.getFatherPath(), LogDataCommon.LEVEL_WARN);
	}
	/**
	 * 自定义日�? fatal级别，使用controller配置
	 * @param module
	 * @param keyword
	 * @param data
	 * @param remark
	 */
	public static void fatal(  String data, String remark){
		commonLog(remark, data, MethodUtil.getFatherPath(), LogDataCommon.LEVEL_FETAL);
	}
	
	
	
	
	
	
	private static void commonLog( String remark,String data,String path,String level){
		try {
			ThreadMessage info = LogThreadUtil.get();		
			if (info.isLog()) {
				commonLog(info.getModule(),info.get(controllerKeyword).toString(), remark, data, MethodUtil.getFatherPath(), LogDataCommon.LEVEL_FETAL);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void commonLog(ModuleInterface module, String keyword, String remark,String data,String path,String level){
		try {
			Long endTime=System.currentTimeMillis();
			ThreadMessage info = LogThreadUtil.get();		
			//如果记录日志
				if (module==null) {
					module=info.getModule();
				}
				
				LogData logData=new LogDataCommon(
						info.getCode(), ModuleUtil.change(module), keyword, endTime,path, data, level, remark);
				
				LogRecord record=new LogRecord(PROJECT,SERVER, logData);
				
				LMMSActiveMQConfigUtil.sendMQMsg(record);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				 
	}
	
	
	
	
	
	
	/**
 	 * 仅为注解的方法所自动调用
 	 */
 	protected static void method(String path, String keyword, String remark, Long beginTime, String paramStringIn, String paramStringOut, String returnResult){
		try {
			Long endTime=System.currentTimeMillis();
			ThreadMessage info = LogThreadUtil.get();		
			//如果记录日志
			if (info.isLog()) {
				String threadCode=info.getCode();
				ModuleInterface module=info.getModule();
				if (keyword==null || keyword.trim().equals("")) {
					keyword=info.get(controllerKeyword).toString();
				}else{
					keyword=info.get(keyword).toString();
				} 		 					
				LogData data=new LogDataMethod(
						threadCode, 
						ModuleUtil.change(module), 
						keyword, 
						endTime, 
						path, 
						remark, 
						beginTime, 
						paramStringIn, 
						paramStringOut, 
						returnResult);
				LogRecord record=new LogRecord(PROJECT,SERVER, data);
				
				LMMSActiveMQConfigUtil.sendMQMsg(record);				 
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
 	
 	
 	
 	
 	private static void exception(ModuleInterface module, Exception e, String keyword, String remark,String flag){
		try {
			Long endTime=System.currentTimeMillis();
			ThreadMessage info = LogThreadUtil.get();		
			//如果记录日志
				if (module==null) {
					module=info.getModule();
				}
				
				LogData data=new LogDataException(
						info.getCode(), 
						ModuleUtil.change(module), 
						keyword, 
						endTime, 
						ExceptionUtil.getPath(e), 
						e.toString(),
						flag, 
						ExceptionUtil.getContent(e), 
						remark);
				
				LogRecord record=new LogRecord(PROJECT,SERVER, data);
				
				LMMSActiveMQConfigUtil.sendMQMsg(record);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}				 
 	}
	 

}
