package com.example.Cinema.mapper;

import com.example.Cinema.dto.MovieDto;
import com.example.Cinema.dto.UserDto;
import com.example.Cinema.model.Movie;
import com.example.Cinema.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class MovieMapper {

    private final MovieDetailsMapper movieDetailsMapper;

    public MovieDto movieEntityToDto(Movie movie) {
        return MovieDto.builder()
                .title(movie.getTitle())
                .movieDetails(movie.getMovieDetails())
                .build();
    }

    public List<MovieDto> movieListEntityToDto(List<Movie> movies){
        return movies.stream()
                .map(movie -> movieEntityToDto(movie))
                .toList();
    }

    public Movie movieDtoToMovie(MovieDto movieDto) {
        return  Movie.builder()
                .title(movieDto.title())
                .movieDetails(movieDto.movieDetails())
                .build();
    }

    public List<Movie> movieListDtoToMovie(List<MovieDto> movieDtos) {
        return movieDtos.stream()
                .map(movieDto -> movieDtoToMovie(movieDto))
                .toList();
    }


}
