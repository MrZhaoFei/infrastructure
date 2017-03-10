package org.main.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @ClassName: RequiresRoles
 * @author: <font color="red"><b>ZhaoFei</b></font>
 * @date: 2017年3月10日 下午2:05:22
 * @version: 1.0
 * @Description: 类似于shiro的角色注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequiresRoles {
	/**
	 * 
	 * @author <font color="green"><b>Zhao.Fei</b></font>
	 * @return
	 * @return {@link String[]}
	 * @date 2017年3月10日 下午2:05:28
	 * @version 1.0
	 * @description 角色描述值
	 */
	String[] value();
}
