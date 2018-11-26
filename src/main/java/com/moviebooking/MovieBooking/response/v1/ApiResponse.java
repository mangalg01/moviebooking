package com.moviebooking.MovieBooking.response.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

	@JsonProperty("api_status")
	private String apiStatus;

	@JsonProperty("status_code")
	private String statusCode;

	@JsonProperty("key")
	private String key;

	@JsonProperty("message")
	private String message;
	
	@JsonProperty("data")
	private HashMap data; 

}
