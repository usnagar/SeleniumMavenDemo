package automation.pageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.abstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {
	WebDriver driver; 

	public ConfirmationPage(WebDriver incDriver) {
		super(incDriver);
		this.driver=incDriver;
		PageFactory.initElements(incDriver,this);
	}

	// Define PageFactory : @FindBy
	
	@FindBy(css=".hero-primary")  
	WebElement getConfirmationText;
	
	public String getConfirmationMessage() {
		return getConfirmationText.getText();
		
	}
}


