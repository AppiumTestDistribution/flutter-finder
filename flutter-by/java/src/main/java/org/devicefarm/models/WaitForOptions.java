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
public class WaitForOptions {
    private WebElement element;
    private FlutterBy.FlutterLocator locator;
    private Duration timeout;
}
