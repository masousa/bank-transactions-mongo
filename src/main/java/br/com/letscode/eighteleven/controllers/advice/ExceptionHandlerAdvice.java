package br.com.letscode.eighteleven.controllers.advice;

import br.com.letscode.eighteleven.exceptions.ClientException;
import br.com.letscode.eighteleven.payloads.GenericEntityResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = ClientException.class)
    public ResponseEntity<GenericEntityResponse> handleClientException(ClientException clientException) {

        final HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(GenericEntityResponse.builder()
                .httpStatusCode(status.name())
                .message(clientException.getMessage()).build()
                , status);
    }
}
