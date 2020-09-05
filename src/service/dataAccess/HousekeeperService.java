/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：业务层，数据访问业务接口，处理不同角色公共的数据访问业务
 * 		如果方法实现相同则在接口中声明的同时，实现为default方法
 * 		如果方法实现不同则只进行声明
 * 		如果只有单个角色需要某个方法，则由其接口声明，本数据访问业务接口不进行声明；除非没有其他角色实现该接口
 * @Package: service.dataAccess 
 * @author: chengbao_0  
 * @date: 2020-7-29 21:20:34 
 */
package service.dataAccess;

import java.util.List;

import dao.HousekeeperDao;
import dao.impl.HousekeeperDaoImpl;
import entity.Housekeeper;

/**
 * @ClassName HousekeeperService
 * @Desc 家政人员相关业务
 * @author chengbao_0
 * @Date 2020-7-20 9:18:03
 */
public interface HousekeeperService {
	/**
	 * @Title: updateHousekeeper 
	 * @Description: 更新家政人员信息
	 * @param @param param 待更新的信息
	 * @param @return
	 * @return int 更新是否成功     int>0  成功         int<0 失败
	 * @throws 
	 */
	public int updateHousekeeper(String[] param);//部分有，但实现不同
	/**
	 * @Title: getAllHousekeeper 
	 * @Description: 获取所有家政人员的信息
	 * @param @return
	 * @return List<Housekeeper> 所有家政人员List对象
	 * @throws 
	 */
	default List<Housekeeper> getAllHousekeeper() {//部分有，但实现相同
		return new HousekeeperDaoImpl().getAllHousekeeper();
	}
	/**
	 * @Title: getSpecialHousekeeper 
	 * @Description: 查询特定分工的家政人员
	 * @param @param service 待查询的服务类型
	 * @param @return
	 * @return List<Housekeeper> 查找到的家政人员List对象，失败时返回null
	 * @throws 
	 */
	default List<Housekeeper> getHousekeeper_by_service(String service){//部分有，但实现相同
		String sql = "select * from housekeeper where service = ? ORDER BY avgScore DESC,state DESC,housekeeperID ASC";
		String[] param= {service};
		HousekeeperDao housekeeperDao=new HousekeeperDaoImpl();
		List<Housekeeper> housekeeperList=housekeeperDao.getHousekeeper(sql, param);
		if(housekeeperList.size()>0) {
			return housekeeperList;
		}
		return null;
	}
	/**
	 * @Title: getHousekeeper_by_ID 
	 * @Description: 根据家政人员ID查询其相关信息
	 * @param @param id 家政人员编号
	 * @param @return
	 * @return List<Housekeeper> 查找到的家政人员List对象，失败时返回null
	 * @throws 
	 */
	default Housekeeper getHousekeeper_by_ID(int id){
		String sql = "select * from housekeeper where housekeeperID = ? ORDER BY avgScore DESC,state DESC,housekeeperID ASC";
		String[] param= {String.valueOf(id)};
		HousekeeperDao housekeeperDao=new HousekeeperDaoImpl();
		List<Housekeeper> housekeeperList=housekeeperDao.getHousekeeper(sql, param);
		if(housekeeperList.size()>0) {
			return housekeeperList.get(0);
		}
		return null;
	}
	/**
	 * @Title: getHousekeeper_by_time 
	 * @Description: 查找符合某个时间段的特定服务类型的家政人员
	 * @param @param service 服务类型
	 * @param @param startTime 工作开始时间
	 * @param @param endTime 工作结束时间
	 * @param @return
	 * @return List<Housekeeper> 查找到的家政人员List对象
	 * @throws 
	 */
	default List<Housekeeper> getHousekeeper_by_time(String service, String startTime, String endTime) {//部分有，实现相同
		HousekeeperDao housekeeperDao=new HousekeeperDaoImpl();
		String sql="SELECT * FROM housekeeper WHERE (service = ?) AND "
				+ "(? between startTime and endTime) AND"
				+ "(? between startTime and endTime) "
				+ "ORDER BY avgScore DESC,state DESC,housekeeperID ASC;";
		String[] param = {service,startTime,endTime};
		return housekeeperDao.getHousekeeper(sql,param);
	}
}
