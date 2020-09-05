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

import dao.AdminDao;
import dao.BaseDao;
import entity.Admin;



/**
 * @ClassName AdminDaoImpl
 * @Desc 负责Admin表的数据访问，实现了AdminDao接口
 * @author chengbao_0
 * @Date 2020年7月16日 下午1:19:41
 */
public class AdminDaoImpl extends BaseDao implements AdminDao {
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	@Override
	public List<Admin> getAllAdmin() {
		List<Admin> adminList=new ArrayList<Admin>();
		try {
			String prepareSql="select * from Admin";
			//连接数据库
			conn=getConn();
			pstmt=conn.prepareStatement(prepareSql);
			//执行查询并处理返回结果
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Admin admin=new Admin();
				admin.setUser(rs.getString("user"));
				admin.setPwd(rs.getString("pwd"));
				admin.setAdminID(rs.getInt("adminID"));
				adminList.add(admin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			this.closeAll(conn, pstmt, rs);
		}
		return adminList;
	}

	@Override
	public Admin getAdmin(String sql, String[] param) {
		Admin admin=null;
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
				admin=new Admin();
				admin.setUser(rs.getString("user"));
				admin.setPwd(rs.getString("pwd"));
				admin.setAdminID(rs.getInt("adminID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			super.closeAll(conn, pstmt, rs);
		}
		return admin;
	}

	@Override
	public int updateAdmin(String sql, Object[] param) {
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
