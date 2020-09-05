/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：实体类
 * @Package: entity 
 * @author: chengbao_0  
 * @date: 2020-7-29 20:19:49 
 */
package entity;

/**
 * @ClassName Admin
 * @Desc 管理员类
 * @author chengbao_0
 * @Date 2020年7月16日 上午11:58:01
 */
public class Admin extends Role{
	private String user;//用户名
	private String pwd;//密码
	private int adminID;//管理员编号
	//getter & setter
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getAdminID() {
		return adminID;
	}
	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}
}
