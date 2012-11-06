package functional.com.thoughtworks.twu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class CreateOfferFunctionalTest {


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
    public void showGoToViewAnOfferPage() throws InterruptedException {
        logIn();

        Thread.sleep(1000);
        webDriver.findElement(By.id("createOffer")).click();
        webDriver.findElement(By.name("title")).sendKeys("TITLE IN TEST");

        Select select = new Select(webDriver.findElement(By.tagName("select")));
        select.selectByValue("Cars");

        webDriver.findElement(By.name("description")).sendKeys("To pass the test or not, this is a question");
        webDriver.findElement(By.name("submit")).click();
        Thread.sleep(1000);
        String expectedUrl = "http://127.0.0.1:8080/twu/offer/viewAnOffer?username=test.twu";
        assertThat(expectedUrl, is(webDriver.getCurrentUrl()));
    }

    @Test
    public void shouldHaveAnElementToEnterDescription() {
        logIn();

        webDriver.findElement(By.id("createOffer")).click();
        WebElement titleTextBox = webDriver.findElement(By.name("description"));

        assertThat(titleTextBox.isDisplayed(), is(true));
    }

    @Test
    public void shouldHaveAnElementToSelectCategory() {
        logIn();

        webDriver.findElement(By.id("createOffer")).click();
        WebElement categoryList = webDriver.findElement(By.name("category"));

        assertThat(categoryList.isDisplayed(), is(true));
    }

    @Test
    public void shouldHaveAnElementToEnterTitle() {
        logIn();

        webDriver.findElement(By.id("createOffer")).click();
        WebElement titleTextBox = webDriver.findElement(By.name("title"));

        assertThat(titleTextBox.isDisplayed(), is(true));
    }

    @Test
    public void shouldCheckExistenceOfCreateOfferLink() {
        logIn();

        WebElement clickButton = webDriver.findElement(By.id("createOffer"));
        assertNotNull(clickButton);
    }

    @Test
    public void shouldDisplayUsername() {
        logIn();

        WebElement clickButton = webDriver.findElement(By.id("username"));
        clickButton.click();
        WebElement userNameOnCreatePage = webDriver.findElement(By.id("username"));

        assertThat(userNameOnCreatePage.getText().contains(username), is(true));
    }

    @After
    public void tearDown() throws Exception {
        webDriver.close();
    }

    private void logIn() {
        webDriver.get("http://127.0.0.1:8080/twu/");

        webDriver.findElement(By.id("username")).sendKeys(username);
        webDriver.findElement(By.id("password")).sendKeys(password);
        webDriver.findElement(By.name("submit")).click();
    }
}
