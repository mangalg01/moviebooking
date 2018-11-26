package com.moviebooking.MovieBooking.request.v1;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterMovieRequest {

    @NotNull
    @Length(min = 1)
    private String imdbId;

    @NotNull
    private Integer availableSeats;

    @NotNull
    @Length(min = 1)
    private String screenId;

    @Length(min = 1)
    @NotNull
    private String movieTitle;
}
