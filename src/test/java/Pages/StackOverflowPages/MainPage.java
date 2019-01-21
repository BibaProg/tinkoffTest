package Pages.StackOverflowPages;

import Pages.Page;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends Page {

    private String xpathSearchInput = "//*[@id=\"search\"]/div/input";
    private String xpathSearchButton = "//*[@id=\"search\"]/div/button";

    public MainPage(WebDriver wd) {
        super(wd);
        webDriver.get("http://stackoverflow.com/");
    }

    @Step("Поиск по слову {str}")
    @Description(value="На главное странице в поле поиска вводится {str} после чего поиск подтверждается и мы переходим на страницу с результатами поиска")
    public SearchPage search(String str){
        webDriver.findElement(By.xpath(xpathSearchInput)).sendKeys(str);
        new WebDriverWait(webDriver,10).until(ExpectedConditions.elementToBeClickable(By.xpath(xpathSearchButton)));
        webDriver.findElement(By.xpath(xpathSearchButton)).click();
        return new SearchPage(webDriver);
    }
}
