package utils;

import org.testng.asserts.SoftAssert;

public class SoftAssertionUtil {

	private SoftAssert softAssert;
//	private static SoftAssertionUtil softAssertUtil;
//	
//	private SoftAssertionUtil() {
//		//softAssert = new SoftAssert();
//	}
	
	public static SoftAssertionUtil getInstance() {
		return new SoftAssertionUtil();
	}
	
	private SoftAssert getSoftAssert() {
		if(softAssert==null) {
			softAssert = new SoftAssert();
		}
		return softAssert;
	}
	
	public void assertTrue(boolean condition, String message) {
		try {
			getSoftAssert().assertTrue(condition, message);
		} catch (AssertionError e) {
			getSoftAssert().fail(message);
			// TODO: handle exception
		}
	}
	
	public void assertEquals(Object actual, Object expected, String message) {
		try {
			getSoftAssert().assertEquals(actual, expected, message);
		} catch (AssertionError e) {
			getSoftAssert().fail(message);
			// TODO: handle exception
		}
	}
	
	public void assertNotEquals(Object actual, Object expected, String message) {
		try {
			getSoftAssert().assertNotEquals(actual, expected, message);
		} catch (AssertionError e) {
			getSoftAssert().fail(message);
			// TODO: handle exception
		}
	}
	
	public void AssertAll() {
		getSoftAssert().assertAll();
	}
	
}
