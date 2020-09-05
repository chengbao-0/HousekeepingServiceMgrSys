/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：业务层，各个表单新建数据项的接口，采用工厂模式进行实现
 * @Package: service.factory 
 * @author: chengbao_0  
 * @date: 2020-7-29 20:38:30 
 */
package service.factory;

import entity.Admin;
import service.general.NextID;

/**
 * @ClassName AdminFactory
 * @Desc 管理员工厂接口
 * @author chengbao_0
 * @Date 2020-7-18 16:37:34
 */
public interface AdminFactory extends NextID{
	/**
	 * @Title: NewAdmin 
	 * @Description: 创建新管理员
	 * @param @param user 用户名
	 * @param @param pwd 密码
	 * @param @return
	 * @return Admin 新建的管理员对象
	 * @throws 
	 */
	public Admin NewAdmin(String user,String pwd);
}
