/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：业务层，不同角色菜单业务的接口，继承其他需要的业务接口，并声明一些独属于自己的方法
 * @Package: service.menu
 * @author: chengbao_0  
 * @date: 2020-7-21 12:14:50 
 */
package service.menu;

import java.util.List;

import entity.ServiceRecord;
import service.dataAccess.ClientService;
import service.dataAccess.HousekeeperService;
import service.dataAccess.ServiceRecordService;
import service.dataAccess.ServiceService;
import service.general.CompleteWork;

/**
 * @ClassName HousekeeperMenuService
 * @Desc 家政人员菜单业务接口
 * @author chengbao_0
 * @Date 2020-7-19 22:55:36
 */
public interface HousekeeperMenuService extends ServiceService,HousekeeperService,ServiceRecordService,CompleteWork,ClientService{
	/**
	 * @Title: getFormOngoing 
	 * @Description: 根据家政人员编号获取正在进行的表单记录
	 * @param @param housekeeperID 家政人员编号
	 * @param @return
	 * @return List<ServiceRecord> 正在进行的表单记录的List对象
	 * @throws 
	 */
	public List<ServiceRecord> getFormOngoing(int housekeeperID);
	/**
	 * @Title: getServiceRecord_by_ID 
	 * @Description: 根据家政人员编号获取其所有的服务记录表单
	 * @param @param housekeeperID 家政人员编号
	 * @param @return
	 * @return List<ServiceRecord> 所有服务记录的List对象
	 * @throws 
	 */
	public List<ServiceRecord> getServiceRecord_by_ID(int housekeeperID);
	/**
	 * @Title: setWorkState 
	 * @Description: 设置工作状态
	 * @param @param housekeeperID 家政人员编号
	 * @param @param state 工作状态(空闲/忙碌)
	 * @param @return
	 * @return int true--设置成功   false--设置失败
	 * @throws 
	 */
	public int setWorkState(int housekeeperID,String state);
}
