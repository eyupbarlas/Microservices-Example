package io.bzzpicks.moviecatalogservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//! Hardcoding inside the project for visualisation purposes

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    private String movieId;
    private Integer rating;
}
