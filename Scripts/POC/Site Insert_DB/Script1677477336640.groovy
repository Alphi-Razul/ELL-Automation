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
import connectionDBKeyword.DB_SiteId_Connection
import excelWrite.Excel_Keyword
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
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import org.apache.poi.xssf.usermodel.XSSFWorkbook

//Connect the database
Connection con=DriverManager.getConnection("jdbc:mysql://ell-test-cluster.cluster-c3r5pldmtzx1.us-east-1.rds.amazonaws.com:3306/ell_qa_automation","masterTestUser","EllTestPassword123!!")

Statement statement=con.createStatement()

//To Read the data from excel
FileInputStream stream =new FileInputStream("F:\\ELL Testing\\ELL Automation\\Excel\\ELL_Site Creation_DB.xlsx")

//Create workbook
XSSFWorkbook wb=new XSSFWorkbook(stream)

//get the sheet 
Sheet sheet=wb.getSheet("DB_Site Creation")

//Take the last no.of rows in sheet
int row=sheet.getLastRowNum()

int k=1

//To write the created siteName in Updated Excel sheet
FileInputStream inputStream=new FileInputStream("F:\\ELL Testing\\ELL Automation\\Excel\\ELL_Updated Site Creation_DB.xlsx")

Workbook book=new XSSFWorkbook(inputStream)

Sheet sht=book.getSheet("DB_Updated Site Creation")

for(int r=1;r<=row;r++){
	
Row rows=sheet.getRow(r)
	
//Date & Time Format --------> to create UNIQUE Site
DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")

LocalDateTime now= LocalDateTime.now()

a = dtf.format(now)

println(a)

b = ' - '

//To get all the rows/cells(columns)from excel
	
	String siteName=rows.getCell(0).getStringCellValue() + b + a
	println (siteName)

	String address=rows.getCell(1).getStringCellValue()
	println (address)
	
	String city=rows.getCell(2).getStringCellValue()
	println (city)
	
	String state=rows.getCell(3).getStringCellValue()
	println (state)
	
	int zip=rows.getCell(4).getNumericCellValue()
	println (zip)
	
	int phoneNumber=rows.getCell(5).getNumericCellValue()
	println (phoneNumber)
	
	int followParentSchedule=rows.getCell(6).getNumericCellValue()
	println (followParentSchedule)
	
	String rollingCompliancePercent=rows.getCell(7).getStringCellValue()
	println (rollingCompliancePercent)
	
	//Automatically generate the ID
	int createdByUserId=rows.getCell(8).getNumericCellValue()
	println (createdByUserId)
	
	//Take DB Date & Time
	int createDateTime=rows.getCell(9).getNumericCellValue()
	println (createDateTime)
	
	//Automatically generate the ID
	int lastUpdatedByUserId=rows.getCell(10).getNumericCellValue()
	println (lastUpdatedByUserId)
	
	//Take DB Date & Time
	int lastUpdatedDateTime=rows.getCell(11).getNumericCellValue()
	println (lastUpdatedDateTime)
	
	//Automatically generate
	int active=rows.getCell(12).getNumericCellValue()
	println (active)
	
	int timeZoneId=rows.getCell(13).getNumericCellValue()
	println (timeZoneId)
	
	int countryId=rows.getCell(14).getNumericCellValue()
	println (countryId)
	
	String phoneCode=rows.getCell(15).getStringCellValue()
	println (phoneCode)

	//Execute ---> Query to insert the values in table -----> (Passing the values(datas) from EXCEL)
      statement.executeUpdate("INSERT into sites  VALUES ((siteId),'"+siteName+"','"+address+"','"+city+"','"+state+"','"+zip+"','"+phoneNumber+"','"+followParentSchedule+"',(rollingCompliancePercent),'"+createdByUserId+"', (select sysdate()),'"+lastUpdatedByUserId+"', (select sysdate()),'"+active+"','"+timeZoneId+"','"+countryId+"','"+phoneCode+"')")
	  
	  //Create Row [Before getRow --> Create Cell]
	   Row ro=sht.createRow(k)

	//Execute ---> Get the SiteId and siteName using Custom keyword
	 
      ResultSet rs=statement.executeQuery("Select siteId,siteName from sites where siteName ='"+siteName+"'")
     
	  rs.next()
 
	//Method used to get the value
	String siteNames = rs.getString("siteName")
	println(siteNames)
	
	//To write the siteName data in excel 
	sht.getRow(k).createCell(1).setCellValue(siteName)
	
	//Method used to get the value
    String siteId=rs.getString("siteId")
	println(siteId)
	
	//To write the siteId data in excel
	sht.getRow(k).createCell(2).setCellValue(siteId)
	sheet.getRow(k).getCell(16).setCellValue(siteId)
	k++

}

FileOutputStream outputStream=new FileOutputStream("F:\\ELL Testing\\ELL Automation\\Excel\\ELL_Updated Site Creation_DB.xlsx")
FileOutputStream OStream=new FileOutputStream("F:\\ELL Testing\\ELL Automation\\Excel\\ELL_Site Creation_DB.xlsx")

book.write(outputStream)
wb.write(OStream)

//close excel
wb.close()
stream.close()
con.close()

println("Successfully pass the data from excel")


