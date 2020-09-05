/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：数据访问接口
 * @Package: dao 
 * @author: chengbao_0  
 * @date: 2020-7-29 20:21:18 
 */
package dao;

import java.util.List;

import entity.ApplyForHousekeeper;

/**
 * @ClassName ApplyForHousekeeper
 * @Desc ApplyForHousekeeper表的数据访问接口
 * @author chengbao_0
 * @Date 2020-7-29 20:21:18
 */
public interface ApplyForHousekeeperDao {
	/**
	 * @Title: getAllApplyForHousekeeper 
	 * @Description: 获取所有待审核的家政人员信息
	 * @return List<ApplyForHousekeeper> 所有待审核家政人员的List
	 */
	public abstract List<ApplyForHousekeeper> getAllApplyForHousekeeper();
	/**
	 * @Title: getApplyForHousekeeper 
	 * @Description: 获取符合查询条件待审核的家政人员信息
	 * @param sql 预编译的sql语句
	 * @param param sql语句的参数
	 * @return List<ApplyForHousekeeper> 符合查询条件待审核家政人员的List
	 */
	public abstract List<ApplyForHousekeeper> getApplyForHousekeeper(String sql,String[] param);
	/**
	 * @Title: updateApplyForHousekeeper 
	 * @Description: 更新待审核见证人员表
	 * @param @param sql 预编译的sql语句
	 * @param @param param sql语句的参数
	 * @param @return 
	 * @return int 判断更新语句是否执行成功，返回值>0时成功
	 */
	public abstract int updateApplyForHousekeeper(String sql,String[] param);
	/**
	 * @Title: getCount 
	 * @Description: 获取表中数据个数
	 * @param sql 预编译的sql语句
	 * @param param sql语句的参数
	 * @return int 数据的个数
	 */
	public int getCount(String sql,Object[] param);
}
