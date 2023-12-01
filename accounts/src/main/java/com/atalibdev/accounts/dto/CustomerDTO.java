package com.atalibdev.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDTO {

    @Schema( description = "Name of the customer", example = "Developer" )
    @NotEmpty(message = "Name is required")
    @Size(min = 4, max = 30, message = "The length of name should be between 4 and 30")
    private String name;

    @Schema( description = "Email of the customer", example = "developer@gmail.com" )
    @NotEmpty(message = "Email is required")
    @Email(message = "Please enter a valid email address")
    private String email;

    @Schema( description = "Mobile of the customer", example = "12345654323" )
    @Pattern(regexp = "(^$|[0-9]{11})", message = "Mobile number must be 11 digits")
    private String phoneNumber;

    @Schema( description = "Account details of the customer" )
    private AccountDTO accountDTO;
}
