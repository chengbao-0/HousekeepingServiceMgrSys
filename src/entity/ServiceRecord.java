/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：实体类
 * @Package: entity 
 * @author: chengbao_0  
 * @date: 2020-7-29 20:19:49 
 */
package entity;

import java.sql.Date;
import java.sql.Time;

import utils.enumeration.FormState;

/**
 * @ClassName ServiceRecord
 * @Desc 服务记录表单类
 * @author chengbao_0
 * @Date 2020年7月17日 下午2:54:43
 */
public class ServiceRecord {
	private long formID;//表单编号
	private String service;//服务类别
	private int clientID;//会员编号
	private int housekeeperID;//家政人员编号
	private Date employDate;//雇佣时期
	private Time startEmployTime;//工作开始时间
	private Time endEmployTime;//工作结束时间
	private double totalCompensation;//总计薪酬
	private int employDays;//雇佣天数
	private FormState formState;//表单状态
	private double clientScore;//会员评分
	private String clientEvaluate;//会员评价
	//getter & setter
	public long getFormID() {
		return formID;
	}
	public void setFormID(long formID) {
		this.formID = formID;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
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
	public Date getEmployDate() {
		return employDate;
	}
	public void setEmployDate(Date employDate) {
		this.employDate = employDate;
	}
	public Time getStartEmployTime() {
		return startEmployTime;
	}
	public void setStartEmployTime(Time startEmployTime) {
		this.startEmployTime = startEmployTime;
	}
	public Time getEndEmployTime() {
		return endEmployTime;
	}
	public void setEndEmployTime(Time endEmployTime) {
		this.endEmployTime = endEmployTime;
	}
	public double getTotalCompensation() {
		return totalCompensation;
	}
	public void setTotalCompensation(double totalCompensation) {
		this.totalCompensation = totalCompensation;
	}
	public int getEmployDays() {
		return employDays;
	}
	public void setEmployDays(int employDays) {
		this.employDays = employDays;
	}
	public FormState getFormState() {
		return formState;
	}
	public void setFormState(FormState formState) {
		this.formState = formState;
	}
	public double getClientScore() {
		return clientScore;
	}
	public void setClientScore(double clientScore) {
		this.clientScore = clientScore;
	}
	public String getClientEvaluate() {
		return clientEvaluate;
	}
	public void setClientEvaluate(String clientEvaluate) {
		this.clientEvaluate = clientEvaluate;
	}
}
