/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：工具类
 * @Package: utils 
 * @author: chengbao_0  
 * @date: 2020-7-29 18:48:10 
 */
package utils;

import java.sql.Time;

import dao.ServiceDao;
import dao.impl.ServiceDaoImpl;
import entity.Service;

/**
 * @ClassName CalculateFees
 * @Desc 费用计算类
 * @author chengbao_0
 * @Date 2020-7-20 11:51:19
 */
public class Fees {
	//实现单例模式
	private static final Fees instance=new Fees();
	private Fees() {};
	public static Fees getInstance() {
		return instance;
	}
	/**
	 * @Title: calculateFees 
	 * @Description: 计算总计薪酬，总计薪酬 =  (工作结束时间 - 工作开始时间) * 时薪 * 雇佣天数
	 * @param @param hourlyWage 时薪
	 * @param @param startTime 工作开始时间
	 * @param @param endTime 工作结束时间
	 * @param @param employDays 雇佣天数
	 * @param @return
	 * @return double 总计薪酬
	 * @throws 
	 */
	public static double calculateFees(double hourlyWage,Time startTime,Time endTime,int employDays) {
		long start=startTime.getTime();
		long end=endTime.getTime();
		double hours = ((double) (end-start) / (1000 * 60 * 60));
		return hourlyWage*hours*employDays;
	}
	/*
	 * 获取时薪
	 * 重载以实现不同需求
	 * 采用静态方法
	 */
	/*
	 * 根据service获取时薪
	 */
	public static double getHourlyWage(String service) {
		ServiceDao serviceDao=new ServiceDaoImpl();
		String sql="SELECT * FROM service WHERE service = ?;";
		String[] param= {service};
		Service s=serviceDao.getService(sql, param);
		return s.getHourlyWage();
	}
	/*
	 * 根据服务id获取时薪
	 */
	public static double getHourlyWage(int id) {
		ServiceDao serviceDao=new ServiceDaoImpl();
		String sql="SELECT * FROM service WHERE serviceID = ?;";
		String[] param= {String.valueOf(id)};
		Service s=serviceDao.getService(sql, param);
		return s.getHourlyWage();
	}
}
