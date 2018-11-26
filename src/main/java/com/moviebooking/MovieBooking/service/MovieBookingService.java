package com.moviebooking.MovieBooking.service;

import com.moviebooking.MovieBooking.domain.Movie;
import com.moviebooking.MovieBooking.repositories.MovieRepository;
import com.moviebooking.MovieBooking.request.v1.BookMovieRequest;
import com.moviebooking.MovieBooking.request.v1.RegisterMovieRequest;
import com.moviebooking.MovieBooking.request.v1.RetrieveMovieInfoRequest;
import com.moviebooking.MovieBooking.response.v1.RetrieveMovieInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MovieBookingService {

    @Autowired
    MovieRepository movieRepository;

    public void registerMovie(RegisterMovieRequest requestParams) throws IllegalAccessException{
        Movie existingMovie = movieRepository.retrieveMovie(requestParams.getImdbId(), requestParams.getScreenId());
        if(existingMovie != null) {
            throw new IllegalAccessException("Movie is already registered with given configuration");
        }

        Movie movie = new Movie();
        movie.setAvailableSeats(requestParams.getAvailableSeats());
        movie.setCapacity(requestParams.getAvailableSeats());
        movie.setImdbId(requestParams.getImdbId());
        movie.setScreenId(requestParams.getScreenId());
        movie.setMovieTitle(requestParams.getMovieTitle());
        //movie.set;
        movieRepository.save(movie);
        log.debug("REGISTER_MOVIE | Movie : {} saved in database successfully", movie) ;
    }

    public void bookMovie(BookMovieRequest request) throws IllegalAccessException{
        Movie movie = movieRepository.retrieveMovie(request.getImdbId(), request.getScreenId());
        if(movie == null) {
            throw new IllegalAccessException("Movie does not exist with given parameters");
        }
        Integer numOfSeats = movie.getAvailableSeats();
        if(numOfSeats < 1){
            throw new IllegalAccessException("No Seat available");
        }
        movie.setAvailableSeats(numOfSeats-1);
        movieRepository.save(movie);
    }

    public RetrieveMovieInfoResponse retrieveMovieInfo(RetrieveMovieInfoRequest request) throws IllegalAccessException{
        Movie movie = movieRepository.retrieveMovie(request.getImdbId(), request.getScreenId());
        if(movie == null) {
            throw new IllegalAccessException("Movie does not exist with given parameters");
        }
        RetrieveMovieInfoResponse response = new RetrieveMovieInfoResponse();
        response.setImdbId(movie.getImdbId());
        response.setScreenId(movie.getScreenId());
        response.setMovieTitle(movie.getMovieTitle());
        Integer totalSeats = movie.getCapacity();
        Integer availableSeats = movie.getAvailableSeats();
        response.setAvailableSeats(availableSeats);
        response.setReservedSeats(totalSeats-availableSeats);
        log.debug("RETRIEVE_MOVIE_INFO | Response : {}", response);
        return response;
    }
}
