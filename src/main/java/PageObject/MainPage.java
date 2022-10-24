package PageObject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private final WebDriver driver;
    protected static final String URL = "https://stellarburgers.nomoreparties.site/";
    private final By loginAccount = By.xpath(".//main//button[text()='Войти в аккаунт']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click on Input Account")
    public void clickLoginAccount() {
        driver.findElement(loginAccount).click();
    }

}
