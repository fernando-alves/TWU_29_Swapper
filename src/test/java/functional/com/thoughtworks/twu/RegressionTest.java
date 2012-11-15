package functional.com.thoughtworks.twu;

import org.hamcrest.core.Is;
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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RegressionTest {
    private WebDriver webDriver;
    private String username;
    private String password;

    @Before
    public void setUp() {
        webDriver = new FirefoxDriver();
        username = "test.twu";
        password = "Th0ughtW0rks@12";
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

    private void gotoCreateOfferFromHomePage() throws Exception {
        Thread.sleep(2000);
        webDriver.findElement(By.id("createOffer")).click();
        Thread.sleep(2000);
    }

    @Test
    public void shouldDoRegressionTestForEntireApplication() throws Exception {
        logIn();
        WebElement userNameOnShowPage = webDriver.findElement(By.id("username"));
        assertThat(userNameOnShowPage.getText().contains(username), is(true));

        gotoCreateOfferFromHomePage();
        String actualTitleText = getTitleTagFromPage();

        String expectedTitle = "Create Offer";
        assertThat(actualTitleText, Is.is(expectedTitle));

        userNameOnShowPage = webDriver.findElement(By.id("username"));
        assertThat(userNameOnShowPage.getText().contains(username), is(true));

        createOffer();

        saveOffer();
        actualTitleText = getTitleTagFromPage();

        expectedTitle = "View An Offer";
        assertThat(expectedTitle, Is.is(actualTitleText));

//        gotoBrowseAllFromNavBar();
//
//        selectAnOffer();
//        WebElement firstOffer = webDriver.findElement(By.id("offer1"));
//
//        assertThat(firstOffer.getText(), Is.is("TITLE IN TEST"));
////
//        takeDownOffer();
//
//        gotoBrowseAllFromButton();
//
//        selectOfferNotYours(OfferId);
//
//        contactOfferHolder();
//
//

    }

    private void selectAnOffer() throws InterruptedException {
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(webDriver, 5000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("offer1")));


    }

    private void gotoBrowseAllFromNavBar() {
        webDriver.findElement(By.id(""));
    }

    private void saveOffer() throws InterruptedException {

        webDriver.findElement(By.name("submit")).click();
        Thread.sleep(2000);

    }

    private void createOffer() {
        webDriver.findElement(By.name("title")).sendKeys("TITLE IN TEST");

        Select select = new Select(webDriver.findElement(By.tagName("select")));
        select.selectByValue("Cars");

        webDriver.findElement(By.name("descriptionTxt")).sendKeys("To pass the test or not, this is a question");

    }

    public String getTitleTagFromPage() {
        WebElement actualTitle = webDriver.findElement(By.tagName("title"));
        return actualTitle.getText();
    }
}
