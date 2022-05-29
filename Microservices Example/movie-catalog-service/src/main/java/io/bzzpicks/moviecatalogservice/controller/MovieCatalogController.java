package io.bzzpicks.moviecatalogservice.controller;

import io.bzzpicks.moviecatalogservice.entity.CatalogItem;
import io.bzzpicks.moviecatalogservice.entity.Movie;
import io.bzzpicks.moviecatalogservice.entity.Rating;
import io.bzzpicks.moviecatalogservice.entity.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {
    @Autowired //? Autowired => somewhere there's a Bean, inject it here
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        //* get all rated movie id's
        //* for each movie id, call movie info service and get details
        //* put them all together

//        List<Rating> ratings = Arrays.asList( //? Hardcoding inside controller
//                new Rating("1111", 4),
//                new Rating("2222", 2)
//        );

        UserRating ratings = restTemplate.getForObject("http://localhost:8083/ratings-data/users/" + userId, UserRating.class);

        assert ratings != null;
        return ratings.getUserRating().stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class); //! This line is making a call to the other API
//!         Async way =>
//            Movie movie = webClientBuilder.build()
//                    .get()
//                    .uri("http://localhost:8082/movies/"+ rating.getMovieId())
//                    .retrieve()
//                    .bodyToMono(Movie.class) //?-> promise to get the object
//                    .block();

            assert movie != null;
            return new CatalogItem(
                    movie.getName(), "About a good kid driver in crime world.", rating.getRating());
            }).collect(Collectors.toList());
    }
}
