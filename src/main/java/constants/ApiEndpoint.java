package constants;

public enum ApiEndpoint {

    AUTHOR("author"),
    SEARCH("search"),
    AUTHORS("authors"),
    GENRE("genre"),
    GENRES("genres"),
    BOOK("book"),
    BOOKS("books");

    String endpoint;

    ApiEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public String toString() {
        return endpoint;
    }
}
