import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.devicefarm.FlutterBy;
import org.devicefarm.FlutterDriver;
import org.devicefarm.models.*;
import org.junit.jupiter.api.*;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;


public class FlutterByTests {
    private AppiumDriver driver;
    private FlutterDriver flutterDriver;
    protected static final int PORT = 4723;
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "1234";
    private static AppiumDriverLocalService service;
    public void performLogin() {
        WebElement userNameTxtField = flutterDriver.getDriver().findElement(FlutterBy.key("username_text_field"));
        WebElement passWordTxtField = flutterDriver.getDriver().findElement(FlutterBy.key("password_text_field"));
        WebElement loginButton = flutterDriver.getDriver().findElement(FlutterBy.key("LoginButton"));

        userNameTxtField.clear();
        userNameTxtField.sendKeys(USERNAME);
        passWordTxtField.clear();
        passWordTxtField.sendKeys(PASSWORD);
        loginButton.click();
    }

    public void openScreen(String screenTitle) {
        ScrollOptions scrollOptions = new ScrollOptions(FlutterBy.text(screenTitle), ScrollOptions.ScrollDirection.DOWN);
        WebElement element = flutterDriver.scrollTillVisible(scrollOptions);
        element.click();
    }

    @BeforeAll
    public static void beforeClass() {
        service = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                .usingPort(PORT)
                .withLogFile(new File("appium.log"))
                .withArgument(GeneralServerFlag.BASEPATH,"/wd/hub").build();
        service.start();
    }

    @AfterAll public static void afterClass() {
        if (service != null) {
            service.stop();
        }
    }

    @BeforeEach
    public void setUp() throws MalformedURLException {
        if(System.getenv("Platform").equalsIgnoreCase("iOS")) {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability("platformName", "iOS");
            desiredCapabilities.setCapability("udid", System.getenv("UDID"));
            desiredCapabilities.setCapability("automationName", "FlutterIntegration");
            desiredCapabilities.setCapability("app", System.getenv("APP_PATH"));
            desiredCapabilities.setCapability("flutterSystemPort", 31212);
            desiredCapabilities.setCapability("usePreinstalledWDA", true);

            driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
        } else {
            UiAutomator2Options options = new UiAutomator2Options()
                    .setApp(System.getenv("APP_PATH"))
                    .setAutomationName("FlutterIntegration");
            driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), options);
        }
        flutterDriver = new FlutterDriver(driver);
    }


    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void doubleTapTest() {
        performLogin();
        openScreen("Double Tap");
        WebElement doubleTap = flutterDriver.getDriver().findElement(FlutterBy.key("double_tap_button"))
                .findElement(FlutterBy.text("Double Tap"));

        Assertions.assertEquals(doubleTap.getText(), "Double Tap");
        flutterDriver.performDoubleClick(new DoubleClickOptions().setElement(doubleTap));

        WebElement successMessage = flutterDriver.getDriver().findElement(FlutterBy.text("Double Tap Successful"));
        Assertions.assertEquals(successMessage.getText(), "Double Tap Successful");

        flutterDriver.getDriver().findElement(FlutterBy.text("Ok")).click();
        flutterDriver.performDoubleClick(new DoubleClickOptions()
                .setElement(doubleTap)
                .setPoint(new Point(10, 0))
        );
        successMessage = flutterDriver.getDriver().findElement(FlutterBy.text("Double Tap Successful"));
        Assertions.assertEquals(successMessage.getText(), "Double Tap Successful");
    }

    @Test
    public void waitTest() {
        performLogin();
        openScreen("Lazy Loading");
        WebElement messageField = flutterDriver.getDriver().findElement(FlutterBy.key("message_field"));
        WebElement toggleButton = flutterDriver.getDriver().findElement(FlutterBy.key("toggle_button"));

        WaitForOptions waitOption = new WaitForOptions()
                .setLocator(FlutterBy.key("message_field"))
                .setTimeout(Duration.ofSeconds(10));
        Assertions.assertEquals(messageField.getText(), "Hello world");

        toggleButton.click();
        flutterDriver.waitForInVisible(waitOption);
        Assertions.assertEquals(flutterDriver.getDriver().findElements(FlutterBy.semanticsLabel("message_field")).size(), 0);

        toggleButton.click();
        flutterDriver.waitForVisible(waitOption);
        Assertions.assertEquals(flutterDriver.getDriver().findElement(FlutterBy.key("message_field")), "Hello world");
    }

    @Test
    public void longPressTest() {
        performLogin();
        openScreen("Long Press");
        WebElement longPressButton = flutterDriver.getDriver().findElement(FlutterBy.key("long_press_button"));
        flutterDriver.performLongPress(new LongPressOptions().setElement(longPressButton));
        Assertions.assertTrue(flutterDriver.getDriver().findElement(FlutterBy.text("It was a long press")).isDisplayed());
    }

    @Test
    public void dragAndDropTest() {
        performLogin();
        openScreen("Drag & Drop");
        WebElement dragElement = flutterDriver.getDriver().findElement(FlutterBy.key("drag_me"));
        WebElement dropElement = flutterDriver.getDriver().findElement(FlutterBy.key("drop_zone"));
        flutterDriver.performDragAndDrop(new DragAndDropOptions(dragElement, dropElement));
        Assertions.assertTrue(flutterDriver.getDriver().findElement(FlutterBy.text("The box is dropped")).isDisplayed());
    }
}
