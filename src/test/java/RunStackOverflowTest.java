import Pages.StackOverflowPages.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class RunStackOverflowTest {
    private WebDriver webDriver;
    private String searchString = "webdriver";

    @Before
    public void start(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito","verbose", "disable-infobars", "disable-translate", "disable-notifications", "disable-popup-blocking", "ignore-certificate-errors");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        webDriver = new ChromeDriver(capabilities);
        webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }

    @Test
    public void StackOverflowTest(){
        MainPage mainPage = new MainPage(webDriver);
        mainPage.search(searchString).strIsPartOfResult(searchString).checkResult().getTags().tagsFilter(searchString).getTaggedQuestion(searchString).checkResultTag(searchString);
    }


    @After
    public void stop(){
        webDriver.quit();
    }
}
