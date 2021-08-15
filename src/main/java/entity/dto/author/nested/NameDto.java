package entity.dto.author.nested;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NameDto {

    @JsonProperty("first")
    private String first;

    @JsonProperty("second")
    private String second;

    public NameDto() {
        this.first = "JIJ";
        this.second = "Roso";
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }
}
