package org.devicefarm.models;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.openqa.selenium.WebElement;

@Accessors(chain = true)
@Getter
public class DragAndDropOptions {
    WebElement source;
    WebElement target;

    private DragAndDropOptions() {
    }

    public DragAndDropOptions(WebElement source, WebElement target) {
        this.source = source;
        this.target = target;
    }
}
