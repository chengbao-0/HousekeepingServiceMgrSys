/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：数据访问接口
 * @Package: dao
 * @author: chengbao_0  
 * @date: 2020-7-29 20:19:49 
 */
package dao;
import java.sql.*;
import java.io.*;
import java.util.*;

/**
 * @ClassName BaseDao
 * @Desc 基本数据访问类
 * @author chengbao_0
 * @Date 2020年7月16日 上午10:32:00
 */
public class BaseDao {
	public static String DRIVER;//数据库驱动
	public static String URL;//链接地址
	public static String DBNAME;//用户名
	public static String DBPASS;//密码
	/*
	 * 静态代码块
	 * 被加载时就会被执行
	 * 并且只会执行一次
	 */
	static {
		init();
	}
	/*
	 * 从配置文件中获取数据库连接的相关参数
	 */
	public static void init() {
		Properties params=new Properties();
		String config="database.properties";
		InputStream is=BaseDao.class.getClassLoader().getResourceAsStream(config);
		try {
			params.load(is);
		}catch(IOException e){
			e.printStackTrace();
		}
		DRIVER=params.getProperty("driver");
		URL=params.getProperty("url");
		DBNAME=params.getProperty("user");
		DBPASS=params.getProperty("password");
	}
	/*
	 * 连接数据库，并返回连接成功时的Connection
	 */
	public Connection getConn()throws ClassNotFoundException,SQLException{
		Connection conn=null;
		try {
			Class.forName(DRIVER);
			conn=DriverManager.getConnection(URL,DBNAME,DBPASS);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	/*
	 * 关闭数据库连接
	 */
	public void closeAll(Connection conn,PreparedStatement pstmt,ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		if(pstmt!=null) {
			try {
				pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/*
	 * 执行sql语句，参数分为预编译的sql语句和其参数
	 */
	public int executeSQL(String preparedSql,Object[] param) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int num=0;
		try {
			conn=getConn();
			pstmt=conn.prepareStatement(preparedSql);
			if(param!=null) {
				for(int i=0;i<param.length;i++) {
					pstmt.setObject(i+1, param[i]);
				}
			}
			num=pstmt.executeUpdate();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			this.closeAll(conn, pstmt, null);
		}
		return num;
	}
}