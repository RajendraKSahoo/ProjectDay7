package com.ibm.pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ibm.utilities.PropertiesFileHandler;

public class UserPage 
{
	WebDriver driver;
	WebDriverWait wait;
	
	public UserPage(WebDriver driver,WebDriverWait wait)
	{
		this.driver=driver;
		this.wait=wait;
		PageFactory.initElements(driver,this);
	}


	public void verifytab() throws IOException, InterruptedException
	{
		
		String file="./TestData/magentodata.properties";
		
		PropertiesFileHandler propFileHandler = new PropertiesFileHandler();
		HashMap<String, String> data= propFileHandler.getPropertiesAsMap(file);
		
		String userurl = data.get("userurl");
				
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait=new WebDriverWait(driver, 60);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get(userurl);
		
		Properties p = new Properties();
		p.load(new FileInputStream("./TestData/magentodata.properties"));
		
		String pagesource = driver.getPageSource();
		if(pagesource.contains(p.getProperty("tabname")))
		{
			System.out.println("The presence of tab name is confirmed on User page!");
		}
		else {
			System.out.println("The tab name doesn't exist on User page");
		}
		
				
	}
	
	//WebElement for link SignUp
		@FindBy(xpath="//a[contains(text(),'SignUp')]")
		WebElement signupEle;
	//WebElement for field Full Name
		@FindBy(xpath="//input[@id='name']")
		WebElement nameEle;
	//WebElement for field Phone Number
		@FindBy(xpath="//input[@id='pnum']")
		WebElement pnumEle;
	//WebElement for field Password
		@FindBy(xpath="//input[@id='password']")
		WebElement pwdEle;
	//WebElement for field Confirm Password
		@FindBy(xpath="//input[@id='cpassword']")
		WebElement cpwdEle;
	//WebElement for checkbox Term & Conditions
		@FindBy(xpath="//input[@id='tccheckbox']")
		WebElement tccEle;
	//WebElement for button SignUp
		@FindBy(xpath="//button[@id='mem_signup']")
		WebElement bsignupEle;
	//WebElement for link My Account
		@FindBy(xpath="(//i[@class='flaticon-user-outline'])[1]")
		WebElement myacctEle;
	//WebElement for link Logout
		@FindBy(xpath="//a[contains(text(),'Log Out')]")
		WebElement logoutEle;
		
	//WebElement for link Login
		@FindBy(xpath="//a[contains(text(),'Login')]")
		WebElement loginlinkEle;
	//WebElement for field Phone Number
		@FindBy(xpath="//input[@id='pnum2']")
		WebElement mobileEle;
	//WebElement for field Password
		@FindBy(xpath="//input[@id='pword2']")
		WebElement passwordEle;
	//WebElement for button Login
		@FindBy(xpath="//button[@id='mem_login']")
		WebElement loginbuttonEle;
		
		public void clickOnLinkSignUp()
		{
			signupEle.click();
		}
		public void enterFullName(String fullname)
		{
			nameEle.sendKeys(fullname);
		}
		public void enterPhoneNo(String pno)
		{
			pnumEle.sendKeys(pno);
		}
		public void enterPassword(String cpassword)
		{
			pwdEle.sendKeys(cpassword);
		}
		public void enterConfirmPassword(String confpassword)
		{
			cpwdEle.sendKeys(confpassword);
		}
		public void clickOnTermCondition()
		{
			tccEle.click();
		}
		public void clickOnButtonSignUp()
		{
			bsignupEle.click();
		}
		public void clickOnAlertBox()
		{
			Alert alertBox = driver.switchTo().alert();
			String alertmsg = alertBox.getText();
			System.out.println(alertmsg);
			alertBox.accept();
		}
		public void clickOnLogout()
		{
			myacctEle.click();
			logoutEle.click();
		}
		
		public void clickOnLinkLogin()
		{
			loginlinkEle.click();
		}
		public void enterMobileNo(String cpno)
		{
			mobileEle.sendKeys(cpno);
		}
		public void enterUserPassword(String cpassword)
		{
			passwordEle.sendKeys(cpassword);
		}
		public void clickOnButtonLogin()
		{
			loginbuttonEle.click();
		}
}
