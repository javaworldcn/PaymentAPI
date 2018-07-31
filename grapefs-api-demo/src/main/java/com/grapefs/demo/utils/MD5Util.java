package com.grapefs.demo.utils;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * 类描述：API MD5工具类
 * 
 * @author lxl
 * @since 1.0, 2016年08月08日
 */
public class MD5Util {

	private static Logger logger = LoggerFactory.getLogger(MD5Util.class);
	
	/** 签名属性名 sign **/
	private static final String SIGN_KEY = "sign";
	
	/** 密钥属性名key**/
	private static final String SECRET_KEY = "key";

	/**
	 * 计算签名
	 * 
	 * @param map
	 *            要参与签名的map数据
	 * @param md5Key
	 *            密钥
	 * @return 签名
	 */
	public static String getSign(Map<String, ?> map, String md5Key) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.putAll(map);
		return getSign(jsonObj, md5Key);
	}

	/**
	 * 计算签名
	 * 
	 * @param jsonObj
	 *            要参与签名的json数据
	 * @param md5Key
	 *            密钥
	 * @return 签名
	 */
	public static String getSign(JSONObject jsonObj, String md5Key) {
		if (jsonObj == null || jsonObj.isEmpty()) {
			return null;
		}
		String str2Sign = buildParam4Sign(jsonObj, SIGN_KEY, md5Key);
		String result = DigestUtils.md5Hex(str2Sign).toUpperCase();
		logger.info("MD5签名原始串：{}，签名结果：{}", new Object[] { str2Sign, result });
		return result;
	}

	/**
	 * 验证签名
	 * 
	 * @param map
	 *            要参与签名的map数据
	 * @param md5Key
	 *            密钥
	 * @param sign
	 *            签名
	 * @return
	 */
	public static boolean verifySign(Map<String, ?> map, String md5Key, String sign) {
		String md5Text = getSign(map, md5Key);
		return md5Text.equalsIgnoreCase(sign);
	}

	/**
	 * 验证签名
	 * 
	 * @param jsonObject
	 *            要参与签名的json数据
	 * @param md5Key
	 *            密钥
	 * @param sign
	 *            签名
	 * @return
	 */
	public static boolean verifySign(JSONObject jsonObject, String md5Key, String sign) {
		String md5Text = getSign(jsonObject, md5Key);
		return md5Text.equalsIgnoreCase(sign);
	}
	
	/**
	 * 拼接用于签名的参数
	 * @param jsonObj
	 * @return
	 */
	private static String buildParam4Sign(JSONObject jsonObj, String signKey, String md5Key) {
		Set<String> keySet = jsonObj.keySet();
		StringBuilder param = new StringBuilder(20 * keySet.size());
		String[] keys = keySet.toArray(new String[keySet.size()]);
		Arrays.sort(keys, String.CASE_INSENSITIVE_ORDER);
		for (String key : keys) {
			// 排除sign
			if (signKey.equals(key)) {
				continue;
			}
			Object value = jsonObj.get(key);
			// 排除值为null的情况
			if (value != null) {
				param.append(key).append("=").append(value).append("&");
			}
		}
		param.append(SECRET_KEY).append("=").append(md5Key);
		return param.toString();
	}
	
}
