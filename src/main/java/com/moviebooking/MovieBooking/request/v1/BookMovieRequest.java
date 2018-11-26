package com.moviebooking.MovieBooking.request.v1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookMovieRequest {

    @NotNull
    @Length(min = 1)
    public String imdbId;

    @NotNull
    @Length(min = 1)
    public String screenId;

}
