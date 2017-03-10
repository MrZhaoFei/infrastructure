package org.system;

public class Global {

	/**
	 * @Fields <font color="blue">TOKEN_FLAG</font>
	 * @description token标识列
	 */
	public static final String TOKEN_FLAG = "token";
	/**
	 * @Fields <font color="blue">STORE_ID</font>
	 * @description 结算用户库ID
	 */
	public static final String STORE_ID = "storeId";

	/**
	 * @Fields <font color="blue">PREFIX</font>
	 * @description 缓存前缀
	 */
	private static final String PREFIX = "fees_";
	/**
	 * @Fields <font color="blue">CACHE_PERMISSION</font>
	 * @description 权限缓存
	 */
	public static final String CACHE_PERMISSION = PREFIX + "permissionCache";
	/**
	 * @Fields <font color="blue">CACHE_USER</font>
	 * @description 用户信息缓存
	 */
	public static final String CACHE_USER = PREFIX + "userCache";
	/**
	 * @Fields <font color="blue">MANAGER_ROLE_ID</font>
	 * @description 超级管理员角色ID
	 */
	public static final Integer MANAGER_ROLE_ID = 1;

}
