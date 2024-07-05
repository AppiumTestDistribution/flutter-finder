package org.devicefarm;

import org.devicefarm.models.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

public class FlutterCommands {

    private static final String FLUTTER_COMMAND_PREFIX = "flutter";

    private static final String WAIT_FOR_VISIBLE_COMMAND = "waitForVisible";
    private static final String WAIT_FOR_INVISIBLE_COMMAND = "waitForAbsent";
    private static final String SCROLL_TILL_VISIBLE = "scrollTillVisible";
    private static final String DOUBLE_CLICK = "doubleClick";
    private static final String LONG_PRESS = "longPress";
    private static final String DRAG_AND_DROP = "dragAndDrop";


    public static void waitForVisible(WebDriver driver, WaitForOptions option) {
        executeScript(driver, WAIT_FOR_VISIBLE_COMMAND, parseWaitForOption(option));
    }

    public static void waitForInVisible(WebDriver driver, WaitForOptions option) {
        executeScript(driver, WAIT_FOR_INVISIBLE_COMMAND, parseWaitForOption(option));
    }


    public static WebElement scrollTillVisible(WebDriver driver, ScrollOptions option) {
        Map<String, Object> args = new HashMap<>();
        args.put("finder", option.getFinder().toJson());
        args.put("scrollView", option.getScrollView());
        args.put("delta", option.getDelta());
        args.put("maxScrolls", option.getMaxScrolls());
        args.put("settleBetweenScrollsTimeout", option.getSettleBetweenScrollsTimeout());

        if (option.getScrollDirection() != null) {
            args.put("scrollDirection", option.getScrollDirection().getDirection());
        }
        if (option.getDragDuration() != null) {
            args.put("dragDuration", option.getDragDuration().getSeconds());
        }

        return (WebElement) executeScript(driver, SCROLL_TILL_VISIBLE, args);
    }

    public static void performDoubleClick(WebDriver driver, DoubleClickOptions option) {
        Map<String, Object> args = new HashMap<>();
        args.put("origin", option.getElement());
        if (option.getPoint() != null) {
            args.put("offset", parsePoint(option.getPoint()));
        }

        executeScript(driver, DOUBLE_CLICK, args);
    }

    public static void performLongPress(WebDriver driver, LongPressOptions option) {
        Map<String, Object> args = new HashMap<>();
        args.put("origin", option.getElement());
        if (option.getPoint() != null) {
            args.put("offset", parsePoint(option.getPoint()));
        }
        executeScript(driver, LONG_PRESS, args);
    }

    public static void performDragAndDrop(WebDriver driver, DragAndDropOptions option) {
        Map<String, Object> args = new HashMap<>();
        args.put("source", option.getSource());
        args.put("target", option.getTarget());

        executeScript(driver, DRAG_AND_DROP, args);
    }

    private static Object executeScript(WebDriver driver, String scriptName, Map<String, Object> args) {
        String commandName = String.format("%s: %s", FLUTTER_COMMAND_PREFIX, scriptName);
        return ((JavascriptExecutor) driver).executeScript(commandName, args);
    }


    private static Map<String, Object> parsePoint(Point point) {
        return new HashMap<>() {{
            put("x", point.x);
            put("y", point.y);
        }};
    }

    private static Map<String, Object> parseWaitForOption(WaitForOptions option) {
        Map<String, Object> args = new HashMap<>();
        args.put("element", option.getElement());
        if (option.getLocator() != null) {
            args.put("locator", option.getLocator().toJson());
        }
        if (option.getTimeout() != null) {
            args.put("timeout", option.getTimeout().getSeconds());
        }
        return args;
    }
}
