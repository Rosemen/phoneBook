package com.scau.pbook.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.scau.pbook.bean.AddressBean;

/**
 * 读写VCF格式文件工具类
 * 
 * @author Administrator
 *
 */
public class VCFTool {
     
	/** 读取联系人文件(vcard格式),用于导入时调用 */
	public static List<AddressBean> importVCFFile(File file) {
		List<AddressBean> list = new ArrayList<AddressBean>();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			StringBuffer bu = new StringBuffer();
			String line = null;

			/* 读取文件的每一行数据 */
			while ((line = nextLine(reader)) != null) {
				bu.append(line + "\r\n");
			}

			/* 给定文件的匹配模式,针对一对VCard */
			Pattern p = Pattern.compile("BEGIN:VCARD(\\r\\n)([\\s\\S\\r\\n\\.]*?)END:VCARD"); // 匹配模式对象
			Matcher m = p.matcher(bu.toString()); // 进行匹配操作的对象

			/* 对每一对Vcard的数据进行提取,封装到AddressBean对象中 */
			while (m.find()) {
				AddressBean be = new AddressBean();
				String str = m.group(0); // 分组

				/* 提取name */
				Pattern pn = Pattern.compile("N;([\\s\\S\\r\\n\\.]*?)([\\r\\n])");// 分组，
				Matcher mn = pn.matcher(m.group(0));
				while (mn.find()) {
					String name = "";
					//有"ENCODING=QUOTED-PRINTABLE"情况下
					if (mn.group(1).indexOf("ENCODING=QUOTED-PRINTABLE") > -1) {
						name = mn.group(1).substring(mn.group(1).indexOf("ENCODING=QUOTED-PRINTABLE:")
								+ "ENCODING=QUOTED-PRINTABLE:".length());
						name = name.substring(name.indexOf(":") + 1);//截取 name以及后面的分号的字符串，如："张三;"
						if (name.indexOf(";") > -1) {
							name = name.substring(0, name.indexOf(";"));//有分号情况下得到 name
							be.setName(qpDecoding(name));
						} else {
							be.setName(qpDecoding(name));
						}
					} else {
						Pattern pnn = Pattern.compile("CHARSET=([A-Za-z0-9-]*?):");
						Matcher mnn = pnn.matcher(mn.group(1));
						while (mnn.find()) {
							name = mn.group(1).substring(mn.group(1).indexOf(mnn.group(0)) + mnn.group(0).length());
							be.setName(name);
						}
					}

				}
				if (be.getName().length() > 20) {
					return null;
				}
				
				/*截取手机号*/
				String cell = "";
				Pattern p1 = Pattern.compile("TEL;CELL;VOICE:\\d*");// 分组，
				Matcher m1 = p1.matcher(str);

				while (m1.find()) {
					cell = m1.group(0).substring(m1.group(0).indexOf("TEL;CELL;VOICE:") + "TEL;CELL;VOICE:".length());
				}
				be.setMobilephone(cell);
				if (be.getMobilephone().length() > 11) {
					return null;
				}
				
				/*截取电话*/
				String work = "";
				Pattern p2 = Pattern.compile("TEL;WORK;VOICE:\\d*");// 分组，
				Matcher m2 = p2.matcher(str);
				while (m2.find()) {
					work = m2.group(0).substring(m2.group(0).indexOf("TEL;WORK;VOICE:") + "TEL;WORK;VOICE:".length());
				}
				be.setTelephone(work);
				if (be.getTelephone().length() > 12) {
					return null;
				}
           
				/*截取Email*/
				String email = "";
				Pattern p4 = Pattern.compile("\\w+(\\.\\w+)*@\\w+(\\.\\w+)+");// 分组，
				Matcher m4 = p4.matcher(str);
				while (m4.find()) {
					email = m4.group(0);
				}
				be.setEmail(email);
				
				/*截取生日*/
				
				String birthday = "";
				Pattern p5 = Pattern.compile("");
				
				/**/
				
				/**/
				
				/**/
				
				/**/
				
				/**/
                
				list.add(be);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		/* 对每一对vcard进行检验 */

		return list;
	}
    
	/*读取下一行数据 */
	public static String nextLine(BufferedReader reader) throws IOException {
		String line;
		String nextLine;
		do {
			line = reader.readLine();
			if (line == null) {
				return null;
			}
		} while (line.length() == 0);
		while (line.endsWith("=")) {
			line = line.substring(0, line.length() - 1);
			line += reader.readLine();
		}
		reader.mark(1000);
		nextLine = reader.readLine();
		if ((nextLine != null) && (nextLine.length() > 0)
				&& ((nextLine.charAt(0) == 0x20) || (nextLine.charAt(0) == 0x09))) {
			line += nextLine.substring(1);
		} else {
			reader.reset();
		}
		line = line.trim();
		return line;
	}

	/** 将联系人信息写到文件(vcard格式)中，用于导出时所用 */
	public static void exportVcfFile(List<AddressBean> list, File file) {
		try {
			if (file.exists()) {
				file.createNewFile();
			}
			BufferedWriter reader = new BufferedWriter(new PrintWriter(file));
			for (AddressBean bean : list) {
				/* 开始信息与基本标签 */
				reader.write("BEGIN:VCARD");
				reader.write("\r\n");
				reader.write("VERSION:2.1");
				reader.write("\r\n");

				/* 写入姓名 */
				reader.write("N;CHARSET=UTF-8;ENCODING=QUOTED-PRINTABLE:" + qpEncodeing(bean.getName()) + ";");
				reader.write("\r\n");

				/* 电话号码 */
				if (!bean.getTelephone().trim().isEmpty() || bean.getTelephone() != null)
					reader.write("TEL;WORK;VOICE:" + bean.getTelephone() + "\r\n");

				/* 手机号码 */
				if (!bean.getMobilephone().trim().isEmpty() || bean.getMobilephone() != null)
					reader.write("TEL;CELL;VOICE:" + bean.getMobilephone() + "\r\n");

				/* 邮箱地址 */
				if (!bean.getEmail().trim().isEmpty() || bean.getEmail() != null)
					reader.write("EMAIL;PREF;INTERNET:" + bean.getEmail() + "\r\n");

				/* 生日 */
				if (!bean.getBirthday().trim().isEmpty() || bean.getBirthday() != null)
					reader.write("BDAY:" + bean.getBirthday() + "\r\n");

				/* 工作单位 */
				if (!bean.getWorkplace().trim().isEmpty() || bean.getWorkplace() != null)
					reader.write("ADR;WORK;POSTAL;PARCEL:" + qpEncodeing(bean.getWorkplace()) + "\r\n");

				/* 家庭住址 */
				if (!bean.getAddress().trim().isEmpty() || bean.getAddress() != null)
					reader.write("ADR;HOME;POSTAL;PARCEL:" + qpEncodeing(bean.getAddress()) + "\r\n");

				/* 邮编 */
				/*
				 * if(!bean.getPostcode().trim().isEmpty() || bean.getPostcode() != null)
				 * reader.write("TTEL;WORK;VOICE:" + bean.getPostcode() +"\r\n");
				 */

				/* 所属组 */
				if (!bean.getGroup().trim().isEmpty() || bean.getGroup() != null)
					reader.write("ORG:" + bean.getGroup() + "\r\n");

				/* 备注 */
				if (!bean.getRemarks().trim().isEmpty() || bean.getRemarks() != null)
					reader.write("NOTE;ENCODING=QUOTED-PRINTABLE:" + bean.getRemarks() + "\r\n");

				/* 结束标志 */
				reader.write("END:VCARD");
				reader.write("\r\n");
			}
			reader.flush();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 对中文进行编码 */
	public static String qpEncodeing(String str) {
		char[] encode = str.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < encode.length; i++) {
			if ((encode[i] >= '!') && (encode[i] <= '~') && (encode[i] != '=') && (encode[i] != '\n')) {
				sb.append(encode[i]);
			} else if (encode[i] == '=') {
				sb.append("=3D");
			} else if (encode[i] == '\n') {
				sb.append("\n");
			} else {
				StringBuffer sbother = new StringBuffer();
				sbother.append(encode[i]);
				String ss = sbother.toString();
				byte[] buf = null;
				try {
					buf = ss.getBytes("utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				if (buf.length == 3) {
					for (int j = 0; j < 3; j++) {
						String s16 = String.valueOf(Integer.toHexString(buf[j]));
						// 抽取中文字符16进制字节的后两位,也就是=E8等号后面的两位,
						// 三个代表一个中文字符
						char c16_6;
						char c16_7;
						if (s16.charAt(6) >= 97 && s16.charAt(6) <= 122) {
							c16_6 = (char) (s16.charAt(6) - 32);
						} else {
							c16_6 = s16.charAt(6);
						}
						if (s16.charAt(7) >= 97 && s16.charAt(7) <= 122) {
							c16_7 = (char) (s16.charAt(7) - 32);
						} else {
							c16_7 = s16.charAt(7);
						}
						sb.append("=" + c16_6 + c16_7);
					}
				}
			}
		}
		return sb.toString();
	}

	/** 对vcf文件进行解码 */

	/** 对中文进行解码 */
	public static String qpDecoding(String str) {
		if (str == null)
			return ""; // 判断所要解码的字符是否为空
		try {
			str = str.replaceAll("=\n", "");
			byte[] bytes = str.getBytes("US-ASCII");
			if (bytes == null)
				return "";
			for (int i = 0; i < bytes.length; i++) {
				byte b = bytes[i];
				if (b != 95) {
					bytes[i] = b;
				} else {
					bytes[i] = 32;
				}
			}
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			for (int i = 0; i < bytes.length; i++) {
				int b = bytes[i];
				if (b == '=') {
					try {
						int u = Character.digit((char) bytes[++i], 16);
						int l = Character.digit((char) bytes[++i], 16);
						if (u == -1 || l == -1) {
							continue;
						}
						buffer.write((char) ((u << 4) + l));
					} catch (ArrayIndexOutOfBoundsException e) {
						e.printStackTrace();
					}
				} else {
					buffer.write(b);
				}
			}
			return new String(buffer.toByteArray(), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	@Test
	public void test() {
		/*List<AddressBean> list = new ArrayList<>();
		AddressBean bean = new AddressBean();
		bean.setName("张三");
		bean.setTelephone("0663-3133456");
		bean.setMobilephone("15819610734");
		bean.setEmail("chen@163.com");
		bean.setAddress("揭阳,中国");
		bean.setWorkplace("广州,中国");
		bean.setGroup("a");
		bean.setPostcode("a");
		bean.setRemarks("e");
		bean.setBirthday("e");
		list.add(bean);*/

		File file = new File("D:/test.vcf");
	    System.out.println(importVCFFile(file));
	//  exportVcfFile(list, file);
	}
}
