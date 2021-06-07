package ru.atmsoft.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.atmsoft.common.ErrorCode;
import ru.atmsoft.common.ErrorEntity;

@Slf4j
@ControllerAdvice
public class ServerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NonValidSessionException.class, NonValidSessionException.class})
    protected ResponseEntity<Object> handleNonValidSessionEx(NonValidSessionException ex, WebRequest request) {
        return createErrorResponse(HttpStatus.FORBIDDEN, ErrorCode.NonValidSession);
    }

    @ExceptionHandler({LockedCardException.class, LockedCardException.class})
    protected ResponseEntity<Object> handleLockedCardEx(LockedCardException ex, WebRequest request) {
        return createErrorResponse(HttpStatus.LOCKED, ErrorCode.CardLocked);
    }

    @ExceptionHandler({NotValidCardException.class, NotValidCardException.class})
    protected ResponseEntity<Object> handleNotValidCardEx(NotValidCardException ex, WebRequest request) {
        return createErrorResponse(HttpStatus.LOCKED, ErrorCode.NotValidCard);
    }

    @ExceptionHandler({CardIsNotReadableException.class, CardIsNotReadableException.class})
    protected ResponseEntity<Object> handleCardIsNotReadableEx(CardIsNotReadableException ex, WebRequest request) {
        return createErrorResponse(HttpStatus.NOT_ACCEPTABLE, ErrorCode.CardIsNotReadable);
    }

    @ExceptionHandler({NotFoundException.class, NotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundEx(NotFoundException ex, WebRequest request) {
        return createErrorResponse(HttpStatus.NOT_FOUND, ErrorCode.CardIsNotReadable);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return createErrorResponse(HttpStatus.BAD_REQUEST, ErrorCode.BadRequest);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("Произошла ошиюбка", ex);
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private ResponseEntity<Object> createErrorResponse(HttpStatus httpStatus, ErrorCode errorCode) {
        ErrorEntity errorEntity = new ErrorEntity(httpStatus, errorCode);
        log.error("Произошла ошиюбка статус возврата '{}' сообщение: {}", httpStatus.toString(), errorCode.getErrorMessage());
        return new ResponseEntity<>(errorEntity, new HttpHeaders(), errorEntity.getHttpStatus());
    }

}
