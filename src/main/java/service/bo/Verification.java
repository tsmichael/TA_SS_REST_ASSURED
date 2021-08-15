package service.bo;

import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class Verification {
    @Step("Verify Status Code")
    public Verification verifyStatusCode(int actualResponseCode, int expectedResponseCode) {
        Assert.assertEquals(actualResponseCode, expectedResponseCode, "Response returned with inappropriate status code.");
        return this;
    }

    @Step("Verify by Id")
    public Verification verifyById(int actualAuthorId, int expectedAuthorId) {
        Assert.assertEquals(actualAuthorId, expectedAuthorId, "Actual authorId is not match expectations");
        return this;
    }

    public Verification verifyEqualsBody(String actualBody, String expectedBody) {
        Assert.assertEquals(actualBody, expectedBody, "Response body is not match with expectations");
        return this;
    }

    @Step("Verify List elements contains '{0}'")
    public Verification verifySearchContainsQuery(String query, List<String> namesList) {
        SoftAssert softAssert = new SoftAssert();
        for (String name : namesList) {
            boolean contain = name.toLowerCase().contains(query.toLowerCase());
            softAssert.assertTrue(contain, String.format("'%s' is not contains inside '%s'", query, name));
        }
        softAssert.assertAll();
        return this;
    }

    @Step("Verify response array is not empty")
    public Verification verifyResponseListIsNotEmpty(Object[] responseList) {
        int empty = 0;
        Assert.assertNotEquals(responseList.length, empty, "Response list is empty");
        return this;
    }
}
