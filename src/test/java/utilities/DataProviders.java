package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders 
{
	@DataProvider(name="loginData")
	public String[][] getLoginData() throws IOException
	{
		String path=".\\testData\\Opencartlogindata.xlsx";
		ExcelUtility eu = new ExcelUtility(path);
		int nRows=eu.getRowCount("loginData");
		int nCols=eu.getCellCount("loginData", 1);
		
		String logindata[][]=new String[nRows][nCols];
		
		for(int i=1;i<=nRows;i++)
		{
			for(int j=0;j<nCols;j++)
			{
				logindata[i-1][j]=eu.getCellData("loginData", i, j);
			}
		}
		
		return logindata;
	}
}
