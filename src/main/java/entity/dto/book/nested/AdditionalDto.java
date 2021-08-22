package entity.dto.book.nested;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdditionalDto {

    @JsonProperty("pageCount")
    private Integer pageCount = 0;

    @JsonProperty("size")
    private SizeDto size = new SizeDto();

    public AdditionalDto() {
        this.pageCount = 100;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public SizeDto getSize() {
        return size;
    }

    public void setSize(Double height, Double width, Double length) {
        if (height != null) this.size.setHeight(height);
        if (width != null) this.size.setWidth(width);
        if (length != null) this.size.setLength(length);
    }

    public void setHeight(Double height) {
        setSize(height, null, null);
    }

    public void setWidth(Double width) {
        setSize(null, width, null);
    }

    public void setLength(Double length) {
        setSize(null, null, length);
    }
}
