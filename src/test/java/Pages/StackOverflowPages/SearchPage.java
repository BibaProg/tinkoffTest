package Pages.StackOverflowPages;

import Pages.Page;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPage extends Page {

    private String classResult = "summary";
    private String classResultLink = "result-link";
    private String classResultExcerpt = "excerpt";
    private String questionName =  "question-hyperlink";
    private String idTagsButton = "nav-tags";

    public SearchPage(WebDriver wd) {
        super(wd);
    }

    public List<WebElement> getAllResult(){
        return webDriver.findElements(By.className(classResult));
    }

    @Step("Проверяем есть ли в результатах искомое слово: {str}")
    public SearchPage strIsPartOfResult(String str){
        boolean contains = false;
        for(WebElement we: getAllResult()){
            contains = false;
            if((we.findElement(By.className(classResultExcerpt)).getText().toLowerCase().contains(str))
                    ||
                    (we.findElement(By.className(classResultLink)).getText().toLowerCase().contains(str))) {
                contains = true;
            }
            Assert.assertTrue(contains);
        }
        return this;
    }
    @Step("Проверка всех обсуждений в выборке")
    public SearchPage checkResult(){
        String firstTab = webDriver.getWindowHandle();
        String secondTab;
        for(WebElement we: getAllResult()){
            String resultTheme = we.findElement(By.className(classResultLink)).getText();
            String resultLink = we.findElement(By.className(classResultLink)).findElement(By.className(questionName)).getAttribute("href");
            ((JavascriptExecutor)webDriver).executeScript("window.open()");
            System.out.println(resultLink);
            for (String str: webDriver.getWindowHandles()){
                if(!str.equals(firstTab)){
                    secondTab = str;
                    webDriver.switchTo().window(secondTab);
                }
            }
            webDriver.get(resultLink);
            System.out.println(resultTheme);
            System.out.println("Q: " + webDriver.findElement(By.className(questionName)).getText());
            Assert.assertEquals(resultTheme,"Q: " + webDriver.findElement(By.className(questionName)).getText());
            webDriver.close();
            webDriver.switchTo().window(firstTab);
        }
        return this;
    }

    @Step("Переходим в раздел Tags")
    public TagsPage getTags(){
        webDriver.findElement(By.id(idTagsButton)).click();
        return new TagsPage(webDriver);
    }
}
