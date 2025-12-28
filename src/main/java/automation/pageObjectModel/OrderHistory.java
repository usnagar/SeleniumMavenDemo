package automation.pageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.abstractComponents.AbstractComponent;

public class OrderHistory extends AbstractComponent {
	WebDriver driver; 

	public OrderHistory(WebDriver incDriver) {
		super(incDriver);
		PageFactory.initElements(incDriver,this);
	}

	public OrderHistory(WebDriver incDriver1, String incProductName1) {
		super(incDriver1,incProductName1);
		PageFactory.initElements(incDriver1,this);
		
	}
	
	@FindBy(xpath="//tbody/tr/td[2]")  
	//@FindBy(css="tr td:nth-child(3)") 
	List<WebElement> ordersList;

	// Define Variable
	By findByLocatedOrdersList=By.xpath("//tbody/tr/td[2]");
	
	// Create Action
	
	public List<WebElement> getOrdersList() {
	
		return ordersList;
	}
	
	public  boolean verifyOrderDisplay(String productName) {
		boolean match=getOrdersList().stream().anyMatch(mt->mt.getText().equalsIgnoreCase(productName));		
		return match;
	}
	

}
