/**
 * Created by MBekker1 on 16/09/2016.
 */

import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static sun.awt.SunToolkit.DEFAULT_WAIT_TIME;

public class TestGoogle {
    private WebDriver driver;
    private String baseUrl;
    private WebDriverWait wait;
    private long time = 10;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        baseUrl = "https://www.google.com";

        // Specify what driver to use.
        // driver = new FirefoxDriver();
        // DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        // capabilities.setBrowserName("Mozilla/5.0 (X11; Linux x86_64; rv:24.0) Gecko/20100101 Firefox/24.0");
        // capabilities.setVersion("24.0");
        // capabilities.setJavascriptEnabled(true);
        // driver = new HtmlUnitDriver(capabilities);
        // driver.setJavascriptEnabled(true);
        // HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver(BrowserVersion.CHROME);
//        HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver();
//        BrowserVersion browserVersion = htmlUnitDriver.getBrowserVersion();
//        htmlUnitDriver.setJavascriptEnabled(true);
       // driver = htmlUnitDriver;
        // driver = new ChromeDriver();
        driver = new PhantomJSDriver();

        // Explicit wait.
        // wait = new WebDriverWait(driver, time);

        // TODO: Remove this debug line.
        // driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
        // DEFAULT_WAIT_TIME
        driver.manage().timeouts().pageLoadTimeout(time, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(time, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    @Test
    public void searchGoogleForSLB() throws Exception {

        // https://cybertron-authenticate.dev.
        driver.get(baseUrl);

        // This code will print the page title.
        System.out.println("Page title is: " + driver.getTitle());

        // Asserts if we don't have the correct title.
        assertEquals("Google", driver.getTitle());

        // Wait until the edit control is available.
        // wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));

        // Locate the search box using its name.
        WebElement element = driver.findElement(By.name("q"));

        // Enter a search query.
        element.sendKeys("Schlumberger");

        // Submit the query.
        // Webdriver searches for the form using the text input element automatically.
        // No need to locate/find the submit button.
        element.submit();

        // This code will print the page title.
        System.out.println("Page title is: " + driver.getTitle());
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("c:\\tmp\\screenshot.png"));

        // wait.until(ExpectedConditions.elementToBeClickable(By.id("abar_button_opt")));
        // driver.findElement(By.id("abar_button_opt")).click();
        WebElement stats = driver.findElement(By.id("resultStats"));
        System.out.println("Stats for search is: " + stats.getText());

        // This code will print the page title.
        System.out.println("Page title is: " + driver.getTitle());
        assertEquals("Schlumberger - Google Search", driver.getTitle());
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}