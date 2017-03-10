package org.main.entity;

/**
 * 
 * @ClassName: BaseEntity
 * @author: <font color="red"><b>ZhaoFei</b></font>
 * @date: 2017年3月10日 下午2:05:44
 * @version: 1.0
 * @Description: 顶层实体类抽象类 包含分页属性
 */
public class BaseEntity {

	/**
	 * @Fields <font color="blue">page</font>
	 * @description 当前页数 从1开始计数
	 */
	Integer page;
	/**
	 * @Fields <font color="blue">rows</font>
	 * @description 每页记录数 必须大于0
	 */
	Integer rows;
	/**
	 * @Fields <font color="blue">total</font>
	 * @description 记录总数 设置分页会自动封装此属性
	 */
	Integer total;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page > 0 ? page : 1;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows >= 0 ? rows : 10;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	/**
	 * 
	 * @ClassName: Insert
	 * @author: <font color="red"><b>ZhaoFei</b></font>
	 * @date: 2017年3月10日 下午2:05:58
	 * @version: 1.0
	 * @Description: 用于新增单条记录的校验分组接口
	 */
	public interface Insert {
	};

	/**
	 * 
	 * @ClassName: Update
	 * @author: <font color="red"><b>ZhaoFei</b></font>
	 * @date: 2017年3月10日 下午2:06:11
	 * @version: 1.0
	 * @Description: 用于修改单条记录的校验分组接口
	 */
	public interface Update {
	};

	/**
	 * 
	 * @ClassName: SelectAll
	 * @author: <font color="red"><b>ZhaoFei</b></font>
	 * @date: 2017年3月10日 下午2:06:21
	 * @version: 1.0
	 * @Description: 用于查询多条记录的校验分组接口
	 */
	public interface SelectAll {
	};

	/**
	 * 
	 * @ClassName: SelectOne
	 * @author: <font color="red"><b>ZhaoFei</b></font>
	 * @date: 2017年3月10日 下午2:06:32
	 * @version: 1.0
	 * @Description: 用于查询条单条记录的校验分组接口
	 */
	public interface SelectOne {
	};

	/**
	 * 
	 * @ClassName: Delete
	 * @author: <font color="red"><b>ZhaoFei</b></font>
	 * @date: 2017年3月10日 下午2:06:44
	 * @version: 1.0
	 * @Description: 用于删除记录的校验分组接口
	 */
	public interface Delete {
	};
}
