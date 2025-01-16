package com.example.Cinema.controller;

import com.example.Cinema.dto.MovieDetailsDto;
import com.example.Cinema.dto.MovieDto;
import com.example.Cinema.dto.UserDto;
import com.example.Cinema.mapper.MovieDetailsMapper;
import com.example.Cinema.model.Movie;
import com.example.Cinema.model.MovieDetails;
import com.example.Cinema.model.RegistrationRequest;
import com.example.Cinema.repository.MovieRepository;
import com.example.Cinema.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final MovieDetailsMapper movieDetailsMapper;

    @GetMapping("/movies")
    public String getMovies(Model model) {
        List<MovieDto> movieDtos = movieService.getAllMovies();
        model.addAttribute("title", "Movies");
        model.addAttribute("movies", movieDtos);
        return "movies";
    }

    @GetMapping("/movies/create")
    public String renderCreateMovieForm(Model model){
        model.addAttribute("title", "Create Movie");
        model.addAttribute(new Movie());
        return "/movies/create";
    }

    @PostMapping("/movies/create")
    public String createMovie(@ModelAttribute Movie movie){

        MovieDto movieDto = movieService.createMovie(movie);
        return "redirect:/movies";
    }

    @GetMapping("/movies/detail")
    public String renderMovieDetailForm(@RequestParam Integer movieId, Model model){

        Integer detailsId = movieService.getMovieById(movieId).movieDetails().getId();
        Optional<MovieDetailsDto> movieDetailsDto = Optional.ofNullable(movieService.getMovieDetailsById(detailsId));

        if(movieDetailsDto.isPresent()){
            model.addAttribute("title", "Details: ");
            model.addAttribute("movieDetails", movieDetailsDto.get());

            System.out.println(movieDetailsDto.get().ticketPrice());
        }else if(movieDetailsDto.isEmpty()){
            model.addAttribute("title", "Invalid movie Id:" + movieId);
        }

        return "/movies/detail";
    }

    @GetMapping("/movies/delete")
    public String renderDeleteMovieForm(Model model){
        List<MovieDto> movieDtos = movieService.getAllMovies();
        model.addAttribute("title", "Delete Movie");
        model.addAttribute("movies", movieDtos);
        return "/movies/delete";
    }

    @PostMapping("/movies/delete")
    public String deleteMovie(@RequestParam List<Integer> movieIds){
        for (Integer id : movieIds) {
            movieService.deleteById(id);
        }

        return "redirect:/movies";
    }

    @GetMapping("/movies/update1")
    public String renderUpdateMovieForm(Model model) {
        List<MovieDto> movieDtos = movieService.getAllMovies();
        model.addAttribute("title", "Select Movie to Update");
        model.addAttribute("movies", movieDtos);
        return "/movies/update1";
    }

    @PostMapping("/movies/update1")
    public String selectMovieForUpdate(@RequestParam Integer movieId, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("movieId", movieId);
        return "redirect:/movies/update2";
    }

    @GetMapping("/movies/update2")
    public String renderUpdateMovieForm(@RequestParam Integer movieId, Model model) {
        MovieDto movieDto = movieService.getMovieById(movieId);
        if (movieDto != null && movieDto.movieDetails() != null) {
            MovieDetailsDto movieDetailsDto = movieService.getMovieDetailsById(movieDto.movieDetails().getId());
            model.addAttribute("movieDetails", movieDetailsDto);
            model.addAttribute("movieId", movieId);
            model.addAttribute("title", "Update Movie Details");
        } else {
            throw new RuntimeException("Movie or MovieDetails not found for ID: " + movieId);
        }
        return "/movies/update2";
    }

    @PostMapping("/movies/update2")
    public String updateMovieDetails(@ModelAttribute MovieDetailsDto movieDetailsDto, @RequestParam Integer movieId) {
        movieService.updateMovieDetails(movieDetailsDto, movieId);
        return "redirect:/movies";
    }

}
