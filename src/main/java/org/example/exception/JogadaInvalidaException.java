package org.example.exception;

public class JogadaInvalidaException extends RuntimeException {




    public JogadaInvalidaException(String message) {
        super("A jogada passada é inválida: \""+message+"\".");

    }


}
