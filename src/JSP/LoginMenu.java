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

import entity.Admin;
import entity.ApplyForHousekeeper;
import entity.Client;
import entity.Housekeeper;
import entity.Role;
import service.general.UserExistVerification;
import service.menu.impl.AdminMenuServiceImpl;
import utils.Clear;
import utils.DBUtil;
import utils.Input;
import utils.MD5Util;
import utils.PrintMenu;
import utils.enumeration.RegisterState;

/**
 * @ClassName LoginMenuImpl
 * @Desc 登录界面类(采用单例模式)， 多态,给用户分配相应的角色
 * @author chengbao_0
 * @Date 2020-7-19 22:32:46
 */
public class LoginMenu {
	//不用角色登录菜单对象
	private static AdminMenu adminMenu=null;
	private static ClientMenu clientMenu=null;
	private static HousekeeperMenu housekeeperMenu=null;
	private static Scanner input = new Scanner(System.in);
	//实现单例模式
	private static final LoginMenu instance=new LoginMenu();
	private LoginMenu() {};//private 避免类在外部被实例化
	@SuppressWarnings("unused")
	private static LoginMenu getInstance() {
		return instance;
	}
	/**
	 * 用户登录界面
	 */
	public static void Login() {
		//获取不同角色登录菜单对象
		adminMenu=AdminMenu.getInstance();
		clientMenu=ClientMenu.getInstance();
		housekeeperMenu=HousekeeperMenu.getInstance();
		//登录界面
		boolean type=true;
		while(type) {
			System.out.print("请输入用户名：");
			String user = input.nextLine().trim();
			System.out.print("请输入密码：   ");
			String pwd = input.nextLine().trim();
			//登录检查
			Role role=UserExistVerification.isUserExist(user);
			if(role instanceof Admin) {//用户名正确
				//验证密码是否正确
				Admin admin=(Admin)role;//给用户分配管理员角色
				String password=MD5Util.MD5(pwd);
				if(password.equals(admin.getPwd())) {//密码正确
					System.out.println("您已登录成功!\n");
					System.out.println("欢迎您使用本系统, 载入中......");
					DBUtil.AutoBackup();//管理员登录时，启动数据库的自动备份
					Clear.delay(2.5);
					adminMenu.AdminChoose((Admin)role);//进入管理员主菜单
					DBUtil.service.shutdown();//管理员退出程序时，关闭该线程
					type=false;
				}else {//密码错误
					System.out.println("登录失败:  密码错误!");
					type=isReturn();
				}
			}else if (role instanceof Client) {//用户名正确
				//验证密码是否正确
				Client client=(Client)role;//给用户分配会员角色
				String password=MD5Util.MD5(pwd);
				if(password.equals(client.getPwd())) {//密码正确
					System.out.println("您已登录成功!\n");
					System.out.println("欢迎您使用本系统, 载入中......");
					Clear.delay(1.5);
					clientMenu.ClientChoose((Client)role);//进入会员菜单
					type=false;
				}else {//密码错误
					System.out.println("登录失败:  密码错误!");
					type=isReturn();
				}
			}else if (role instanceof Housekeeper) {//用户名正确
				//验证密码是否正确
				Housekeeper housekeeper=(Housekeeper)role;//给用户分配家政人员角色
				String password=MD5Util.MD5(pwd);
				if(password.equals(housekeeper.getPwd())) {//密码正确
					System.out.println("您已登录成功!\n");
					System.out.println("欢迎您使用本系统, 载入中......");
					Clear.delay(1.5);
					housekeeperMenu.HousekeeperChoose((Housekeeper)role);//进入家政人员主菜单
					type=false;
				}else {//密码错误
					System.out.println("登录失败:  密码错误!");
					type=isReturn();
				}
			}else if (role instanceof ApplyForHousekeeper) {//用户名正确
				//处理待审核的家政人员登录的情景
				ApplyForHousekeeper applyForHousekeeper=(ApplyForHousekeeper)role;//给用户分配待审核家政人员角色
				//给出审核通过与否的提示
				if(applyForHousekeeper.getRegisterState().equals(RegisterState.未通过)) {
					//审核未通过则删除该项申请，释放系统资源
					new AdminMenuServiceImpl().deleteApplyForHousekeeper(applyForHousekeeper.getID());
					System.out.println("您的审核未通过，注册失败");
				}else {
					System.out.println("您的注册申请正在审核中，目前不能登录");
				}
				type=isReturn();
			}else {//用户名错误，用户不存在
				System.out.println("登录失败:  用户不存在!");
				type=isReturn();
			}
		}
	}
	/**
	 * 用户退出系统的交互
	 */
	private static boolean isReturn() {
		return Input.PromptAndChoose("按y重新输入，按其他键退出系统", "y",()->{return true;},()->{
				return Input.PromptAndChoose("\n确认退出?       y/n","y", ()->{
				Clear.delay(0.8);
				Clear.clear();//清屏
				PrintMenu.printEndMenu();
				return false;
			},()->{
				return true;
		});});
	}
}
