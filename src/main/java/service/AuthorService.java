package service;

import client.HttpClient;
import constants.ApiEndpoint;
import entity.ListOptions;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import response.BaseResponse;
import utils.EndpointBuilder;
import utils.JsonReader;

public class AuthorService {

    private static final Logger LOG = Logger.getLogger(AuthorService.class);

    private JsonReader jsonReader = new JsonReader();

    @Step("Create Author")
    public BaseResponse<Object> createAuthor(Object author) {
        String endpoint = new EndpointBuilder().pathParameter(ApiEndpoint.AUTHOR).get();
        LOG.info("Endpoint CALL : " + endpoint);
        return new BaseResponse<>(HttpClient.post(endpoint, jsonReader.objectToJson(author)), Object.class);
    }

    @Step("Update Author")
    public BaseResponse<Object> updateAuthor(Object author) {
        String endpoint = new EndpointBuilder().pathParameter(ApiEndpoint.AUTHOR).get();
        LOG.info("Endpoint CALL : " + endpoint);
        return new BaseResponse<>(HttpClient.put(endpoint, jsonReader.objectToJson(author)), Object.class);
    }

    @Step("Get Author by authorId")
    public BaseResponse<Object> getAuthor(int authorId) {
        String endpoint = new EndpointBuilder().pathParameter(ApiEndpoint.AUTHOR).pathParameter(authorId).get();
        LOG.info("Endpoint CALL : " + endpoint);
        return new BaseResponse<>(HttpClient.get(endpoint), Object.class);
    }

    @Step("Get Authors with name which contains '{0}'")
    public BaseResponse<Object> getAuthorsByName(String name) {

        EndpointBuilder endpoint = new EndpointBuilder().pathParameter(ApiEndpoint.AUTHORS).pathParameter(ApiEndpoint.SEARCH);
        endpoint
                .queryParam("query", name);
        LOG.info("Endpoint CALL : " + endpoint.get());
        return new BaseResponse<>(HttpClient.get(endpoint.get()), Object.class);
    }

    @Step("Get List of Authors")
    public BaseResponse<Object> getAuthors(ListOptions options) {
        EndpointBuilder endpoint = new EndpointBuilder().pathParameter(ApiEndpoint.AUTHORS);
        if (options.orderType != null) endpoint.queryParam("orderType", options.orderType);
        endpoint
                .queryParam("page", options.page)
                .queryParam("pagination", options.pagination)
                .queryParam("size", options.size);
        if (options.sortBy != null) endpoint.queryParam("sortBy", options.sortBy);
        LOG.info("Endpoint CALL : " + endpoint.get());
        return new BaseResponse<>(HttpClient.get(endpoint.get()), Object.class);
    }

    @Step("Get List of Authors of Genre by GenreId")
    public BaseResponse<Object> getAuthorsOfGenreByGenreId(int genreId) {
        EndpointBuilder endpoint = new EndpointBuilder()
                .pathParameter(ApiEndpoint.GENRE)
                .pathParameter(genreId)
                .pathParameter(ApiEndpoint.AUTHORS);
        LOG.info("Endpoint CALL : " + endpoint.get());
        return new BaseResponse<>(HttpClient.get(endpoint.get()), Object.class);
    }

    @Step("Get Author of Book by BookId")
    public BaseResponse<Object> getAuthorOfBookByBookId(int bookId) {
        EndpointBuilder endpoint = new EndpointBuilder()
                .pathParameter(ApiEndpoint.BOOK)
                .pathParameter(bookId)
                .pathParameter(ApiEndpoint.AUTHOR);
        LOG.info("Endpoint CALL : " + endpoint.get());
        return new BaseResponse<>(HttpClient.get(endpoint.get()), Object.class);
    }

    @Step("Delete Author")
    public BaseResponse<Object> deleteAuthor(int authorId) {
        String endpoint = new EndpointBuilder().pathParameter(ApiEndpoint.AUTHOR).pathParameter(authorId).get();
        LOG.info("Endpoint CALL : " + endpoint);
        return new BaseResponse<>(HttpClient.delete(endpoint), Object.class);
    }
}
