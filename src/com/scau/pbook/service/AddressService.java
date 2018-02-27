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
		if(file != null)
		filename = file.getName();
	}
	
	/** �����ļ� */
	public List<AddressBean> importFile() {
		if (file == null || filename == null)       //�ж��Ƿ�ɹ������ļ�
			throw new RuntimeException("����ʧ��");
		if (filename.endsWith(".csv"))// �ж��ļ�����
			return FileTools.importCsvFile(file);
		else if(filename.endsWith(".vcf"))
			return FileTools.importVcfFile(file);
		else return null;
	}

	/** �����ļ� */
	public void exportFile(List<AddressBean> list,File file) {
		if (list == null || file == null)
			throw new RuntimeException("����ʧ��");
		String filename = file.getName();
		if (!filename.isEmpty() && filename.endsWith(".csv"))
			FileTools.exportCsvFile(list, file);
		else if (!filename.isEmpty() && filename.endsWith(".vcf"))
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
		//System.out.println(importFile(new File("D:/a.csv")));
	}
}
