/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：业务层，不同角色菜单业务接口的具体实现
 * @Package: service.menu.impl
 * @author: chengbao_0  
 * @date: 2020-7-21 12:14:50 
 */
package service.menu.impl;

import java.util.List;

import entity.Housekeeper;
import entity.ServiceRecord;
import service.menu.HousekeeperMenuService;

/**
 * @ClassName HousekeeperMenuAdapter
 * @Desc 家政人员菜单业务接口适配器
 * 			采用接口适配器模式，自身为抽象类，实现了家政人员菜单业务接口
 * 			将接口中不属于家政人员菜单业务的方法进行过滤，实现权限的隔离
 * 				具体操作为:  对于接口中不属于家政人员菜单业务的方法:
 * 								default方法，进行重写并返回空值
 * 						     	其他方法，方法体内不写语句，返回空值
 * 						     属于其业务的方法:
 * 								声明为abstract，强制子类进行实现
 * @author chengbao_0
 * @Date 2020-7-22 10:44:20
 */
public abstract class HousekeeperMenuAdapter implements HousekeeperMenuService {

	@Override
	public abstract int updateHousekeeper(String[] param) ;

	@Override
	public abstract List<ServiceRecord> getUnpaidServiceRecord(int id) ;
	@Override
	public abstract void completeWork(long formID);

	@Override
	public abstract List<ServiceRecord> getFormOngoing(int housekeeperID) ;

	@Override
	public abstract List<ServiceRecord> getServiceRecord_by_ID(int housekeeperID) ;
	/**
	 * 不属于家政人员菜单业务
	 */
	@Override
	public int updateClient(String[] param){//部分有，并且实现不同
		return 0;
	}
	@Override
	public abstract int setWorkState(int housekeeperID,String state);
	/**
	 * 不属于家政人员菜单业务
	 */
	//修改default方法, 相当于权限管理
	@Override
	public List<Housekeeper> getAllHousekeeper(){
		return null;
	}
	@Override
	public List<Housekeeper> getHousekeeper_by_service(String service){
		return null;
	}
	@Override
	public List<Housekeeper> getHousekeeper_by_time(String service, String startTime, String endTime){
		return null;
	}
}
