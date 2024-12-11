package utils;

import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

//import com.relevantcodes.extentreports.ExtentReports;
//import com.relevantcodes.extentreports.ExtentTest;

public class ExtentReport {
	private static ExtentReport instance = null;
	private static ExtentReports extentReport;
	private static ThreadLocal<ExtentTest> extentLog = new ThreadLocal<>();
	
	
	public static ExtentReport getInstance() {
		if(instance==null) {
			instance = new ExtentReport();
		}
		
		return instance;
	}
	
	public synchronized void initialize(String extentReportFilePath) {
				
		if(extentReport == null) {
			
			extentReport = new ExtentReports();
						
			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFilePath);
						
			try {
				sparkReporter.loadXMLConfig(new File(System.getProperty("user.dir")+"/"+"resources/extent-config.xml"));
								
				extentReport.attachReporter(sparkReporter);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			extentReport.setSystemInfo("Product", "RestfullBooker WebAPI");
			extentReport.setSystemInfo("Host", "Harishyam Sharma");
			extentReport.setSystemInfo("Environment", "QA");
			extentReport.setSystemInfo("OS", System.getProperty("os.name"));
			extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
		}
		
	}
	
	public static void startTest(String testName, String testDescription) {
				
		if (extentReport != null) {
            ExtentTest test = extentReport.createTest(testName, testDescription);
            extentLog.set(test); // Set ThreadLocal
        } else {
            throw new IllegalStateException("ExtentReports is not initialized. Call initialize() first.");
        }
	}
	
	public static ExtentTest getTest() {
		ExtentTest test = extentLog.get();
        if (test == null) {
            throw new IllegalStateException("No test started. Call startTest() before logging.");
        }
        return test;
	}
	
	public void flush() {
		if(extentReport != null) {
			extentReport.flush();
		}
	}
}
