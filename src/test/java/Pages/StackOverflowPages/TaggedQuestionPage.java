package Pages.StackOverflowPages;

import Pages.Page;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TaggedQuestionPage extends Page {
    private String classTagResult = "question-summary";

    public TaggedQuestionPage(WebDriver wd) {
        super(wd);
    }

    private List<WebElement> getAllResult(){
        return webDriver.findElements(By.className(classTagResult));
    }

    @Step("Проверка все ли результаты помечены тэгом: {tag}")
    public TaggedQuestionPage checkResultTag(String tag){
        for(WebElement we: getAllResult()){
            boolean contains = false;
            for (WebElement curentTag: we.findElements(By.className("post-tag"))){
                System.out.println(curentTag.getText());
                System.out.println(tag);
                if(curentTag.getText().equals(tag)){
                    contains = true;
                    break;
                }
            }
            Assert.assertTrue(contains);
        }
        return this;
    }
}
