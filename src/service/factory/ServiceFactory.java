/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：业务层，各个表单新建数据项的接口，采用工厂模式进行实现
 * @Package: service.factory 
 * @author: chengbao_0  
 * @date: 2020-7-29 20:38:30 
 */
package service.factory;

import entity.Service;
import service.general.NextID;

/**
 * @ClassName ServiceFactory
 * @Desc 服务类型工厂接口
 * @author chengbao_0
 * @Date 2020-7-20 19:50:10
 */
public interface ServiceFactory extends NextID{
	/**
	 * @Title: NewService 
	 * @Description: 新建服务类型
	 * @param @param param 服务类型的信息数组
	 * @param @return
	 * @return Service 新建的服务类型对象
	 * @throws 
	 */
	public abstract Service NewService(String[] param);
}
