package com.infa.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {
	
	private String fileName;
	private String sheetName;
	
	public ExcelReader(String fileName,String sheet){
		this.fileName = fileName;
		this.sheetName = sheet;
	}
	
	public HashMap<String,HashMap<String,String>> readExcelData() throws FileNotFoundException{
		HashMap<String,HashMap<String,String>> readData = new HashMap<String,HashMap<String,String>>();
		FileInputStream inp = new FileInputStream(fileName);
		Workbook workbook = null;
		try {
			workbook = WorkbookFactory.create(inp);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {			
			e.printStackTrace();
		}  
		Sheet sheet =workbook.getSheet(sheetName);
		System.out.println(sheet.getLastRowNum());
		
		Row row=null;
		Cell cell=null;
		Row nRow = sheet.getRow(0);
		Iterator<?> rows = sheet.rowIterator();
		rows.next();
		try {
	
		while(rows.hasNext()) {
			row = (Row) rows.next();
			HashMap<String,String> data = new HashMap<String,String>();
			String header_New = null;
			for ( int i=0; i<row.getLastCellNum(); i++) {
				String value = null;
				cell = row.getCell(i);
				if(cell.getCellType() == Cell.CELL_TYPE_STRING){
					value = cell.getStringCellValue();
				}else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
					value = String.valueOf(cell.getNumericCellValue());
				}else if(cell.getCellType()==Cell.CELL_TYPE_BOOLEAN){
					value = String.valueOf(cell.getBooleanCellValue());
				}else {
					
				}
				
				//System.out.println("***********************");
				String header = nRow.getCell(i).getStringCellValue();
				if(header.contentEquals("Module") || header.contentEquals("TestCase Name")) {
					header_New = row.getCell(i).getStringCellValue();
				}
				data.put(header, value);
			}
			try {
				readData.put(header_New,data);
			}catch(Exception ex) {
				
			}
					
		}
		}catch(Exception ex) {
			
		}
		
		
		//System.out.println(readData);

		
		
		
		return readData;
		
	}
	
	public static void main(String[] args) {
		ExcelReader ex = new ExcelReader("C:\\1040\\TestData.xlsx","Resource");
		try {
			System.out.println(ex.readExcelData());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
