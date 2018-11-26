package com.moviebooking.MovieBooking.request.v1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RetrieveMovieInfoRequest {


    public String movieTitle;

    public String imdbId;

    public String screenId;
}
