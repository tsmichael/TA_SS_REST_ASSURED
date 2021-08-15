package runners;

import entity.dto.author.AuthorDto;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import response.BaseResponse;
import service.AuthorService;
import service.bo.Verification;

import static org.apache.http.HttpStatus.*;

public class BaseAuthorRunner {

    protected AuthorService authorService = new AuthorService();

    protected AuthorDto authorDto;

    protected Verification verification = new Verification();

    @BeforeMethod(description = "Verify [POST] method to create new AUTHOR")
    public void createAuthorTest() {
        authorDto = new AuthorDto();
        BaseResponse response = authorService.createAuthor(authorDto);

        verification
                .verifyStatusCode(response.getStatusCode(), SC_CREATED)
                .verifyById(response.getAuthor().getAuthorId(), authorDto.getAuthorId());
    }

    @AfterMethod(description = "Verify [DELETE] method to delete AUTHOR by authorId")
    public void deleteAuthorTest() {
        BaseResponse response = authorService.deleteAuthor(authorDto.getAuthorId());

        verification
                .verifyStatusCode(response.getStatusCode(), SC_NO_CONTENT);
    }
}
