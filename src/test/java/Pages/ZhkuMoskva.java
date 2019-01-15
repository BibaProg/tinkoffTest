package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ZhkuMoskva extends Page {
    private final String xpathOplataZhkuMoskva ="/html/body/div[1]/div/div/div[2]/div[1]/div[2]/div[1]/div[6]/div/div[2]/div[1]/div/div/div/div[3]/div/ul/li[2]/div/a/span";
    private final String xpathKodPlatelshika = "//*[@id=\"payerCode\"]";
    private final String xpathPeriodOplati = "//*[@id=\"period\"]";
    private final String xpathSummaDobrovStrah ="/html/body/div[1]/div/div/div[2]/div[1]/div[2]/div[1]/div[6]/div/div[2]/div[1]/div/div/div/div[4]/div[1]/form/div[3]/div/div[1]/label/div/input";
    private final String xpathSummPlatezha = "/html/body/div[1]/div/div/div[2]/div[1]/div[2]/div[1]/div[6]/div/div[2]/div[1]/div/div/div/div[4]/div[1]/form/div[4]/div/div/div/div/div/div/div/div[1]/label/div/input";
    private final String xpathButtonOplatit = "/html/body/div[1]/div/div/div[2]/div[1]/div[2]/div[1]/div[6]/div/div[2]/div[1]/div/div/div/div[4]/div[1]/form/div[6]/div/div[1]/div/div/button/h2";

    private final String xpathKodPlatelshikaError = "/html/body/div[1]/div/div/div[2]/div[1]/div[2]/div[1]/div[6]/div/div[2]/div[1]/div/div/div/div[4]/div[1]/form/div[1]/div/div[2]";
    private final String xpathPeriodOplatiError = "/html/body/div[1]/div/div/div[2]/div[1]/div[2]/div[1]/div[6]/div/div[2]/div[1]/div/div/div/div[4]/div[1]/form/div[2]/div/div[2]";
    private final String xpathSummaDobrovStrahError = "/html/body/div[1]/div/div/div[2]/div[1]/div[2]/div[1]/div[6]/div/div[2]/div[1]/div/div/div/div[4]/div[1]/form/div[3]/div/div[2]";
    private final String xpathSummPlatezhaError = "/html/body/div[1]/div/div/div[2]/div[1]/div[2]/div[1]/div[6]/div/div[2]/div[1]/div/div/div/div[4]/div[1]/form/div[4]/div/div/div/div/div/div/div/div[2]";

    public ZhkuMoskva(WebDriver wd) {
        super(wd);
    }

    public ZhkuMoskva switchToOplata(){
        clickByXpath(xpathOplataZhkuMoskva);
        new WebDriverWait(webDriver,10).until(ExpectedConditions.titleContains("оплата"));
        return this;
    }

    public ZhkuMoskva inputNotValidData(){
        inputTextByXpath(xpathKodPlatelshika,"123");
        inputTextByXpath(xpathPeriodOplati,"32.5");
        inputTextByXpath(xpathSummaDobrovStrah,"123123123");
        inputTextByXpath(xpathSummPlatezha,"111111111111111111111111111111");
        clickByXpath(xpathButtonOplatit);
        printErrorMessage();
        return this;
    }

    private void printErrorMessage() {
        System.out.println("Страница: ");
        printURL();
        System.out.println("Ошибки не валидных данных: ");
        System.out.println(getTextByXpath(xpathKodPlatelshikaError));
        System.out.println(getTextByXpath(xpathPeriodOplatiError));
        System.out.println(getTextByXpath(xpathSummaDobrovStrahError));
        System.out.println(getTextByXpath(xpathSummPlatezhaError));
    }
}
