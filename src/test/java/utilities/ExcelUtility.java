package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility 
{
		public FileInputStream fi;
		public FileOutputStream fo;
		public XSSFWorkbook wb;
		public XSSFSheet sh;
		public XSSFRow row;
		public XSSFCell cell;
		public String path;
		
		public ExcelUtility(String path)
		{
			this.path=path;
		}
		
		public int getRowCount(String sheetName ) throws IOException
		{
			fi=new FileInputStream(path);
			wb=new XSSFWorkbook(fi);
			sh=wb.getSheet(sheetName);
			int rowCount=sh.getLastRowNum();
			wb.close();
			fi.close();
			return rowCount;
		}
		
		public int getCellCount(String sheetName, int rowNum ) throws IOException
		{
			fi=new FileInputStream(path);
			wb=new XSSFWorkbook(fi);
			sh=wb.getSheet(sheetName);
			row=sh.getRow(rowNum);
			int cellCount= row.getLastCellNum();
			wb.close();
			fi.close();
			return cellCount;
		}
		
		public String getCellData(String sheetName, int rowNum, int cellNum ) throws IOException
		{
			fi=new FileInputStream(path);
			wb=new XSSFWorkbook(fi);
			sh=wb.getSheet(sheetName);
			row=sh.getRow(rowNum);
			cell=row.getCell(cellNum);
			String cellData;
			try
			{
				cellData=cell.toString();
			}
			catch(Exception e)
			{
				cellData="";
			}
			wb.close();
			fi.close();
			return cellData;
		}

		public void setCellData(String sheetName, int rowNum, int cellNum, String columnData ) throws IOException
		{
			fi=new FileInputStream(path);
			wb=new XSSFWorkbook(fi);
			sh=wb.getSheet(sheetName);
			row=sh.getRow(rowNum);
			cell=row.createCell(cellNum);
			cell.setCellValue(columnData);
			
			fo=new FileOutputStream(path);
			wb.write(fo);
				
			wb.close();
			fi.close();
			fo.close();
		}
	}

