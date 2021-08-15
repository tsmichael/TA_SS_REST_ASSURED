package service;

import client.HttpClient;
import constants.ApiEndpoint;
import entity.ListOptions;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import response.BaseResponse;
import utils.EndpointBuilder;
import utils.JsonReader;

public class BookService {

    private static final Logger LOG = Logger.getLogger(BookService.class);

    private JsonReader jsonReader = new JsonReader();

    @Step("Create Book")
    public BaseResponse<Object> createBook(Object book, int authorId, int genreId) {
        EndpointBuilder endpoint = new EndpointBuilder()
                .pathParameter(ApiEndpoint.BOOK)
                .pathParameter(authorId)
                .pathParameter(genreId);
        LOG.info("Endpoint CALL : " + endpoint.get());
        return new BaseResponse<>(HttpClient.post(endpoint.get(), jsonReader.objectToJson(book)), Object.class);
    }

    @Step("Update Book")
    public BaseResponse<Object> updateBook(Object book) {
        EndpointBuilder endpoint = new EndpointBuilder().pathParameter(ApiEndpoint.BOOK);
        LOG.info("Endpoint CALL : " + endpoint.get());
        return new BaseResponse<>(HttpClient.put(endpoint.get(), jsonReader.objectToJson(book)), Object.class);
    }

    @Step("Get Book by bookId")
    public BaseResponse<Object> getBook(int bookId) {
        String endpoint = new EndpointBuilder().pathParameter(ApiEndpoint.BOOK).pathParameter(bookId).get();
        LOG.info("Endpoint CALL : " + endpoint);
        return new BaseResponse<>(HttpClient.get(endpoint), Object.class);
    }

    @Step("Get List of Books")
    public BaseResponse<Object> getBooks(ListOptions options) {
        EndpointBuilder endpoint = new EndpointBuilder().pathParameter(ApiEndpoint.BOOKS);
        if (options.orderType != null) endpoint.queryParam("orderType", options.orderType);
        endpoint
                .queryParam("page", options.page)
                .queryParam("pagination", options.pagination)
                .queryParam("size", options.size);
        if (options.sortBy != null) endpoint.queryParam("sortBy", options.sortBy);
        LOG.info("Endpoint CALL : " + endpoint.get());
        return new BaseResponse<>(HttpClient.get(endpoint.get()), Object.class);
    }

    @Step("Get Books with name which contains '{0}'")
    public BaseResponse<Object> getBooksByName(String name) {
        EndpointBuilder endpoint = new EndpointBuilder().pathParameter(ApiEndpoint.BOOKS).pathParameter(ApiEndpoint.SEARCH);
        endpoint
                .queryParam("q", name);
        LOG.info("Endpoint CALL : " + endpoint.get());
        return new BaseResponse<>(HttpClient.get(endpoint.get()), Object.class);
    }

    @Step("Get List of Books of Author by AuthorId")
    public BaseResponse<Object> getBooksOfAuthorByAuthorId(int authorId) {
        EndpointBuilder endpoint = new EndpointBuilder()
                .pathParameter(ApiEndpoint.AUTHOR)
                .pathParameter(authorId)
                .pathParameter(ApiEndpoint.BOOKS);
        LOG.info("Endpoint CALL : " + endpoint.get());
        return new BaseResponse<>(HttpClient.get(endpoint.get()), Object.class);
    }

    @Step("Get List of Books of Author in Genre")
    public BaseResponse<Object> getBooksOfAuthorByAuthorIdAndGenreByGenreId(int authorId, int genreId) {
        EndpointBuilder endpoint = new EndpointBuilder()
                .pathParameter(ApiEndpoint.AUTHOR)
                .pathParameter(authorId)
                .pathParameter(ApiEndpoint.GENRE)
                .pathParameter(genreId)
                .pathParameter(ApiEndpoint.BOOKS);
        LOG.info("Endpoint CALL : " + endpoint.get());
        return new BaseResponse<>(HttpClient.get(endpoint.get()), Object.class);
    }

    @Step("Delete Book")
    public BaseResponse<Object> deleteBook(int bookId) {
        String endpoint = new EndpointBuilder().pathParameter(ApiEndpoint.BOOK).pathParameter(bookId).get();
        LOG.info("Endpoint CALL : " + endpoint);
        return new BaseResponse<>(HttpClient.delete(endpoint), Object.class);
    }
}
