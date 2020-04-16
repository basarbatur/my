package com.qatools.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	public static FileInputStream fis;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	
	/**
	 * Method will open specified excelfile and sheet
	 * @param xlFilePath
	 * @param sheetName
	 */
	public static void openExcel(String xlFilePath, String sheetName) {
		
		
		try {
			fis= new FileInputStream(xlFilePath);
			workbook=new XSSFWorkbook(fis);
			sheet=workbook.getSheet(sheetName);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	   *  method will return String value of specified cell
	   * @param rowIndex
	   * @param cellIndex
	   * @return String
	   */
	public static String getCellData (int rowIndex, int cellIndex) {
		return sheet.getRow(rowIndex).getCell(cellIndex).toString();
	}
	
	/**
	 * Method will return number of actual used rows
	 * @return
	 */
	public static int rowCount() {
		return sheet.getPhysicalNumberOfRows();
	}
	/**
	 * method will return number of last columns
	 * 
	 * @return int
	 */
	public static int colCount() {
		return sheet.getRow(0).getLastCellNum();
	}
	
	/**
	 * This method will read any Excel file and return data in 2D array
	 * 
	 * @param filePath
	 * @param sheetName
	 * @return
	 */
	public static Object[][] excelIntoArray(String xlFilePath, String sheetName) {

		openExcel(xlFilePath, sheetName);

		Object[][] data = new Object[rowCount() - 1][colCount()];

		for (int i = 1; i < rowCount(); i++) {
			for (int y = 0; y < colCount(); y++) {
				data[i - 1][y] = getCellData(i, y);
			}
		}
		return data;
	}
}
