import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By
import org.openqa.selenium.By.ByXPath as ByXPath
import org.openqa.selenium.TakesScreenshot as TakesScreenshot
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import java.io.File as File
import java.io.IOException as IOException
import org.apache.poi.ss.usermodel.Row as Row
import org.apache.commons.io.FileUtils as FileUtils
import org.apache.poi.ss.usermodel.Cell as Cell
import org.apache.poi.ss.usermodel.Sheet as Sheet
import org.apache.poi.ss.usermodel.Workbook as Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook as XSSFWorkbook
import java.io.FileOutputStream as FileOutputStream
import java.io.FileInputStream as FileInputStream
import java.io.FileNotFoundException as FileNotFoundException
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
import org.openqa.selenium.interactions.Actions as Actions
import org.openqa.selenium.Keys as Keys
import java.awt.Desktop.Action as Action
import java.awt.Robot as Robot
import java.awt.event.KeyEvent as KeyEvent
import java.io.*
import java.util.*
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil


//Suite
//Scroll to Top of the screen
WebDriver driver = DriverFactory.getWebDriver()

Actions actions = new Actions(driver).sendKeys(Keys.HOME).perform()

WebUI.click(findTestObject('Add Instrument/span_Instruments'))

WebUI.click(findTestObject('Add Instrument/button_Add Instrument Type'))

DateFormat dateFormat = new SimpleDateFormat("yyyy/dd/MM HH:mm:ss");

Date date = new Date();

String a= dateFormat.format(date);

println(a);

b = ' - '

Ins = ((findTestData('Add Inst-Type Data').getValue('InsType_Name', 1) + b) + a)

WebUI.setText(findTestObject('Add Instrument/InstrumentTypeName'), Ins)

keyword.Excel.writeData("ELL_Add Instrument & Type","Add Inst-Type", 1, 1, Ins)

println(Ins)

KeywordUtil.logInfo(Ins)

//Hard code
WebUI.setText(findTestObject('Add Instrument/input_Manufacturer'),"Siemens")
//Hard code
WebUI.setText(findTestObject('Add Instrument/input_Model'),'A-01')

WebUI.click(findTestObject('Add Instrument/button_Next'))

WebUI.click(findTestObject('Add Instrument/button_Skip'))

WebUI.click(findTestObject('Add Instrument/span_Add Maintenance Task'))

WebUI.setText(findTestObject('Add Instrument/input_Maintenance Task'), findTestData('Add Inst-Type Data').getValue('Task Name', 1))

R3 = WebUI.getAttribute(findTestObject('Add Instrument/input_Maintenance Task'), 'value')
println(R3)

WebUI.verifyEqual(findTestData('Add Inst-Type Data').getValue('Task Name', 1), R3)

WebUI.selectOptionByLabel(findTestObject('Add Instrument/FrequencyList'), findTestData('Add Inst-Type Data').getValue('Frequency', 1), true)

WebUI.verifyOptionPresentByLabel(findTestObject('Add Instrument/FrequencyList'), findTestData('Add Inst-Type Data').getValue('Frequency', 1), false,0)

WebUI.click(findTestObject('Add Instrument/button_Save Task'))

WebUI.click(findTestObject('Add Instrument/span_Add Maintenance Task'))

WebUI.setText(findTestObject('Add Instrument/input_Maintenance Task'), findTestData('Add Inst-Type Data').getValue('Task Name', 2))

R4 = WebUI.getAttribute(findTestObject('Add Instrument/input_Maintenance Task'), 'value')
println(R4)

WebUI.verifyEqual(findTestData('Add Inst-Type Data').getValue('Task Name', 2), R4)

WebUI.click(findTestObject('Add Instrument/Fixed'))

WebUI.selectOptionByLabel(findTestObject('Add Instrument/FixedFreq'), findTestData('Add Inst-Type Data').getValue('Frequency', 2), true)

//WebUI.verifyOptionPresentByLabel(findTestData('Add Instrument/FixedFreq'), findTestData('Add Inst-Type Data').getValue('Frequency', 2),false,0)

WebUI.click(findTestObject('Add Instrument/button_Add Data Field'))

JavascriptExecutor exe = ((driver) as JavascriptExecutor)

WebElement elementToScroll1 = driver.findElement(By.xpath('//span[text()=\'Field Label\']'))

exe.executeScript('arguments[0].scrollIntoView(true)', elementToScroll1)

WebUI.setText(findTestObject('Add Instrument/input_Field Label'), findTestData('Add Inst-Type Data').getValue('FieldLable', 2))

R5 = WebUI.getAttribute(findTestObject('Add Instrument/input_Field Label'), 'value')
println(R5)

WebUI.verifyEqual(findTestData('Add Inst-Type Data').getValue('FieldLable', 2), R5)

WebUI.selectOptionByLabel(findTestObject('Add Instrument/select_FieldType_Definedvalue'), findTestData('Add Inst-Type Data').getValue('FieldType', 2), true)

WebUI.verifyOptionPresentByLabel(findTestObject('Add Instrument/select_FieldType_Definedvalue'), findTestData('Add Inst-Type Data').getValue('FieldType', 2), false,0)

WebUI.setText(findTestObject('Add Instrument/input_Validate Field_txtDefinedvalue'), findTestData('Add Inst-Type Data').getValue('ValidateField', 2))

R6 = WebUI.getAttribute(findTestObject('Add Instrument/input_Validate Field_txtDefinedvalue'), 'value')
println(R6)

WebUI.verifyEqual(findTestData('Add Inst-Type Data').getValue('ValidateField', 2), R6)

WebUI.click(findTestObject('Add Instrument/button_Add More Validation'))

WebUI.setText(findTestObject('Add Instrument/input_remove_txtDefinedvalue'), findTestData('Add Inst-Type Data').getValue('ValidateField', 3))

R7 = WebUI.getAttribute(findTestObject('Add Instrument/input_remove_txtDefinedvalue'), 'value')

println(R7)

WebUI.verifyEqual(findTestData('Add Inst-Type Data').getValue('ValidateField', 3), R7)

WebUI.click(findTestObject('Add Instrument/input_Acceptable_Checkbox'))

WebUI.click(findTestObject('Add Instrument/button_Save Task'))

WebUI.click(findTestObject('Add Instrument/span_Add Maintenance Task'))

WebUI.setText(findTestObject('Add Instrument/input_Maintenance Task'), findTestData('Add Inst-Type Data').getValue('Task Name', 4))

R3 = WebUI.getAttribute(findTestObject('Add Instrument/input_Maintenance Task'), 'value')
println(R3)

WebUI.verifyEqual(findTestData('Add Inst-Type Data').getValue('Task Name', 4), R3)

WebUI.selectOptionByLabel(findTestObject('Add Instrument/FrequencyList'), findTestData('Add Inst-Type Data').getValue('Frequency', 4), true)

WebUI.verifyOptionPresentByLabel(findTestObject('Add Instrument/FrequencyList'), findTestData('Add Inst-Type Data').getValue('Frequency', 4), false,0)

WebUI.click(findTestObject('Add Instrument/button_Save Task'))

WebUI.click(findTestObject('Add Instrument/span_Add Maintenance Task'))

WebUI.setText(findTestObject('Add Instrument/input_Maintenance Task'), findTestData('Add Inst-Type Data').getValue('Task Name', 5))

R8 = WebUI.getAttribute(findTestObject('Add Instrument/input_Maintenance Task'), 'value')

println(R8)

WebUI.verifyEqual(findTestData('Add Inst-Type Data').getValue('Task Name',5), R8)

WebUI.selectOptionByLabel(findTestObject('Add Instrument/FrequencyList'), findTestData('Add Inst-Type Data').getValue('Frequency', 5), true)

WebUI.verifyOptionPresentByLabel(findTestObject('Add Instrument/FrequencyList'), findTestData('Add Inst-Type Data').getValue('Frequency', 5), false,0)

WebUI.click(findTestObject('Add Instrument/button_Add Data Field'))

WebUI.click(findTestObject('Add Instrument/chkrequired'))

WebUI.setText(findTestObject('Add Instrument/input_Field Label'), findTestData('Add Inst-Type Data').getValue('FieldLable', 5))

R9 = WebUI.getAttribute(findTestObject('Add Instrument/input_Field Label'), 'value')

println(R9)

WebUI.verifyEqual(findTestData('Add Inst-Type Data').getValue('FieldLable', 5), R9)

WebUI.selectOptionByLabel(findTestObject('Add Instrument/select_FieldType_Definedvalue'), findTestData('Add Inst-Type Data').getValue('FieldType', 5), true)

WebUI.verifyOptionPresentByLabel(findTestObject('Add Instrument/select_FieldType_Definedvalue'), findTestData('Add Inst-Type Data').getValue('FieldType', 5), false,0)

WebUI.click(findTestObject('Add Instrument/button_Save Task'))

WebUI.click(findTestObject('Add Instrument/button_Finish'))

WebUI.click(findTestObject('Add Instrument/button_Yes'))