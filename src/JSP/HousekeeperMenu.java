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
import java.util.List;
import java.util.Scanner;

import entity.Client;
import entity.HistoricalEvaluation;
import entity.Housekeeper;
import entity.Service;
import entity.ServiceRecord;
import service.menu.HousekeeperMenuService;
import service.menu.impl.HousekeeperMenuServiceImpl;
import utils.Clear;
import utils.Input;
import utils.PrintInfo;
import utils.PrintMenu;
import utils.enumeration.HireState;
import utils.enumeration.Sex;
import utils.enumeration.WorkState;

/**
 * @ClassName HousekeeperMenuImpl
 * @Desc 家政人员菜单类(单例模式)
 * @author chengbao_0
 * @Date 2020-7-19 22:09:55
 */
public class HousekeeperMenu {
	private static Scanner input = new Scanner(System.in);
	//实现单例模式
	private static final HousekeeperMenu instance=new HousekeeperMenu();
	private static Housekeeper housekeeper=null;
	private HousekeeperMenu() {};//private 避免类在外部被实例化
	public static HousekeeperMenu getInstance() {
		return instance;
	}
	/**
	 * 家政人员主菜单
	 */
	public void HousekeeperChoose(Housekeeper h) {
		//将家政人与角色信息存放到本地
		if(housekeeper==null) {
			housekeeper=h;
		}
		//主菜单
		boolean type = true;
		boolean flag=true;
		while (type) {
			if(flag) {
				Clear.clear();//清屏
				Clear.delay(0.3);
				//打印菜单
				PrintMenu.printHousekeeperMainMenu();
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
					registerCompletedWork();//工作完成打卡
					type = !IsHousekeeperLogOut();
					break;
				case 2:
					getUnpaidForm();//查询未到账表单
					type = !IsHousekeeperLogOut();
					break;
				case 3:
					showEmployerInfo();//查看雇主信息
					type = !IsHousekeeperLogOut();
					break;
				case 4:
					setWorkState();//设置工作状态
					type = !IsHousekeeperLogOut();
					break;
				case 5:
					showOwnInfo();//查看个人信息
					type = !IsHousekeeperLogOut();
					break;
				case 6:
					updateOwnInfo();//修改个人信息
					type = !IsHousekeeperLogOut();
					break;
				case 7:
					seeHistoricalEvaluation();//查看历史评价
					type = !IsHousekeeperLogOut();
					break;
				case 8:
					seeFeesRules();//查看收费规则
					type = !IsHousekeeperLogOut();
					break;
				default:
					System.out.println("输入有误,请重新输入");
					flag=false;
					type=true;
					break;
				}
		}
	}
	/**
	 * 工作完成打卡
	 */
	private void registerCompletedWork() {
		System.out.println("================-----工作完成打卡-----================\n");
		HousekeeperMenuService housekeeperMenuService=new HousekeeperMenuServiceImpl();
		// 打印所有进行中的表单
		List<ServiceRecord> serviceRecordList=housekeeperMenuService.getFormOngoing(housekeeper.getHousekeeperID());
		if(serviceRecordList!=null) {
			if(serviceRecordList.size()>0) {
				System.out.println("未完成工作的表单信息如下:  ");
				PrintInfo.printServiceRecordList(serviceRecordList);
				//选择一个进行中的表单进行完成
				System.out.println("请输入你已完成的工作表单编号进行打卡:  ");
				long formID=Long.parseLong(Input.inputNum(Long.class));
				housekeeperMenuService.completeWork(formID);
				System.out.println("\n恭喜你已完成表单编号为"+formID+"的工作打卡！");
			}else {
				System.out.println("没有要完成的工作");
			}
		}else {
			System.out.println("没有要完成的工作");
		}
	}
	/**
	 * 查询未到账表单
	 */
	private void getUnpaidForm() {
		System.out.println("===============-----查询未到账表单-----===============\n");
		HousekeeperMenuService housekeeperMenuService=new HousekeeperMenuServiceImpl();
		List<ServiceRecord> unPaidformList=housekeeperMenuService.getUnpaidServiceRecord(housekeeper.getHousekeeperID());
		if(unPaidformList!=null) {
			if(unPaidformList.size()>0) {//查询成功
				System.out.println("未到账的表单信息如下:");
				PrintInfo.printServiceRecordList(unPaidformList);
			}else {
				System.out.println("没有未到账表单");
			}
		}else {
			System.out.println("查询失败");
		}
	}
	/**
	 * 查看雇主信息
	 */
	private void showEmployerInfo() {
		System.out.println("================-----查看雇主信息-----================\n");
		if((housekeeper.getState() == HireState.未雇佣 )||(housekeeper.getClientID() <= 0) ) {
			System.out.println("您当前没有雇主");
			return;
		}
		//获取雇主信息
		Client client=new HousekeeperMenuServiceImpl().getClient(housekeeper.getClientID());
		if(client!=null) {//获取成功
			System.out.println("雇主信息为: ");
			PrintInfo.printSingleClient_by_Housekeeper(client);
		}else {
			System.out.println("雇主信息异常");
		}
	}
	/**
	 *设置工作状态 
	 */
	private void setWorkState() {
		System.out.println("================-----设置工作状态-----================\n");
		//如果正在被雇佣，则不能进行此项操作
		if(housekeeper.getState( )== HireState.雇佣中) {
			System.out.println("当前正在被雇佣，工作状态被锁定，无法更改");
			return;
		}
		System.out.print("请输入工作状态(忙碌/空闲):  ");//空闲不进行任何操作
		String s=Input.inputOtherType(WorkState.class);
		if(s.equals(WorkState.忙碌.toString())){
			//设置工作状态为忙碌
			if(new HousekeeperMenuServiceImpl().setWorkState(housekeeper.getHousekeeperID(),HireState.忙碌.toString()) > 0) {
				housekeeper.setState(HireState.忙碌);
				System.out.println("设置成功! ");
			}else {
				System.out.println("设置失败");
			}
		}else if (s.equals(WorkState.空闲.toString())) {
			//设置工作状态为空闲
			if(new HousekeeperMenuServiceImpl().setWorkState(housekeeper.getHousekeeperID(),HireState.未雇佣.toString()) > 0) {
				housekeeper.setState(HireState.未雇佣);
				System.out.println("设置成功! ");
			}else {
				System.out.println("设置失败");
			}
		}
	}
	/**
	 * 查看个人信息
	 */
	private void showOwnInfo() {
		System.out.println("================-----查看个人信息-----================\n");
		System.out.println("当前的个人信息为:");
		housekeeper=new HousekeeperMenuServiceImpl().getHousekeeper_by_ID(housekeeper.getHousekeeperID());
		PrintInfo.printSingleHousekeeper(housekeeper);
	}
	/**
	 * 修改个人信息
	 */
	private void updateOwnInfo() {
		System.out.println("================-----修改个人信息-----================\n");
		HousekeeperMenuService housekeeperMenuService=new HousekeeperMenuServiceImpl();
		//显示个人信息
		System.out.println("当前的个人信息为:");
		housekeeper=new HousekeeperMenuServiceImpl().getHousekeeper_by_ID(housekeeper.getHousekeeperID());
		PrintInfo.printSingleHousekeeper(housekeeper);
		//进行信息更改
		System.out.println("开始进行信息更改: ");
		String param[]=new String[8];
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
		System.out.print("服务类型(保健、维修、保姆、看护、保洁): ");
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
		param[7]=String.valueOf(housekeeper.getHousekeeperID());
		//提示确认更改？
		Input.PromptAndChoose("确认更改？（y/n）","y", ()->{
			//进行更改
			int result=housekeeperMenuService.updateHousekeeper(param);
			if(result>0) {//更改成功
				System.out.println("更改成功！");
				housekeeper=housekeeperMenuService.getHousekeeper_by_ID(housekeeper.getHousekeeperID());
				System.out.println("更改后的信息为: ");
				PrintInfo.printSingleHousekeeper(housekeeper);
			}else {
				System.out.println("更改失败");
			}
		});
	}
	/**
	 * 查看历史评价
	 */
	private void seeHistoricalEvaluation() {
		System.out.println("================-----查看历史评价-----================\n");
		List<HistoricalEvaluation> historyList=new HousekeeperMenuServiceImpl().getHistoricalEvaluation(housekeeper.getHousekeeperID());
		if(historyList!=null&&historyList.size()>0&&!historyList.get(0).getEvaluation().equals("")) {//查找成功
			System.out.println("您的历史评价如下: ");
			PrintInfo.printHistoricalEvaluation(historyList);
		}else {
			System.out.println("您没有历史评价");
		}
	}
	/**
	 * 查看收费细则
	 */
	private void seeFeesRules() {
		System.out.println("================-----查看收费细则-----================\n");
		List<Service> serviceList=new HousekeeperMenuServiceImpl().getAllService();
		if(serviceList.size()>0) {//查找成功
			System.out.println("当前的收费细则如下: ");
			PrintInfo.printFeesRules(serviceList);
		}else {
			System.out.println("查询失败！");
		}
	}
	/**
	 * 家政人员选择是否退出系统
	 */
	private boolean IsHousekeeperLogOut() {
		System.out.println("\n====================-----END-----=====================\n");
		return Input.PromptAndChoose("您是否返回主菜单，若是请输入y,退出系统请按任意键", "y",()->{return false;},()->{
				return Input.PromptAndChoose("\n确认退出?       y/n","y", ()->{
				Clear.delay(0.8);
				Clear.clear();//清屏
				PrintMenu.printEndMenu();
				return true;
			},()->{
				return false;
		});});
	}
}
