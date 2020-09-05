/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：表示层，管理员二级菜单，显示菜单同时与用户进行交互
 * @Package: JSP.adminSecondaryMenu
 * @author: chengbao_0  
 * @date: 2020-7-29 18:48:10 
 */
package JSP.adminSecondaryMenu;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import entity.Admin;
import entity.Service;
import service.factory.impl.AdminFactoryImpl;
import service.menu.AdminMenuService;
import service.menu.impl.AdminMenuServiceImpl;
import utils.Clear;
import utils.DBUtil;
import utils.Input;
import utils.PrintInfo;
import utils.PrintMenu;

/**
 * @ClassName SystemManageImpl
 * @Desc 系统管理次级菜单
 * @author chengbao_0
 * @Date 2020-8-1 11:35:08
 */
public class SystemManageImpl implements ReturnToAdminMainMenu {
	private static Scanner input = new Scanner(System.in);
	//实现单例模式
	private static final SystemManageImpl instance=new SystemManageImpl();
	private static Admin admin=null;
	private SystemManageImpl() {}//private 避免类在外部被实例化
	public static SystemManageImpl getInstance() {
		return instance;
	}
	/**
	 * 系统管理次级菜单
	 */
	public void SystemManage(Admin a) {
		//将管理员信息存放到本地
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
				PrintMenu.printSystemManageMenu();//打印菜单
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
					BackupDB();//备份数据库
					type = !ReturnToAdminMainMenu.IsReturn();
					break;
				case 2:
					RecoveryDB();//恢复数据库
					type = !ReturnToAdminMainMenu.IsReturn();
					break;
				case 3:
					DeleteBackupRecord();//删除数据库备份记录
					type = !ReturnToAdminMainMenu.IsReturn();
					break;
				case 4:
					QueryAllAdmin();//查看所有管理员
					type = !ReturnToAdminMainMenu.IsReturn();
					break;
				case 5:
					DeleteAdmin();//删除管理员
					type = !ReturnToAdminMainMenu.IsReturn();
					break;
				case 6:
					NewAdmin();//新增管理员
					type = !ReturnToAdminMainMenu.IsReturn();
					break;
				case 7:
					DataStatistics();//服务数据统计
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
	 * @Title: BackupDB 
	 * @Description: 备份数据库
	 * @param 
	 * @return void
	 * @throws 
	*/
	private static void BackupDB() {
		System.out.println("=================-----备份数据库-----=================");
		DBUtil.Backup();
	}
	/** 
	 * @Title: RecoveryDB 
	 * @Description: 恢复数据库
	 * @param 
	 * @return void
	 * @throws 
	*/
	private static void RecoveryDB() {
		System.out.println("=================-----恢复数据库-----=================");
		//获取本地数据库记录列表
		System.out.println("\n本地数据库记录: ");
		List<File> files=DBUtil.getLocalBackupRecord();
		//打印备份记录
		System.out.println("序号\t备份时间");
		int i=1;
		for (File file : files) {
            System.out.println((i++)+"\t"+file.getName());
        }
		//进行恢复
		System.out.println("请输入您想恢复的备份记录序号:  ");
        System.out.println("注: 需要等待稍长一段时间");
		int num=Integer.parseInt(Input.inputNum(Integer.class));
		if(num>files.size()) {//确认输入序号的有效性
			System.out.println("该备份记录不存在！");
			return;
		}
		DBUtil.Restore(files.get(num-1).getAbsolutePath());//执行恢复操作
		Clear.delay(2);//延时
	}
	/** 
	 * @Title: DeleteBackupRecord 
	 * @Description: 删除数据库备份记录
	 * @param 
	 * @return void
	 * @throws 
	*/
	private void DeleteBackupRecord() {
		System.out.println("==============-----删除数据备份记录-----==============");
		//获取本地数据库记录列表
		System.out.println("\n本地数据库记录: ");
		List<File> files=DBUtil.getLocalBackupRecord();
		//打印备份记录
		System.out.println("序号\t备份时间");
		int i=1;
		for (File file : files) {
            System.out.println((i++)+"\t"+file.getName());
        }
		//进行删除
		System.out.println("请输入您想删除的备份记录序号:  ");
		int num=Integer.parseInt(Input.inputNum(Integer.class));
		if(num>files.size()) {//确认输入序号的有效性
			System.out.println("该备份记录不存在！");
			return;
		}
		boolean flag=DBUtil.deleteBackupRecord(files.get(num-1).getAbsolutePath());//执行删除操作
		if(flag) {
			System.out.println("已成功删除该备份记录");
		}else {
			System.out.println("删除失败");
		}
	}
	/** 
	 * @Title: QueryAllAdmin 
	 * @Description: 查询所有管理员的信息
	 * @param 
	 * @return void
	 * @throws 
	*/
	private static void QueryAllAdmin() {
		System.out.println("=============-----查询所有管理员信息-----=============");
		List<Admin> adminList=new AdminMenuServiceImpl().getAllAdmin();
	    System.out.println("\n所有管理人员信息如下:");
	    PrintInfo.printAdminList(adminList);
	}
	/** 
	 * @Title: DeleteAdmin 
	 * @Description: 删除管理员
	 * @param 
	 * @return void
	 * @throws 
	*/
	private static void DeleteAdmin() {
		System.out.println("==================-----删除管理员-----=================");
		System.out.print("请输入您所要删除的管理员ID: ");
		int id=Integer.parseInt(Input.inputNum(Integer.class));
		//进行查询
		AdminMenuService adminService=new AdminMenuServiceImpl();
		Admin admin=adminService.getAdmin(id);
		if(admin!=null) {//查询成功
			//打印具体信息
			System.out.println("\nID="+id+"的管理员信息为: ");
			PrintInfo.printSingleAdmin(admin);
			//提示确认删除
			Input.PromptAndChoose("确认删除？（y/n）", "y",()->{
				int result=adminService.deleteAdmin(id);
				if(result>0) {
					System.out.println("已删除ID="+id+"的管理员");
				}else {
					System.out.println("删除失败");
				}
			});
		}else {//查询失败
			System.out.println("该管理员不存在！请确认ID是否正确");
		}
	}
	/** 
	 * @Title: NewAdmin 
	 * @Description: 新增管理员
	 * @param 
	 * @return void
	 * @throws 
	*/
	private static void NewAdmin() {
		System.out.println("=================-----新增管理员-----=================");
		System.out.print("请输入用户名：");
		String user=Input.inputUserName();
		System.out.print("请输入密码：");
		String pwd=input.nextLine();
		System.out.print("确认密码: ");
		if(!input.nextLine().equals(pwd)) {
			System.out.println("两次密码输入不一致，新增失败!");
			return;
		}
		//执行新增操作
		Admin admin=new AdminFactoryImpl().NewAdmin(user, pwd);
		if(admin!=null) {
			System.out.println("新增成功！\n");
			System.out.println("\n管理员基本信息为：");
			PrintInfo.printSingleAdmin(admin);
		}else {
			System.out.println("新增失败!");
		}
		return;
	}
	/** 
	 * @Title: DataStatistics 
	 * @Description: 服务数据统计
	 * @param 
	 * @return void
	 * @throws 
	*/
	private void DataStatistics() {
		System.out.println("================-----服务数据统计-----================");
		AdminMenuService adminMenuService=new AdminMenuServiceImpl();
		//表单记录统计
		System.out.println("\n-----------------------------------------/表单记录统计");
		System.out.println("表单总数: "+adminMenuService.totalServiceRecord());
		System.out.println("已结算表单总数:"+adminMenuService.totalSettledRecord());
		List<Service> serviceList=adminMenuService.getAllService();
		if(serviceList.size()>0) {
			System.out.println("----------------");
			System.out.println("服务名称\t表单数目");
			System.out.println("----------------");
			for (Service service:serviceList) {
				System.out.println(service.getService()+"\t"+adminMenuService.diffServiceTotalRecord(service.getService()));
			}
			System.out.println("----------------");
		}
		//会员统计
		System.out.println("\n---------------------------------------------/会员统计");
		System.out.println("会员总数: "+adminMenuService.totalClient());
		System.out.println("未付费的会员数: "+adminMenuService.totalUnpaidClient());
		//家政人员统计
		System.out.println("\n-----------------------------------------/家政人员统计");
		System.out.println("家政人员总数: "+adminMenuService.totalHousekeeper());
		System.out.println("待审核的家政人员数目: "+adminMenuService.totalPendingHousekeeper());
		System.out.println("正在被雇佣的家政人员数目: "+adminMenuService.totalHiredHousekeeper());
		//各类服务对应的家政人员数   包括服务类型，数目，top3的id和姓名,   可以考虑用Print
		if(serviceList.size()>0) {
			System.out.println("-------------------------------------------------------------------------------------------------");
		    System.out.println("服务名称\t" + "总人数\t" +"雇佣中\t\t"+"Top1编号\t"+"Top1姓名\t\t"+"Top2编号\t"+"Top2姓名\t\t"+"Top3编号\t"+"Top3姓名\t\t");
		    System.out.println("-------------------------------------------------------------------------------------------------");
			for (Service service:serviceList) {
				PrintInfo.printDiffServiceTotalHousekeeper(adminMenuService.diffServiceTotalHousekeeper(service.getService()));
			}
			System.out.println("-------------------------------------------------------------------------------------------------");
		}
		//管理员统计
		System.out.println("\n--------------------------------------------/管理员统计");
		System.out.println("管理员总数: "+adminMenuService.totalAdmin());
		//数据库备份记录统计
		System.out.println("\n------------------------------------/数据库备份记录统计");
		System.out.println("数据库备份记录总数: "+DBUtil.totalBackupRecord());
		System.out.print("\n");
	}
}
