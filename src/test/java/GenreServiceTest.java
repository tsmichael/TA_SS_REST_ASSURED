import entity.ListOptions;
import entity.dto.genre.GenreDto;
import org.testng.annotations.Test;
import response.BaseResponse;
import runners.BaseGenreRunner;

import java.util.ArrayList;
import java.util.List;

import static org.apache.http.HttpStatus.*;

public class GenreServiceTest extends BaseGenreRunner {

    @Test(description = "Verify [GET] method to get info about GENRE by genreId")
    public void getGenreByIdTest() {
        BaseResponse response = genreService.getGenre(genreDto.getGenreId());

        verification
                .verifyStatusCode(response.getStatusCode(), SC_OK)
                .verifyById(response.getGenre().getGenreId(), genreDto.getGenreId())
        ;
    }

    @Test(description = "Verify [GET] method to get info about GENRES")
    public void getGenresTest() {
        ListOptions listOptions = new ListOptions();

        BaseResponse response = genreService.getGenres(listOptions);

        verification
                .verifyStatusCode(response.getStatusCode(), SC_OK)
                .verifyResponseListIsNotEmpty(response.getGenresArray());
    }

    @Test(description = "Verify [GET] method to info about GENRES by genreName")
    public void getGenresByNameTest() {
        String searchQuery = genreDto.getGenreName();

        BaseResponse response = genreService.getGenresByName(searchQuery);

        List<String> genresNamesList = new ArrayList<>();
        for (GenreDto genre : response.getGenresArray()) {
            genresNamesList.add(genre.getGenreName());
        }

        verification
                .verifyStatusCode(response.getStatusCode(), SC_OK)
                .verifyResponseListIsNotEmpty(response.getGenresArray())
                .verifySearchContainsQuery(searchQuery, genresNamesList);
    }

    @Test(description = "Verify [PUT] method to update GENRE")
    public void updateGenreTest() {
        genreDto.setGenreName("automationLiterature");
        genreDto.setGenreDescription("bla-bla-bla-TEEEEST!");

        BaseResponse response = genreService.updateGenre(genreDto);

        verification
                .verifyStatusCode(response.getStatusCode(), SC_OK);
    }
}
