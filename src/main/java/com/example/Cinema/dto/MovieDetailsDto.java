package com.example.Cinema.dto;

import lombok.Builder;

@Builder
public record MovieDetailsDto (String genre, Double duration, Double ticketPrice) {}
