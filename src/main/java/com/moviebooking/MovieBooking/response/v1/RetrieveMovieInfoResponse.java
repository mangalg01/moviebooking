package com.moviebooking.MovieBooking.response.v1;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RetrieveMovieInfoResponse {

    private String imdbId;

    private String screenId;

    private String movieTitle;

    private Integer availableSeats;

    private Integer reservedSeats;
}
