package functional.com.thoughtworks.twu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HomeFunctionalTest {

    private WebDriver webDriver;

    @Before
    public void setUp() {
        webDriver = new FirefoxDriver();
    }

    @Test
    public void showGoToCreateOfferPage() throws InterruptedException {
        logIn();

        Thread.sleep(1000);
        webDriver.findElement(By.id("createOffer")).click();

        String expectedUrl = "http://127.0.0.1:8080/twu/offer/create";
        assertThat(webDriver.getCurrentUrl(), is(expectedUrl));

    }



    private void logIn() {
        webDriver.get("http://127.0.0.1:8080/twu/");
        String username = "test.twu";
        String password = "Th0ughtW0rks@12";

        webDriver.findElement(By.id("username")).sendKeys(username);
        webDriver.findElement(By.id("password")).sendKeys(password);
        webDriver.findElement(By.name("submit")).click();
    }

    @After
    public void tearDown() throws Exception {
        webDriver.close();
    }
}
