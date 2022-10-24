package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConstructorPage {
    private final WebDriver driver;
    private final By bunsTab = By.xpath(".//main//span[text()='Булки']");
    private final By saucesTab = By.xpath(".//main//span[text()='Соусы']");
    private final By fillingsTab = By.xpath(".//main//span[text()='Начинки']");

    private final By bunsHeader = By.xpath(".//main//h2[text()='Булки']");
    private final By saucesHeader = By.xpath(".//main//span[text()='Соусы']");
    private final By fillingsHeader = By.xpath(".//main//span[text()='Начинки']");

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click Sauces")
    public void clickSauces() {
        driver.findElement(saucesTab).click();
    }

    @Step("Click Fillings")
    public void clickFillings() {
        driver.findElement(fillingsTab).click();
    }

    @Step("Click Buns")
    public void clickBuns() {
        driver.findElement(bunsTab).click();
    }

    @Step("Find header Buns")
    public boolean isDisplayBuns () {
        return driver.findElement(bunsHeader).isDisplayed();
    }

    @Step("Find header Sauces")
    public boolean isDisplaySauces () {
        return driver.findElement(saucesHeader).isDisplayed();
    }

    @Step("Find header Fillings")
    public boolean isDisplayFillings () {
        return driver.findElement(fillingsHeader).isDisplayed();
    }
}
