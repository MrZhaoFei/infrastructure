package org.system.controller.iface.user;

import java.util.Map;

import org.main.annotation.RequiresMethods;
import org.main.annotation.RequiresRoles;
import org.main.entity.BaseEntity.Insert;
import org.main.entity.BaseEntity.SelectAll;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.system.entity.user.User;
import org.system.entity.user.User.Login;

@RequestMapping
public interface IUserController {

	/**
	 * 
	 * @author <font color="green"><b>Zhao.Fei</b></font>
	 * @param user
	 * @param result
	 * @return
	 * @return {@link Map<String,Object>}
	 * @date 2017年3月10日 下午1:57:42
	 * @version 1.0
	 * @description 用户登录 必须具备user:login这个权限的用户才能执行此方法
	 */
	@RequestMapping(value = { "/user/login" }, method = RequestMethod.GET)
	@ResponseBody
	@RequiresMethods(value = { "user:login" }, ignore = true)
	public Map<String, Object> login(@Validated({ Login.class }) User user, BindingResult result);

	/**
	 * 
	 * @author <font color="green"><b>Zhao.Fei</b></font>
	 * @param user
	 * @param result
	 * @return
	 * @return {@link Map<String,Object>}
	 * @date 2017年3月10日 下午1:58:06
	 * @version 1.0
	 * @description 新增用户 必须具备user:insert这个权限的用户才能执行此方法
	 */
	@RequestMapping(value = { "/user" }, method = RequestMethod.POST)
	@ResponseBody
	@RequiresMethods({ "user:insert" })
	public Map<String, Object> insertUser(@Validated({ Insert.class }) @RequestBody User user, BindingResult result);

	/**
	 * 
	 * @author <font color="green"><b>Zhao.Fei</b></font>
	 * @param user
	 * @param result
	 * @param token
	 * @return
	 * @return {@link Map<String,Object>}
	 * @date 2017年3月10日 下午1:58:23
	 * @version 1.0
	 * @description 获取用户列表 必须具有member这个角色的用户才能执行此方法
	 */
	@RequestMapping(value = { "/users" }, method = RequestMethod.GET)
	@ResponseBody
	@RequiresRoles({ "member", "visitors" })
	public Map<String, Object> getUserList(@Validated({ SelectAll.class }) User user, BindingResult result,
			@RequestHeader(value = "token", required = false) String token);

}
