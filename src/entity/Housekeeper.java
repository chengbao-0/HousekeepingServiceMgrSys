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

import utils.enumeration.HireState;
import utils.enumeration.Sex;

/**
 * @ClassName Housekeeper
 * @Desc 家政人员类
 * @author chengbao_0
 * @Date 2020年7月16日 上午11:49:37
 */
public class Housekeeper extends Role{
	private String user;//用户名
	private String pwd;//密码吗
	private int housekeeperID;//家政人员编号
	private String name;//姓名
	private Sex sex;//性别
	private String service;//服务类别
	private String phone;//联系电话
	private Time startTime;//工作开始时间
	private Time endTime;//工作结束时间
	private HireState state;//雇佣状态
	private int clientID;//雇主编号
	private double avgScore;//历史评分（平均分）
	//getter & setter
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public double getAvgScore() {
		return avgScore;
	}
	public void setAvgScore(double avgScore) {
		this.avgScore = avgScore;
	}
	public int getClientID() {
		return clientID;
	}
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	public int getHousekeeperID() {
		return housekeeperID;
	}
	public void setHousekeeperID(int housekeeperID) {
		this.housekeeperID = housekeeperID;
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
	public HireState getState() {
		return state;
	}
	public void setState(HireState state) {
		this.state = state;
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
