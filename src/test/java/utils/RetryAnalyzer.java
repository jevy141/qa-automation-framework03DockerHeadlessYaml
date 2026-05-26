package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private static final int MAX_RETRY = 1;
    private int count = 0;

    @Override
    public boolean retry(ITestResult result) {

        if (count < MAX_RETRY) {
            count++;
            System.out.println("Retrying Test: " + result.getName());
            return true;
        }

        return false;
    }
}