package com.example.Cinema.dto;

import com.example.Cinema.model.MovieDetails;
import lombok.Builder;

@Builder
public record MovieDto (String title,
        MovieDetails movieDetails) {}

