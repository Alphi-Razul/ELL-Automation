import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.ExcelData as ExcelData
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import keyword.ScrollIntoCreatedSite as ScrollIntoCreatedSite
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import java.text.DateFormat as DateFormat
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.Date as Date
import org.openqa.selenium.Rectangle as Rectangle
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By
import org.openqa.selenium.By.ByXPath as ByXPath
import org.openqa.selenium.ElementClickInterceptedException as ElementClickInterceptedException
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor
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
import java.awt.Robot as Robot
import java.awt.event.KeyEvent as KeyEvent
import java.io.*
import java.util.*
import java.util.concurrent.TimeUnit as TimeUnit

//Suite 
WebUI.delay(5)

WebUI.click(findTestObject('ELL Menu/Select_MenuIcon'))

WebUI.click(findTestObject('ELL Menu/a_ADMINISTRATION'))

WebUI.click(findTestObject('Site creation/button_Add Site'))

WebUI.click(findTestObject('Site creation/input_Site Name'))

DateFormat dateFormat = new SimpleDateFormat('yyyy/dd/MM HH:mm:ss')

Date date = new Date()

String a = dateFormat.format(date)

println(a)

b = ' - '

Site = ((findTestData('Site Data').getValue('Site Name', 1) + b) + a)

println(Site)

KeywordUtil.logInfo(Site)

keyword.Excel.writeData('ELL_Departments & Units', 'Add D&U', 1, 1, Site)

WebUI.setText(findTestObject('Site creation/input_Site Name'), Site)

s1 = WebUI.getAttribute(findTestObject('Site creation/input_Site Name'), 'value')

println(s1)

WebUI.verifyEqual(Site, s1)

A1 = findTestData('Site Data').getValue('Address', 1)

WebUI.setText(findTestObject('Site creation/input_Address'), A1)

A2 = WebUI.getAttribute(findTestObject('Site creation/input_Address'), 'value')

println(A2)

WebUI.verifyEqual(A1, A2)

c1 = findTestData('Site Data').getValue('City', 1)

WebUI.setText(findTestObject('Site creation/input_City_city'), c1)

c2 = WebUI.getAttribute(findTestObject('Site creation/input_City_city'), 'value')

WebUI.verifyEqual(c1, c2)

S5 = findTestData('Site Data').getValue('State', 1)

WebUI.setText(findTestObject('Site creation/input_State_state'), S5)

S6 = WebUI.getAttribute(findTestObject('Site creation/input_State_state'), 'value')

WebUI.verifyEqual(S5, S6)

z1 = findTestData('Site Data').getValue('Zip', 1)

WebUI.setText(findTestObject('Site creation/input_Zip_zip'), z1)

z2 = WebUI.getAttribute(findTestObject('Site creation/input_Zip_zip'), 'value')

WebUI.verifyEqual(z1, z2)

WebUI.selectOptionByLabel(findTestObject('Site creation/select_country'), findTestData('Site Data').getValue('Country', 
        1), true)

WebUI.verifyOptionPresentByLabel(findTestObject('Site creation/select_country'), findTestData('Site Data').getValue('Country', 
        1), false, 0)

WebUI.setText(findTestObject('Site creation/input_Phone_phone'), findTestData('Site Data').getValue('Phone', 1))

WebUI.selectOptionByLabel(findTestObject('Site creation/select_Timezone'), findTestData('Site Data').getValue('Timezone', 
        1), true)

WebUI.verifyOptionPresentByLabel(findTestObject('Site creation/select_Timezone'), findTestData('Site Data').getValue('Timezone', 
        1), false, 0)

WebUI.click(findTestObject('Site creation/input_system_Schedule'))

WebUI.click(findTestObject('Site creation/SA_ON'))

WebUI.click(findTestObject('Site creation/SU_ON'))

WebUI.click(findTestObject('Site creation/button_Save'))

WebUI.verifyElementPresent(findTestObject('Site creation/div_Success  Site added successfully'), 0)

c = WebUI.getText(findTestObject('Site creation/div_Success  Site added successfully'))

println(c)

KeywordUtil.logInfo(c)

WebUI.click(findTestObject('Site creation/div_click'))

WebDriver driver = DriverFactory.getWebDriver()

JavascriptExecutor executor = ((driver) as JavascriptExecutor)

List<String> li = driver.findElements(By.xpath('//div[@class=' + '\'col\']'))

int op = li.size()

println(op)

for (int i = 0; i < li.size(); i++) {
    WebElement x2 = li.get(i)

    String x3 = x2.getText()

    println(x3)

    if (Site.equals(x3)) {
        x2.click()

        executor.executeScript('arguments[0].scrollIntoView(true)', x2)

        break
    }
}

ScrollIntoCreatedSite.screenshot()


