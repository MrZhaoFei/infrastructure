package org.main.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @ClassName: RequiresPermissions
 * @author: <font color="red"><b>ZhaoFei</b></font>
 * @date: 2017年3月10日 下午2:04:51
 * @version: 1.0
 * @Description: 类似于shiro的权限注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequiresPermissions {
	/**
	 * 
	 * @author <font color="green"><b>Zhao.Fei</b></font>
	 * @return
	 * @return {@link String[]}
	 * @date 2017年3月10日 下午2:04:59
	 * @version 1.0
	 * @description 权限描述值
	 */
	String[] value();
}
