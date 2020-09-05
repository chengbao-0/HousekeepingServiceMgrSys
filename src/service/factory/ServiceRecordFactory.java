/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：业务层，各个表单新建数据项的接口，采用工厂模式进行实现
 * @Package: service.factory 
 * @author: chengbao_0  
 * @date: 2020-7-29 20:38:30 
 */
package service.factory;

import entity.ServiceRecord;

/**
 * @ClassName ServiceRecordFactory
 * @Desc 服务记录工厂接口
 * @author chengbao_0
 * @Date 2020年7月17日 下午6:37:38
 */
public interface ServiceRecordFactory{
	/**
	 * @Title: NewServiceRecord 
	 * @Description: 创建新的服务记录表单
	 * @param @param param 服务记录表单的信息数组
	 * @param @return
	 * @return ServiceRecord 新建的服务记录表单对象
	 * @throws 
	 */
	public ServiceRecord NewServiceRecord(String[] param);
}
