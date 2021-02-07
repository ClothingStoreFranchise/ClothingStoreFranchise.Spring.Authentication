package clothingstorefranchise.spring.authentication.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import clothingstorefranchise.spring.common.exceptions.EntityAlreadyExistsException;

//@EnableWebMvc
//@ControllerAdvice
public class ExceptionHelper {
	/*
	@ExceptionHandler(value = { EntityAlreadyExistsException.class })
    public ResponseEntity<Object> handleInvalidInputException(EntityAlreadyExistsException ex) {
        ////return new ResponseEntity<EntityAlreadyExistsException>(ex);
    }*/
}
