package functional.com.thoughtworks.twu;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UIFunctionalTest {

    private WebDriver webDriver;
    private String username;
    private String password;

    @Before
    public void setUp() {
        webDriver = new FirefoxDriver();
        username = "test.twu";
        password = "Th0ughtW0rks@12";
    }


    @Test
    public void shouldCheckFocusOfHomeFromNavigationBar() throws InterruptedException {
        logIn();
        webDriver.findElement(By.id("homeLink")).click();

        WebElement homeActive = webDriver.findElement(By.id("homeActive"));


        assertThat(homeActive.getAttribute("class").contains("active"), is(true));

        WebElement createOfferActive = webDriver.findElement(By.id("createOfferActive"));
        assertThat(createOfferActive.getAttribute("class").contains("active"), is(false));

        WebElement browseOfferActive = webDriver.findElement(By.id("browseOfferActive"));
        assertThat(browseOfferActive.getAttribute("class").contains("active"), is(false));

    }

    @Test
    public void shouldCheckFocusOfCreateOfferFromNavigationBar() throws InterruptedException {
        logIn();
        webDriver.findElement(By.id("createOfferLink")).click();

        WebElement createOfferActive = webDriver.findElement(By.id("createOfferActive"));

        assertThat(createOfferActive.getAttribute("class").contains("active"), is(true));

        WebElement homeActive = webDriver.findElement(By.id("homeActive"));
        assertThat(homeActive.getAttribute("class").contains("active"), is(false));

        WebElement browseOfferActive = webDriver.findElement(By.id("browseOfferActive"));
        assertThat(browseOfferActive.getAttribute("class").contains("active"), is(false));

    }

    @Test
    public void shouldCheckFocusOfBrowseOfferFromNavigationBar() throws InterruptedException {
        logIn();
        webDriver.findElement(By.id("browseOfferLink")).click();

        WebElement browseOfferActive = webDriver.findElement(By.id("browseOfferActive"));

        assertThat(browseOfferActive.getAttribute("class").contains("active"), is(true));

        WebElement homeActive = webDriver.findElement(By.id("homeActive"));
        assertThat(homeActive.getAttribute("class").contains("active"), is(false));

        WebElement createOfferActive = webDriver.findElement(By.id("createOfferActive"));
        assertThat(createOfferActive.getAttribute("class").contains("active"), is(false));

    }

    @After
    public void tearDown() throws Exception {
        webDriver.close();
    }

    private void logIn() throws InterruptedException {
        webDriver.get("http://127.0.0.1:8080/twu/");

        webDriver.findElement(By.id("username")).sendKeys(username);
        webDriver.findElement(By.id("password")).sendKeys(password);
        webDriver.findElement(By.name("submit")).click();
        Thread.sleep(2000);
    }
}
