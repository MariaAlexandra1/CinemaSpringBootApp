package com.example.Cinema.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "MOVIES")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //auto-increment
    private Integer id;

    @NonNull
    @Column(unique = true)
    private String title;

    @OneToOne(cascade = CascadeType.ALL)
    @NonNull
    private MovieDetails movieDetails;

}
