package com.gorest.gorestinfo;

import com.gorest.constants.EndPoints;
import com.gorest.model.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class UserSteps {
    @Step("Creating user with name : {0}, gender:{1}, status:{2}, email:{3}")
    public ValidatableResponse createUser(String name, String email, String gender, String status){
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer d235d8fe8e2001308dc4860ffd7a004a969e0b94a8680259cf66e639f3cb687b")
                .body(userPojo)
                .when()
                .post(EndPoints.CREATE_USER)
                .then();
    }

    @Step ("Get the user information with Id : {0}")
    public HashMap<String, Object> getUserById(int userID){

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer d235d8fe8e2001308dc4860ffd7a004a969e0b94a8680259cf66e639f3cb687b")
                .pathParam("userID", userID)
                .when()
                .get(EndPoints.GET_USER_BY_ID)
                .then()
                .statusCode(200)
                .extract()
                .path("");
    }

    @Step("Update User with userId: {0}, name: {1},gender: {2}, email: {3}, status: {4}")
    public ValidatableResponse updateUser(int userID,String name,String gender,String email,String status) {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setGender(gender);
        userPojo.setEmail(email);
        userPojo.setStatus(status);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer d235d8fe8e2001308dc4860ffd7a004a969e0b94a8680259cf66e639f3cb687b")
                .body(userPojo)
                .pathParam("userID",userID)
                .when()
                .patch(EndPoints.UPDATE_USER_BY_ID)
                .then();
    }

    @Step("Deleting User with ID {0}")
    public ValidatableResponse deleteUser(int userID){
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer d235d8fe8e2001308dc4860ffd7a004a969e0b94a8680259cf66e639f3cb687b")
                .pathParam("userID", userID)
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then();
    }

    @Step("Getting User with ID {0}")
    public ValidatableResponse getUserByID(int userID){
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer d235d8fe8e2001308dc4860ffd7a004a969e0b94a8680259cf66e639f3cb687b")
                .pathParam("userID", userID)
                .when()
                .get(EndPoints.GET_USER_BY_ID)
                .then();
    }
}
