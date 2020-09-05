/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：
 * @Package: utils 
 * @author: chengbao_0  
 * @date: 2020-8-1 12:21:37 
 */
package utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.Console;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Clear
 * @Desc 控制台清屏
 * @author chengbao_0
 * @Date 2020-8-1 12:21:37
 */
public class Clear {
	/**
	 * 根据是否能得到windows控制台的console进行判断，调用不同的清屏函数
	 */
	public static void clear() {
		Console console = System.console();
	    if(console==null) {//eclipse控制台
	    	clear_Eclipse();
	    }else {//Dos控制台
	    	clear_Dos();
	    }
	}
	/**
	 * 采用Dos命令进行清屏
	 */
	private static void clear_Dos() {
		try {
			new ProcessBuilder("cmd","/C","cls").inheritIO().start().waitFor(30000, TimeUnit.SECONDS);
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 模拟鼠标点击进行清屏
	 */
	@SuppressWarnings("deprecation")
	private static void clear_Eclipse(){
        Robot r=null;
		try {
			r = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
        r.mousePress(InputEvent.BUTTON3_MASK);       // 按下鼠标右键
        r.mouseRelease(InputEvent.BUTTON3_MASK);    // 释放鼠标右键
        r.keyPress(KeyEvent.VK_CONTROL);             // 按下Ctrl键
        r.keyPress(KeyEvent.VK_R);                    // 按下R键
        r.keyRelease(KeyEvent.VK_R);                  // 释放R键
        r.keyRelease(KeyEvent.VK_CONTROL);            // 释放Ctrl键
        r.delay(200);       

    }
	public static void delay(double delaySeconds) {
		Robot r=null;
		try {
			r = new   Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        r.delay((int) (1000*delaySeconds));
	}
}
