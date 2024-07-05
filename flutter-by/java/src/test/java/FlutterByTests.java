import io.appium.java_client.android.options.UiAutomator2Options;
import org.devicefarm.FlutterBy;
import org.devicefarm.FlutterCommands;
import org.devicefarm.models.ScrollOptions;
import org.devicefarm.models.WaitForOptions;
import org.junit.jupiter.api.Test;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.net.URL;


public class FlutterByTests {
    private AndroidDriver driver;

    public void performLogin(String userName, String password) {
//        WebElement userNameTxtField = driver.findElement(FlutterBy.key("username_text_field"));
//        WebElement passWordTxtField = driver.findElement(FlutterBy.key("password_text_field"));
      WebElement loginButton = driver.findElement(FlutterBy.key("login_button"));
//
//        userNameTxtField.clear();
//        userNameTxtField.sendKeys(userName);
//        passWordTxtField.clear();
//        passWordTxtField.sendKeys(password);
        loginButton.click();
        WebElement longPress = driver.findElement(FlutterBy.text("Long Press"));
        //FlutterCommands.waitForVisible(driver, WaitForVisibleOptions.builder().locator(FlutterBy.text("Long Press")).build());
        FlutterCommands.waitForVisible(driver, WaitForOptions.builder().element(longPress).build());
        //FlutterCommands.waitForInVisible(driver, WaitForOptions.builder().element(loginButton).build());
    }

    @BeforeEach
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("Android Emulator")
                .setApp("/Users/saikrishna/Downloads/git/appium-flutter-server/demo-app/build/app/outputs/apk/debug/app-debug.apk")
                .setAutomationName("FlutterIntegration");

        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), options);
    }

    @Test
    public void testCalculatorAddition() {
         performLogin("admin", "1234");
         FlutterCommands.scrollTillVisible(driver,
                 ScrollOptions.builder(driver.findElement(FlutterBy.text("Loader Screen")),
                         ScrollOptions.ScrollDirection.DOWN).build());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
