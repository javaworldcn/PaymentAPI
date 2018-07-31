package com.grapefs.demo.utils;

import java.util.UUID;

/**
 * 
 * 类描述：API工具类
 * 
 * @author lxl
 * @since 1.0, 2017年1月4日
 */
public final class ApiUtil {

	private ApiUtil() {

	}
	
	/**
	 * 生成随机字符串
	 * @return
	 */
	public static String genRandomStr(){
		return UUID.randomUUID().toString().replace("-", "");
	}

}
