package org.example;

import org.example.exception.JogadaInvalidaException;

import java.util.*;

public class Loteria {

    private final Set<Integer> numerosSorteados;
    private final double premio;

    public Loteria(Set<Integer> sorteados, double premio) {
        this.numerosSorteados = sorteados;
        this.premio = premio;
    }
    public Loteria(double premio) {
        this.numerosSorteados = sortearNumeros(6, 1, 60);
        this.premio = premio;
    }

    public static Set<Integer> validarJogada(Set<Integer> jogada, int limiteInferior, int limiteSuperior){

        Set<Integer> jogadaValidada = new HashSet<>();

        for (Integer n: jogada){
            if (n < limiteInferior || n > limiteSuperior){
                throw new JogadaInvalidaException(n+" está fora do intervalo ("+limiteInferior+" > X > "+limiteSuperior+")");
            }
            jogadaValidada.add(n);
        }

        if (jogadaValidada.size() < 6 || jogadaValidada.size() > 15){
            throw new JogadaInvalidaException("o tamanho de "+jogadaValidada.size()+" é inválido");
        }

        return jogadaValidada;
    }

    public static Set<Integer> sortearNumeros(int tamanho, int valorMinimo, int valorMaximo){

        Set<Integer> sorteio = new HashSet<>();

        while (sorteio.size() < tamanho) {
            int s = new Random().nextInt(valorMaximo - valorMinimo + 1) + valorMinimo;
            sorteio.add(s);
        }
        return sorteio;
    }

    public double jogar(Set<Integer> jogada){

        Set<Integer> jogadaFiltrada = validarJogada(jogada, 1, 60);

        int totalCoincidentes = 0;

        for (Integer numeroJogada: jogadaFiltrada){
            if(numerosSorteados.contains(numeroJogada)){
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

    public double getPremio() {
        return premio;
    }

    public Set<Integer> getNumerosSorteados() {
        return new HashSet<>(numerosSorteados);
    }

}
