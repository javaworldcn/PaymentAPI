package com.grapefs.demo.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * 类描述：对账文件分流工具
 * @author zhoujin
 * @since 1.0, 2016年03月14日
 */
public class FileProcessUtil {

	/**
	 * 民生对账文件区分
	 * @param bankFile
	 * @param prefix
	 * @return
	 * @throws IOException 
	 */
	public static String msFileDistinguish(File bankFile,String prefix) throws IOException{
		InputStream input =  new FileInputStream(bankFile); 
		InputStreamReader read = new InputStreamReader(input, "utf-8");// 考虑到编码格式
		BufferedReader bufferedReader = new BufferedReader(read);
		String lineTxt = null;
		//收银家对账文件Result
		StringBuffer result = new StringBuffer();
		try {
			while ((lineTxt = bufferedReader.readLine()) != null) {
				if (StringUtils.isBlank(lineTxt)||lineTxt.contains("#")) {
					result.append(lineTxt).append("\n");
					continue;
				}
				
				String[] split = lineTxt.split("\\|");
				String reqMsgId = split[3];
				if(reqMsgId.startsWith(prefix)){
					result.append(lineTxt).append("\n");
					continue;
				}
				continue;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			bufferedReader.close();
			read.close();
		}
		return result.toString();
	}
	
	/**
	 * 华夏对账文件区分
	 * @param bankFile
	 * @param prefix
	 * @return
	 * @throws IOException 
	 */
	public static String hxFileDistinguish(File bankFile,String prefix) throws IOException{
		InputStream input =  new FileInputStream(bankFile); 
		InputStreamReader read = new InputStreamReader(input, "utf-8");// 考虑到编码格式
		BufferedReader bufferedReader = new BufferedReader(read);
		String lineTxt = null;
		String orderType = "1"; //开始读取的是交易订单，退款订单改为2
		//收银家对账文件Result
		StringBuffer result = new StringBuffer();
		try {
			while ((lineTxt = bufferedReader.readLine()) != null) {
				if (StringUtils.isBlank(lineTxt)||lineTxt.contains("#")) {
					result.append(lineTxt).append("\n");
					continue;
				}
				if(lineTxt.startsWith("﻿商户编号|")){
					if(lineTxt.contains("原交易订单号")){
						orderType = "2";
					}
					if(lineTxt.contains("出款流水号")){
						return result.toString();
					}
					result.append(lineTxt).append("\n");
					continue;
				}
				
				String[] split = lineTxt.split("\\|");
				String reqMsgId = null;
				if(orderType.equals("1")){
					reqMsgId = split[2];
				}else if(orderType.equals("2")){
					reqMsgId = split[3];
				}else{
					continue;
				}
				if(reqMsgId.startsWith(prefix)){
					result.append(lineTxt).append("\n");
					continue;
				}
				continue;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			bufferedReader.close();
			read.close();
		}
		return result.toString();
	}
	
	/**
	 * 根据商户编号分流
	 * @param bankFile
	 * @param prefix
	 * @return
	 * @throws IOException 
	 */
	public static String MerchantFileDistinguish(File bankFile,String merchantNo) throws IOException{
			InputStream input =  new FileInputStream(bankFile); 
			InputStreamReader read = new InputStreamReader(input, "utf-8");// 考虑到编码格式
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = null;
			//收银家商户分流对账文件Result
			StringBuffer result = new StringBuffer();
			try {
				while ((lineTxt = bufferedReader.readLine()) != null) {
					if (StringUtils.isBlank(lineTxt)||lineTxt.contains("#") || lineTxt.startsWith("商户编号|")) {
						result.append(lineTxt).append("\n");
						continue;
					}
					String[] split = lineTxt.split("\\|");
					String merchantNoText = split[0];
					if(merchantNoText.endsWith(merchantNo)){
						result.append(lineTxt).append("\n");
						continue;
					}
					continue;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				bufferedReader.close();
				read.close();
			}
			return result.toString();
		}
	
	
	/**
	 * 快捷对账文件合并
	 * @param bankFile
	 * @param prefix
	 * @return
	 * @throws IOException 
	 */
	public static String MergefastReconFile(List<File> fastFile) throws IOException{
		StringBuffer result = new StringBuffer();
		int i = 0; //只取一行标题
		for(File file:fastFile){
			InputStream input =  new FileInputStream(file); 
			InputStreamReader read = new InputStreamReader(input, "GBK");// 考虑到编码格式
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = null;
			if(i == 0){
				result.append(bufferedReader.readLine()).append(StringUtils.LF);
				i++;
			}else{
				bufferedReader.readLine(); //过滤第一行标题
			}
			try {
				while ((lineTxt = bufferedReader.readLine()) != null) {
					if (StringUtils.isBlank(lineTxt)) {
						continue;
					}
					result.append(lineTxt).append(StringUtils.LF);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				bufferedReader.close();
				read.close();
			}
		}
		return result.toString();
	}
}
