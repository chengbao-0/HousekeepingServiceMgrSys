/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：工具类
 * @Package: utils 
 * @author: chengbao_0  
 * @date: 2020-7-29 18:48:10 
 */
package utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import entity.Admin;
import entity.ApplyForHousekeeper;
import entity.Client;
import entity.HistoricalEvaluation;
import entity.Housekeeper;
import entity.Service;
import entity.ServiceRecord;

/**
 * @ClassName Print
 * @Desc 对不同表单数据的格式化输出，实现输出格式的统一
 * 		只涉及输出，不涉及任何逻辑与数据访问
 * @author chengbao_0
 * @Date 2020-7-20 10:25:26
 */
public class PrintInfo {
	//实现单例模式
	private static final PrintInfo instance=new PrintInfo();
	private PrintInfo() {}//private 避免类在外部被实例化
	public static PrintInfo getInstance() {
		return instance;
	}
	/**
	 * @Title: printSingleClient 
	 * @Description: 打印输出单个会员的信息
	 * @param @param client 待打印的会员对象
	 * @return void
	 * @throws 
	 */
	public static void printSingleClient(Client client) {
		System.out.println("------------------------------------------------------");
		System.out.println("用户名: "+client.getUser());
		System.out.println("会员编号: "+client.getClientID());
		System.out.println("姓名: "+client.getName());
		System.out.println("性别: "+client.getSex());
		System.out.println("联系电话: "+client.getPhone());
		System.out.println("家庭住址: "+client.getAddress());
		System.out.println("付费状态: "+client.getPaidState());
		System.out.println("------------------------------------------------------");
		System.out.print("\n");
	}
	/**
	 * @Title: printSingleClient 
	 * @Description: 打印输出单个会员的信息
	 * @param @param client 待打印的会员对象
	 * @return void
	 * @throws 
	 */
	public static void printSingleClient_by_Housekeeper(Client client) {
		System.out.println("------------------------------------------------------");
		System.out.println("会员编号: "+client.getClientID());
		System.out.println("姓名: "+client.getName());
		System.out.println("性别: "+client.getSex());
		System.out.println("联系电话: "+client.getPhone());
		System.out.println("家庭住址: "+client.getAddress());
		System.out.println("付费状态: "+client.getPaidState());
		System.out.println("------------------------------------------------------");
		System.out.print("\n");
	}
	/**
	 * @Title: printClientList 
	 * @Description: 打印输出会员列表
	 * @param @param clientList 待打印的会员List对象
	 * @return void
	 * @throws 
	 */
	public static void printClientList(List<Client> clientList) {
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.println("用户名\t\t" +"会员编号\t"+ "姓名\t"+"性别\t"+"联系电话\t\t"+"付费状态\t"+"家庭住址\t\t\t");
		System.out.println("-----------------------------------------------------------------------------------------------");
		for (int i = 0; i < clientList.size(); i++) {
			Client c=clientList.get(i);
			System.out.println(c.getUser()+"\t"+c.getClientID()+"\t"+c.getName()+"\t"+c.getSex()+"\t"+c.getPhone()+(c.getPhone()==null?"\t\t":"\t")+c.getPaidState()+"\t"+c.getAddress()+"\t");
		}
		System.out.println("-----------------------------------------------------------------------------------------------");
		System.out.print("\n");
	}
	/**
	 * @Title: printSingleHousekeeper 
	 * @Description: 打印输出单个家政人员的信息
	 * @param @param housekeeper 待打印的家政人员对象
	 * @return void
	 * @throws 
	 */
	public static void printSingleHousekeeper(Housekeeper housekeeper) {
		System.out.println("------------------------------------------------------");
		System.out.println("用户名: "+housekeeper.getUser());
		System.out.println("家政人员编号: "+housekeeper.getHousekeeperID());
		System.out.println("姓名: "+housekeeper.getName());
		System.out.println("性别: "+housekeeper.getSex());
		System.out.println("服务类别: "+housekeeper.getService());
		System.out.println("联系电话: "+housekeeper.getPhone());
		System.out.println("历史评分: "+housekeeper.getAvgScore());
		System.out.println("工作开始时间: "+housekeeper.getStartTime());
		System.out.println("工作结束时间: "+housekeeper.getEndTime());
		System.out.println("雇佣状态: "+housekeeper.getState());
		System.out.println("雇主编号: "+housekeeper.getClientID());
		System.out.println("------------------------------------------------------");
		System.out.print("\n");
	}
	/**
	 * @Title: printSingleHousekeeper 
	 * @Description: 打印输出单个家政人员的信息
	 * @param @param housekeeper 待打印的家政人员对象
	 * @return void
	 * @throws 
	 */
	public static void printSingleHousekeeper_by_Client(Housekeeper housekeeper) {
		System.out.println("------------------------------------------------------");
		System.out.println("家政人员编号: "+housekeeper.getHousekeeperID());
		System.out.println("姓名: "+housekeeper.getName());
		System.out.println("性别: "+housekeeper.getSex());
		System.out.println("服务类别: "+housekeeper.getService());
		System.out.println("联系电话: "+housekeeper.getPhone());
		System.out.println("历史评分: "+housekeeper.getAvgScore());
		System.out.println("工作开始时间: "+housekeeper.getStartTime());
		System.out.println("工作结束时间: "+housekeeper.getEndTime());
		System.out.println("雇佣状态: "+housekeeper.getState());
		System.out.println("雇主编号: "+housekeeper.getClientID());
		System.out.println("------------------------------------------------------");
		System.out.print("\n");
	}
	/**
	 * @Title: printHousekeeperList 
	 * @Description: 打印输出家政人员列表
	 * @param @param housekeeperList 待打印的家政人员List对象
	 * @return void
	 * @throws 
	 */
	public static void printHousekeeperList(List<Housekeeper> housekeeperList) {
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("用户名\t\t"+"家政人员编号\t" + "姓名\t"+"性别\t"+"服务类别\t"+"联系电话\t\t"+"历史评分\t"+"开始时间\t\t"+"结束时间\t\t"+"状态\t"+"雇主编号\t");
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
		for (int i = 0; i < housekeeperList.size(); i++) {
			Housekeeper h=housekeeperList.get(i);
			System.out.println(h.getUser()+"\t"+h.getHousekeeperID()+"\t\t"+h.getName()+"\t"+h.getSex()+"\t"+h.getService()+"\t"+h.getPhone()+(h.getPhone()==null?"\t\t":"\t")
								+h.getAvgScore()+"\t"+h.getStartTime()+"\t"+h.getEndTime()+"\t"+h.getState()+"\t"+h.getClientID()+"\t");
		}
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
		System.out.print("\n");
		return;
	}
	/**
	 * @Title: printHousekeeperList 
	 * @Description: 打印输出家政人员列表
	 * @param @param housekeeperList 待打印的家政人员List对象
	 * @return void
	 * @throws 
	 */
	public static void printHousekeeperList_by_Client(List<Housekeeper> housekeeperList) {
		System.out.println("----------------------------------------------------------------------------------------------------------------");
		System.out.println("家政人员编号\t" + "姓名\t"+"性别\t"+"服务类别\t"+"联系电话\t\t"+"历史评分\t"+"开始时间\t\t"+"结束时间\t\t"+"状态\t"+"雇主编号\t");
		System.out.println("----------------------------------------------------------------------------------------------------------------");
		for (int i = 0; i < housekeeperList.size(); i++) {
			Housekeeper h=housekeeperList.get(i);
			System.out.println(h.getHousekeeperID()+"\t\t"+h.getName()+"\t"+h.getSex()+"\t"+h.getService()+"\t"+h.getPhone()+(h.getPhone()==null?"\t\t":"\t")
								+h.getAvgScore()+"\t"+h.getStartTime()+"\t"+h.getEndTime()+"\t"+h.getState()+"\t"+h.getClientID()+"\t");
		}
		System.out.println("----------------------------------------------------------------------------------------------------------------");
		System.out.print("\n");
		return;
	}
	/** 
	 * @Title: printSingleServiceRecord 
	 * @Description: 打印输出单个服务记录表单
	 * @param @param serviceRecord 待打印的表单对象
	 * @return void
	 * @throws 
	*/
	public static void printSingleServiceRecord(ServiceRecord serviceRecord) {
		System.out.println("------------------------------------------------------");
		System.out.println("表单编号: "+serviceRecord.getFormID());
		System.out.println("服务类型: "+serviceRecord.getService());
		System.out.println("会员编号: "+serviceRecord.getClientID());
		System.out.println("家政人员编号: "+serviceRecord.getHousekeeperID());
		System.out.println("雇佣日期: "+serviceRecord.getEmployDate());
		System.out.println("开始工作时间: "+serviceRecord.getStartEmployTime());
		System.out.println("结束工作时间: "+serviceRecord.getEndEmployTime());
		System.out.println("雇佣天数: "+serviceRecord.getEmployDays());
		System.out.println("薪酬总计: "+serviceRecord.getTotalCompensation());
		System.out.println("表单状态: "+serviceRecord.getFormState());
		System.out.println("客户评分: "+serviceRecord.getClientScore());
		System.out.println("客户评价: ");
		String str=serviceRecord.getClientEvaluate();
		printEvaluation(str.equals("")?"无":str);
		System.out.println("------------------------------------------------------");
		System.out.print("\n");
	}
	/**
	 * @Title: printServiceRecordList 
	 * @Description: 打印输出服务记录表单列表
	 * @param @param serviceRecordList 待打印的服务记录List对象
	 * @return void
	 * @throws 
	 */
	public static void printServiceRecordList(List<ServiceRecord> serviceRecordList) {
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("表达编号\t\t\t" + "服务类别\t" +"会员编号\t"+ "家政人员ID\t"+"雇佣日期\t\t"+"工作开始时间\t"+"工作结束时间\t"+"雇佣天数\t"+"总计薪酬\t"+"表单状态\t"+"评分\t"+"\t");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
		for (int i = 0; i < serviceRecordList.size(); i++) {
			ServiceRecord s=serviceRecordList.get(i);
			System.out.println(s.getFormID()+"\t"+s.getService()+"\t"+s.getClientID()+"\t"+s.getHousekeeperID()
					+"\t\t"+s.getEmployDate()+"\t"+s.getStartEmployTime()+"\t"+s.getEndEmployTime()+"\t"+
					s.getEmployDays()+"\t"+s.getTotalCompensation()+"\t"+s.getFormState()+"\t"+s.getClientScore()
					+"\t");
		}
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
		System.out.print("\n");
	}
	/**
	 * @Title: printSingleAdmin 
	 * @Description: 打印输出单个管理员的信息
	 * @param admin 待打印的管理员对象
	 * @return void
	 * @throws 
	 */
	public static void printSingleAdmin(Admin admin) {
		System.out.println("------------------------------------------------------");
		System.out.println("用户名: "+admin.getUser());
		System.out.println("管理员编号: "+admin.getAdminID());
		System.out.println("------------------------------------------------------");
		System.out.print("\n");
	}
	/**
	 * @Title: printAdminList 
	 * @Description: 打印输出管理员列表
	 * @param @param adminList 待打印的管理员List对象
	 * @return void
	 * @throws 
	 */
	public static void printAdminList(List<Admin> adminList) {
		System.out.println("------------------------------------------------------");
	    System.out.println("用户名\t"  +"管理员编号\t");
	    System.out.println("------------------------------------------------------");
	    for (int i=0;i<adminList.size();i++) {
	    	Admin a=adminList.get(i);
	    	System.out.println(a.getUser()+"\t"+a.getAdminID()+"\t");
	    }
	    System.out.println("------------------------------------------------------");
	    System.out.print("\n");
	}
	/**
	 * @Title: printSingleApplyForHousekeeper 
	 * @Description: 打印单个待审核家政人员的信息
	 * @param @param applyForHousekeeper 待打印的待审核家政人员对象
	 * @return void
	 * @throws 
	 */
	public static void printSingleApplyForHousekeeper(ApplyForHousekeeper applyForHousekeeper) {
		System.out.println("------------------------------------------------------");
		System.out.println("编号: "+applyForHousekeeper.getID());
		System.out.println("姓名: "+applyForHousekeeper.getName());
		System.out.println("性别: "+applyForHousekeeper.getSex());
		System.out.println("服务类别: "+applyForHousekeeper.getService());
		System.out.println("工作开始时间: "+applyForHousekeeper.getStartTime());
		System.out.println("工作结束时间: "+applyForHousekeeper.getEndTime());
		System.out.println("------------------------------------------------------");
		System.out.print("\n");
	}
	/**
	 * @Title: printApplyForHousekeeperList 
	 * @Description: 打印输出待审核家政人员列表
	 * @param @param applyList 待打印的List对象
	 * @return void
	 * @throws 
	 */
	public static void printApplyForHousekeeperList(List<ApplyForHousekeeper> applyList) {
		System.out.println("------------------------------------------------------------------");
		System.out.println("编号\t" + "姓名\t"+"性别\t"+"服务类型\t"+"开始时间\t"+"结束时间\t");
		System.out.println("------------------------------------------------------------------");
		for (int i = 0; i < applyList.size(); i++) {
			ApplyForHousekeeper h=applyList.get(i);
			System.out.println(h.getID()+"\t"+h.getName()+"\t"+h.getSex()+"\t"+h.getService()+"\t"+h.getStartTime()
								+"\t"+h.getEndTime()+"\t");
		}
		System.out.println("------------------------------------------------------------------");
		System.out.print("\n");
		return;
	}
	/**
	 * 打印输出历史历史评价信息
	 */
	public static void printHistoricalEvaluation(List<HistoricalEvaluation> historyList) {
		System.out.println("------------------------------------------------------");

		for(HistoricalEvaluation h:historyList) {
			System.out.println("------------------------------------------/");
			System.out.println("会员编号: "+h.getClientID()+"\n评分: "+h.getScore()+"\n评价内容: ");
			String str=h.getEvaluation();
			printEvaluation(str.equals("")?"无":str);
		}
		System.out.println("------------------------------------------------------");
		System.out.print("\n");
		return;
	}
	/**
	 * 只打印历史评价，通过subString方法
	 */
	public static void printEvaluation(String str) {
		final int lineMaxWords=25;
		int lines=str.length()/lineMaxWords+1;
		System.out.print("\t");//首行缩进
		if(str.length()<=(lineMaxWords-4)) {//评论只有一行，评论字数小于最大字数-2
			System.out.println(str);
			return;
		}else if(lines==4) {//评论有两行
			System.out.println(str.substring(0,lineMaxWords-4));
			System.out.println(str.substring(lineMaxWords-4,str.length()));
			return;
		}
		//评论有多行
		//打印第一行
		System.out.println(str.substring(0,lineMaxWords-4));
		//打印中间行
		int i=0;
		for(i=0;i<lines-4;i++) {
			int begin=(lineMaxWords-4)+i*lineMaxWords;
			int end=(lineMaxWords-4)+(i+1)*lineMaxWords;
			System.out.println(str.substring(begin,end));
		}
		//打印最后一行
		System.out.println(str.substring((lineMaxWords-4)+i*lineMaxWords,str.length()));
	}
	/**
	 * 打印输出传入的单条历史评价信息
	 */
	public static void printSingleEvaluation(ServiceRecord serviceRecord) {
		System.out.println("------------------------------------------------------");
		System.out.println("会员编号:  " +serviceRecord.getClientID());
		System.out.println("评分:  "+serviceRecord.getClientScore());
		System.out.println("评价内容:");
		String str=serviceRecord.getClientEvaluate();
		printEvaluation(str.equals("")?"无":str);
		System.out.println("------------------------------------------------------");
	}
	/**
	 * 打印输出计费规则
	 */
	public static void printFeesRules(List<Service> serviceList) {
		System.out.println("------------------------------------------------------");
	    System.out.println("服务编号\t" + "服务类别\t" +"时薪\t");
	    System.out.println("------------------------------------------------------");
	    for (int i=0;i<serviceList.size();i++) {
	    	Service s=serviceList.get(i);
	    	System.out.println(s.getServiceID()+"\t"+s.getService()+"\t"+s.getHourlyWage()+"\t");
	    }
	    System.out.println("------------------------------------------------------");
	    System.out.print("\n");
	}
	/**
	 * 打印输出不同服务类型的家政人员统计信息
	 */
	public static void printDiffServiceTotalHousekeeper(Map<String,String> map) {
		Set<String> set=map.keySet();
		Iterator <String> key=set.iterator();
		Collection <String> coll=map.values();
		Iterator<String> value=coll.iterator();
		//剔除掉不必要的信息
		key.next();
		key.next();
		key.next();
		System.out.print(value.next()+"\t"+value.next()+"\t"+value.next()+"\t\t");
		while(key.hasNext()&&value.hasNext()) {
			System.out.print(key.next()+"\t"+value.next()+"\t\t");
		}
		System.out.print("\n");
	}
}
