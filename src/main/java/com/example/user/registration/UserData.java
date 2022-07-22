package com.example.user.registration;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

//@PasswordValueMatch.List({
//    @PasswordValueMatch(
//        field = "password",
//        fieldMatch = "confirmPassword",
//        message = "Passwords do not match!"
//    )
//})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserData {
    @NonNull
    @NotBlank(message = "username is required")
    private String username;

    @IpAddress
    @NotNull
    @NotEmpty
    private String ipAddress;

    @ValidPassword
    @NonNull
    @NotBlank(message = "password is required")
    private String password;

}