/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：业务层，各个表单新建数据项的接口，采用工厂模式进行实现
 * @Package: service.factory 
 * @author: chengbao_0  
 * @date: 2020-7-29 20:38:30 
 */
package service.factory;

import entity.Housekeeper;
import service.general.NextID;

/**
 * @ClassName HousekeeperFactory
 * @Desc 家政人员工厂接口
 * @author chengbao_0
 * @Date 2020年7月17日 下午7:53:29
 */
public interface HousekeeperFactory extends NextID {
	/**
	 * @Title: NewHousekeeper 
	 * @Description: 登记新的家政人员
	 * @param @param param 家政人员的信息数组
	 * @param @return
	 * @return Housekeeper 新建的家政人员对象
	 * @throws 
	 */
	public abstract Housekeeper NewHousekeeper(String[] param);
}
