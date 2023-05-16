import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date;
import java.time.LocalDate as LocalDate
import java.time.LocalDateTime as LocalDateTime
import java.time.LocalTime
import java.time.ZoneId as ZoneId
import java.time.format.DateTimeFormatter as DateTimeFormatter

import javax.swing.text.DateFormatter

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
WebUI.setText(findTestObject('Add Instrument/input_Manufacturer'),"Lot Manufacture")

//Hard code
WebUI.setText(findTestObject('Add Instrument/input_Model'),'LO-01')

WebUI.click(findTestObject('Add Instrument/button_Next'))

WebUI.click(findTestObject('LOT/click-addReagentControlBtn'))

WebUI.setText(findTestObject('LOT/enter-LotName'),findTestData('Lot Data').getValue('Lot Name', 1))

//Hardcode
WebUI.setText(findTestObject('LOT/enter-Lot Notes'),'Checking Lot Notes')

WebUI.click(findTestObject('LOT/btn-Save Lot'))

//Create Normal --->LOT ---> [HARDCODED]
WebUI.click(findTestObject('Add Instrument/span_Add Maintenance Task'))

WebUI.setText(findTestObject('Add Instrument/input_Maintenance Task'), findTestData('Lot Data').getValue('Lot-Task Name', 1))

MTName_01 = WebUI.getAttribute(findTestObject('Add Instrument/input_Maintenance Task'), 'value')
println(MTName_01)

WebUI.verifyEqual(findTestData('Lot Data').getValue('Lot-Task Name', 1), MTName_01)

//Task - 1 ----> Scheduled Frequency (with 3 Data field) 
WebUI.selectOptionByLabel(findTestObject('Add Instrument/FrequencyList'), findTestData('Lot Data').getValue('Lot-Frequency', 1), true)

WebUI.verifyOptionPresentByLabel(findTestObject('Add Instrument/FrequencyList'), findTestData('Lot Data').getValue('Lot-Frequency', 1), false,0)

//Link LOT
WebUI.selectOptionByLabel(findTestObject('LOT/Link-lotList'),findTestData('Lot Data').getValue('Lot Name', 1), false)

WebUI.verifyOptionPresentByLabel(findTestObject('LOT/Link-lotList'), findTestData('Lot Data').getValue('Lot Name', 1), false,0)

//Add Data Field (Validations)
WebUI.click(findTestObject('Add Instrument/button_Add Data Field'))

WebUI.scrollToElement(findTestObject('LOT/scrollIntoField Label'), 0)

//Field Label ---> [Text] ---> [HARDCODED]
WebUI.setText(findTestObject('LOT/input_Field Label_Number'),'T1-PH Value 1')

//Field Type 
WebUI.selectOptionByValue(findTestObject('LOT/select_Text_Number'),'1',true)

WebUI.click(findTestObject('LOT/button_Add Data Field'))

//Field Label ---> [Text Area] ---> [HARDCODED]
WebUI.setText(findTestObject('LOT/input_Field Label_Number'),'T1-PH Value 2')

//Field Type ---> [HARDCODED]
WebUI.selectOptionByValue(findTestObject('LOT/select_Text_Number'),'2',true)

WebUI.click(findTestObject('LOT/button_Add Data Field'))

//Field Label ---> [Number]
WebUI.setText(findTestObject('LOT/input_Field Label_Number'),'T1-PH Value 3')

//Field Type
WebUI.selectOptionByValue(findTestObject('LOT/select_Text_Number'),'3',true)

WebUI.click(findTestObject('LOT/button_Add Data Field'))

//Field Label ---> [File] ---> [HARDCODED]
WebUI.setText(findTestObject('LOT/input_Field Label_Number'),'T1-PH Value 4')

//Field Type
WebUI.selectOptionByValue(findTestObject('LOT/select_Text_Number'),'5',true)

WebUI.click(findTestObject('LOT/Lot-Save Task'))

//Task - 2 ----> CLONE---> LOT for verification  ---> [HARDCODED]
WebUI.click(findTestObject('LOT/Lot-Clone'))

WebUI.scrollToElement(findTestObject('LOT/scrollIntoField Label'), 0)

Clone_MTName_02 = WebUI.getAttribute(findTestObject('Add Instrument/input_Maintenance Task'), 'value')
println(Clone_MTName_02)

WebUI.verifyEqual(findTestData('Lot Data').getValue('Lot-Task Name', 1)+' (Copy)', Clone_MTName_02)

WebUI.verifyOptionPresentByLabel(findTestObject('Add Instrument/FrequencyList'), findTestData('Lot Data').getValue('Lot-Frequency', 1), false,0)

WebUI.verifyOptionPresentByLabel(findTestObject('LOT/Link-lotList'), findTestData('Lot Data').getValue('Lot Name', 1), false,0)

//Clone Field Label ---> [Text]
clone_DF_ValT1=WebUI.getAttribute(findTestObject('LOT/clone_DF -T1'), 'value')

WebUI.verifyEqual('T1-PH Value 1',clone_DF_ValT1)

WebUI.verifyOptionPresentByLabel(findTestObject('LOT/clone_FT-F1'),'Text', false,0)

//Clone Field Label ---> [Text Area]
clone_DF_ValT2=WebUI.getAttribute(findTestObject('LOT/clone_DF - T2'), 'value')

WebUI.verifyEqual('T1-PH Value 2',clone_DF_ValT2)

WebUI.verifyOptionPresentByLabel(findTestObject('LOT/clone_FT-F2'),'Text Area', false,0)

//Clone Field Label ---> [Number]
clone_DF_ValT3=WebUI.getAttribute(findTestObject('LOT/clone_DF - T3'), 'value')

WebUI.verifyEqual('T1-PH Value 3',clone_DF_ValT3)

WebUI.verifyOptionPresentByLabel(findTestObject('LOT/clone_FT-F3'),'Number', false,0)

//Clone Field Label ---> [File]
clone_DF_ValT4=WebUI.getAttribute(findTestObject('LOT/clone_DF - T4'), 'value')

WebUI.verifyEqual('T1-PH Value 4',clone_DF_ValT4)

WebUI.verifyOptionPresentByLabel(findTestObject('LOT/clone_FT-F4'),'File Attachment', false,0)

WebUI.click(findTestObject('LOT/Lot-Save Task'))

//Task - 3 ----> FIXED FREQUENCY
WebUI.click(findTestObject('Add Instrument/span_Add Maintenance Task'))

//Maintance Task Name
WebUI.setText(findTestObject('Add Instrument/input_Maintenance Task'), findTestData('Lot Data').getValue('Lot-Task Name', 2))

MTName_03= WebUI.getAttribute(findTestObject('Add Instrument/input_Maintenance Task'), 'value')
println(MTName_03)

WebUI.verifyEqual(findTestData('Lot Data').getValue('Lot-Task Name', 2), MTName_03)

WebUI.click(findTestObject('Add Instrument/Fixed'))

//Select Frequency -----> FIXED
WebUI.selectOptionByLabel(findTestObject('Add Instrument/FixedFreq'), findTestData('Lot Data').getValue('Lot-Frequency', 2), true)

WebUI.verifyOptionPresentByLabel(findTestObject('Add Instrument/FixedFreq'), findTestData('Lot Data').getValue('Lot-Frequency', 2),false,0)

//Link LOT
WebUI.selectOptionByLabel(findTestObject('LOT/Link-lotList'),findTestData('Lot Data').getValue('Lot Name', 1), false)

WebUI.verifyOptionPresentByLabel(findTestObject('LOT/Link-lotList'), findTestData('Lot Data').getValue('Lot Name', 1), false,0)

//Add Data Field (Validations) 
WebUI.click(findTestObject('LOT/button_Add Data Field'))

//Field Label ---> [Number] ---> [HARDCODED]
WebUI.setText(findTestObject('LOT/input_Field Label_Number'),'T2-PH Value 1')

//Field Type
WebUI.selectOptionByValue(findTestObject('LOT/select_Text_Number'),'3',true)

WebUI.click(findTestObject('LOT/button_Add Data Field'))

//Field Label ---> [Number] ---> [HARDCODED]
WebUI.setText(findTestObject('LOT/input_Field Label_Number'),'T2-PH Value 2')

//Field Type
WebUI.selectOptionByValue(findTestObject('LOT/select_Text_Number'),'3',true)

//To uncheck the Required field
WebUI.click(findTestObject('LOT/click-RequiredField'))

//To uncheck the Validate field
WebUI.click(findTestObject('LOT/click-ValidateField'))

//Add Data field (Validation) ----> 2
WebUI.click(findTestObject('LOT/button_Add Data Field'))

//Field Label ---> [Defined Value] ---> [HARDCODED]
WebUI.setText(findTestObject('LOT/input_Text_Defined Value'),'T2-PH Value 3')

//Field Type
WebUI.selectOptionByValue(findTestObject('LOT/select__Text_Defined Value'),'4', true)

//scroll
WebUI.scrollToElement(findTestObject('LOT/scroll-Add Data field'), 0)

//Validation 01 ---> [HARDCODED]
WebUI.setText(findTestObject('Lot/txt_Validation'),'5')

//Validation 02 ---> [HARDCODED]
WebUI.click(findTestObject('LOT/click-AddMoreValidation'))

WebUI.setText(findTestObject('Lot/txt_Validation'),'15')

//Validation 03 ---> [HARDCODED]
WebUI.click(findTestObject('LOT/click-AddMoreValidation'))

WebUI.setText(findTestObject('Lot/txt_Validation'),'25')

WebUI.click(findTestObject('LOT/Lot-Save Task'))

//Task - 4 Fixed Scheduled
WebUI.click(findTestObject('Add Instrument/span_Add Maintenance Task'))

//Maintance Task Name
WebUI.setText(findTestObject('Add Instrument/input_Maintenance Task'), findTestData('Lot Data').getValue('Lot-Task Name', 3))

MTName_04= WebUI.getAttribute(findTestObject('Add Instrument/input_Maintenance Task'), 'value')
println(MTName_04)

WebUI.verifyEqual(findTestData('Lot Data').getValue('Lot-Task Name', 3), MTName_04)

WebUI.click(findTestObject('Add Instrument/Fixed'))

//Select Frequency -----> FIXED
WebUI.selectOptionByLabel(findTestObject('Add Instrument/FixedFreq'), findTestData('Lot Data').getValue('Lot-Frequency', 3), true)

WebUI.verifyOptionPresentByLabel(findTestObject('Add Instrument/FixedFreq'), findTestData('Lot Data').getValue('Lot-Frequency', 3),false,0)

//scroll
WebUI.scrollToElement(findTestObject('LOT/scroll-Add Data field'), 0)

//Use a LOT
WebUI.click(findTestObject('LOT/click-Use a LOT'))

//Add Data Field (Validations) 
WebUI.click(findTestObject('LOT/button_Add Data Field'))

//Field Label ---> [HARDCODED]
WebUI.setText(findTestObject('LOT/input_Field Label_Number'),'T3-PH Value 1')

//Field Type ---> [HARDCODED]
WebUI.selectOptionByValue(findTestObject('LOT/select_Text_Number'),'3',true)

WebUI.selectOptionByValue(findTestObject('LOT/Greater Than-Value'),'1', true)

WebUI.setText(findTestObject('LOT/txtbox-greaterThan'),'12')

WebUI.click(findTestObject('LOT/click-AddMoreValidation'))

WebUI.selectOptionByLabel(findTestObject('LOT/condition'),'AND', false)

WebUI.selectOptionByValue(findTestObject('LOT/Less Than-Value'),'2', true)
	
WebUI.setText(findTestObject('LOT/txtbox-lessThan'),'20')

//Task - 4 Fixed Scheduled -----> Clone Verification ---> [HARDCODED]
WebUI.click(findTestObject('LOT/DF_Clone'))

clone_DFText=WebUI.getAttribute(findTestObject('LOT/clone_DF'), 'value')

WebUI.verifyEqual('T3-PH Value 1'+ ' (Copy)',clone_DFText)

WebUI.verifyOptionSelectedByLabel(findTestObject('LOT/clone_FT-F2'),'Number', false, 0)

WebUI.verifyOptionSelectedByLabel(findTestObject('LOT/clone_greaterThan'),'Greater Than', false, 0)

clone_VF01 = WebUI.getAttribute(findTestObject('LOT/txtbox-greaterThan'), 'value')
println(clone_VF01)

WebUI.verifyEqual('12',clone_VF01)

WebUI.verifyOptionSelectedByLabel(findTestObject('LOT/clone_condition'),'AND', false, 0)

WebUI.verifyOptionSelectedByLabel(findTestObject('LOT/clone_lessThan'),'Less Than', false, 0)

clone_VF02 = WebUI.getAttribute(findTestObject('LOT/txtbox-lessThan'), 'value')
println(clone_VF02)

WebUI.verifyEqual('20',clone_VF02)

WebUI.click(findTestObject('LOT/Lot-Save Task'))

//Task - 5 ----> Select Frequency -----> Unscheduled
WebUI.click(findTestObject('Add Instrument/span_Add Maintenance Task'))

//Maintance Task Name
WebUI.setText(findTestObject('Add Instrument/input_Maintenance Task'), findTestData('Lot Data').getValue('Lot-Task Name', 4))

MTName_05= WebUI.getAttribute(findTestObject('Add Instrument/input_Maintenance Task'), 'value')
println(MTName_05)

WebUI.verifyEqual(findTestData('Lot Data').getValue('Lot-Task Name', 4), MTName_05)

WebUI.selectOptionByLabel(findTestObject('Add Instrument/FrequencyList'), findTestData('Lot Data').getValue('Lot-Frequency', 4), true)

WebUI.verifyOptionPresentByLabel(findTestObject('Add Instrument/FrequencyList'), findTestData('Lot Data').getValue('Lot-Frequency', 4), false,0)

//Link LOT
WebUI.selectOptionByLabel(findTestObject('LOT/Link-lotList'),findTestData('Lot Data').getValue('Lot Name', 1), false)

WebUI.verifyOptionPresentByLabel(findTestObject('LOT/Link-lotList'), findTestData('Lot Data').getValue('Lot Name', 1), false,0)

//Add Data Field (Validations)
WebUI.click(findTestObject('LOT/button_Add Data Field'))

//Field Label
WebUI.setText(findTestObject('LOT/input_Field Label_Number'),'T4-PH Value 1')

//Field Type
WebUI.selectOptionByValue(findTestObject('LOT/select_Text_Number'),'3',true)

WebUI.click(findTestObject('LOT/Lot-Save Task'))

//Task - 6 ----> Select Frequency -----> Scheduled
WebUI.click(findTestObject('Add Instrument/span_Add Maintenance Task'))

//Maintance Task Name
WebUI.setText(findTestObject('Add Instrument/input_Maintenance Task'), findTestData('Lot Data').getValue('Lot-Task Name', 5))

MTName_06= WebUI.getAttribute(findTestObject('Add Instrument/input_Maintenance Task'), 'value')
println(MTName_06)

WebUI.verifyEqual(findTestData('Lot Data').getValue('Lot-Task Name', 5), MTName_06)

WebUI.selectOptionByLabel(findTestObject('Add Instrument/FrequencyList'), findTestData('Lot Data').getValue('Lot-Frequency', 5), true)

WebUI.verifyOptionPresentByLabel(findTestObject('Add Instrument/FrequencyList'), findTestData('Lot Data').getValue('Lot-Frequency', 5), false,0)

//Use a LOT
WebUI.click(findTestObject('LOT/click-Use a LOT'))

//Add Data Field (Validations)
WebUI.click(findTestObject('LOT/button_Add Data Field'))

//Field Label ---> [HARDCODED]
WebUI.setText(findTestObject('LOT/input_Field Label_Number'),'T4-PH Value 1')

//Field Type
WebUI.selectOptionByValue(findTestObject('LOT/select_Text_Number'),'4',true)

WebUI.setText(findTestObject('LOT/VF_DV_Yes'),'Yes')

WebUI.click(findTestObject('LOT/Acceptable checkBox'))

WebUI.click(findTestObject('LOT/click-AddMoreValidation'))

WebUI.setText(findTestObject('LOT/VF_DV_No'),'No')

WebUI.click(findTestObject('LOT/Lot-Save Task'))

//Task - 7 Fixed Scheduled (Without data field)
WebUI.click(findTestObject('Add Instrument/span_Add Maintenance Task'))

//Maintance Task Name
WebUI.setText(findTestObject('Add Instrument/input_Maintenance Task'), findTestData('Lot Data').getValue('Lot-Task Name', 6))

MTName_07= WebUI.getAttribute(findTestObject('Add Instrument/input_Maintenance Task'), 'value')
println(MTName_07)

WebUI.verifyEqual(findTestData('Lot Data').getValue('Lot-Task Name', 6), MTName_07)

WebUI.click(findTestObject('Add Instrument/Fixed'))

//Select Frequency -----> FIXED
WebUI.selectOptionByLabel(findTestObject('Add Instrument/FixedFreq'), findTestData('Lot Data').getValue('Lot-Frequency', 6), true)

WebUI.verifyOptionPresentByLabel(findTestObject('Add Instrument/FixedFreq'), findTestData('Lot Data').getValue('Lot-Frequency', 6),false,0)

//Link LOT
WebUI.selectOptionByLabel(findTestObject('LOT/Link-lotList'),findTestData('Lot Data').getValue('Lot Name', 1), false)

WebUI.verifyOptionPresentByLabel(findTestObject('LOT/Link-lotList'), findTestData('Lot Data').getValue('Lot Name', 1), false,0)

WebUI.click(findTestObject('LOT/Lot-Save Task'))

////////////////////////////////////////////////////

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

//////////////////////////////////

//Select the created Instrument
WebUI.scrollToElement(findTestObject('Add Instrument/scrollToTop'), 0)

WebUI.setText(findTestObject('Add Inst-Type/input_SearchString'),findTestData('Add Inst-Type Data').getValue('Created-InsType_Name', 1))

WebUI.delay(2)

createdInstType=findTestData('Add Inst-Type Data').getValue('Created-InsType_Name', 1)

List<String>InstType=driver.findElements(By.xpath("//div[@class='col']//span"))

for (int i = 0; i < InstType.size(); i++) {
	
	WebElement x1 = InstType.get(i)

	String x2 = x1.getText()
	println(x2)

	if (createdInstType.equals(x2)) {
		x1.click()
	}
}

// Add a Instrument 
WebUI.click(findTestObject('Add Inst-Type/button_Add Instrument'))

WebUI.selectOptionByLabel(findTestObject('Add Inst-Type/Select_Site'), findTestData('Dept_Unit Data').getValue('Site', 1), true)

WebUI.verifyOptionPresentByLabel(findTestObject('Add Inst-Type/Select_Site'), findTestData('Dept_Unit Data').getValue('Site', 1), true, 0)

WebUI.selectOptionByLabel(findTestObject('Add Inst-Type/select Department Core Lab'), findTestData('Dept_Unit Data').getValue('Created-Dept Name', 1), true)

WebUI.verifyOptionPresentByLabel(findTestObject('Add Inst-Type/select Department Core Lab'), findTestData('Dept_Unit Data').getValue('Created-Dept Name', 1), true,0)

WebUI.selectOptionByLabel(findTestObject('Add Inst-Type/select_UnitCore Lab'), findTestData('Dept_Unit Data').getValue('Bench / Unit Name', 1), true)

WebUI.verifyOptionPresentByLabel(findTestObject('Add Inst-Type/select_UnitCore Lab'), findTestData('Dept_Unit Data').getValue('Bench / Unit Name', 1), true,0)

WebUI.setText(findTestObject('Add Inst-Type/input_Instrument Label'), findTestData('Add Inst-Type Data').getValue('Created-InsType_Name', 1))

WebUI.setText(findTestObject('Add Inst-Type/instrumentSerialNumber'),findTestData('Add Inst-Type Data').getValue('Instrument Serial Number', 1))

WebUI.scrollToElement(findTestObject('Add Instrument/scrollToSystemscheduled'),0)

WebUI.click(findTestObject('Add Instrument/input_fixedScheduled'))

WebUI.click(findTestObject('Add Instrument/SA_ON'))

WebUI.click(findTestObject('Add Instrument/SU_ON'))

WebUI.click(findTestObject('Add Instrument/button_Save Instrument'))

WebUI.click(findTestObject('Add Inst-Type/select_Logo'))

WebUI.delay(2)

WebUI.takeScreenshot()

//4 Places to Edit
//1st place - Control/Reagents -----> Verification
WebUI.click(findTestObject('LOT/click_ControlandReagents'))

WebUI.click(findTestObject('LOT/button-EditLot'))

//Verification for LOT Notes
WebUI.verifyElementText(findTestObject('LOT/Verify-LogNotes'),'Checking Lot Notes')

// Edit Lot --> [HARDCODED]
WebUI.click(findTestObject('LOT/btn_Add-New'))

WebUI.setText(findTestObject('LOT/txtBox-LotNo'), '123RD5')

timeZone = findTestData('Add Inst-Type Data').getValue('Log -TimeZone', 1)

//Get the Timezone Date
LocalDateTime dateTime = LocalDateTime.now(ZoneId.of(timeZone))

//Reduce days from the current date
LocalDateTime Days = dateTime.plusDays(10)
LocalDateTime DIU = dateTime.plusDays(9)
LocalDateTime pastDay = dateTime.minusDays(3)

// Format the DATE & TIME in MM/dd/yyyy & HH:mm format for Ready
DateTimeFormatter dtf = DateTimeFormatter.ofPattern('MM/dd/yyyy')

String expDate = Days.format(dtf)
println(expDate)

String DateOpened=dtf.format(dateTime)
println(DateOpened)

String DateReceived=pastDay.format(dtf)
println(DateReceived)

String DateInUse=DIU.format(dtf)
println(DateInUse)

// Edit Lot --> [HARDCODED]
WebUI.setText(findTestObject('LOT/txtBox-Expiration Date'),expDate)

WebUI.setText(findTestObject('LOT/txtBox-Opened Expiration'),expDate)

WebUI.setText(findTestObject('LOT/txtBox-Other Identifier'),'012345678910111213141516171819202122232425262728')

WebUI.setText(findTestObject('LOT/txtBox-Manufacture'),'Lot-L1')

WebUI.setText(findTestObject('LOT/txtBox-Description'),'Checking Text')

WebUI.setText(findTestObject('LOT/txtBox-Date Received'),DateReceived)

WebUI.setText(findTestObject('LOT/txtBox-Date Opened'),DateOpened)

WebUI.setText(findTestObject('LOT/txtBox-Date In Use'),DateOpened)

WebUI.setText(findTestObject('LOT/txtBox-DateDiscarded'),DateInUse)

WebUI.setText(findTestObject('LOT/txtBox-Product'),'ELL')

//Condition ---> EQUALS --> [HARDCODED]
WebUI.selectOptionByValue(findTestObject('LOT/Condition-Equals'),'4', true)

WebUI.setText(findTestObject('LOT/txtBox-Condition-Equals'),'22')

WebUI.click(findTestObject('LOT/checkBox-Acceptable'))

//Condition ---> BETWEEN --> [HARDCODED]
WebUI.selectOptionByValue(findTestObject('LOT/Condition-Between'),'3', true)

WebUI.setText(findTestObject('LOT/txtBox-Condition-Between'),'10')

WebUI.setText(findTestObject('LOT/txtBox-Condition-Between 2'),'20')

//Condition ---> Greater Than or Equal --> [HARDCODED]
WebUI.selectOptionByValue(findTestObject('LOT/Condition-Greater Than or Equal'),'6', true)

WebUI.setText(findTestObject('LOT/txtbox-greaterThan or Equal'),'10')

WebUI.click(findTestObject('LOT/btn-Add More Validation (control-Reagent)'))

// [OR] Condition ---> Less Than or Equal --> [HARDCODED]
WebUI.selectOptionByLabel(findTestObject('LOT/condition-OR'),'OR', false)

WebUI.selectOptionByValue(findTestObject('LOT/Condition-Less Than or Equal'),'7', true)

WebUI.setText(findTestObject('LOT/txtbox-lessThan or Equal'),'20')

// --------> File Attachment <----------
//WebUI.scrollToElement(findTestObject('LOT/scrollToFiles'), 0)
//
//filePath = findTestData('Add Inst-Type Data').getValue('Attached FilePath', 1)
//
//WebUI.uploadFile(findTestObject('LOT/file-btn_AddNew'),filePath)
//
//WebUI.setText(findTestObject('LOT/Note-FileTransfer'),'Checking File notes')
//
WebUI.click(findTestObject('LOT/btn-Save_Edit-control-Reagent'))

//SS for create LOT
WebUI.takeScreenshot()

//Close 1st 
WebUI.click(findTestObject('LOT/span_'))

//Close 2nd
WebUI.click(findTestObject('LOT/button_Cancel'))

