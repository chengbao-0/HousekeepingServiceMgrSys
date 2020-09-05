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
import entity.Client;
import service.factory.impl.ClientFactoryImpl;
import service.menu.AdminMenuService;
import service.menu.impl.AdminMenuServiceImpl;
import utils.Clear;
import utils.Input;
import utils.PrintInfo;
import utils.PrintMenu;
import utils.enumeration.PaidState;
import utils.enumeration.Sex;

/**
 * @ClassName ClientManageImpl
 * @Desc 会员管理次级菜单
 * @author chengbao_0
 * @Date 2020-8-1 11:02:17
 */
public class ClientManageImpl implements ReturnToAdminMainMenu{
	private static Scanner input = new Scanner(System.in);
	private static Admin admin=null;
	//实现单例模式
	private static final ClientManageImpl instance=new ClientManageImpl();
	private ClientManageImpl() {}//private 避免类在外部被实例化
	public static ClientManageImpl getInstance() {
		return instance;
	}
	/**
	 * 会员管理次级菜单
	 */
	public void ClientManage(Admin a) {
		//将管理员角色信息存放到本地管理员
		if(admin==null) {
			admin=a;
		}
		//菜单
		boolean type = true;
		boolean flag = true;
		while (type) {
			if(flag) {
				Clear.clear();//清屏
				Clear.delay(0.3);
				PrintMenu.printClientManageMenu();//打印菜单
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
					NewClient();//新增会员信息
					type = !ReturnToAdminMainMenu.IsReturn();
					break;
				case 2:
					DeleteClient();//删除会员信息
					type = !ReturnToAdminMainMenu.IsReturn();
					break;
				case 3:
					UpdateClient();//更改会员信息
					type = !ReturnToAdminMainMenu.IsReturn();
					break;
				case 4:
					QueryAllClient();//查询所有会员的信息
					type = !ReturnToAdminMainMenu.IsReturn();
					break;
				case 5:
					QueryClientByID();//查询具体ID的会员
					type = !ReturnToAdminMainMenu.IsReturn();
					break;
				default:
					System.out.println("输入有误,请重新输入");
					type = true;
					flag = false;
					break;
				}
		}
	}
	/** 
	 * @Title: NewClient 
	 * @Description: 新增会员信息
	 * @param 
	 * @return void
	 * @throws 
	*/
	private static void NewClient() {
		System.out.println("================-----新增会员信息-----================");
		String param[]=new String[6];
		System.out.print("请输入用户名：");
		param[0]=Input.inputUserName();
		System.out.print("请输入密码：");
		param[1]=input.nextLine();
		System.out.print("确认密码: ");
		if(!input.nextLine().equals(param[1])) {
			System.out.println("两次密码输入不一致，新增失败!");
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
		//新增会员
		Client client=new ClientFactoryImpl().NewClientRegistered(param);
		if(client!=null) {
			System.out.println("新增成功！\n");
			System.out.println("\n会员基本信息为：");
			PrintInfo.printSingleClient(client);
		}else {
			System.out.println("新增失败!");
		}
		return;
	}
	/** 
	 * @Title: DeleteClient 
	 * @Description: 删除会员信息
	 * @param 
	 * @return void
	 * @throws 
	*/
	private static void DeleteClient() {
		System.out.println("================-----删除会员信息-----================");
		System.out.println("请输入您所要删除的会员ID: ");
		int id=Integer.parseInt(Input.inputNum(Integer.class));
		//查询该id的会员
		AdminMenuService adminMenuService=new AdminMenuServiceImpl();
		Client client=adminMenuService.getClient(id);
		if(client!=null) {//查询成功
			//打印该会员的信息
			System.out.println("\nID="+id+"的会员信息为: ");
			PrintInfo.printSingleClient(client);
			//提示确认删除
			Input.PromptAndChoose("确认删除？（y/n）","y", ()->{
				int result=adminMenuService.deleteClient(id);//进行删除操作
				if(result>0) {//删除成功
					System.out.println("已删除ID="+id+"的会员");
				}else {
					System.out.println("删除失败");
				}
			});
		}else {//查询失败
			System.out.println("该会员不存在！请确认ID是否正确");
		}
	}
	/** 
	 * @Title: UpdateClient 
	 * @Description: 更改会员信息
	 * @param 
	 * @return void
	 * @throws 
	*/
	private static void UpdateClient() {
		System.out.println("================-----更改会员信息-----================");
		System.out.println("请输入您所要更改信息的会员ID: ");
		int id=Integer.parseInt(Input.inputNum(Integer.class));
		//查询该id的会员
		AdminMenuService adminMenuService=new AdminMenuServiceImpl();
		Client client=adminMenuService.getClient(id);
		if(client!=null) {//查询成功
			//打印该id的会员信息
			System.out.println("\nID="+id+"的会员当前信息为: ");
			PrintInfo.printSingleClient(client);
			//进行信息更改
			System.out.println("开始进行信息更改: ");
			String param[]=new String[7];
			System.out.println("注: 1.如需更改请直接输入新数据 2.用户名、会员编号不可更改\n");//如果可以更改需要进行重复性验证
			System.out.print("密码: ");//对输入的正确性进行检验
			String s=input.next();
			param[0]="n".equals(s)?client.getPwd():s;
			System.out.print("姓名: ");
			s=input.next();
			param[1]="n".equals(s)?client.getName():s;
			System.out.print("性别(男/女): ");
			s=Input.inputOtherType(Sex.class);
			param[2]="n".equals(s)?client.getSex().toString():s;
			System.out.print("联系电话: ");
			s=Input.inputChangePhoneNumber();
			param[3]="n".equals(s)?client.getPhone():s;
			System.out.print("家庭住址: ");
			s=input.next();
			param[4]="n".equals(s)?client.getAddress():s;
			System.out.print("付费状态(待支付/正常): ");
			s=Input.inputOtherType(PaidState.class);
			param[5]="n".equals(s)?client.getPaidState().toString():s;
			param[6]=String.valueOf(client.getClientID());
			//提示确认更改
			System.out.println("确认更改？（y/n）");
			if("y".equals(input.next())){//确认更改
				int result=adminMenuService.updateClient(param);//执行更改操作
				if(result>0) {
					System.out.println("更改成功！");
					client=adminMenuService.getClient(id);
					System.out.println("ID="+id+"的会员更改后的信息为: ");
					PrintInfo.printSingleClient(client);
				}else {
					System.out.println("更改失败");
				}
			}
		}else {//查询失败
			System.out.println("该会员不存在！请确认ID是否正确");
		}
	}
	/** 
	 * @Title: QueryAllClient 
	 * @Description: 查询所有会员信息
	 * @param 
	 * @return void
	 * @throws 
	*/
	private static void QueryAllClient() {
		System.out.println("===============----查询所有会员信息----===============");
        System.out.println("\n所有会员信息如下:");
        List<Client> clientList=new AdminMenuServiceImpl().getAllClient();
		PrintInfo.printClientList(clientList);
		return;
	}
	/** 
	 * @Title: QueryClientByID 
	 * @Description: 查询指定ID的会员信息
	 * @param 
	 * @return void
	 * @throws 
	*/
	private static void QueryClientByID() {
		System.out.println("=============---查询指定编号的会员信息---=============");
		System.out.println("请输入您所要查询会员的ID: ");
		int id=Integer.parseInt(Input.inputNum(Integer.class));
		//查询该id的会员
		Client client=new AdminMenuServiceImpl().getClient(id);
		if(client!=null) {//查询成功
			System.out.println("查询成功");
			System.out.println("\nID="+id+"的会员信息为: ");
			PrintInfo.printSingleClient(client);
		}else {//查询失败
			System.out.println("该会员不存在！请确认ID是否正确");
		}
		return;
	}
}
