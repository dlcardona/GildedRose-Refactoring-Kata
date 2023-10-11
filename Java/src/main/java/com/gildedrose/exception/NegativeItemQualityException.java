package com.gildedrose.exception;

public class NegativeItemQualityException extends RuntimeException {
    public NegativeItemQualityException(String message) {
        super(message);
    }
}
