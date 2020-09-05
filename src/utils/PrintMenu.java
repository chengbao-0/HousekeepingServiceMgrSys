/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：工具类
 * @Package: utils 
 * @author: chengbao_0  
 * @date: 2020-8-1 13:17:08 
 */
package utils;

/**
 * @ClassName PrintMenu
 * @Desc 打印不同的菜单
 * @author chengbao_0
 * @Date 2020-8-1 13:17:08
 */
public class PrintMenu {
	/**
	 * 打印启动界面
	 */
	public static void printStartMenu() {
		System.out.println("******************************************************");
        System.out.println("*                                                    *");
        System.out.println("*              *家 政 服 务 管 理 系 统"+"*             *");
        System.out.println("*             ---------------------------            *");
        System.out.println("*                                                    *");
        System.out.println("*                                                    *");
        System.out.println("*                                   ｡:.ﾟヽ(｡◕‿◕｡)ﾉﾟ.:｡+ﾟ"+"                                       *");
        System.out.println("*                                                    *");
        System.out.println("*              *欢  迎  您  的  使  用"+"*              *");
        System.out.println("*                                                    *");
        System.out.println("******************************************************");
	}
	/**
	 * 打印会员主菜单
	 */
	public static void printClientMainMenu() {
		System.out.println("******************************************************");
        System.out.println("*                                                    *");
        System.out.println("*                 *会 员 主 菜 单"+"*                   *");
        System.out.println("*              -----------------------               *");
        System.out.println("*                                                    *");
        System.out.println("*          1.查看所有家政人员信息                    *");
        System.out.println("*          2.查询某一服务类别有合适时间的家政人员    *");
        System.out.println("*          3.雇佣指定编号的家政人员                  *");
        System.out.println("*          4.家政服务评分                            *");
        System.out.println("*          5.付费                                    *");
        System.out.println("*          6.查看历史服务记录                        *");
        System.out.println("*          7.修改个人信息                            *");
        System.out.println("*          8.查看收费细则                            *");
        System.out.println("*                                                    *");
        System.out.println("******************************************************");
	}
	/**
	 * 打印家政人员主菜单
	 */
	public static void printHousekeeperMainMenu() {
		System.out.println("******************************************************");
        System.out.println("*                                                    *");
        System.out.println("*               *家 政 人 员 主 菜 单"+"*               *");
        System.out.println("*             -------------------------              *");
        System.out.println("*                                                    *");
        System.out.println("*               1.工作完成打卡                       *");
        System.out.println("*               2.查询未到账表单                     *");
        System.out.println("*               3.查看雇主信息                       *");
        System.out.println("*               4.设置工作状态                       *");
        System.out.println("*               5.查看个人信息                       *");
        System.out.println("*               6.修改个人信息                       *");
        System.out.println("*               7.查看历史评价                       *");
        System.out.println("*               8.查看收费规则                       *");
        System.out.println("*                                                    *");
        System.out.println("******************************************************");
	}
	/**
	 * 打印管理员主菜单
	 */
	public static void printAdminMainMenu() {
		System.out.println("******************************************************");
        System.out.println("*                                                    *");
        System.out.println("*                *管 理 员 主 菜 单"+"*                 *");
        System.out.println("*              -----------------------               *");
        System.out.println("*                                                    *");
        System.out.println("*               1.会员管理                           *");
        System.out.println("*               2.家政人员管理                       *");
        System.out.println("*               3.家政服务记录管理                   *");
        System.out.println("*               4.收费细则管理                       *");
        System.out.println("*               5.系统管理                           *");
        System.out.println("*                                                    *");
        System.out.println("******************************************************");
	}
	/**
	 * 打印会员管理次级菜单
	 */
	public static void printClientManageMenu() {
		System.out.println("******************************************************");
        System.out.println("*                                                    *");
        System.out.println("*                 *会  员  管  理 "+"*                  *");
        System.out.println("*              -----------------------               *");
        System.out.println("*                                                    *");
        System.out.println("*               1.新增会员信息                       *");
        System.out.println("*               2.删除会员信息                       *");
        System.out.println("*               3.更改会员信息                       *");
        System.out.println("*               4.查询所有会员的信息                 *");
        System.out.println("*               5.查询具体编号的会员                 *");
        System.out.println("*                                                    *");
        System.out.println("******************************************************");
	}
	/**
	 * 打印收费细则管理次级菜单
	 */
	public static void printFeesManageMenu() {
		System.out.println("******************************************************");
        System.out.println("*                                                    *");
        System.out.println("*                *收 费 细 则 管 理"+"*                 *");
        System.out.println("*              -----------------------               *");
        System.out.println("*                                                    *");
        System.out.println("*                1.查看收费细则                      *");
        System.out.println("*                2.修改服务费用                      *");
        System.out.println("*                3.新增服务类别                      *");
        System.out.println("*                                                    *");
        System.out.println("******************************************************");  
	}
	/**
	 * 打印家政人员管理次级菜单
	 */
	public static void printHousekeeperManageMenu() {
		System.out.println("******************************************************");
        System.out.println("*                                                    *");
        System.out.println("*                *家 政 人 员 管 理"+"*                 *");
        System.out.println("*              -----------------------               *");
        System.out.println("*                                                    *");
        System.out.println("*             1.新增家政人员信息                     *");
        System.out.println("*             2.删除家政人员信息                     *");
        System.out.println("*             3.更改家政人员信息                     *");
        System.out.println("*             4.查询所有家政人员的信息               *");
        System.out.println("*             5.查询某类服务的所有家政人员           *");
        System.out.println("*             6.查询具体编号的家政人员               *");
        System.out.println("*             7.家政人员注册审核                     *");
        System.out.println("*                                                    *");
        System.out.println("******************************************************");
	}
	/**
	 * 打印家政服务记录管理次级菜单
	 */
	public static void printServiceRecordManageMenu() {
		System.out.println("******************************************************");
        System.out.println("*                                                    *");
        System.out.println("*                *服 务 记 录 管 理"+"*                 *");
        System.out.println("*              -----------------------               *");
        System.out.println("*                                                    *");
        System.out.println("*             1.新增表单信息                         *");
        System.out.println("*             2.删除表单信息                         *");
        System.out.println("*             3.删除特定编号的评论信息               *");
        System.out.println("*             4.更改表单信息                         *");
        System.out.println("*             5.查询所有的表单信息                   *");
        System.out.println("*             6.查询特定编号的表单信息               *");
        System.out.println("*             7.查询特定服务类别的表单信息           *");
        System.out.println("*                                                    *");
        System.out.println("******************************************************");
	}
	/**
	 * 打印系统管理次级菜单
	 */
	public static void printSystemManageMenu() {
		System.out.println("******************************************************");
        System.out.println("*                                                    *");
        System.out.println("*                 *系  统  管  理 "+"*                  *");
        System.out.println("*              -----------------------               *");
        System.out.println("*                                                    *");
        System.out.println("*                1.备份数据库                        *");
        System.out.println("*                2.恢复数据库                        *");
        System.out.println("*                3.删除数据库备份记录                *");
        System.out.println("*                4.查看所有管理员                    *");
        System.out.println("*                5.删除管理员                        *");
        System.out.println("*                6.新增管理员                        *");
        System.out.println("*                7.服务数据统计                      *");
        System.out.println("*                                                    *");
        System.out.println("******************************************************");
	}
	/**
	 * 打印启动界面
	 */
	public static void printEndMenu() {
		System.out.println("******************************************************");
        System.out.println("*                                                    *");
        System.out.println("*              *家 政 服 务 管 理 系 统"+"*             *");
        System.out.println("*             ---------------------------            *");
        System.out.println("*                                                    *");
        System.out.println("*                                                    *");
        System.out.println("*                                            ヽ(✿ﾟ▽ﾟ)ノ"+"                                           *");
        System.out.println("*                                                    *");
        System.out.println("*          *期  待  您  的  下  次  使  用"+"*          *");
        System.out.println("*                                                    *");
        System.out.println("******************************************************");
	}
}
