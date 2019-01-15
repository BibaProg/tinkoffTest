package Pages;

import org.openqa.selenium.WebDriver;

public class MainPage extends Page {
    private final String xpathPlatezhi = "//*[@id=\"xccb5e\"]/div/footer/div[2]/div/div/ul/li[1]/ul/li[7]/span/span/a";

    public MainPage(WebDriver wd) {
        super(wd);
    }

    public Platezhi getPlatezhi(){
        clickByXpath(xpathPlatezhi);

        return new Platezhi(webDriver);
    }
}