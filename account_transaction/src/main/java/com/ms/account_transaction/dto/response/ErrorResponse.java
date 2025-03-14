package com.ms.account_transaction.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

/**
 * @author : Freddy Torres
 * file :  ErrorResponse
 * @since : 11/3/2025, mar
 **/

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ErrorResponse {
    private Boolean status;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;
    private String path;
}
