package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class KommunalniePlatezhi extends Page {
    private final String xpathLocationLabel = "/html/body/div[1]/div/div/div[2]/div[1]/div[2]/div[1]/div[6]/div/div[2]/div/div/div/div[2]/div/div/div/span/span/span";
    private final String xpathForFirstResult = "/html/body/div[1]/div/div/div[2]/div[1]/div[2]/div[1]/div[6]/div/div[2]/div/div/div/section/ul/li[1]/span[2]/a/span/div";
    private final String xpathAllResult = "/html/body/div[1]/div/div/div[2]/div[1]/div[2]/div[1]/div[6]/div/div[2]/div/div/div/section/ul/li";

    public KommunalniePlatezhi(WebDriver wd) {
        super(wd);
    }

    public String getLocationLabel(){
        return getTextByXpath(xpathLocationLabel);
    }

    public String getFirstResultName(){
        return getTextByXpath(xpathForFirstResult);
    }

    public ZhkuMoskva getFirstResultPage(){
        clickByXpath(xpathForFirstResult);
        return new ZhkuMoskva(webDriver);
    }

    @Step
    public KommunalniePlatezhi switcgLocation(String location) {
        clickByXpath(xpathLocationLabel);
        webDriver.findElement(By.linkText(location)).click();
        return this;
    }

    public boolean checkProvider(String provider) {
        boolean result = false;
        new WebDriverWait(webDriver,10).until(ExpectedConditions.elementToBeClickable(By.xpath(xpathAllResult)));
        List<WebElement> webElements = webDriver.findElements(By.xpath(xpathAllResult));
        for(WebElement we: webElements){
            System.out.println(we.getText()+ "  :::  " +provider);
            if(we.getText().contains(provider)){
                result = true;
            }
        }
        return result;

    }
}
