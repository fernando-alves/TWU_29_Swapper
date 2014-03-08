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

        WebDriverWait wait = new WebDriverWait(webDriver, 1000);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("browseOfferLink")));

        webDriver.findElement(By.id("browseOfferLink")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("browseOfferActive")));

        WebElement browseOfferActive = webDriver.findElement(By.id("browseOfferActive"));

        assertThat(browseOfferActive.getAttribute("class").contains("active"), is(true));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("homeActive")));

        WebElement homeActive = webDriver.findElement(By.id("homeActive"));

        assertThat(homeActive.getAttribute("class").contains("active"), is(false));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("createOfferActive")));

        WebElement createOfferActive = webDriver.findElement(By.id("createOfferActive"));
        assertThat(createOfferActive.getAttribute("class").contains("active"), is(false));

    }

    @Test
    public void shouldCheckAbsenceOfFocusInNavigationBarOnViewOfferFromCreateOfferPage() throws InterruptedException {
        logIn();
        webDriver.findElement(By.id("createOfferLink")).click();

        webDriver.findElement(By.name("title")).sendKeys("TITLE IN TEST");

        Select select = new Select(webDriver.findElement(By.tagName("select")));
        select.selectByValue("Cars");

        webDriver.findElement(By.name("descriptionTxt")).sendKeys("To pass the test or not, this is a question");
        webDriver.findElement(By.name("submit")).click();

        WebElement browseOfferActive = webDriver.findElement(By.id("browseOfferActive"));
        assertThat(browseOfferActive.getAttribute("class").contains("active"), is(false));

        WebElement homeActive = webDriver.findElement(By.id("homeActive"));
        assertThat(homeActive.getAttribute("class").contains("active"), is(false));

        WebElement createOfferActive = webDriver.findElement(By.id("createOfferActive"));
        assertThat(createOfferActive.getAttribute("class").contains("active"), is(false));

    }

    @Test
    public void shouldCheckAbsenceOfFocusInNavigationBarOnViewOfferFromBrowseOfferPage() throws InterruptedException {
        logIn();
        webDriver.findElement(By.id("browseOfferLink")).click();

        WebElement firstOffer = webDriver.findElement(By.id("offer1"));
        firstOffer.click();
        Thread.sleep(3000);

        WebElement browseOfferActive = webDriver.findElement(By.id("browseOfferActive"));
        assertThat(browseOfferActive.getAttribute("class").contains("active"), is(false));

        WebElement homeActive = webDriver.findElement(By.id("homeActive"));
        assertThat(homeActive.getAttribute("class").contains("active"), is(false));

        WebElement createOfferActive = webDriver.findElement(By.id("createOfferActive"));
        assertThat(createOfferActive.getAttribute("class").contains("active"), is(false));

    }

    @Test
    public void shouldCheckPresenceOfWarningMessageOnCreateOfferPage() throws InterruptedException {
        logIn();

        WebDriverWait wait = new WebDriverWait(webDriver, 5000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("createOfferLink")));
        webDriver.findElement(By.id("createOfferLink")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailMessage")));
        WebElement emailMessageElement = webDriver.findElement(By.id("emailMessage"));

        String expectedMessage = "Note: We send emails from twufeedmycat@gmail.com to the user when you have to contact them.\n"+
                "Please ensure that the emails from this id don't end up in the spam folder.";

        assertThat(emailMessageElement.getText(), is(expectedMessage));

    }

    @Test
    public void shouldCheckPresenceOfWarningMessageOnBrowseOfferPage() throws InterruptedException {
        logIn();

        WebDriverWait wait = new WebDriverWait(webDriver, 5000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("browseOfferLink")));
        webDriver.findElement(By.id("browseOfferLink")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailMessage")));
        WebElement emailMessageElement = webDriver.findElement(By.id("emailMessage"));

        String expectedMessage = "Note: We send emails from twufeedmycat@gmail.com to the user when you have to contact them.\n"+
                "Please ensure that the emails from this id don't end up in the spam folder.";

        assertThat(emailMessageElement.getText(), is(expectedMessage));

    }

    @Test
    public void shouldCheckPresenceOfWarningMessageOnViewOfferPage() throws InterruptedException {
        logIn();

        WebDriverWait wait = new WebDriverWait(webDriver, 5000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("browseOfferLink")));
        webDriver.findElement(By.id("browseOfferLink")).click();

        WebElement firstOffer = webDriver.findElement(By.id("offer1"));
        firstOffer.click();
        Thread.sleep(2000);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emailMessage")));
        WebElement emailMessageElement = webDriver.findElement(By.id("emailMessage"));

        String expectedMessage = "Note: We send emails from twufeedmycat@gmail.com to the user when you have to contact them.\n"+
                "Please ensure that the emails from this id don't end up in the spam folder.";

        assertThat(emailMessageElement.getText(), is(expectedMessage));

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
