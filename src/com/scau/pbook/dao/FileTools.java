package com.scau.pbook.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.scau.pbook.bean.AddressBean;
import com.scau.pbook.tools.AddressBeanTool;

/**
 * ��д�ļ�������
 * 
 * @author Administrator
 *
 */
public class FileTools {
	/** ��ȡ��ϵ���ļ�(csv��ʽ),���ڵ���ʱ���� */
	public static List<AddressBean> importCsvFile(File file) {
		List<AddressBean> list = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			reader.readLine();// ������Ϣ
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] item = line.split(",");// �Զ��ŷָ�ÿһ������
				AddressBean bean = AddressBeanTool.toBean(item);// ��װ�������ݵ�AddressBean����
				if (bean != null)
					list.add(bean);
			}
			reader.close();
			return list;
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/** ����ϵ����Ϣд���ļ�(csv��ʽ)�У����ڵ���ʱ���� */
	public static void exportCsvFile(List<AddressBean> list, File file) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write("ͨѶ¼");// ������
			writer.newLine();// ��������
			for (AddressBean bean : list) {
				writer.write(bean.getName()+",");
				writer.write(bean.getTelephone()+",");
				writer.write(bean.getMobilephone()+",");
				writer.write(bean.getEmail()+",");
				writer.write(bean.getBirthday()+",");
				writer.write(bean.getWorkplace()+",");
				writer.write(bean.getAddress()+",");
				writer.write(bean.getPostcode()+",");
				writer.write(bean.getGroup()+",");
				writer.write(bean.getRemarks());
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/** ��ȡ��ϵ���ļ�(vcard��ʽ),���ڵ���ʱ���� */
	public static List<AddressBean> importVcfFile(File file) {
		List<AddressBean> list = new ArrayList<>();
		return list;
	}

	/** ����ϵ����Ϣд���ļ�(vcard��ʽ)�У����ڵ���ʱ���� */
	public static void exportVcfFile(List<AddressBean> list, File file) {
	}

	@Test
	public void test() {
		/*File f1 = new File("D:/a.csv");
		File f2 = new File("D:/b.csv");
		List<AddressBean> list = importCsvFile(f1);
		exportCsvFile(list, f2);*/
	}
}
