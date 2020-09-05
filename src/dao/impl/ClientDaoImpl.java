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
import dao.ClientDao;
import entity.Client;
import utils.enumeration.PaidState;
import utils.enumeration.Sex;

/**
 * @ClassName ClientDaoImpl
 * @Desc 负责Client表的数据访问，实现了ClienDao接口
 * @author chengbao_0
 * @Date 2020年7月16日 下午1:20:16
 */
public class ClientDaoImpl extends BaseDao implements ClientDao {
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	@Override
	public List<Client> getAllClient() {
		List<Client> clientList=new ArrayList<Client>();
		try {
			//连接数据库
			String prepareSql="select * from Client";
			conn=getConn();
			pstmt=conn.prepareStatement(prepareSql);
			//执行查询并处理返回结果
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Client client=new Client();
				client.setUser(rs.getString("user"));
				client.setPwd(rs.getString("pwd"));
				client.setClientID(rs.getInt("clientID"));
				client.setName(rs.getString("name"));
				client.setSex(Sex.valueOf(rs.getString("sex")));
				client.setPhone(rs.getString("phone"));
				client.setAddress(rs.getString("address"));
				client.setPaidState(PaidState.valueOf(rs.getString("paidState")));
				clientList.add(client);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			this.closeAll(conn, pstmt, rs);
		}
		return clientList;
	}

	@Override
	public List<Client> getClient(String sql, String[] param) {
		List<Client> clientList=new ArrayList<Client>();
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
			Client client=null;
			while (rs.next()) {
				client=new Client();
				client.setUser(rs.getString("user"));
				client.setPwd(rs.getString("pwd"));
				client.setClientID(rs.getInt("clientID"));
				client.setName(rs.getString("name"));
				client.setSex(Sex.valueOf(rs.getString("sex")));
				client.setPhone(rs.getString("phone"));
				client.setAddress(rs.getString("address"));
				client.setPaidState(PaidState.valueOf(rs.getString("paidState")));
				clientList.add(client);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			super.closeAll(conn, pstmt, rs);
		}
		return clientList;
	}

	@Override
	public int updateClient(String sql, Object[] param) {
		int count=executeSQL(sql, param);
		return count;
	}

	@Override
	public int getCount(String sql,Object[] param) {
		int count=0;
		try {
			//连接数据库
			conn=getConn();
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
