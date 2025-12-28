package automation.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automation.pageObjectModel.CheckoutPage;
import automation.pageObjectModel.ConfirmationPage;
import automation.pageObjectModel.MyCartPage;
import automation.pageObjectModel.OrderHistory;
import automation.pageObjectModel.ProductCatalogue;
import automation.testComponents.BaseTest;

public class StandAloneTest extends BaseTest {
		//String productName = "ZARA COAT 3";	
		@Test(dataProvider ="getData",groups = {"Purchase"})
		//@Test(dataProvider ="getData",groups = {"Purchase","Smoke","Sanity","Regression"}) //To Add Multiple Groups
		//public void submitOrder(String email,String password, String productName) throws InterruptedException, IOException {
		public void submitOrder(HashMap<String, String>Input) throws InterruptedException, IOException {
		//public void submitOrder(HashMap<Object, Object>Input) throws InterruptedException, IOException {
				
			
		//Invoke Browser
		//LandingPage objLandingPage=launchApplication();
		
		//PageObject:1
		//LandingPage objLandingPage=new LandingPage(driver,productName);
		//objLandingPage.goTo();
		
		ProductCatalogue objProductCatalogue=objLandingPage.loginApplication(Input.get("email"), Input.get("password"));
		//*******************
		
		//PageObject:2
		//ProductCatalogue objProductCatalogue=new ProductCatalogue(driver,productName);
		objProductCatalogue.addProductToCart(Input.get("productName"));
		MyCartPage objMyCartPage=objProductCatalogue.goToMyCartPage(); 
	

		//PageObject:3
		//MyCartPage objMyCartPage=new MyCartPage(driver);
		boolean match=objMyCartPage.verifyProductDisplay(Input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage objCheckoutPage=objMyCartPage.goToCheckout();
		
		//PageObject:4
		//CheckoutPage objCheckoutPage=new CheckoutPage(driver);
		objCheckoutPage.selectCountryFromDropDown("India");
		ConfirmationPage objConfirmationPage=objCheckoutPage.submitOrder();
		
		//PageObject:5
		//ConfirmationPage objConfirmationPage=new ConfirmationPage(driver);
		String confirmMessage=objConfirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		Thread.sleep(3000L);
		}	
		
		//PageObject:6
		@Test(dependsOnMethods = {"submitOrder"} )
		public void orderHistoryTest(HashMap<String, String>Input) {
			ProductCatalogue objProductCatalogue=objLandingPage.loginApplication(Input.get("email"), Input.get("password"));
			OrderHistory objOrderHistory=objProductCatalogue.goToYourOrders();
			Assert.assertTrue(objOrderHistory.verifyOrderDisplay(Input.get("productName")));
		}
		
		//Way:3
		@DataProvider
		public Object[][] getData() throws IOException {
		    List<HashMap<String, String>> data =getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//automation//data//PurchaseOrder.json");
			return new Object[][] {{data.get(0)},{data.get(1)}};
		}
		
	//Way:1	
		/*
	@DataProvider
	public Object[][] getData() {
		//int[] arr =new int[]{1,5,8}; //means 3 set of data 
		return new Object[][] {{"Test_2026@gmail.com","Test_2026@","ZARA COAT 3"},{"Test.2026@gmail.com","Test.2026@","ADIDAS ORIGINAL"}};//means 2 set of data
		//means 2 set of data with 3 parameters
		//{"Test_2026@gmail.com","Test_2026@","ZARA COAT 3"},{"Test.2026@gmail.com","Test.2026@","ADIDAS ORIGINAL"}
	}
	*/
	/*	
	//Way:2	
	@DataProvider
	public Object[][] getData() {
		HashMap<String, String> h1Map=new HashMap<String, String>();
		h1Map.put("email", "Test_2026@gmail.com");
		h1Map.put("password", "Test_2026@");
		h1Map.put("productName", "ZARA COAT 3");
		HashMap<Object, Object> h2Map=new HashMap<Object, Object>();
		h2Map.put("email", "Test.2026@gmail.com");
		h2Map.put("password", "Test.2026@");
		h2Map.put("productName", "ADIDAS ORIGINAL");
		return new Object[][] {{h1Map},{h2Map}};
		//Put used to set the data & get used to get the data
	}
	*/

}