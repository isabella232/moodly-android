

import java.io.File;
import java.net.URL;
import java.util.List;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasTouchScreen;
import org.openqa.selenium.interactions.TouchScreen;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteTouchScreen;
import org.openqa.selenium.remote.RemoteWebDriver;


public class AppiumTest {


    private AndroidDriver driver;

    @Before
    public void setUp() throws Exception {

        // set up appium
        File appDir = new File("../moodly-android/app/build/outputs/apk/");
        File app = new File(appDir, "app-release.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "appium-test");
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        capabilities.setCapability(CapabilityType.VERSION, "4.2");
        capabilities.setCapability(CapabilityType.PLATFORM, "WINDOW");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("app-package", "com.leanovate.moodly.app");
        capabilities.setCapability("appActivity", ".Settings");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void shouldUpdateSettings() throws InterruptedException {
        updateSettings();
    }


    private void updateSettings() {
        WebElement moodlyId = driver.findElement(By.id("com.leanovate.moodly.app:id/moodlyIdValue"));
        WebElement host = driver.findElement(By.id("com.leanovate.moodly.app:id/hostValue"));
        WebElement updateButton = driver.findElement(By.id("com.leanovate.moodly.app:id/updateSettings"));

        moodlyId.clear();
        moodlyId.sendKeys("bc16b958-d8c5-1004-8b85-dc068a7baf30");
        host.clear();
        host.sendKeys("moodly-alpha.herokuapp.com");
        updateButton.click();
    }


}

