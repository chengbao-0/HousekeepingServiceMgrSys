/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：业务层，各个表单新建数据项的具体实现，采用了工厂模式进行创建
 * @Package: service.factory.impl 
 * @author: chengbao_0  
 * @date: 2020-7-29 20:40:04 
 */
package service.factory.impl;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Random;

import dao.ServiceRecordDao;
import dao.impl.ServiceRecordDaoImpl;
import entity.ServiceRecord;
import service.factory.ServiceRecordFactory;
import utils.Fees;
import utils.enumeration.FormState;

/**
 * @ClassName ServiceRecordFactoryImpl
 * @Desc 服务记录工厂实现类
 * @author chengbao_0
 * @Date 2020年7月17日 下午6:40:22
 */
public class ServiceRecordFactoryImpl implements ServiceRecordFactory {
	/**
	 * 创建新的服务记录表单
	 */
	@Override
	public ServiceRecord NewServiceRecord(String[] param) {
		//新建服务记录对象作为返回值
		ServiceRecord serviceRecord=new ServiceRecord();
		serviceRecord.setFormID(getNextID());
		serviceRecord.setService(param[0]);
		serviceRecord.setClientID(Integer.parseInt(param[1]));
		serviceRecord.setHousekeeperID(Integer.parseInt(param[2]));
		serviceRecord.setEmployDate(Date.valueOf(param[3]));
		serviceRecord.setStartEmployTime(Time.valueOf(param[4]));
		serviceRecord.setEndEmployTime(Time.valueOf(param[5]));
		serviceRecord.setEmployDays(Integer.parseInt(param[6]));
		//根据传入的参数，调用计费方法计算总计薪酬
		double fees=Fees.calculateFees(Fees.getHourlyWage(param[0]),serviceRecord.getStartEmployTime(),
				serviceRecord.getEndEmployTime(),serviceRecord.getEmployDays());
		serviceRecord.setTotalCompensation(fees);
		serviceRecord.setFormState(FormState.进行中);
		serviceRecord.setClientScore(0);
		serviceRecord.setClientEvaluate("");
		//插入到数据库中
		String sql = "insert into ServiceRecord(formID,service,clientID,housekeeperID,employDate,startEmployTime,endEmployTime,employDays,"
				+ "totalCompensation,formState,clientScore,clientEvaluate) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] next_param = {String.valueOf(getNextID()),param[0],param[1],param[2],param[3],param[4],param[5],param[6],
				fees,FormState.进行中.toString(),"0",""};
		ServiceRecordDao serviceRecordDao=new ServiceRecordDaoImpl();
		int count = serviceRecordDao.updateServiceRecord(sql, next_param);
		if (count > 0) {//创建成功
			return serviceRecord;
		}
		return null;//创建失败
	}
	/**
	 * 获取新的ID
	 */
	public long getNextID() {
		//ID生成规则为:  目前的时间戳+两位随机数字
		return Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date())+new Random().nextInt(100));
	}

}
