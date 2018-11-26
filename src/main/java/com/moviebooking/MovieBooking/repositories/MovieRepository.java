package com.moviebooking.MovieBooking.repositories;

import com.moviebooking.MovieBooking.domain.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Integer> {

    @Query(value = "select * from movie where imdb_id = ?1 and screen_id = ?2",nativeQuery = true)
    public Movie retrieveMovie(String imdbId, String screenId);
}
