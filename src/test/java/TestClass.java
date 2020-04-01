import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestClass {
	
	String sikuliServerAddress = "localhost";

	//@Test
	public void test1() {
		ActionsClass actions = new ActionsClass(getRemoteDriver());
		actions.launchApplication("https://canvasjs.com/docs/charts/chart-options/");
		actions.maximizeBrower();
		actions.driver.switchTo().frame(actions.driver.findElement(By.id("preview1")));
		WebElement el = actions.driver.findElements(By.cssSelector("[class=\"canvasjs-chart-canvas\"]")).get(0);
		actions.scrollDown(el);
		/**
		 * this should be ip of node
		 */
		SikuliClient sikuliClient = new SikuliClient(sikuliServerAddress);
		sikuliClient.clickUsingSikuliSever("90.png");
		System.out.println("**********"+actions.driver.findElements(By.cssSelector("[class=\"canvasjs-chart-tooltip\"]")).get(0).getText());
		
	}
	
	@Test
	public void test2() throws IOException {
		ActionsClass actions = new ActionsClass(getDriver());
		actions.launchApplication("https://canvasjs.com/docs/charts/chart-options/");
		actions.maximizeBrower();
		actions.driver.switchTo().frame(actions.driver.findElement(By.id("preview3")));
		WebElement el = actions.driver.findElements(By.cssSelector("[class=\"canvasjs-chart-canvas\"]")).get(0);
		actions.scrollDown(el);
		actions.scrollInBetween(el);
		//JsonPath.read(readFromStream().toString(), "$");
		SikuliClient sikuliClient = new SikuliClient(sikuliServerAddress);
		List<Object> josnPayload = JsonPath.parse(new File(System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"relativeimage.json")).read("$");
		sikuliClient.clickUsingRelativeImages(josnPayload);
		System.out.println("**********"+actions.driver.findElements(By.cssSelector("[class=\"canvasjs-chart-tooltip\"]")).get(0).getText());
		
	}
	
	
	public WebDriver getDriver() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		return driver;
	}
	
	public WebDriver getRemoteDriver() {
		RemoteWebDriver  driver = null;
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		//DesiredCapabilities cap = new DesiredCapabilities();
		//cap.setBrowserName("chrome");
		System.out.println(cap);
		try {
			driver =  new  RemoteWebDriver(new URL("http://10.0.10.133:4445/wd/hub"), cap);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SessionId s = driver.getSessionId();
		HtttpGetCall get = new HtttpGetCall();
		String sikuliServer = get.getSeleniumNodeIPAddress("http://10.0.10.133:4445", s.toString());
		this.sikuliServerAddress = sikuliServer;
		return driver;
	}
}
