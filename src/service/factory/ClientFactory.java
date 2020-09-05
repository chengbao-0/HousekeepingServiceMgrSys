/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：业务层，各个表单新建数据项的接口，采用工厂模式进行实现
 * @Package: service.factory 
 * @author: chengbao_0  
 * @date: 2020-7-29 20:38:30 
 */
package service.factory;

import entity.Client;
import service.general.NextID;

/**
 * @ClassName ClientFactory
 * @Desc 会员工厂接口
 * @author chengbao_0
 * @Date 2020年7月16日 下午7:29:51
 */
public interface ClientFactory extends NextID {
	/**
	 * @Title: NewClientRegistered 
	 * @Description: 新用户（会员）注册
	 * @param @param clientParam 会员的信息数组
	 * @param @return
	 * @return ClientService 新建的会员对象
	 * @throws 
	 */
	public Client NewClientRegistered(String[] param);
}
