/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：业务层，各个表单新建数据项的具体实现，采用了工厂模式进行创建
 * @Package: service.factory.impl 
 * @author: chengbao_0  
 * @date: 2020-7-29 20:40:04 
 */
package service.factory.impl;

import dao.ServiceDao;
import dao.impl.ServiceDaoImpl;
import entity.Service;
import service.factory.ServiceFactory;

/**
 * @ClassName ServiceFactoryImpl
 * @Desc 服务类型工厂实现类
 * @author chengbao_0
 * @Date 2020-7-20 19:51:15
 */
public class ServiceFactoryImpl implements ServiceFactory {
	/**
	 * 创建新的服务类型对象
	 */
	@Override
	public Service NewService(String[] param) {
		//新建服务类型对象作为返回值
		Service service=new Service();
		service.setServiceID(getNextID());
		service.setService(param[0]);
		service.setHourlyWage(Double.valueOf(param[1]));
		//插入到数据库
		String sql = "insert into service(serviceID,service,hourlyWage) values(?,?,?)";
		Object[] next_param = {String.valueOf(getNextID()),param[0],param[1]};
		ServiceDao serviceDao=new ServiceDaoImpl();
		int count = serviceDao.updateService(sql, next_param);
		if (count > 0) {//创建成功
			return service;
		}
		return null;//创建失败
	}

	@Override
	public int getNextID() {
		return new ServiceDaoImpl().getCount()+1;
	}
}
