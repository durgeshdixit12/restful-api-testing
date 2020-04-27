package deck.api.test;

import java.util.Map;
import org.json.simple.JSONObject;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredClient {

	public Response execute(String uri, Method name, JSONObject requestBody, Map<String, String> headers, Map<String, Object> params) {
		
		RestAssured.baseURI = uri;
		RequestSpecification httpRequest = RestAssured.given();
		if(requestBody != null) {
			httpRequest.body(requestBody.toJSONString());
		}
		if(headers != null && headers.size() > 0) {
			httpRequest.headers(headers);
		}
		if(params != null && params.size() > 0) {
			httpRequest.params(params);
		}
		return httpRequest.request(name);
	}
}
