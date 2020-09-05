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

import java.util.ArrayList;
import java.util.List;

import dao.ServiceRecordDao;
import dao.impl.ServiceRecordDaoImpl;
import entity.HistoricalEvaluation;
import entity.ServiceRecord;

/**
 * @ClassName FormService
 * @Desc 服务记录表单相关业务
 * @author chengbao_0
 * @Date 2020年7月17日 下午7:27:53
 */
public interface ServiceRecordService {
	/**
	 * @Title: getServiceRecord_by_ID 
	 * @Description: 根据编号获取服务记录表单，具体编号可由实现类进行重载
	 * @param @param formID 表单标号
	 * @param @param clientID 会员标号
	 * @param @param housekeeperID 家政人员编号
	 * @param @return
	 * @return List<ServiceRecord> 服务记录表单List对象
	 * @throws 
	 */
	default List<ServiceRecord> getServiceRecord_by_ID(long formID,int clientID,int housekeeperID) {
		ServiceRecordDao serviceRecordDao=new ServiceRecordDaoImpl();
		String sql=null;
		String[] param=null;
		if(formID>0) {//只根据表单ID进行查询
			param=new String[1];
			sql="select * from ServiceRecord where formID = ?;";
			param[0]=String.valueOf(formID);
		}else {//不根据表单ID进行查询
			if(clientID>0) {
				if(housekeeperID>0) {//同时根据会员ID和家政人员ID进行查询
					param=new String[2];
					sql="select * from ServiceRecord where clientID = ? AND housekeeperID = ?;";
					param[0]=String.valueOf(clientID);
					param[1]=String.valueOf(housekeeperID);
				}else {////只根据会员ID进行查询
					param=new String[1];
					sql="select * from ServiceRecord where clientID = ?;";
					param[0]=String.valueOf(clientID);
				}
			}else {//只根据家政人员ID进行查询
				param=new String[1];
				sql="select * from ServiceRecord where housekeeperID = ?;";
				param[0]=String.valueOf(housekeeperID);
			}
		}
		List<ServiceRecord> serviceRecordList=serviceRecordDao.getServiceRecord(sql, param);
		if(serviceRecordList!=null&&serviceRecordList.size()>0) {
			return serviceRecordList;
		}else {
			return null;
		}
	}
	/**
	 * @Title: getUnpaidServiceRecord 
	 * @Description: 根据编号获取未支付的服务记录，重载
	 * @param @param id 具体的编号类型
	 * @param @return
	 * @return List<ServiceRecord> 服务记录List对象
	 * @throws 
	 */
	public List<ServiceRecord> getUnpaidServiceRecord(int id);//不同的实现不同，因为id不同
	/**
	 * @Title: getHistoricalEvaluation 
	 * @Description: 获取指定家政人员编号的历史评价信息
	 * @param @param housekeeperID 家政人员编号
	 * @param @return
	 * @return List<HistoricalEvaluation> 历史评价List对象，没有历史评价时返回null
	 * @throws 
	 */
	default List<HistoricalEvaluation> getHistoricalEvaluation(int housekeeperID) {
		//获取当前ID家政人员的所有服务记录
		List<ServiceRecord> list=getServiceRecord_by_ID(0, 0, housekeeperID);
		if(list!=null&&list.size()>0) {//是否存在服务记录
			//存在服务记录
			//从服务记录List中创建历史评价List
			List<HistoricalEvaluation> historyList=new ArrayList<HistoricalEvaluation>();
			for(ServiceRecord s:list) {
				//会员ID，评分，评价内容
				HistoricalEvaluation historicalEvaluation=new HistoricalEvaluation();
				historicalEvaluation.setClientID(s.getClientID());
				historicalEvaluation.setScore(s.getClientScore());
				historicalEvaluation.setEvaluation(s.getClientEvaluate());
				historyList.add(historicalEvaluation);
			}
			return historyList;
		}else {//该家政人员没有服务记录
			return null;
		}
	}
}
