package com.mystore.utility;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**here rowNum and columnNum should be more then zero, as in excel file visible row stars with 1 and column starts with A 
 * 
 */
public class NewExcelLibrary {

	public static String path = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\TestData.xlsx";

//	public  String path;
	public FileInputStream fis = null;
	public FileOutputStream fileOut = null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;

	public NewExcelLibrary() {
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public NewExcelLibrary(String path) {
		this.path = path;

		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getCellData(String sheetName, String colName, int rowNum) {
		try {
			int sheetIndex = workbook.getSheetIndex(sheetName);
			if (sheetIndex == -1)
				return "";
			if (rowNum <= 0)
				return "";
			row = sheet.getRow(0);
			int colIndex = -1;
			for (int i = 0; i <= row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().trim().equals(colName)) {
					colIndex = i;
					break;
				}
			}
			if (colIndex == -1)
				return "";
			row = sheet.getRow(rowNum - 1); // assging the row passed in parameter
			cell = row.getCell(colIndex);
			if (cell == null)
				return "";

			switch (cell.getCellType()) {
			case STRING:
				return cell.getStringCellValue();

			case NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					return new SimpleDateFormat("dd-MM-yyyy").format(cell.getDateCellValue());

				} else {
					return String.valueOf(cell.getNumericCellValue());

				}

			case BOOLEAN:
				return String.valueOf(cell.getBooleanCellValue());

			case FORMULA:
				return "";

			case BLANK:
				return "";

			default:
				return "";

			}
		} catch (Exception e) {
			e.printStackTrace();
			return "there no value in sheet" + sheetName + " at row " + rowNum;
		}

	}

	public String getCellData(String sheetName, int colNum, int rowNum) {
		try {
			int sheetIndex = workbook.getSheetIndex(sheetName);
			if (sheetIndex == -1)
				return "";
			if (colNum <= 0 || rowNum <= 0)
				return "";
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(rowNum - 1);
			cell = row.getCell(colNum - 1);

			switch (cell.getCellType()) {

			case STRING:
				return cell.getStringCellValue();

			case NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					return new SimpleDateFormat("dd-MM-yyyy").format(cell.getDateCellValue());

				} else {
					return String.valueOf(cell.getNumericCellValue());

				}

			case BOOLEAN:
				return String.valueOf(cell.getBooleanCellValue());

			case FORMULA:
				return "";

			case BLANK:
				return "";

			default:
				return "";

			}
		} catch (Exception e) {
			e.printStackTrace();
			return "There is no data in sheet " + sheetName + " ar rowNum" + rowNum;

		}

	}

	public boolean setCellData(String sheetName, String colName, int rowNum, String data) {
		try {
			int sheetIndex = workbook.getSheetIndex(sheetName);
			if (sheetIndex == -1)
				return false;
			if (rowNum <= 0)
				return false;
			row = sheet.getRow(0); // getting header row to get column index
			int columnIndex = -1;
			for (int i = 0; i <= row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().trim().equals(colName)) {
					columnIndex = i;
					break;
				}
			}
			if (columnIndex == -1)
				return false;

			row = sheet.getRow(rowNum - 1);
			if (row == null)
				row = sheet.createRow(rowNum - 1);

			cell = row.getCell(columnIndex);
			if (cell == null)
				cell = row.createCell(columnIndex);

			cell.setCellValue(data);

			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	public boolean addSheet(String sheetName) {
		try {
			workbook.createSheet(sheetName);
			fileOut=new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean removeSheet(String sheetName) {
		try {
			int sheetIndex = workbook.getSheetIndex(sheetName);
			workbook.removeSheetAt(sheetIndex);
			fileOut=new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
			return true;
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean addColumn(String sheetName,String colName) {
		try {
		int sheetIndex=workbook.getSheetIndex(sheetName);
		if(sheetIndex==-1)return false;
		sheet=workbook.getSheet(sheetName);
		row =sheet.getRow(0);
		if(row==null) {
			row=sheet.createRow(0);
			cell=row.createCell(0);
		}else {
		int columnPosition= row.getLastCellNum();
		
		cell = row.createCell(columnPosition);
		}
		cell.setCellValue(colName);
		fileOut=new FileOutputStream(path);
		workbook.write(fileOut);
		fileOut.close();
		
		return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public int getRowCount(String sheetName) {
		int sheetIndex= workbook.getSheetIndex(sheetName);
		if(sheetIndex==-1) {
			return 0;
		}else {		
		
		sheet=workbook.getSheet(sheetName);
		int noOfrows=sheet.getLastRowNum()+1;
		return noOfrows;
		}
	}
	public int getColumnCount(String sheetName) {
		int sheetIndex = workbook.getSheetIndex(sheetName);
		if(sheetIndex==-1)return 0;	
		
			sheet=workbook.getSheet(sheetName);
			row=sheet.getRow(0);
			if(row==null) {
				return 0;
			}else {
			int noOfColums=row.getLastCellNum(); 
			return noOfColums;
			
		}
		
	}
	
//	public static void main(String[] args) {
//		NewExcelLibrary ne = new NewExcelLibrary();
//		//ne.addSheet("TestData123");
//		int num =ne.getColumnCount("TestData123");
//		System.out.println(num);
//	}

}