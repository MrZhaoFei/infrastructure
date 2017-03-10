package org.system.message;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 
 * @ClassName: Prompt
 * @author: <font color="red"><b>ZhaoFei</b></font>
 * @date: 2017年3月10日 下午2:35:58
 * @version: 1.0
 * @Description: 系统提示语国际化 默认当前语言环境
 */
public class Prompt {
	private static String filePath = "properties.prompt.prompt";

	/**
	 * <font color="red">无占位符的字符资源</font>
	 * 
	 * @Title bundle
	 * @param key
	 * @return {@linkplain String}
	 * @since 1.0
	 */
	public static String bundle(String key) {
		return ResourceBundle.getBundle(filePath, Locale.getDefault()).getString(key);
	}

	/**
	 * <font color="red">有占位符的字符资源</font>
	 * 
	 * @Title bundle
	 * @param key
	 * @param arguments
	 * @return {@linkplain String}
	 * @since 1.0
	 */
	public static String bundle(String key, Object... arguments) {
		return MessageFormat.format(ResourceBundle.getBundle(filePath, Locale.getDefault()).getString(key), arguments);
	}
}
