package runners;

import entity.dto.author.AuthorDto;
import entity.dto.book.BookDto;
import entity.dto.genre.GenreDto;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import response.BaseResponse;
import service.AuthorService;
import service.BookService;
import service.GenreService;
import service.bo.Verification;

import static org.apache.http.HttpStatus.*;

public class BaseBookRunner {

    protected BookService bookService = new BookService();

    private AuthorDto authorDto;
    private GenreDto genreDto;

    protected BookDto bookDto = new BookDto();

    protected Verification verification = new Verification();

    @BeforeClass
    public void createAuthorAndGenre() {
        authorDto = new AuthorDto();
        new AuthorService().createAuthor(authorDto);

        genreDto = new GenreDto();
        new GenreService().createGenre(genreDto);
    }

    @BeforeMethod
    public void createBookTest() {
        BaseResponse response = bookService.createBook(bookDto, authorDto.getAuthorId(), genreDto.getGenreId());

        verification
                .verifyStatusCode(response.getStatusCode(), SC_CREATED)
                .verifyById(response.getBook().getBookId(), bookDto.getBookId());
    }

    @AfterMethod
    public void deleteBookTest() {
        BaseResponse response = bookService.deleteBook(bookDto.getBookId());

        verification
                .verifyStatusCode(response.getStatusCode(), SC_NO_CONTENT);
    }

    @AfterClass
    public void deleteAuthorAndGenre() {
        new AuthorService().deleteAuthor(authorDto.getAuthorId());

        new GenreService().deleteGenre(genreDto.getGenreId());
    }
}
