package com.example;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class GenerateReport implements ITestListener {

    @Override
    public void onStart(ITestContext arg0) {
        System.out.println("+Begin test: " + arg0.getName());
    }

    @Override
    public void onTestStart(ITestResult arg0) {
        System.out.println(" Starting test: " + arg0.getName());
    }

    @Override
    public void onTestSuccess(ITestResult arg0) {
        System.out.println(" Test passed: " + arg0.getName());
    }

}