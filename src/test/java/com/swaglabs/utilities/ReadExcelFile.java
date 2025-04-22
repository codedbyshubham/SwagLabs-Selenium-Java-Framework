package com.swaglabs.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

public class ReadExcelFile {

	public static FileInputStream inputStream;
	public static XSSFWorkbook workBook;
	public static XSSFSheet excelSheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	

	public static String getCellValue(String fileName, String sheetName, int rowNo, int cellNo) {
		try {
			inputStream = new FileInputStream(fileName);
			workBook = new XSSFWorkbook(inputStream);
			excelSheet= workBook.getSheet(sheetName);
			cell=workBook.getSheet(sheetName).getRow(rowNo).getCell(cellNo);
			String cellValue = "";

	        if (cell != null) {
                switch (cell.getCellType()) {
                    case STRING:
                        cellValue = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                            cellValue = cell.getDateCellValue().toString();
                        } else {
                            double numericValue = cell.getNumericCellValue();
                            // Check if it's a whole number
                            if (numericValue == Math.floor(numericValue)) {
                                cellValue = String.valueOf((long) numericValue);  // Cast to long to remove .0
                            } else {
                                cellValue = String.valueOf(numericValue);  // Keep decimal if it's really a decimal
                            }
                        }
                        break;
                    case BOOLEAN:
                        cellValue = String.valueOf(cell.getBooleanCellValue());
                        break;
                    default:
                        cellValue = "Unsupported Cell Type";
                        break;}}
			workBook.close();
			System.out.println("Cell Value: "+ cellValue);
			return cellValue;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "";
		}
	}

	public static int getRowCount(String fileName, String sheetName) {
		try {
			inputStream = new FileInputStream(fileName);

			workBook = new XSSFWorkbook(inputStream);
			excelSheet = workBook.getSheet(sheetName);

			//		get total num of rows
			int ttlRows = excelSheet.getLastRowNum()+1;

			workBook.close();

			return ttlRows;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return 0;
		}
	}
	
	public static int getColCount(String fileName, String sheetName) {
		try {
			inputStream= new FileInputStream(fileName);
		
		workBook= new XSSFWorkbook(inputStream);
		excelSheet=workBook.getSheet(sheetName);
		
//		get total num of colns
		int ttlCells = excelSheet.getRow(0).getLastCellNum();
	
		workBook.close();
		return ttlCells;
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return 0;
		}
	}
		
}
