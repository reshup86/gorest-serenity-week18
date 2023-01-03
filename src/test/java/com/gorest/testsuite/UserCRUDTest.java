package com.gorest.testsuite;

import com.gorest.gorestinfo.UserSteps;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class UserCRUDTest extends TestBase {
    static String name = "Sia" + TestUtils.getRandomValue();
    static String email = "sia123" + TestUtils.getRandomValue() + "@example.com";
    static String gender = "female";
    static String status = "active";

    static int userID;

    @Steps
    UserSteps userSteps;

    @Title("This will create a new user")
    @Test
    public void test001() {
        ValidatableResponse response = userSteps.createUser(name, email, gender, status);
        response.log().all().statusCode(201);
        userID = response.log().all().extract().path("id");
    }

    @Title("This will verify that user was added")
    @Test
    public void test002() {

        HashMap<String, Object> userMap = userSteps.getUserById(userID);
        Assert.assertThat(userMap, hasValue(name));
    }

    @Title("This test will Update the user information")
    @Test
    public void test003() {
        name = name + "_updated";
        email = "siap123"+TestUtils.getRandomValue()+"@email.com";
        ValidatableResponse response = userSteps.updateUser(userID, name, gender, email, status);
        response.log().all().statusCode(200);
        HashMap<String, Object> userMap = response.log().all().extract().path("");
        Assert.assertThat(userMap, hasValue(name));
    }

    @Title("This test will Delete the user by ID")
    @Test
    public void test004() {
        userSteps.deleteUser(userID).statusCode(204).log().all();
        userSteps.getUserByID(userID).statusCode(404).log().all();
    }
}
