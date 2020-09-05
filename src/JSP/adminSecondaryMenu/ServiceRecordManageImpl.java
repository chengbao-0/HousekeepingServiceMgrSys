/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：表示层，管理员二级菜单，显示菜单同时与用户进行交互
 * @Package: JSP.adminSecondaryMenu
 * @author: chengbao_0  
 * @date: 2020-7-29 18:48:10 
 */
package JSP.adminSecondaryMenu;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

import entity.Admin;
import entity.Service;
import entity.ServiceRecord;
import service.factory.impl.ServiceRecordFactoryImpl;
import service.menu.AdminMenuService;
import service.menu.impl.AdminMenuServiceImpl;
import utils.Clear;
import utils.Input;
import utils.PrintInfo;
import utils.PrintMenu;
import utils.enumeration.FormState;

/**
 * @ClassName ServiceRecordManageImpl
 * @Desc 服务记录管理次级菜单
 * @author chengbao_0
 * @Date 2020-8-1 11:27:03
 */
public class ServiceRecordManageImpl implements ReturnToAdminMainMenu {
	private static Scanner input = new Scanner(System.in);
	//实现单例模式
	private static final ServiceRecordManageImpl instance=new ServiceRecordManageImpl();
	private static Admin admin=null;
	private ServiceRecordManageImpl() {}//private 避免类在外部被实例化
	public static ServiceRecordManageImpl getInstance() {
		return instance;
	}
	/**
	 * 服务记录管理次级菜单
	 */
	public void ServiceRecordManage(Admin a) {
		//将管理员角色信息存放到本地管理员
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
				PrintMenu.printServiceRecordManageMenu();//打印菜单
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
					NewServiceRecord();//新增表单信息
					type = !ReturnToAdminMainMenu.IsReturn();
					break;
				case 2:
					DeleteServiceRecord();//删除表单信息
					type = !ReturnToAdminMainMenu.IsReturn();
					break;
				case 3:
					DeleteEvaluation();//删除特定编号的评论信息
					type = !ReturnToAdminMainMenu.IsReturn();
					break;
				case 4:
					UpdateServiceRecord();//更改表单信息
					type = !ReturnToAdminMainMenu.IsReturn();
					break;
				case 5:
					QueryAllServiceRecord();//查询所有的表单信息
					type = !ReturnToAdminMainMenu.IsReturn();
					break;
				case 6:
					QueryServiceRecordByID();//查询特定编号的表单信息
					type = !ReturnToAdminMainMenu.IsReturn();
					break;
				case 7:
					QueryServiceRecordByService();//查询特定服务类别的表单信息
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
	 * @Title: NewServiceRecord 
	 * @Description: 新增表单信息
	 * @param 
	 * @return void
	 * @throws 
	*/
	private static void NewServiceRecord() {//考虑是否需要同时更改相应会员和家政人员的信息，同时考虑更改的可行性
		System.out.println("================-----新增表单信息-----================");
		String param[]=new String[7];
		System.out.print("请输入服务类型(保健、维修、保姆、看护、保洁): ");
		param[0]=Input.inputOtherType(Service.class);
		System.out.print("请输入会员编号: ");
		param[1]=Input.inputNum(Integer.class);
		System.out.print("请输入家政人员编号: ");
		param[2]=Input.inputNum(Integer.class);
		System.out.print("请输入雇佣日期(yy-mm-dd): ");
		param[3]=Input.inputOtherType(Date.class);
		System.out.print("请输入开始工作时间(hh:mm:ss)：");
		param[4]=Input.inputOtherType(Time.class);
		System.out.print("请输入结束工作时间(hh:mm:ss)：");
		param[5]=Input.inputOtherType(Time.class);
		System.out.print("请输入雇佣天数: ");//总计薪酬是自动算的，可以尝试放进Utils类中
		param[6]=Input.inputNum(Integer.class);
		//执行新增操作
		ServiceRecord serviceRecord=new ServiceRecordFactoryImpl().NewServiceRecord(param);
		if(serviceRecord!=null) {
			System.out.println("新增成功！\n\n");
			System.out.println("\n服务记录表单基本信息为：");
			PrintInfo.printSingleServiceRecord(serviceRecord);
		}else {
			System.out.println("新增失败!");
		}
		return;
	}
	/** 
	 * @Title: DeleteServiceRecord 
	 * @Description: 删除表单信息
	 * @param 
	 * @return void
	 * @throws 
	*/
	private static void DeleteServiceRecord() {
		System.out.println("================-----删除表单信息-----================");
		System.out.println("请输入您所要删除的表单ID: ");
		long id=Long.parseLong(Input.inputNum(Long.class));
		//进行查询
		AdminMenuService adminMenuService=new AdminMenuServiceImpl();
		List<ServiceRecord> serviceRecordList=adminMenuService.getServiceRecord_by_ID(id,0,0);
		if(serviceRecordList==null) {//查询失败
			System.out.println("该表单不存在！请确认ID是否正确");
			return;
		}
		ServiceRecord serviceRecord=serviceRecordList.get(0);
		if(serviceRecord!=null) {//查询成功
			//打印表单信息
			System.out.println("\nID="+id+"的表单信息为: ");
			PrintInfo.printSingleServiceRecord(serviceRecord);
			//提示确认删除
			System.out.println("确认删除？（y/n）");
			if("y".equals(input.next())){
				int result=adminMenuService.deleteServiceRecord(id);//执行删除操作
				if(result>0) {//删除成功
					System.out.println("已删除ID="+id+"的表单");
				}else {
					System.out.println("删除失败");
				}
			}
		}else {//查询失败
			System.out.println("该表单不存在！请确认ID是否正确");
		}
	}
	/** 
	 * @Title: DeleteEvaluation 
	 * @Description: 删除评论信息
	 * @param 
	 * @return void
	 * @throws 
	*/
	private void DeleteEvaluation() {
		System.out.println("================-----删除评论信息-----================");
		System.out.println("请输入您所要删除评论的表单ID: ");
		long id=Long.parseLong(Input.inputNum(Long.class));
		//进行查询
		AdminMenuService adminMenuService=new AdminMenuServiceImpl();
		List<ServiceRecord> serviceRecordList=adminMenuService.getServiceRecord_by_ID(id,0,0);
		if(serviceRecordList==null) {//查询失败
			System.out.println("该表单不存在！请确认ID是否正确");
			return;
		}
		ServiceRecord serviceRecord=serviceRecordList.get(0);
		//查询成功
		if(serviceRecord.getClientEvaluate()!=null&&serviceRecord.getClientEvaluate().length()>0&&!serviceRecord.getClientEvaluate().equals("")) {//有评论信息
			//打印评论详情
			System.out.println("\n评论详情为: ");
			PrintInfo.printSingleEvaluation(serviceRecord);
			//提示确认删除
			System.out.println("确认删除？（y/n）");
			if("y".equals(input.next())){
				int result=adminMenuService.deleteServiceRecordEvaluation(id);//执行删除操作
				if(result>0) {//删除成功
					System.out.println("已删除ID="+id+"的表单的评论");
				}else {
					System.out.println("删除失败");
				}
			}
		}else {//无评论信息
			System.out.println("该表单暂无评论信息");
		}
	}
	/** 
	 * @Title: UpdateServiceRecord 
	 * @Description: 修改表单信息
	 * @param 
	 * @return void
	 * @throws 
	*/
	private static void UpdateServiceRecord() {
		System.out.println("================-----修改表单信息-----================");
		System.out.println("请输入您所要更改信息的表单 ID: ");
		long id=Long.parseLong(Input.inputNum(Long.class));
		//执行查询
		AdminMenuService adminMenuService=new AdminMenuServiceImpl();
		List<ServiceRecord> serviceRecordList=adminMenuService.getServiceRecord_by_ID(id,0,0);
		if(serviceRecordList==null||serviceRecordList.size()<=0) {//查询失败
			System.out.println("该表单不存在！请确认ID是否正确");
			return;
		}
		//查询成功
		ServiceRecord serviceRecord=serviceRecordList.get(0);
		//打印表单信息
		System.out.println("\nID="+id+"的表单当前信息为: ");
		PrintInfo.printSingleServiceRecord(serviceRecord);
		//进行信息更改
		System.out.println("开始进行信息更改: ");
		String param[]=new String[9];
		System.out.println("注: 1.如需更改请直接输入新数据 2.表单编号、总计薪酬、客户评分、客户评价不可更改");//如果可以更改需要进行重复性验证
		System.out.println("服务类型(保健、维修、保姆、看护、保洁): ");
		String s=Input.inputOtherType(Service.class);
		param[0]="n".equals(s)?serviceRecord.getService().toString():s;
		System.out.print("会员编号: ");
		s=Input.inputNum(Integer.class);
		param[1]="n".equals(s)?String.valueOf(serviceRecord.getClientID()):s;
		System.out.print("家政人员编号: ");
		s=Input.inputNum(Integer.class);
		param[2]="n".equals(s)?String.valueOf(serviceRecord.getHousekeeperID()):s;
		System.out.print("雇佣日期(yy-mm-dd): ");
		s=Input.inputOtherType(Date.class);
		param[3]="n".equals(s)?serviceRecord.getEmployDate().toString():s;
		System.out.print("开始工作时间(hh:mm:ss)：");
		s=Input.inputOtherType(Time.class);
		param[4]="n".equals(s)?serviceRecord.getStartEmployTime().toString():s;
		System.out.print("结束工作时间(hh:mm:ss)：");
		s=Input.inputOtherType(Time.class);
		param[5]="n".equals(s)?serviceRecord.getEndEmployTime().toString():s;
		System.out.print("雇佣天数: ");//总计薪酬是自动算的，可以尝试放进Utils类中
		s=Input.inputNum(Integer.class);
		param[6]="n".equals(s)?String.valueOf(serviceRecord.getEmployDays()):s;
		System.out.print("表单状态(进行中/未付费/已付费/已结算): ");
		s=Input.inputOtherType(FormState.class);
		param[7]="n".equals(s)?serviceRecord.getFormState().toString():s;
		param[8]=String.valueOf(serviceRecord.getFormID());
		//提示确认更改
		System.out.println("确认更改？（y/n）");
		if("y".equals(input.next())){
			int result=adminMenuService.updateServiceRecord(param);//执行更改操作
			if(result>0) {
				System.out.println("更改成功！");
				serviceRecord=adminMenuService.getServiceRecord_by_ID(id,0,0).get(0);
				System.out.println("\nID="+id+"的表单更改后的信息为: ");
				PrintInfo.printSingleServiceRecord(serviceRecord);
			}else {
				System.out.println("更改失败");
			}
		}
	}
	/** 
	 * @Title: QueryAllServiceRecord 
	 * @Description: 查询所有表单信息
	 * @param 
	 * @return void
	 * @throws 
	*/
	private static void QueryAllServiceRecord() {
		System.out.println("===============----查询所有表单信息----===============");
		System.out.println("\n所有服务记录信息如下: ");
        List<ServiceRecord> serviceRecordList=new AdminMenuServiceImpl().getAllServiceRecord();
		PrintInfo.printServiceRecordList(serviceRecordList);
		return;
	}
	/** 
	 * @Title: QueryServiceRecordByID 
	 * @Description: 根据ID查询服务记录表单
	 * @param 
	 * @return void
	 * @throws 
	*/
	private static void QueryServiceRecordByID() {
		System.out.println("=============---查询特定编号的表单信息---=============");
		System.out.println("请输入您所要查询的表单ID");
		long id=Long.parseLong(Input.inputNum(Long.class));
		//进行查询
		AdminMenuService adminMenuService=new AdminMenuServiceImpl();
		List<ServiceRecord> serviceRecordList=adminMenuService.getServiceRecord_by_ID(id,0,0);
		if(serviceRecordList==null) {
			System.out.println("该表单不存在！请确认ID是否正确");
			return;
		}
		ServiceRecord serviceRecord=serviceRecordList.get(0);
		System.out.println("查询成功");
		System.out.println("\nID="+id+"的表单信息为: ");
		PrintInfo.printSingleServiceRecord(serviceRecord);
		return;
	}
	/** 
	 * @Title: QueryServiceRecordByService 
	 * @Description: 根据服务类型查询服务记录表单
	 * @param 
	 * @return void
	 * @throws 
	*/
	private static void QueryServiceRecordByService() {
		System.out.println("=============---查询特定服务类型的表单---=============");
		System.out.println("请输入所要查询的服务类型(保健、维修、保姆、看护、保洁): ");
		//获取输入并进行查询
		List<ServiceRecord> serviceRecordList=new AdminMenuServiceImpl().getServiceRecord_by_Service(Input.inputOtherType(Service.class));
		if(serviceRecordList!=null&&serviceRecordList.size()>0) {//查询成功
			System.out.println("\n该类别的所有表单信息如下:");
			PrintInfo.printServiceRecordList(serviceRecordList);
		}else {//查询失败
			System.out.println("没有该类别的表单信息！");
		}
		return;
	}
}
