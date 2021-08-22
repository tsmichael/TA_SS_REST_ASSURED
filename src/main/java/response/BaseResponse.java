package response;

import entity.dto.author.AuthorDto;
import entity.dto.book.BookDto;
import entity.dto.genre.GenreDto;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class BaseResponse<T> {
    private Response response;
    private Class<T> responseClass;

    private static final Logger LOG = Logger.getLogger(BaseResponse.class);

    public BaseResponse(Response response, Class<T> responseClass) {
        this.response = response;
        this.responseClass = responseClass;
    }

    public int getStatusCode() {
        return this.response.getStatusCode();
    }

    public String getHeader(String header) {
        return this.response.getHeader(header);
    }

    public String getBody() {
        return this.response.body().asString();
    }

    //region Genre
    @Step("Parse body as Genre object")
    public GenreDto getGenre() {
        return this.response.body().as(GenreDto.class);
    }

    @Step("Parse body as array of Genre objects")
    public GenreDto[] getGenresArray() {
        return (this.response.body().as(GenreDto[].class));
    }

    @Step("Parse array of Genres and get their Ids")
    public int[] getGenresId() {
        GenreDto[] genreDtosArray = getGenresArray();
        int[] genresId = new int[genreDtosArray.length];
        for (int i = 0; i < genreDtosArray.length; i++) {
            genresId[i] = genreDtosArray[i].getGenreId();
        }
        return genresId;
    }

    //endregion
    //region Author
    @Step("Parse body as Author object")
    public AuthorDto getAuthor() {
        return this.response.body().as(AuthorDto.class);
    }

    @Step("Parse body as array of Authors")
    public AuthorDto[] getAuthorsArray() {
        return (this.response.body().as(AuthorDto[].class));
    }

    @Step("Parse array of Authors and get their Ids")
    public int[] getAuthorsId() {
        AuthorDto[] authorDtosArray = getAuthorsArray();
        int[] authorsId = new int[authorDtosArray.length];
        for (int i = 0; i < authorDtosArray.length; i++) {
            authorsId[i] = authorDtosArray[i].getAuthorId();
        }
        return authorsId;
    }

    @Step("Parse array of Authors and get their names")
    public List<String> getAuthorsNamesList() {
        List<String> authorsNames = new ArrayList<>();
        for (AuthorDto author : getAuthorsArray()) {
            authorsNames.add(author.getAuthorName().getFirst() + " " + author.getAuthorName().getSecond());
        }
        return authorsNames;
    }

    //endregion
    //region Book
    @Step("Parse body as Book object")
    public BookDto getBook() {
        return this.response.body().as(BookDto.class);
    }

    @Step("Parse body as array of Book objects")
    public BookDto[] getBooksArray() {
        return this.response.getBody().as(BookDto[].class);
    }

    @Step("Parse array of Books and get their Ids")
    public int[] getBooksId() {
        BookDto[] bookDtosArray = getBooksArray();
        int[] booksId = new int[bookDtosArray.length];
        for (int i = 0; i < bookDtosArray.length; i++) {
            booksId[i] = bookDtosArray[i].getBookId();
        }
        return booksId;
    }

    @Step("Parse array of Books and get their names")
    public List<String> getBooksNamesList() {
        List<String> booksNames = new ArrayList<>();
        for (BookDto book : getBooksArray()) {
            booksNames.add(book.getBookName());
        }
        return booksNames;
    }
    //endregion
}
