package functional.com.thoughtworks.twu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class CreateOfferFunctionalTest {


    private WebDriver webDriver;
    private String username;
    private String password;
    DataSource dataSource;

    @Before
    public void setUp() throws IOException {
        webDriver = new FirefoxDriver();
        username = "test.twu";
        password = "Th0ughtW0rks@12";
        cleanDatabase();

    }

    private void cleanDatabase() throws IOException {
        SimpleJdbcTemplate template = new SimpleJdbcTemplate(dataSource);
        template.update("delete from offer");
//        Runtime.getRuntime().exec("sh cleandb.sh", null, new File("."));
    }

    @Test
    public void shouldGoToCreateOfferPage() throws InterruptedException {
        logIn();

        Thread.sleep(1000);
        webDriver.findElement(By.id("createOffer")).click();

        String expectedUrl = "http://127.0.0.1:8080/twu/offer/create";
        assertThat(webDriver.getCurrentUrl(), is(expectedUrl));

    }

    @Test
    public void shouldGoToViewAnOfferPage() throws InterruptedException {
        logIn();

        Thread.sleep(1000);
        createOffer("TITLE IN TEST", "To pass the test or not, this is a question");
        Thread.sleep(1000);
        String expectedUrl = "http://127.0.0.1:8080/twu/offer/viewAnOfferAfterCreating?username=test.twu";
        assertThat(expectedUrl, is(webDriver.getCurrentUrl()));
    }

    @Test
    public void showGoToViewAnOfferPageFromBrowse() throws InterruptedException {
        logIn();

        Thread.sleep(1000);
        webDriver.findElement(By.id("browse")).click();

        webDriver.findElement(By.id("offer1")).click();
        Thread.sleep(1000);
        assertTrue(webDriver.getCurrentUrl().contains("http://127.0.0.1:8080/twu/offer/viewAnOfferFromBrowse?offerId="));
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
    public void shouldCheckExistenceOfBrowseOfferLink() {
        logIn();

        WebElement clickButton = webDriver.findElement(By.id("browse"));
        assertNotNull(clickButton);
    }

    @Test
    public void  shouldDisplayUsername() {
        logIn();

        WebElement userNameOnCreatePage = webDriver.findElement(By.id("username"));

        assertThat(userNameOnCreatePage.getText().contains(username), is(true));
    }

    @Test
    public void shouldDisplayUsernameOnBrowseOfferPage() {
        logIn();



        WebElement userNameOnBrowsePage = webDriver.findElement(By.id("username"));

        assertThat(userNameOnBrowsePage.getText().contains(username), is(true));
    }


    @Transactional
    @Test
    public void shouldDisplayListOfOffersOnBrowsePage() throws InterruptedException {

        logIn();

        Thread.sleep(1000);
        String description = "To pass the test or not, this is a question";
        createOffer("TITLE IN TEST", description);
        Select select;
        Thread.sleep(1000);
        webDriver.get("http://127.0.0.1:8080/twu/");

        final String title = "TITLE IN TEST 2";
        createOffer(title, description);

        webDriver.get("http://127.0.0.1:8080/twu/");

        webDriver.findElement(By.id("browse")).click();
        Thread.sleep(100 * 1000);
        List<WebElement> listOfWebElements =  webDriver.findElements(By.xpath("/html/body/div/ol/li"));
        List<String> expectedListOfTitles = new ArrayList<String>(){{
            add(title);
            add("TITLE IN TEST");
        }};
        List<String> actualListOfTitles = new ArrayList<String>();
        for (WebElement listOfWebElement : listOfWebElements) {
            actualListOfTitles.add(listOfWebElement.getText());
        }
        assertThat(actualListOfTitles, is(expectedListOfTitles));
    }

    private void createOffer(String title, String description) {
        webDriver.findElement(By.id("createOffer")).click();
        webDriver.findElement(By.name("title")).sendKeys(title);

        Select select = new Select(webDriver.findElement(By.tagName("select")));
        select.selectByValue("Cars");

        webDriver.findElement(By.name("description")).sendKeys(description);
        webDriver.findElement(By.name("submit")).click();
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
