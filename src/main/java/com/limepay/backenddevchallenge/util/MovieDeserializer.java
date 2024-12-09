package com.limepay.backenddevchallenge.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.limepay.backenddevchallenge.dto.Movie;

import java.io.IOException;

public class MovieDeserializer extends StdDeserializer<Movie> {
    public MovieDeserializer(){
        this(null);
    }

    public MovieDeserializer(Class<?> c) {
        super(c);
    }

    @Override
    public Movie deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String title = node.get("Title").asText();
        String year = node.get("Year").asText();
        String rated = node.get("Rated").asText();
        String released = node.get("Released").asText();
        String runtime = node.get("Runtime").asText();
        String genre = node.get("Genre").asText();
        String director = node.get("Director").asText();
        String writer = node.get("Writer").asText();
        return new Movie(title, year, rated, released,runtime, genre, director, writer, null);
    }
}