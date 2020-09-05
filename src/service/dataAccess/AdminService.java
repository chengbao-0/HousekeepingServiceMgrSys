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

import dao.impl.AdminDaoImpl;
import entity.Admin;

/**
 * @ClassName AdminService
 * @Desc //管理员相关业务
 * @author chengbao_0
 * @Date 2020年7月16日 下午3:01:29
 */
public interface AdminService{	
	/**
	 * @Title: getAllAdmin 
	 * @Description: 获取所有管理员的信息
	 * @param @return
	 * @return List<Admin> 管理员List对象
	 * @throws 
	 */
	default List<Admin> getAllAdmin(){
		return new AdminDaoImpl().getAllAdmin();
	}
	/**
	 * @Title: getAdmin 
	 * @Description: 根据管理员编号获取管理员信息
	 * @param @param id 管理员编号
	 * @param @return
	 * @return Admin 管理员对象
	 * @throws 
	 */
	default Admin getAdmin(int id){
		String sql = "select * from admin where adminID = ?";
		String[] param= {String.valueOf(id)};
		return new AdminDaoImpl().getAdmin(sql, param);
	}
	/**
	 * @Title: deleteAdmin 
	 * @Description: 根据管理员标号删除管理员信息
	 * @param @param id 管理员编号
	 * @param @return
	 * @return int 删除是否成功  int>0: 成功        int<=0: 删除失败
	 * @throws 
	 */
	default int deleteAdmin(int id){
		String sql = "delete from admin where adminID = ?";
		String[] param= {String.valueOf(id)};
		return new AdminDaoImpl().updateAdmin(sql, param);
	}
}
