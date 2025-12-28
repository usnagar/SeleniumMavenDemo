package automation.pageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.abstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	/*
	//Class Level Variable
	WebDriver driver; 
	String productName;
	
	*/
	// Constructor
	 //Created a constructor along with instance variable and it's having scope only inside this method 
	//that's why we pass it's variable value to Class level Variable
	public LandingPage(WebDriver incDriver) { 
		super(incDriver); //Calling parent Class parameterized constructor here
		//The super keyword is used inside a subclass to refer to its parent (superclass). 
		//It helps you call the parent’s constructor, access parent methods that are overridden, or use parent fields when they are hidden by subclass fields.
		//Note: when an object created for a class then constructor will execute firstly that's why we have created constructor.
		this.driver=incDriver; // 'This' keyword share the knowledge to itself the class where it is getting used
		//PageFactory.initElements(WebDriver driver, Object page)
		PageFactory.initElements(incDriver,this);  //Here 'this' keyword initialize 'incDriver' argument value to current class driver variable.
		
	}
	
	public LandingPage(WebDriver incDriver1, String incProductName1) {
		super(incDriver1,incProductName1);
		PageFactory.initElements(incDriver1,this);
		
	}
	

	// Define PageFactory : @FindBy
	@FindBy(id="userEmail")
	WebElement userEmailField;
	
	@FindBy(id="userPassword")
	WebElement passwordField;
	
	@FindBy(id="login")
	WebElement loginButton;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	// Create Action
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public ProductCatalogue loginApplication(String UN,String PWD) {
		userEmailField.sendKeys(UN);
		passwordField.sendKeys(PWD);
		loginButton.click();
		ProductCatalogue objProductCatalogue=new ProductCatalogue(driver); //When we are assure landing to new page then directly create object over here
		return objProductCatalogue;  //Here returning the Object
	}
		
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		 return errorMessage.getText();
	}

}
