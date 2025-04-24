package org.com.spectorassignment.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.com.spectorassignment.domain.response.ErrorResponse;
import org.com.spectorassignment.exception.CustomException;
import org.com.spectorassignment.exception.ErrorCode;
import org.com.spectorassignment.exception.enumtype.ServiceExceptionCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvisorController {
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e) {
        ServiceExceptionCode errorCode = ServiceExceptionCode.ACCESS_DENIED;

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .code(errorCode.getCode())
                .message(e.getFormattedMessage())
                .build();

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception e) {
        ServiceExceptionCode errorCode = ServiceExceptionCode.INTERNAL_SERVER_ERROR;

        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(errorCode.getCode())
                .message(e.getMessage())
                .build();

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationError(MethodArgumentNotValidException e) {
        ServiceExceptionCode errorCode = ServiceExceptionCode.BAD_REQUEST_BODY;

        FieldError fieldError = e.getBindingResult().getFieldError();
        String errMessage = (fieldError != null) ? fieldError.getDefaultMessage() : errorCode.getMessage();

        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .code(errorCode.getCode())
                .message(errMessage)
                .build();

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleEnumBindingException(HttpMessageNotReadableException e) {
        ServiceExceptionCode errorCode = ServiceExceptionCode.INVALID_ROLE_TYPE;
        String errMessage = errorCode.getMessage();

        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .code(errorCode.getCode())
                .message(errMessage)
                .build();

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(errorResponse);
    }
}
