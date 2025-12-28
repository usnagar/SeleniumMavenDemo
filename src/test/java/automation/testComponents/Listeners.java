package automation.testComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import automation.resources.ExtentReporterNG;


public class Listeners extends BaseTest implements ITestListener { 
	

	ExtentReports er=ExtentReporterNG.getReportObject();
	  //Calling to static method using class name
	ThreadLocal<ExtentTest> threadId =new ThreadLocal<ExtentTest>();
	 ExtentTest test;
	 String testcaseName;

		@Override
	    public void onTestStart(ITestResult result) {
			testcaseName=result.getMethod().getMethodName(); //To get the test case name
			test= er.createTest(testcaseName);
	        //test= er.createTest(result.getMethod().getMethodName()); //To get the test case name
			threadId.set(test);//Used to set unique threadId //Applied setter
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	    	threadId.get().log(Status.PASS, "TEST PASS"); //Applied getter
	    }

	    
	  
	    @Override
	    public void onTestFailure(ITestResult result) {
	    	//test.fail(result.getThrowable()); //To get the actual error
	    	threadId.get().fail(result.getThrowable());
	    	//threadId.get().log(Status.FAIL, "TEST FAIL");
	    	
	    	String saveScreenshotPath = null;
	    	//result variable holds all the required info about the test cases.
	    	try {
				driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			} catch (Exception exp) { // Put parent(general) exception here rather than multiple 
				exp.printStackTrace();
			}
	    	/*
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	*/
	    	
			try {
				saveScreenshotPath = getScreenshot(testcaseName,driver);
			} catch (IOException e) {
				e.printStackTrace();
			}
			threadId.get().addScreenCaptureFromPath(saveScreenshotPath, testcaseName);
	    }

	    @Override
	    public void onTestSkipped(ITestResult result) {
	    	threadId.get().log(Status.SKIP, "TEST SKIP For RE-TRY");
	    }

	    @Override
	    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

	    @Override
	    public void onTestFailedWithTimeout(ITestResult result) {}

	    @Override
	    public void onStart(ITestContext context) {
	    	/*
	    	er.setSystemInfo("Java Version", System.getProperty("java.version"));
	    	er.setSystemInfo("User", System.getProperty("user.name"));
	    	er.setSystemInfo("Browser", "Chrome");
	    	//Note : if we mention here then above details getting repeated as per Tests count so avoid here
            */
	    }
	    @Override
	    public void onFinish(ITestContext context) {
	    	
	    	er.flush();  //To generate the report once execution is finished 
	    
	    }
	    

}

/*
Note: 
Please put below listener details in the xml file.
<listeners>
<listener class-name="automation.testComponents.Listeners"/>
//"Package_Folder.Package_Sub-Folder.ClassName"
</listeners>
*/