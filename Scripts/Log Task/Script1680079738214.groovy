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
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import keyword.LogTaskstatus
import keyword.ScrollIntoCompliance

import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import static org.junit.Assert.*
import org.openqa.selenium.By as By
import org.openqa.selenium.By.ByXPath as ByXPath
import org.openqa.selenium.JavascriptExecutor

import java.sql.DriverManager as DriverManager
import java.text.DateFormat as DateFormat
import java.text.SimpleDateFormat as SimpleDateFormat
import java.time.LocalDate as LocalDate
import java.time.LocalDateTime as LocalDateTime
import java.time.ZoneId as ZoneId
import java.time.format.DateTimeFormatter as DateTimeFormatter
import java.awt.Robot
import java.awt.event.KeyEvent
import java.io.File as File
import java.io.IOException as IOException
import org.apache.poi.ss.usermodel.Row as Row
import org.apache.poi.ss.usermodel.Cell as Cell
import org.apache.poi.ss.usermodel.Sheet as Sheet
import org.apache.poi.ss.usermodel.Workbook as Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook as XSSFWorkbook
import java.io.FileOutputStream as FileOutputStream
import java.io.FileInputStream as FileInputStream
import java.io.FileNotFoundException as FileNotFoundException




WebUI.openBrowser(findTestData('Login').getValue('URL', 1))

WebUI.maximizeWindow()

WebUI.setText(findTestObject('Login_SelectDatabase/input_Sign in to your account_ng-untouched _b61fc2'), findTestData('Login').getValue('UserName', 1))

WebUI.setEncryptedText(findTestObject('Login_SelectDatabase/input_Email_ng-touched ng-dirty ng-valid'), findTestData('Login').getValue('Password', 1))

WebUI.click(findTestObject('Login_SelectDatabase/button_SIGN IN'))

WebUI.click(findTestObject('Login_SelectDatabase/div_QA Automation'))

WebUI.click(findTestObject('Dept-User creation/button_Use Selected Database'))

WebUI.delay(5)

WebUI.click(findTestObject('ELL Menu/Select_MenuIcon'))

WebUI.click(findTestObject('ELL Menu/a_ADMINISTRATION'))

WebUI.click(findTestObject('Add Inst-Type/span_Instruments'))

WebUI.setText(findTestObject('Add Inst-Type/input_SearchString'), findTestData('Add Inst-Type Data').getValue('Created-InsType_Name', 1))

WebUI.delay(2)

createdInstType = findTestData('Add Inst-Type Data').getValue('Created-InsType_Name', 1)

println(createdInstType)

WebDriver driver = DriverFactory.getWebDriver()

List<String> InstType = driver.findElements(By.xpath('//div[@class=\'col\']//span'))

for (int i = 0; i < InstType.size(); i++) {
	WebElement x1 = InstType.get(i)

	String x2 = x1.getText()

	println(x2)

	if (createdInstType.equals(x2)) {
		x1.click()
		break
	}
}

WebUI.click(findTestObject('LogTask-Ready_Status/click_View Instrument'))

WebUI.delay(2)

//READY STATUS
Task1 = findTestData('Add Inst-Type Data').getValue('Task Name', 1)

println(Task1)

List<String> T1 = driver.findElements(By.xpath('//div[@class=\'col\']//span'))

for (int i = 0; i < T1.size(); i++) {
	WebElement y1 = T1.get(i)

	String y2 = y1.getText()

	println(y2)

	if (y2.equals(Task1)) {
		y1.click()
		break
	}
}

WebUI.scrollToElement(findTestObject('LogTask-Ready_Status/span_Fixedfrequency(Verification)'), 2)

WebUI.setText(findTestObject('LogTask-Ready_Status/textarea_T1'), 'Checking')

WebUI.click(findTestObject('LogTask-Ready_Status/a_Edit'))

timeZone = findTestData('Add Inst-Type Data').getValue('Log -TimeZone', 1)

//Get the Timezone Date & Time for Ready
LocalDateTime Ready_ldt = LocalDateTime.now(ZoneId.of(timeZone))

//Reduce 8Hrs from the current Time for Ready
LocalDateTime Ready_minusHours = Ready_ldt.minusHours(22)

println(Ready_minusHours)

// Format the DATE & TIME in MM/dd/yyyy & HH:mm format for Ready
DateTimeFormatter Ready_dtfHH = DateTimeFormatter.ofPattern('MM/dd/yyyy,HH')

String Ready_formatedDateTimeHH = Ready_minusHours.format(Ready_dtfHH)

//Split the DATE & TIME -----> Hour for Ready
String[] DateTime = Ready_formatedDateTimeHH.split(',')

String readyDate = DateTime[0]

String readyTimeHH = DateTime[1]

println(readyDate)

println(readyTimeHH)

WebUI.setText(findTestObject('LogTask-Ready_Status/input_Date'), readyDate)

WebUI.setText(findTestObject('LogTask-Ready_Status/input__HH'), readyTimeHH)

WebUI.setText(findTestObject('LogTask-Ready_Status/textarea_ReasonTxtBox'), 'Ready Status Log-checking')

WebUI.click(findTestObject('LogTask-Ready_Status/button_Mark as Complete'))

// Verification for ----> Ready Status
WebUI.verifyElementText(findTestObject('LogTask-Ready_Status/verify_Ready status'), 'Ready')

WebUI.click(findTestObject('ELL Menu/Select_MenuIcon'))

WebUI.click(findTestObject('LogTask-Ready_Status/click_logInstrumentMenu'))

WebUI.setText(findTestObject('LogTask-Ready_Status/span_searchInstTextBox'), findTestData('Add Inst-Type Data').getValue(
		'Created-InsType_Name', 1))

WebUI.verifyElementText(findTestObject('LogTask-Ready_Status/verifyReadySt_inInstrument'), 'Ready')

WebUI.scrollToElement(findTestObject('LogTask-Ready_Status/verify_InstrumentLabel'), 2)

WebUI.waitForElementVisible(findTestObject('LogTask-DueNow_Status/verifyReadySt_inInstrument'), 1)

WebUI.takeScreenshot()

WebUI.click(findTestObject('LogTask-Ready_Status/verify_InstrumentLabel'))

//DUENOW STATUS
Task2 = findTestData('Add Inst-Type Data').getValue('Task Name', 2)

println(Task2)

List<String> T2 = driver.findElements(By.xpath('//div[@class=\'col\']//span'))

for (int i = 0; i < T2.size(); i++) {
	WebElement z1 = T2.get(i)

	String z2 = z1.getText()

	println(z2)

	if (z2.equals(Task2)) {
		z1.click()
		break
	}
}

WebUI.scrollToElement(findTestObject('LogTask-DueNow_Status/span_scroll-FixedSchedule'), 0)

ValidateField = findTestData('Add Inst-Type Data').getValue('ValidateField', 2)

println(ValidateField)

List<String> vf = driver.findElements(By.xpath('//div//label[@aria-pressed=\'false\']'))

for (int i = 0; i < vf.size(); i++) {
	WebElement el = vf.get(i)

	String s = el.getText()

	println(s)

	if (ValidateField.equals(s)) {
		el.click()
		break
	}
}

WebUI.setText(findTestObject('LogTask-DueNow_Status/textarea_T2'), 'Checking')

WebUI.click(findTestObject('LogTask-DueNow_Status/a_Edit'))

//Get the Timezone Date & Time for DueNow
LocalDateTime DueNow_ldt = LocalDateTime.now(ZoneId.of(timeZone))

//Reduce 8Hrs from the current Time for DueNow
LocalDateTime DueNow_minusHours = DueNow_ldt.minusHours(49)

println(DueNow_minusHours)

// Format the DATE & TIME in MM/dd/yyyy & HH:mm:ss format for DueNow
DateTimeFormatter DueNow_dtfHH = DateTimeFormatter.ofPattern('MM/dd/yyyy,HH')

String formatedDateTimeHH = DueNow_minusHours.format(DueNow_dtfHH)

//Split the DATE & TIME for DueNow
String[] dueNowDateTime = formatedDateTimeHH.split(',')

String dueNowDate = dueNowDateTime[0]

String dueNowHH = dueNowDateTime[1]

println(dueNowDate)

println(dueNowHH)

WebUI.setText(findTestObject('LogTask-DueNow_Status/input_Date'), dueNowDate)

WebUI.setText(findTestObject('LogTask-DueNow_Status/input__HH'), dueNowHH)

WebUI.setText(findTestObject('LogTask-DueNow_Status/textarea_ReasonTxtBox'), 'Due Now Status Log-checking')

WebUI.click(findTestObject('LogTask-DueNow_Status/button_Mark as Complete'))

//Verification for Due Now Status
WebUI.verifyElementText(findTestObject('LogTask-DueNow_Status/verify_DueNow status'), 'Due Now')

WebUI.click(findTestObject('ELL Menu/Select_MenuIcon'))

WebUI.click(findTestObject('LogTask-DueNow_Status/click_logInstrumentMenu'))

WebUI.setText(findTestObject('LogTask-DueNow_Status/span_searchInstTextBox'), findTestData('Add Inst-Type Data').getValue(
		'Created-InsType_Name', 1))

WebUI.verifyElementText(findTestObject('LogTask-DueNow_Status/verifyDueNowSt_Instrument'), 'Due Now')

WebUI.scrollToElement(findTestObject('LogTask-DueNow_Status/verify_InstrumentLabel'), 2)

WebUI.waitForElementVisible(findTestObject('LogTask-DueNow_Status/verifyDueNowSt_Instrument'), 1)

WebUI.takeScreenshot()

WebUI.click(findTestObject('LogTask-DueNow_Status/verify_InstrumentLabel'))

// OVERDUE STATUS
Task3 = findTestData('Add Inst-Type Data').getValue('Task Name', 4)

println(Task3)

List<String> T3 = driver.findElements(By.xpath('//div[@class=\'col\']//span'))

for (int i = 0; i < T3.size(); i++) {
	WebElement a1 = T3.get(i)

	String a2 = a1.getText()

	println(a2)

	if (a2.equals(Task3)) {
		a1.click()
		break
	}
}

WebUI.setText(findTestObject('LogTask-OverDue_Status/textarea_T3'), 'Checking')

WebUI.click(findTestObject('LogTask-OverDue_Status/a_Edit'))

//Get the Timezone Date & Time for Overdue
LocalDateTime Overdue_ldt = LocalDateTime.now(ZoneId.of(timeZone))

DateTimeFormatter currentDate = DateTimeFormatter.ofPattern('MM/dd/yyyy')
String endDate=currentDate.format(Overdue_ldt)

DateTimeFormatter dateTimeFor = DateTimeFormatter.ofPattern('dd')
String CD=dateTimeFor.format(Overdue_ldt)

//Reduce 8Hrs from the current Time for Overdue
LocalDateTime Overdue_minusHours = Overdue_ldt.minusHours(72)

println(Overdue_minusHours)

// Format the DATE & TIME in MM/dd/yyyy & H:mm:ss format for Overdue
DateTimeFormatter Overdue_dtfHH = DateTimeFormatter.ofPattern('MM/dd/yyyy,HH')

String Overdue_formatedDateTimeHH = Overdue_minusHours.format(Overdue_dtfHH)

//Split the DATE & TIME for Overdue
String[] Overdue_DateTime = Overdue_formatedDateTimeHH.split(',')

String OverdueDate = Overdue_DateTime[0]

String OverdueHH = Overdue_DateTime[1]

println(OverdueDate)

println(OverdueHH)

WebUI.setText(findTestObject('LogTask-OverDue_Status/input_Date'), OverdueDate)

WebUI.setText(findTestObject('LogTask-OverDue_Status/input__HH'), OverdueHH)

WebUI.setText(findTestObject('LogTask-OverDue_Status/textarea_ReasonTxtBox'), 'Over Due Status Log-checking')

WebUI.click(findTestObject('LogTask-OverDue_Status/button_Mark as Complete'))

WebUI.click(findTestObject('LogTask-OverDue_Status/div_Success-OK'))

//Verification for Over due Status
WebUI.verifyElementText(findTestObject('LogTask-OverDue_Status/verify_OverDue status'), 'Overdue')

WebUI.click(findTestObject('ELL Menu/Select_MenuIcon'))

WebUI.click(findTestObject('LogTask-OverDue_Status/click_logInstrumentMenu'))

WebUI.setText(findTestObject('LogTask-OverDue_Status/span_searchInstTextBox'), findTestData('Add Inst-Type Data').getValue(
		'Created-InsType_Name', 1))

WebUI.verifyElementText(findTestObject('LogTask-OverDue_Status/verify-OverdueSt_Instrument'), 'Overdue')

WebUI.scrollToElement(findTestObject('LogTask-OverDue_Status/verify_InstrumentLabel'), 0)

WebUI.waitForElementVisible(findTestObject('LogTask-OverDue_Status/verify_InstrumentLabel'), 1)

WebUI.takeScreenshot()

WebUI.click(findTestObject('LogTask-OverDue_Status/verify_InstrumentLabel'))
 
//Unschedule STATUS
Task4 = findTestData('Add Inst-Type Data').getValue('Task Name', 5)

println(Task4)

List<String> T4 = driver.findElements(By.xpath('//div[@class=\'col\']//span'))

for (int i = 0; i < T4.size(); i++) {
	WebElement b1 = T4.get(i)

	String b2 = b1.getText()

	println(b2)

	if (Task4.equals(b2)) {
		b1.click()
		break
	}
}

WebUI.scrollToElement(findTestObject('LogTask-Status-A-Z/button_Attach a File'), 2)

filePath = findTestData('Add Inst-Type Data').getValue('Attached FilePath', 1)

WebUI.uploadFile(findTestObject('LogTask-Status-A-Z/button_Attach a File'),filePath)
	
WebUI.setText(findTestObject('LogTask-OverDue_Status/textarea_T3'), 'Checking')

WebUI.click(findTestObject('LogTask-OverDue_Status/button_Mark as Complete'))

//Default Log Status -------> Screenshot
WebUI.scrollToElement(findTestObject('LogTask-Status-A-Z/scrollToStatus'),0)

WebUI.takeScreenshot()

WebUI.scrollToElement(findTestObject('LogTask-DueNow_Status/verify_DueNow status'), 0)

WebUI.takeScreenshot()

List<String>defaultStatus=driver.findElements(By.xpath("//div[@class='col']"))
List<String>l1=new ArrayList<String>()

for (int j=0;j<defaultStatus.size();j++){
	WebElement element=defaultStatus.get(j)
	String ds=element.getText()
	
	println(ds)
	
	l1.add(ds)
}

println(l1)

//Status Log Verfication + Screenshot
WebUI.scrollToElement(findTestObject('LogTask-Status-A-Z/scrollToStatus'),0)

WebUI.click(findTestObject('LogTask-Status-A-Z/span_Status'))

//JS is used for Scrolling purpose
JavascriptExecutor js = (JavascriptExecutor) driver

List<String>status=driver.findElements(By.xpath("//div[@class='col']"))
List<String>l2=new ArrayList<String>()

for(int k=0;k<status.size();k++){
	WebElement ele=status.get(k)
	String st=ele.getText()
	println(st)
	
	l2.add(st)
}

println(l2)

//Compare the STATUS order contain same as Default order)
if(l1.containsAll(l2) && l2.containsAll(l1)){
	println("The list are Equal")
}

WebUI.scrollToElement(findTestObject('LogTask-OverDue_Status/verify_OverDue status'), 0)

WebUI.takeScreenshot()

//A-Z Log Status ------> Screenshot
WebUI.click(findTestObject('LogTask-Status-A-Z/A-Z'))

WebUI.takeScreenshot()
//Default Log Status -------> Screenshot
WebUI.waitForElementNotVisible(findTestObject('LogTask-Status-A-Z/div_Success'), 5)

WebUI.takeScreenshot()

List<String>defaultStatus01=driver.findElements(By.xpath("//div[@class='col']"))
List<String>l4=new ArrayList<String>()

for (int j=0;j<defaultStatus01.size();j++){
	WebElement element=defaultStatus01.get(j)
	String ds=element.getText()
	
	println(ds)
	
	l4.add(ds)
}

println(l4)

//Status Log Verfication + Screenshot
WebUI.scrollToElement(findTestObject('LogTask-Status-A-Z/scrollToTask'),0)

WebUI.click(findTestObject('LogTask-Status-A-Z/span_Status'))

//JS is used for Scrolling purpose
//JavascriptExecutor js = (JavascriptExecutor) driver

List<String>sta=driver.findElements(By.xpath("//div[@class='col']"))
List<String>l3=new ArrayList<String>()

for(int k=0;k<status.size();k++){
	WebElement ele=sta.get(k)
	String st=ele.getText()
	println(st)
	
	l3.add(st)
}

println(l3)

//Compare the STATUS order contain same as Default order)
if(l4.containsAll(l3) && l4.containsAll(l1)){
	println("The list are Equal")
}

WebUI.scrollToElement(findTestObject('LogTask-DueNow_Status/verify_DueNow status'), 0)

WebUI.takeScreenshot()

//A-Z Log Status -------> Screenshot
WebUI.scrollToElement(findTestObject('LogTask-Status-A-Z/scrollToTask'),0)

WebUI.click(findTestObject('LogTask-Status-A-Z/A-Z'))

WebUI.scrollToElement(findTestObject('LogTask-Status-A-Z/scroll-ReadyStatus'), 0)

WebUI.takeScreenshot()