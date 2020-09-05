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

import dao.ApplyForHousekeeperDao;
import dao.impl.ApplyForHousekeeperDaoImpl;
import dao.impl.ServiceRecordDaoImpl;
import entity.ApplyForHousekeeper;

/**
 * @ClassName ApplyForHousekeeperService
 * @Desc 待审核家政人员相关业务
 * @author chengbao_0
 * @Date 2020-7-29 21:20:34
 */
public interface ApplyForHousekeeperService {
	/**
	 * @Title: getAllApply 
	 * @Description: 获取所有的待审核家政人员信息
	 * @param @return
	 * @return List<ApplyForHousekeeper> 待审核家政人员List对象
	 * @throws 
	 */
	default List<ApplyForHousekeeper> getAllApply() {
		return new ApplyForHousekeeperDaoImpl().getAllApplyForHousekeeper();
	}
	/**
	 * @Title: getApplyForHousekeeper 
	 * @Description: 根据编号获取待审核家政人员的信息
	 * @param @param id 待审核家政人员的编号
	 * @param @return
	 * @return ApplyForHousekeeper 待审核家政人员对象，如果查找失败返回null
	 * @throws 
	 */
	default ApplyForHousekeeper getApplyForHousekeeper(int id){
		String sql = "select * from ApplyForHousekeeper where ID = ?";
		String[] param= {String.valueOf(id)};
		ApplyForHousekeeperDao applyForHousekeeperDao=new ApplyForHousekeeperDaoImpl();
		List<ApplyForHousekeeper> applyList=applyForHousekeeperDao.getApplyForHousekeeper(sql, param);
		//适应数据访问层提供的方法，只返回第一个对象
		if(applyList.size()>0) {
			return applyList.get(0);
		}
		return null;
	}
	/**
	 * @Title: deleteApplyForHousekeeper 
	 * @Description: 根据编号删除待审核家政人员的信息
	 * @param @param id 待审核家政人员的编号
	 * @param @return
	 * @return int 删除是否成功  int>0: 成功        int<=0: 删除失败
	 * @throws 
	 */
	default int deleteApplyForHousekeeper(int id) {
		String sql = "delete from ApplyForHousekeeper where ID = ?;";
		String[] param= {String.valueOf(id)};
		return new ServiceRecordDaoImpl().updateServiceRecord(sql, param);
	}

}
