/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：业务层，数据访问业务接口，处理不同角色公共的数据访问业务
 * 		如果方法实现相同则在接口中声明的同时，实现为default方法
 * 		如果方法实现不同则只进行声明
 * 		如果只有单个角色需要某个方法，则由其接口声明，本数据访问业务接口不进行声明；除非没有其他角色实现该接口
 * @Package: service.dataAccess 
 * @author: chengbao_0  
 * @date: 2020-7-29 21:20:34 
 */
package service.dataAccess;

import java.util.List;

import dao.impl.ServiceDaoImpl;
import entity.Service;

/**
 * @ClassName ServiceService
 * @Desc 服务类型相关业务
 * @author chengbao_0
 * @Date 2020-7-20 11:55:08
 */
public interface ServiceService {
	/**
	 * @Title: getAllService 
	 * @Description: 获取所有的服务类型信息
	 * @param @return
	 * @return List<Service> 服务类型List对象
	 * @throws 
	 */
	default List<Service> getAllService(){
		return new ServiceDaoImpl().getAllService();
	}
	default int getCount() {
		return new ServiceDaoImpl().getCount();
	}
}
