/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：业务层，各个表单新建数据项的具体实现，采用了工厂模式进行创建
 * @Package: service.factory.impl 
 * @author: chengbao_0  
 * @date: 2020-7-29 20:40:04 
 */
package service.factory.impl;

import dao.ClientDao;
import dao.impl.ClientDaoImpl;
import entity.Client;
import service.factory.ClientFactory;
import utils.enumeration.PaidState;
import utils.enumeration.Sex;

/**
 * @ClassName ClientFactoryImpl
 * @Desc 会员工厂实现类
 * @author chengbao_0
 * @Date 2020年7月16日 下午7:31:41
 */
public class ClientFactoryImpl implements ClientFactory {
	/**
	 * 新用户（会员）注册
	 */
	@Override
	public Client NewClientRegistered(String[] param) {
		//创建新会员
		Client client=new Client();
		client.setUser(param[0]);
		client.setPwd(param[1]);
		client.setClientID(getNextID());//获取总数+1
		client.setName(param[2]);
		client.setSex(Sex.valueOf(param[3]));
		client.setPhone(param[4]);
		client.setAddress(param[5]);
		client.setPaidState(PaidState.正常);
		//插入到数据库中
		String sql = "insert into client(user,pwd,clientID,name,sex,phone,address,paidState) values(?,MD5(?),?,?,?,?,?,?)";
		Object[] next_param = {param[0],param[1],String.valueOf(getNextID()),param[2],param[3],param[4],param[5],PaidState.正常.toString()};
		ClientDao clientDao=new ClientDaoImpl();
		int count = clientDao.updateClient(sql, next_param);
		if (count > 0) {//创建成功
			return client;
		}
		return null;//创建失败
	}
	/**
	 * 获取新的ID
	 */
	@Override
	public int getNextID() {
		int nextID=new ClientDaoImpl().getCount("select Max(clientID) from Client",null)+1;
		if(nextID<Integer.MAX_VALUE) {
			return nextID;
		}else {
			return -1;
		}
	}

}
