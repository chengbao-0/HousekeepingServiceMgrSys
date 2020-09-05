/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：实体类
 * @Package: entity 
 * @author: chengbao_0  
 * @date: 2020-7-29 20:19:49 
 */
package entity;

import utils.enumeration.PaidState;
import utils.enumeration.Sex;

/**
 * @ClassName Client
 * @Desc 会员类
 * @author chengbao_0
 * @Date 2020年7月16日 上午11:56:25
 */
public class Client extends Role{
	private String user;//用户名
	private String pwd;//密码
	private int clientID;//会员编号
	private String name;//姓名
	private Sex sex;//性别
	private String phone;//联系电话
	private String address;//家庭住址
	private PaidState paidState;//支付状态
	//getter & setter
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public PaidState getPaidState() {
		return paidState;
	}
	public void setPaidState(PaidState paidState) {
		this.paidState = paidState;
	}
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
	public int getClientID() {
		return clientID;
	}
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
}
