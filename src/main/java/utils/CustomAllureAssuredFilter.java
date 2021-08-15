package utils;

import io.qameta.allure.attachment.DefaultAttachmentProcessor;
import io.qameta.allure.attachment.FreemarkerAttachmentRenderer;
import io.qameta.allure.attachment.http.HttpRequestAttachment;
import io.qameta.allure.attachment.http.HttpResponseAttachment;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.filter.FilterContext;
import io.restassured.internal.NameAndValue;
import io.restassured.internal.support.Prettifier;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CustomAllureAssuredFilter extends AllureRestAssured {

    private String requestTemplatePath = "http-request.ftl";
    private String responseTemplatePath = "http-response.ftl";

    public CustomAllureAssuredFilter() {
    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext filterContext) {
        Prettifier prettifier = new Prettifier();
        HttpRequestAttachment.Builder requestAttachmentBuilder =
                HttpRequestAttachment.Builder.create("REQUEST", requestSpec.getURI())
                        .setMethod(requestSpec.getMethod())
                        .setHeaders(toMapConverter(requestSpec.getHeaders()));

        if (Objects.nonNull(requestSpec.getBody())) {
            requestAttachmentBuilder.setBody(prettifier.getPrettifiedBodyIfPossible(requestSpec));
        }

        HttpRequestAttachment requestAttachment = requestAttachmentBuilder.build();
        (new DefaultAttachmentProcessor()).addAttachment(requestAttachment, new FreemarkerAttachmentRenderer(this.requestTemplatePath));
        Response response = filterContext.next(requestSpec, responseSpec);
        HttpResponseAttachment.Builder responseAttachmentBuilder =
                HttpResponseAttachment.Builder.create("RESPONSE")
                        .setResponseCode(response.getStatusCode())
                        .setHeaders(toMapConverter(response.getHeaders()))
                        .setBody(prettifier.getPrettifiedBodyIfPossible(response, response.getBody()));
        HttpResponseAttachment responseAttachment = responseAttachmentBuilder.build();
        (new DefaultAttachmentProcessor()).addAttachment(responseAttachment, new FreemarkerAttachmentRenderer(this.responseTemplatePath));
        return response;
    }

    private static Map<String, String> toMapConverter(Iterable<? extends NameAndValue> items) {
        Map<String, String> result = new HashMap();
        items.forEach((h) -> {
            String var10000 = (String) result.put(h.getName(), h.getValue());
        });
        return result;
    }

}
