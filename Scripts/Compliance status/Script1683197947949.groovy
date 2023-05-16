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
import java.time.LocalTime
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
WebUI.waitForElementNotVisible(findTestObject('LogTask-Status-A-Z/div_Success'), 5)

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
WebUI.scrollToElement(findTestObject('LogTask-Status-A-Z/scrollToTask'),0)

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
	
	WebUI.takeScreenshot()
}
//A-Z Log Status -------> Screenshot

WebUI.click(findTestObject('LogTask-Status-A-Z/A-Z'))

WebUI.takeScreenshot()

WebUI.delay(2)

//scrollTo Top of the page

WebElement scrollTopReport=driver.findElement(By.xpath("//a//span[text()='Reports']"))

js.executeScript("arguments[0].scrollIntoView(false)",scrollTopReport)

WebUI.click(findTestObject('Compliance status/click_Reports'))
	
WebUI.setText(findTestObject('Compliance status/fromDate'),OverdueDate)
	
WebUI.setText(findTestObject('Compliance status/toDate'),endDate)

WebUI.click(findTestObject('Compliance status/click_Apply'))

//Changes made for month-end Scroll
int num=Integer.parseInt(CD)
println(num)

//NEW CHANGES MADE FOR SS
LocalTime now=LocalTime.now(ZoneId.of(timeZone))
if(num == 3 && now.isAfter(LocalTime.parse("12:00")) && now.isBefore(LocalTime.parse("23:59"))){

	WebUI.click(findTestObject('Compliance status/click_RightArrow'))

//	WebUI.click(findTestObject('Compliance status/click_cal'))
//	LogTaskstatus.horizontalScroll()
	
	println("The current date is below 1,2")
}

WebUI.scrollToElement(findTestObject('Compliance status/scrollDown'),0)

// RESOLVE Status --------> 1st Place

List<String>lo=driver.findElements(By.xpath("//div[@title='Non-Compliance Not Yet Reviewed']"))

for (int i=0;i<lo.size();i++) {
WebElement elem=lo.get(i)
elem.click()
break
}

WebUI.click(findTestObject('Compliance status/click_Overdue'))

//Resolve Date & Time ---->for taking SS at the end

WebUI.click(findTestObject('Compliance status/click_Resolve'))

//Resolve status SS
WebUI.takeScreenshot()

List<String> l = driver.findElements(By.xpath("//div[@aria-expanded='true']//label[contains(@class,'white')]"))

for (int i = 0; i < l.size(); i++) {
	WebElement elem = l.get(i)

	String s = elem.getText()

	println(s)

	if (ValidateField.equals(s)) {
		elem.click()
	}
	break
}


WebUI.setText(findTestObject('Compliance status/setTextForReason-Resolve'),'Checking Resolve Status')

WebUI.click(findTestObject('Compliance status/btn_MarkasComplete'))

// NO TESTING Status --------> 2nd place

WebUI.click(findTestObject('ELL Menu/Select_MenuIcon'))

WebUI.click(findTestObject('ELL Menu/a_Dashboard'))

WebUI.setText(findTestObject('Compliance status/input_SearchString'),findTestData('Add Inst-Type Data').getValue('Created-InsType_Name',1))

//Verification for given Search Instrument name is shown or not
WebUI.delay(2)

Actual=WebUI.getText(findTestObject('Compliance status/verifi_ExpvsAct'))
println(Actual)

expected=findTestData('Add Inst-Type Data').getValue('Created-InsType_Name', 1)
println(expected)

WebUI.verifyEqual(Actual, expected)

WebUI.scrollToElement(findTestObject('Compliance status/scrollToNonCompliance(1)'), 0)
																															
WebUI.click(findTestObject('Compliance status/click_NT'))

WebUI.scrollToElement(findTestObject('Compliance status/scroll_viewInst'), 0)

WebUI.click(findTestObject('Compliance status/click_shownCalender'))

WebUI.scrollToElement(findTestObject('Compliance status/scrollToCalenderView'), 0)

//NEW CHANGES MADE FOR SS
if(num<=2 || num<=1){

	WebUI.click(findTestObject('Compliance status/click_leftArrow'))

	WebUI.click(findTestObject('Compliance status/click_cal'))

	LogTaskstatus.horizontalScroll()
	println("The current date is below 1,2")
}

else if(num >= 17){

WebUI.click(findTestObject('Compliance status/click_cal'))

LogTaskstatus.horizontalScroll()
println("The current date is equal or above 17")

	}else{

	WebUI.click(findTestObject('Compliance status/click'))
	println("At current date")
	}

expStatus='NT'

List<String>NT=driver.findElements(By.xpath("//span[@class='glyphicon glyphicon-remove text-danger ng-star-inserted'] |//span[@class='glyphicon glyphicon-remove bg-dark ng-star-inserted']"))
for(int k=0;k<NT.size();k++){
	WebElement noTesting=NT.get(k)
	noTesting.click()
	break
}

List<String>NoTesting1=driver.findElements(By.xpath("//span[contains(@class,'rounded-pill-button p-1')]"))
for(int i=0;i<NoTesting1.size();i++){
	WebElement noTTesting1=NoTesting1.get(i)
	String s1=noTTesting1.getText()
	
	if(expStatus.equals(s1)){
		WebUI.takeScreenshot()
		break
	}
	else{
	WebUI.click(findTestObject('Compliance status/dateChanger'))
}
}

List<String>NoTesting2=driver.findElements(By.xpath("//span[contains(@class,'rounded-pill-button p-1')]"))
for(int j=0;j<NoTesting2.size();j++){
	WebElement NoTTesting2=NoTesting2.get(j)
	String s2=NoTTesting2.getText()
	
	if(expStatus.equals(s2)){
		WebUI.takeScreenshot()
		break
	}
}
WebUI.click(findTestObject('Compliance status/click_cancel'))

// IGNORE Status --------> 3rd place

//NEW CHANGES MADE FOR SCROLL
if(num<=2 || num<=1){
	
	WebUI.click(findTestObject('Compliance status/click_leftArrow'))

	WebUI.click(findTestObject('Compliance status/click_cal'))
	
	LogTaskstatus.horizontalScroll()
	println("The current date is below 1,2,3")
}

else if(num >= 16){
	
WebUI.click(findTestObject('Compliance status/click_cal'))

LogTaskstatus.horizontalScroll()
println("The current date is equal or above 16")

	}else{
	
	WebUI.click(findTestObject('Compliance status/click'))
	println("At current date")
	}

List<String>lol=driver.findElements(By.xpath("//div[@title='Non-Compliance Not Yet Reviewed']"))

for (int i=0;i<lol.size();i++) {
WebElement elem=lol.get(i)
elem.click()
break
}

WebUI.click(findTestObject('Compliance status/click_overdue-Ignore'))

WebUI.click(findTestObject('Compliance status/click_Ignore'))

//Ignore status SS
WebUI.takeScreenshot()

WebUI.setText(findTestObject('Compliance status/setTextForReason-Ignore'),'Checking Ignore Status')

WebUI.click(findTestObject('Compliance status/btn_MarkasOverride-Ignore'))

WebUI.delay(2)

// CONFIRM Status --------> 4th place
WebElement scrollTop=driver.findElement(By.xpath("//a//span[text()='Non-Compliance']"))

js.executeScript("arguments[0].scrollIntoView(false)",scrollTop)

WebUI.click(findTestObject('Compliance status/scrollToNoncompliance(Top)'))

WebUI.setText(findTestObject('Compliance status/input_SearchString'),findTestData('Add Inst-Type Data').getValue('Created-InsType_Name',1))

WebUI.verifyEqual(Actual, expected)

WebUI.setText(findTestObject('Compliance status/fromDate'),OverdueDate)

WebUI.setText(findTestObject('Compliance status/toDate'),endDate)

WebUI.click(findTestObject('Compliance status/click_submit'))

//scroll To Edge
WebUI.executeJavaScript("window.scrollBy(0,document.body.scrollHeight)", null)

WebUI.delay(2)

WebUI.click(findTestObject('Compliance status/click_Confirm'))

//Confirm Status SS
WebUI.takeScreenshot()

WebUI.setText(findTestObject('Compliance status/setTextForReason-Confirm'),'Checking Confirm Status')

WebUI.click(findTestObject('Compliance status/click_btnConfirm'))

WebElement scrollToTask=driver.findElement(By.xpath("//a//span[text()='Tasks']"))

js.executeScript("arguments[0].scrollIntoView(false)",scrollToTask)
	
WebUI.click(findTestObject('Compliance status/click_Top-Task'))
	
WebUI.setText(findTestObject('Compliance status/input_SearchString'),findTestData('Add Inst-Type Data').getValue('Created-InsType_Name',1))

WebUI.verifyEqual(Actual, expected)

WebUI.delay(2)

WebUI.click(findTestObject('Compliance status/click_Confirm'))

WebUI.setText(findTestObject('Compliance status/setTextForReason-Confirm'),'Checking extra Confirm Status')

WebUI.click(findTestObject('Compliance status/click_btnConfirm'))

WebUI.scrollToElement(findTestObject('Compliance status/scroll_viewInst'), 0)

WebUI.waitForElementClickable(findTestObject('Compliance status/scroll_shownCalender'), 0)

WebUI.click(findTestObject('Compliance status/scroll_shownCalender'))

//Changes made for Month-end scroll
if(num == 1){

	WebUI.click(findTestObject('Compliance status/click_leftArrow'))

	WebUI.click(findTestObject('Compliance status/click_cal'))

	LogTaskstatus.horizontalScroll()
	println("The current date is below 1,2")
}

else if(num >= 17){

WebUI.click(findTestObject('Compliance status/click_cal'))

LogTaskstatus.horizontalScroll()
println("The current date is equal or above 17")

	}else{

	WebUI.click(findTestObject('Compliance status/click'))
	println("At current date")
	}


List<String>NC=driver.findElements(By.xpath("//span[@class='glyphicon glyphicon-remove text-danger ng-star-inserted'] |//span[@class='glyphicon glyphicon-remove bg-dark ng-star-inserted']"))
for(int i=0;i<NC.size();i++){
	WebElement elemen=NC.get(i)
	elemen.click()
	break
}
WebUI.takeScreenshot()

WebUI.click(findTestObject('Compliance status/dateChanger'))
WebUI.takeScreenshot()

WebUI.click(findTestObject('Compliance status/dateChanger'))
WebUI.takeScreenshot()

WebUI.click(findTestObject('Compliance status/click_cancel'))

WebUI.executeJavaScript('window.scrollBy(0,document.body.scrollHeight)', null)

WebUI.click(findTestObject('Compliance status/click_HideCalender'))

WebUI.scrollToElement(findTestObject('Compliance status/scroll_shownCalender'), 0)

WebElement scrollToOvrdue=driver.findElement(By.xpath("//strong[text()='Overdue']"))

js.executeScript("arguments[0].scrollIntoView(false)",scrollToOvrdue)

WebUI.takeScreenshot()
