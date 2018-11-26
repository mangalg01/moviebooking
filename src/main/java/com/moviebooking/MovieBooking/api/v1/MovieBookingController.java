package com.moviebooking.MovieBooking.api.v1;

import com.moviebooking.MovieBooking.request.v1.BookMovieRequest;
import com.moviebooking.MovieBooking.request.v1.RegisterMovieRequest;
import com.moviebooking.MovieBooking.request.v1.RetrieveMovieInfoRequest;
import com.moviebooking.MovieBooking.response.v1.ApiResponse;
import com.moviebooking.MovieBooking.response.v1.RetrieveMovieInfoResponse;
import com.moviebooking.MovieBooking.service.MovieBookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping(value = "/v1/movie")
public class MovieBookingController {
    @Autowired
    MovieBookingService movieBookingService;

    @PostMapping(value = "/register", consumes = {"application/json"}, produces = {"application/json"})
    ResponseEntity<ApiResponse> register(@Valid @RequestBody RegisterMovieRequest requestParams) {
        log.debug("MOVIE_REGISTER | Request Params : {} ", requestParams);
        try{
            movieBookingService.registerMovie(requestParams);
        }
        catch (Exception e) {
            return new ResponseEntity<ApiResponse>(
                    new ApiResponse("FAILURE", "400", "MOVIE_REGISTRATION_FAILED", e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ApiResponse>(
                new ApiResponse("SUCCESS", "200", "MOVIE_REGISTERED_SUCCESSFULLY", "Movie Registered Successfully", null),
                HttpStatus.OK);
    }

    @PostMapping(value = "/book_tickets", consumes = {"application/json"}, produces = {"application/json"})
    ResponseEntity<ApiResponse> bookTickets(@Valid @RequestBody BookMovieRequest requestParams) {
        log.debug("TICKET_BOOK | Request Params : {} ", requestParams);
        try{
            movieBookingService.bookMovie(requestParams);
        }
        catch (Exception e) {
            return new ResponseEntity<ApiResponse>(
                    new ApiResponse("FAILURE", "400", "TICKET_BOOKING_FAILED", e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ApiResponse>(
                new ApiResponse("SUCCESS", "200", "TICKET_BOOKING_SUCCESSFULLY", "Ticket Booked Successfully", null),
                HttpStatus.OK);
    }

    @PostMapping(value = "/retrieve_info", consumes = {"application/json"}, produces = {"application/json"})
    ResponseEntity<?> retrieveMovieInfo(@Valid @RequestBody RetrieveMovieInfoRequest requestParams) {
        log.debug("RETRIEVE_MOVIE_INFO | Request Params : {} ", requestParams);
        try{
            RetrieveMovieInfoResponse response = movieBookingService.retrieveMovieInfo(requestParams);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<ApiResponse>(
                    new ApiResponse("FAILURE", "400", "MOVIE_INFO_RETRIEVAL_FAILED", e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping(value = "/ping")
    ResponseEntity<?> ping() {
        String message = "Application is Up and Running";
        return new ResponseEntity<Object>(message, HttpStatus.OK);
    }
}
