package org.devicefarm.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.devicefarm.FlutterBy;
import org.openqa.selenium.WebElement;

import java.time.Duration;

@Accessors(chain = true)
@Getter
@Setter
public class ScrollOptions {
    FlutterBy.FlutterLocator finder;
    WebElement scrollView;
    ScrollDirection scrollDirection;
    Integer delta;
    Integer maxScrolls;
    Integer settleBetweenScrollsTimeout;
    Duration dragDuration;

    private ScrollOptions() {
    }

    public ScrollOptions(FlutterBy.FlutterLocator finder, ScrollDirection scrollDirection) {
        this.finder = finder;
        this.scrollDirection = scrollDirection;
    }

    @Getter
    public static enum ScrollDirection {
        UP("up"),
        RIGHT("right"),
        DOWN("down"),
        LEFT("left");

        private final String direction;

        ScrollDirection(String direction) {
            this.direction = direction;
        }
    }
}
