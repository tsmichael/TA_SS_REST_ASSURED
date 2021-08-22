package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.dto.author.AuthorDto;

import java.util.ArrayList;
import java.util.List;

public class JsonReader {
    private static final ObjectMapper mapper = new ObjectMapper();
    private JsonNode nodeObjects;

    public JsonReader() {
    }

    public JsonNode getNode(String jsonBody) {
        try {
            this.nodeObjects = mapper.readTree(jsonBody);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return nodeObjects;
    }

    public String objectToJson(Object obj) {
        String json = "";

        try {
            json = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    //region [AUTHOR]

    public int getAuthorId(String body) {
        return getNode(body).get("authorId").asInt();
    }

    public List<String> getAuthorsListNames(String body) {
        List<String> authors = new ArrayList<>();

        for (JsonNode author : getNode(body)) {
            String firstName = author.get("authorName").get("first").textValue();
            String secondName = author.get("authorName").get("second").textValue();
            String fullName = firstName + " " + secondName;
            authors.add(fullName);
        }
        return authors;
    }


    //endregion [AUTHOR]
    //region [GENRE]

    public int getGenreId(String body) {
        return getNode(body).get("genreId").asInt();
    }

    public List<String> getGenresListNames(String body) {
        List<String> genreNames = new ArrayList<>();

        for (JsonNode genre : getNode(body)) {
            genreNames.add(genre.get("genreName").textValue());
        }
        return genreNames;
    }

    //endregion [GENRE]
}
