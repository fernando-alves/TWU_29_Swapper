package functional.com.thoughtworks.twu;

import org.apache.commons.lang.RandomStringUtils;
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
        WebDriverWait wait = new WebDriverWait(webDriver, 3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("createOffer")));
        webDriver.findElement(By.id("createOffer")).click();

        wait = new WebDriverWait(webDriver, 3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("submit")));
        webDriver.findElement(By.name("submit")).click();

        String actualTitle = getTitleTagFromPage();
        String expectedTitle = "Create Offer";

        String alertMsg = webDriver.switchTo().alert().getText();
        String expectedAlertMsg = " Title can not be empty";
        assertThat(alertMsg, is(expectedAlertMsg));
        assertThat(actualTitle, is(expectedTitle));

    }

    @Test(expected = UnhandledAlertException.class)
    public void shouldNotCreateOfferWithBlankTitleFields() throws InterruptedException {
        logIn();

        WebDriverWait wait = new WebDriverWait(webDriver, 3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("createOffer")));

        webDriver.findElement(By.id("createOffer")).click();

        wait = new WebDriverWait(webDriver, 3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("title")));

        webDriver.findElement(By.name("title")).sendKeys("   ");
        webDriver.findElement(By.name("descriptionTxt")).sendKeys("Valid Description");

        Select select = new Select(webDriver.findElement(By.tagName("select")));
        select.selectByValue("Cars");

        webDriver.findElement(By.name("submit")).click();



        String alertMsg = webDriver.switchTo().alert().getText();
        String expectedAlertMsg = "Title cannot be blank";

        assertThat(alertMsg, is(expectedAlertMsg));

        String actualTitle = getTitleTagFromPage();
        String expectedTitle = "Create Offer";

        assertThat(actualTitle, is(expectedTitle));

    }

    @Test(expected = UnhandledAlertException.class)
    public void shouldNotCreateOfferWithBlankDescriptionFields() throws InterruptedException {
        logIn();
        WebDriverWait wait = new WebDriverWait(webDriver, 3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("createOffer")));
        webDriver.findElement(By.id("createOffer")).click();


        wait = new WebDriverWait(webDriver, 3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("title")));

        webDriver.findElement(By.name("title")).sendKeys("Valid title");
        webDriver.findElement(By.name("descriptionTxt")).sendKeys("       ");

        Select select = new Select(webDriver.findElement(By.tagName("select")));
        select.selectByValue("Cars");

        webDriver.findElement(By.name("submit")).click();
        Thread.sleep(2000);

        String alertMsg = webDriver.switchTo().alert().getText();
        String expectedAlertMsg = "Description can not be blank";

        assertThat(alertMsg, is(expectedAlertMsg));


        String actualTitle = getTitleTagFromPage();
        String expectedTitle = "Create Offer";

        assertThat(actualTitle, is(expectedTitle));

    }

    @Test(expected = UnhandledAlertException.class)
    public void shouldNotCreateOfferWithNoCategorySelect() throws InterruptedException {
        logIn();

        WebDriverWait wait = new WebDriverWait(webDriver, 3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("createOffer")));
        webDriver.findElement(By.id("createOffer")).click();

        wait = new WebDriverWait(webDriver, 3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("title")));

        webDriver.findElement(By.name("title")).sendKeys("Valid title");
        webDriver.findElement(By.name("descriptionTxt")).sendKeys("Valid description");

        webDriver.findElement(By.name("submit")).click();

        Thread.sleep(2000);

        String alertMsg = webDriver.switchTo().alert().getText();
        String expectedAlertMsg = "Please select a category";

        assertThat(alertMsg, is(expectedAlertMsg));

        String actualTitle = getTitleTagFromPage();
        String expectedTitle = "Create Offer";

        assertThat(actualTitle, is(expectedTitle));

    }


    @Test(expected = UnhandledAlertException.class)
    public void shouldNotCreateOfferWithEmptyDescription() throws InterruptedException {
        logIn();

        WebDriverWait wait = new WebDriverWait(webDriver, 3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("createOffer")));
        webDriver.findElement(By.id("createOffer")).click();

        wait = new WebDriverWait(webDriver, 3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("title")));

        webDriver.findElement(By.name("title")).sendKeys("Valid title");
        String emptyDescription = "      ";

        webDriver.findElement(By.name("descriptionTxt")).sendKeys(emptyDescription);

        Select select = new Select(webDriver.findElement(By.tagName("select")));
        select.selectByValue("Cars");

        webDriver.findElement(By.name("submit")).click();

        Thread.sleep(2000);

        String alertMsg = webDriver.switchTo().alert().getText();
        String expectedAlertMsg = "Description can not be blank";

        assertThat(alertMsg, is(expectedAlertMsg));

        String actualTitle = getTitleTagFromPage();
        String expectedTitle = "Create Offer";

        assertThat(actualTitle, is(expectedTitle));
    }

    @Test
    public void shouldCutTheTitleWhenLongerThan100() throws InterruptedException{
        logIn();
        WebDriverWait wait = new WebDriverWait(webDriver, 3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("createOffer")));

        webDriver.findElement(By.id("createOffer")).click();

        WebDriverWait waitForCreateOfferPage = new WebDriverWait(webDriver, 3000);
        waitForCreateOfferPage.until(ExpectedConditions.visibilityOfElementLocated(By.name("title")));

        int tooLongTitleLength = 150;
        String testTitle = RandomStringUtils.randomAlphanumeric(tooLongTitleLength);

        webDriver.findElement(By.name("title")).sendKeys(testTitle);

        Select select = new Select(webDriver.findElement(By.tagName("select")));
        select.selectByValue("Cars");

        webDriver.findElement(By.name("descriptionTxt")).sendKeys("Long Title");

        webDriver.findElement(By.name("submit")).click();

        wait = new WebDriverWait(webDriver, 3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("offerTitle")));

        String offerTitle = webDriver.findElement(By.id("offerTitle")).getText();

        int expectedTitleLength = 100;
        assertThat(offerTitle.length(), is(expectedTitleLength));


    }
    @Test
    public void shouldCutTheDescriptionWhenLongerThan1000() throws InterruptedException{
        logIn();
        WebDriverWait wait = new WebDriverWait(webDriver, 3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("createOffer")));

        webDriver.findElement(By.id("createOffer")).click();

        WebDriverWait waitForCreateOfferPage = new WebDriverWait(webDriver, 3000);
        waitForCreateOfferPage.until(ExpectedConditions.visibilityOfElementLocated(By.name("descriptionTxt")));
        int tooLongTitleLength = 1500;
        String testTitle = RandomStringUtils.randomAlphanumeric(tooLongTitleLength);
        webDriver.findElement(By.name("title")).sendKeys("Test for long description");
        Select select = new Select(webDriver.findElement(By.tagName("select")));
        select.selectByValue("Cars");
        webDriver.findElement(By.name("descriptionTxt")).sendKeys(testTitle);

        webDriver.findElement(By.name("submit")).click();

        wait = new WebDriverWait(webDriver, 3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("offerTitle")));

        String descriptionTitle = webDriver.findElement(By.id("descriptionTxt")).getText();

        int expectedTitleLength = 1000;
        assertThat(descriptionTitle.length(), is(expectedTitleLength));


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

