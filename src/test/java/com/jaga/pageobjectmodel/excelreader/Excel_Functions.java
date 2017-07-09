/**
 *The below interface is created to have the Excel Data reader methods to be implemented
 *
 * @author Jagatheshwaran
 */

/**
 *Importing Package
 */
package com.jaga.pageobjectmodel.excelreader;

/**
 * A interface is created with name : Excel_Functions
 */
public interface Excel_Functions {

	public String getCellData(String sheetName, String colName, int rowNum);

	public String getCellData(String sheetName, int colName, int rowNum);

	public int getRowCount(String sheetName);

	public int getColumnCount(String sheetName);

}
