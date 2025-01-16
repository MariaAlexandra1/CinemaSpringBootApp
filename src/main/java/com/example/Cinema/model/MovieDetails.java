package com.example.Cinema.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "MOVIES_DETAILS")
public class MovieDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //auto-increment
    private Integer id;

    @NonNull
    private String genre;

    @NonNull
    private Double duration;

    @NonNull
    private Double ticketPrice;

}
