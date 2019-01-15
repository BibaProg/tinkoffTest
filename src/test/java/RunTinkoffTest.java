import Pages.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class RunTinkoffTest {
    private WebDriver webDriver;
    //Test Setup
    private final String url = "https://www.tinkoff.ru/";
    private final String town = "Москве";
    private final String location = "г. Санкт-Петербург";

    private String providerUrl;
    private String provider;


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
    public void tinkoffTest(){
        MainPage mainPage = new Page(webDriver).goUrl(url);

        KommunalniePlatezhi kommunalniePlatezhi = mainPage.getPlatezhi().getKommunalniePlatezhi();

        String str = kommunalniePlatezhi.getLocationLabel();
        Assert.assertEquals(str,town);
        provider = kommunalniePlatezhi.getFirstResultName();
        System.out.println(provider);

        ZhkuMoskva zhkuMoskva = kommunalniePlatezhi.getFirstResultPage().switchToOplata();

        zhkuMoskva.inputNotValidData();

        providerUrl = zhkuMoskva.getURL();

        Platezhi platezhi =zhkuMoskva.goUrl(url).getPlatezhi();

        str = platezhi.quickSearch(provider).gerFirstQuickResultName();
        Assert.assertEquals(str,provider);

        platezhi.getFirstQuickResult().switchToOplata().inputNotValidData();

        Assert.assertEquals(zhkuMoskva.getURL(),providerUrl);

        zhkuMoskva.goUrl(url).getPlatezhi().getKommunalniePlatezhi().switcgLocation(location);

        Assert.assertTrue(!kommunalniePlatezhi.checkProvider(provider));
    }

    @After
    public void stop(){
        //webDriver.quit();
    }


}
