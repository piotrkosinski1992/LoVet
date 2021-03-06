package com.project.config;

import static com.project.commons.errors.ErrorCode.E_AUTH_04;
import static com.project.commons.errors.ErrorCode.E_DEFAULT;

import com.project.commons.errors.LoVetException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestEndpointErrorHandler {

  Logger logger = LoggerFactory.getLogger(RestEndpointErrorHandler.class);

  @ExceptionHandler(value = {LoVetException.class})
  protected ResponseEntity<ErrorMessage> handleException(LoVetException ex) {
    logger.info(ex.getMessage());
    return ResponseEntity.badRequest().body(new ErrorMessage(ex.getErrorCode().name()));
  }

  @ExceptionHandler(value = {RuntimeException.class})
  protected ResponseEntity<ErrorMessage> handleException(RuntimeException ex) {
    logger.info(ex.getMessage());
    if(ex.getClass().getName().contains("AccessDeniedException")) {
      return ResponseEntity.badRequest().body(new ErrorMessage(E_AUTH_04.name()));
    }
    return ResponseEntity.badRequest().body(new ErrorMessage(E_DEFAULT.name()));
  }
}
