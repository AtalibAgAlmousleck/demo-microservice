package com.atalibdev.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
@Schema( name = "Response", description = "Schema to hold success response information")
public class ResponseDTO {

    @Schema( description = "Status code in the response", example = "200")
    private String statusCode;

    @Schema( description = "Status Message in the response", example = "Detail not found")
    private String message;
}
