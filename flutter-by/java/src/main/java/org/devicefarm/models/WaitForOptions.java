package org.devicefarm.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.devicefarm.FlutterBy;
import org.openqa.selenium.WebElement;

@Setter
@Getter
@Builder
public class WaitForOptions {
    private WebElement element;
    private FlutterBy.FlutterLocator locator;
}
