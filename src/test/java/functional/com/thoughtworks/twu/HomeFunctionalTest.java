package functional.com.thoughtworks.twu;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HomeFunctionalTest {

    private WebDriver webDriver;

    @Before
    public void setUp() {
       webDriver = new FirefoxDriver();
    }

    @Test
    public void shouldShowTryMeLink() {
        webDriver.get("http://google.com");
        WebElement testElement =webDriver.findElement(By.name("q"));
        testElement.sendKeys("Cheese");
        testElement.submit();

        assertThat(webDriver.getTitle(),is("Google"));

    }

    @After
    public void tearDown() throws Exception{
        webDriver.close();
    }

}
