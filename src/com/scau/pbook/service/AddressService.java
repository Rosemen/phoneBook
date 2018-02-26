package com.scau.pbook.service;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.scau.pbook.bean.AddressBean;
import com.scau.pbook.dao.FileTools;

/**
 * 联系人业务类：联系人可进行的操作
 * 
 * @author Administrator
 *
 */
public class AddressService {
	private File file = null;//当前所导入文件
	private String filename = null;//当前所导入文件名
	
	public AddressService(File file) {
		this.file = file;
		filename = file.getName();
	}
	
	/** 导入文件 */
	public List<AddressBean> importFile(File file) {
		if (file == null || !file.exists())
			throw new RuntimeException("导入失败");
		String filename = file.getName();// 得到导入文件的文件名
		if (filename.endsWith(".csv"))   // 判断文件类型
			return FileTools.importCsvFile(file);
		else
			return FileTools.importVcfFile(file);
	}

	/** 导出文件 */
	public void exportFile(List<AddressBean> list, File file) {
		if (list == null || file == null)
			throw new RuntimeException("导出失败");
		String filename = file.getName();// 得到导出的文件名,
		if (filename.endsWith(".csv"))
			FileTools.exportCsvFile(list, file);
		else if (filename.endsWith(".vcf"))
			FileTools.exportVcfFile(list, file);

	}

	/** 添加联系人 */
	public void add(File file, String[] item) {
    
	}
	
	/** 删除联系人*/
	public void delete() {
		
	}
	
	/** 修改联系人*/
	public void modify() {
		
	}
	
	/** 查询联系人 */
	public AddressBean query() {
		return null;
	}

	@Test
	public void test() {
		System.out.println(importFile(new File("D:/a.csv")));
	}
}
