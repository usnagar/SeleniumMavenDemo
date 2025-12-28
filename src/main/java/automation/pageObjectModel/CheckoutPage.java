package automation.pageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.abstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver; 

	public CheckoutPage(WebDriver incDriver) {
		super(incDriver);
		this.driver=incDriver;
		PageFactory.initElements(incDriver,this);
	}

	// Define PageFactory : @FindBy
	
	@FindBy(xpath="//button[@class='ta-item list-group-item ng-star-inserted']")  
	private List<WebElement> countriesName; //Applied encapsulation : means hiding information to share with anyone
	
	@FindBy(xpath=("//a[contains(text(),'Place Order ')]"))
	private WebElement btnPlaceOrder; //Applied encapsulation : means hiding information to share with anyone
	
	@FindBy(xpath=("//input[@placeholder='Select Country']"))
	private WebElement countryNameSelection; //Applied encapsulation : means hiding information to share with anyone
	
	// Define Variable
	By findByLocatedCountryList=By.xpath("//section[@class='ta-results list-group ng-star-inserted']");
	
	// Create Action
	
	public List<WebElement> getCountriesList(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(countryNameSelection, countryName).build().perform();
		waitForElementToAppear(findByLocatedCountryList);
		return countriesName;
	}
	
	public void selectCountryFromDropDown(String countryName) {

		getCountriesList(countryName).stream().filter(m2->m2.getText().trim().equalsIgnoreCase(countryName)).findFirst().ifPresent(WebElement::click);
	}
	
	public ConfirmationPage submitOrder() {
		btnPlaceOrder.click();
		return new ConfirmationPage(driver);
	}
}
