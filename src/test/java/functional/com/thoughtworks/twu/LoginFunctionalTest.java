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
import static org.junit.Assert.assertNotNull;

public class LoginFunctionalTest {

    private WebDriver webDriver;

    @Before
    public void setUp() {
        webDriver = new FirefoxDriver();
    }

//    @Test
//    public void shouldShowErrorWhenEmptyFields() {
//        webDriver.get("https://cas.thoughtworks.com/cas/login");
//        webDriver.findElement(By.name("submit")).click();
//
//        WebElement errorMsgElement = webDriver.findElement(By.id("msg"));
//
//        String expectedErrorMsg = "Password is a required field.\n" +
//                "Username is a required field.";
//        assertThat(errorMsgElement.getText(), is(expectedErrorMsg));
//    }

    @Test
    public void shouldShowErrorMsgWhenInvalidCredentials() {
        webDriver.get("https://cas.thoughtworks.com/cas/login");
        webDriver.findElement(By.id("username")).sendKeys("blah");
        webDriver.findElement(By.id("password")).sendKeys("blah");
        webDriver.findElement(By.name("submit")).click();

        WebElement errorMsgElement = webDriver.findElement(By.id("msg"));

        String expectedErrorMsg = "The credentials you provided cannot be determined to be authentic.";
        assertThat(errorMsgElement.getText(), is(expectedErrorMsg));
    }

    @Test
    public void shouldLoginSuccessfully() {
        webDriver.get("http://127.0.0.1:8080/twu/");
        String username = "test.twu";
        String password = "Th0ughtW0rks@12";

        webDriver.findElement(By.id("username")).sendKeys(username);
        webDriver.findElement(By.id("password")).sendKeys(password);
        webDriver.findElement(By.name("submit")).click();
        WebElement clickButton = webDriver.findElement(By.id("createOffer"));
        assertNotNull(clickButton);

    }

    @Test
    public void shouldBeAbleToPerformSSO() throws InterruptedException {
        webDriver.get("http://castest.thoughtworks.com");
        String username = "test.twu";
        String password = "Th0ughtW0rks@12";

        webDriver.findElement(By.id("username")).sendKeys(username);
        webDriver.findElement(By.id("password")).sendKeys(password);
        webDriver.findElement(By.name("submit")).click();
        Thread.sleep(2000);
        webDriver.get("http://127.0.0.1:8080/twu/");

        Thread.sleep(2000);
        WebElement userNameOnShowPage = webDriver.findElement(By.id("username"));
        assertThat(userNameOnShowPage.getText().contains(username), is(true));

    }

    @After
    public void tearDown() {
        webDriver.close();
    }

}
