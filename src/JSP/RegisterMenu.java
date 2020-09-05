/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：表示层，不同界面的显示,同时与用户进行交互
 * @Package: JSP
 * @author: chengbao_0  
 * @date: 2020-7-29 18:48:10 
 */
package JSP;

import java.sql.Time;
import java.util.Scanner;

import entity.ApplyForHousekeeper;
import entity.Client;
import entity.Service;
import service.factory.impl.ApplyForHousekeeperFactoryImpl;
import service.factory.impl.ClientFactoryImpl;
import utils.Input;
import utils.PrintInfo;
import utils.enumeration.Sex;

/**
 * @ClassName RegisterMenuImpl
 * @Desc 注册界面类(采用单例模式)
 * @author chengbao_0
 * @Date 2020-7-19 22:33:07
 */
public class RegisterMenu {
	private static Scanner input = new Scanner(System.in);
	//实现单例模式
	private static final RegisterMenu instance=new RegisterMenu();
	private RegisterMenu() {};//private 避免类在外部被实例化
	@SuppressWarnings("unused")
	private static RegisterMenu getInstance() {
		return instance;
	}
	/**
	 * 注册类型选择界面
	 */
	public static void Register() {
		System.out.println("请选择:  输入1为新会员注册，输入2为家政人员注册");
    	boolean type = true;
		String num;
		while (type) {
			num = input.next();
			if ("1".equals(num)) {
				NewClientRegister();
				type = false;
			}else if ("2".equals(num)) {
				NewHousekeeperRegister();
				type = false;
			}else {
				System.out.println("输入有误，请按照指定规则输入");
				System.out.println("请选择:  输入1为新会员注册，输入2为家政人员注册");;
				type = true;
			}
		}
	}
	/** 
	 * 家政人员注册界面
	 */
	private static void NewHousekeeperRegister() {
		System.out.println("==============----欢迎新家政人员注册----==============\n");
		String param[]=new String[8];
		input.nextLine();//处理多余字符
		System.out.print("请输入用户名：");
		param[0]=Input.inputUserName();
		System.out.print("请输入密码：");
		param[1]=input.nextLine();
		System.out.print("确认密码: ");
		if(!input.nextLine().equals(param[1])) {
			System.out.println("两次密码输入不一致，注册失败!");
			System.out.println("请重新注册");
			return;
		}
		System.out.print("请输入姓名：");
		param[2]=input.nextLine();
		System.out.print("请输入性别(男、女)：");
		param[3]=Input.inputOtherType(Sex.class);
		System.out.println("请输入服务类别：/保健/维修/保姆/看护/保洁/");
		param[4]=Input.inputOtherType(Service.class);
		System.out.print("请输入联系电话: ");
		param[5]=Input.inputPhoneNumber();
		System.out.print("请输入开始工作时间(hh:mm:ss)：");
		param[6]=Input.inputOtherType(Time.class);
		System.out.print("请输入结束工作时间(hh:mm:ss)：");
		param[7]=Input.inputOtherType(Time.class);
		//进行注册
		ApplyForHousekeeper applyForHousekeeper=new ApplyForHousekeeperFactoryImpl().NewApply(param);
		if(applyForHousekeeper!=null) {
			System.out.println("已提交注册申请，正在等待管理员审核......\n\n");
		}else {
			System.out.println("注册失败!");
			System.out.println("请重新注册");
		}
		return;
	}

	/**
	 * 会员注册界面
	 */
	private static void NewClientRegister() {
		System.out.println("===============-----欢迎新会员注册-----===============\n");
		String param[]=new String[6];
		input.nextLine();//处理多余字符
		System.out.print("请输入用户名：");
		param[0]=Input.inputUserName();
		System.out.print("请输入密码：");
		param[1]=input.nextLine();
		System.out.print("确认密码: ");
		if(!input.nextLine().equals(param[1])) {
			System.out.println("两次密码输入不一致，注册失败!");
			System.out.println("请重新注册");
			return;
		}
		System.out.print("请输入姓名：");
		param[2]=input.nextLine();
		System.out.print("请输入性别(男、女)：");
		param[3]=Input.inputOtherType(Sex.class);
		System.out.print("请输入联系电话: ");
		param[4]=Input.inputPhoneNumber();
		System.out.print("请输入家庭住址: ");
		param[5]=input.next();
		//进行注册
		Client client=new ClientFactoryImpl().NewClientRegistered(param);
		if(client!=null) {
			System.out.println("恭喜您注册成功！\n\n");
			System.out.println("您的基本信息为：");
			PrintInfo.printSingleClient(client);
		}else {
			System.out.println("注册失败!");
			System.out.println("请重新注册");
		}
		return;
	}
}
