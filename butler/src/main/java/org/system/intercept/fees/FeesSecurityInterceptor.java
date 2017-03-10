package org.system.intercept.fees;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.main.annotation.RequiresMethods;
import org.main.annotation.RequiresRoles;
import org.main.result.ResultCode;
import org.main.result.ResultMap;
import org.redis.cache.RedisCacheManager;
import org.redis.cache.authentication.AuthenticationSession;
import org.redis.cache.authentication.AuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.system.Global;
import org.system.service.impl.user.UserService;

import com.alibaba.fastjson.JSON;

/**
 * @author <font color="red"><b>Liu.Gang.Qiang</b></font>
 * @Date 2016年11月22日
 * @Version 1.0
 * @Description 计费系统权限拦截器<拦截路径"/fees/**">
 */
public class FeesSecurityInterceptor extends HandlerInterceptorAdapter {

	Logger log = LoggerFactory.getLogger(FeesSecurityInterceptor.class);
	/**
	 * @Fields <font color="blue">CACHENAME</font>
	 * @description 用户信息的缓存信息
	 */
	private final String CACHENAME = Global.CACHE_USER;
	/**
	 * @Fields <font color="blue">USER_ID</font>
	 * @description 查询权限时的用户ID
	 */
	private final String USER_ID = "userId";
	/**
	 * @Fields <font color="blue">STORE_ID</font>
	 * @description 查询权限时的结算用户库ID
	 */
	private final String  STORE_ID = "storeId";

	@Resource
	private RedisCacheManager cache;
	@Resource
	private UserService userService;

	/**
	 * @author <font color="green"><b>Liu.Gang.Qiang</b></font>
	 * @param list
	 * @param auths
	 * @return {@link Boolean}
	 * @date 2016年12月8日
	 * @version 1.0
	 * @description 校验权限
	 */
	private boolean validate(List<Map<String, Object>> list, String[] auths) {
		for (String auth : auths) {
			for (Map<String, Object> map : list) {
				if (auth.equals(map.get("value"))) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			boolean authc = false;
			/* 获取认证token */
			String token = request.getHeader(Global.TOKEN_FLAG);
			String storeId = request.getHeader(Global.STORE_ID);

			/* 获取方法 */
			HandlerMethod method = (HandlerMethod) handler;

			/* 获取注解 包含角色注解和权限注解 */
			RequiresRoles roles = method.getMethodAnnotation(RequiresRoles.class);
			RequiresMethods methodsSources = method.getMethodAnnotation(RequiresMethods.class);

			/* 实例化用户对象 缓存对象 */
			AuthenticationSession session = null;
			Map<String, Object> permissionMap = new HashMap<>();

			/* 设置响应头为Json */
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			/* 判断token */
			// 不为空 可能登录
			if (token != null && token != "") {
				/* 判断token是否符合系统定义规则 */
				if (token.length() <= 32) {
					/* token格式错误 */
					response.getWriter().write(JSON.toJSONString(ResultMap.convertMap(ResultCode.UNSIGNATURE)));
					return false;
				}
				/* 获取缓存 */
				session = cache.getSession(new AuthenticationToken(CACHENAME, token));
				if (session != null) {
					/* 获取用户唯一标识 这里默认采用用户的主键ID */
					Integer userId = (Integer) session.get(Map.class).get("id");
					/* 设置用户对象 ID属性 */
					permissionMap.put(USER_ID, userId);
					/* 设置已登录 */
					authc = true;
				}
			}

			if (roles != null) {
				/* 判断是否忽略校验 */
				if (roles.ignore()) {
					return true;
				} else if (roles.authc()) {
					if (authc) {
						return true;
					} else {
						/* 用户缓存信息不存在则表示未登录，可以提示用户登录后重试 */
						response.getWriter().write(JSON.toJSONString(ResultMap.convertMap(ResultCode.UNSIGNATURE)));
						return false;
					}
				}
				/* 设置结算用户库ID */
				permissionMap.put(STORE_ID, storeId);
				/* 方法角色注解不为空 获取用户角色集合 */
				List<Map<String, Object>> roleList = userService.getRoleList(permissionMap);
				if (validate(roleList, roles.value())) {
					return true;
				} else {
					if (session == null) {
						/* 用户缓存信息不存在则表示未登录，可以提示用户登录后重试 */
						response.getWriter().write(JSON.toJSONString(ResultMap.convertMap(ResultCode.UNSIGNATURE)));
					} else {
						/* 用户缓存信息存在则表示无权限，直接提示无权限操作 */
						response.getWriter().write(JSON.toJSONString(ResultMap.convertMap(ResultCode.UNAUTHORIZED)));
					}
					return false;
				}
			} else if (methodsSources != null) {
				/* 判断是否忽略校验 */
				if (methodsSources.ignore()) {
					return true;
				} else if (methodsSources.authc()) {
					if (authc) {
						return true;
					} else {
						/* 用户缓存信息不存在则表示未登录，可以提示用户登录后重试 */
						response.getWriter().write(JSON.toJSONString(ResultMap.convertMap(ResultCode.UNSIGNATURE)));
						return false;
					}
				}
				/* 设置结算用户库ID */
				permissionMap.put(STORE_ID, storeId);
				/* 方法权限注解不为空 获取用户权限集合 */
				List<Map<String, Object>> roleList = userService.getPermissionList(permissionMap);
				if (validate(roleList, methodsSources.value())) {
					return true;
				} else {
					if (session == null) {
						/* 用户缓存信息不存在则表示未登录，可以提示用户登录后重试 */
						response.getWriter().write(JSON.toJSONString(ResultMap.convertMap(ResultCode.UNSIGNATURE)));
					} else {
						/* 用户缓存信息存在则表示无权限，直接提示无权限操作 */
						response.getWriter().write(JSON.toJSONString(ResultMap.convertMap(ResultCode.UNAUTHORIZED)));
					}
					return false;
				}
			} else {
				/* 方法未定义安全访问提示此方法不可访问 */
				response.getWriter().write(JSON.toJSONString(ResultMap.convertMap(ResultCode.SYSTEM_DEFINE)));
				return false;
			}
		}
		return true;
	}
}
