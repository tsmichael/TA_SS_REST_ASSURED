import entity.ListOptions;
import org.testng.annotations.Test;
import response.BaseResponse;
import runners.BaseAuthorRunner;

import static org.apache.http.HttpStatus.*;

public class AuthorServiceTest extends BaseAuthorRunner {

    @Test(description = "Verify [GET] method to get info about AUTHOR by authorId")
    public void getAuthorByIdTest() {
        BaseResponse response = authorService.getAuthor(authorDto.getAuthorId());

        verification
                .verifyStatusCode(response.getStatusCode(), SC_OK)
                .verifyById(response.getAuthor().getAuthorId(), authorDto.getAuthorId());
    }

    @Test(description = "Verify [GET] method to get info about AUTHORS") //more than 1 or not empty
    public void getAuthorsTest() {
        ListOptions listOptions = new ListOptions();
        BaseResponse response = authorService.getAuthors(listOptions);

        verification
                .verifyStatusCode(response.getStatusCode(), SC_OK)
                .verifyResponseListIsNotEmpty(response.getAuthorsArray());
    }

    @Test(description = "Verify [PUT] method to update info about AUTHOR")
    public void updateAuthorTest() {
        authorDto.setAuthorDescription("author under update");
        authorDto.setNationality("horachiyHlop");

        BaseResponse response = authorService.updateAuthor(authorDto);

        verification
                .verifyStatusCode(response.getStatusCode(), SC_OK);
    }

    @Test(description = "Verify [GET] method to get info about AUTHORS with name which contains some string-query")
    public void getAuthorsByNameTest() {
        String searchName = authorDto.getAuthorName().getSecond();

        BaseResponse response = authorService.getAuthorsByName(searchName);

        verification
                .verifyStatusCode(response.getStatusCode(), SC_OK)
                .verifyResponseListIsNotEmpty(response.getAuthorsArray())
                .verifySearchContainsQuery(searchName, response.getAuthorsNamesList());
    }
}
