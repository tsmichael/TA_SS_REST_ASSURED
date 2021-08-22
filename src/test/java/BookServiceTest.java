import entity.ListOptions;
import org.testng.annotations.Test;
import response.BaseResponse;
import runners.BaseBookRunner;

import java.util.List;

import static org.apache.http.HttpStatus.*;

public class BookServiceTest extends BaseBookRunner {

    @Test
    public void getBookByIdTest() {
        BaseResponse response = bookService.getBook(bookDto.getBookId());

        verification
                .verifyStatusCode(response.getStatusCode(), SC_OK)
                .verifyById(response.getBook().getBookId(), bookDto.getBookId());
    }

    @Test(description = "Verify [GET] method to get info about BOOKS")
    public void getBooksTest() {
        ListOptions listOptions = new ListOptions();
        BaseResponse response = bookService.getBooks(listOptions);

        verification
                .verifyStatusCode(response.getStatusCode(), SC_OK)
                .verifyResponseListIsNotEmpty(response.getBooksArray());
    }

    @Test(description = "Verify [PUT] method to update info about BOOK")
    public void updateBookTest() {
        bookDto.setBookLanguage("abrakadabra");
        bookDto.setBookDescription("nothing about all");

        BaseResponse response = bookService.updateBook(bookDto);

        verification
                .verifyStatusCode(response.getStatusCode(), SC_OK);
    }

    @Test(description = "Verify [GET] method to get info about BOOKS with name which contains some string-query")
    public void getBooksByNameTest() {
        String searchName = bookDto.getBookName();

        BaseResponse response = bookService.getBooksByName(searchName);

        List<String> booksNames = response.getBooksNamesList();

        verification
                .verifyStatusCode(response.getStatusCode(), SC_OK)
                .verifyResponseListIsNotEmpty(response.getBooksArray())
                .verifySearchContainsQuery(searchName, booksNames);
    }
}
