package runners;

import entity.dto.genre.GenreDto;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import response.BaseResponse;
import service.GenreService;
import service.bo.Verification;

import static org.apache.http.HttpStatus.*;

public class BaseGenreRunner {
    protected GenreService genreService = new GenreService();
    protected GenreDto genreDto = new GenreDto();

    protected Verification verification = new Verification();

    @BeforeMethod(description = "Verify [POST] method to create new GENRE")
    public void createGenreTest() {
        BaseResponse response = genreService.createGenre(genreDto);

        verification
                .verifyStatusCode(response.getStatusCode(), SC_CREATED)
                .verifyStatusCode(response.getGenre().getGenreId(), genreDto.getGenreId());
    }

    @AfterMethod(description = "Verify [DELETE] method to delete GENRE")
    public void deleteGenreTest() {
        BaseResponse response = genreService.deleteGenre(genreDto.getGenreId());

        verification
                .verifyStatusCode(response.getStatusCode(), SC_NO_CONTENT);
    }
}
