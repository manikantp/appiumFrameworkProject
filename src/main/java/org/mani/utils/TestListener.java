package org.mani.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.itextpdf.html2pdf.HtmlConverter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class TestListener implements ITestListener {
    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, "Test Failed: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "Test Skipped: " + result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        convertHtmlToPdf();
    }

    private void convertHtmlToPdf() {
        try {
            String htmlFilePath = System.getProperty("user.dir") + "/test-output/Test-Automaton-Report.html";
            String pdfFilePath = System.getProperty("user.dir") + "/test-output/Test-Automaton-Report.pdf";
            HtmlConverter.convertToPdf(new File(htmlFilePath), new File(pdfFilePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

