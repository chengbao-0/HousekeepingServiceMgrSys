/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：业务层，各个表单新建数据项的接口，采用工厂模式进行实现
 * @Package: service.factory 
 * @author: chengbao_0  
 * @date: 2020-7-29 20:38:30 
 */
package service.factory;

import entity.ApplyForHousekeeper;
import service.general.NextID;

/**
 * @ClassName ApplyForHousekeeperFactory
 * @Desc 待申请家政人员工厂接口
 * @author chengbao_0
 * @Date 2020-7-29 20:38:30
 */
public interface ApplyForHousekeeperFactory extends NextID{
	/**
	 * @Title: NewApply 
	 * @Description: 新建待申请家政人员
	 * @param @param param 待申请家政人员的信息数组
	 * @param @return
	 * @return ApplyForHousekeeper 新建的待申请家政人员对象
	 * @throws 
	 */
	public ApplyForHousekeeper NewApply(String[] param);
}
