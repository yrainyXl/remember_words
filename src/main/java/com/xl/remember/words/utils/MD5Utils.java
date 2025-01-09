package com.xl.remember.words.utils;

import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;

public class MD5Utils {

	/**
	 * 
	 * @Title: MD5Utils.java
	 * @Package com.imooc.utils
	 * @Description: 对字符串进行md5加密
	 */
	@SneakyThrows
	public static String getMD5Str(String strValue)  {
		MessageDigest md5 = MessageDigest.getInstance("MD5");

		// 计算 MD5 的字节数组
		byte[] md5Bytes = md5.digest(strValue.getBytes());

		// 将字节数组转换为 32 位小写十六进制字符串
		StringBuilder hexString = new StringBuilder();
		for (byte b : md5Bytes) {
			String hex = Integer.toHexString(0xFF & b); // 转为无符号十六进制
			if (hex.length() == 1) {
				hexString.append('0'); // 补齐前导0
			}
			hexString.append(hex);
		}

		return hexString.toString();
	}

	public static void main(String[] args) {
		try {
			String md5 = getMD5Str("2015063000000001apple143566028812345678");
			System.out.println(md5);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
