package com.example.user.registration;

import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.UUID;

@RestController
public class UserResource {

    @PostMapping(value = "/registration")
    public ResponseEntity<ApiResponse> registration(@Valid @RequestBody UserData userData) {

        GeoLocationResponse locationResponse = getGeoLocation(userData.getIpAddress());

        if (!locationResponse.isIpAddressWithinCountry("CA")) {
            String errorMessage = userData.getUsername() + " is not eligible to register.";
            return new ResponseEntity<>(
                    new ApiResponse(errorMessage, "success", false), HttpStatus.NOT_FOUND);
        }

        String welcomeMessage = "Hello " + userData.getUsername() + " - " + UUID.randomUUID() + " from " + locationResponse + "Welcome you are registered user now.";
        return new ResponseEntity<>(
                new ApiResponse(welcomeMessage, "success", false), HttpStatus.OK);

    }



    @Retry(name = "geoLocation")
    private GeoLocationResponse getGeoLocation(String ipAddress) {
        RestTemplate restTemplate = new RestTemplate();

        String resourceUrl = "http://ip-api.com/json/" + ipAddress + "?fields=city,country,countryCode";

        // Fetch response as List wrapped in ResponseEntity
        ResponseEntity<GeoLocationResponse> response
                = restTemplate.getForEntity(resourceUrl, GeoLocationResponse.class);

        GeoLocationResponse locationResponse = response.getBody();

        assert locationResponse != null;
        return locationResponse;
    }
}

