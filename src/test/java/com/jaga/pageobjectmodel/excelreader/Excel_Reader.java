/**
 *The below class is created to read the Data from the Excel file
 *
 * @author Jagatheshwaran
 */

/**
 *Importing Package
 */
package com.jaga.pageobjectmodel.excelreader;

/**
 * Importing the necessary predefined classes
 */
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * A class is created with name : Excel_Reader
 */
public class Excel_Reader implements Excel_Functions{
	
	/**
	 * The predefined classes Object references is created and initialized  
	 */
	public String path;
	public FileInputStream fis;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;

	/**
	 * The constructor is created for the class
	 * 
	 * @param path
	 * 
	 * @author Jagatheshwaran
	 */
	public Excel_Reader(String path) {
		this.path = path;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method will get the data from the Excel sheet
	 * 
	 * @param sheetName
	 * @param colName
	 * @param rowNum
	 * 
	 * @author Jagatheshwaran
	 */
	public String getCellData(String sheetName, String colName, int rowNum) {
		try {
			int col_Num = 0;
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().equals(colName)) {
					col_Num = i;
				}
			}
			row = sheet.getRow(rowNum - 1);
			cell = row.getCell(col_Num);

			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				return cell.getStringCellValue();
			} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				return String.valueOf(cell.getNumericCellValue());
			} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
				return String.valueOf(cell.getBooleanCellValue());
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
				return "";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * This method will get the data from the Excel sheet
	 * 
	 * @param sheetName
	 * @param colName
	 * @param rowNum
	 * 
	 * @author Jagatheshwaran
	 */
	public String getCellData(String sheetName, int colName, int rowNum) {
		try {
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(0);
			row = sheet.getRow(rowNum - 1);
			cell = row.getCell(colName);
			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				return cell.getStringCellValue();
			} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				return String.valueOf(cell.getNumericCellValue());
			} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
				return String.valueOf(cell.getBooleanCellValue());
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
				return "";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * This method will get the Row count of the Excel sheet
	 * 
	 * @param sheetName
	 * 
	 * @author Jagatheshwaran
	 */
	public int getRowCount(String sheetName) {
		try {
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1) {
				return 0;
			} else {
				sheet = workbook.getSheetAt(index);
				int number = sheet.getLastRowNum() + 1;
				return number;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * This method will get the Column count of the Excel sheet
	 * 
	 * @param sheetName
	 * 
	 * @author Jagatheshwaran
	 */
	public int getColumnCount(String sheetName) {
		try {
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1) {
				return 0;
			} else {
				sheet = workbook.getSheet(sheetName);
				row = sheet.getRow(0);
				return row.getLastCellNum();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	

}
