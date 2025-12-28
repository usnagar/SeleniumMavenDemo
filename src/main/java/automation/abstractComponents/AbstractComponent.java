package automation.abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import automation.pageObjectModel.MyCartPage;
import automation.pageObjectModel.OrderHistory;

public class AbstractComponent {
	public WebDriver driver; 
	//public String productName;
	
	public AbstractComponent(WebDriver incDriver) {
		this.driver=incDriver;
		PageFactory.initElements(incDriver,this);
	}

	public AbstractComponent(WebDriver incDriver1, String incProductName1) {
		this.driver=incDriver1; 
		//this.productName=incProductName1;
		PageFactory.initElements(incDriver1,this);
		
	}
	// Define PageFactory : @FindBy
	
	@FindBy(css=("[routerlink*='cart']"))
	WebElement cartMenu;
	
	@FindBy(xpath=("//button[contains(@routerlink,'myorders')]"))
	WebElement ordersMenu;


	
	// Create Action
	
	public MyCartPage goToMyCartPage() {
		//Since Headers are common for all pages and can re-use to them so that's why declare here.
		cartMenu.click();
		MyCartPage objMyCartPage=new MyCartPage(driver);
		return objMyCartPage;
	}
	
	public OrderHistory goToYourOrders() {
		ordersMenu.click();
		OrderHistory objOrderHistory=new OrderHistory(driver);
		return objOrderHistory;
	}
	
	public void waitForElementToAppear(By findByElementLocatedText) {
	WebDriverWait explicitWait=new WebDriverWait(driver, Duration.ofSeconds(15)); //Applied explicit wait
	//Here driver is dummy until defined above in the class 
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(findByElementLocatedText));
	}
	
	public void waitForWebElementToAppear(WebElement findWebElementByDriverText) {
	WebDriverWait explicitWait=new WebDriverWait(driver, Duration.ofSeconds(15)); //Applied explicit wait
	//Here driver is dummy until defined above in the class 
	explicitWait.until(ExpectedConditions.visibilityOf(findWebElementByDriverText));
	}
	
	public void waitForElementToDisappear(WebElement findByElementDriverText) throws InterruptedException {
	Thread.sleep(1000);
	/*
	//Because due to some Application issue,below code is taking some extra time.
	WebDriverWait explicitWait=new WebDriverWait(driver, Duration.ofSeconds(15)); 
	//Here driver is dummy until defined above in the class 
	explicitWait.until(ExpectedConditions.invisibilityOf(findByElementDriverText));
	*/
	}
	

}
