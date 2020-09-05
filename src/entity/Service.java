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
 * @ClassName Service
 * @Desc 服务类别类
 * @author chengbao_0
 * @Date 2020-7-20 11:21:06
 */
public class Service {
	private int serviceID;//服务类别编号
	private String service;//服务名称
	private double hourlyWage;//时薪
	//getter & setter
	public int getServiceID() {
		return serviceID;
	}
	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public double getHourlyWage() {
		return hourlyWage;
	}
	public void setHourlyWage(double hourlyWage) {
		this.hourlyWage = hourlyWage;
	}
}
