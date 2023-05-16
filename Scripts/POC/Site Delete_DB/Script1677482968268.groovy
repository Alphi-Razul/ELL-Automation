import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.googlecode.javacv.cpp.swscale
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import connectionDBKeyword.DB_Connection
import internal.GlobalVariable as GlobalVariable
import java.io.File as File
import java.io.IOException as IOException
import org.apache.poi.ss.usermodel.Row as Row
import org.apache.commons.io.FileUtils as FileUtils
import org.apache.poi.ss.usermodel.Cell as Cell
import org.apache.poi.ss.usermodel.Sheet as Sheet
import org.apache.poi.ss.usermodel.Workbook as Workbook
import org.apache.poi.xssf.usermodel.XSSFRow
import org.apache.poi.xssf.usermodel.XSSFWorkbook as XSSFWorkbook
import java.io.FileOutputStream as FileOutputStream
import java.io.FileInputStream as FileInputStream

import java.sql.Connection;
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.Statement

import org.apache.poi.xssf.usermodel.XSSFWorkbook


//Connect to DB
Connection con=DriverManager.getConnection("jdbc:mysql://ell-test-cluster.cluster-c3r5pldmtzx1.us-east-1.rds.amazonaws.com:3306/ell_qa_automation","masterTestUser","EllTestPassword123!!")

//Create Statement 
Statement statement=con.createStatement()

//To Read the data from excel
FileInputStream stream =new FileInputStream("F:\\ELL Testing\\ELL Automation\\Excel\\ELL_Updated Site Creation_DB.xlsx")

//Create workbook
XSSFWorkbook wb=new XSSFWorkbook(stream)

//get the sheet
Sheet sheet=wb.getSheet("DB_Updated Site Creation")

//Take the last no.of rows in sheet
int row=sheet.getLastRowNum()

//To get all the rows/cells(columns)from excel
for(int r=1;r<=row;r++){
	Row rows=sheet.getRow(r)

	String Updatedaddress=rows.getCell(0).getStringCellValue()
	println (Updatedaddress)

	String siteId=rows.getCell(2).getStringCellValue()
	println (siteId)
	
	//Execute ---> Delete Query 
	statement.executeUpdate("DELETE from sites where siteId='"+siteId+"'")

}
	
//close connection
wb.close()
stream.close()
con.close()

println("Successfully pass the data from excel")

