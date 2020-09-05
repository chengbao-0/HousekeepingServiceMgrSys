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

import dao.ApplyForHousekeeperDao;
import dao.BaseDao;
import entity.ApplyForHousekeeper;
import utils.enumeration.RegisterState;
import utils.enumeration.Sex;

/**
 * @ClassName ApplyForHousekeeperDaoImpl
 * @Desc 实现了ApplyForHousekeeperDao接口，负责ApplyForHouseeper表的数据访问
 * @author chengbao_0
 * @Date 2020-7-29 20:24:41
 */
public class ApplyForHousekeeperDaoImpl extends BaseDao implements ApplyForHousekeeperDao {
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	@Override
	public List<ApplyForHousekeeper> getAllApplyForHousekeeper() {
		List<ApplyForHousekeeper> applyList=new ArrayList<ApplyForHousekeeper>();
		try {
			//连接数据库
			String prepareSql="select * from ApplyForHousekeeper";
			conn=getConn();
			pstmt=conn.prepareStatement(prepareSql);
			//执行查询并处理返回结果
			rs=pstmt.executeQuery();
			while(rs.next()) {
				ApplyForHousekeeper applyForHousekeeper=new ApplyForHousekeeper();
				applyForHousekeeper.setUser(rs.getString("user"));
				applyForHousekeeper.setPwd(rs.getString("pwd"));
				applyForHousekeeper.setID(rs.getInt("ID"));
				applyForHousekeeper.setName(rs.getString("name"));
				applyForHousekeeper.setSex(Sex.valueOf(rs.getString("sex")));
				applyForHousekeeper.setService(rs.getString("service"));
				applyForHousekeeper.setPhone(rs.getString("phone"));
				applyForHousekeeper.setStartTime(rs.getTime("startTime"));
				applyForHousekeeper.setEndTime(rs.getTime("endTime"));
				applyForHousekeeper.setRegisterState(RegisterState.valueOf(rs.getString("registerState")));
				applyList.add(applyForHousekeeper);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			this.closeAll(conn, pstmt, rs);
		}
		return applyList;
	}

	@Override
	public List<ApplyForHousekeeper> getApplyForHousekeeper(String sql, String[] param) {
		List<ApplyForHousekeeper> applyList=new ArrayList<ApplyForHousekeeper>();
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
			ApplyForHousekeeper applyForHousekeeper=null;
			while (rs.next()) {
				applyForHousekeeper=new ApplyForHousekeeper();
				applyForHousekeeper.setUser(rs.getString("user"));
				applyForHousekeeper.setPwd(rs.getString("pwd"));
				applyForHousekeeper.setID(rs.getInt("ID"));
				applyForHousekeeper.setName(rs.getString("name"));
				applyForHousekeeper.setSex(Sex.valueOf(rs.getString("sex")));
				applyForHousekeeper.setService(rs.getString("service"));
				applyForHousekeeper.setPhone(rs.getString("phone"));
				applyForHousekeeper.setStartTime(rs.getTime("startTime"));
				applyForHousekeeper.setEndTime(rs.getTime("endTime"));
				applyForHousekeeper.setRegisterState(RegisterState.valueOf(rs.getString("registerState")));
				applyList.add(applyForHousekeeper);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			super.closeAll(conn, pstmt, rs);
		}
		return applyList;
	}

	@Override
	public int updateApplyForHousekeeper(String sql, String[] param) {
		int count=executeSQL(sql, param);
		return count;
	}
	@Override
	public int getCount(String sql,Object[] param) {
		int count=0;
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
