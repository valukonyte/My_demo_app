import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

public class Base {

    private AndroidDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException, URISyntaxException {
      DesiredCapabilities caps = new DesiredCapabilities();

      // BrowserStack authentication
      String USERNAME = System.getenv("BROWSERSTACK_USERNAME");
      String ACCESS_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");

      // Device and platform
      caps.setCapability("platformName", "Android");
      caps.setCapability("device", "Samsung Galaxy S22 Ultra");  // You can change to another available device
      caps.setCapability("os_version", "12.0");        // Match the device’s OS version
      caps.setCapability("app", "bs://35bc001c4dd6874cd42b2eb634aaf50302153d19");


      // Optional metadata for BrowserStack dashboard
      caps.setCapability("project", "My Demo Appium Project");
      caps.setCapability("build", "GitHub Actions Build");
      caps.setCapability("name", "Sample Test");

      // Connect to BrowserStack hub
      URI hubUri = new URI("https://" + USERNAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub");
      driver = new AndroidDriver(hubUri.toURL(), caps);

      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }


  @AfterEach
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }

}
