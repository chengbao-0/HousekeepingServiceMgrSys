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

import dao.HousekeeperDao;
import dao.impl.HousekeeperDaoImpl;
import entity.Housekeeper;
import service.factory.HousekeeperFactory;
import utils.enumeration.HireState;
import utils.enumeration.Sex;

/**
 * @ClassName HouseKeeperFactoryImpl
 * @Desc 家政人员工厂实现类
 * @author chengbao_0
 * @Date 2020年7月17日 下午7:55:50
 */
public class HouseKeeperFactoryImpl implements HousekeeperFactory {
	/**
	 * 创建新的家政人员对象
	 */
	@Override
	public Housekeeper NewHousekeeper(String[] param) {
		//新建家政人员对象作为返回值用以登录
		Housekeeper housekeeper=new Housekeeper();
		housekeeper.setUser(param[0]);
		housekeeper.setPwd(param[1]);
		housekeeper.setHousekeeperID(getNextID());
		housekeeper.setName(param[2]);
		housekeeper.setSex(Sex.valueOf(param[3]));
		housekeeper.setService(param[4]);
		housekeeper.setPhone(param[5]);
		housekeeper.setAvgScore(0);
		housekeeper.setStartTime(Time.valueOf(param[6]));
		housekeeper.setEndTime(Time.valueOf(param[7]));
		housekeeper.setState(HireState.未雇佣);
		housekeeper.setClientID(0);
		//插入到数据库中
		String sql = "insert into housekeeper(user,pwd,housekeeperID,name,sex,service,phone,avgScore,startTime,endTime,state,clientID) values(?,MD5(?),?,?,?,?,?,?,?,?,?,?)";
		Object[] next_param = {param[0],param[1],String.valueOf(getNextID()),param[2],param[3],param[4],param[5],"0",param[6],param[7],HireState.未雇佣.toString(),null};
		HousekeeperDao housekeeperDao=new HousekeeperDaoImpl();
		int count = housekeeperDao.updateHousekeeper(sql, next_param);
		if (count > 0) {//创建成功
			return housekeeper;
		}
		return null;//创建失败
	}
	/**
	 * 获取新的ID
	 */
	@Override
	public int getNextID() {
		int nextID= new HousekeeperDaoImpl().getCount("select Max(housekeeperID) from Housekeeper;",null)+1;
		if(nextID<Integer.MAX_VALUE) {
			return nextID;
		}else {
			return -1;
		}
	}
}
