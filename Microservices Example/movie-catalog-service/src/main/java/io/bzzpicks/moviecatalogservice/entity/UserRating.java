package io.bzzpicks.moviecatalogservice.entity;

import lombok.Data;

import java.util.List;

@Data
public class UserRating {
    private List<Rating> userRating;
}
