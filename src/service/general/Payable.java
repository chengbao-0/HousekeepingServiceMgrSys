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
 * @ClassName Payable
 * @Desc 付费接口
 * @author chengbao_0
 * @Date 2020年7月16日 下午2:52:41
 */
public interface Payable {
	/**
	 * @Title: pay 
	 * @Description: 会员支付某个表单
	 * @param @param formID 待支付表单的ID
	 * @param @param clientID 会员编号
	 * @param @return
	 * @return boolean true--支付成功    false--支付失败
	 * @throws 
	 */
	public boolean pay(long formID,int clientID);
}
