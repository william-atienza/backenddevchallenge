package com.limepay.backenddevchallenge.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.limepay.backenddevchallenge.util.MovieDeserializer;

import java.util.List;

@JsonDeserialize(using = MovieDeserializer.class)
public record Movie(String title, String year, String rated, String released,
                    String runtime, String genre, String director, String writer, List<String> actors) {
}
