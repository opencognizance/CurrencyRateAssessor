package com.currencyrates.hooks;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.currencyrates.utils.GeneralUtils;

public class ExtentManager {
    private static ExtentReports extent;
    private static ExtentHtmlReporter htmlReporter;

    public static ExtentReports getInstance(){
        if (extent == null) {
            extent = new ExtentReports();
            htmlReporter = new ExtentHtmlReporter("reports/test_reports_"+ GeneralUtils.getFileName()+".html");
            extent.attachReporter(htmlReporter);
        }
        return extent;
    }
}
