package utils;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import helper.BaseTestHelper;

public class BaseTest {
	
	private static ThreadLocal<ExtentTest> extentLog = null;
	
	public static ThreadLocal<ExtentTest> getTest(){
		if(extentLog == null) {
			extentLog = new ThreadLocal<>();
		}
		return extentLog;
	}
	
	
    @BeforeSuite(alwaysRun = true)
	public static void config(@Optional("Optional name Automation ") String reportName, @Optional("API Report") String flow) {
		
		String subFolderPath = System.getProperty("user.dir")+"/reports/"+BaseTestHelper.currentDate();
		
		BaseTestHelper.createFolder(subFolderPath);
		
		ExtentReport.getInstance().initialize(subFolderPath+"/"+BaseTestHelper.Timestamp()+"_RestAPI_Automation.html");
		
	}
	
	@BeforeMethod(alwaysRun = true)
	public static void logBeforeMethod(Method method) {
		Test annotation = method.getAnnotation(Test.class);
		String desc = annotation.description()==null?"":annotation.description();
		ExtentReport.startTest(method.getName(), "<b>Description</b> : "+desc+"<hr style='margin:0.1%;'/> ");
	}
	
	@AfterMethod(alwaysRun = true)
	public void getResult(ITestResult result) {
						
		if(result.getStatus()==ITestResult.SUCCESS) {
			ExtentReport.getTest().generateLog(Status.PASS, result.getName()+" : "+result.getMethod().getDescription()+" has been <b style='color:green'>PASSED</b>");
		}else if(result.getStatus()==ITestResult.FAILURE) {
			ExtentReport.getTest().generateLog(Status.FAIL, result.getName()+" : "+result.getMethod().getDescription()+" has been <b style='color:red'>FAILED</b></br>due to<br/>"+result.getThrowable());
		}else if(result.getStatus()==ITestResult.SKIP) {
			ExtentReport.getTest().generateLog(Status.SKIP, result.getName()+" : "+result.getMethod().getDescription()+" has been <b style='color:blue'>SKIPPED</b>");
		}
	}
	
	@AfterSuite(alwaysRun = true)
	public void endReport() {
		ExtentReport.getInstance().flush();
	}
}
