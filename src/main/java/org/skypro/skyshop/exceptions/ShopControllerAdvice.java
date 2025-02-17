package org.skypro.skyshop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShopControllerAdvice {
    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> NoSuchProductExceptionHandler(NoSuchProductException e) {
        ShopError shopError = new ShopError("123", "Такого продукта нет");
        return new ResponseEntity<>(shopError, HttpStatus.NOT_FOUND);
    }
}
