package org.fluentlenium.integration;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.integration.localtest.LocalFluentCase;
import org.fluentlenium.integration.localtest.SauceLabsFluentCase;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class AlertOnLabsTest extends SauceLabsFluentCase {

    @Page
    private AlertPage alertPage;

    @Override
    public WebDriver getDefaultDriver() {
        return new FirefoxDriver();
    }

    @Override
    public void setDefaultConfig() {
        withDefaultSearchWait(5, TimeUnit.HOURS);
    }

    @Test
    public void should_accept() {
        // Given
        // When
        alertPage.go();
        alertPage.maximizeWindow();
        // Then
        alertPage.isAt();
        click("#alertBox");
        alert().accept();
        assertThat($("#result").getText()).isEqualTo("alertBox");

    }

    @Test
    public void should_confirm() {
        // Given
        // When
        alertPage.go();
        alertPage.maximizeWindow();
        // Then
        alertPage.isAt();
        click("#confirmBox");
        alert().accept();
        assertThat($("#result").getText()).isEqualTo("confirmBox OK");

    }

    @Test
    public void should_dismiss() {
        // Given
        // When
        alertPage.go();
        alertPage.maximizeWindow();
        // Then
        alertPage.isAt();
        click("#confirmBox");
        alert().dismiss();
        assertThat($("#result").getText()).isEqualTo("confirmBox CANCEL");

    }

    @Test
    public void should_get_text() {
        // Given
        // When
        alertPage.go();
        alertPage.maximizeWindow();
        // Then
        alertPage.isAt();
        click("#alertBox");
        assertThat(alert().getText()).isEqualTo("alertBox");
        alert().accept();

    }

    @Test
    public void should_prompt() {
        // Given
        // When
        alertPage.go();
        alertPage.maximizeWindow();
        // Then
        alertPage.isAt();
        click("#promptBox");
        alert().prompt("it works");
        assertThat($("#result").getText()).isEqualTo("it works");

    }

}

class AlertPage extends FluentPage {
    @Override
    public String getUrl() {
        return LocalFluentCase.BASE_URL + "alert.html";
    }

    @Override
    public void isAt() {
        assertThat($("#result").first().getText()).isEmpty();
    }
}
