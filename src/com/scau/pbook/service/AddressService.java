package com.scau.pbook.service;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.scau.pbook.bean.AddressBean;
import com.scau.pbook.dao.FileTools;

/**
 * ��ϵ��ҵ���ࣺ��ϵ�˿ɽ��еĲ���
 * 
 * @author Administrator
 *
 */
public class AddressService {
	private File file = null;//��ǰ�������ļ�
	private String filename = null;//��ǰ�������ļ���
	
	public AddressService(File file) {
		this.file = file;
		filename = file.getName();
	}
	
	/** �����ļ� */
	public List<AddressBean> importFile(File file) {
		if (file == null || !file.exists())
			throw new RuntimeException("����ʧ��");
		String filename = file.getName();// �õ������ļ����ļ���
		if (filename.endsWith(".csv"))   // �ж��ļ�����
			return FileTools.importCsvFile(file);
		else
			return FileTools.importVcfFile(file);
	}

	/** �����ļ� */
	public void exportFile(List<AddressBean> list, File file) {
		if (list == null || file == null)
			throw new RuntimeException("����ʧ��");
		String filename = file.getName();// �õ��������ļ���,
		if (filename.endsWith(".csv"))
			FileTools.exportCsvFile(list, file);
		else if (filename.endsWith(".vcf"))
			FileTools.exportVcfFile(list, file);

	}

	/** �����ϵ�� */
	public void add(File file, String[] item) {
    
	}
	
	/** ɾ����ϵ��*/
	public void delete() {
		
	}
	
	/** �޸���ϵ��*/
	public void modify() {
		
	}
	
	/** ��ѯ��ϵ�� */
	public AddressBean query() {
		return null;
	}

	@Test
	public void test() {
		System.out.println(importFile(new File("D:/a.csv")));
	}
}
