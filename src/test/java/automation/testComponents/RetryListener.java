package automation.testComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListener implements IRetryAnalyzer {
	 int retryCount = 0; 
	int maxRetryCount = 1; // retry twice
	@Override 
	public boolean retry(ITestResult result) { 
		if (retryCount < maxRetryCount) {
			retryCount++;
			return true; // re-run the test } return false; // stop retrying }
		}
		return false;
	}
}
