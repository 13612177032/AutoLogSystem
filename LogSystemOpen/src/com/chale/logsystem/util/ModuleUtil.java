package com.chale.logsystem.util;

import java.util.ArrayList;
import java.util.List;

import com.chale.logsystem.bean.ModuleInterface;
import com.chale.logsystem.bean.ModuleModel;

 
 
 
public class ModuleUtil {
	
	 
	 

	
	public final static List<ModuleModel> change(final ModuleInterface t) {
		
		List<ModuleModel> list=new ArrayList<ModuleModel>();
		
		ModuleInterface _t=t;
 		while(_t!=null){
 			list.add(new ModuleModel(_t.getName(), _t.toString()));
 			_t=_t.getFather(); 			
 		}		
 		
 		return list;
 		
	}

}

