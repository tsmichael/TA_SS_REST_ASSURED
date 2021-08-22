package entity.dto.author;

import com.fasterxml.jackson.annotation.JsonProperty;
import entity.dto.author.nested.BirthDto;
import entity.dto.author.nested.NameDto;

public class AuthorDto {

    @JsonProperty("authorId")
    private int authorId;

    @JsonProperty("authorName")
    private NameDto authorName = new NameDto();

    @JsonProperty("nationality")
    private String nationality;

    @JsonProperty("birth")
    private BirthDto birth = new BirthDto();

    @JsonProperty("authorDescription")
    private String authorDescription;

    public AuthorDto() {
        this.authorId = 10001;
        this.nationality = "Konohovec";
        this.authorDescription = "Strong and Fateful";
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public NameDto getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String first, String second) {
        if (first != null) this.authorName.setFirst(first);
        if (second != null) this.authorName.setSecond(second);
    }

    public void setAuthorFirstName(String firstName) {
        setAuthorName(firstName, null);
    }

    public void setAuthorSecondName(String secondName) {
        setAuthorName(null, secondName);
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public BirthDto getBirth() {
        return birth;
    }

    public void setBirth(String date, String country, String city) {
        if (date != null) this.birth.setDate(date);
        if (country != null) this.birth.setCountry(country);
        if (city != null) this.birth.setCity(city);
    }

    public void setDate(String date) {
        setBirth(date, null, null);
    }

    public void setCountry(String country) {
        setBirth(null, country, null);
    }

    public void setCity(String city) {
        setBirth(null, null, city);
    }

    public String getAuthorDescription() {
        return authorDescription;
    }

    public void setAuthorDescription(String authorDescription) {
        this.authorDescription = authorDescription;
    }
}
