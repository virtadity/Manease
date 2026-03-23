package com.virtadity.manease.infrastructure.web.rest_controller;

import com.virtadity.manease.application.model.error_response.ErrorResponse;
import com.virtadity.manease.application.service.producer.exception.ProducerNotFoundException;
import com.virtadity.manease.application.service.product.exception.ProductNotFoundException;

import com.virtadity.manease.application.service.product_type.exception.ProductTypeNotFoundException;
import com.virtadity.manease.application.service.purchase.exception.PurchaseNotFoundException;
import com.virtadity.manease.application.service.purchase_line.exception.PurchaseLineNotFoundException;
import com.virtadity.manease.domain.service.purchase.validation.exception.ProducerIsDifferentException;
import com.virtadity.manease.domain.service.purchase_line.validation.exception.ProductIsAlreadyInPurchaseException;
import com.virtadity.manease.infrastructure.database.dao.exception.ProducerEntityNotFoundException;
import com.virtadity.manease.infrastructure.database.dao.exception.ProductEntityNotFoundException;
import com.virtadity.manease.infrastructure.database.dao.exception.ProductTypeEntityNotFoundException;
import com.virtadity.manease.infrastructure.database.dao.exception.PurchaseEntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProducerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProducerNotFound(
            ProducerNotFoundException producerNotFoundException,
            HttpServletRequest request
    ) {
        return buildResponse(HttpStatus.NOT_FOUND, producerNotFoundException.getMessage(), request);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(
            ProductNotFoundException productNotFoundException,
            HttpServletRequest request
    ) {
        return buildResponse(HttpStatus.NOT_FOUND, productNotFoundException.getMessage(), request);
    }

    @ExceptionHandler(ProductTypeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductTypeNotFound(
            ProductTypeNotFoundException productTypeNotFoundException,
            HttpServletRequest request
    ) {
        return buildResponse(HttpStatus.NOT_FOUND, productTypeNotFoundException.getMessage(), request);
    }

    @ExceptionHandler(PurchaseNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePurchaseNotFound(
            PurchaseNotFoundException purchaseNotFoundException,
            HttpServletRequest request
    ) {
        return buildResponse(HttpStatus.NOT_FOUND, purchaseNotFoundException.getMessage(), request);
    }

    @ExceptionHandler(PurchaseLineNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePurchaseLineNotFound(
            PurchaseLineNotFoundException purchaseLineNotFoundException,
            HttpServletRequest request
    ) {
        return buildResponse(HttpStatus.NOT_FOUND, purchaseLineNotFoundException.getMessage(), request);
    }

    @ExceptionHandler(ProducerIsDifferentException.class)
    public ResponseEntity<ErrorResponse> handleProducerIsDifferent(
            ProducerIsDifferentException producerIsDifferentException,
            HttpServletRequest request
    ) {
        return buildResponse(HttpStatus.UNPROCESSABLE_CONTENT, producerIsDifferentException.getMessage(), request);
    }

    @ExceptionHandler(ProductIsAlreadyInPurchaseException.class)
    public ResponseEntity<ErrorResponse> handleProductIsAlreadyInPurchase(
            ProductIsAlreadyInPurchaseException productIsAlreadyInPurchaseException,
            HttpServletRequest request
    ) {
        return buildResponse(
                HttpStatus.UNPROCESSABLE_CONTENT,
                productIsAlreadyInPurchaseException.getMessage(),
                request
        );
    }

    @ExceptionHandler(ProducerEntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProducerEntityNotFound(
            ProducerEntityNotFoundException producerEntityNotFoundException,
            HttpServletRequest request
    ) {
        return buildResponse(
                HttpStatus.UNPROCESSABLE_CONTENT,
                producerEntityNotFoundException.getMessage(),
                request
        );
    }

    @ExceptionHandler(ProductEntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductEntityNotFound(
            ProductEntityNotFoundException productEntityNotFoundException,
            HttpServletRequest request
    ) {
        return buildResponse(
                HttpStatus.UNPROCESSABLE_CONTENT,
                productEntityNotFoundException.getMessage(),
                request
        );
    }

    @ExceptionHandler(ProductTypeEntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductTypeEntityNotFound(
            ProductTypeEntityNotFoundException productTypeEntityNotFoundException,
            HttpServletRequest request
    ) {
        return buildResponse(
                HttpStatus.UNPROCESSABLE_CONTENT,
                productTypeEntityNotFoundException.getMessage(),
                request
        );
    }

    @ExceptionHandler(PurchaseEntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePurchaseEntityNotFound(
            PurchaseEntityNotFoundException purchaseEntityNotFoundException,
            HttpServletRequest request
    ) {
        return buildResponse(
                HttpStatus.UNPROCESSABLE_CONTENT,
                purchaseEntityNotFoundException.getMessage(),
                request
        );
    }

    private ResponseEntity<ErrorResponse> buildResponse(HttpStatus status, String message, HttpServletRequest request) {
        var error = new ErrorResponse(
                status.getReasonPhrase(),
                message,
                request.getRequestURI(),
                status.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error, status);
    }
}
