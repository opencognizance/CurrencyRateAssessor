package com.currencyrates.hooks;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.currencyrates.utils.GeneralUtils;

/**
 * ExtentManager class is responsible for managing the ExtentReports instance.
 * It ensures that a single instance of ExtentReports is used throughout the application.
 */
public class ExtentManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    private ExtentManager() { }

    public static ExtentReports getInstance() {
        if (extent == null) {
            synchronized (ExtentManager.class) {
                if (extent == null) {
                    ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("reports/test_reports_" + GeneralUtils.getFileName() + ".html");
                    extent = new ExtentReports();
                    extent.attachReporter(htmlReporter);
                }
            }
        }
        return extent;
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void setTest(ExtentTest extentTest) {
        test.set(extentTest);
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}
