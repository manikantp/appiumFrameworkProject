package org.mani.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
public class ExtentManager {
    private static ExtentReports extent;
    private static ExtentSparkReporter htmlReporter;
    private static String reportFileName = "Test-Automaton-Report.html";
    private static String filePath = System.getProperty("user.dir") + "/test-output/";

    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }

    public static ExtentReports createInstance() {
        htmlReporter = new ExtentSparkReporter(filePath + reportFileName);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("Automation Test Results");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        return extent;
    }
}
