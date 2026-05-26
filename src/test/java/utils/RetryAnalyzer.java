package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    int count = 0;
    int maxTry = 1;

    @Override
    public boolean retry(ITestResult result) {
    	if (result.getStatus() == ITestResult.SUCCESS) {
    		return false;
    	}

        if (count < maxTry) {
            count++;
            System.out.println("Retrying Test: " + result.getName());
            return true;
        }

        return false;
    }
}