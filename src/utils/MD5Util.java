/**   
 * Copyright © 2020 chengbao_0 All rights reserved.
 * 
 * 功能描述：工具类
 * @Package: utils 
 * @author: chengbao_0  
 * @date: 2020-7-29 18:48:10 
 */
package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName MD5Util
 * @Desc MD5加密工具类
 * @author chengbao_0
 * @Date 2020-7-29 18:48:10
 */
public class MD5Util { 
	/**
	 * @Title: MD5 
	 * @Description: MD5加密 生成32位md5码
	 * @param @param inStr 待加密的字符串
	 * @param @return
	 * @return String 生成的32位md5码
	 * @throws 
	 */
    public static String MD5(String inStr){  
    	 /**
    	  * 对文本执行 md5 摘要加密, 此算法与 mysql,JavaScript生成的md5摘要进行过一致性对比.
    	  * @param plainText
    	  * @return 返回值中的字母为小写
    	  */
    	  if (null == inStr) {
    	   inStr = "";
    	  }
    	  String MD5Str = "";
    	  try {
    	   // JDK 6 支持以下6种消息摘要算法，不区分大小写
    	   // md5,sha(sha-1),md2,sha-256,sha-384,sha-512
    	   MessageDigest md = MessageDigest.getInstance("MD5");
    	   md.update(inStr.getBytes());
    	   byte b[] = md.digest();
    	 
    	   int i;
    	 
    	   StringBuilder builder = new StringBuilder(32);
    	   for (int offset = 0; offset < b.length; offset++) {
    	    i = b[offset];
    	    if (i < 0)
    	     i += 256;
    	    if (i < 16)
    	     builder.append("0");
    	    builder.append(Integer.toHexString(i));
    	   }
    	   MD5Str = builder.toString();
    	  } catch (NoSuchAlgorithmException e) {
    	   e.printStackTrace();
    	  }
    	  return MD5Str;
  
    }
}
