/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：工具类
 * @Package: utils 
 * @author: chengbao_0  
 * @date: 2020-7-29 18:48:10 
 */
package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName DBBackup
 * @Desc 数据库备份工具类，负责数据库的备份与恢复，备份记录的删除
 * 			涉及异常、文件操作
 * @author chengbao_0
 * @Date 2020-7-19 18:46:38
 */
public class DBUtil {
	private static final DBUtil instance=new DBUtil();
	public static String USER;//用户名
	public static String PASSWORD;//密码
	public static String DBNAME;//数据库名称
	public static String BACKUPPATH;//备份路径
	public static String AUTOBACKUPTIME;//自动备份时间
	public static String BACKUPINTERVAL;//每次自动备份间隔
	public static ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();//自动备份的线程
	private DBUtil() {}//private 避免类在外部被实例化
	public static DBUtil getInstance() {
		return instance;
	}
	//静态代码块，用以加载配置文件
	static {
		init();
	}
	/*
	 * 加载配置文件并完成初始化
	 */
	private static void init() {
		//加载配置文件
		Properties params=new Properties();
		String config="Backup.properties";
		InputStream is=DBUtil.class.getClassLoader().getResourceAsStream(config);
		try {
			params.load(is);
		}catch(IOException e){
			e.printStackTrace();
		}
		//完成初始化
		USER=params.getProperty("user");
		PASSWORD=params.getProperty("password");
		DBNAME=params.getProperty("dbName");
		BACKUPPATH=params.getProperty("backupPath");
		AUTOBACKUPTIME=params.getProperty("autoBackupTime");
		BACKUPINTERVAL=params.getProperty("backupInterval");
	}
	/*
	 * 对Backup进行重载
	 * 根据现在的时间生成备份记录的文件名
	 * 对外隐藏所需要的参数，方便调用
	 */
	public static void Backup() {
		String backName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date())+".sql";
		try {
			Backup(USER, PASSWORD,DBNAME,BACKUPPATH, backName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @Title: Backup 
	 * @Description: 进行数据库的备份
	 * @param @param root 用户名
	 * @param @param pwd 密码
	 * @param @param dbName 数据库名称
	 * @param @param backPath 备份地址
	 * @param @param backName 备份名称
	 * @param @throws Exception
	 * @return void
	 * @throws 
	 */
	private static void Backup(String root,String pwd,String dbName,String backPath,String backName) throws Exception {
        String pathSql = backPath+backName;
        File fileSql = new File(pathSql);
        //创建备份sql文件
        if (!fileSql.exists()){
            fileSql.createNewFile();
        }
        //mysqldump -hlocalhost -uroot -p123456 db > /home/back.sql
        StringBuffer sb = new StringBuffer();
        sb.append("mysqldump");
        sb.append(" -h127.0.0.1");
        sb.append(" -u"+root);
        sb.append(" -p"+pwd);
        sb.append(" "+dbName+" >");
        sb.append(pathSql);
        //System.out.println("cmd命令为："+sb.toString());
        Runtime runtime = Runtime.getRuntime();
        System.out.println("开始备份......");
        @SuppressWarnings("unused")
		Process process = runtime.exec("cmd /c"+sb.toString());
        System.out.println("备份成功!");
     }
	 /*
	 * 对Restore进行重载，隐藏与用户调用不相关的参数,方便调用
	 */
	 public static void Restore(String filePath) {
		 Restore(USER,PASSWORD,DBNAME,filePath);
	 }
	 
     /**
          * 恢复数据库
     * @param root 用户名
     * @param pwd 密码
     * @param dbName 数据库名称
     * @param filePath 备份文件路径
     * mysql -hlocalhost -uroot -p123456 db < /home/back.sql
     */
    private static void Restore(String root,String pwd,String dbName,String filePath){
        StringBuilder sb = new StringBuilder();
        sb.append("mysql");
        sb.append(" -h127.0.0.1");
        sb.append(" -u"+root);
        sb.append(" -p"+pwd);
        sb.append(" "+dbName+" <");
        sb.append(filePath);
        //System.out.println("cmd命令为："+sb.toString());
        Runtime runtime = Runtime.getRuntime();
        System.out.println("开始还原数据...");
        try {
            Process process = runtime.exec("cmd /c"+sb.toString());
            InputStream is = process.getInputStream();
            BufferedReader bf = new BufferedReader(new InputStreamReader(is,"utf8"));
            String line = null;
            while ((line=bf.readLine())!=null){
                System.out.println(line);
            }
            is.close();
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("还原成功！");
    }
    public static List<File> getLocalBackupRecord(){
    	return DBUtil.searchFiles(new File(DBUtil.BACKUPPATH),"sql");
    }
    /**
     * @Title: searchFiles 
     * @Description: 查找当前文件下所有后缀名为keyword的文件
     * @param @param folder 待查找的文件夹
     * @param @param keyword 文件后缀名
     * @param @return
     * @return List<File> 符合查找条件的文件列表
     * @throws 
     */
    public static List<File> searchFiles(File folder, final String keyword) {
        List<File> result = new ArrayList<File>();
        if (folder.isFile())
            result.add(folder);
        //内部方法，获取所有的子文件
        File[] subFolders = folder.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return true;
                }
                if (file.getName().toLowerCase().endsWith("."+keyword)) {
                    return true;
                }
                return false;
            }
        });
        //判断子文件是文件还是文件夹
        if (subFolders != null) {
            for (File file : subFolders) {
                if (file.isFile()) {
                    // 如果是文件则将文件添加到结果列表中
                    result.add(file);
                } else {
                    // 如果是文件夹，则递归调用本方法，然后把所有的文件加到结果列表中
                    result.addAll(searchFiles(file, keyword));
                }
            }
        }

        return result;
    }
    /**
     * @Title: deleteBackupRecord 
     * @Description: 删除备份记录
     * @param @param fileAbsolutePath 所要删除的文件的绝对路径
     * @param @return
     * @return boolean 删除成功与否  true: 成功     false: 删除失败
     * @throws 
     */
    public static boolean deleteBackupRecord(String fileAbsolutePath) {
         boolean flag=false;
         try{
        	 File file = new File(fileAbsolutePath);
        	 flag=file.delete()?true:false;
         }catch(Exception e){
             e.printStackTrace();
         }
         return flag;
    }
    /**
     * @Title: AutoBackup 
     * @Description: 数据库的自动备份
     * @param 
     * @return void
     * @throws 
     */
    public static void AutoBackup() {
    	SimpleDateFormat simpleFormat = new SimpleDateFormat("hh:mm:ss");
    	String nowTime = simpleFormat.format(new Date());//获取格式化的当前实际按
    	Date now=null;
    	try {
			now=simpleFormat.parse(nowTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//获取格式化的自动备份时间
    	Date end=null;
    	try {
			end=simpleFormat.parse(AUTOBACKUPTIME);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(!isBackupToday()) {//今天尚未备份过
			//立即进行备份
    		System.out.println("今天尚未备份，开始自动备份:");
    		DBUtil.Backup();
		}    	
    	//将延迟时间调到明天
		long delaySeconds=(24*60*60)-((now.getTime()-end.getTime())/1000);	
		//新建定时任务
        Runnable runnable = new Runnable() {
            //run方法中是定时执行的操作
            public void run() {
            	System.out.println("开始自动备份:");
        		DBUtil.Backup();
            }
        };
        /*
         * 参数一:command：执行线程
         * 参数二:initialDelay：初始化延时
         * 参数三:period：两次开始执行最小间隔时间
         * 参数四:unit：计时单位
         */
        service.scheduleAtFixedRate(runnable, delaySeconds,Long.valueOf(BACKUPINTERVAL), TimeUnit.SECONDS);
    }
    /**
     * @Title: isBackupToday 
     * @Description: 判断今天是否备份过
     * @param @return
     * @return boolean true--已经备份过    false--今天没有备份
     * @throws 
     */
    private static boolean isBackupToday() {
    	//查找本地是否存在今天的备份文件
    	String backupFile = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    	List<File> files=DBUtil.searchFiles(new File(DBUtil.BACKUPPATH),"sql");
    	for(File file:files) {
    		if(file.getName().toLowerCase().contains(backupFile)) {
    			return true;
    		}
    	}
    	return false;
    }
    /**
     * @Title: totalBackupRecord 
     * @Description: 获取备份记录的总数
     * @param @return
     * @return int 备份记录总数
     * @throws 
     */
    public static int totalBackupRecord() {
		return DBUtil.searchFiles(new File(DBUtil.BACKUPPATH),"sql").size();
	}
}
