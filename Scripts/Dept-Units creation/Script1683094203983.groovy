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
import org.stringtemplate.v4.compiler.STParser.element_return as element_return
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
import java.io.*
import java.util.*
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil



WebUI.openBrowser(findTestData('Login').getValue('URL', 1))

WebUI.maximizeWindow()

WebUI.setText(findTestObject('Login_SelectDatabase/input_Sign in to your account_ng-untouched _b61fc2'), findTestData('Login').getValue(
		'UserName', 1))

WebUI.setEncryptedText(findTestObject('Login_SelectDatabase/input_Email_ng-touched ng-dirty ng-valid'), findTestData('Login').getValue(
		'Password', 1))

WebUI.click(findTestObject('Login_SelectDatabase/button_SIGN IN'))

WebUI.click(findTestObject('Site creation/div_QA Automation'))

WebUI.click(findTestObject('Site creation/button_Use Selected Database'))

WebUI.delay(5)

WebUI.click(findTestObject('ELL Menu/Select_MenuIcon'))

WebUI.click(findTestObject('ELL Menu/a_ADMINISTRATION'))

WebUI.click(findTestObject('Dept-User creation/Departments - Units'))

WebUI.click(findTestObject('Dept-User creation/button_Add Department'))

DateFormat dateFormat = new SimpleDateFormat("yyyy/dd/MM HH:mm:ss");

Date date = new Date();

String a= dateFormat.format(date);

println(a);

b = ' - '

D = ((findTestData('Dept_Unit Data').getValue('Department Name', 1) + b) + a)

WebUI.setText(findTestObject('Dept-User creation/input_Department Name'), D)

println(D)

KeywordUtil.logInfo(D)

keyword.Excel.writeData("ELL_Departments & Units","Add D&U",1,2,D)

WebUI.selectOptionByLabel(findTestObject('Dept-User creation/input_Site'), findTestData('Dept_Unit Data').getValue('Site', 1), true)

WebUI.verifyOptionPresentByLabel(findTestObject('Dept-User creation/input_Site'), findTestData('Dept_Unit Data').getValue('Site', 1), false, 2)

WebUI.setText(findTestObject('Dept-User creation/input_Bench Unit Name'), findTestData('Dept_Unit Data').getValue('Bench / Unit Name', 1))

D1 = WebUI.getAttribute(findTestObject('Dept-User creation/input_Bench Unit Name'), 'value')

println(D1)

WebUI.verifyEqual(findTestData('Dept_Unit Data').getValue('Bench / Unit Name', 1), D1)

WebUI.setText(findTestObject('Dept-User creation/input_CLIA'), findTestData('Dept_Unit Data').getValue('CLIA License Number', 1))

D2 = WebUI.getAttribute(findTestObject('Dept-User creation/input_CLIA'), 'value')

println(D2)

WebUI.verifyEqual(findTestData('Dept_Unit Data').getValue('CLIA License Number', 1), D2)

WebUI.setText(findTestObject('Dept-User creation/input_Joint Commission'), findTestData('Dept_Unit Data').getValue('Joint Commission', 1))

D3 = WebUI.getAttribute(findTestObject('Dept-User creation/input_Joint Commission'), 'value')

println(D3)

WebUI.verifyEqual(findTestData('Dept_Unit Data').getValue('Joint Commission', 1), D3)

WebUI.setText(findTestObject('Dept-User creation/input_CAP'), findTestData('Dept_Unit Data').getValue('CAP', 1))

D4 = WebUI.getAttribute(findTestObject('Dept-User creation/input_CAP'), 'value')

println(D4)

WebUI.verifyEqual(findTestData('Dept_Unit Data').getValue('CAP', 1), D4)

WebUI.setText(findTestObject('Dept-User creation/input_COLA'), findTestData('Dept_Unit Data').getValue('COLA', 1))

D5 = WebUI.getAttribute(findTestObject('Dept-User creation/input_COLA'), 'value')

println(D5)

WebUI.verifyEqual(findTestData('Dept_Unit Data').getValue('COLA', 1), D5)

WebUI.setText(findTestObject('Dept-User creation/input_AABB'), findTestData('Dept_Unit Data').getValue('AABB', 1))

D6 = WebUI.getAttribute(findTestObject('Dept-User creation/input_AABB'), 'value')

println(D6)

WebUI.verifyEqual(findTestData('Dept_Unit Data').getValue('AABB', 1), D6)

WebUI.setText(findTestObject('Dept-User creation/input_AALA'),'AALA')

WebUI.setText(findTestObject('Dept-User creation/input_AOAHFAP'),'AOAHFAP')

WebUI.setText(findTestObject('Dept-User creation/input_ASHI'),'ASHI')

WebUI.setText(findTestObject('Dept-User creation/input_NYDOH'),'NYDOH')

WebUI.setText(findTestObject('Dept-User creation/input_CADOH'),'CADOH')

WebUI.setText(findTestObject('Dept-User creation/input_FLDOH'),'FLDOH')

WebUI.setText(findTestObject('Dept-User creation/input_NYSPFI'),'NYSPFI')

WebUI.setText(findTestObject('Dept-User creation/input_AU-ID'),'AU-ID')

WebUI.click(findTestObject('Dept-User creation/button_Add Bench  Unit'))

WebUI.setText(findTestObject('Dept-User creation/input_BenchUnit_1'), findTestData('Dept_Unit Data').getValue('Bench / Unit Name', 
        2))

D15 = WebUI.getAttribute(findTestObject('Dept-User creation/input_BenchUnit_1'), 'value')

println(D15)

WebUI.verifyEqual(findTestData('Dept_Unit Data').getValue('Bench / Unit Name', 2), D15)

WebUI.setText(findTestObject('Dept-User creation/input_CLIA 1'), findTestData('Dept_Unit Data').getValue('CLIA License Number', 
        2))

D16 = WebUI.getAttribute(findTestObject('Dept-User creation/input_CLIA 1'), 'value')

println(D16)

WebUI.verifyEqual(findTestData('Dept_Unit Data').getValue('CLIA License Number', 2), D16)

WebUI.setText(findTestObject('Dept-User creation/input_Joint Commission 1'), findTestData('Dept_Unit Data').getValue('Joint Commission', 
        2))

D17 = WebUI.getAttribute(findTestObject('Dept-User creation/input_Joint Commission 1'), 'value')

println(D17)

WebUI.verifyEqual(findTestData('Dept_Unit Data').getValue('Joint Commission', 2), D17)

WebUI.setText(findTestObject('Dept-User creation/input_CAP 1'), findTestData('Dept_Unit Data').getValue('CAP', 2))

D18 = WebUI.getAttribute(findTestObject('Dept-User creation/input_CAP 1'), 'value')

println(D18)

WebUI.verifyEqual(findTestData('Dept_Unit Data').getValue('CAP', 2), D18)

WebUI.setText(findTestObject('Dept-User creation/input_COLA 1'), findTestData('Dept_Unit Data').getValue('COLA', 2))

D19 = WebUI.getAttribute(findTestObject('Dept-User creation/input_COLA 1'), 'value')

println(D19)

WebUI.verifyEqual(findTestData('Dept_Unit Data').getValue('COLA', 2), D19)

WebUI.setText(findTestObject('Dept-User creation/input_AABB 1'), findTestData('Dept_Unit Data').getValue('AABB', 2))

D20 = WebUI.getAttribute(findTestObject('Dept-User creation/input_AABB 1'), 'value')

println(D20)

WebUI.verifyEqual(findTestData('Dept_Unit Data').getValue('AABB', 2), D20)

WebUI.setText(findTestObject('Dept-User creation/input_AALA 1'),'AALA 1')

WebUI.setText(findTestObject('Dept-User creation/input_AOAHFAP 1'),'AOAHFAP 1')

WebUI.setText(findTestObject('Dept-User creation/input_ASHI 1'),'ASHI 1')

WebUI.setText(findTestObject('Dept-User creation/input_NYDOH 1'),'NYDOH 1')

WebUI.setText(findTestObject('Dept-User creation/input_CADOH 1'),'CADOH 1')

WebUI.setText(findTestObject('Dept-User creation/input_FLDOH 1'),'FLDOH 1')

WebUI.setText(findTestObject('Dept-User creation/input_NYSPFI 1'),'NYSPFI 1')

WebUI.setText(findTestObject('Dept-User creation/input_AU-ID 1'),'AU-ID 1')

WebUI.click(findTestObject('Dept-User creation/button_Finish'))

Text = WebUI.getText(findTestObject('Dept-User creation/div_Success Dpt Created'))

println(Text)

KeywordUtil.logInfo(Text)

WebUI.delay(2)

WebUI.scrollToElement(findTestObject('Dept-User creation/input_ADD'), 2)

WebUI.click(findTestObject('Dept-User creation/input_ADD'))

WebUI.click(findTestObject('Dept-User creation/click_AddUser_button'))

WebUI.setText(findTestObject('Dept-User creation/input_Search_SignOffUser'), findTestData('SignOff User Data').getValue('SignOff Users', 1))

D21=WebUI.getAttribute(findTestObject('Dept-User creation/input_Search_SignOffUser'), 'value')
println(D21)

WebUI.verifyEqual( findTestData('SignOff User Data').getValue('SignOff Users', 1), D21)

WebUI.click(findTestObject('Dept-User creation/click_search_SignOffUser'))

WebUI.click(findTestObject('Dept-User creation/input_Add_SignInUser'))

WebUI.click(findTestObject('Dept-User creation/input_AddOptional'))

WebUI.setText(findTestObject('Dept-User creation/input_Search_SignOffUser'), findTestData('SignOff User Data').getValue('SignOff Users', 2))

D22=WebUI.getAttribute(findTestObject('Dept-User creation/input_Search_SignOffUser'), 'value')
println(D22)

WebUI.verifyEqual(findTestData('SignOff User Data').getValue('SignOff Users', 2), D22)

WebUI.click(findTestObject('Dept-User creation/click_search_SignOffUser'))

WebUI.click(findTestObject('Dept-User creation/input_Add_SignInUser'))

WebUI.click(findTestObject('Dept-User creation/input_Add Next User'))

WebUI.setText(findTestObject('Dept-User creation/input_Search_SignOffUser'), findTestData('SignOff User Data').getValue('SignOff Users', 3))

D23=WebUI.getAttribute(findTestObject('Dept-User creation/input_Search_SignOffUser') , 'value')
println(D23)

WebUI.verifyEqual(findTestData('SignOff User Data').getValue('SignOff Users', 3), D23)

WebUI.click(findTestObject('Dept-User creation/click_search_SignOffUser'))

WebUI.click(findTestObject('Dept-User creation/input_Add_SignInUser'))

WebUI.click(findTestObject('Dept-User creation/input_Save SignOffUser'))

signOffUsers = WebUI.getText(findTestObject('Dept-User creation/div_Success Sign-off Chain'))

KeywordUtil.logInfo(signOffUsers)

WebUI.click(findTestObject('Dept-User creation/input_click_ReturnToDptment'))

WebUI.click(findTestObject('Dept-User creation/input_click_ReturnToList'))

WebUI.delay(2)

WebDriver driver = DriverFactory.getWebDriver()

List<String> li = driver.findElements(By.xpath('//div[@class=' + '\'col\']'))

x1 = ((D + '- ') + findTestData('Dept_Unit Data').getValue('Site', 1))

println(x1)

for (int i = 0; i < li.size(); i++) {
    WebElement x2 = li.get(i)

    String x3 = x2.getText()

    if (x1.equals(x3)) {
        x2.click()
		
        break
    } 
}

keyword.ScrollIntoCreatedDpts_Units.Screenshot()
