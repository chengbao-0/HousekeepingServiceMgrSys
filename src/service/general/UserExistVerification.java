/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：业务层，一些普通业务接口，通常只由一个角色菜单进行实现
 * @Package: service.common 
 * @author: chengbao_0  
 * @date: 2020-7-21 12:14:50 
 */
package service.general;

import java.util.List;

import dao.AdminDao;
import dao.ApplyForHousekeeperDao;
import dao.ClientDao;
import dao.HousekeeperDao;
import dao.impl.AdminDaoImpl;
import dao.impl.ApplyForHousekeeperDaoImpl;
import dao.impl.ClientDaoImpl;
import dao.impl.HousekeeperDaoImpl;
import entity.Admin;
import entity.ApplyForHousekeeper;
import entity.Client;
import entity.Housekeeper;
import entity.Role;

/**
 * @ClassName Login
 * @Desc 用户存在性验证接口
 * @author chengbao_0
 * @Date 2020-7-21 13:37:37
 */
public interface UserExistVerification {
	/**
	 * @Title: isUserExist 
	 * @Description: 检查用户是否存在
	 * @param @param user 待验证的用户名
	 * @param @return
	 * @return Role 返回验证成功的用户角色，验证失败时返回null
	 * @throws 
	 */
	static Role isUserExist(String user) {
		//检测是否为管理员登录
		Admin admin=null;
		AdminDao adminDao=new AdminDaoImpl();
		String sql = "select * from admin where user=?";
		String[] param = { user};
		admin= adminDao.getAdmin(sql, param);
		if(admin != null) {
			return admin;
		}
		//检测是否为用户登录
		Client client=null;
		ClientDao clientDao=new ClientDaoImpl();
		sql = "select * from client where user=?";
		List<Client> clientList=clientDao.getClient(sql, param);
		if(clientList.size()>0) {
			client=clientList.get(0);
			 return client;
		}
		//检测是否为家政人员登录
		Housekeeper housekeeper=null;
		HousekeeperDao housekeeperDao=new HousekeeperDaoImpl();
		sql = "select * from housekeeper where user=?";
		List<Housekeeper> housekeeperList=housekeeperDao.getHousekeeper(sql, param);
		if(housekeeperList.size()>0) {
			housekeeper=housekeeperList.get(0);
			return housekeeper;
		}
		//检测是否为待审核家政人员登录
		ApplyForHousekeeper applyForHousekeeper=null;
		ApplyForHousekeeperDao applyForHousekeeperDao=new ApplyForHousekeeperDaoImpl();
		sql = "select * from ApplyForHousekeeper where user=?";
		List<ApplyForHousekeeper> applyList=applyForHousekeeperDao.getApplyForHousekeeper(sql, param);
		if(applyList.size()>0) {
			applyForHousekeeper=applyList.get(0);
			return applyForHousekeeper;
		}
		return null;
	}
}
