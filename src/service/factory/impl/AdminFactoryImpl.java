/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：业务层，各个表单新建数据项的具体实现，采用了工厂模式进行创建
 * @Package: service.factory.impl 
 * @author: chengbao_0  
 * @date: 2020-7-29 20:40:04 
 */
package service.factory.impl;

import dao.AdminDao;
import dao.impl.AdminDaoImpl;
import entity.Admin;
import service.factory.AdminFactory;

/**
 * @ClassName AdminFactoryImpl
 * @Desc 管理员工厂实现类
 * @author chengbao_0
 * @Date 2020-7-18 16:39:19
 */
public class AdminFactoryImpl implements AdminFactory {
	/**
	 * 新建管理员对象
	 */
	@Override
	public Admin NewAdmin(String user, String pwd) {
		Admin admin=new Admin();
		admin.setUser(user);
		admin.setPwd(pwd);
		admin.setAdminID(getNextID());
		//插入到数据库中
		String sql = "insert into admin(user,pwd,adminID) values(?,MD5(?),?)";
		Object[] param = {user,pwd,String.valueOf(getNextID())};
		AdminDao adminDao=new AdminDaoImpl();
		int count = adminDao.updateAdmin(sql, param);
		if (count > 0) {//创建成功
			return admin;
		}
		return null;//创建失败
	}
	/**
	 * 获取新的ID
	 */
	@Override
	public int getNextID() {
		int nextID=new AdminDaoImpl().getCount("select Max(adminID) from Admin;",null)+1;
		if(nextID<Integer.MAX_VALUE) {
			return nextID;
		}else {
			return -1;
		}
	}

}
