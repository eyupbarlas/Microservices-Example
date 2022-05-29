package io.bzzpicks.ratingsdataservice.controller;

import io.bzzpicks.ratingsdataservice.entity.Rating;
import io.bzzpicks.ratingsdataservice.entity.UserRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratings-data")
public class RatingsDataController {
    @GetMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId) {
        return new Rating(
                movieId,
                4
        );
    }

    @GetMapping("users/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String UserId) {
        List<Rating> ratings =  Arrays.asList(
                new Rating("3333", 3),
                new Rating("4444", 4)
        );
        UserRating userRating = new UserRating();
        userRating.setUserRating(ratings);
        return userRating;
    }
}
