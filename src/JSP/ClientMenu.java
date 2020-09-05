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
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import entity.Client;
import entity.HistoricalEvaluation;
import entity.Housekeeper;
import entity.Service;
import entity.ServiceRecord;
import service.menu.AdminMenuService;
import service.menu.ClientMenuService;
import service.menu.impl.AdminMenuServiceImpl;
import service.menu.impl.ClientMenuServiceImpl;
import utils.PrintInfo;
import utils.PrintMenu;
import utils.Clear;
import utils.Input;
import utils.enumeration.HireState;
import utils.enumeration.Sex;

/**
 * @ClassName ClientMenuImpl
 * @Desc 会员菜单类(单例模式)
 * @author chengbao_0
 * @Date 2020年7月17日 上午11:12:32
 */
public class ClientMenu{
	private static Scanner input = new Scanner(System.in);
	//实现单例模式
	private static final ClientMenu instance=new ClientMenu();
	private static Client client=null;
	private ClientMenu(){};//private 避免类在外部被实例化
	public static ClientMenu getInstance() {
		return instance;
	}
	/**
	 * 会员菜单
	 */
	public void ClientChoose(Client c) {
		//将会员角色信息存放到本地
		if(client==null) {
			client=c;
		}
		//会员菜单
		boolean type = true;
		boolean flag=true;
		while (type) {
			if(flag) {
				Clear.clear();//清屏
				Clear.delay(0.3);
				//打印菜单
				PrintMenu.printClientMainMenu();
				System.out.println("请根据需要执行的操作，选择序号输入，退出系统请输入0");
			}else {
				flag=true;
			}
			int num = input.nextInt();
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
					ClientQueryAllHousekeeper();//查看所有家政人员信息
					type = !IsClientLogOut();
					break;
				case 2:
					ClientQuarySpecificServiceHousekeeper();//查询某一服务类别有合适时间的家政人员
					type = !IsClientLogOut();
					break;
				case 3:
					ClientHireHousekeeper_by_ID();//雇佣指定ID的家政人员
					type = !IsClientLogOut();
					break;
				case 4:
					ClientEvaluate();//家政服务评分
					type = !IsClientLogOut();
					break;
				case 5:
					ClientPay();//付费
					type = !IsClientLogOut();
					break;
				case 6:
					SeeHistoryServiceRecord();//查看历史服务记录
					type = !IsClientLogOut();
					break;
				case 7:
					UpdateOwnInfo();//修改个人信息
					type = !IsClientLogOut();
					break;
				case 8:
					SeeFeesRules();//查看收费细则
					type = !IsClientLogOut();
					break;
				default:
					System.out.println("输入有误,请重新输入");
					type = true;
					break;
				}
			}
	}
	/** 
	 * @Title: ClientQueryAllHousekeeper 
	 * @Description: 会员查询所有的家政人员
	 * @param 
	 * @return void
	 * @throws 
	*/
	private static void ClientQueryAllHousekeeper() {
		System.out.println("============-----查看所有家政人员信息-----============");
		ClientMenuService clientMenuService=new ClientMenuServiceImpl();
		List<Housekeeper> housekeeperList=clientMenuService.getAllHousekeeper();
		if(housekeeperList!=null&&housekeeperList.size()>0) {//查询成功
			PrintInfo.printHousekeeperList_by_Client(housekeeperList);
		}
		//提示是否进一步筛选
		Input.PromptAndChoose("请问是否需要进一步筛选（y/n）","y",()->{ClientQuarySpecificServiceHousekeeper();});
		return;
	}
	/** 
	 * @Title: ClientQuarySpecificServiceHousekeeper 
	 * @Description: 会员查询某一服务类别有合适时间的家政人员
	 * @param 
	 * @return void
	 * @throws 
	*/
	private static void ClientQuarySpecificServiceHousekeeper() {
		System.out.println("=======--查询某一服务类别有合适时间的家政人员--=======");
		System.out.println("\n请输入您所要查询的服务类别(保健/维修/保姆/看护/保洁): ");
		String service=Input.inputOtherType(Service.class);
		//查找service的所有家政人员
		AdminMenuService adminMenuService=new AdminMenuServiceImpl();
		List<Housekeeper> housekeeperList=adminMenuService.getHousekeeper_by_service(service);
		if(housekeeperList!=null&&housekeeperList.size()>0) {//查找成功
			//打印家政人员信息
			System.out.println("\n类别为: "+service+"的家政人员信息如下:");
			PrintInfo.printHousekeeperList_by_Client(housekeeperList);
			//进行实践段筛选
			System.out.println("是否进行时间段筛选?   进行筛选请输入y，结束查询请按任意键");
			String code=input.next();
			if(code.equals("y")){
				System.out.println("请输入时间段(格式: hh:mm:ss):");
				System.out.print("起始时间: ");
				String startTime=Input.inputOtherType(Time.class);
				System.out.print("结束时间: ");
				String endTime=Input.inputOtherType(Time.class);
				//进行查询
				housekeeperList=adminMenuService.getHousekeeper_by_time(service, startTime, endTime);
				if(housekeeperList!=null&&housekeeperList.size()>0) {//查找成功
					System.out.println("\n类别为: "+service+",工作时间段为: "+startTime+"--"+endTime+"的家政人员信息如下:");
					PrintInfo.printHousekeeperList_by_Client(housekeeperList);
					Input.PromptAndChoose("\n是否需要查看具体历史评价?     y/n", "y", ()->{seeHistoricalEvaluation();});
					//雇佣或返回主界面//可以查看历史评价
					Input.PromptAndChoose("\n请问您是否开始进行雇佣操作？ y/n", "y",()->{ClientHireHousekeeper_by_ID();});
				}else {//查询失败
					System.out.println("\n暂无满足时间要求的该服务类型的家政人员");
				}
			}
		}else {//查找失败
			System.out.println("\n暂无该服务类型的家政人员");
		}
		return;
	}
	/** 
	 * @Title: seeHistoricalEvaluation 
	 * @Description: (会员)查看家政人员的历史评价
	 * @param 
	 * @return void
	 * @throws 
	*/
	private static void seeHistoricalEvaluation() {
		System.out.println("请输入您所要查看历史评价的家政人员对应的编号");
		int id=Integer.parseInt(Input.inputNum(Integer.class));
		//进行查询
		ClientMenuService clientMenuService=new ClientMenuServiceImpl();
		Housekeeper housekeeper=clientMenuService.getHousekeeper_by_ID(id);
		if(housekeeper==null) {//查询失败
			System.out.println("\n该家政人员不存在！请确认ID是否正确");
			return;
		}
		//查询成功，并打印家政人员具体信息
		List<HistoricalEvaluation> historyList=clientMenuService.getHistoricalEvaluation(id);
		if(historyList!=null&&historyList.size()>0&&!historyList.get(0).getEvaluation().equals("")) {
			System.out.println("\nID="+id+"的家政人员历史评价如下: ");
			PrintInfo.printHistoricalEvaluation(historyList);
		}else {
			System.out.println("\n该家政人员暂无历史评价");
		}
		return;
	}
	/** 
	 * @Title: ClientHireHousekeeper_by_ID 
	 * @Description: 雇佣指定ID的家政人员
	 * @param 
	 * @return void
	 * @throws 
	*/
	private static void ClientHireHousekeeper_by_ID() {
		System.out.println("===========-----雇佣指定编号的家政人员-----===========");
		System.out.println("\n请输入您所要雇佣的家政人员对应的编号");
		int id=Integer.parseInt(Input.inputNum(Integer.class));
		//开始查询
		ClientMenuService clientMenuService=new ClientMenuServiceImpl();
		Housekeeper housekeeper=clientMenuService.getHousekeeper_by_ID(id);
		if(housekeeper==null) {//查询失败
			System.out.println("\n该家政人员不存在！请确认ID是否正确");
			return;
		}
		//查询成功，判断是否已被雇佣、忙碌
		if(housekeeper.getState()==HireState.未雇佣) {//未雇佣
			//打印该家政人员的具体信息
			System.out.println("\nID="+id+"的家政人员信息为: ");
			PrintInfo.printSingleHousekeeper_by_Client(housekeeper);
			//提示是否查看历史评价
			Input.PromptAndChoose("\n是否需要查看其历史评价?     若是请输入y", "y",()->{
				List<HistoricalEvaluation> historyList=clientMenuService.getHistoricalEvaluation(id);
				if(historyList!=null&&historyList.size()>0&&!historyList.get(0).getEvaluation().equals("")) {
					PrintInfo.printHistoricalEvaluation(historyList);
				}else {
					System.out.println("\n该家政人员暂无历史评价");
				}
			});
			//提示确认雇佣
			if(!Input.PromptAndChoose("\n确认雇佣？（y/n）","y", ()->{return true;},()->{return false;})) {
				return;
			}
			//进行雇佣操作
			String param[]=new String[7];
			param[0]=housekeeper.getService();
			param[1]=String.valueOf(client.getClientID());
			param[2]=String.valueOf(id);
			param[3]=LocalDate.now().toString();
			System.out.println("请输入雇佣的时间段(格式: hh:mm:ss):");
			System.out.print("起始时间: ");
			param[4]=Input.inputOtherType(Time.class);
			System.out.print("结束时间: ");
			param[5]=Input.inputOtherType(Time.class);
			System.out.print("请输入雇佣天数: ");
			param[6]=Input.inputNum(Integer.class);
			if(!Input.isTimeInclude(housekeeper.getStartTime(), Time.valueOf(param[4]), Time.valueOf(param[5]), housekeeper.getEndTime())) {
				System.out.println("\n该家政人员在此时间段内没有时间!");
				return;
			}
			ServiceRecord serviceRecord=clientMenuService.ClientHireHousekeeper(param);//进行雇佣
			if(serviceRecord!=null) {//雇佣成功
				System.out.println("\n雇佣成功!");
				System.out.println("\n表单详情如下:");
				PrintInfo.printSingleServiceRecord(serviceRecord);
			}else {//雇佣失败
				System.out.print("\n雇佣失败!");
			}
		}else if(housekeeper.getState()==HireState.忙碌) {//忙碌
			System.out.println("\n该家政人员目前没有空闲时间，您可以选择其他家政人员");
		}else {//已雇佣
			System.out.println("\n该家政人员已被其他会员雇佣，您可以选择其他家政人员");
		}
		return;
	}
	/** 
	 * @Title: ClientEvaluate 
	 * @Description: 会员评价家政人员
	 * @param 
	 * @return void
	 * @throws 
	*/
	private static void ClientEvaluate() {
		System.out.println("================-----家政服务评分-----================");
		// 首先列出所有的历史雇佣记录，并显示具体的雇佣信息
        System.out.print("\n");
        ClientMenuService clientMenuService=new ClientMenuServiceImpl();
        List<ServiceRecord> serviceRecordList=clientMenuService.getUnEvaluableServiceRecord(client.getClientID());
        if(serviceRecordList.size()==0) {
        	System.out.println("您没有待评分表单...");
        }else {
        	System.out.println("\n您的服务记录信息如下: ");
    		PrintInfo.printServiceRecordList(serviceRecordList);
    		//只有表单显示已付费的才可以及进行评价，已评价过的无法进行更改，即只可评价一次
    		System.out.println("请选择您想评价的表单编号: \n"
            		+ "注: 只有已付费的才可以进行评分且不可重复评分");//得主动检查
    		long id=Long.parseLong(Input.inputNum(Long.class));
    		//排除不合适的表单编号
    		boolean flag=false;
    		for(ServiceRecord s:serviceRecordList) {
    			if(s.getFormID()==id) {
					flag=true;
					break;
				}
    		}
    		if(flag==false) {
    			System.out.println("\n该编号的表单不可评价!");
				return;
    		}
    		System.out.println("请输入评价内容(100字以内): ");
    		String evaluateStr=Input.inputEvaluation();
    		System.out.println("请输入评分(1-10): ");
    		double score=Double.parseDouble(Input.inputNum(Double.class));
    		boolean result=clientMenuService.evaluate(id,score,evaluateStr);
    		if(result) {
    			System.out.println("\n评价成功!");
    		}else {
    			System.out.println("\n评价失败!");
    		}
        }
	}
	/** 
	 * @Title: ClientPay 
	 * @Description: 会员付费
	 * @param 
	 * @return void
	 * @throws 
	*/
	private static void ClientPay() {
		System.out.println("====================-----付费-----====================");
		//查询所有未支付表单信息
		ClientMenuService clientMenuService=new ClientMenuServiceImpl();
		List<ServiceRecord> serviceRecordList=clientMenuService.getUnpaidServiceRecord(client.getClientID());
		if(serviceRecordList!=null) {//查询成功
			if(serviceRecordList.size()>0) {//存在未支付表单
				//选择欲支付的表达
				PrintInfo.printServiceRecordList(serviceRecordList);
				System.out.println("请输入您所要支付的表单编号: ");
				long id=Long.parseLong(Input.inputNum(Long.class));//检测id有效性
				serviceRecordList=clientMenuService.getServiceRecord_by_ID(id,0,0);
				if(serviceRecordList==null) {//查询失败
					System.out.println("该表单不存在！请确认ID是否正确");
					return;
				}
				ServiceRecord serviceRecord=serviceRecordList.get(0);
				//提示确认支付
				Input.PromptAndChoose("\n确认支付"+serviceRecord.getTotalCompensation()+"元？ y/n", "y", ()->{
					//输入y,确认支付
					if(clientMenuService.pay(serviceRecord.getFormID(),client.getClientID())) {
						System.out.println("恭喜您支付成功！");
						Input.PromptAndChoose("\n请问是否立即进行评分？ y/n", "y",()->{ClientEvaluate();});
					}else {
						System.out.println("支付失败");
					}
				},()->{//取消支付,输入其他
					System.out.println("支付失败");
				});
			}else {//无未支付表单
				System.out.println("\n您已完成所有表单的付费，尚无待付费的表单");
			}
		}else {//查询失败
			System.out.println("\n您已完成所有表单的付费，尚无待付费的表单");
		}
	}
	/** 
	 * @Title: SeeHistoryServiceRecord 
	 * @Description: 查看历史服务记录
	 * @param 
	 * @return void
	 * @throws 
	*/
	private void SeeHistoryServiceRecord() {
		System.out.println("==============-----查看历史服务记录-----==============\n");
		List<ServiceRecord> serviceRecordList=new ClientMenuServiceImpl().getServiceRecord_by_ID(client.getClientID());
		if(serviceRecordList==null||serviceRecordList.size()<=0) {
        	System.out.println("您没有任何服务记录...");
        }else {
        	System.out.println("您的历史服务记录信息如下: ");
    		PrintInfo.printServiceRecordList(serviceRecordList);
        }
	}
	/** 
	 * @Title: UpdateOwnInfo 
	 * @Description: 修改个人信息
	 * @param 
	 * @return void
	 * @throws 
	*/
	private void UpdateOwnInfo() {
		System.out.println("================-----修改个人信息-----================\n");
		//显示当前个人信息
		ClientMenuService clientMenuService=new ClientMenuServiceImpl();
		System.out.println("当前的个人信息为:");
		PrintInfo.printSingleClient(client);
		//进行信息更改操作
		System.out.println("开始进行信息更改: ");
		String param[]=new String[6];
		System.out.println("注: 1.如需更改请直接输入新数据 2.用户名、会员编号不可更改");//如果可以更改需要进行重复性验证
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
		param[5]=String.valueOf(client.getClientID());
		//提示确认更改？
		Input.PromptAndChoose("确认更改？（y/n）", "y",()->{
			//确认更改
			int result=clientMenuService.updateClient(param);
			if(result>0) {//更改成功
				System.out.println("更改成功！");
				client=clientMenuService.getClient(client.getClientID());
				System.out.println("更改后的信息为: ");
				PrintInfo.printSingleClient(client);
			}else {//更改失败
				System.out.println("更改失败");
			}
		});
	}
	/** 
	 * @Title: SeeFeesRules 
	 * @Description: 查看收费细则
	 * @param 
	 * @return void
	 * @throws 
	*/
	private void SeeFeesRules() {
		System.out.println("================-----查看收费细则-----================\n");
		List<Service> serviceList=new ClientMenuServiceImpl().getAllService();
		if(serviceList!=null) {
			System.out.println("\n当前的收费细则如下: ");
			PrintInfo.printFeesRules(serviceList);
		}else {
			System.out.println("查询失败！");
		}
	}
	/**
	 * @Title: IsClientLogOut 
	 * @Description: 会员退出界面
	 * @param @return
	 * @return boolean true--退出    false--返回主菜单
	 * @throws 
	 */
	private boolean IsClientLogOut() {
		System.out.println("\n=====================-----END-----====================\n");
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
