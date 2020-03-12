import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestClass {

	@Test
	public void test1() {
		ActionsClass actions = new ActionsClass(getDriver());
		actions.launchApplication("https://canvasjs.com/docs/charts/chart-options/");
		actions.maximizeBrower();
		actions.driver.switchTo().frame(actions.driver.findElement(By.id("preview1")));
		WebElement el = actions.driver.findElements(By.cssSelector("[class=\"canvasjs-chart-canvas\"]")).get(0);
		actions.scrollDown(el);
		/**
		 * this should be ip of node
		 */
		SikuliClient sikuliClient = new SikuliClient("localhost");
		sikuliClient.clickUsingSikuliSever("90.png");
		System.out.println("**********"+actions.driver.findElements(By.cssSelector("[class=\"canvasjs-chart-tooltip\"]")).get(0).getText());
		
	}
	
	
	public WebDriver getDriver() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		return driver;
	}
	
	public WebDriver getRemoteDriver() {
		RemoteWebDriver  driver = null;
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setBrowserName("chrome");
		try {
			driver =  new  RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SessionId s = driver.getSessionId();
		return driver;
		
		
	}
}
