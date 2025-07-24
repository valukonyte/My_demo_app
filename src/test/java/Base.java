import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Base {

    protected AndroidDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException, URISyntaxException {
      DesiredCapabilities caps = new DesiredCapabilities();

      // BrowserStack authentication
      String USERNAME = System.getenv("BROWSERSTACK_USERNAME");
      String ACCESS_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");

      // Device and platform
      caps.setCapability("platformName", "Android");
      caps.setCapability("app", "bs://35bc001c4dd6874cd42b2eb634aaf50302153d19"); // Match the device’s OS version

      // BrowserStack-specific options (no device or OS specified)
      Map<String, Object> bstackOptions = new HashMap<>();
      bstackOptions.put("projectName", "My Demo Appium Project");
      bstackOptions.put("buildName", "GitHub Actions Build");
      bstackOptions.put("sessionName", "Sample Test");
      // BrowserStack will auto-select a device and OS

      caps.setCapability("bstack:options", bstackOptions);

      // Connect to BrowserStack
      URI hubUri = new URI("https://" + USERNAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub");
      driver = new AndroidDriver(hubUri.toURL(), caps);
    }


  @AfterEach
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }

}
