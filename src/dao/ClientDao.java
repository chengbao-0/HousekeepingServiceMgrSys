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

import entity.Client;

/**
 * @ClassName ClientDao
 * @Desc Client表的数据访问接口
 * @author chengbao_0
 * @Date 2020年7月16日 下午12:00:37
 */
public interface ClientDao {
	//能查询和更改自己的信息，不能查询其他客户的信息，但可以查询所有家政人员的信息
	/**
	 * 
	 * @Title: getAllClient 
	 * @Description: 查询所有会员信息
	 * @param @return
	 * @return List<Client> 包含所有会员的List
	 */
	public abstract List<Client> getAllClient();
	/**
	 * 
	 * @Title: selectClient 
	 * @Description: 根据查询条件查询会员信息
	 * @param @param sql 预编译的查询语句
	 * @param @param param 查询语句的参数
	 * @param @return
	 * @return List<Client> 符合条件的会员List
	 * @throws 
	 */
	public abstract List<Client> getClient(String sql,String[] param);
	/**
	 * 
	 * @Title: updateClient 
	 * @Description: 更新客户信息
	 * @param @param sql 预编译的更新语句
	 * @param @param param 数据库更新语句的参数
	 * @param @return
	 * @return int 判断更新语句是否执行成功，返回值>0时成功
	 * @throws 
	 */
	public abstract int updateClient(String sql,Object[] param);
	/**
	 * @Title: getCount 
	 * @Description: 获取表中数据个数
	 * @param sql 预编译的sql语句
	 * @param param sql语句的参数
	 * @return int 数据的个数
	 */
	public abstract int getCount(String sql,Object[] param);
}
