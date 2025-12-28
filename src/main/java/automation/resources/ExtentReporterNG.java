package automation.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReportObject() {
		String reportPath=System.getProperty("user.dir")+"//reports//index.html";
		ExtentSparkReporter esr=new ExtentSparkReporter(reportPath);
		esr.config().setReportName("Web Automation Results");
		esr.config().setDocumentTitle("Test Results");
		
		//ExtentSparkReporter class: used to make the config
		//ExtentReports class : this is the main class and take care of execution 
		
		ExtentReports er=new ExtentReports();
		er.attachReporter(esr);
		er.setSystemInfo("Tested By", "Etisalat User");
		er.setSystemInfo("Environment", "UAT");
		er.setSystemInfo("Browser",System.getProperty("browser", "defaultBrowser"));
		er.setSystemInfo("OS", System.getProperty("os.name"));
		er.setSystemInfo("OS Version", System.getProperty("os.version"));
		er.setSystemInfo("User", System.getProperty("user.name"));
		er.setSystemInfo("Java Version",System.getProperty("java.version"));
		
		return er;
	}

}
