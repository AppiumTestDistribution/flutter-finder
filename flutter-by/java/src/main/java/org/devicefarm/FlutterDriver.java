package org.devicefarm;

import org.devicefarm.models.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FlutterDriver {
    private final WebDriver driver;

    public FlutterDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void waitForVisible(WaitForOptions option) {
        FlutterCommands.waitForVisible(driver, option);
    }

    public void waitForInVisible(WaitForOptions option) {
        FlutterCommands.waitForInVisible(driver, option);
    }

    public WebElement scrollTillVisible(ScrollOptions option) {
        return FlutterCommands.scrollTillVisible(driver, option);
    }

    public void performDoubleClick(DoubleClickOptions option) {
        FlutterCommands.performDoubleClick(driver, option);
    }

    public void performLongPress(LongPressOptions option) {
        FlutterCommands.performLongPress(driver, option);
    }

    public void performDragAndDrop(DragAndDropOptions option) {
        FlutterCommands.performDragAndDrop(driver, option);
    }
}
