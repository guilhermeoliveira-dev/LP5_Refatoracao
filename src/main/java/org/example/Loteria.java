package org.example;

import org.example.exception.JogadaInvalidaException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Loteria {

    private final List<Integer> sorteados;
    private final double premio;

    public Loteria(List<Integer> sorteados, double premio) {
        this.sorteados = sorteados;
        this.premio = premio;
    }
    public Loteria(double premio) {
        this.sorteados = sortearNumeros(6, 1, 60);
        this.premio = 0;
    }

    public List<Integer> validarJogada(List<Integer> jogada, int limiteInferior, int limiteSuperior){

        List<Integer> listaFiltrada = new ArrayList<>();

        for (Integer n: jogada){
            if (n < limiteInferior || n > limiteSuperior){
                throw new JogadaInvalidaException(n+" está fora do limite ("+limiteInferior+" > X > "+limiteSuperior+")", 0.0);
            }
            if (listaFiltrada.contains(n)){
                throw new JogadaInvalidaException(n+" é repetido", 0.0);
            }
            listaFiltrada.add(n);
        }

        if (listaFiltrada.size() < 6 || listaFiltrada.size() > 15){
            throw new JogadaInvalidaException("o tamanho de "+listaFiltrada.size()+" é inválido", 0.0);
        }

        return listaFiltrada;
    }

    public List<Integer> sortearNumeros(int tamanho, int valorMinimo, int valorMaximo){

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

    public double jogar(List<Integer> jogada){

        List<Integer> jogadaFiltrada = jogadaFiltrada = validarJogada(jogada, 1, 60);

        int totalCoincidentes = 0;

        for (Integer numeroJogada: jogadaFiltrada){
            if(sorteados.contains(numeroJogada)){
                totalCoincidentes++;
            }
        }

        double multiplicador = 0;

        if (totalCoincidentes == 6){
            multiplicador = 1.0;
        } else if (totalCoincidentes == 5){
            multiplicador = 0.2;
        } else if (totalCoincidentes == 4){
            multiplicador = 0.05;
        }

        return premio * multiplicador;

    }

}
