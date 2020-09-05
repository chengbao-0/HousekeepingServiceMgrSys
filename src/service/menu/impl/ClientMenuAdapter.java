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

import entity.ServiceRecord;
import service.menu.ClientMenuService;

/**
 * @ClassName ClientMenuServiceAdapter
 * @Desc 会员菜单业务接口适配器
 * 			采用接口适配器模式，自身为抽象类，实现了会员菜单业务接口
 * 			将接口中不属于会员菜单业务的方法进行过滤，实现权限的隔离
 * 				具体操作为:  对于接口中不属于会员菜单业务的方法:
 * 								default方法，进行重写并返回空值
 * 						     	其他方法，方法体内不写语句，返回空值
 * 						     属于其业务的方法:
 * 								声明为abstract，强制子类进行实现
 * @author chengbao_0
 * @Date 2020-7-22 10:29:23
 */
public abstract class ClientMenuAdapter implements ClientMenuService {

	@Override
	public abstract ServiceRecord ClientHireHousekeeper(String[] param) ;

	@Override
	public abstract int updateClient(String[] param);

	@Override
	public abstract boolean evaluate(long formID, double score,String evaluateStr) ;

	@Override
	public abstract boolean pay(long formID, int clientID) ;
	/**
	 * 不属于会员菜单业务
	 */
	@Override
	public int updateHousekeeper(String[] param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public abstract List<ServiceRecord> getUnpaidServiceRecord(int id) ;

	@Override
	public abstract List<ServiceRecord> getServiceRecord_by_ID(int id) ;

	@Override
	public abstract List<ServiceRecord> getUnEvaluableServiceRecord(int clientID);
}
