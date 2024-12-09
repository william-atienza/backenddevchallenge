package com.limepay.backenddevchallenge.dto;

import com.fasterxml.jackson.annotation.*;

import java.util.List;

public record MovieRecord(int page, int per_page, int total, int total_pages, List<Movie> data) {
}
