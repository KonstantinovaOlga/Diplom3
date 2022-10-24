package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriver driver;
    private final By email = By.xpath(".//main//fieldset[1]//input");
    private final By password = By.xpath(".//main//fieldset[2]//input");
    private final By login = By.xpath(".//main//button[text()='Войти']");
    private final By doOrder = By.xpath(".//main//button[text()='Оформить заказ']");
    private final By lk = By.xpath(".//header//a[@href='/account']");
    private final By inputRegForm = By.xpath(".//main//a[text()='Войти']");
    private final By forgotPassword = By.xpath(".//main//a[text()='Восстановить пароль']");
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Fill email of User")
    public void fillEmail(String email) {
        driver.findElement(this.email).sendKeys(email);
    }

    @Step("Fill password of User")
    public void fillPassword(String password) {
        driver.findElement(this.password).sendKeys(password);
    }

    @Step("Click on Login button")
    public void clickLogin() {
        driver.findElement(login).click();
    }

    @Step("Find button Do Order")
    public boolean isDisplayDoOrder () {
        return driver.findElement(doOrder).isDisplayed();
    }

    @Step("Click on LK button")
    public void clickLK() {
        driver.findElement(lk).click();
    }
    @Step("Click Input in Registration Form")
    public void clickInputRegForm() {
        driver.findElement(inputRegForm).click();
    }

    @Step("Click Forgot Password in Registration Form")
    public void clickForgotPassword() {
        driver.findElement(forgotPassword).click();
    }

    @Step("Scroll to Forgot password")
    public void scrollToForgotPassword() {
        WebElement element = driver.findElement(forgotPassword);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
        new WebDriverWait(driver, 3);
    }
}
