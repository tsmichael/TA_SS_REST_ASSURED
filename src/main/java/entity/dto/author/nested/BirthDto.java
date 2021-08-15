package entity.dto.author.nested;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BirthDto {

    @JsonProperty("date")
    private String date;

    @JsonProperty("country")
    private String country;

    @JsonProperty("city")
    private String city;

    public BirthDto() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        this.date = currentDateTime.format(formatter);
        this.country = "firecountry";
        this.city = "KONOKHA";
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
