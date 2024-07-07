package org.devicefarm.models;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

@Accessors(chain = true)
@Getter
@Setter
public class DoubleClickOptions {
    WebElement element;
    Point point;
}