package com.ibm.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ibm.utilities.PropertiesFileHandler;

public class CustomerPage {

	WebDriver driver;
	WebDriverWait wait;
	
	public CustomerPage(WebDriver driver,WebDriverWait wait)
	{
		this.driver=driver;
		this.wait=wait;
		PageFactory.initElements(driver,this);
	}
	
	//WebElement for link Customers
		@FindBy(xpath="//a[contains(text(), 'Customers')]")
		WebElement custEle;
	//WebElement for button Action
		@FindBy(xpath="(//button[contains(text(), 'Action')])[1]")
		WebElement actionEle;
	//WebElement for Edit
		@FindBy(xpath="//a[@title='Edit']")
		WebElement editEle;
	//WebElement for field Phone Number
		@FindBy(xpath="//input[@name='pnum']")
		WebElement cpnoEle;
	//WebElement for field Email
		@FindBy(xpath="//input[@name='email']")
		WebElement emailEle;
	//WebElement for field Address
		@FindBy(xpath="//textarea[@name='address']")
		WebElement addEle;
	//WebElement for field City
		@FindBy(xpath="//input[@name='city']")
		WebElement cityEle;
	//WebElement for field Pincode
		@FindBy(xpath="//input[@name='pincode']")
		WebElement pincodeEle;
	//WebElement for field IP Address
		@FindBy(xpath="//input[@name='ip']")
		WebElement ipaddEle;
	//WebElement for icon Top Arrow
		@FindBy(xpath="//div[@id='toTop']")
		WebElement topEle;
	//WebElement for icon Save
		@FindBy(xpath="//button[@title='Save']")
		WebElement saveEle;
			
	public CustomerPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void launchAdminPage() throws IOException, InterruptedException
	{
		
		String file="./TestData/magentodata.properties";
		
		PropertiesFileHandler propFileHandler = new PropertiesFileHandler();
		HashMap<String, String> data= propFileHandler.getPropertiesAsMap(file);
		
		String url = data.get("url");
		String email = data.get("email");
		String password = data.get("password");
						
		WebDriverWait wait=new WebDriverWait(driver, 60);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get(url);
		
		LoginPage login = new LoginPage(driver);
		login.enterEmailAddress(email);
		login.enterPassword(password);
		login.clickOnLogin();
	}
	
	public void clickOnLinkCustomer()
	{
		custEle.click();
	}
	
	public void clickOnAction() throws IOException
	{
		String file="./TestData/magentodata.properties";
		PropertiesFileHandler propFileHandler = new PropertiesFileHandler();
		HashMap<String, String> data= propFileHandler.getPropertiesAsMap(file);
		String fullname = data.get("fullname");
		
		List<WebElement> rows= driver.findElements(By.xpath("//table[@id='dataTableExample2']/tbody/tr"));
		int rowCount=rows.size();
		for(int i=1;i<=rowCount;i++)
		{
			WebElement custnameEle=	driver.findElement(By.xpath("//table[@id='dataTableExample2']/tbody/tr["+i+"]/td[2]"));
			String custname = custnameEle.getText();
			if(custname.equals(fullname))
			{
				WebElement actionEle = driver.findElement(By.xpath("(//button[contains(text(), 'Action')])["+i+"]"));
				actionEle.click();
				editEle.click();
				break;
			}
		}
		
	}

	public void enterPhoneNumber(String cpno)
	{
		cpnoEle.clear();
		cpnoEle.sendKeys(cpno);
	}
	
	public void enterEmail(String cemail)
	{
		emailEle.sendKeys(cemail);
	}
	
	public void enterAddress(String caddress)
	{
		addEle.sendKeys(caddress);
	}
	
	public void enterCity(String ccity)
	{
		cityEle.sendKeys(ccity);
	}
	
	public void enterPincode(String cpincode)
	{
		pincodeEle.sendKeys(cpincode);
	}
	
	public void enterIPAddress(String IPaddress)
	{
		ipaddEle.sendKeys(IPaddress);
	}
	
	public void clickOnTopArrow()
	{
		topEle.click();
	}
	
	public void clickOnIconSave()
	{
		saveEle.click();
	}
				
	public void validationOnRecord() throws FileNotFoundException, IOException
	{
		Properties p = new Properties();
		p.load(new FileInputStream("./TestData/magentodata.properties"));
	
		String pagesource = driver.getPageSource();
		//System.out.println(pagesource);
		
		if(pagesource.contains(p.getProperty("cpno"))) {
			System.out.println("The presence of edited customer name is confirmed!");
		}
		else {
			System.out.println("The record is not edited on the Customer List");
		}
	}
	
	public void launchUserPage () throws IOException, InterruptedException
	{
		
		String file="./TestData/magentodata.properties";
		
		PropertiesFileHandler propFileHandler = new PropertiesFileHandler();
		HashMap<String, String> data= propFileHandler.getPropertiesAsMap(file);
		
		String userurl = data.get("userurl");
								
		//WebDriverWait wait=new WebDriverWait(driver, 60);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get(userurl);
		
	}
}
