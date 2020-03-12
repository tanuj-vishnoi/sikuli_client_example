import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class HtttpGetCall {

	public HtttpGetCall() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}

	public Response getRequest(String url) {
		return RestAssured.given().log().all()
				.expect().when().get(url).then().extract().response();
	}
	
	public String getSeleniumNodeIPAddress(String hubAddress,String sessionId) {
		String res = getRequest(hubAddress+"/grid/api/testsession?session="+sessionId).asString();
		String nodeAddress =  JsonPath.read(res, "$.proxyId");
		System.out.println(nodeAddress);
		return nodeAddress.split("//")[1].split(":")[0];
	}
}
