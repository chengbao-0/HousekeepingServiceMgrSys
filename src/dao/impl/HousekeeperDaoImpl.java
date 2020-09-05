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
import dao.HousekeeperDao;
import entity.Housekeeper;
import utils.enumeration.HireState;
import utils.enumeration.Sex;

/**
 * @ClassName HousekeeperDaoImpl
 * @Desc 负责Housekeeper的数据访问，实现了HousekeeperDao接口
 * @author chengbao_0
 * @Date 2020年7月16日 下午1:21:35
 */
public class HousekeeperDaoImpl extends BaseDao implements HousekeeperDao {
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	@Override
	public List<Housekeeper> getAllHousekeeper() {
		List<Housekeeper> housekeeperList=new ArrayList<Housekeeper>();
		try {
			//连接数据库
			String prepareSql="select * from Housekeeper";
			conn=getConn();
			pstmt=conn.prepareStatement(prepareSql);
			//执行查询并处理返回结果
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Housekeeper housekeeper=new Housekeeper();
				housekeeper.setUser(rs.getString("user"));
				housekeeper.setPwd(rs.getString("pwd"));
				housekeeper.setHousekeeperID(rs.getInt("housekeeperID"));
				housekeeper.setName(rs.getString("name"));
				housekeeper.setSex(Sex.valueOf(rs.getString("sex")));
				housekeeper.setService(rs.getString("service"));
				housekeeper.setPhone(rs.getString("phone"));
				housekeeper.setAvgScore(rs.getDouble("avgScore"));
				housekeeper.setStartTime(rs.getTime("startTime"));
				housekeeper.setEndTime(rs.getTime("endTime"));
				housekeeper.setState(HireState.valueOf(rs.getString("state")));
				housekeeper.setClientID(rs.getInt("clientID"));
				housekeeperList.add(housekeeper);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			this.closeAll(conn, pstmt, rs);
		}
		return housekeeperList;
	}

	@Override
	public List<Housekeeper> getHousekeeper(String sql, String[] param) {
		List<Housekeeper> housekeeperList=new ArrayList<Housekeeper>();
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
			Housekeeper housekeeper=null;
			while (rs.next()) {
				housekeeper=new Housekeeper();
				housekeeper.setUser(rs.getString("user"));
				housekeeper.setPwd(rs.getString("pwd"));
				housekeeper.setHousekeeperID(rs.getInt("housekeeperID"));
				housekeeper.setName(rs.getString("name"));
				housekeeper.setSex(Sex.valueOf(rs.getString("sex")));
				housekeeper.setService(rs.getString("service"));
				housekeeper.setPhone(rs.getString("phone"));
				housekeeper.setAvgScore(rs.getDouble("avgScore"));
				housekeeper.setStartTime(rs.getTime("startTime"));
				housekeeper.setEndTime(rs.getTime("endTime"));
				housekeeper.setState(HireState.valueOf(rs.getString("state")));
				housekeeper.setClientID(rs.getInt("clientID"));
				housekeeperList.add(housekeeper);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			super.closeAll(conn, pstmt, rs);
		}
		return housekeeperList;
	}
	@Override
	public int updateHousekeeper(String sql, Object[] param) {
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
