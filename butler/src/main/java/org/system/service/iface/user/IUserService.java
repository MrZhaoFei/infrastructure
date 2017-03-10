package org.system.service.iface.user;

import java.util.List;
import java.util.Map;

import org.system.entity.user.User;

public interface IUserService {
	/**
	 * 
	 * @author <font color="green"><b>Zhao.Fei</b></font>
	 * @param user
	 * @return
	 * @return {@link List<Map<String,Object>>}
	 * @date 2017年3月10日 下午2:03:38
	 * @version 1.0
	 * @description 获取用户角色集合
	 */
	List<Map<String, Object>> getRoleList(Map<String, Object> permissionMap);

	List<Map<String, Object>> getRoleList(User user);
	/**
	 * 
	 * @author <font color="green"><b>Zhao.Fei</b></font>
	 * @param user
	 * @return
	 * @return {@link List<Map<String,Object>>}
	 * @date 2017年3月10日 下午2:03:47
	 * @version 1.0
	 * @description 获取用户权限集合
	 */
	List<Map<String, Object>> getPermissionList(Map<String, Object> permissionMap);
	
	List<Map<String, Object>> getPermissionList(User user);
	
	Map<String, Object> getUser(User user);

	int insertUser(User user);

	List<Map<String, Object>> getUserList(User user);

	Map<String, Object> selectExist(User user);

}
