package org.devicefarm.models;

import lombok.Builder;
import lombok.Getter;
import org.openqa.selenium.WebElement;



@Builder
@Getter
public class ScrollOptions {
    WebElement finder;
    WebElement scrollView;
    ScrollDirection scrollDirection;
    Integer delta;
    Integer maxScrolls;
    Integer settleBetweenScrollsTimeout;
    Integer dragDuration;

    private static ScrollOptionsBuilder builder() {
        return new ScrollOptionsBuilder();
    }

    public static ScrollOptionsBuilder builder(WebElement finder, ScrollDirection direction) {
        return builder().finder(finder).scrollDirection(direction);
    }


    public static enum ScrollDirection {
        UP("up"),
        RIGHT("right"),
        DOWN("down"),
        LEFT("left");

        private final String direction;

        ScrollDirection(String direction) {
            this.direction = direction;
        }

        public String getDirection() {
            return this.direction;
        }
    }
}
