package automation.pageObjectModel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.abstractComponents.AbstractComponent;

public class MyCartPage extends AbstractComponent  {
	WebDriver driver; 
	
	public MyCartPage(WebDriver incDriver) {
		super(incDriver);
		this.driver=incDriver; 
		PageFactory.initElements(incDriver,this);
	}


	// Define PageFactory : @FindBy
	@FindBy(xpath="//div[@class='cartSection']/h3")  
	List<WebElement> cartProducts;
	
	@FindBy(xpath=("//button[contains(text(),'Checkout')]"))
	WebElement checkoutButton;
	
	// Create Action
	public  boolean verifyProductDisplay(String productName) {
		boolean match=cartProducts.stream().anyMatch(mt->mt.getText().equalsIgnoreCase(productName));		
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		checkoutButton.click();
		return new CheckoutPage(driver);
	}
	
	/*
	public List<WebElement> getAddedProductsList() {
		return cartProducts;
	}
	
	public  boolean verifyProductDisplay() {
		boolean match=getAddedProductsList().stream().anyMatch(mt->mt.getText().equalsIgnoreCase(productName));		
		return match;
	}
	*/

}
