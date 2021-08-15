package entity.dto.genre;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GenreDto {

    @JsonProperty("genreId")
    private int genreId;

    @JsonProperty("genreName")
    private String genreName;

    @JsonProperty("genreDescription")
    private String genreDescription = "";

    public GenreDto() {
        genreId = 10011;
        genreName = "fanstastica";
        genreDescription = "sladkaya loj`";
    }

    public GenreDto(int i) {
        genreId = 10012;
        genreName = "ica";
        genreDescription = "sla`";
    }

    public GenreDto(String s) {
        genreId = 10011;
        genreName = "fanstastica";
        genreDescription = "sladkaya loj`";
    }


    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getGenreDescription() {
        return genreDescription;
    }

    public void setGenreDescription(String genreDescription) {
        this.genreDescription = genreDescription;
    }
}
