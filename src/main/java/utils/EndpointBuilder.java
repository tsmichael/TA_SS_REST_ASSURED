package utils;

import constants.ApiEndpoint;

public class EndpointBuilder {
    private String endpoint;

    public EndpointBuilder() {
        this.endpoint = "/api/library";
    }

    public EndpointBuilder pathParameter(String param) {
        this.endpoint += "/" + param;
        return this;
    }

    public EndpointBuilder pathParameter(ApiEndpoint param) {
        return this.pathParameter(String.valueOf(param));
    }

    public EndpointBuilder pathParameter(int param) {
        return this.pathParameter(String.valueOf(param));
    }

    public EndpointBuilder queryParam(String param, String value) {
        String delimiter;
        if (this.endpoint.contains("?")) delimiter = "&";
        else delimiter = "?";
        this.endpoint += delimiter + param + "=" + value;
        return this;
    }

    public EndpointBuilder queryParam(String param, int value) {
        return this.queryParam(param, String.valueOf(value));
    }

    public EndpointBuilder queryParam(String param, boolean value) {
        return this.queryParam(param, String.valueOf(value));
    }

    public String get() {
        return this.endpoint;
    }

}
