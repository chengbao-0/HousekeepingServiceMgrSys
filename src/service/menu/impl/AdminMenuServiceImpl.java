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
import dao.impl.ServiceDaoImpl;
import dao.impl.ServiceRecordDaoImpl;
import entity.ApplyForHousekeeper;
import entity.Client;
import entity.Service;
import entity.ServiceRecord;
import service.factory.impl.HouseKeeperFactoryImpl;
import service.menu.AdminMenuService;
import utils.enumeration.RegisterState;

/**
 * @ClassName AdminServiceImpl
 * @Desc 管理员菜单业务接口实现类
 * @author chengbao_0
 * @Date 2020年7月16日 下午4:21:13
 */
public class AdminMenuServiceImpl implements AdminMenuService{
	@Override
	public int updateClient(String[] param) {
		String sql = "UPDATE CLIENT SET pwd = MD5(?), NAME=?, sex=?, phone= ?,address= ?, paidState=? WHERE clientID = ?;";
		return new ClientDaoImpl().updateClient(sql, param);
	}

	@Override
	public int updateHousekeeper(String[] param) {
		String sql="UPDATE Housekeeper SET pwd = MD5(?),name = ?,sex = ?, service = ?,phone= ?, startTime = ?, endTime = ?, state = ? WHERE housekeeperID = ?;";
		return new HousekeeperDaoImpl().updateHousekeeper(sql, param);
	}

	@Override
	public List<ServiceRecord> getUnpaidServiceRecord(int id) {
		ServiceRecordDao serviceRecordDao=new ServiceRecordDaoImpl();
		String sql="SELECT * FROM ServiceRecord WHERE ( formState= ?);";
		String[] param = {"未付费"};
		return serviceRecordDao.getServiceRecord(sql, param);
	}
	@Override
	public List<ServiceRecord> getUpaidServiceRecord() {
		return getUnpaidServiceRecord(0);
	}

	@Override
	public int deleteClient(int id){//考虑删除失败的情况
		String sql = "delete from client where clientID = ?";
		String[] param= {String.valueOf(id)};
		return new ClientDaoImpl().updateClient(sql, param);
		
	}

	@Override
	public int deleteHousekeeper(int id) {
		String sql = "delete from housekeeper where housekeeperID = ?";
		String[] param= {String.valueOf(id)};
		return new HousekeeperDaoImpl().updateHousekeeper(sql, param);
	}

	@Override
	public int deleteServiceRecord(long id) {
		String sql = "delete from ServiceRecord where formID = ?;";
		String[] param= {String.valueOf(id)};
		return new ServiceRecordDaoImpl().updateServiceRecord(sql, param);
	}

	@Override
	public List<Client> getAllClient() {
		return new ClientDaoImpl().getAllClient();
	}

	@Override
	public List<ServiceRecord> getAllServiceRecord() {
		return new ServiceRecordDaoImpl().getAllServiceRecord();
	}

	@Override
	public Service getService(int id) {
		String sql="select * from service WHERE serviceID = ?;";
		String[] param= {String.valueOf(id)};
		return new ServiceDaoImpl().getService(sql, param);
	}

	@Override
	public List<ServiceRecord> getServiceRecord_by_Service(String service) {
		String sql = "select * from ServiceRecord where service = ?;";
		String[] param= {service};
		ServiceRecordDao serviceRecordDao=new ServiceRecordDaoImpl();
		List<ServiceRecord> serviceRecordList=serviceRecordDao.getServiceRecord(sql, param);
		//判断查询结果是否有效
		if(serviceRecordList.size()>0) {
			return serviceRecordList;
		}
		return null;
	}

	@Override
	public int updateService(String[] param) {
		String sql="UPDATE Service SET hourlyWage = ? WHERE serviceID = ?;";
		return new ServiceDaoImpl().updateService(sql, param);
	}

	@Override
	public int updateServiceRecord(String[] param){
		String sql = "UPDATE ServiceRecord SET service = ?, clientID = ?,housekeeperID = ?,employDate = ?,startEmployTime = ?,"
				+ "endEmployTime = ?,employDays = ?,formState = ? WHERE formID = ?;";
		return new ServiceRecordDaoImpl().updateServiceRecord(sql, param);
	}

	@Override
	public void passAudits(int id) {
		//相当于家政人员表新增家政人员
		ApplyForHousekeeper applyForHousekeeper=getApplyForHousekeeper(id);
		String param[]=new String[8];
		param[0]=applyForHousekeeper.getUser();
		param[1]=applyForHousekeeper.getPwd();
		param[2]=applyForHousekeeper.getName();
		param[3]=applyForHousekeeper.getSex().toString();
		param[4]=applyForHousekeeper.getService();
		param[5]=applyForHousekeeper.getPhone();
		param[6]=applyForHousekeeper.getStartTime().toString();
		param[7]=applyForHousekeeper.getEndTime().toString();
		new HouseKeeperFactoryImpl().NewHousekeeper(param);
		//删除注册申请表中的对应信息
		deleteApplyForHousekeeper(id);
		return;
	}

	@Override
	public void failAudits(int id) {
		//更改待审核家政人员的状态为--未通过
		String sql = "UPDATE ApplyForHousekeeper SET registerState = ? WHERE ID = ?;";
		String[] param= {RegisterState.未通过.toString(),String.valueOf(id)};
		new ServiceRecordDaoImpl().updateServiceRecord(sql, param);
		return;
	}

	@Override
	public int deleteServiceRecordEvaluation(long id) {
		//将评论置为空
		String sql = "UPDATE ServiceRecord SET clientEvaluate = ? WHERE formID = ?;";
		String[] param= {"",String.valueOf(id)};
		return new ServiceRecordDaoImpl().updateServiceRecord(sql, param);
	}

}
