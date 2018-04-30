
package com.agile.framework.excel;

import java.io.*;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.agile.framework.utils.FileDownloadUtils;
import com.agile.framework.utils.ReflectUtils;

public class ExcelExport {
	
	// 日志
	static final Logger logger = LoggerFactory.getLogger(ExcelExport.class.getName());
	
	// 文件名
	private String fileName ;
	// 文件保存路径
	private String fileDir;
	// Sheet名
	private String sheetName;
	// 标题名
	private String titleName;
	// 表头名
	private List<String> headers;
	// 当前行索引
	private int rowIndex = 0;
	
	// Workbooks实例
	private HSSFWorkbook workbook;
	// Sheet实例
	private HSSFSheet sheet;
	
	
	/**
	 * 创建Excel工作薄
	 */	
	public void createWorkBook() {
		// 创建工作薄
        workbook = new HSSFWorkbook();
        workbook.createInformationProperties();
        workbook.getDocumentSummaryInformation().setCompany("*****公司");
        
        // 设置概要信息
        SummaryInformation summary = workbook.getSummaryInformation();
        summary.setAuthor("Admin");
        summary.setApplicationName("Admin");
        summary.setLastAuthor("Admin");
        summary.setComments("Data export");
        summary.setTitle("Data export");
        summary.setSubject("Data export");
        summary.setCreateDateTime(new Date());        
	}

	/**
	 * 创建工作薄Sheet
	 * @param name sheet名
	 */		
	public void createSheet(String name) {
		// 设置缺省工作表名
		this.sheetName = name;
		if (this.sheetName == null) {
			this.sheetName = "Sheet1";
		}
		
		sheet = workbook.createSheet(name);
	}

    /**
     * 创建Excel标题
	 * @param title 标题
	 * @param width 标题宽度(占据多少格)
     */			
	public void createTableTitle(String title, int width) {
		// 没标题名,则不创建标题栏
		this.titleName = title;
		if (this.titleName == null) {
			return;
		}
		
       // 标题样式
       HSSFCellStyle titleStyle = workbook.createCellStyle();
       titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
       HSSFFont titleFont = workbook.createFont();
       titleFont.setFontHeightInPoints((short) 20);
       titleFont.setBoldweight((short) 700);
       titleStyle.setFont(titleFont);		
       
       // 创建标题行
       HSSFRow titleRow = sheet.createRow(rowIndex++);
       titleRow.createCell(0).setCellValue(title);
       titleRow.getCell(0).setCellStyle(titleStyle);
       sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, width - 1));
	}

    /**
     * 创建数据表头
	 * @param headers 表头
     */			
	public void createTableHeader(List<String> headers) {
		// 没表头,则不创建
		this.headers = headers;
		if (this.headers == null) {
			return;
		}
		
        // 列头样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        headerStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        headerStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        headerStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        headerStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFFont headerFont = workbook.createFont();
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerStyle.setFont(headerFont);
        
        // 创建列头行
        HSSFRow headerRow = sheet.createRow(rowIndex ++);
        for(int i=0; i < headers.size(); i++) {
            headerRow.createCell(i).setCellValue(headers.get(i));
            headerRow.getCell(i).setCellStyle(headerStyle);
        }
	}

    /**
     * 创建数据行
     * @param dataList 表数据
     */		
	public void createTableRow(List<?> dataList) {
        // 单元格样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFFont cellFont = workbook.createFont();
        cellFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        cellStyle.setFont(cellFont);

		Object obj = dataList.get(0);
		Class<?> clazz = obj.getClass();
		List<String> fieldNames = ReflectUtils.getFieldNames(clazz);

		// 没有指定表头,则对象属性名为列名
        if (this.headers == null) {
    		createTableHeader(fieldNames);
        }
        
        // 遍历数据,创建数据行
    	for(int i=0; i<=dataList.size(); i++){
    		Object object = dataList.get(i);
    		Row dataRow = sheet.createRow(i+rowIndex);
    		
    		// 没有指定表头,则整个对象属性值导出
    		if (this.headers == null) {
        		for (int columnIndex = 0; columnIndex<fieldNames.size(); columnIndex++) {
        			try {
	        			Cell cell = dataRow.createCell(columnIndex);        			
	        			Object value = ReflectUtils.getFieldValue(object, fieldNames.get(columnIndex));
	        			cell.setCellValue(value.toString());
        			}catch(Exception e) {
        				e.printStackTrace();
        			}        			
        			rowIndex++;
        		}
    		}
    	}
	}

    /**
     * 导出Excel 97(.xls)格式
     */	
	public void exportList(List<?> dataList) {
		// 创建Excel工作表
		createWorkBook();
		createSheet("Sheet1");
        
		// 空数据也导出
		if(dataList==null || dataList.size()==0)
            return;
        
		// 导出表头和数据
        createTableRow(dataList);
	}

    /**
     * 保存Excel格式文件
     */		
	public void saveXls() {
		String filePath = fileDir + "//" + fileName +".xls";
		logger.info("Export excel file:" + filePath);
		
    	try {
			FileOutputStream out = new FileOutputStream(new File(filePath + "\\" + fileName + ".xlsx"));
		    workbook.write(out);
		    out.close();		   
    	}catch(Exception e) {
    		logger.error(e.getMessage());
    	}    	    	
	}

    /**
     * 响应Excel文件下载
     */			
	public void downloadFile(HttpServletRequest request, HttpServletResponse response) {
		String filePath = fileDir + "//" + fileName +".xls";
		FileDownloadUtils.fileDonwload(request, response, filePath);
	}
	
}