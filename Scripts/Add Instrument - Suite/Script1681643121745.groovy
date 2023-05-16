import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import java.time.LocalDateTime as LocalDateTime
import java.time.format.DateTimeFormatter as DateTimeFormatter
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
//Scroll to TOP of the Screen

WebDriver driver = DriverFactory.getWebDriver()

Actions actions=new Actions(driver).sendKeys(Keys.HOME).perform()

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


WebUI.click(findTestObject('Add Inst-Type/button_Add Instrument'))

WebUI.selectOptionByLabel(findTestObject('Add Inst-Type/Select_Site'), findTestData('Dept_Unit Data').getValue('Site', 1), true)

WebUI.verifyOptionPresentByLabel(findTestObject('Add Inst-Type/Select_Site'), findTestData('Dept_Unit Data').getValue('Site', 1), true, 0)

WebUI.selectOptionByLabel(findTestObject('Add Inst-Type/select Department Core Lab'), findTestData('Dept_Unit Data').getValue('Created-Dept Name', 1), true)

WebUI.verifyOptionPresentByLabel(findTestObject('Add Inst-Type/select Department Core Lab'), findTestData('Dept_Unit Data').getValue('Created-Dept Name', 1), true,0)

WebUI.selectOptionByLabel(findTestObject('Add Inst-Type/select_UnitCore Lab'), findTestData('Dept_Unit Data').getValue('Bench / Unit Name', 1), true)

WebUI.verifyOptionPresentByLabel(findTestObject('Add Inst-Type/select_UnitCore Lab'), findTestData('Dept_Unit Data').getValue('Bench / Unit Name', 1), true,0)

WebUI.setText(findTestObject('Add Inst-Type/input_Instrument Label'), findTestData('Add Inst-Type Data').getValue('Created-InsType_Name', 1))

//I2=WebUI.getAttribute(findTestObject('Add Inst-Type/input_Instrument Label','value'))
//
//println(I2)
//
//WebUI.verifyEqual(findTestData('Add Inst-Type Data').getValue('Created-InsType_Name', 1),I2)

WebUI.setText(findTestObject('Add Inst-Type/instrumentSerialNumber'),findTestData('Add Inst-Type Data').getValue('Instrument Serial Number', 1))

//I3=WebUI.getAttribute(findTestObject('Add Inst-Type/instrumentSerialNumber','value'))
//
//println(I3)
//
//WebUI.verifyEqual(findTestData('Add Inst-Type Data').getValue('Instrument Serial Number', 1),I3)

WebUI.scrollToElement(findTestObject('Add Instrument/scrollToSystemscheduled'),0)

WebUI.click(findTestObject('Add Instrument/input_fixedScheduled'))

WebUI.click(findTestObject('Add Instrument/SA_ON'))

WebUI.click(findTestObject('Add Instrument/SU_ON'))

WebUI.click(findTestObject('Add Instrument/button_Save Instrument'))

WebUI.click(findTestObject('Add Inst-Type/select_Logo'))

WebUI.delay(2)

WebUI.takeScreenshot()
