
public class SikuliClient {

	private String serverAddress;

	public SikuliClient(String severAddress) {
		this.serverAddress = severAddress;
	}

	public void clickUsingSikuliSever(String imageName) {
		HtttpGetCall get = new HtttpGetCall();
		get.getRequest("http://" + serverAddress + ":4567" + "/action/click/" + imageName);
	}
	
	public void hoverUsingSikuliSever(String imageName) {
		HtttpGetCall get = new HtttpGetCall();
		get.getRequest("http://" + serverAddress + ":4567" + "/action/hover/" + imageName);
	}
}
