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
 * @ClassName Count
 * @Desc ID获取接口
 * @author chengbao_0
 * @Date 2020年7月17日 下午8:36:45
 */
public interface NextID {
	/**
	 * @Title: getNextID 
	 * @Description: 获取新的ID，采用最大值+1的方式，避免编号的重复
	 * @param @return
	 * @return int 新生成的ID，当编号超出int类型容许的最大值时，返回-1
	 * @throws 
	 */
	public abstract int getNextID();
}
