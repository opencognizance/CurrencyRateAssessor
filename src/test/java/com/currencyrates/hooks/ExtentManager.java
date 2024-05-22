package com.currencyrates.hooks;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.currencyrates.utils.GeneralUtils;

/**
 * ExtentManager class is responsible for managing the ExtentReports instance.
 * It ensures that a single instance of ExtentReports is used throughout the application.
 */
public class ExtentManager {
    private static ExtentReports extent;
    private static ExtentHtmlReporter htmlReporter;

    /**
     * Returns the single instance of ExtentReports. If the instance is null, it initializes
     * the ExtentReports and attaches an HTML reporter with a timestamped report file name.
     *
     * @return the instance of ExtentReports
     */
    public static ExtentReports getInstance(){
        if (extent == null) {
            extent = new ExtentReports();
            htmlReporter = new ExtentHtmlReporter("reports/test_reports_" + GeneralUtils.getFileName() + ".html");
            extent.attachReporter(htmlReporter);
        }
        return extent;
    }
}
