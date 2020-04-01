import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ActionsClass {
	
	public WebDriver driver;
	
	public ActionsClass(WebDriver driver) {
		this.driver = driver;
	}

	public void launchApplication(String url) {
		this.driver.get(url);
	}
	
	public void maximizeBrower() {
		this.driver.manage().window().maximize();
	}
	
	protected void scrollDown(WebElement element) {
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView(true);", element);
	}
	
	protected void scrollInBetween(WebElement element) {
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView(false);", element);
	}

	
}
