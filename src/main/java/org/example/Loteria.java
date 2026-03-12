package org.example;

import org.example.exception.ListaInvalidaException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Loteria {

    private List<Integer> sorteados;

    public Loteria(List<Integer> sorteados) {
        this.sorteados = sorteados;
    }
    public Loteria() {
        this.sorteados = gerarListaAleatoria(6, 1, 60);
    }

    public List<Integer> validarLista(List<Integer> lista, int limiteInferior, int limiteSuperior){

        List<Integer> listaFiltrada = new ArrayList<>();

        for (Integer n: lista){
            if (n < limiteInferior || n > limiteSuperior){
                throw new ListaInvalidaException(n+" está fora do limite ("+limiteInferior+" > X > "+limiteSuperior+")", 0.0);
            }
            if (listaFiltrada.contains(n)){
                throw new ListaInvalidaException(n+" é repetido", 0.0);
            }
            listaFiltrada.add(n);
        }

        if (listaFiltrada.size() < 6 || listaFiltrada.size() > 15){
            throw new ListaInvalidaException("o tamanho de "+listaFiltrada.size()+" ", 0.0);
        }

        return listaFiltrada;
    }

    public List<Integer> gerarListaAleatoria(int tamanho, int valorMinimo, int valorMaximo){

        List<Integer> lista = new ArrayList<>();
        int s;
        while (lista.size() < tamanho) {
            s = new Random().nextInt(valorMaximo - valorMinimo) + valorMinimo;
            if (!lista.contains(s)) {
                lista.add(s);
            }
        }
        return lista;
    }

    public double sortear(List<Integer> jogada, double premio){
        List<Integer> listaFiltrada;
        try{
            listaFiltrada = validarLista(jogada, 1, 60);
        }catch(ListaInvalidaException e){
            return e.getValor();
        }




        return 0;
    }

}
