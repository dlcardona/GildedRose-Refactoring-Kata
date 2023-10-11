package com.gildedrose.exception;

public class ItemQualityGreaterThanFiftyException extends RuntimeException {
    public ItemQualityGreaterThanFiftyException(String message) {
        super(message);
    }
}
