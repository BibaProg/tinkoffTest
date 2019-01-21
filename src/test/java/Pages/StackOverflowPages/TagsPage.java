package Pages.StackOverflowPages;

import Pages.Page;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Pattern;

public class TagsPage extends Page {
    private String idTagsFilter = "tagfilter";
    private String classTagResult = "post-tag";

    public TagsPage(WebDriver wd) {
        super(wd);
    }

    @Step("Применить фильтр тегов: {tag}")
    public TagsPage tagsFilter(String tag){
        webDriver.findElement(By.id(idTagsFilter)).sendKeys(tag);
        new WebDriverWait(webDriver,10).until(ExpectedConditions.textMatches(By.className(classTagResult), Pattern.compile(tag)));
        return this;
    }

    @Step("Проверка точного совпадения тэга и переход в обсуждения")
    public TaggedQuestionPage getTaggedQuestion(String tag){
        boolean tagEquals = false;
        for(WebElement we: webDriver.findElements(By.className(classTagResult))){
            System.out.println(we.getText());
            if(we.getText().equals(tag)){
                tagEquals = true;
                we.click();
                break;
            }
        }
        Assert.assertTrue(tagEquals);
        return new TaggedQuestionPage(webDriver);
    }
}
