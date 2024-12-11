package restFullBooker;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;


import core.StatusCode;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.AuthTokenPOJO;
import pojo.BookingDetailPOJO;
import pojo.CustomerDetailPOJO;
import userSpecification.UserRequestSpecification;
import utils.BaseTest;
import utils.JSONReader;
import utils.SoftAssertionUtil;

import static io.restassured.RestAssured.*;

public class RestFullBookerTest extends BaseTest{
	
	SoftAssertionUtil softAssertUtil;

	private String baseURI = "https://restful-booker.herokuapp.com";
	private static String authToken;
	private static int bookingId;
	
	@Test(description = "RB_01 : Test to validate the Healthcheck point for the API.", priority=1, groups = {"SmokeSuite"})
	public void pingServerForHealthCheck() throws IOException, ParseException {
		softAssertUtil = new SoftAssertionUtil();
		
		RequestSpecification spec = UserRequestSpecification.getInstance().getRequestSpecification(baseURI);
		
		Response response = given()
			.spec(spec)
			.when()
			.get("/ping");
		
		System.out.println("****************************** pingServerForHealthCheck ************************************");	
		System.out.println(response.body().asString());	
		softAssertUtil.assertEquals(response.statusCode(), StatusCode.CREATED.code, "Response Code is not 201 instead got : "+response.statusCode());
		softAssertUtil.assertTrue(response.body().asString().indexOf("Created")!=-1, "Response body null!");
		softAssertUtil.AssertAll();
		
	}
	
	private AuthTokenPOJO authTokenBody() {
		AuthTokenPOJO getAuthTokenBody = new AuthTokenPOJO();
		getAuthTokenBody.setUsername("admin");
		getAuthTokenBody.setPassword("password123");
		
		return getAuthTokenBody;
	}
	
	@Test(description = "RB_02 : Test to create new auth token for booking", priority=2)
	public void getAuthToken() throws IOException, ParseException {
		softAssertUtil = new SoftAssertionUtil();
		
		System.out.println(authTokenBody().toString());
		Response response = given()
			.spec(UserRequestSpecification.getInstance().getRequestSpecification(baseURI, "application/json"))
			.body(authTokenBody())
			.when()
			.post("/auth");
		
		System.out.println("****************************** getAuthToken ************************************");
		
		String body = response.body().asString();
				
		softAssertUtil.assertEquals(response.statusCode(), StatusCode.SUCCESS.code, "Response Code is not 200 instead get : "+response.statusCode());
		
		System.out.println("*************** Status Code validated ******************");

		softAssertUtil.assertTrue(response.body().asString().indexOf("token")!=-1, "Token not found");
		
		System.out.println("*************** Token Found and validated ******************");
		
		authToken = JSONReader.getTestData(body, "token");
		//System.out.println("Token : "+authToken);
		
		softAssertUtil.AssertAll();
		
	}
	
	@Test(description = "RB_03 : Test to fetch all the booking IDs", priority=3)
	public void fetchAllBookingIDs() {
		softAssertUtil = new SoftAssertionUtil();
		Response response = given()
			.spec(UserRequestSpecification.getInstance().getRequestSpecification(baseURI))
			.when()
			.get("/booking");
		
		System.out.println("****************************** fetchAllBookingIDs ************************************");
		
		softAssertUtil.assertEquals(response.statusCode(), StatusCode.SUCCESS.code, "Response Code is not "+response.statusCode());
		
		String body = response.body().asString();
		System.out.println("Response Body : "+body);
				
		softAssertUtil.assertTrue(body.indexOf("bookingid")!=-1, "BookingIDs not found");		
		softAssertUtil.AssertAll();
		
	}
	
	@Test(description="RB_04 : Test to fetch all the booking IDs filtered by first & last name", priority=6, enabled=true)
	public void filterBookingIDsByName() {
		softAssertUtil = new SoftAssertionUtil();
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("firstname", getRequestBody().getFirstname());
		queryParams.put("lastname", getRequestBody().getLastname());
		
		Response response = given()
			.spec(UserRequestSpecification.getInstance().getRequestSpecification(baseURI))
			.queryParams(queryParams)
			.when()
			.get("/booking");
		
		System.out.println("****************************** filterBookingIDsByName ************************************");
		
		softAssertUtil.assertEquals(response.statusCode(), StatusCode.SUCCESS.code, "Response Code is not 200 instead get : "+response.statusCode());
		
		System.out.println(">>>>>>>>> Status Code validated");
		String responseBody = response.body().asString();
		System.out.println("Response Body : "+responseBody);
		
		softAssertUtil.assertTrue(responseBody.indexOf("bookingid")!=-1, "BookingIDs not found");		
		softAssertUtil.AssertAll();
	}
	
	@Test(description="RB_05 : Test to fetch all the booking IDs filtered by checkIn & checkOut Dates", priority=7, enabled=true)
	public void filterBookingIDsByCheckInOutDate() {
		softAssertUtil = new SoftAssertionUtil();
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("checkin", getRequestBody().getBookingdates().get("checkin"));
		queryParams.put("checkout", getRequestBody().getBookingdates().get("checkout"));
		
		Response response = given()
			.spec(UserRequestSpecification.getInstance().getRequestSpecification(baseURI))
			.queryParams(queryParams)
			.when()
			.get("/booking");
		
		System.out.println("****************************** filterBookingIDsByCheckInOutDate ************************************");
		
		softAssertUtil.assertEquals(response.statusCode(), StatusCode.SUCCESS.code, "Response Code is not 200 instead get : "+response.statusCode());
		
		System.out.println(">>>>>>>>> Status Code validated");
		String responseBody = response.body().asString();
		System.out.println("Response Body : "+responseBody);
		
		softAssertUtil.assertTrue(responseBody.indexOf("bookingid")!=-1, "BookingIDs not found");		
		softAssertUtil.AssertAll();
	}
	
	@Test(description="RB_06 : Test to fetch the customer detail by booking ID", priority=5)
	public void fetchCustomerDetailByID() {
		softAssertUtil = new SoftAssertionUtil();
		
		//int customerID = 15;
		String firstname = getRequestBody().getFirstname();
		String lastname = getRequestBody().getLastname();
		int totalprice = getRequestBody().getTotalprice();
		boolean depositpaid = getRequestBody().isDepositpaid();
		String checkin = getRequestBody().getBookingdates().get("checkin");
		String checkout = getRequestBody().getBookingdates().get("checkout");
		String additionalneeds = getRequestBody().getAdditionalneeds();
				
		Response response = given()
			.spec(UserRequestSpecification.getInstance().getRequestSpecification(baseURI))
			.when()
			.get("/booking/"+bookingId);
		
		System.out.println("****************************** fetchCustomerDetailByID ************************************");
		
		softAssertUtil.assertEquals(response.statusCode(), StatusCode.SUCCESS.code, "Response Code is not 200 instead get : "+response.statusCode());
		
		System.out.println(">>>>>>>>> ByID Status Code validated");
		BookingDetailPOJO responseBody = response.body().as(BookingDetailPOJO.class);
		System.out.println("ByID Response Body : "+responseBody.toString());
		
		softAssertUtil.assertEquals(responseBody.getFirstname(), firstname, "FirstName not as expected");		
		softAssertUtil.assertEquals(responseBody.getLastname(), lastname, "LastName not as expected");		
		softAssertUtil.assertEquals(responseBody.getTotalprice(), totalprice, "totalprice not as expected");		
		softAssertUtil.assertEquals(responseBody.isDepositpaid(), depositpaid, "depositpaid not as expected");		
		softAssertUtil.assertEquals(responseBody.getBookingdates().get("checkin"), checkin, "checkin not as expected");		
		softAssertUtil.assertEquals(responseBody.getBookingdates().get("checkout"), checkout, "checkin not as expected");		
		softAssertUtil.assertEquals(responseBody.getAdditionalneeds(), additionalneeds, "totalprice not as expected");		
		softAssertUtil.AssertAll();
	}
	
	private BookingDetailPOJO getRequestBody() {
		BookingDetailPOJO requestBody= new BookingDetailPOJO();
		
		Map<String, String> bookingdates = new HashMap<>();
		bookingdates.put("checkin", "2023-12-21");
		bookingdates.put("checkout", "2021-02-28");
		
		requestBody.setFirstname("Harishyam");
		requestBody.setLastname("Sharma");
		requestBody.setTotalprice(256);
		requestBody.isDepositpaid();
		requestBody.setBookingdates(bookingdates);
		requestBody.setAdditionalneeds("Lunch");
		
		return requestBody;
	}
	
	@Test(description="RB_07 : Test to validate to create New Booking with Content-Type : application/json", priority=4)
	public void createNewBooking() {
		softAssertUtil = new SoftAssertionUtil();
		
		Response response = given()
			.spec(UserRequestSpecification.getInstance().getRequestSpecification(baseURI, "application/json"))
			.body(getRequestBody())
			.when()
			.post("/booking");
		
		System.out.println("****************************** createNewBooking ************************************");
		softAssertUtil.assertEquals(response.statusCode(), StatusCode.SUCCESS.code, "Status Code is not 200 instead get "+response.statusCode());
		
		CustomerDetailPOJO responseBody = response.as(CustomerDetailPOJO.class);
		System.out.println("Response Body : "+responseBody.toString());
		
		bookingId = responseBody.getBookingid();
		
		softAssertUtil.assertEquals(getRequestBody().getFirstname(), responseBody.getBooking().getFirstname(), "First Name not as expected");
		softAssertUtil.assertEquals(getRequestBody().getLastname(), responseBody.getBooking().getLastname(), "Last Name not as expected");
		softAssertUtil.assertEquals(getRequestBody().getTotalprice(), responseBody.getBooking().getTotalprice(), "Total Price not as expected");
		softAssertUtil.assertEquals(getRequestBody().isDepositpaid(), responseBody.getBooking().isDepositpaid(), "Deposit Paid not as expected");
		softAssertUtil.assertEquals(getRequestBody().getBookingdates().get("checkin"), responseBody.getBooking().getBookingdates().get("checkin"), "CheckIn Date not as expected");
		softAssertUtil.assertEquals(getRequestBody().getBookingdates().get("checkout"), responseBody.getBooking().getBookingdates().get("checkout"), "CheckOut Date not as expected");
		softAssertUtil.assertEquals(getRequestBody().getAdditionalneeds(), responseBody.getBooking().getAdditionalneeds(), "Additional Needs Date as expected");		
		softAssertUtil.AssertAll();
	}
	
	@Test(description = "RB_08 : Test to validate to update the current Booking with Content-Type : application/json", priority = 8)
	public void updateCurrentBookingPUT() {
		softAssertUtil = new SoftAssertionUtil();
		
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json");
		//headers.put("Cookie", "token="+authToken);
		
		String lastname = "Harishyam";
		BookingDetailPOJO requestBody = getRequestBody();
		requestBody.setLastname(lastname);
		
		Response response = given()
			.spec(UserRequestSpecification.getInstance().getRequestSpecification(baseURI, headers))
			.cookie("token", authToken)
			.body(requestBody)
			.when()
			.put("/booking/"+bookingId);
		
		System.out.println("****************************** updateCurrentBookingPUT ************************************");
		System.out.println("ResponseBody : "+response.body().asString());
		softAssertUtil.assertEquals(response.statusCode(), StatusCode.SUCCESS.code, "Status Code is not 200 instead get "+response.statusCode());
		//BookingDetailPOJO responseBody = response.as(BookingDetailPOJO.class);
		softAssertUtil.AssertAll();
	}
	
	@Test(description = "RB_09 : Test to validate to update the current Booking partially with Content-Type : application/json", priority = 9)
	public void updateCurrentBookingPATCH() {
		softAssertUtil = new SoftAssertionUtil();
		
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "application/json");
		
		Map<String, String> partialBody = new HashMap<>();
		partialBody.put("firstname", "Hari");
		partialBody.put("lastname", "Hari");
		
		Response response = given()
			.spec(UserRequestSpecification.getInstance().getRequestSpecification(baseURI, headers))
			.cookie("token", authToken)
			.body(partialBody)
			.when()
			.patch("/booking/"+bookingId);
		
		System.out.println("****************************** updateCurrentBookingPATCH ************************************");
		System.out.println("ResponseBody : "+response.body().asString());
		softAssertUtil.assertEquals(response.statusCode(), StatusCode.SUCCESS.code, "Status Code is not 200 instead get "+response.statusCode());
		
		//BookingDetailPOJO responseBody = response.as(BookingDetailPOJO.class);
		softAssertUtil.AssertAll();
	}
	
	@Test(description = "RB_10 : Test validate to delete specifig booking", priority=10)
	public void deletecurrentBooking() {
		softAssertUtil = new SoftAssertionUtil();
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		
		Response response = given()
			.spec(UserRequestSpecification.getInstance().getRequestSpecification(baseURI, headers))
			.cookie("token", authToken)
			.when()
			.delete("/booking/"+bookingId);
		
		System.out.println("****************************** deletecurrentBooking ************************************");
		System.out.println("ResponseBody : ["+response.statusCode()+"] : "+response.body().asString());
		softAssertUtil.assertEquals(response.statusCode(), StatusCode.CREATED.code, "Status Code is not 201 instead get "+response.statusCode());
		softAssertUtil.AssertAll();

		
	}
}
