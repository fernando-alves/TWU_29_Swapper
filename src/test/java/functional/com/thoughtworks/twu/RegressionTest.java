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

import java.util.List;

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

        gotoBrowseAllFromNavBar();
        actualTitleText = getTitleTagFromPage();
        expectedTitle = "Browse Offers";
        assertThat(actualTitleText, Is.is(expectedTitle));
        List<WebElement> initialList = webDriver.findElements(By.className("offer"));


        selectAnOffer();
        WebElement firstOffer = webDriver.findElement(By.id("offer1"));

        assertThat(firstOffer.getText(), Is.is("TITLE IN TEST"));
        firstOffer.click();
        actualTitleText = getTitleTagFromPage();

        expectedTitle = "View An Offer";
        assertThat(expectedTitle, Is.is(actualTitleText));


        takeDownOffer();
        actualTitleText = getTitleTagFromPage();
        String expectedPageTitle = "Home";
        assertThat(actualTitleText, Is.is(expectedPageTitle));

        gotoBrowseAllFromButton();
        actualTitleText = getTitleTagFromPage();
        expectedTitle = "Browse Offers";
        assertThat(actualTitleText, Is.is(expectedTitle));

        List<WebElement> offerList = webDriver.findElements(By.className("offer"));
        assertThat(offerList.size(), Is.is(initialList.size()-1));

    }

    private void gotoBrowseAllFromButton() throws InterruptedException {
        webDriver.findElement(By.id("browse")).click();
        Thread.sleep(2000);


    }

    private void takeDownOffer() throws InterruptedException {
        webDriver.findElement(By.id("takeDownButton")).click();
        Thread.sleep(2000);

    }

    private void selectAnOffer() throws InterruptedException {
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(webDriver, 5000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("offer1")));


    }

    private void gotoBrowseAllFromNavBar() throws InterruptedException {
        webDriver.findElement(By.id("browseOfferLink")).click();
        Thread.sleep(2000);
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
