package userSpecification;

import java.util.Map;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class UserRequestSpecification {
	
	//Singleton Design Pattern
	private static UserRequestSpecification userRequestSpecification;
	private static RequestSpecification requestSpecification;
	
	private UserRequestSpecification() {}
	
	public static synchronized UserRequestSpecification getInstance() {
		if(userRequestSpecification == null) {
			userRequestSpecification = new UserRequestSpecification();
		}
		return userRequestSpecification;
	}
	
	//Builder Design Pattern
	
	public RequestSpecification getRequestSpecification(String uri) {
			requestSpecification = new RequestSpecBuilder()
					.setBaseUri(uri)
					.build();
		return requestSpecification;
	}
	
	public RequestSpecification getRequestSpecification(String uri, String contentType) {
			requestSpecification = new RequestSpecBuilder()
					.setBaseUri(uri)
					.setContentType(contentType)
					.build();		
			return requestSpecification;
	}
	
	public RequestSpecification getRequestSpecification(String uri, String contentType, Map<String, String> queryParam) {
			requestSpecification = new RequestSpecBuilder()
					.setBaseUri(uri)
					.setContentType(contentType)
					.addQueryParams(queryParam)
					.build();	
			return requestSpecification;
	}

	public RequestSpecification getRequestSpecification(String uri, Map<String, String> headers) {
		requestSpecification = new RequestSpecBuilder()
				.setBaseUri(uri)
				.addHeaders(headers)
				.build();	
		return requestSpecification;
	}
	
	public RequestSpecification getRequestSpecification(String uri, Map<String, String> headers, Map<String, String> queryParam) {
		requestSpecification = new RequestSpecBuilder()
				.setBaseUri(uri)
				.addHeaders(headers)
				.addQueryParams(queryParam)
				.build();	
		return requestSpecification;
	}

}
