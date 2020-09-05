/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：表示层，管理员二级菜单，显示菜单同时与用户进行交互
 * @Package: JSP.adminSecondaryMenu
 * @author: chengbao_0  
 * @date: 2020-7-29 18:48:10 
 */
package JSP.adminSecondaryMenu;

import utils.Input;

/**
 * @ClassName IsReturenToMainMenu
 * @Desc 次级菜单返回主菜单接口
 * @author chengbao_0
 * @Date 2020-7-19 12:40:46
 */
public interface ReturnToAdminMainMenu {
	/**
	 * @Title: IsReturn 
	 * @Description: 选择是否返回主菜单
	 * @param @return
	 * @return boolean true--返回主菜单   false--不返回主菜单
	 * @throws 
	 */
	static boolean IsReturn() {
		//提示是否返回主菜单
		System.out.println("\n=====================-----END-----====================\n");
		return Input.PromptAndChoose("您是否继续其它操作？若返回上一界面请输入y,返回主菜单请按任意键", "y",()->{return false;},()->{return true;});
	}
}
