package com.chale.logsystem.bean;

import java.io.Serializable;

public class ModuleModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String moduleName;
	private String moduleCode;
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getModuleCode() {
		return moduleCode;
	}
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
	public ModuleModel(String moduleName, String moduleCode) {
		super();
		this.moduleName = moduleName;
		this.moduleCode = moduleCode;
	}
	
	
	
}

