package com.example.user.registration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class GeoLocationResponse {

    @NotNull
    @NotEmpty
    private String city;

    @NotNull
    @NotEmpty
    private String countryCode;

    @NotNull
    @NotEmpty
    private String country;

    public boolean isIpAddressWithinCountry(String countryCode) {
        return this.countryCode.equalsIgnoreCase(countryCode);
    }

    @Override
    public String toString() {
        return city + ", " + country + ". ";
    }
}
