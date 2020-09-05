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
 * @ClassName Evaluable
 * @Desc (会员)评价接口
 * @author chengbao_0
 * @Date 2020年7月16日 下午2:49:58
 */
public interface Evaluable {
	/**
	 * @Title: evaluate 
	 * @Description: 会员评价家政人员
	 * @param @param formID 待评价的表单编号
	 * @param @param score 评分
	 * @param @param evaluateStr 评价内容
	 * @param @return
	 * @return boolean 评价是否成功   true--成功   false--失败
	 * @throws 
	 */
	public boolean evaluate(long formID,double score,String evaluateStr);
}
