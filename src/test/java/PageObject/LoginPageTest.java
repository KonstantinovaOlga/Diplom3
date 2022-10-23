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

public class LoginPageTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private RegistrationPage regPage;
    private UserObj user;
    private MainPage objMainPage;

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

        loginPage = new LoginPage(driver);
        regPage = new RegistrationPage(driver);
    }

    @Test
    @DisplayName("Login with 'Input to Account'")
    public void loginInputAccount() {
        driver.manage().timeouts().implicitlyWait (3, TimeUnit.SECONDS);
        objMainPage.clickLoginAccount();
        driver.manage().timeouts().implicitlyWait (3, TimeUnit.SECONDS);
        loginPage.fillEmail(this.user.getEmail());
        loginPage.fillPassword(user.getPassword());
        loginPage.clickLogin();
        driver.manage().timeouts().implicitlyWait (3, TimeUnit.SECONDS);
        assertTrue(loginPage.isDisplayDoOrder());
    }

    @Test
    @DisplayName("Login with 'Personal Cabinet'")
    public void loginPersonalCabinet() {
        driver.manage().timeouts().implicitlyWait (3, TimeUnit.SECONDS);
        loginPage.clickLK();
        driver.manage().timeouts().implicitlyWait (3, TimeUnit.SECONDS);
        loginPage.fillEmail(this.user.getEmail());
        loginPage.fillPassword(user.getPassword());
        loginPage.clickLogin();
        driver.manage().timeouts().implicitlyWait (3, TimeUnit.SECONDS);
        assertTrue(loginPage.isDisplayDoOrder());
    }

    @Test
    @DisplayName("Login with Registration form - Input")
    public void loginInputRegistrationForm() {
        driver.manage().timeouts().implicitlyWait (3, TimeUnit.SECONDS);
        objMainPage.clickLoginAccount();
        regPage.scrollToRegistration();
        regPage.clickNewUser();
        loginPage.clickInputRegForm();
        driver.manage().timeouts().implicitlyWait (3, TimeUnit.SECONDS);
        loginPage.fillEmail(this.user.getEmail());
        loginPage.fillPassword(user.getPassword());
        loginPage.clickLogin();
        driver.manage().timeouts().implicitlyWait (3, TimeUnit.SECONDS);
        assertTrue(loginPage.isDisplayDoOrder());
    }

    @Test
    @DisplayName("Login with Forgot Password - Input")
    public void loginForgotPassword() {
        driver.manage().timeouts().implicitlyWait (3, TimeUnit.SECONDS);
        objMainPage.clickLoginAccount();
        loginPage.scrollToForgotPassword();
        loginPage.clickForgotPassword();
        loginPage.clickInputRegForm();
        driver.manage().timeouts().implicitlyWait (3, TimeUnit.SECONDS);
        loginPage.fillEmail(this.user.getEmail());
        loginPage.fillPassword(user.getPassword());
        loginPage.clickLogin();
        driver.manage().timeouts().implicitlyWait (3, TimeUnit.SECONDS);
        assertTrue(loginPage.isDisplayDoOrder());
    }

    @After
    public void tearDown() {
        driver.quit();
        this.userAPI.defineToken();
        this.userAPI.deleteUser();
    }
}