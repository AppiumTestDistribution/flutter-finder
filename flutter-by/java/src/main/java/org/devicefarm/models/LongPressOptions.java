package org.devicefarm.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

@Accessors(chain = true)
@Setter
@Getter
public class LongPressOptions {
    WebElement element;
    Point point;
}
