package com.example.Cinema.service;

import com.example.Cinema.dto.MovieDetailsDto;
import com.example.Cinema.dto.MovieDto;
import com.example.Cinema.mapper.MovieDetailsMapper;
import com.example.Cinema.mapper.MovieMapper;
import com.example.Cinema.model.Movie;
import com.example.Cinema.model.MovieDetails;
import com.example.Cinema.repository.MovieDetailsRepository;
import com.example.Cinema.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {
    private static final Logger log = LoggerFactory.getLogger(MovieService.class);
    private final MovieRepository movieRepository;

    private final MovieMapper movieMapper;
    private final MovieDetailsMapper movieDetailsMapper;
    private final MovieDetailsRepository movieDetailsRepository;

    public List<MovieDto> getAllMovies() {
        return movieMapper.movieListEntityToDto(movieRepository.findAll());
    }

    public MovieDto getMovieById(Integer id) {
        return movieMapper.movieEntityToDto(movieRepository.findById(id).orElse(null));
    }

    public MovieDto createMovie(Movie movie) {
        return movieMapper.movieEntityToDto(movieRepository.save(movie));
    }

    public void deleteById(Integer id){
        movieRepository.deleteById(id);
    }

    public MovieDetailsDto getMovieDetailsById(Integer id) {
        return movieDetailsMapper.movieDetailstoDto(movieDetailsRepository.findById(id).get());
    }

    public void updateMovieDetails(MovieDetailsDto movieDetailsDto, Integer movieId) {
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        if (movieOptional.isPresent()) {
            Movie movie = movieOptional.get();
            MovieDetails movieDetails = movie.getMovieDetails();

            movieDetails.setGenre(movieDetailsDto.genre());
            movieDetails.setDuration(movieDetailsDto.duration());
            movieDetails.setTicketPrice(movieDetailsDto.ticketPrice());

            movieDetailsRepository.save(movieDetails);
        } else {
            throw new RuntimeException("Movie not found for ID: " + movieId);
        }
    }
}
