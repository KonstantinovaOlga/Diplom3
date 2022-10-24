package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LKPage {
    private final WebDriver driver;
    private final By profil = By.xpath(".//main//a[text()='Профиль']");
    private final By constructor = By.xpath(".//header//p[text()='Конструктор']");
    private final By headerBurger = By.xpath(".//main//h1[text()='Соберите бургер']");
    private final By logout = By.xpath(".//main//button[text()='Выход']");

    public LKPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Find header Profile")
    public boolean isDisplayProfile () {
        return driver.findElement(profil).isDisplayed();
    }

    @Step("Click Constructor")
    public void clickConstructor() {
        driver.findElement(constructor).click();
    }

    @Step("Find header Burger")
    public boolean isDisplayHeaderBurger () {
        return driver.findElement(headerBurger).isDisplayed();
    }

    @Step("Log out")
    public void clickLogout() {
        driver.findElement(logout).click();
    }
}
