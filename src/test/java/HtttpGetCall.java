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
}
