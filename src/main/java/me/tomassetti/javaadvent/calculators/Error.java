package me.tomassetti.javaadvent.calculators;

import lombok.Data;

@Data
public class Error {
    private int httpCode;
    private String message;

    public Error(int httpCode, String message) {
        this.httpCode = httpCode;
        this.message = message;
    }
}
