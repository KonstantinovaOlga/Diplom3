package PageObject;

import UserAPI.UserAPI;
import UserAPI.UserObj;
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

public class LKPageTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private RegistrationPage regPage;
    private MainPage objMainPage;
    private LKPage lkPage;
    private UserObj user;

    private UserAPI userAPI;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        this.objMainPage = new MainPage(driver);
        driver.get(objMainPage.URL);

        this.user = new UserObj();
        this.user = this.user.getRandomUser("password");

        RestAssured.baseURI = objMainPage.URL;
        this.userAPI = new UserAPI(this.user);
        this.userAPI.addUser();

        this.loginPage = new LoginPage(driver);
        this.regPage = new RegistrationPage(driver);
        this.lkPage = new LKPage(driver);

        driver.manage().timeouts().implicitlyWait (3, TimeUnit.SECONDS);
        objMainPage.clickLoginAccount();
        driver.manage().timeouts().implicitlyWait (3, TimeUnit.SECONDS);
        loginPage.fillEmail(this.user.getEmail());
        loginPage.fillPassword(user.getPassword());
        loginPage.clickLogin();
        driver.manage().timeouts().implicitlyWait (3, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Open LK'")
    public void openLK() {
        loginPage.clickLK();
        assertTrue(lkPage.isDisplayProfile());
    }

    @Test
    @DisplayName("Go to constructor'")
    public void openConstructor() {
        loginPage.clickLK();
        lkPage.clickConstructor();
        assertTrue(lkPage.isDisplayHeaderBurger());
    }

    @Test
    @DisplayName("Log out'")
    public void logOut() {
        loginPage.clickLK();
        lkPage.clickLogout();
        assertTrue(regPage.isDisplayInput());
    }

    @After
    public void tearDown() {
        driver.quit();
        this.userAPI.defineToken();
        this.userAPI.deleteUser();
    }
}