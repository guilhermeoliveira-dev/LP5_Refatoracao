package org.example.exception;

public class JogadaInvalidaException extends RuntimeException {


    private double valor;

    public JogadaInvalidaException(String message, double valor) {
        super("A jogada passada é inválida: \""+message+"\".");
        this.valor = valor;
    }

    public double getValor(){
        return valor;
    }
}
