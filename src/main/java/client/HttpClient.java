package client;

import config.ServiceConfig;
import io.qameta.allure.Step;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import utils.CustomAllureAssuredFilter;

import static io.restassured.RestAssured.given;

public class HttpClient {
    private static final Logger LOG = Logger.getLogger(HttpClient.class);

    public static Response get(String endpoint) {
        LOG.info("Method [GET] used for request");
        return HttpClient.sendRequest(Method.GET, endpoint);
    }

    public static Response post(String endpoint, String body) {
        LOG.info("Method [POST] used for request");
        return HttpClient.sendRequest(Method.POST, endpoint, body);
    }

    public static Response put(String endpoint, String body) {
        LOG.info("Method [PUT] used for request");
        return HttpClient.sendRequest(Method.PUT, endpoint, body);
    }

    public static Response delete(String endpoint) {
        LOG.info("Method [DELETE] used for request");
        return HttpClient.sendRequest(Method.DELETE, endpoint);
    }

    private static Response sendRequest(Method method, String endpoint) {
        return HttpClient.sendRequest(method, endpoint, null);
    }

    @Step("Send Request")
    private static Response sendRequest(Method method, String endpoint, String body) {
        String url = ServiceConfig.HOST + endpoint;

        RequestSpecification spec = given();

        if (body != null) {
            spec.header("Content-Type", "application/json");
            spec.body(body);
        }
        spec
                .log()
                .all()
                .filter(new CustomAllureAssuredFilter());

        Response rawResponse = spec.request(method, url);
        return rawResponse;
    }
}
