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

import dao.HousekeeperDao;
import dao.ServiceRecordDao;
import dao.impl.ClientDaoImpl;
import dao.impl.HousekeeperDaoImpl;
import dao.impl.ServiceRecordDaoImpl;
import entity.Housekeeper;
import entity.ServiceRecord;
import service.factory.impl.ServiceRecordFactoryImpl;
import utils.enumeration.FormState;
import utils.enumeration.PaidState;

/**
 * @ClassName ClientServiceImpl
 * @Desc 会员菜单业务接口实现类
 * @author chengbao_0
 * @Date 2020年7月16日 下午4:22:03
 */
public class ClientMenuServiceImpl extends ClientMenuAdapter {
	@Override
	public List<ServiceRecord> getUnEvaluableServiceRecord(int clientID) {
		ServiceRecordDao serviceRecordDao=new ServiceRecordDaoImpl();
		String sql="SELECT * FROM ServiceRecord WHERE ( formState= ?) AND (clientID = ?);";
		String[] param = {FormState.已付费.toString(),String.valueOf(clientID)};
		return serviceRecordDao.getServiceRecord(sql, param);
	}
	@Override
	public List<ServiceRecord> getUnpaidServiceRecord(int clientID){
		ServiceRecordDao serviceRecordDao=new ServiceRecordDaoImpl();
		String sql="SELECT * FROM ServiceRecord WHERE ( formState= ?) AND (clientID = ?);";
		String[] param = {FormState.未付费.toString(),String.valueOf(clientID)};
		return serviceRecordDao.getServiceRecord(sql, param);
	}
	@Override
	public ServiceRecord ClientHireHousekeeper(String[] param) {
		//向数据库新增表单
		ServiceRecord serviceRecord=new ServiceRecordFactoryImpl().NewServiceRecord(param);
		//改变会员的状态信息
		String sql1 = "UPDATE CLIENT SET paidState= '待支付'  WHERE clientID = ?;";
		String[] param1= {param[1]};
		int result1=new ClientDaoImpl().updateClient(sql1, param1);
		//改变家政人员的状态信息
		String sql2= "UPDATE Housekeeper SET State = '雇佣中' , clientID = ? WHERE housekeeperID = ?;";
		String[] param2= {param[1],param[2]};
		int result2=new HousekeeperDaoImpl().updateHousekeeper(sql2, param2);
		//判断信息变更是否成功，如果失败则返回null
		if(result1>0&&result2>0) {
			return serviceRecord;
		}else {
			return null;
		}
	}
	@Override
	public boolean evaluate(long formID,double score,String evaluateStr) {
		ServiceRecordDao serviceRecordDao=new ServiceRecordDaoImpl();
		ServiceRecord serviceRecord=getServiceRecord_by_ID(formID, 0, 0).get(0);
		//确保待评价的表单的状态为--已付费
		if(serviceRecord.getFormState()!=FormState.已付费) {
			return false;
		}
		//更改表单评分
		String sql2="UPDATE ServiceRecord SET formState = ? , clientScore = ?, clientEvaluate= ?  WHERE formID = ?;";
		String[] param2= {FormState.已结算.toString(),String.valueOf(score),evaluateStr,String.valueOf(formID)};
		int result2=serviceRecordDao.updateServiceRecord(sql2, param2);
		//重新获取历史评分
		int housekeeperID=getServiceRecord_by_ID(formID, 0, 0).get(0).getHousekeeperID();
		String sql1="SELECT AVG(clientScore) FROM servicerecord WHERE housekeeperID = ?;";
		String[] param1= {String.valueOf(housekeeperID)};
		double avgScore=serviceRecordDao.getCount(sql1, param1);
		//更改对应家政人员的历史评分
		String sql3="UPDATE Housekeeper SET avgScore = ? WHERE housekeeperID = ?;";
		String[] param3= {String.valueOf(avgScore),String.valueOf(housekeeperID)};
		int result3=new HousekeeperDaoImpl().updateHousekeeper(sql3, param3);
		//判断信息变更是否成功，如果失败则返回null
		if(result2>0&&result3>0) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public boolean pay(long formID,int clientID) {
		//改变会员的支付状态
		String sql1 = "UPDATE CLIENT SET paidState= ? WHERE clientID = ?;";
		String[] param1= {PaidState.正常.toString(),String.valueOf(clientID)};
		int result1=new ClientDaoImpl().updateClient(sql1, param1);
		//改变表单状态
		String sql2="UPDATE ServiceRecord SET formState = ? WHERE formID = ?;";
		String[] param2= {FormState.已付费.toString(),String.valueOf(formID)};
		int result2=new ServiceRecordDaoImpl().updateServiceRecord(sql2, param2);
		//判断信息变更是否成功，如果失败则返回null
		if(result1>0&&result2>0) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * @Title: getServiceRecord_by_ID 
	 * @Description: 重载getServiceRecord_by_ID方法，实现对会员的隐藏
	 * @param @param id 待查询的表单ID
	 * @param @return
	 * @return List<ServiceRecord> 查询结果
	 * @throws 
	 */
	@Override
	public List<ServiceRecord> getServiceRecord_by_ID(int id) {
		return getServiceRecord_by_ID(0, id, 0);
	}
	@Override
	public int updateClient(String[] param) {
		String sql = "UPDATE CLIENT SET pwd = MD5(?), NAME=?, sex=? ,phone = ?, address= ? WHERE clientID = ?;";
		return new ClientDaoImpl().updateClient(sql, param);
	}
	@Override
	public List<Housekeeper> getHousekeeper_by_service(String service){
		String sql = "select * from housekeeper where service = ?";
		String[] param= {service};
		HousekeeperDao housekeeperDao=new HousekeeperDaoImpl();
		List<Housekeeper> housekeeperList=housekeeperDao.getHousekeeper(sql, param);
		//判断查询是否成功，如果失败则返回null
		if(housekeeperList.size()>0) {
			return housekeeperList;
		}
		return null;
	}
}
