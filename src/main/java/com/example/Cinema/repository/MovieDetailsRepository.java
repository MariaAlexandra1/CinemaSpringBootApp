package com.example.Cinema.repository;

import com.example.Cinema.model.MovieDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface MovieDetailsRepository extends JpaRepository<MovieDetails, Integer> {
}
