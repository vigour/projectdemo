package com.milesbone.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.milesbone.common.po.ExcelSheetPO;
import com.milesbone.util.ExcelUtil;

import junit.framework.TestCase;

public class ExcelTest extends TestCase{
	
	private static final Logger logger = LoggerFactory.getLogger(ExcelTest.class);
	
	//excel 文件
	String excelPath = "/Users/flnet/Documents/work/帮助中心二期/规格书对照表V3_20181023.xlsx";
	
	//SQL文件
	String sqlPath = "/Users/flnet/Documents/work/帮助中心二期/helppdfV3_20181023.sql";
	
	//PDF 源路径
	String pdfPath = "/Users/flnet/Documents/work/帮助中心二期/电子说明书/";
	
	//目标PDF目录
	String destPDFDir = "/Users/flnet/Documents/work/帮助中心二期/pdfV3_20181023/";
	
	private final static String PDF_EXTENSION = ".pdf";
	
	@Test
	public void testReadExcel() throws FileNotFoundException, IOException {
		File excelFile = new File(excelPath);
		List<ExcelSheetPO> list = ExcelUtil.readExcel(excelFile, null, null);
		List<List<Object>> data = null;
		List<Object> contents = null;
		int size = 0;
		StringBuffer sql = new StringBuffer();
		
		File srcDir = new File(pdfPath);
		
		String model = null;
		String realname = null;
		
		File destPathTemp = null;
		
		File destPath = null;
		
		File sqlFile = new File(sqlPath);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		for(ExcelSheetPO po : list) {
			data = po.getDataList();
			size = data.size();
			if(size > 0) {
				logger.debug("sheetName : " + po.getSheetName());
				logger.debug("title : " + po.getTitle());
				logger.debug("headers : " + po.getHeaders());
				logger.debug("data size = " + size);
				
				for(int i=1; i < size; i++) {
					contents = data.get(i);
//					logger.debug("data.contents : " + contents);
					model = String.valueOf(contents.get(1)).trim();
					realname = String.valueOf(contents.get(2)).trim() + PDF_EXTENSION;
					if(modelMap.containsKey(model)) {
						logger.warn("model duplicated!!!!! \t" +model);
					}else {
						sql.append("insert into `fsk`.`fsk_help_pdf`( `fsk_state`, `fsk_file_name`, `fsk_create_time`, `fsk_file_url`, `fsk_create_user`, `fsk_model_name`) values ( '1', '"+model+PDF_EXTENSION+"', "+System.currentTimeMillis()+", '[pdf]/fsk/pic/photo/2.5/help/pdf/"+model+PDF_EXTENSION+"', 'root', '"+model+"');\n");
					}
					modelMap.put(model, true);
					logger.debug( "model: " + model + "\t 对应说明书：" + realname);
					
					destPathTemp = new File(pdfPath + realname);
					if(FileUtils.directoryContains(srcDir, destPathTemp)) {
						destPath = new File(destPDFDir+model+PDF_EXTENSION);
						logger.debug("path :" +destPath.getName());
						FileUtils.copyFile(destPathTemp, destPath);
						//logger.debug("contains: " + FileUtils.directoryContains(srcDir, destPathTemp));
					}else {
						logger.error("not exist:" + model +"\t realname" + realname);
					}
				}
			}
		}
		logger.debug("start generate sql..");
		FileUtils.writeStringToFile(sqlFile, sql.toString(), "utf-8", false);
		logger.debug("generate sql finish..");
		
		logger.debug("Done!");
	}

}
