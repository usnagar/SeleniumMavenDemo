package automation.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import automation.pageObjectModel.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage objLandingPage;

	public WebDriver initializeDriver() throws IOException {
		// FileInputStream fis = new
		// FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\automation\\resources\\GlobalData.properties");
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//automation//resources//GlobalData.properties");
		Properties pros = new Properties();
		pros.load(fis);
		//String browserName = pros.getProperty("browser");
		String browserName = System.getProperty("browser")!=null ?System.getProperty("browser"):pros.getProperty("browser"); //Config to run Maven from CMD
		/*
		Java Ternary Operator
		variable=Expression1 ? Expression2(If True then execute this block ):Expression3(If False then execute this block);
		Equivalent/ Similar to if-else Statement
		
		if (Expression1) {
	        variable = Expression2;
		} else {
		       variable = Expression3;
		}
	   */

		if (browserName.contains("chrome")) {
			ChromeOptions option=new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if (browserName.contains("headless")) { 
			option.addArguments("headless");
			}
			driver = new ChromeDriver(option);
			//driver.manage().window().setSize(new Dimension(1440,900));//Full Screen
			//driver.manage().window().setSize(new Dimension(1920, 1080));
			//driver.manage().window().setSize(new Dimension(1024, 768));
			//driver.manage().window().maximize(); //OR

		} else if (browserName.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\baljeesi\\OneDrive - AMDOCS\\Selenium_WorkSpace\\Webdriver\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {

			System.setProperty("webdriver.edge.driver",
					"C:\\Users\\baljeesi\\OneDrive - AMDOCS\\Selenium_WorkSpace\\Webdriver\\msedgedriver.exe");
			driver = new EdgeDriver();

		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		objLandingPage = new LandingPage(driver);
		objLandingPage.goTo();
		return objLandingPage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}
	
	
	public List<HashMap<String, String>> getJsonDataToMap(String jsonFilePath) throws IOException {
		//read JSON to String
		String jsonContent=FileUtils.readFileToString(new File(jsonFilePath),StandardCharsets.UTF_8);

		//Convert String to HashMap
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){ });
		
		return data;
		
		//Created one Mapper Object and reading json value using it
		//ReadValue method read a string value and convert into HashMap
		//Currently asking create two HashMaps based upon numbers of index and put into one list and give us to back(Array is having 2 indexes[{},{} in json file])

	}
	
	//Screenshot Utility
	public String getScreenshot(String testcaseName,WebDriver driver) throws IOException {
	TakesScreenshot ts=(TakesScreenshot) driver;   //Type casting
	File srcFile=ts.getScreenshotAs(OutputType.FILE);
	String saveScreenshot=System.getProperty("user.dir")+"//reports//"+testcaseName+".png";
	File destFile= new File(saveScreenshot);
	FileUtils.copyFile(srcFile, destFile);
	return saveScreenshot;

	// File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	// String screenshotBase64 = ((TakesScreenshot) element).getScreenshotAs(OutputType.BASE64);
	}
	
}