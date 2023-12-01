package com.atalibdev.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "account",
        description = "Schema to hold Account information"
)
public class AccountDTO {

    @Schema( description = "Account number of the bank account", example = "1234352345")
    @NotEmpty(message = "AccountNumber is required")
    @Pattern(regexp = "(^$|[0-9]{11})", message = "AccountNumber must be 11 digits")
    private Long accountNumber;

    @Schema( description = "Account type of the bank account", example = "SAVING")
    @NotEmpty(message = "Account Type is required")
    private String accountType;

    @Schema( description = "Account owner branch address", example = "123, Paris")
    @NotEmpty(message = "Branch Address is required")
    private String branchAddress;
}
