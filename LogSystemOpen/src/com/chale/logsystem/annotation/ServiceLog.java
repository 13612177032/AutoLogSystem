package com.chale.logsystem.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

 
/**
 * @author liangcl
 * keyword 关键字的key�? <br>
 * remark 备注<br>
 *
 *{@code		
		@ServiceLog(keyword="student_method") <br>
		public Student study(Student s) {<br>
		// TODO Auto-generated method stub<br>
		LogThreadUtil.get().put("student_method","xxxxxxxssdw");<br>}
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceLog {
//	String module();
	String keyword() default "";
	String remark() default "";

}
