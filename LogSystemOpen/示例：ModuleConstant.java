package com.hna.hka.aio.common.constants;

import com.hna.hka.lmms.bean.ModuleInterface;

public class ModuleConstant {
	public static final String PROJECT="aio_test";
	
	public static final String DEMO="测试";
	public static final String DEMO_TEST="测试—调试";
	public static final String DEMO_HELLO="测试-使用";
	public static final String YEAR="年票";
	public static final String YEAR_AV="年票运价查";
	public static final String YEAR_PV="年票出票";
	
	public enum  Module implements ModuleInterface{
		PROJECT(ModuleConstant.PROJECT,null),
				
		DEMO(ModuleConstant.DEMO,PROJECT),
		DEMO_HELLO(ModuleConstant.DEMO_HELLO,DEMO),
		DEMO_TEST(ModuleConstant.DEMO_TEST,DEMO),
		
		YEAR(ModuleConstant.YEAR,PROJECT),
		YEAR_AV(ModuleConstant.YEAR_AV,YEAR),
		YEAR_PV(ModuleConstant.YEAR_PV,YEAR);
 	
	 	private ModuleInterface father;
		private String name;
 	 	 
		private  Module(String name, ModuleInterface father) {
			this.father = father;
			this.name = name;
			 
		}

		public ModuleInterface getFather() {
			return father;
		}

		public String getName() {
			return name;
		}	
		
		
		public Module getByName(String name){
			Module[] ms=this.values();
			if (ms!=null) {
				for (Module module : ms) {
					if (module.getName()==name) {
						return module;
					}
				}
			}			
			return null;
		}
	}

	
}


