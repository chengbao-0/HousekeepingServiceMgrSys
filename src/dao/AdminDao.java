/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：数据访问接口
 * @Package: dao
 * @author: chengbao_0  
 * @date: 2020-7-29 20:19:49 
 */
package dao;

import java.util.List;

import entity.Admin;

/**
 * @ClassName AdminDao
 * @Desc Admin表的数据访问接口
 * @author chengbao_0
 * @Date 2020年7月16日 下午12:00:20
 */
public interface AdminDao {
	/**
	 * @Title: getAllAdmin 
	 * @Description: 查询所有管理员信息
	 * @return List<Admin>: 所有管理员的List
	 */
	public abstract List<Admin> getAllAdmin();
	/**
	 * @Title: selectAdmin 
	 * @Description: 根据查询条件查询管理员信息
	 * @param sql 预编译的查询语句
	 * @param param 查询语句的参数
	 * @return List<Admin> 符合条件的管理员List
	 */
	public abstract Admin getAdmin(String sql,String[] param);
	/**
	 * @Title: updateAdmin 
	 * @Description: 更新管理员信息
	 * @param sql 预编译的更新语句
	 * @param param 数据库更新语句的参数
	 * @return int 判断更新语句是否执行成功，返回值>0时成功
	 */
	public abstract int updateAdmin(String sql,Object[] param);
	/**
	 * @Title: getCount 
	 * @Description: 获取表中数据个数
	 * @param sql 预编译的sql语句
	 * @param param sql语句的参数
	 * @return int 数据的个数
	 */
	public abstract int getCount(String sql,Object[] param);
}
