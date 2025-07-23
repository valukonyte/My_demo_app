import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import java.time.Duration;

public class Base {

  protected AppiumDriver driver;
  protected static AppiumDriverLocalService service;

  @BeforeEach
  public void setUp() {
    service = new AppiumServiceBuilder()
            .withIPAddress("127.0.0.1")
            .usingAnyFreePort()
            //.withArgument(() -> "--log-level", "debug")
            .build();
    service.start();

    UiAutomator2Options options = new UiAutomator2Options()
            .setUdid("emulator-5554")
            .setPlatformName("Android")
            .setAutomationName("UiAutomator2")
            .setAppPackage("com.saucelabs.mydemoapp.rn")
            .setAppActivity("MainActivity")
            .eventTimings();

    driver = new AndroidDriver(service.getUrl(), options);
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
  }

  @AfterEach
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }

}
