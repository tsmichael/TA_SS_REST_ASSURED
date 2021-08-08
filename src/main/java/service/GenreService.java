package service;

import client.HttpClient;
import response.BaseResponse;
import utils.EndpointBuilder;

public class GenreService {

    private EndpointBuilder endpoint;
    // feel free to change "String body" to whatever suits you
    private String body;

    public GenreService() {
        this.endpoint = new EndpointBuilder();
    }

    public GenreService genre() {
        this.endpoint.genrePath();
        return this;
    }

    public GenreService genre(int genreId) {
        this.endpoint.genrePath().pathId(genreId);
        return this;
    }

    public GenreService genres() {
        this.endpoint.genresPath();
        return this;
    }

    public GenreService body(String body) {
        this.body = body;
        return this;
    }

    public GenreService size(int size) {
        this.endpoint.size(size);
        return this;
    }

    public BaseResponse get() {
        return HttpClient.get(this.endpoint.get());
    }

    public BaseResponse post() {
        return HttpClient.post(this.endpoint.get(), this.body);
    }
}
