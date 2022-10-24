package PageObject;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class ConstructorPageTest {

    private WebDriver driver;
    private ConstructorPage constPage;
    private MainPage objMainPage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        this.objMainPage = new MainPage(driver);
        driver.get(objMainPage.URL);
        this.constPage = new ConstructorPage(driver);
        driver.manage().timeouts().implicitlyWait (3, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Choose type of ingredient")
    public void chooseTypeOfIngredient() {
        constPage.clickFillings();
        assertTrue(constPage.isDisplayFillings());
        constPage.clickSauces();
        assertTrue(constPage.isDisplaySauces());
        constPage.clickBuns();
        assertTrue(constPage.isDisplayBuns());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}