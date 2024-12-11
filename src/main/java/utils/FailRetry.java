package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class FailRetry implements IRetryAnalyzer{

	private int retryCount = 0;
	private static final int MAX_RETRY_COUNT = 2;
	
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(retryCount<MAX_RETRY_COUNT) {
			retryCount++;
			
            System.out.println("Retrying test " + result.getName() + " (attempt " + retryCount + ")");
			
			return true;
		}
		return false;
	}

}
