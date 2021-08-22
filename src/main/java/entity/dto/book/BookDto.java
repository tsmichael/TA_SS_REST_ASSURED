package entity.dto.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import entity.dto.book.nested.AdditionalDto;

public class BookDto {

    @JsonProperty("bookId")
    private int bookId;

    @JsonProperty("bookName")
    private String bookName;

    @JsonProperty("bookLanguage")
    private String bookLanguage;

    @JsonProperty("bookDescription")
    private String bookDescription;

    @JsonProperty("additional")
    private AdditionalDto additional = new AdditionalDto();

    @JsonProperty("publicationYear")
    private Integer publicationYear;

    public BookDto() {
        this.bookId = 11111;
        this.bookName = "Legendario";
        this.bookLanguage = "Irish";
        this.bookDescription = "bla bla bla adn happy end";
        this.publicationYear = 2001;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookLanguage() {
        return bookLanguage;
    }

    public void setBookLanguage(String bookLanguage) {
        this.bookLanguage = bookLanguage;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public AdditionalDto getAdditional() {
        return additional;
    }

    public void setAdditional(AdditionalDto additional) {
        this.additional = additional;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }
}
