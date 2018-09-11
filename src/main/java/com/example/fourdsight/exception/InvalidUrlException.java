package com.example.fourdsight.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidUrlException extends GlobalResponseException {
  private static final long serialVersionUID = 1L;

  public InvalidUrlException(String message) {
    super(HttpStatus.BAD_REQUEST.value(), message);
  }
}
