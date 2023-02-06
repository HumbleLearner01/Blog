package com.blog.helper.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private int id;

    private @NotEmpty String name;
    private @Email String email;

    @NotEmpty
    @Pattern(regexp = "^" + //start of line
                      "(?=.*[0-9])" + //0-9 numbers
                      "(?=.*[a-z])" + //a-z letters
                      "(?=.*[A-Z])" + //A-Z letters
                      "(?=.*[!@#&()–[{}]:;',?/*~$^+=<>])" + // one of the special characters
                      "." + // matches anything
                      "{3,20}" +// minimum and maximum length of password
                      "$" // end of line
            , message = "Pass must be between 3-20 characters & must contain a-z, A-Z, 0-9 and special characters.")
    private String password;
    private String about;
}