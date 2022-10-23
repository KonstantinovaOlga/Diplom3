package PageObject;

import UserAPI.UserObj;
import UserAPI.UserAPI;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class RegistrationPageTest {
    private WebDriver driver;
    private RegistrationPage regPage;
    private MainPage objMainPage;

    private UserObj user;
    private UserAPI userAPI;
    private boolean needDelete;

    @Before
    public void setUp() {


        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        this.objMainPage = new MainPage(driver);
        driver.get(objMainPage.URL);
        objMainPage.clickLoginAccount();
        regPage = new RegistrationPage(driver);
        needDelete=false;
        this.user = new UserObj();
    }

    @Test
    @DisplayName("Registration of new user with correct data")
    public void correctData() {
        user = user.getRandomUser("password");
        regPage.scrollToRegistration();
        regPage.clickNewUser();
        regPage.fillName(user.getName());
        regPage.fillEmail(user.getEmail());
        regPage.fillPassword(user.getPassword());
        regPage.clickRegistration();
        driver.manage().timeouts().implicitlyWait (3, TimeUnit.SECONDS);
        assertTrue(regPage.isDisplayInput());
        needDelete=true;
    }

    @Test
    @DisplayName("Registration with short (less than 6 symbol) password")
    public void shortPassword() {
        user = user.getRandomUser("passw");
        regPage.scrollToRegistration();
        regPage.clickNewUser();
        regPage.fillName(user.getName());
        regPage.fillEmail(user.getEmail());
        regPage.fillPassword(user.getPassword());
        regPage.clickRegistration();
        driver.manage().timeouts().implicitlyWait (3, TimeUnit.SECONDS);
        assertTrue(regPage.isDisplayError());
        needDelete=false;
    }

    @After
    public void tearDown() {
        driver.quit();
        if(needDelete) {
            RestAssured.baseURI = objMainPage.URL;
            this.userAPI = new UserAPI(this.user);
            this.userAPI.defineToken();
            this.userAPI.deleteUser();
        }
    }
}