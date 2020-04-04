import java.util.List;

import io.restassured.response.Response;

public class SikuliClient {

	private String serverAddress;
	private String portIs="8100";

	public SikuliClient(String severAddress) {
		this.serverAddress = severAddress;
		System.out.println("Sikuli server is "+"http://" + serverAddress + ":"+portIs);
	}

	public void clickUsingSikuliSever(String imageName) {
		HtttpGetCall get = new HtttpGetCall();
		get.getRequest("http://" + serverAddress + ":"+portIs + "/action/click/" + imageName);
	}
	
	public void hoverUsingSikuliSever(String imageName) {
		HtttpGetCall get = new HtttpGetCall();
		get.getRequest("http://" + serverAddress + ":"+portIs + "/action/hover/" + imageName);
	}
	
	public void clickUsingRelativeImages(List<Object> body) {
		HtttpGetCall get = new HtttpGetCall();
		Response res = get.getRequest("http://" + serverAddress + ":"+portIs +"/relativeaction/click",body);
		System.out.println("Server response after performing event is "+res.getStatusLine());
	}
}
