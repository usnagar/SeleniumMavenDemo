package automation.pageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.abstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	
	public ProductCatalogue(WebDriver incDriver) {
		super(incDriver);
		PageFactory.initElements(incDriver,this);
		
	}
	
	public ProductCatalogue(WebDriver incDriver1, String incProductName1) {
		super(incDriver1,incProductName1);
		//PageFactory.initElements(incDriver1,this);	
	}
	
	
	// Define PageFactory : @FindBy
	@FindBy(xpath="//div[contains(@class,'mb-3 ng-star-inserted')]")   //To open any class/method,just Press Select/Hover it then press ctrl + click[Example @FindBy]
	//@FindBy(css=".mb-3") 
	List<WebElement> prodList;
	
	@FindBy(css=(".ng-animating")) 
	WebElement spinnerText;
	
	// Define Variable
	By findByLocatedProduct=By.xpath("//div[contains(@class,'mb-3 ng-star-inserted')]");
	//By findByElementProductName=By.xpath("//b[contains(text(),'"+productName+"')]");
	By findByElementProductName=By.cssSelector("b");
	//By findByElementProductName=By.xpath("//div[@class='card-body']/h5/b");
	//By findByElementAddToCart=By.xpath("//div[@class='card-body']/button[contains(text(),' Add To Cart')]");
	By findByElementAddToCart=By.cssSelector(".card-body button:last-of-type");
	By findByElementToastMessage=By.cssSelector("#toast-container");
	
	
	// Create Action
	public List<WebElement> getProductList() {
		waitForElementToAppear(findByLocatedProduct);
		return prodList;
	}
	
	public WebElement getProductByName(String productName) {
	WebElement prod =prodList.stream().filter(product->
	product.findElement(findByElementProductName).getText().equals(productName)).findFirst().orElse(null);
	return prod;
	}
	
	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod =getProductByName(productName);
		prod.findElement(findByElementAddToCart).click();
		waitForElementToAppear(findByElementToastMessage);
		waitForElementToDisappear(spinnerText);
	}


}
