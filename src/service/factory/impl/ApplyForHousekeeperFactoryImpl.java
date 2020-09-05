/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：业务层，各个表单新建数据项的具体实现，采用了工厂模式进行创建
 * @Package: service.factory.impl 
 * @author: chengbao_0  
 * @date: 2020-7-29 20:40:04 
 */
package service.factory.impl;

import java.sql.Time;

import dao.ApplyForHousekeeperDao;
import dao.impl.ApplyForHousekeeperDaoImpl;
import entity.ApplyForHousekeeper;
import service.factory.ApplyForHousekeeperFactory;
import utils.enumeration.RegisterState;
import utils.enumeration.Sex;

/**
 * @ClassName ApplyForHousekeeperFactoryImpl
 * @Desc 待申请家政人员工厂实现类
 * @author chengbao_0
 * @Date 2020-7-29 20:40:04
 */
public class ApplyForHousekeeperFactoryImpl implements ApplyForHousekeeperFactory {
	/**
	 * 新建待审核家政人员对象
	 */
	@Override
	public ApplyForHousekeeper NewApply(String[] param) {
		//新建待审核家政人员对象，作为返回值，用以登录使用
		ApplyForHousekeeper applyForHousekeeper=new ApplyForHousekeeper();
		applyForHousekeeper.setUser(param[0]);
		applyForHousekeeper.setPwd(param[1]);
		applyForHousekeeper.setID(getNextID());
		applyForHousekeeper.setName(param[2]);
		applyForHousekeeper.setSex(Sex.valueOf(param[3]));
		applyForHousekeeper.setService(param[4]);
		applyForHousekeeper.setPhone(param[5]);
		applyForHousekeeper.setStartTime(Time.valueOf(param[6]));
		applyForHousekeeper.setEndTime(Time.valueOf(param[7]));
		applyForHousekeeper.setRegisterState(RegisterState.审核中);
		//插入到数据库中
		String sql = "insert into ApplyForHousekeeper(user,pwd,ID,name,sex,service,phone,startTime,endTime,registerState) values(?,MD5(?),?,?,?,?,?,?,?,?)";
		String[] next_param = {param[0],param[1],String.valueOf(getNextID()),param[2],param[3],param[4],param[5],param[6],param[7],RegisterState.审核中.toString()};
		ApplyForHousekeeperDao applyForHousekeeperDao=new ApplyForHousekeeperDaoImpl();
		int count = applyForHousekeeperDao.updateApplyForHousekeeper(sql, next_param);
		if (count > 0) {//创建成功
			return applyForHousekeeper;
		}
		return null;//创建失败
	}
	/**
	 * 获取新的ID
	 */
	@Override
	public int getNextID() {
		int nextID= new ApplyForHousekeeperDaoImpl().getCount("select Max(ID) from ApplyForHousekeeper;",null)+1;
		if(nextID<Integer.MAX_VALUE) {
			return nextID;
		}else {
			return -1;
		}
	}
}
