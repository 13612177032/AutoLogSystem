package com.chale.logsystem.bean;

 
public interface ModuleInterface {
	
	ModuleInterface father=null;
	String name=null;
	
	public ModuleInterface getFather();
	public String getName();
 }
