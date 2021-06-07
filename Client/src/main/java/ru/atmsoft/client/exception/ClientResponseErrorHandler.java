package ru.atmsoft.client.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import ru.atmsoft.common.ErrorCode;
import ru.atmsoft.common.ErrorEntity;
import ru.atmsoft.common.exception.CommonException;

import java.io.IOException;

@Slf4j
@Component
public class ClientResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return clientHttpResponse.getStatusCode().isError();
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        log.warn("Сервер вернул сообщение об ошибке со статусом: {}, ", clientHttpResponse.getStatusText());
        ErrorEntity errorEntity = responseToErrorEntity(clientHttpResponse);
        throw new CommonException(errorEntity.getErrorCode().getErrorMessage());

    }

    private ErrorEntity responseToErrorEntity(ClientHttpResponse clientHttpResponse) throws IOException {
        ErrorEntity errorEntity;
        try {
            errorEntity = new ObjectMapper().readValue(clientHttpResponse.getBody(), ErrorEntity.class);
            log.warn("Сервер вернул ошибку: {}, ", errorEntity.getErrorCode().getErrorMessage());
        } catch (Exception e) {
            log.error("При выполнении запроса была получена ошибка", e);
            errorEntity = new ErrorEntity(clientHttpResponse.getStatusCode(), ErrorCode.UnknownError);
        }
        return errorEntity;
    }

}
