package utils;

public class EndpointBuilder {
    private String endpoint;

    public EndpointBuilder() {
        this.endpoint = "/api/library";
    }

    public EndpointBuilder pathId(String id) {
        this.endpoint += "/" + id;
        return this;
    }

    public EndpointBuilder pathId(int id) {
        return this.pathId(String.valueOf(id));
    }

    public EndpointBuilder genrePath() {
        this.endpoint += "/genre";
        return this;
    }

    public EndpointBuilder genresPath() {
        this.endpoint += "/genres";
        return this;
    }

    public EndpointBuilder size(int size) {
        this.addParam("size", String.valueOf(size));
        return this;
    }

    public String get() {
        return this.endpoint;
    }

    private void addParam(String param, String value) {
        String delimiter;
        if (this.endpoint.contains("?")) delimiter = "&";
        else delimiter = "?";
        this.endpoint += delimiter + param + "=" + value;
    }
}
