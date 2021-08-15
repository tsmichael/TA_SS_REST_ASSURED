package service;

import client.HttpClient;
import constants.ApiEndpoint;
import entity.ListOptions;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import response.BaseResponse;
import utils.EndpointBuilder;
import utils.JsonReader;

public class GenreService {

    private static final Logger LOG = Logger.getLogger(GenreService.class);

    private JsonReader jsonReader = new JsonReader();

    @Step("Create Genre")
    public BaseResponse<Object> createGenre(Object genre) {
        String endpoint = new EndpointBuilder().pathParameter(ApiEndpoint.GENRE).get();
        LOG.info("Endpoint CALL : " + endpoint);
        return new BaseResponse<>(HttpClient.post(endpoint, jsonReader.objectToJson(genre)), Object.class);
    }

    @Step("Update Genre")
    public BaseResponse<Object> updateGenre(Object genre) {
        String endpoint = new EndpointBuilder().pathParameter(ApiEndpoint.GENRE).get();
        LOG.info("Endpoint CALL : " + endpoint);
        return new BaseResponse<>(HttpClient.put(endpoint, jsonReader.objectToJson(genre)), Object.class);
    }

    @Step("Get Genre by genreId")
    public BaseResponse<Object> getGenre(int genreId) {
        String endpoint = new EndpointBuilder().pathParameter(ApiEndpoint.GENRE).pathParameter(genreId).get();
        LOG.info("Endpoint CALL : " + endpoint);
        return new BaseResponse<>(HttpClient.get(endpoint), Object.class);
    }

    @Step("Get List of Genres")
    public BaseResponse<Object> getGenres(ListOptions options) {
        EndpointBuilder endpoint = new EndpointBuilder().pathParameter(ApiEndpoint.GENRES);
        if (options.orderType != null) endpoint.queryParam("orderType", options.orderType);
        endpoint
                .queryParam("page", options.page)
                .queryParam("pagination", options.pagination)
                .queryParam("size", options.size);
        if (options.sortBy != null) endpoint.queryParam("sortBy", options.sortBy);
        LOG.info("Endpoint CALL : " + endpoint.get());
        return new BaseResponse<>(HttpClient.get(endpoint.get()), Object.class);
    }

    @Step("Get Genres with name which contain '{0}'")
    public BaseResponse<Object> getGenresByName(String name) {
        EndpointBuilder endpoint = new EndpointBuilder().pathParameter(ApiEndpoint.GENRES).pathParameter(ApiEndpoint.SEARCH);
        endpoint
                .queryParam("query", name);
        LOG.info("Endpoint CALL : " + endpoint.get());
        return new BaseResponse<>(HttpClient.get(endpoint.get()), Object.class);
    }

    @Step("Get Genres list of Author by authorId")
    public BaseResponse<Object> getGenresOfAuthorByAuthorId(int authorId) {
        EndpointBuilder endpoint = new EndpointBuilder()
                .pathParameter(ApiEndpoint.AUTHOR)
                .pathParameter(authorId)
                .pathParameter(ApiEndpoint.GENRES);
        LOG.info("Endpoint CALL : " + endpoint.get());
        return new BaseResponse<>(HttpClient.get(endpoint.get()), Object.class);
    }

    @Step("Get Genre of Book by bookId")
    public BaseResponse<Object> getGenreOfBookByBookId(int bookId) {
        EndpointBuilder endpoint = new EndpointBuilder()
                .pathParameter(ApiEndpoint.BOOK)
                .pathParameter(bookId)
                .pathParameter(ApiEndpoint.GENRE);
        LOG.info("Endpoint CALL : " + endpoint.get());
        return new BaseResponse<>(HttpClient.get(endpoint.get()), Object.class);
    }

    @Step("Delete Genre")
    public BaseResponse<Object> deleteGenre(int genreId) {
        String endpoint = new EndpointBuilder().pathParameter(ApiEndpoint.GENRE).pathParameter(genreId).get();
        LOG.info("Endpoint CALL : " + endpoint);
        return new BaseResponse<>(HttpClient.delete(endpoint), Object.class);
    }


}
