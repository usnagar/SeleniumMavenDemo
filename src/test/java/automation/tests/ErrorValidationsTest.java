package automation.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import automation.pageObjectModel.MyCartPage;
import automation.pageObjectModel.ProductCatalogue;
import automation.testComponents.BaseTest;
import automation.testComponents.RetryListener;

public class ErrorValidationsTest extends BaseTest {
//@Test(retryAnalyzer(Attribute) = RetryAnalyzer(ClassName).class)
	@Test(groups = {"ErrorHanding"},retryAnalyzer=RetryListener.class)
	public void LoginErrorValidation() throws InterruptedException, IOException {
		objLandingPage.loginApplication("Test_2026@gmail.com", "Test_2");
		Assert.assertEquals( objLandingPage.getErrorMessage(),"Incorrect email or password.");
	}

	@Test
	public void ProductErrorValidation() throws InterruptedException, IOException {
		String productName = "ZARA COAT 3";
		//String productName = "ZARA COAT 4";

		ProductCatalogue objProductCatalogue = objLandingPage.loginApplication("Test_2026@gmail.com", "Test_2026@");
		objProductCatalogue.addProductToCart(productName);
		MyCartPage objMyCartPage = objProductCatalogue.goToMyCartPage();
		boolean match = objMyCartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		
	

	}

}
