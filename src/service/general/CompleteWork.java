/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：业务层，一些普通业务接口，通常只由一个角色菜单进行实现
 * @Package: service.common 
 * @author: chengbao_0  
 * @date: 2020-7-21 12:14:50 
 */
package service.general;

/**
 * @ClassName CompleteWork
 * @Desc (家政人员)完成工作打卡接口
 * @author chengbao_0
 * @Date 2020-7-21 12:14:50
 */
public interface CompleteWork {
	/**
	 * @Title: completeWork 
	 * @Description: 家政人员完成指定编号的表单进行打卡
	 * @param @param formID 服务记录表单编号
	 * @return void
	 * @throws 
	 */
	public void completeWork(long formID);
}
