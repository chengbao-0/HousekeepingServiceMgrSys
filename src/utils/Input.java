/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：工具类
 * @Package: utils 
 * @author: chengbao_0  
 * @date: 2020-7-21 15:37:56 
 */
package utils;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import dao.impl.ServiceDaoImpl;
import entity.Service;
import service.general.UserExistVerification;

/**
 * @ClassName Input
 * @Desc 格式化输入
 * 		获取输入，对输入的不同类型数据进行合法性检查，
 * 		同时满足重新输入的需求
 * 		采用了重载，泛型和lamada表达式，异常的使用
 * @author chengbao_0
 * @Date 2020-7-21 15:37:56
 */
public class Input {
	//实现单例模式
	private static Scanner input=new Scanner(System.in);
	private static final Input instance=new Input();
	private Input() {};//private 避免类在外部被实例化
	public static Input getInstance() {
		return instance;
	}
	/**
	 * @Title: inputNum 
	 * @Description: 实现对数字类型的输入
	 * @param @param <T> 待输入的类型
	 * @param @param clazz T类型所对应的类
	 * @param @return
	 * @return String 输入成功的数字转换成的字符串
	 * @throws 
	 */
	public static <T> String inputNum(Class<T> clazz) {
		String s=null;
		boolean flag=true;
		while(flag) {
			try {
				s=input.next();//获取输入
				if(isNonNegative(s, clazz)) {//是否大于0
					flag=false;
				}else {
					System.out.println("输入应为大于零的非负数，请重新输入: ");
					flag=true;
				}
			}catch (NumberFormatException e) {//处理isNonNegative抛出的异常，输入的类型错误
				System.out.println("输入不合法，请重新输入: ");
			}finally {
				
			}
		}
		return s;
	}
	/**
	 * @Title: isNonNegative 
	 * @Description: 检测是否为某个类型的数(如果不是，则抛出异常)，同时判断是否为正数
	 * @param @param <T> 待检测的类型
	 * @param @param s 待检测数字转换成的字符串
	 * @param @param clazz T类型所对应的类
	 * @param @return
	 * @return boolean true--是正数   false--不是正数
	 * @throws NumberFormatException 数字格式异常
	 */
	private static <T> boolean isNonNegative(String s,Class<T> clazz) throws NumberFormatException{
		if(clazz.equals(Integer.class)) {//int类型
			return (Integer.parseInt(s)>0);
		}else if (clazz.equals(Double.class)) {//double类型
			return (Double.parseDouble(s)>0);
		}else if (clazz.equals(Long.class)) {//long类型
			return(Long.parseLong(s)>0);
		}else {
			System.out.println("未知类型");
			return false;
		}
	}
	/**
	 * 实现电话号码的合法输入
	 */
	public static String inputPhoneNumber() {
		String s=null;
		boolean flag=true;
		while(flag) {
			try {
				s=input.next();//获取输入
				if(Long.parseLong(s)>0 && s.length()==11) {//是否大于0,并且满足电话号码长度为11位
					flag=false;
				}else {
					System.out.println("输入应为11位的电话号码，请重新输入: ");
					flag=true;
				}
			}catch (NumberFormatException e) {//处理isNonNegative抛出的异常，输入的类型错误
				System.out.println("输入不合法，请重新输入: ");
			}finally {
				
			}
		}
		return s;
	}
	/**
	 * 更改电话号码时进行输入
	 */
	public static String inputChangePhoneNumber() {
		String s=null;
		boolean flag=true;
		while(flag) {
			try {
				s=input.next();//获取输入
				if(s.equals("n")) {
					return s;
				}
				if(Long.parseLong(s)>0 && s.length()==11) {//是否大于0,并且满足电话号码长度为11位
					flag=false;
				}else {
					System.out.println("输入应为11位的电话号码，请重新输入: ");
					flag=true;
				}
			}catch (NumberFormatException e) {//处理isNonNegative抛出的异常，输入的类型错误
				System.out.println("输入不合法，请重新输入: ");
			}finally {
				
			}
		}
		return s;
	}
	/**
	 * 实现评论的合法输入
	 */
	public static String inputEvaluation() {
		String s=null;
		boolean flag=true;
		while(flag) {		
			s=input.next();//获取输入
			if(s.length() <= 100) {//字数是否小于一百
				flag=false;
			}else {
				System.out.println("输入字数不应超过100，请重新输入: ");
				flag=true;
			}
		}
		return s;
	}
	/**
	 * @Title: inputOtherType 
	 * @Description: 实现对其他类型的输入，包括（Date，Time，Service，以及枚举类型），使用了泛型
	 * @param @param <T> 待输入的类型
	 * @param @param clazz T类型所对应的类
	 * @param @return
	 * @return String 输入成功的数据转换成的字符串
	 * @throws 
	 */
	public static <T> String inputOtherType(Class<T> clazz) {
		boolean flag=true;
		String s=null;
		while(flag) {
			s=input.next();//获取输入
			if(isInputValide(s, clazz)) {//检测输入是否合法
				flag=false;
			}else {
				System.out.println("输入不合法，请重新输入: ");
			}
		}
		return s;
	}
	/**
	 * @Title: isInputValide 
	 * @Description: 检测其他类型的输入是否合法
	 * @param @param <T> 待检测的类型
	 * @param @param s 待检测的输入
	 * @param @param clazz T类型所对应的类
	 * @param @return
	 * @return boolean true--输入合法    false--输入不合法
	 * @throws 
	 */
	private static <T> boolean isInputValide(String s,Class<T> clazz) {
		if(clazz.equals(Date.class)) {//Date类型
			return checkDate(s);
		}else if (clazz.equals(Time.class)) {//Time类型
			return checkTime(s);
		}else if (clazz.equals(Service.class)) {//Service类型
			return isServiceValide(s);
		}else if(clazz.isEnum()){//枚举类型
			return EnumUtil.validateField(s, clazz);
		}else {
			System.out.println("未知类型");
			return false;
		}
	}
	/**
	 * Date合法性检测
	 */
	private static boolean checkDate (String str) {
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");//括号内为日期格式，y代表年份，M代表年份中的月份（为避免与小时中的分钟数m冲突，此处用M），d代表月份中的天数
		try {
			sd.setLenient(false);//此处指定日期/时间解析是否不严格，在true是不严格，false时为严格
			sd.parse(str);//从给定字符串的开始解析文本，以生成一个日期
		}
		catch (Exception e) {
			return false;
		}
		return true;
	}
	/**
	 * Time合法性检测
	 */
	private static boolean checkTime (String str) {
		SimpleDateFormat sd=new SimpleDateFormat("HH:mm:ss");//括号内为日期格式，y代表年份，M代表年份中的月份（为避免与小时中的分钟数m冲突，此处用M），d代表月份中的天数
		try {
			sd.setLenient(false);//此处指定日期/时间解析是否不严格，在true是不严格，false时为严格
			sd.parse(str);//从给定字符串的开始解析文本，以生成一个日期
		}
		catch (Exception e) {
			return false;
		}
		return true;
	}
	/**
	 * Service合法性检测
	 */
	public static boolean isServiceValide(String s) {
		//从数据库中获取所有的service列表
		List<Service> serviceList=new ServiceDaoImpl().getAllService();
		//检测service是否在数据库的表中存在
		for(Service service:serviceList) {
			if(s.equals(service.getService())) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 时间段是否包含在内
	 */
	public static boolean isTimeInclude(Time start,Time startTime,Time endTime,Time end) {
		if(start.before(startTime)&&startTime.before(endTime) && endTime.before(end)) {
			return true;
		}
		return false;
	}
	/**
	 * @Title: inputUserName 
	 * @Description: 用户名输入合法性检测，主要检测注册时用户名是否已被注册
	 * @param @return
	 * @return String 输入成功的用户名
	 * @throws 
	 */
	public static String inputUserName() {
		String user=null;
		boolean flag=true;
		while(flag) {
			user=input.next();//获取输入
			if(UserExistVerification.isUserExist(user)==null) {//用户名不存在
				flag=false;
			}else {//用户名存在
				System.out.println("该用户名已被注册，请重新输入: ");
				flag=true;
			}
		}
		return user;
	}
	/**
	 * @Title: inputToChoose 
	 * @Description: 打印一句话，让用户做出选择(2个选项)，并对用户的选择做出处理(执行不同的函数)，有返回值
	 * @param @param <T> 返回值的类型
	 * @param @param message 待打印的话语
	 * @param @param str 预设的用户选择
	 * @param @param func_1 符合预设的用户选择str，则执行该函数，并返回T类型的数据
	 * @param @param func_2 不符合预设的用户选择str，则执行该函数，并返回T类型的数据
	 * @param @return
	 * @return T
	 * @throws 
	 */
	public static <T> T PromptAndChoose(String message,String str,Function<T> func_1,Function<T> func_2) {
		System.out.println(message);
        @SuppressWarnings("resource")
		String code=new Scanner(System.in).next();
        if(code.equals(str)){
        	//一个函数体
        	return func_1.func();
        }else{
			//另一个函数体
        	return func_2.func();
		}
	}
	/**
	 * 重载，打印一句话，让用户做出选择(2个选项)，并对用户的选择做出处理(执行不同的函数)，无返回值
	 */
	public static void PromptAndChoose(String message,String str,Function2 func_1,Function2 func_2) {
		System.out.println(message);
        @SuppressWarnings("resource")
		String code=new Scanner(System.in).next();
        if(code.equals(str)){
        	//一个函数体
        	func_1.func();
        }else{
			//另一个函数体
        	func_2.func();
		}
	}
	/**
	 * 重载，打印一句话，让用户做出选择(1个选项)，并对用户的选择做出处理(执行不同的函数)，无返回值
	 */
	public static void PromptAndChoose(String message,String str,Function2 func) {
		System.out.println(message);
        @SuppressWarnings("resource")
		String code=new Scanner(System.in).next();
        if(code.equals(str)){
        	//一个函数体
        	func.func();
        }
	}
	/**
	 * 函数式接口，用于有T类型返回值的lamada表达式
	 */
	@FunctionalInterface
	public interface Function<T>{
		T func();
	}
	/**
	 * 函数式接口，用于无返回值的lamada表达式
	 */
	@FunctionalInterface
	public interface Function2{
		void func();
	}
}
