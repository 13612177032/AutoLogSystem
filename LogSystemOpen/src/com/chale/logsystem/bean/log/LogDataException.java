package com.chale.logsystem.bean.log;

import java.io.Serializable;
import java.util.List;

import com.chale.logsystem.bean.ModuleModel;

public class LogDataException  extends LogData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String EXCEPTION_FLAG_SYSTEM="system";
	public static final String EXCEPTION_FLAG_USER="user";
	
	private String path;
 
	//异常�?单描�?
	private String name;
	//是否属于系统异常
	private String falg=EXCEPTION_FLAG_USER;
	//异常详细描述
	private String info;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFalg() {
		return falg;
	}
	public void setFalg(String falg) {
		this.falg = falg;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public LogDataException(String threadCode, List<ModuleModel> modules,
			String keyword, Long recordTime,  String path, String name, String falg, String info, String remark) {
		super(threadCode, modules, keyword, recordTime, LogData.LOGTYPE_EXCEPTION);
		this.path = path;
 		this.name = name;
		this.falg = falg;
		this.info = info;
 		this.setRemark(remark);
	}
	
	
	
 	 
	

}
