package ru.atmsoft.client.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.atmsoft.common.ErrorCode;
import ru.atmsoft.common.exception.CommonException;

import java.net.URI;

@Slf4j
@ControllerAdvice
public class ClientExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Обработка известных нам ошибок с отображение пользователю нашего текста ошибки
     */
    @ExceptionHandler({CommonException.class, CommonException.class})
    protected ResponseEntity<Object> handleNonValidSessionEx(CommonException ex, WebRequest request) {
        log.warn(ex.getMessage());
        return createErrorResponse(ex.getMessage());
    }

    /**
     * Данное сообщение об ошибке будет выдаваться всегда в случае если произошла неизвестная нам ошибка
     */
    @ExceptionHandler({Exception.class, Exception.class})
    protected ResponseEntity<Object> handleOtherException(Exception ex, WebRequest request) {
        log.error("Произошло исключение: ", ex);
        return createErrorResponse(ErrorCode.FatalError.getErrorMessage());
    }

    /**
     * Вернёт респонс содержащий текст ошибки, который будет выдаваться пользователю
     */
    private ResponseEntity<Object> createErrorResponse(String errorMessage){
        log.info("После обработки ошибки, перенаправляем пользователя на страницу с отображением ошибки");
        HttpHeaders httpHeaders = new HttpHeaders();
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/errorInfo/{errorMessage}")
                .buildAndExpand(errorMessage).toUri();
        httpHeaders.setLocation(location);
        ErrorInfo errorInfo = new ErrorInfo(""); // Не могу обработать тело данного респонса в ClientRestController.errorInfo, возможно ли это????
        return new ResponseEntity<>(errorInfo, httpHeaders, HttpStatus.MOVED_PERMANENTLY);
    }

}
