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
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
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
import java.io.*
import java.util.*
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By
import org.openqa.selenium.By.ByXPath as ByXPath
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement

WebUI.openBrowser("https://test.lablogs.co/#/login")

//findTestData('Login').getValue('URL', 1)
WebUI.maximizeWindow()

WebUI.setText(findTestObject('Login_SelectDatabase/input_Sign in to your account_ng-untouched _b61fc2'), 
    findTestData('Login').getValue('UserName', 1))

WebUI.setEncryptedText(findTestObject('Login_SelectDatabase/input_Email_ng-touched ng-dirty ng-valid'), 
    findTestData('Login').getValue('Password', 1))

WebUI.click(findTestObject('Login_SelectDatabase/button_SIGN IN'))

WebUI.delay(5)

WebDriver d = DriverFactory.getWebDriver()

List<String> b = d.findElements(By.xpath("//p[@class='card-text mb-0']"))

int r = b.size()

println(r)

for (int i = 0; i < b.size(); i++) {
    WebElement x1 = b.get(i)

    System.out.println(x1.getText())
}

FileInputStream fis = new FileInputStream('C://Hajira//ELL//Automation//Excel//DataBase Selection.xlsx')

int k = 1

Workbook wb = new XSSFWorkbook(fis)

Sheet s = wb.getSheet('Select Database')

for (int i = 0; i < b.size(); i++) {
    WebElement x1 = b.get(i)

    s.createRow(k).createCell(0).setCellValue(x1.getText())

    k++
}

FileOutputStream fos = new FileOutputStream('C://Hajira//ELL//Automation//Excel//DataBase Selection.xlsx')

wb.write(fos)

String P1 = findTestData('Select_Database').getValue('Database', 1)

System.out.println(P1)

for (int i = 0; i < b.size(); i++) {
    WebElement y1 = b.get(i)

    String z1 = y1.getText()

    System.out.println(z1)

    if (z1.equals(P1)) {
        y1.click()

        break
    }
}

System.out.print('good')

WebUI.click(findTestObject('Login_SelectDatabase/button_Use Selected Database'))

