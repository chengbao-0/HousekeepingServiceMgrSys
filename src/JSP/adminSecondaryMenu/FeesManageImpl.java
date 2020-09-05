/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：表示层，管理员二级菜单，显示菜单同时与用户进行交互
 * @Package: JSP.adminSecondaryMenu
 * @author: chengbao_0  
 * @date: 2020-7-29 18:48:10 
 */
package JSP.adminSecondaryMenu;

import java.util.List;
import java.util.Scanner;

import entity.Admin;
import entity.Service;
import service.factory.impl.ServiceFactoryImpl;
import service.menu.impl.AdminMenuServiceImpl;
import utils.Clear;
import utils.Input;
import utils.PrintInfo;
import utils.PrintMenu;

/**
 * @ClassName FeesManageImpl
 * @Desc 收费细则管理次级菜单
 * @author chengbao_0
 * @Date 2020-7-20 13:24:02
 */
public class FeesManageImpl {
	private static Scanner input = new Scanner(System.in);
	//实现单例模式
	private static final FeesManageImpl instance=new FeesManageImpl();
	private static Admin admin=null;
	private FeesManageImpl() {};
	public static FeesManageImpl getInstance() {
		return instance;
	}
	/**
	 * 收费细则管理菜单
	 */
	public void FeesManage(Admin a) {
		//将管理员角色信息存放到本地
		if(admin==null) {
			admin=a;
		}
		//菜单
		boolean type = true;
		boolean flag=true;
		while (type) {
			if(flag) {
				Clear.clear();//清屏
				Clear.delay(0.3);
				PrintMenu.printFeesManageMenu();//打印菜单
				System.out.println("请根据需要执行的操作，选择序号输入，返回主界面请输入0");
			}else {
				flag=true;
			}
			int num = input.nextInt();
			input.nextLine();
			switch (num) {
				case 0:
					type = false;
					break;
				case 1:
					SeeFeesRules();//查看收费细则
					type = !ReturnToAdminMainMenu.IsReturn();
					break;
				case 2:
					updateFeesRules();//修改服务费用
					type = !ReturnToAdminMainMenu.IsReturn();
					break;
				case 3:
					NewServiceType();//新增服务类别
					type = !ReturnToAdminMainMenu.IsReturn();
					break;
				default:
					System.out.println("输入有误,请重新输入");
					type = true;
					flag=false;
					break;
				}
		}
	}
	/** 
	 * @Title: SeeFeesRules 
	 * @Description: 查看收费细则
	 * @param 
	 * @return void
	 * @throws 
	*/
	private void SeeFeesRules() {
		System.out.println("================-----查看收费细则-----================");
		List<Service> serviceList=new AdminMenuServiceImpl().getAllService();
		if(serviceList!=null) {
			System.out.println("\n当前的收费细则如下: ");
			PrintInfo.printFeesRules(serviceList);
		}else {
			System.out.println("查询失败！");
		}
	}
	/** 
	 * @Title: updateFeesRules 
	 * @Description: 修改收费细则
	 * @param 
	 * @return void
	 * @throws 
	*/
	private void updateFeesRules() {
		System.out.println("================-----修改收费细则-----================");
		//显示当前收费细则
		List<Service> serviceList=new AdminMenuServiceImpl().getAllService();
		if(serviceList!=null) {
			System.out.println("\n当前的收费细则如下: ");
			PrintInfo.printFeesRules(serviceList);
		}else {
			System.out.println("查询失败！");
		}
		//开始进行修改
		String param[]=new String[2];
		System.out.print("请输入将要更改服务类型的编号:  ");
		param[1]=Input.inputNum(Integer.class);//编号的正确性
		System.out.print("请输入新的服务费用(时薪):  ");
		param[0]=Input.inputNum(Integer.class);
		//执行修改操作
		if(new AdminMenuServiceImpl().updateService(param)>0) {
			System.out.println("修改成功");
		}else {
			System.out.println("修改失败");
		}
	}
	/** 
	 * @Title: NewServiceType 
	 * @Description: 新增服务类别
	 * @param 
	 * @return void
	 * @throws 
	*/
	private void NewServiceType() {
		System.out.println("================-----新增服务类别-----================");
		//查看当前的收费细则
		List<Service> serviceList=new AdminMenuServiceImpl().getAllService();
		if(serviceList!=null) {
			System.out.println("\n当前的收费细则如下: ");
			PrintInfo.printFeesRules(serviceList);
		}else {
			System.out.println("查询失败！");
		}
		//新增服务类型
		String param[]=new String[2];
		System.out.print("请输入服务类型:  ");
		String s=null;
		boolean flag=false;
		do {
			s=input.next();
			if(Input.isServiceValide(s)) {
				System.out.println("服务类型重复!");
				System.out.println("请重新输入: ");
				flag=true;
			}else {
				flag=false;
			}
		} while (flag);
		param[0]=s;
		System.out.print("请输入服务时薪:  ");
		param[1]=Input.inputNum(Double.class);
		//执行新增操作
		Service service=new ServiceFactoryImpl().NewService(param);
		if(service!=null) {
			System.out.println("新增成功!\n");
			System.out.println("\n新的收费细则如下: ");
			serviceList=new AdminMenuServiceImpl().getAllService();
			PrintInfo.printFeesRules(serviceList);
		}else {
			System.out.println("新增失败");
		}
	}
	
}
