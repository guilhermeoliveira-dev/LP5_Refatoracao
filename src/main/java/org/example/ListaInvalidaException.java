package org.example;

public class ListaInvalidaException extends RuntimeException {

    private double valor;

    public ListaInvalidaException(String message, double valor) {
        super(message);
        this.valor = valor;
    }

    public double getValor(){
        return valor;
    }
}
