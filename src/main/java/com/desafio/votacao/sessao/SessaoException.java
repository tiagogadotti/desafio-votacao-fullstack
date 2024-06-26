package com.desafio.votacao.sessao;

public class SessaoException extends RuntimeException{

    public SessaoException(String message) {
        super(message);
    }

    public SessaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
