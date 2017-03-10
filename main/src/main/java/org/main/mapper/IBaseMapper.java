package org.main.mapper;

import java.util.List;
import java.util.Map;

import org.main.entity.BaseEntity;

/**
 * 
 * @ClassName: IBaseMapper
 * @author: <font color="red"><b>ZhaoFei</b></font>
 * @date: 2017年3月10日 下午2:07:07
 * @version: 1.0
 * @Description: 顶层基础Mapper
 * @param <T>
 */
public abstract interface IBaseMapper<T extends BaseEntity> {

	/**
	 * 
	 * @author <font color="green"><b>Zhao.Fei</b></font>
	 * @param entity
	 * @return
	 * @return {@link int}
	 * @date 2017年3月10日 下午2:07:16
	 * @version 1.0
	 * @description 新增对象
	 */
	abstract int insert(T entity);

	/**
	 * 
	 * @author <font color="green"><b>Zhao.Fei</b></font>
	 * @param entity
	 * @return
	 * @return {@link int}
	 * @date 2017年3月10日 下午2:07:26
	 * @version 1.0
	 * @description 删除对象
	 */
	abstract int delete(T entity);

	/**
	 * 
	 * @author <font color="green"><b>Zhao.Fei</b></font>
	 * @param entity
	 * @return
	 * @return {@link int}
	 * @date 2017年3月10日 下午2:07:36
	 * @version 1.0
	 * @description 修改对象
	 */
	abstract int update(T entity);

	/**
	 * 
	 * @author <font color="green"><b>Zhao.Fei</b></font>
	 * @param entity
	 * @return
	 * @return {@link Map<String,Object>}
	 * @date 2017年3月10日 下午2:07:48
	 * @version 1.0
	 * @description 查询单个对象
	 */
	abstract Map<String, Object> selectOne(T entity);

	/**
	 * 
	 * @author <font color="green"><b>Zhao.Fei</b></font>
	 * @param entity
	 * @return
	 * @return {@link Map<String,Object>}
	 * @date 2017年3月10日 下午2:07:56
	 * @version 1.0
	 * @description 检查数据是否存在
	 */
	abstract Map<String, Object> selectIsExists(T entity);

	/**
	 * 
	 * @author <font color="green"><b>Zhao.Fei</b></font>
	 * @param entity
	 * @return
	 * @return {@link List<Map<String,Object>>}
	 * @date 2017年3月10日 下午2:08:07
	 * @version 1.0
	 * @description 查询多条记录
	 */
	abstract List<Map<String, Object>> selectAll(T entity);
}