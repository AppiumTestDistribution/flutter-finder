package org.devicefarm;

import org.devicefarm.models.ScrollOptions;
import org.devicefarm.models.WaitForOptions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class FlutterCommands {

    private static final String WAIT_FOR_VISIBLE_COMMAND = "waitForVisible";
    private static final String WAIT_FOR_INVISIBLE_COMMAND = "waitForAbsent";
    private static final String SCROLL_TILL_VISIBLE = "scrollTillVisible";


    public static void waitForVisible(WebDriver driver, WaitForOptions option) {
        Map<String, Object> args = new HashMap<>();
        args.put("element", option.getElement());
        if(option.getLocator() != null) {
            args.put("locator", option.getLocator().toJson());
        }
        executeScript(driver, WAIT_FOR_VISIBLE_COMMAND, args);
    }

    public static void waitForInVisible(WebDriver driver, WaitForOptions option) {
        Map<String, Object> args = new HashMap<>();
        args.put("element", option.getElement());
        if(option.getLocator() != null) {
            args.put("locator", option.getLocator().toJson());
        }
        executeScript(driver, WAIT_FOR_INVISIBLE_COMMAND, args);
    }


    public static void scrollTillVisible(WebDriver driver, ScrollOptions option) {
        Map<String, Object> args = new HashMap<>();
        args.put("finder", option.getFinder());
        args.put("scrollView", option.getScrollView());
        args.put("scrollDirection", option.getScrollDirection());
        args.put("delta", option.getDelta());
        args.put("maxScrolls", option.getMaxScrolls());
        args.put("settleBetweenScrollsTimeout", option.getSettleBetweenScrollsTimeout());
        args.put("dragDuration", option.getDragDuration());

        executeScript(driver, SCROLL_TILL_VISIBLE, args);
    }

    private static Object executeScript(WebDriver driver, String scriptName, Map<String, Object> args) {
        return ((JavascriptExecutor)driver).executeScript(String.format("flutter: %s", scriptName), args);
    }

}
