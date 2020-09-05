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
import dao.ServiceDao;
import entity.Service;

/**
 * @ClassName ServiceDaoImpl
 * @Desc 负责Service表的数据访问，实现了ServiceDao接口
 * @author chengbao_0
 * @Date 2020-7-20 11:27:03
 */
public class ServiceDaoImpl extends BaseDao implements ServiceDao {
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	@Override
	public List<Service> getAllService() {
		List<Service> serviceList=new ArrayList<Service>();
		try {
			//连接数据库
			String prepareSql="select * from Service;";
			conn=getConn();
			pstmt=conn.prepareStatement(prepareSql);
			//执行查询并处理返回结果
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Service service=new Service();
				service.setServiceID(rs.getInt("serviceID"));
				service.setService(rs.getString("service"));
				service.setHourlyWage(rs.getDouble("hourlyWage"));
				serviceList.add(service);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			this.closeAll(conn, pstmt, rs);
		}
		return serviceList;
	}

	@Override
	public Service getService(String sql, String[] param) {
		Service service=null;
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
			while (rs.next()) {
				service=new Service();
				service.setServiceID(rs.getInt("serviceID"));
				service.setService(rs.getString("service"));
				service.setHourlyWage(rs.getDouble("hourlyWage"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			super.closeAll(conn, pstmt, rs);
		}
		return service;
	}

	@Override
	public int updateService(String sql, Object[] param) {
		int count=executeSQL(sql, param);
		return count;
	}

	@Override
	public int getCount() {
		int count=0;
		try {
			//连接数据库
			conn=getConn();
			pstmt=conn.prepareStatement("select count(*) from Service;");
			//执行查询并处理返回结果
			rs=pstmt.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1);
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
