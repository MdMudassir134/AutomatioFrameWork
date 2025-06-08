/**
 * 
 */
package com.mystore.dataprovider;

import org.testng.annotations.DataProvider;

import com.mystore.utility.NewExcelLibrary;

/**
 * 
 */
public class DataProviders {

	NewExcelLibrary obj = new NewExcelLibrary();

	@DataProvider(name = "loginCredentials")
	public Object[][] getEmailCredentials() {
		// get no of rows
		int rowsVisible = obj.getRowCount("LoginCredentialsData");
		// get no of columns
		int colums = obj.getColumnCount("LoginCredentialsData");
		int rows = rowsVisible - 1;// as row index start from zero

		Object[][] data = new Object[rows][colums];
		for (int i = 0; i < rows; i++) { // iterating through each row
			for (int j = 0; j < colums; j++) { // iterating through each coulmn in row
				data[i][j] = obj.getCellData("LoginCredentialsData", j + 1, i + 2);//
				// j+1 - coz columns should start form 1, i+2 as i cant be zero and should
				// ignore first column

			}
		}

		return data;
	}
	@DataProvider(name = "invalidLoginCrendentials")
	public Object[][] invalidSignInCrenditials(){
		int visibleRows = obj.getRowCount("InvaildLoginCredentials");
		int rows = visibleRows-1;
		int columns = obj.getColumnCount("InvaildLoginCredentials");
		Object[][] data = new Object [rows][columns];
		for(int i=0;i<rows;i++) {
			for(int j=0;j<columns;j++) {
				data[i][j]=obj.getCellData("InvaildLoginCredentials", j+1, i+2);
				
			}
		}
		
		return data;
	}
	

}
