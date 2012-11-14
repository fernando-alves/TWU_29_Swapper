package functional.com.thoughtworks.twu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ValidationTest {

    private WebDriver webDriver;
    private String username;
    private String password;

    @Before
    public void setUp() {
        webDriver = new FirefoxDriver();
        username = "test.twu";
        password = "Th0ughtW0rks@12";
    }

    private String getTitleTagFromPage() {
        WebElement actualTitle = webDriver.findElement(By.tagName("title"));
        return actualTitle.getText();
    }


    @Test(expected = UnhandledAlertException.class)
    public void shouldNotCreateOfferWithEmptyFields() throws InterruptedException {
        logIn();
        Thread.sleep(1000);
        webDriver.findElement(By.id("createOffer")).click();
        webDriver.findElement(By.name("submit")).click();
        Thread.sleep(1000);

        String actualTitle = getTitleTagFromPage();
        String expectedTitle = "Create Offer";

        assertThat(actualTitle, is(expectedTitle));

    }

    @Test(expected = UnhandledAlertException.class)
    public void shouldNotCreateOfferWithBlankTitleFields() throws InterruptedException {
        logIn();
        Thread.sleep(1000);
        webDriver.findElement(By.id("createOffer")).click();

        webDriver.findElement(By.name("title")).sendKeys("   ");
        webDriver.findElement(By.name("descriptionTxt")).sendKeys("Valid Description");


        webDriver.findElement(By.name("submit")).click();
        Thread.sleep(1000);

        String actualTitle = getTitleTagFromPage();
        String expectedTitle = "Create Offer";

        assertThat(actualTitle, is(expectedTitle));

    }
    @Test(expected = UnhandledAlertException.class)
    public void shouldNotCreateOfferWithBlankDescriptionFields() throws InterruptedException {
        logIn();
        Thread.sleep(1000);
        webDriver.findElement(By.id("createOffer")).click();

        webDriver.findElement(By.name("title")).sendKeys("Valid title");
        webDriver.findElement(By.name("descriptionTxt")).sendKeys("       ");

        Select select = new Select(webDriver.findElement(By.tagName("select")));
        select.selectByValue("Cars");

        webDriver.findElement(By.name("submit")).click();
        Thread.sleep(1000);

        String actualTitle = getTitleTagFromPage();
        String expectedTitle = "Create Offer";

        assertThat(actualTitle, is(expectedTitle));

    }

    @Test(expected = UnhandledAlertException.class)
    public void shouldNotCreateOfferWithNoCategorySelect() throws InterruptedException {
        logIn();
        Date uniqueDate = new Date();
        String testTitle = uniqueDate.toString();

        Thread.sleep(2000);
        webDriver.findElement(By.id("createOffer")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.name("title")).sendKeys(testTitle);

        webDriver.findElement(By.name("descriptionTxt")).sendKeys("To pass the test or not, this is a question");
        webDriver.findElement(By.name("submit")).click();

        Thread.sleep(2000);

        webDriver.get("http://127.0.0.1:8080/twu/");
        webDriver.findElement(By.id("browse")).click();
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(webDriver, 5000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("offer1")));
        WebElement firstOffer = webDriver.findElement(By.id("offer1"));

        assertThat(firstOffer.getText(), is(testTitle));
    }

    @Test(expected = UnhandledAlertException.class)
    public void shouldNotCreateOfferWithEmptyDescription() throws InterruptedException {
        logIn();
        Date uniqueDate = new Date();
        String testTitle = uniqueDate.toString();

        Thread.sleep(2000);
        webDriver.findElement(By.id("createOffer")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.name("title")).sendKeys(testTitle);

        Select select = new Select(webDriver.findElement(By.tagName("select")));
        select.selectByValue("Cars");

        webDriver.findElement(By.name("submit")).click();

        Thread.sleep(2000);

        webDriver.get("http://127.0.0.1:8080/twu/");
        webDriver.findElement(By.id("browse")).click();
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(webDriver, 5000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("offer1")));
        WebElement firstOffer = webDriver.findElement(By.id("offer1"));

        assertThat(firstOffer.getText(), is(testTitle));
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

