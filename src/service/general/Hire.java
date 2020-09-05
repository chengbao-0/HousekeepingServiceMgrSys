/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：业务层，一些普通业务接口，通常只由一个角色菜单进行实现
 * @Package: service.common 
 * @author: chengbao_0  
 * @date: 2020-7-21 12:14:50 
 */
package service.general;

import entity.ServiceRecord;

/**
 * @ClassName Hire
 * @Desc 雇佣接口
 * @author chengbao_0
 * @Date 2020-7-21 12:48:19
 */
public interface Hire {
	/**
	 * @Title: ClientHireHousekeeper 
	 * @Description: 会员雇佣家政人员
	 * @param @param param 雇佣信息数组
	 * @param @return
	 * @return ServiceRecord 雇佣成功后生成服务记录表单
	 * @throws 
	 */
	public ServiceRecord ClientHireHousekeeper(String[] param);
}
