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

import entity.Service;

/**
 * @ClassName ServiceDao
 * @Desc Service表的数据访问接口
 * @author chengbao_0
 * @Date 2020-7-20 11:23:01
 */
public interface ServiceDao {
	/**
	 * @Title: getAllService 
	 * @Description: 查询所有服务类别
	 * @param @return
	 * @return List<Service> 所有服务类别的List
	 * @throws 
	 */
	public abstract List<Service> getAllService();
	/**
	 * @Title: getService 
	 * @Description: 根据查询条件查询服务类别
	 * @param @param sql 预编译的查询语句
	 * @param @param param 查询语句的参数
	 * @param @return
	 * @return Service 符合条件的服务类别
	 * @throws 
	 */
	public abstract Service getService(String sql,String[] param);
	/**
	 * @Title: updateService 
	 * @Description: 更新服务类别
	 * @param @param sql 预编译的更新语句
	 * @param @param param 数据库更新语句的参数
	 * @param @return
	 * @return int 判断更新语句是否执行成功，返回值>0时成功
	 * @throws 
	 */
	public abstract int updateService(String sql,Object[] param);
	/**
	 * @Title: getCount 
	 * @Description: 获取表中数据个数
	 * @param sql 预编译的sql语句
	 * @param param sql语句的参数
	 * @return int 数据的个数
	 */
	public abstract int getCount();
}
