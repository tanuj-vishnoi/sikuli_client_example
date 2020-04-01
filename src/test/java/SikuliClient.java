import java.util.List;

import io.restassured.response.Response;

public class SikuliClient {

	private String serverAddress;

	public SikuliClient(String severAddress) {
		this.serverAddress = severAddress;
		System.out.println("Sikuli server is "+"http://" + serverAddress + ":4567");
	}

	public void clickUsingSikuliSever(String imageName) {
		HtttpGetCall get = new HtttpGetCall();
		get.getRequest("http://" + serverAddress + ":4567" + "/action/click/" + imageName);
	}
	
	public void hoverUsingSikuliSever(String imageName) {
		HtttpGetCall get = new HtttpGetCall();
		get.getRequest("http://" + serverAddress + ":4567" + "/action/hover/" + imageName);
	}
	
	public void clickUsingRelativeImages(List<Object> body) {
		HtttpGetCall get = new HtttpGetCall();
		Response res = get.getRequest("http://" + serverAddress + ":4567" +"/relativeaction/click",body);
		System.out.println("Server response after performing event is "+res.getStatusLine());
	}
}
