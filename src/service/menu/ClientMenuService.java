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
import service.general.Evaluable;
import service.general.Hire;
import service.general.Payable;

/**
 * @ClassName ClientMenuService
 * @Desc 会员菜单业务接口
 * @author chengbao_0
 * @Date 2020-7-19 22:55:22
 */
public interface ClientMenuService extends Hire,ClientService,Evaluable,Payable,HousekeeperService,ServiceRecordService,ServiceService{
	/**
	 * 根据获取所有的服务记录表单
	 */
	public List<ServiceRecord> getServiceRecord_by_ID(int id);
	/**
	 * 根据会员ID获取其所有已支付的服务记录表单
	 */
	public List<ServiceRecord> getUnEvaluableServiceRecord(int clientID);
}
