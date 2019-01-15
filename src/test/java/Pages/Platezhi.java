package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Platezhi extends Page {
    private final String xpathKomunPlatezhi = "/html/body/div[1]/div/div/div[2]/div[1]/div[2]/div[1]/div[6]/div/div[2]/div[1]/div[2]/div/div[2]/div/div[2]/div/a/span/div/div[1]";
    private final String xPathQuickSearch = "//*[@id=\"search-and-pay-container\"]/div[2]/div[2]/div/form/div[3]/div/div/div/div/label/div/input";
    private final String xpathFirstQuickResult = "//*[@id=\"search-and-pay-container\"]/div[2]/div[2]/div/form/div[3]/div/div/div/div[2]/div/div[1]/div/div/div[1]/div/div[1]/div[1]";

    public Platezhi(WebDriver wd) {
        super(wd);
    }
    public KommunalniePlatezhi getKommunalniePlatezhi(){
        clickByXpath(xpathKomunPlatezhi);

        return new KommunalniePlatezhi(webDriver);
    }

    public Platezhi quickSearch(String str){
        inputTextByXpath(xPathQuickSearch,str);
        return this;
    }

    public String gerFirstQuickResultName(){
        new WebDriverWait(webDriver,10).until(ExpectedConditions.elementToBeClickable(By.xpath(xpathFirstQuickResult)));
        return getTextByXpath(xpathFirstQuickResult);
    }

    public ZhkuMoskva getFirstQuickResult(){
        clickByXpath(xpathFirstQuickResult);
        return new ZhkuMoskva(webDriver);
    }
}
