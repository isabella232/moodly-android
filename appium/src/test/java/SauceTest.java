import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.junit.SauceOnDemandTestWatcher;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.URL;

/**
 * Created by claus on 04/03/15.
 */
public class SauceTest implements SauceOnDemandSessionIdProvider {

    /**
     * Constructs a {@link com.saucelabs.common.SauceOnDemandAuthentication} instance using the supplied user name/access key.  To use the authentication
     * supplied by environment variables or from an external file, use the no-arg {@link SauceOnDemandAuthentication} constructor.
     */
    public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(System.getenv("SAUCE_USERNAME"), System.getenv("SAUCE_ACCESS_KEY"));

    /**
     * JUnit Rule which will mark the Sauce Job as passed/failed when the test succeeds or fails.
     */
    @Rule
    public SauceOnDemandTestWatcher resultReportingTestWatcher = new SauceOnDemandTestWatcher(this, authentication);

    private AndroidDriver driver;
    private String sessionId;

    @Before
    public void setUp() throws Exception {

        // set up appium
        DesiredCapabilities caps = new DesiredCapabilities();  //DesiredCapabilities.android();
        caps.setCapability("appiumVersion", "1.3.6");
        caps.setCapability("deviceName","Android Emulator");
        caps.setCapability("device-orientation", "portrait");
        caps.setCapability("browserName", "");
        caps.setCapability("platformVersion","4.4");
        caps.setCapability("platformName","Android");
        caps.setCapability("app","sauce-storage:app-release.apk");
        caps.setCapability("appPackage","com.leanovate.moodly.app");
        caps.setCapability("appActivity",".Settings");

        driver = new AndroidDriver(new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub"), caps);

        this.sessionId = (((RemoteWebDriver) driver).getSessionId()).toString();
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

    @Override
    public String getSessionId() {
        return this.sessionId;
    }
}
