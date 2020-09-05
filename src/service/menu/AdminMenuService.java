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

import entity.Client;
import entity.Service;
import entity.ServiceRecord;
import service.dataAccess.AdminService;
import service.dataAccess.ApplyForHousekeeperService;
import service.dataAccess.ClientService;
import service.dataAccess.HousekeeperService;
import service.dataAccess.ServiceRecordService;
import service.dataAccess.ServiceService;
import service.general.Statistics;

/**
 * @ClassName AdminMenuService
 * @Desc 管理员菜单业务接口
 * @author chengbao_0
 * @Date 2020-7-19 22:54:22
 */
public interface AdminMenuService extends Statistics,AdminService,ClientService,HousekeeperService,
										ServiceRecordService,ServiceService,ApplyForHousekeeperService{
	/**
	 * 获取未支付服务记录表单
	 */
	public List<ServiceRecord> getUpaidServiceRecord();
	/**
	 * 根据会员编号删除会员
	 */
	public int deleteClient(int id);
	/**
	 * 获取所有的会员信息
	 */
	public List<Client> getAllClient();
	/**
	 * 根据服务类型编号获取服务类型信息
	 */
	public Service getService(int id) ;
	/**
	 * 根据传入的参数更新服务类型信息
	 */
	public int updateService(String[] param) ;
	/**
	 * 根据服务记录编号删除对应表单
	 */
	public int deleteServiceRecord(long id);
	/**
	 * 获取所有的服务记录表单编号
	 */
	public List<ServiceRecord> getAllServiceRecord();
	/**
	 * 根据家政人员编号删除相应的家政人员
	 */
	public int deleteHousekeeper(int id) ;
	/**
	 * 根据传入的参数更新服务记录表单信息
	 */
	public int updateServiceRecord(String[] param);
	/**
	 * 获取指定服务类型的表单记录
	 */
	public List<ServiceRecord> getServiceRecord_by_Service(String service);
	/**
	 * 审核通过指定ID的待审核家政人员
	 */
	public void passAudits(int id);
	/**
	 * 审核不通过指定ID的待审核家政人员ID
	 */
	public void failAudits(int id);
	/**
	 * 根据表单编号删除该表单的评论信息
	 */
	public int deleteServiceRecordEvaluation(long id);
}
