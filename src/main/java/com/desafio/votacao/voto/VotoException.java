package com.desafio.votacao.voto;

public class VotoException extends RuntimeException{

    public VotoException(String message) {
        super(message);
    }

    public VotoException(String message, Throwable cause) {
        super(message, cause);
    }

    public VotoException(Throwable cause) {
        super(cause);
    }
    public VotoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
