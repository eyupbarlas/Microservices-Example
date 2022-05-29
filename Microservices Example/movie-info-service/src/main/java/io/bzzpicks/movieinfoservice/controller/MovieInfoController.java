package io.bzzpicks.movieinfoservice.controller;

import io.bzzpicks.movieinfoservice.entity.Movie;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieInfoController {
    @GetMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
        return new Movie(
                movieId,
                "test_name"
        );
    }
}
