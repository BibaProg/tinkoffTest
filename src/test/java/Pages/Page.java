package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {
    public WebDriver webDriver;
    private final String xpathPlatezhi = "//*[@id=\"xccb5e\"]/div/footer/div[2]/div/div/ul/li[1]/ul/li[7]/span/span/a";

    public Page(WebDriver wd){
        this.webDriver = wd;
    }

    public Page() {
    }

    public MainPage goUrl(String url){
        webDriver.get(url);
        return new MainPage(webDriver);
    }

    public Page changePage(Page page){
        page.setWebDriver(webDriver);
        return page;
    }

    public void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public Page clickByXpath(String xpath){
        new WebDriverWait(webDriver,10).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        webDriver.findElement(By.xpath(xpath)).click();
        return this;
    }

    public String getTextByXpath(String xpath){
        return webDriver.findElement(By.xpath(xpath)).getText();
    }

    public Page inputTextByXpath(String xpath,String keys){
        webDriver.findElement(By.xpath(xpath)).sendKeys(keys);
        return this;
    }

    public Page printURL(){
        System.out.println(webDriver.getCurrentUrl());
        return this;
    }

    public String getURL(){
        return webDriver.getCurrentUrl();
    }


}
