/*
package automation.tests;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import automation.testComponents.BaseTest;

public class ExtentReportDemo extends BaseTest{
	
	ExtentReports er; //Global Variable
	
	@BeforeTest
	public void reportConfig() {
		String reportPath=System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter esr=new ExtentSparkReporter(reportPath);
		esr.config().setReportName("Web Automation Results");
		esr.config().setDocumentTitle("Test Results");
		
		//ExtentSparkReporter class: used to make the config
		//ExtentReports class : this is the main class and take care of execution 
		
		er=new ExtentReports();
		er.attachReporter(esr);
		er.setSystemInfo("Executed By : ", "Etisalat User");
	}
	                    
	@Test
	public void invokeApplication() throws IOException, InterruptedException {
		ExtentTest test=er.createTest("invokeChromeBrowser");
		//objLandingPage.goTo();
		objLandingPage.loginApplication("Test_2026@gmail.com", "Test_2026@");
		test.addScreenCaptureFromBase64String(getScreenshot("invokeChromeBrowser",driver));
		Thread.sleep(2000L);
		test.fail("Forcefully falied test");
		er.flush();
		
	}

}
*/