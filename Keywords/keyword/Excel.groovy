package keyword
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable


import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

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

class Excel {

	@Keyword
	def static writeData(String fileName,String sheetName,int rowNum,int cellNum,newDataCell){
		File file =new File("C://Hajiraa//ELL//Automation//Excel//"+fileName+".xlsx")
		FileInputStream stream =new FileInputStream(file)
		Workbook workbook=new XSSFWorkbook(stream)
		Sheet sheet=workbook.getSheet(sheetName)
		Row row=sheet.getRow(rowNum)
		Cell cell=row.createCell(cellNum)
		cell.setCellValue(newDataCell)
		FileOutputStream outputStream =new FileOutputStream("C://Hajiraa//ELL//Automation//Excel//"+fileName+".xlsx")
		workbook.write(outputStream)
		outputStream.close()
	}

	@Keyword
	def static readData(String fileName,String sheetName,int rowNum,int cellNum,String newDataCell){
		File file =new File("C://Hajiraa//ELL//Automation//Excel//"+fileName+".xlsx")
		FileInputStream stream =new FileInputStream(file)
		Workbook workbook=new XSSFWorkbook(stream)
		Sheet sheet=workbook.getSheet(sheetName)
		Row row=sheet.getRow(rowNum)
		Cell cell=row.getCell(cellNum)
	}
}