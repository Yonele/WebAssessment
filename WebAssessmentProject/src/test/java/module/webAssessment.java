package module;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pom.webObject_POM;


public class webAssessment {

	public static Map<String, String> My_Page_Obejcts = new HashMap<String, String>();
	private static WebDriver driver; 
	String appURL = "http://www.way2automation.com/angularjs-protractor/webtables/";
	static String userDir = System.getProperty("user.dir");

	@BeforeClass
	public void testSetUp() throws Exception {
		
		//open chrome browser and navigate to given url 
		System.setProperty("webdriver.chrome.driver", userDir + "\\plugin\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.navigate().to(appURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(60,TimeUnit.SECONDS);
		takeScreenshot();
	}
	
	@Test
	public void validateUserTable() {

		try{

			// verify if user is on User Table
			String getTitle = driver.getTitle();
			Assert.assertEquals(getTitle, "Protractor practice website - WebTables");
		
			new webObject_POM().SetPageObject();
			takeScreenshot();
			WebElement elmn = getElement("userListTable");
			if(elmn.isDisplayed()){
				Reporter.log("User Table is displayed");
			}else{
				Reporter.log("User Table is not displayed");
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());	
		}
	}
	
	//Test data
	@DataProvider(name="data-provider")
    public Object[][] getDataFromDataprovider(){
    return new Object[][] 
    	{
            { "FName1", "LName1","User1","Pass1","company AAA","Admin","admin@mail.com", "082555"},
            { "FName2", "LName2","User2","Pass2","company BBB","Customer","cusomter@mail.com", "083444" },
        };

    }
	
	@Test(dataProvider = "data-provider")
	public void addUser(String data1,String data2,String data3,String data4,String data5,String data6,String data7,String data8) {

		try{
			
			//Add user and verify if user is addded successfully
			new webObject_POM().SetPageObject();

			getElement("btnAddUser").click();
			takeScreenshot();
			getElement("firstName").sendKeys(data1);
			getElement("lastName").sendKeys(data2);
			getElement("userName").sendKeys(data3);
			getElement("password").sendKeys(data4);
			if(data5.equals("company AAA")){
			getElement("companyAAA").sendKeys(data5);
			}else{
				getElement("companyBBB").sendKeys(data5);
			}
			Select sel = new Select(getElement("role")); 
			sel.selectByVisibleText(data6);
			getElement("email").sendKeys(data7);
			getElement("cell").sendKeys(data8);
			takeScreenshot();
			getElement("btnSave").click();
			
			Thread.sleep(2000);
			getElement("search").sendKeys(data3);
			takeScreenshot();
			if(getElement("addedRecord").getText().equals(data3)){
				Reporter.log(data1+" is added successfully");
			}else{
				Reporter.log(data1+" is not added successfully");
			}
			
			driver.navigate().refresh();

		}catch(Exception e){
			System.out.println(e.getMessage());	
		}
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	public WebElement getElement( String str) throws Exception
	{
		//get element reusable method 
		try{
			String desc = My_Page_Obejcts.get(str);
			WebDriverWait wait = new WebDriverWait(driver, 120);	
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(desc)));
			return this.driver.findElement(By.xpath(desc));
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	//capturing screenshot and store in screenshot folder
	public static void takeScreenshot() throws Exception {
		String timeStamp;
		File screenShotName;
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()); 
		screenShotName = new File(userDir+"\\Screenshots\\"+timeStamp+".png");
		FileUtils.copyFile(scrFile, screenShotName);

		String filePath = screenShotName.toString();
		String path = "<img src=\"file://" + filePath + "\" alt=\"\"/>";
		Reporter.log(path);

	}
	
}
