package com.gorest.testsuite;

import com.gorest.gorestinfo.PostsSteps;
import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class PostCRUDTest extends TestBase {
    static int id = 129;
    static int user_id = 1290;
    static String title = "Acidus versus terebro claustrum modi tyrannus asdfg";
    static String body = "Curso combibo super. Voluptas demum speciosus. Enim cado alias. Candidus antea animus. Alter facilis sub. Autem creber amitto. Ambitus comptus sunt. Modi delinquo antiquus. Auxilium angustus volubilis. Aufero urbs balbus. Deorsum ultra vigor. Crux sponte synagoga. Consequatur triumphus tamisium. Vulgo avaritia cohaero. Capillus minus cattus. Aggredior ut iusto. Officiis torrens autem.";
    static int postID;

    @Steps
    PostsSteps postsSteps;

    @Title("This will get all posts users")
    @Test
    public void test001() {
        ValidatableResponse response = postsSteps.getAllPosts();
        List<?> totalRecord = response.log().all().extract().path("");
        Assert.assertEquals(10, totalRecord.size());
    }

    @Title("This test will Create a new Post")
    @Test
    public void test002() {
        ValidatableResponse response = postsSteps.createPost(id,user_id,title,body);
        response.log().all().statusCode(201);
        postID = response.log().all().extract().path("id");
        System.out.println(postID);
    }

    @Title("This will verify that post user was added")
    @Test
    public void test003() {

        HashMap<String, ?> userMap = postsSteps.getPostsById(postID);
        Assert.assertThat(userMap, hasValue(title));
    }

    @Title("This test will Update the title")
    @Test
    public void test004() {
        title = title + "_updated";
        ValidatableResponse response = postsSteps.updatePost(postID, id, user_id, title, body);
        response.log().all().statusCode(200);
        HashMap<String, Object> userMap = response.log().all().extract().path("");
        Assert.assertThat(userMap, hasValue(title));
    }

    @Title("This test will Delete the post")
    @Test
    public void test005() {
        postsSteps.deletePost(postID).statusCode(204).log().all();
        postsSteps.getPostsByID(postID).statusCode(404).log().all();
    }
}
