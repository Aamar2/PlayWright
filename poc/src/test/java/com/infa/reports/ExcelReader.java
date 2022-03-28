package com.infa.reports;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {

	private String inputFile;
	private String sheetName;

	public ExcelReader(String inputFile) throws IOException {
		this.inputFile = inputFile;
	}
		
	public List<String>	readAllCategoriesData() throws IOException{
	
		List<String> table = new ArrayList<String>();
		StringBuilder rowList;

		FileInputStream inp = new FileInputStream(inputFile);
		Workbook workbook = null;
		try {
			workbook = WorkbookFactory.create(inp);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {			
			e.printStackTrace();
		}  
		Sheet sheet =workbook.getSheet(sheetName);
		Row row=null;
		Cell cell=null;
		
		Iterator<?> rows = sheet.rowIterator();
		rows.next();  	//We don't want heading, so we move to next row 

		while(rows.hasNext()){
			
			row = (Row) rows.next();
			Iterator<?> cells = row.cellIterator();
			rowList=new StringBuilder();
			
			while (cells.hasNext())	{
				
				cell = (Cell) cells.next();
				if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
					rowList.append(cell.getStringCellValue()+"%%");
				}
				else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
					rowList.append((int)cell.getNumericCellValue()+"%%");
				}
				else if(cell.getCellType()==Cell.CELL_TYPE_BOOLEAN){
					rowList.append(cell.getBooleanCellValue()+"%%");
				}				
				else {  
					//U Can Handle Formula, Errors 					
				}
			}
			table.add(rowList.toString());
		}
		return table;
	}	

	public List<String> getData(){
		return null;
	}
	
	public List<String> readSheet(String sheetName) throws IOException  {
		this.sheetName = sheetName;
		List<String> table = null;

		try{
			table = readAllCategoriesData();
		}catch(IOException e){
			System.out.println("Exception in readSheet : " +e.getMessage());
		}			
		return table;			
	}
	
	public String readAttributeforGivenTermFromExcel(String termName, String attributeName) throws EncryptedDocumentException, InvalidFormatException, IOException {
		FileInputStream fs = new FileInputStream(inputFile);
		String attributeValue = null;
		int term = 0;
		int desc = 0;
		Workbook wb = null;
		Row row = null;
		try {
			wb = WorkbookFactory.create(fs);
		} catch (Exception ex) {
			System.out.println("blah blah blah");

		}

		org.apache.poi.ss.usermodel.Sheet sh = wb.getSheet("Terms");
		row = sh.getRow(0);
		int firstColumn = row.getFirstCellNum();
		int lastColumn = row.getLastCellNum();
		for (int i = firstColumn; i < lastColumn ; i++) {
			Cell cell = row.getCell(i);
			if (cell.getStringCellValue().equals("Term Name")) {
				term = i;
			} else if (cell.getStringCellValue().equals(attributeName)) {
				desc = i;
			}
		}

		System.out.println(term + "and" + desc);
		
		for (int i = 1;i <= sh.getLastRowNum();i++){
			row = sh.getRow(i);
			Cell cell = row.getCell(term);
			if(cell.getStringCellValue().equals(termName)){
				attributeValue = row.getCell(desc).getStringCellValue();
			}
		}
		System.out.println(attributeValue);
		return attributeValue;

	}


}
