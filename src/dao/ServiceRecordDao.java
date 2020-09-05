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

import entity.ServiceRecord;

/**
 * @ClassName ServiceRecordDao
 * @Desc ServiceRecord表的数据访问接口
 * @author chengbao_0
 * @Date 2020年7月17日 下午3:01:38
 */
public interface ServiceRecordDao {
	/**
	 * @Title: getAllServiceRecord 
	 * @Description: 查询所有服务记录
	 * @param @return
	 * @return List<ServiceRecord> 所有服务记录的List
	 * @throws 
	 */
	public abstract List<ServiceRecord> getAllServiceRecord();
	/**
	 * @Title: getServiceRecord 
	 * @Description: 根据查询条件查询服务记录
	 * @param @param sql 预编译的查询语句
	 * @param @param param 查询语句的参数
	 * @param @return
	 * @return List<ServiceRecord> 符合条件的服务记录表单List
	 * @throws 
	 */
	public abstract List<ServiceRecord> getServiceRecord(String sql,Object[] param);
	/**
	 * @Title: updateServiceRecord 
	 * @Description: 更新服务记录
	 * @param @param sql 预编译的更新语句
	 * @param @param param 数据库更新语句的参数
	 * @param @return
	 * @return int 判断更新语句是否执行成功，返回值>0时成功
	 * @throws 
	 */
	public abstract int updateServiceRecord(String sql,Object[] param);
	/**
	 * @Title: getCount 
	 * @Description: 获取表中数据个数
	 * @param @return
	 * @return double
	 * @throws 
	 */
	public abstract double getCount(String sql, Object[] param);
}
