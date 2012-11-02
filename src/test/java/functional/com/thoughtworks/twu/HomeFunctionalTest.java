package functional.com.thoughtworks.twu;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static junit.framework.Assert.assertEquals;
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
        webDriver.findElement(By.name("title")).sendKeys("TITLE IN TEST");

        Select select = new Select(webDriver.findElement(By.tagName("select")));
        select.selectByValue("Cars");

        webDriver.findElement(By.name("description")).sendKeys("To pass the test or not, this is a question");
        webDriver.findElement(By.name("submit")).click();
        Thread.sleep(1000);
        String expectedUrl = "http://127.0.0.1:8080/twu/home.ftl";
        assertThat(expectedUrl, is(webDriver.getCurrentUrl()));
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
