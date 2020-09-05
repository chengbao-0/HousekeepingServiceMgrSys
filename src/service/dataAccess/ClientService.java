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

import dao.ClientDao;
import dao.impl.ClientDaoImpl;
import entity.Client;

/**
 * @ClassName ClientService
 * @Desc 会员相关业务
 * @author chengbao_0
 * @Date 2020年7月16日 下午3:01:41
 */
public interface ClientService{
	/**
	 * @Title: getClient 
	 * @Description: 根据编号获取会员的信息
	 * @param @param id 会员编号
	 * @param @return
	 * @return Client 查找的会员对象，如果查找失败返回null
	 * @throws 
	 */
	default Client getClient(int id){
		String sql = "select * from client where clientID = ?";
		String[] param= {String.valueOf(id)};
		ClientDao clientDao=new ClientDaoImpl();
		List<Client> clientList=clientDao.getClient(sql, param);
		if(clientList.size()>0) {
			return clientList.get(0);
		}
		return null;
	}
	/**
	 * @Title: updateClient 
	 * @Description: 更新会员信息
	 * @param @param param 待更新的数据项
	 * @param @return
	 * @return int 更新是否成功  int>0: 成功        int<=0: 失败
	 * @throws 
	 */
	public int updateClient(String[] param);//部分有，并且实现不同
}
