package ru.atmsoft.common;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorEntity {

    private HttpStatus httpStatus;
    private ErrorCode errorCode;

}
