/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：表示层，不同界面的显示,同时与用户进行交互
 * @Package: JSP
 * @author: chengbao_0  
 * @date: 2020-7-29 18:48:10 
 */
package JSP;

import java.util.Scanner;

import JSP.adminSecondaryMenu.ClientManageImpl;
import JSP.adminSecondaryMenu.FeesManageImpl;
import JSP.adminSecondaryMenu.HousekeeperManageImpl;
import JSP.adminSecondaryMenu.ServiceRecordManageImpl;
import JSP.adminSecondaryMenu.SystemManageImpl;
import entity.Admin;
import utils.Clear;
import utils.Input;
import utils.PrintMenu;

/**
 * @ClassName AdminMenuImpl
 * @Desc 管理员菜单(单例模式)
 * @author chengbao_0
 * @Date 2020年7月17日 上午11:11:37
 */

public class AdminMenu{
	//实现单例模式
	private static final AdminMenu instance=new AdminMenu();
	private AdminMenu() {}//private 避免类在外部被实例化
	public static AdminMenu getInstance() {
		return instance;
	}
	//通用变量
	private static Scanner input = new Scanner(System.in);
	private static Admin admin=null;
	//二级菜单对象
	private static ClientManageImpl clientMenu=null;
	private static HousekeeperManageImpl housekeeperMenu=null;
	private static ServiceRecordManageImpl serviceRecordMenu=null;
	private static SystemManageImpl systemMenu=null;
	private static FeesManageImpl feesMenu=null;
	
	/**
	 * @Title: AdminChoose 
	 * @Description: 管理员主界面选择菜单
	 * @param @param a 登录的管理员角色
	 * @return void
	 * @throws 
	 */
	public void AdminChoose(Admin a) {
		//将管理员角色信息存放到本地管理员
		if(admin==null) {
			admin=a;
		}
		//获取二级菜单对象
		clientMenu=ClientManageImpl.getInstance();
		housekeeperMenu=HousekeeperManageImpl.getInstance();
		serviceRecordMenu=ServiceRecordManageImpl.getInstance();
		systemMenu=SystemManageImpl.getInstance();
		feesMenu=FeesManageImpl.getInstance();
		//管理员主界面选择菜单
		boolean type = true;
		boolean flag=true;
		while (type) {
			if(flag) {
				Clear.clear();//清屏
				PrintMenu.printAdminMainMenu();//打印管理员主菜单
				System.out.println("请根据需要执行的操作，选择序号输入，退出系统请输入0");
			}else {
				flag=true;
			}
			int num = input.nextInt();
			input.nextLine();
			switch (num) {
				case 0:
					type = Input.PromptAndChoose("\n确认退出?       y/n","y", ()->{
						Clear.delay(0.8);
						Clear.clear();//清屏
						PrintMenu.printEndMenu();
						return false;
					},()->{
						return true;
					});
					break;
				case 1:
					clientMenu.ClientManage(admin);//会员管理
					break;
				case 2:
					housekeeperMenu.HousekeeperManage(admin);//家政人员管理
					break;
				case 3:
					serviceRecordMenu.ServiceRecordManage(admin);//家政服务记录管理
					break;
				case 4:
					feesMenu.FeesManage(admin);//收费细则管理
					break;
				case 5:
					systemMenu.SystemManage(admin);//系统管理
					break;
				default:
					System.out.println("输入有误,请重新输入");
					flag=false;
					break;
				}
		}
	}
}