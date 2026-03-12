package org.example.exception;

public class ListaInvalidaException extends RuntimeException {


    private double valor;

    public ListaInvalidaException(String message, double valor) {
        super("A lista passada é inválida: \""+message+"\".");
        this.valor = valor;
    }

    public double getValor(){
        return valor;
    }
}
