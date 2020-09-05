/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：数据访问接口的实现
 * @Package: dao.impl 
 * @author: chengbao_0  
 * @date: 2020-7-29 20:24:41 
 */
package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.BaseDao;
import dao.ServiceRecordDao;
import entity.ServiceRecord;
import utils.enumeration.FormState;

/**
 * @ClassName ServiceRecordDaoImpl
 * @Desc 服务记录表单类在Dao模式下的实现
 * @author chengbao_0
 * @Date 2020年7月17日 下午3:07:21
 */
public class ServiceRecordDaoImpl extends BaseDao implements ServiceRecordDao {
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	@Override
	public List<ServiceRecord> getAllServiceRecord() {
		List<ServiceRecord> serviceRecordList=new ArrayList<ServiceRecord>();
		try {
			//连接数据库
			String prepareSql="select * from ServiceRecord";
			conn=getConn();
			pstmt=conn.prepareStatement(prepareSql);
			//执行查询并处理返回结果
			rs=pstmt.executeQuery();
			while(rs.next()) {
				ServiceRecord serviceRecord=new ServiceRecord();
				serviceRecord.setFormID(rs.getLong("formID"));
				serviceRecord.setService(rs.getString("service"));
				serviceRecord.setClientID(rs.getInt("clientID"));
				serviceRecord.setHousekeeperID(rs.getInt("housekeeperID"));
				serviceRecord.setEmployDate(rs.getDate("employDate"));
				serviceRecord.setStartEmployTime(rs.getTime("startEmployTime"));
				serviceRecord.setEndEmployTime(rs.getTime("endEmployTime"));
				serviceRecord.setEmployDays(rs.getInt("employDays"));
				serviceRecord.setTotalCompensation(rs.getDouble("totalCompensation"));
				serviceRecord.setFormState(FormState.valueOf(rs.getString("formState")));
				serviceRecord.setClientScore(rs.getDouble("clientScore"));
				serviceRecord.setClientEvaluate(rs.getString("clientEvaluate"));
				serviceRecordList.add(serviceRecord);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			this.closeAll(conn, pstmt, rs);
		}
		return serviceRecordList;
	}

	@Override
	public List<ServiceRecord> getServiceRecord(String sql, Object[] param) {
		List<ServiceRecord> serviceRecordList=new ArrayList<ServiceRecord>();
		try {
			//连接数据库
			conn=getConn();
			//处理预编译sql语句
			pstmt=conn.prepareStatement(sql);
			if(param!=null) {
				for(int i=0;i<param.length;i++) {
					pstmt.setObject(i+1,param[i]);
				}
			}
			//执行查询并处理返回结果
			rs=pstmt.executeQuery();
			ServiceRecord serviceRecord=null;
			while (rs.next()) {
				serviceRecord=new ServiceRecord();
				serviceRecord.setFormID(rs.getLong("formID"));
				serviceRecord.setService(rs.getString("service"));
				serviceRecord.setClientID(rs.getInt("clientID"));
				serviceRecord.setHousekeeperID(rs.getInt("housekeeperID"));
				serviceRecord.setEmployDate(rs.getDate("employDate"));
				serviceRecord.setStartEmployTime(rs.getTime("startEmployTime"));
				serviceRecord.setEndEmployTime(rs.getTime("endEmployTime"));
				serviceRecord.setEmployDays(rs.getInt("employDays"));
				serviceRecord.setTotalCompensation(rs.getDouble("totalCompensation"));
				serviceRecord.setFormState(FormState.valueOf(rs.getString("formState")));
				serviceRecord.setClientScore(rs.getDouble("clientScore"));
				serviceRecord.setClientEvaluate(rs.getString("clientEvaluate"));
				serviceRecordList.add(serviceRecord);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			super.closeAll(conn, pstmt, rs);
		}
		return serviceRecordList;
	}

	@Override
	public int updateServiceRecord(String sql, Object[] param) {
		int count=executeSQL(sql, param);
		return count;
	}

	@Override
	public double getCount(String sql, Object[] param) {
		double count=0;
		try {
			//连接数据库
			conn=getConn();
			//处理预编译sql语句
			pstmt=conn.prepareStatement(sql);
			if(param!=null) {
				for(int i=0;i<param.length;i++) {
					pstmt.setObject(i+1,param[i]);
				}
			}
			//执行查询并处理返回结果
			rs=pstmt.executeQuery();
			if(rs.next()) {
				count=rs.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			super.closeAll(conn, pstmt, rs);
		}
		return count;
	}
}
