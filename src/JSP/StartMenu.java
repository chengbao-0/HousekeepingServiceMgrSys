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

import utils.Clear;
import utils.Input;
import utils.PrintMenu;

/**
 * @ClassName LoginMenuImpl
 * @Desc 登录界面类(采用单例模式)
 * @author chengbao_0
 * @Date 2020年7月17日 上午11:12:03
 */
public class StartMenu{
	private static Scanner input = new Scanner(System.in);
	//实现单例模式
	private static final StartMenu instance=new StartMenu();
	private StartMenu() { //private 避免类在外部被实例化
		SystemStart();
	}
	public static StartMenu getInstance() {
		return instance;
	}
	/**
	 * 系统启动后的登录界面
	 */
	private static void SystemStart() {
		PrintMenu.printStartMenu();//打印启动界面
        System.out.println("请选择: 1.登录  2.注册");
        boolean type=true;
        String num;
        while (type) {
			num=input.next();
			if ("1".equals(num)) {
				LoginMenu.Login();//登录
				type=false;
			} else if ("2".equals(num)){
				RegisterMenu.Register();//注册
				//注册后提示返回与否
				System.out.println("\n=====================-----END-----=====================\n");
				System.out.println("您是否返回系统启动界面，若是请输入y,退出系统请按任意键");
				String code=input.next();
				if(code.equals("y")){
					Clear.clear();//清屏
					PrintMenu.printStartMenu();//打印启动界面
			        System.out.println("请选择: 1.登录  2.注册");
					type=true;
				}else{
					type = Input.PromptAndChoose("\n确认退出?       y/n","y", ()->{
						Clear.delay(0.8);
						Clear.clear();//清屏
						PrintMenu.printEndMenu();
						return false;
					},()->{
						return true;
					});
				}
				
			}else {
				System.out.println("输入有误，请按照指定规则输入");
				type = true;
			}
		}
	}
}
