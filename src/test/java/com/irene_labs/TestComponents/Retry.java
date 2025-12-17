package com.irene_labs.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/*public class Retry implements IRetryAnalyzer {

    int count = 0;
    int maxTry = 1;

    @Override
    public boolean retry(ITestResult result) {

        if(count<maxTry) {
            count++;
            return true;
        }
            return false;  //al moment que retorne falso deja de ejecutarse el codigo
    }
}*/
public class Retry implements IRetryAnalyzer {

    private int count = 0;
    private static final int MAX_RETRY = 1;

    @Override
    public boolean retry(ITestResult result) {

        if (count < MAX_RETRY) {
            count++;
            System.out.println(
                    "🔁 Retrying test: " + result.getName() +
                            " | Attempt: " + (count + 1)
            );
            return true;
        }

        return false;
    }
}
