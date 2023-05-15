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


WebDriver driver = DriverFactory.getWebDriver()

JavascriptExecutor js = (JavascriptExecutor)driver

timeZone = findTestData('Add Inst-Type Data').getValue('Log -TimeZone', 1)

ValidateField = findTestData('Add Inst-Type Data').getValue('ValidateField', 2)

//Get the Timezone Date & Time for Overdue
LocalDateTime Overdue_ldt = LocalDateTime.now(ZoneId.of(timeZone))

DateTimeFormatter currentDate = DateTimeFormatter.ofPattern('MM/dd/yyyy')
String endDate=currentDate.format(Overdue_ldt)

DateTimeFormatter dateTimeFor = DateTimeFormatter.ofPattern('dd')
String CD=dateTimeFor.format(Overdue_ldt)

//Reduce 8Hrs from the current Time for Overdue
LocalDateTime Overdue_minusHours = Overdue_ldt.minusHours(72)

// Format the DATE & TIME in MM/dd/yyyy & H:mm:ss format for Overdue
DateTimeFormatter Overdue_dtfHH = DateTimeFormatter.ofPattern('MM/dd/yyyy,HH')

String Overdue_formatedDateTimeHH = Overdue_minusHours.format(Overdue_dtfHH)

//Split the DATE & TIME for Overdue
String[] Overdue_DateTime = Overdue_formatedDateTimeHH.split(',')

String OverdueDate = Overdue_DateTime[0]

//Compliance ---> scrollTo Top of the page
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
//
////NEW CHANGES MADE FOR SCROLL
//if(num<=2 || num<=1){
//
//	WebUI.click(findTestObject('Compliance status/click_leftArrow'))
//
//	WebUI.click(findTestObject('Compliance status/click_cal'))
//
//	LogTaskstatus.horizontalScroll()
//	println("The current date is below 1,2,3")
//}
//
//else if(num >= 16){
//
//WebUI.click(findTestObject('Compliance status/click_cal'))
//
//LogTaskstatus.horizontalScroll()
//println("The current date is equal or above 16")
//
//	}else{
//
//	WebUI.click(findTestObject('Compliance status/click'))
//	println("At current date")
//	}

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

WebUI.click(findTestObject('Compliance status/click_cancel'))

//NEW CHANGES MADE FOR SS -----> Scenario expected to happened on month starting (1st and 2nd and 3rd date)
if(num<=2 || num<=1){

	WebUI.click(findTestObject('Compliance status/click_leftArrow'))

	WebUI.click(findTestObject('Compliance status/click_cal'))

	LogTaskstatus.horizontalScroll()
	println("The current date is below 1,2")
	
	List<String>missing=driver.findElements(By.xpath("//span[@class='glyphicon glyphicon-remove text-danger ng-star-inserted']| (//span[@class='glyphicon glyphicon-ok text-warning ng-star-inserted'])[2]"))
	for(int i=0;i<missing.size();i++){
		WebElement eln=missing.get(i)
		eln.click()
		break
	}
	WebUI.takeScreenshot()
	
	WebUI.click(findTestObject('Compliance status/rightDateChanger'))
	
	List<String>mi=driver.findElements(By.xpath("//span[@class='glyphicon glyphicon-remove text-danger ng-star-inserted']|//span[@class='glyphicon glyphicon-remove bg-dark ng-star-inserted']"))
	for(int i=0;i<mi.size();i++){
		WebElement elen=mi.get(i)
		elen.click()
		break
	}
	
WebUI.takeScreenshot()
	
WebUI.click(findTestObject('Compliance status/dateChanger'))
WebUI.takeScreenshot()
	
	WebUI.click(findTestObject('Compliance status/click_cancel'))
}
else if (num==3){
	
	List<String>missi=driver.findElements(By.xpath("//span[@class='glyphicon glyphicon-remove text-danger ng-star-inserted']|//span[@class='glyphicon glyphicon-remove bg-dark ng-star-inserted']"))
	for(int i=0;i<missi.size();i++){
		WebElement elemen=missi.get(i)
		elemen.click()
		break
	}
	
WebUI.click(findTestObject('Compliance status/dateChanger'))
WebUI.takeScreenshot()

WebUI.click(findTestObject('Compliance status/click_cancel'))

WebUI.click(findTestObject('Compliance status/click_leftArrow'))

	WebUI.click(findTestObject('Compliance status/click_cal'))

	LogTaskstatus.horizontalScroll()
	println("The current date is 3")
	
	List<String>mng=driver.findElements(By.xpath("//span[@class='glyphicon glyphicon-remove text-danger ng-star-inserted']| (//span[@class='glyphicon glyphicon-ok text-warning ng-star-inserted'])[1]"))
	for(int i=0;i<mng.size();i++){
		WebElement eleen=mng.get(i)
		eleen.click()
		break
	}
	
	WebUI.takeScreenshot()
	
	WebUI.click(findTestObject('Compliance status/click_cancel'))
}
else { 
	List<String>missing=driver.findElements(By.xpath("//span[@class='glyphicon glyphicon-remove text-danger ng-star-inserted']|//span[@class='glyphicon glyphicon-remove bg-dark ng-star-inserted']"))
	for(int i=0;i<missing.size();i++){
		WebElement eln=missing.get(i)
		eln.click()
		break
	}
	WebUI.takeScreenshot()
	
	WebUI.click(findTestObject('Compliance status/dateChanger'))
	WebUI.takeScreenshot()
	
	WebUI.click(findTestObject('Compliance status/dateChanger'))
	WebUI.takeScreenshot()
	
	WebUI.click(findTestObject('Compliance status/click_cancel'))
}

WebUI.executeJavaScript('window.scrollBy(0,document.body.scrollHeight)', null)

WebUI.click(findTestObject('Compliance status/click_HideCalender'))

WebUI.scrollToElement(findTestObject('Compliance status/scroll_shownCalender'), 0)

WebElement scrollToOvrdue=driver.findElement(By.xpath("//strong[text()='Overdue']"))

js.executeScript("arguments[0].scrollIntoView(false)",scrollToOvrdue)

WebUI.takeScreenshot()
