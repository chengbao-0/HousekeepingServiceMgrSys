/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：实体类
 * @Package: entity 
 * @author: chengbao_0  
 * @date: 2020-7-29 20:19:49 
 */
package entity;

import java.sql.Time;

import utils.enumeration.RegisterState;
import utils.enumeration.Sex;

/**
 * @ClassName ApplyForHousekeeper
 * @Desc 待审核家政人员类
 * @author chengbao_0
 * @Date 2020-7-29 20:19:49
 */
public class ApplyForHousekeeper extends Role {
	private String user;//用户名
	private String pwd;//密码
	private int ID;//家政人员编号
	private String name;//姓名
	private Sex sex;//性别
	private String service;//服务类别
	private String phone;//联系电话
	private Time startTime;//工作开始时间
	private Time endTime;//工作结束时间
	private RegisterState registerState;//注册状态
	//getter & setter
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public RegisterState getRegisterState() {
		return registerState;
	}
	public void setRegisterState(RegisterState registerState) {
		this.registerState = registerState;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
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
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
}
