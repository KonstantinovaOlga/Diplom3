package UserAPI;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserAPI {
    public static final String CREATE_USER = "api/auth/register";
    public static final String LOGIN_USER = "api/auth/login";
    public static final String UPDATE_USER = "api/auth/user";

    private String token;

    private final UserObj user;

    public UserAPI (UserObj user) {
        this.user=user;
    }

    @Step("Creating of user with API")
    public Response addUser() {
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(user)
                        .when()
                        .post(CREATE_USER);
        return response;
    }

    @Step("Define token with API")
    public void defineToken() {
        String token =
                given().
                        header("Content-type", "application/json").
                        and().
                        body(user).
                        when().
                        post(LOGIN_USER).
                        then().
                        statusCode(200).
                        extract().
                        path("accessToken");

        this.token=token.substring(7);
    }

    @Step("Delete User with API")
    public Response deleteUser() {
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .auth().oauth2(token)
                        .and()
                        .body(user)
                        .when()
                        .post(UPDATE_USER);
        return response;
    }

    public String getToken() {
        return token;
    }
}
