package com.milesbone.test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;

public class FileTest extends TestCase{
	
	private static final Logger logger = LoggerFactory.getLogger(FileTest.class);

	String srcDir = "/Users/flnet/Documents/work/帮助中心二期/";
	
	String srcPath = "/Users/flnet/Documents/work/帮助中心二期/规格书对照表.xlsx";
	
	String destPath = "规格书对照表";
	
	
	@Test
	public void testFileName() throws IOException {
		File file = new File(srcPath);
		String filename = file.getName();
		logger.debug("file name:"+filename);
		logger.debug("file name no extension:"+FilenameUtils.getBaseName(filename));
		logger.debug("file name no extension:"+FilenameUtils.equals(FilenameUtils.getBaseName(filename), destPath));
		logger.debug("file name contain:"+ FilenameUtils.directoryContains(srcDir, FilenameUtils.getBaseName(srcPath)));
		
		
	}
}
