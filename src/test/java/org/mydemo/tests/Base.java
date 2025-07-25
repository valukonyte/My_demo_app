package org.mydemo.tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

@ExtendWith(AllureJunit5.class)
public class Base {
  protected AndroidDriver driver;
  private final Properties properties = new Properties();

  @BeforeEach
  public void setUp() throws URISyntaxException, IOException {
    // Load credentials from config if needed
    try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
      if (input != null) {
        properties.load(input);
      }
    }

    String USERNAME = System.getenv("BROWSERSTACK_USERNAME");
    String ACCESS_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY");
    if (USERNAME == null || ACCESS_KEY == null) {
      throw new RuntimeException("BrowserStack credentials not set. Please export BROWSERSTACK_USERNAME and BROWSERSTACK_ACCESS_KEY.");
    }

    // Device, OS, and app are configurable via env variables
    String device = System.getenv().getOrDefault("DEVICE_NAME", "Google Pixel 5");
    String osVersion = System.getenv().getOrDefault("OS_VERSION", "12.0");
    String appId = System.getenv().getOrDefault("APP_ID", "bs://35bc001c4dd6874cd42b2eb634aaf50302153d19");

    UiAutomator2Options options = new UiAutomator2Options();
    options.setPlatformName("Android");
    options.setDeviceName(device);
    options.setPlatformVersion(osVersion);
    options.setApp(appId);
    options.setNewCommandTimeout(Duration.ofSeconds(60));

    // BrowserStack-specific settings (still as capabilities)
    options.setCapability("bstack:options", new java.util.HashMap<String, Object>() {{
      put("projectName", "My Demo Appium Project");
      put("buildName", "GitHub Actions Build");
      put("sessionName", "Sample Test");
    }});

    URI hubUri = new URI("https://" + USERNAME + ":" + ACCESS_KEY + "@hub-cloud.browserstack.com/wd/hub");
    driver = new AndroidDriver(hubUri.toURL(), options);

    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
  }

  protected String getUsername() {
    return properties.getProperty("username");
  }

  protected String getPassword() {
    return properties.getProperty("password");
  }

  @AfterEach
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }
}