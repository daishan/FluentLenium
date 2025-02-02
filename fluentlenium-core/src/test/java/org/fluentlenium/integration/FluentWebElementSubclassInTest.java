package org.fluentlenium.integration;

import org.fluentlenium.core.domain.FluentWebElement;
import org.fluentlenium.integration.localtest.LocalFluentCase;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

public class FluentWebElementSubclassInTest extends LocalFluentCase {
    ALink linkToPage2;

    public static class ALink extends FluentWebElement {
        public ALink(WebElement webElement) {
            super(webElement);
        }

        public void clickIfDisplayed() {
            if (isDisplayed()) {
                click();
            }
        }
    }

    @Test
    public void when_web_element_in_test_then_they_are_instanciated() {
        goTo(LocalFluentCase.DEFAULT_URL);
        linkToPage2.clickIfDisplayed();
        assertThat(url()).isEqualTo(LocalFluentCase.BASE_URL + "page2.html");
    }
}
