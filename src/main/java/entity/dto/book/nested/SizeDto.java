package entity.dto.book.nested;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SizeDto {

    @JsonProperty("height")
    private Double height = 0.0;

    @JsonProperty("width")
    private Double width = 0.0;

    @JsonProperty("length")
    private Double length = 0.0;

    public SizeDto() {
        this.height = 10d;
        this.width = 7d;
        this.length = 4d;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }
}
