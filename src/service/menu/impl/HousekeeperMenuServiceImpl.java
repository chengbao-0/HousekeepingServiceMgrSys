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

import dao.ServiceRecordDao;
import dao.impl.ClientDaoImpl;
import dao.impl.HousekeeperDaoImpl;
import dao.impl.ServiceRecordDaoImpl;
import entity.ServiceRecord;
import utils.enumeration.FormState;
import utils.enumeration.HireState;
import utils.enumeration.PaidState;

/**
 * @ClassName HousekeeperMenuServiceImpl
 * @Desc 家政人员菜单业务接口实现类
 * @author chengbao_0
 * @Date 2020-7-20 13:01:02
 */
public class HousekeeperMenuServiceImpl extends HousekeeperMenuAdapter {

	@Override
	public void completeWork(long formID) {
		//获得该表单
		ServiceRecordDao serviceRecordDao=new ServiceRecordDaoImpl();
		String sql="SELECT * FROM ServiceRecord WHERE (formID = ?);";
		String[] param = {String.valueOf(formID)};
		List<ServiceRecord> serviceRecordList=serviceRecordDao.getServiceRecord(sql, param);
		ServiceRecord serviceRecord=null;
		if(serviceRecordList!=null&&serviceRecordList.size()>0) {
			serviceRecord =serviceRecordList.get(0);
		}else {
			return;
		}
		if(serviceRecord!=null) {
			//更改表单信息
			String sql2="UPDATE ServiceRecord SET formState = ? WHERE formID = ?;";
			String param2[]= {FormState.未付费.toString(),String.valueOf(formID)};
			if(serviceRecordDao.updateServiceRecord(sql2, param2)>0) {
				//更改家政人员信息
				String sql3="UPDATE Housekeeper SET state = ? , clientID = null WHERE housekeeperID = ?;";
				String param3[]= {HireState.未雇佣.toString(),String.valueOf(serviceRecord.getHousekeeperID())};
				new HousekeeperDaoImpl().updateHousekeeper(sql3, param3);
				//更改对应会员信息
				String sql4="UPDATE Client SET paidState = ? WHERE clientID = ?;";
				String param4[]= {PaidState.待支付.toString(),String.valueOf(serviceRecord.getClientID())};
				new ClientDaoImpl().updateClient(sql4, param4);
			}
		}
		return;
	}
	
	@Override
	public List<ServiceRecord> getFormOngoing(int housekeeperID) {
		ServiceRecordDao serviceRecordDao=new ServiceRecordDaoImpl();
		String sql="SELECT * FROM ServiceRecord WHERE ( formState= ?) AND (housekeeperID = ?);";
		String[] param = {FormState.进行中.toString(),String.valueOf(housekeeperID)};
		return serviceRecordDao.getServiceRecord(sql, param);
	}

	@Override
	public int updateHousekeeper(String[] param) {
		String sql="UPDATE Housekeeper SET pwd = MD5(?),name = ?,sex = ?, service = ?,phone= ?, startTime = ?, endTime = ? WHERE housekeeperID = ?;";
		return new HousekeeperDaoImpl().updateHousekeeper(sql, param);
	}
	
	@Override
	public List<ServiceRecord> getUnpaidServiceRecord(int housekeeperID) {
		ServiceRecordDao serviceRecordDao=new ServiceRecordDaoImpl();
		String sql="SELECT * FROM ServiceRecord WHERE ( formState= ?) AND (housekeeperID = ?);";
		String[] param = {FormState.未付费.toString(),String.valueOf(housekeeperID)};
		return serviceRecordDao.getServiceRecord(sql, param);
	}
	@Override
	public List<ServiceRecord> getServiceRecord_by_ID(int housekeeperID) {
		return getServiceRecord_by_ID(0, 0,housekeeperID);
	}

	@Override
	public int setWorkState(int housekeeperID,String state) {
		String sql="UPDATE Housekeeper SET  state = ? WHERE housekeeperID = ?;";
		String[] param= {state,String.valueOf(housekeeperID)};
		return new HousekeeperDaoImpl().updateHousekeeper(sql, param);
	}
}
