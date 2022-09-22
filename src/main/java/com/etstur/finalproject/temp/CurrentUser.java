package com.etstur.finalproject.temp;



import com.etstur.finalproject.validation.FieldMatch;
import com.etstur.finalproject.validation.ValidEmail;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
        @FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match")
})
public class CurrentUser {
    // temp class to filter data and get it from controller to database using services
    // current user fields and annotate to get the required data

    public CurrentUser() {
    }

    public CurrentUser(@NotNull(message = "is required")
                       @Size(min = 1, message = "is required") String userName,
                       @NotNull(message = "is required")
                       @Size(min = 1, message = "is required") String password,
                       @NotNull(message = "is required")
                       @Size(min = 1, message = "is required") String confirmPassword,
                       @ValidEmail
                       @NotNull(message = "is required")
                       @Size(min = 1, message = "is required") String email) {
        this.userName = userName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
    }

    private String userName;


    private String password;


    private String confirmPassword;


    private String email;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
