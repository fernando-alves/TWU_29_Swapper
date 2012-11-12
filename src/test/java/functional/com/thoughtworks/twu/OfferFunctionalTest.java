package functional.com.thoughtworks.twu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class OfferFunctionalTest {

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
    public void shouldCheckExistenceOfCreateOfferLink() throws InterruptedException {
        logIn();

        WebElement clickButton = webDriver.findElement(By.id("createOffer"));
        assertNotNull(clickButton);
    }

    @Test
    public void shouldGoToCreateOfferPage() throws InterruptedException {
        logIn();

        Thread.sleep(2000);
        webDriver.findElement(By.id("createOffer")).click();
        Thread.sleep(2000);
        String actualTitleText = getTitleTagFromPage();

        String expectedTitle = "Create Offer";
        assertThat(actualTitleText, is(expectedTitle));

    }

    private String getTitleTagFromPage() {
        WebElement actualTitle = webDriver.findElement(By.tagName("title"));
        return actualTitle.getText();
    }

    @Test
    public void shouldHaveAnElementToEnterTitle() throws InterruptedException {
        logIn();

        webDriver.findElement(By.id("createOffer")).click();
        WebElement titleTextBox = webDriver.findElement(By.name("title"));

        assertThat(titleTextBox.isDisplayed(), is(true));
    }

    @Test
    public void shouldHaveAnElementToSelectCategory() throws InterruptedException {
        logIn();

        webDriver.findElement(By.id("createOffer")).click();

        WebDriverWait wait = new WebDriverWait(webDriver, 5000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("category")));

        WebElement categoryList = webDriver.findElement(By.name("category"));

        assertThat(categoryList.isDisplayed(), is(true));
    }

    @Test
    public void shouldHaveAnElementToEnterDescription() throws InterruptedException {
        logIn();

        webDriver.findElement(By.id("createOffer")).click();
        Thread.sleep(1000);
        WebElement titleTextBox = webDriver.findElement(By.name("descriptionTxt"));

        assertThat(titleTextBox.isDisplayed(), is(true));
    }

    @Test
    public void shouldGoToViewAnOfferPage() throws InterruptedException {
        logIn();

        Thread.sleep(2000);
        webDriver.findElement(By.id("createOffer")).click();
        Thread.sleep(2000);

        webDriver.findElement(By.name("title")).sendKeys("TITLE IN TEST");

        Select select = new Select(webDriver.findElement(By.tagName("select")));
        select.selectByValue("Cars");

        webDriver.findElement(By.name("descriptionTxt")).sendKeys("To pass the test or not, this is a question");
        webDriver.findElement(By.name("submit")).click();
        Thread.sleep(2000);

        String actualTitleText = getTitleTagFromPage();

        String expectedTitle = "View An Offer";
        assertThat(expectedTitle, is(actualTitleText));
    }

    @Test
    public void shouldDisplayOfferDetailsAfterCreating() throws InterruptedException {
        logIn();

        Thread.sleep(2000);
        webDriver.findElement(By.id("createOffer")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.name("title")).sendKeys("TITLE IN TEST");

        Select select = new Select(webDriver.findElement(By.tagName("select")));
        select.selectByValue("Cars");

        webDriver.findElement(By.name("descriptionTxt")).sendKeys("To pass the test or not, this is a question");
        webDriver.findElement(By.name("submit")).click();
        Thread.sleep(2000);
        String offerOwnerEmail = username + "@thoughtworks.com";
        assertThat(webDriver.getPageSource().contains(offerOwnerEmail),is(true));
    }

    @Test
    public void shouldDisplayRequiredOfferDetails() throws InterruptedException{
        logIn();

        Thread.sleep(2000);
        webDriver.findElement(By.id("browse")).click();
        Thread.sleep(2000);

        WebElement firstOffer = webDriver.findElement(By.id("offer1"));
        String firstOfferTitle = firstOffer.getText();
        firstOffer.click();
        Thread.sleep(2000);

        assertThat(webDriver.getPageSource().contains(firstOfferTitle),is(true));

    }

    @Test
    public void shouldGoToViewAnOfferPageFromBrowse() throws InterruptedException {
        logIn();

        Thread.sleep(2000);
        webDriver.findElement(By.id("browse")).click();
        Thread.sleep(2000);

        webDriver.findElement(By.id("offer1")).click();
        Thread.sleep(2000);

        String actualTitleText = getTitleTagFromPage();

        String expectedTitle = "View An Offer";
        assertThat(expectedTitle, is(actualTitleText));
    }

    @Test
    public void shouldCheckExistenceOfBrowseOfferLink() throws InterruptedException {
        logIn();

        WebElement clickButton = webDriver.findElement(By.id("browse"));
        assertNotNull(clickButton);
    }

    @Test
    public void shouldCreateOfferAndDisplayRecentOfferOnBrowseAllPage() throws InterruptedException {
        logIn();
        Date uniqueDate = new Date();
        String testTitle = uniqueDate.toString();

        Thread.sleep(2000);
        webDriver.findElement(By.id("createOffer")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.name("title")).sendKeys(testTitle);

        Select select = new Select(webDriver.findElement(By.tagName("select")));
        select.selectByValue("Cars");

        webDriver.findElement(By.name("descriptionTxt")).sendKeys("To pass the test or not, this is a question");
        webDriver.findElement(By.name("submit")).click();
<<<<<<< HEAD
        Thread.sleep(2000);
=======

      //  Thread.sleep(1000);
>>>>>>> fad8fee... rebasing


        webDriver.get("http://127.0.0.1:8080/twu/");
        webDriver.findElement(By.id("browse")).click();
<<<<<<< HEAD
        Thread.sleep(2000);
=======
        WebDriverWait wait = new WebDriverWait(webDriver, 5000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("offer1")));
>>>>>>> fad8fee... rebasing
        WebElement firstOffer = webDriver.findElement(By.id("offer1"));

        assertThat(firstOffer.getText(),is(testTitle));
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
