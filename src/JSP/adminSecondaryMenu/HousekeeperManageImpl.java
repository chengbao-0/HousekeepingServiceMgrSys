/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：表示层，管理员二级菜单，显示菜单同时与用户进行交互
 * @Package: JSP.adminSecondaryMenu
 * @author: chengbao_0  
 * @date: 2020-7-29 18:48:10 
 */
package JSP.adminSecondaryMenu;

import java.sql.Time;
import java.util.List;
import java.util.Scanner;

import entity.Admin;
import entity.ApplyForHousekeeper;
import entity.HistoricalEvaluation;
import entity.Housekeeper;
import entity.Service;
import service.factory.impl.HouseKeeperFactoryImpl;
import service.menu.AdminMenuService;
import service.menu.impl.AdminMenuServiceImpl;
import utils.Clear;
import utils.Input;
import utils.PrintInfo;
import utils.PrintMenu;
import utils.enumeration.HireState;
import utils.enumeration.Sex;

/**
 * @ClassName AdminMenu_HousekeeperManage
 * @Desc 家政人员管理次级菜单
 * @author chengbao_0
 * @Date 2020-7-19 12:56:37
 */
public class HousekeeperManageImpl implements ReturnToAdminMainMenu {
	private static Scanner input = new Scanner(System.in);
	//实现单例模式
	private static final HousekeeperManageImpl instance=new HousekeeperManageImpl();
	private static Admin admin=null;
	private HousekeeperManageImpl() {}//private 避免类在外部被实例化
	public static HousekeeperManageImpl getInstance() {
		return instance;
	}
	/**
	 * 家政人员管理次级菜单
	 */
	public void HousekeeperManage(Admin a) {
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
				PrintMenu.printHousekeeperManageMenu();//打印菜单
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
					NewHousekeeper();//新增家政人员信息
					type = !ReturnToAdminMainMenu.IsReturn();
					break;
				case 2:
					DeleteHousekeeper();//删除家政人员信息
					type = !ReturnToAdminMainMenu.IsReturn();
					break;
				case 3:
					UpdateHousekeeper();//更改家政人员信息
					type = !ReturnToAdminMainMenu.IsReturn();
					break;
				case 4:
					QueryAllHousekeeper();//查询所有家政人员的信息
					type = !ReturnToAdminMainMenu.IsReturn();
					break;
				case 5:
					QueryHousekeeperByService();//查询某类服务的所有家政人员
					type = !ReturnToAdminMainMenu.IsReturn();
					break;
				case 6:
					QueryHousekeeperByID();//查询具体ID的家政人员
					type = !ReturnToAdminMainMenu.IsReturn();
					break;
				case 7:
					HousekeeperRegistrationReview();//家政人员注册审核
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
	 * @Title: NewHousekeeper 
	 * @Description: 新增家政人员信息
	 * @param 
	 * @return void
	 * @throws 
	*/
	private static void NewHousekeeper() {
		System.out.println("==============-----新增家政人员信息-----==============");
		String param[]=new String[8];
		System.out.print("请输入用户名：");
		param[0]=Input.inputUserName();
		System.out.print("请输入密码：");
		param[1]=input.next();
		System.out.print("确认密码: ");
		if(!input.next().equals(param[1])) {
			System.out.println("两次密码输入不一致，新增失败!");
			return;
		}
		System.out.print("请输入姓名：");
		param[2]=input.next();
		System.out.print("请输入性别：男/女");
		param[3]=input.next();
		System.out.println("请输入服务类别：/保健/维修/保姆/看护/保洁/");
		param[4]=input.next();
		System.out.print("请输入联系电话: ");
		param[5]=Input.inputPhoneNumber();
		System.out.print("请输入开始工作时间(hh:mm:ss)：");
		param[6]=input.next();
		System.out.print("请输入结束工作时间(hh:mm:ss)：");
		param[7]=input.next();
		//执行新增操作
		Housekeeper housekeeper=new HouseKeeperFactoryImpl().NewHousekeeper(param);
		if(housekeeper!=null) {
			System.out.println("新增成功！");
			System.out.println("\n家政人员基本信息为：");
			PrintInfo.printSingleHousekeeper(housekeeper);
		}else {
			System.out.println("新增失败!");
		}
		return;
	}
	/** 
	 * @Title: DeleteHousekeeper 
	 * @Description: 删除家政人员信息 
	 * @param 
	 * @return void
	 * @throws 
	*/
	private static void DeleteHousekeeper() {
		System.out.println("==============-----删除家政人员信息-----==============");
		System.out.println("请输入您所要删除的家政人员ID: ");
		int id=Integer.parseInt(Input.inputNum(Integer.class));
		//查询该id的家政人员
		AdminMenuService adminMenuService=new AdminMenuServiceImpl();
		Housekeeper housekeeper=adminMenuService.getHousekeeper_by_ID(id);
		if(housekeeper!=null) {//查询成功
			//打印该id家政人员的信息
			System.out.println("\nID="+id+"的家政人员信息为: ");
			PrintInfo.printSingleHousekeeper(housekeeper);
			//提示确认删除
			Input.PromptAndChoose("确认删除？（y/n）","y",()->{
				int result=adminMenuService.deleteHousekeeper(id);
				if(result>0) {
					System.out.println("已删除ID="+id+"的家政人员");
				}else {
					System.out.println("删除失败");
				}
			});
		}else {//查询失败
			System.out.println("该家政人员不存在！请确认ID是否正确");
		}
	}
	/** 
	 * @Title: UpdateHousekeeper 
	 * @Description: 更改家政人员信息
	 * @param 
	 * @return void
	 * @throws 
	*/
	private static void UpdateHousekeeper() {
		System.out.println("==============-----更改家政人员信息-----==============");
		System.out.println("请输入您所要更改信息的家政人员ID: ");
		int id=Integer.parseInt(Input.inputNum(Integer.class));
		//查询该id的家政人员
		AdminMenuService adminMenuService=new AdminMenuServiceImpl();
		Housekeeper housekeeper=adminMenuService.getHousekeeper_by_ID(id);
		if(housekeeper!=null) {//查询成功
			//打印其信息
			System.out.println("\nID="+id+"的家政人员当前信息为: ");
			PrintInfo.printSingleHousekeeper(housekeeper);
			//进行信息更改
			System.out.println("开始进行信息更改: ");//理论上是可以更改的，但可能会造成冲突,考虑这种情况，
			String param[]=new String[9];
			System.out.println("注: 1.如需更改请直接输入新数据 2.家政人员编号，历史评分不可更改");//如果可以更改需要进行重复性验证
			System.out.print("密码: ");
			String s=input.next();
			param[0]="n".equals(s)?housekeeper.getPwd():s;
			System.out.print("姓名: ");//对输入的正确性进行检验
			s=input.next();
			param[1]="n".equals(s)?housekeeper.getName():s;
			System.out.print("性别(男/女): ");
			s=Input.inputOtherType(Sex.class);
			param[2]="n".equals(s)?housekeeper.getSex().toString():s;
			System.out.println("服务类型(保健、维修、保姆、看护、保洁): ");
			s=Input.inputOtherType(Service.class);
			param[3]="n".equals(s)?housekeeper.getService().toString():s;
			System.out.print("联系电话: ");
			s=Input.inputChangePhoneNumber();
			param[4]="n".equals(s)?housekeeper.getPhone():s;
			System.out.print("开始工作时间(hh:mm:ss)：");
			s=Input.inputOtherType(Time.class);
			param[5]="n".equals(s)?housekeeper.getStartTime().toString():s;
			System.out.print("结束工作时间(hh:mm:ss)：");
			s=Input.inputOtherType(Time.class);
			param[6]="n".equals(s)?housekeeper.getEndTime().toString():s;
			//雇主编号——————如果更改了雇佣状态，就必须更改雇主编号，无法单独更改雇主编号
			System.out.print("雇佣状态(未雇佣、雇佣中、忙碌): ");
			s=Input.inputOtherType(HireState.class);
			if(s.equals(HireState.未雇佣.toString()) || s.equals(HireState.忙碌.toString())) {//如果不更改雇佣状态，则一切保持原样
				param[7]=housekeeper.getState().toString();
			}else {//否则替换为新输入的数据
				param[7]=s;
			}
			param[8]=String.valueOf(housekeeper.getHousekeeperID());
			//提示确认更改
			System.out.println("确认更改？（y/n）");
			if("y".equals(input.next())){
				int result=adminMenuService.updateHousekeeper(param);//执行更改操作
				if(result>0) {
					System.out.println("更改成功！");
					housekeeper=adminMenuService.getHousekeeper_by_ID(id);
					System.out.println("\nID="+id+"的家政人员更改后的信息为: ");
					PrintInfo.printSingleHousekeeper(housekeeper);
				}else {
					System.out.println("更改失败");
				}
			}
		}else {//查询失败
			System.out.println("该家政人员不存在！请确认ID是否正确");
		}
	}
	/** 
	 * @Title: QueryAllHousekeeper 
	 * @Description: 查询所有的家政人员信息
	 * @param 
	 * @return void
	 * @throws 
	*/
	private static void QueryAllHousekeeper() {
		System.out.println("=============----查询所有家政人员信息----=============");
		List<Housekeeper> housekeeperList=new AdminMenuServiceImpl().getAllHousekeeper();
		System.out.println("\n所有家政人员信息如下:");
		PrintInfo.printHousekeeperList(housekeeperList);
	}
	/** 
	 * @Title: QueryHousekeeperByService 
	 * @Description: 查询某类服务的所有家政人员//对家政人员的查询可以继续细化为查询具体服务类型具体的时间段，提示：是否进行时间段筛选
	 * @param 
	 * @return void
	 * @throws 
	*/
	private static void QueryHousekeeperByService() {
		System.out.println("============---查询特定服务家政人员信息---============");
		System.out.println("请输入您所要查询的服务类别(保健/维修/保姆/看护/保洁): ");
		String service=Input.inputOtherType(Service.class);
		//执行查询
		AdminMenuService adminMenuService=new AdminMenuServiceImpl();
		List<Housekeeper> housekeeperList=adminMenuService.getHousekeeper_by_service(service);
		if(housekeeperList!=null) {//查询成功
			//打印具体信息
			System.out.println("\n类别为"+service+"的家政人员信息如下:");
			PrintInfo.printHousekeeperList(housekeeperList);
			//提示时间段筛选
			System.out.println("是否进行时间段筛选?   进行筛选请输入y，结束查询请按任意键");
			String code=input.next();
			if(code.equals("y")){
				//进行时间段筛选
				System.out.println("请输入时间段(格式: hh:mm:ss):");
				System.out.print("起始时间: ");
				String startTime=Input.inputOtherType(Time.class);
				System.out.print("结束时间: ");
				String endTime=Input.inputOtherType(Time.class);
				//执行时间段查询
				housekeeperList=adminMenuService.getHousekeeper_by_time(service, startTime, endTime);
				if(housekeeperList!=null) {//查询成功
					if(housekeeperList.size()>0) {//存在符合条件的家政人员
						System.out.println("\n类别为: "+service+",工作时间段为: "+startTime+"~"+endTime+"的家政人员信息如下:");
						PrintInfo.printHousekeeperList(housekeeperList);
					}else {
						System.out.println("暂无符合该时间段的家政人员");
					}
				}else {
					System.out.println("查询失败");
				}
			}
		}else {//查询失败
			System.out.println("查询失败");
		}
		return;
	}
	/** 
	 * @Title: QueryHousekeeperByID 
	 * @Description: 查询具体ID的家政人员
	 * @param 
	 * @return void
	 * @throws 
	*/
	private static void QueryHousekeeperByID() {
		System.out.println("============---查询特定编号家政人员信息---============");
		System.out.println("请输入您所要查询的家政人员编号: ");
		int id=Integer.parseInt(Input.inputNum(Integer.class));
		//进行查询
		AdminMenuService adminMenuService=new AdminMenuServiceImpl();
		Housekeeper housekeeper=adminMenuService.getHousekeeper_by_ID(id);
		if(housekeeper!=null) {//查询成功
			//打印其具体信息
			System.out.println("查询成功");
			System.out.println("\nID="+id+"的家政人员信息为: ");
			PrintInfo.printSingleHousekeeper(housekeeper);
			//提示是否查看历史评价
			Input.PromptAndChoose("是否需要查看其历史评价?     若是请输入y", "y", ()->{
				List<HistoricalEvaluation> historyList=adminMenuService.getHistoricalEvaluation(id);
				if(historyList!=null&&historyList.size()>0) {
					PrintInfo.printHistoricalEvaluation(historyList);
				}else {
					System.out.println("该家政人员没有历史评价");
				}
			});
		}else {//查询失败
			System.out.println("该家政人员不存在！请确认ID是否正确");
		}
		return;
	}
	/** 
	 * @Title: HousekeeperRegistrationReview 
	 * @Description: 家政人员注册审核
	 * @param 
	 * @return void
	 * @throws 
	*/
	private void HousekeeperRegistrationReview() {
		System.out.println("==============-----家政人员注册审核-----==============");
		AdminMenuService adminMenuService=new AdminMenuServiceImpl();
		boolean flag=true;
		do {
			List<ApplyForHousekeeper> applyList=adminMenuService.getAllApply();
			if(applyList.size()<1) {
				System.out.println("暂无待审核的家政人员");
				return;
			}
			System.out.println("\n所有待审核家政人员信息如下:");
			PrintInfo.printApplyForHousekeeperList(applyList);
			System.out.println("请输入您所要审核的家政人员编号: ");
			int id=Integer.parseInt(Input.inputNum(Integer.class));
			//执行查询
			ApplyForHousekeeper applyForHousekeeper=adminMenuService.getApplyForHousekeeper(id);
			if(applyForHousekeeper!=null) {//查询成功
				System.out.println("\nID="+id+"的家政人员信息为: ");
				PrintInfo.printSingleApplyForHousekeeper(applyForHousekeeper);
				//提示受否通过审核
				Input.PromptAndChoose("是否通过其审核?   (y/n)","y",()->{adminMenuService.passAudits(id);},()->{adminMenuService.failAudits(id);});
				System.out.println("审核通过\n\n");
			}else {//查询失败
				System.out.println("该家政人员不存在！请确认ID是否正确");
			}
			flag=Input.PromptAndChoose("是否继续审核其他人员?   (y/n)", "y",()->{return true;},()->{return false;});
		} while (flag);
		return;
	}
}
