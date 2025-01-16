package com.example.Cinema.mapper;

import com.example.Cinema.dto.MovieDetailsDto;
import com.example.Cinema.model.MovieDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieDetailsMapper {

    public MovieDetailsDto movieDetailstoDto(MovieDetails movieDetails) {
        return MovieDetailsDto.builder()
                .genre(movieDetails.getGenre())
                .duration(movieDetails.getDuration())
                .ticketPrice(movieDetails.getTicketPrice())
                .build();
    }

    public List<MovieDetailsDto> movieDetailsListtoDto(List<MovieDetails> movieDetails) {
        return movieDetails.stream()
                .map(details -> movieDetailstoDto(details))
                .toList();
    }

    public MovieDetails movieDetailsDtotoMovieDetails(MovieDetailsDto movieDetailsDto) {
        return MovieDetails.builder()
                .genre(movieDetailsDto.genre())
                .duration(movieDetailsDto.duration())
                .ticketPrice(movieDetailsDto.ticketPrice())
                .build();
    }

    public List<MovieDetails> movieDetailsDtoListtoMovieDetails(List<MovieDetailsDto> movieDetailsDtoList) {
        return movieDetailsDtoList.stream()
                .map(detailsDto -> movieDetailsDtotoMovieDetails(detailsDto))
                .toList();
    }
}
