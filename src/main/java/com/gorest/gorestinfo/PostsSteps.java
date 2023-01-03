package com.gorest.gorestinfo;

import com.gorest.constants.EndPoints;
import com.gorest.model.PostsPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class PostsSteps {
    @Step("Getting all posts")
    public ValidatableResponse getAllPosts() {
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer d235d8fe8e2001308dc4860ffd7a004a969e0b94a8680259cf66e639f3cb687b")
                .when()
                .get(EndPoints.GET_ALL_POST)
                .then();
    }

    @Step ("Create post with id: {0}, user_id: {1}, title: {2}, body {3}")
    public ValidatableResponse createPost(int id, int user_id, String title, String body) {
        PostsPojo postsPojo = new PostsPojo();
        postsPojo.setId(id);
        postsPojo.setUser_id(user_id);
        postsPojo.setTitle(title);
        postsPojo.setBody(body);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer d235d8fe8e2001308dc4860ffd7a004a969e0b94a8680259cf66e639f3cb687b")
                .body(postsPojo)
                .when()
                .post(EndPoints.CREATE_POST)
                .then();
    }

    @Step ("Get the post information with Id : {0}")
    public HashMap<String, Object> getPostsById(int postID){

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer d235d8fe8e2001308dc4860ffd7a004a969e0b94a8680259cf66e639f3cb687b")
                .pathParam("postID", postID)
                .when()
                .get(EndPoints.GET_POST_BY_ID)
                .then()
                .statusCode(200)
                .extract()
                .path("");
    }

    @Step("Update post with id: {0}, user_id: {1}, title: {2}, body {3}")
    public ValidatableResponse updatePost(int postId, int id, int user_id, String title, String body) {
        PostsPojo postsPojo = new PostsPojo();
        postsPojo.setId(id);
        postsPojo.setUser_id(user_id);
        postsPojo.setTitle(title);
        postsPojo.setBody(body);

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer d235d8fe8e2001308dc4860ffd7a004a969e0b94a8680259cf66e639f3cb687b")
                .body(postsPojo)
                .pathParam("postID",postId)
                .when()
                .patch(EndPoints.UPDATE_POST_BY_ID)
                .then();
    }

    @Step("Deleting post with ID {0}")
    public ValidatableResponse deletePost(int postID){
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer d235d8fe8e2001308dc4860ffd7a004a969e0b94a8680259cf66e639f3cb687b")
                .pathParam("postID", postID)
                .when()
                .delete(EndPoints.DELETE_POST_BY_ID)
                .then();
    }

    @Step("Getting Post with ID {0}")
    public ValidatableResponse getPostsByID(int postID){
        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer d235d8fe8e2001308dc4860ffd7a004a969e0b94a8680259cf66e639f3cb687b")
                .pathParam("postID", postID)
                .when()
                .get(EndPoints.GET_POST_BY_ID)
                .then();
    }
}
