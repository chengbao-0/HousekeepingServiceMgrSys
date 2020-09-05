/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：业务层，一些普通业务接口，通常只由一个角色菜单进行实现
 * @Package: service.common 
 * @author: chengbao_0  
 * @date: 2020-7-21 12:14:50 
 */
package service.general;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import dao.AdminDao;
import dao.ApplyForHousekeeperDao;
import dao.ClientDao;
import dao.HousekeeperDao;
import dao.ServiceRecordDao;
import dao.impl.AdminDaoImpl;
import dao.impl.ApplyForHousekeeperDaoImpl;
import dao.impl.ClientDaoImpl;
import dao.impl.HousekeeperDaoImpl;
import dao.impl.ServiceRecordDaoImpl;
import entity.Housekeeper;
import utils.enumeration.FormState;
import utils.enumeration.HireState;
import utils.enumeration.PaidState;

/**
 * @ClassName Statistics
 * @Desc 数据统计接口
 * @author chengbao_0
 * @Date 2020-7-30 17:54:50
 */
public interface Statistics{
	/**
	 * 截至此刻共有表单多少
	 */
	default int totalServiceRecord() {
		ServiceRecordDao serviceRecordDao=new ServiceRecordDaoImpl();
		String sql = "select COUNT(*) from ServiceRecord;";
		return (int) serviceRecordDao.getCount(sql,null);//一定是整型
	}
	/**
	 * 已结算表单数目
	 */
	default int totalSettledRecord() {
		ServiceRecordDao serviceRecordDao=new ServiceRecordDaoImpl();
		String sql = "select COUNT(*) from ServiceRecord where formState = ?;";
		String[] param= {FormState.已结算.toString()};
		return (int) serviceRecordDao.getCount(sql, param);//一定是整型
	}
	/**
	 * 各类服务对应的表单数目
	 */
	default int diffServiceTotalRecord(String service) {
		ServiceRecordDao serviceRecordDao=new ServiceRecordDaoImpl();
		String sql = "select COUNT(*) from ServiceRecord where service = ?;";
		String[] param= {service};
		return (int) serviceRecordDao.getCount(sql, param);//一定是整型
	}
	/**
	 * 会员总数
	 */
	default int totalClient() {
		ClientDao clientDao=new ClientDaoImpl();
		String sql = "select COUNT(*) from client;";
		return clientDao.getCount(sql, null);
	}
	/**
	 * 未支付会员数目
	 */
	default int totalUnpaidClient() {
		ClientDao clientDao=new ClientDaoImpl();
		String sql = "select COUNT(*) from client where paidState= ?;";
		String param[]= {PaidState.待支付.toString()};
		return clientDao.getCount(sql, param);
	}
	/**
	 * 家政人员总数
	 */
	default int totalHousekeeper() {
		HousekeeperDao housekeeperDao=new HousekeeperDaoImpl();
		String sql="select COUNT(*) from Housekeeper;";
		return housekeeperDao.getCount(sql, null);
	}
	/**
	 * 正在被雇佣的家政人员总数
	 */
	default int totalHiredHousekeeper() {
		HousekeeperDao housekeeperDao=new HousekeeperDaoImpl();
		String sql="select COUNT(*) from Housekeeper where state= ?;";
		String param[]= {HireState.雇佣中.toString()};
		return housekeeperDao.getCount(sql, param);
	}
	/**
	 * @Title: diffServiceTotalHousekeeper 
	 * @Description:  * 该类服务对应的家政人员总数； 其中，多少人正在被雇佣，评分前三的家政人员id 姓名 分别为
	 * @param @param service 所要统计的服务
	 * @param @return
	 * @return Map<String,String> 统计结果
	 * 
	 * *Map
	 * 	服务名称:  
	 * 	人数:
	 * 	雇佣中: 
	 * (top1)id    姓名
	 * (top2)id    姓名
	 * (top3)id    姓名
	 * 
	 * @throws 
	 */
	default Map<String,String> diffServiceTotalHousekeeper(String service) {
		HousekeeperDao housekeeperDao=new HousekeeperDaoImpl();
		Map <String,String> map=new LinkedHashMap<>();
		map.put("服务名称", service);
		//统计该类service的家政人员总数
		String sql="SELECT COUNT(*) FROM Housekeeper WHERE service = ?;";
		String param[]= {service};
		int totalNum=housekeeperDao.getCount(sql, param);
		map.put("人数", String.valueOf(totalNum));
		//统计该类service正在被雇佣的家政人员总数
		String sql3="SELECT COUNT(*) FROM Housekeeper WHERE service = ? AND state= ?;";
		String param3[]= {service,HireState.雇佣中.toString()};
		int hiredNum=housekeeperDao.getCount(sql3, param3);
		map.put("雇佣中", String.valueOf(hiredNum));
		//统计top信息
		String sql2 = "select * from housekeeper where service = ? ORDER BY avgScore DESC,state DESC,housekeeperID ASC";
		String[] param2= {service};
		List<Housekeeper> housekeeperList=housekeeperDao.getHousekeeper(sql2, param2);
		for(Housekeeper housekeeper:housekeeperList) {
			map.put(String.valueOf(housekeeper.getHousekeeperID()), housekeeper.getName());
		}
		return map;
	}
	/**
	 * 待审核家政人员总数
	 */
	default int totalPendingHousekeeper() {
		ApplyForHousekeeperDao applyForHousekeeperDao=new ApplyForHousekeeperDaoImpl();
		String sql="select COUNT(*) from ApplyForHousekeeper;";
		return applyForHousekeeperDao.getCount(sql, null);
	}
	/**
	 * 管理员总数
	 */
	default int totalAdmin() {
		AdminDao adminDao=new AdminDaoImpl();
		String sql="select COUNT(*) from Admin;";
		return adminDao.getCount(sql, null);
	}
}
