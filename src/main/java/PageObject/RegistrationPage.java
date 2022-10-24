package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
    private final WebDriver driver;
    private final By newUser = By.xpath(".//main//a[text()='Зарегистрироваться']");
    private final By name = By.xpath(".//main//fieldset[1]//input");
    private final By email = By.xpath(".//main//fieldset[2]//input");
    private final By password = By.xpath(".//main//fieldset[3]//input");
    private final By registration = By.xpath(".//main//button[text()='Зарегистрироваться']");
    private final By input = By.xpath(".//main//h2[text()='Вход']");
    private final By incorrectPassword = By.xpath(".//main//p[text()='Некорректный пароль']");


    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Scroll to Registration")
    public void scrollToRegistration() {
        WebElement element = driver.findElement(newUser);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
        new WebDriverWait(driver, 3);
    }

    @Step("Click on Registration for start registration")
    public void clickNewUser() {
        driver.findElement(newUser).click();
    }

    @Step("Fill name of User")
    public void fillName(String name) {
        driver.findElement(this.name).sendKeys(name);
    }
    @Step("Fill email of User")
    public void fillEmail(String email) {
        driver.findElement(this.email).sendKeys(email);
    }

    @Step("Fill password of User")
    public void fillPassword(String password) {
        driver.findElement(this.password).sendKeys(password);
    }

    @Step("Find header Input")
    public boolean isDisplayInput() {
        return driver.findElement(input).isDisplayed();
    }

    @Step("Find error message")
    public boolean isDisplayError() {
        return driver.findElement(incorrectPassword).isDisplayed();
    }

    @Step("Click on Registration for create user")
    public void clickRegistration() {
        driver.findElement(registration).click();
    }
}
