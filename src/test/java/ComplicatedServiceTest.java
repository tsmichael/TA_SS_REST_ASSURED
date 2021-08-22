import entity.ListOptions;
import org.testng.annotations.Test;
import response.BaseResponse;
import service.AuthorService;
import service.BookService;
import service.GenreService;
import service.bo.Verification;

import java.util.Random;

import static org.apache.http.HttpStatus.*;

public class ComplicatedServiceTest {
    private Random random = new Random();

    private Verification verification = new Verification();

    private AuthorService authorService = new AuthorService();
    private GenreService genreService = new GenreService();
    private BookService bookService = new BookService();

    private ListOptions listOptions = new ListOptions();

    //region Author

    @Test(description = "Verify [GET] method to get info about AUTHORS of special GENRE by genreId, pre-condition: getGenres and pick one of them for request",
            groups = {"AuthorTest"})
    public void getAuthorsOfGenreByGenreIdTest() {
        int[] genres = genreService.getGenres(listOptions).getGenresId();
        int randomIndex = random.nextInt(genres.length);

        BaseResponse response = authorService.getAuthorsOfGenreByGenreId(genres[randomIndex]);

        verification
                .verifyStatusCode(response.getStatusCode(), SC_OK)
                .verifyResponseListIsNotEmpty(response.getAuthorsArray());
    }

    @Test(description = "Verify [GET] method to get info about AUTHOR of special BOOK by bookId, pre-condition: getBooks and pick one of them for request",
            groups = {"AuthorTest"})
    public void getAuthorOfBookByBookId() {
        int[] books = new BookService().getBooks(listOptions).getBooksId();
        int randomIndex = random.nextInt(books.length);

        BaseResponse response = authorService.getAuthorOfBookByBookId(books[randomIndex]);

        verification
                .verifyStatusCode(response.getStatusCode(), SC_OK);
    }
    //endregion

    //region Genre
    @Test(description = "Verify [GET] method to get info about GENRES of special AUTHOR by authorId, pre-condition: getAuthors and pick one of them for request",
            groups = {"GenreTest"})
    public void getGenresOfAuthorByAuthorIdTest() {
        int[] authors = authorService.getAuthors(listOptions).getAuthorsId();
        int randomIndex = random.nextInt(authors.length);

        BaseResponse response = genreService.getGenresOfAuthorByAuthorId(authors[randomIndex]);

        verification
                .verifyStatusCode(response.getStatusCode(), SC_OK)
                .verifyResponseListIsNotEmpty(response.getGenresArray());
    }

    @Test(description = "Verify [GET] method to get info about GENRE of special BOOK by bookId, pre-condition: getBooks and pick one of them for request",
            groups = {"GenreTest"})
    public void getGenreOfBookByBookIdTest() {
        int[] books = new BookService().getBooks(listOptions).getBooksId();
        int randomIndex = random.nextInt(books.length);

        BaseResponse response = genreService.getGenreOfBookByBookId(books[randomIndex]);

        verification
                .verifyStatusCode(response.getStatusCode(), SC_OK);
    }

    //endregion

    //region Book

    @Test(description = "Verify [GET] method to get info about BOOKS of special AUTHOR by authorId",
            groups = {"BookTest"})
    public void getBooksOfAuthorByAuthorIdTest() {
        int[] authors = authorService.getAuthors(listOptions).getAuthorsId();
        int randomIndex = random.nextInt(authors.length);

        BaseResponse response = bookService.getBooksOfAuthorByAuthorId(authors[randomIndex]);

        verification
                .verifyStatusCode(response.getStatusCode(), SC_OK)
                .verifyResponseListIsNotEmpty(response.getBooksArray());
    }

    @Test(description = "Verify [GET] method to get info about BOOKS of special AUTHOR by authorId and special GENRE by genreId",
            groups = {"BookTest"})
    public void getBooksOfAuthorByAuthorIdAndGenreByGenreIdTest() {

        int[] authorsId = authorService.getAuthors(listOptions).getAuthorsId();
        int authorId = authorsId[random.nextInt(authorsId.length)];

        int[] genresId = genreService.getGenresOfAuthorByAuthorId(authorId).getGenresId();
        int genreId = genresId[random.nextInt(genresId.length)];

        BaseResponse response = bookService
                .getBooksOfAuthorByAuthorIdAndGenreByGenreId(authorId, genreId);

        verification
                .verifyStatusCode(response.getStatusCode(), SC_OK)
                .verifyResponseListIsNotEmpty(response.getBooksArray());
    }
    //endregion
}
