package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Classe {

    public List<Integer> filtrarLista(List<Integer> lista, int limiteInferior, int limiteSuperior){

        List<Integer> listaFiltrada = new ArrayList<>();

        for (Integer n: lista){
            if (n < limiteInferior || n > limiteSuperior){
                throw new ListaInvalidaException("A lista passada é inválida pois "+n+" está fora do limite ("+limiteInferior+" > X > "+limiteSuperior+").", 0.0);
            }
            if (listaFiltrada.contains(n)){
                throw new ListaInvalidaException("A lista passada é inválida pois "+n+" é repitido.", 0.0);
            }
            listaFiltrada.add(n);
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

    public double metodoRefatorado(List<Integer> num, double valor){


        try{
            List<Integer> listaFiltrada = filtrarLista(num, 1, 60);
        }catch(ListaInvalidaException e){
            return e.getValor();
        }

        List<Integer> listaGerada = gerarListaAleatoria(6, 1, 60);

        return 0;
    }

    public double metodo(List<Integer> num, double valor) {
        List<Integer> list = new ArrayList<>();
        for (Integer n: num){
            if (n < 1 || n > 60){
                return 0.0;
            }
            if (list.contains(n)){
                return 0.0;
            }
            list.add(n);
        }
        if (list.size() >= 6 && list.size() <= 15) {
            List<Integer> list2 = new ArrayList<>();
            int s;
            while (list2.size() < 6) {
                s = new Random().nextInt(59) + 1;
                if (!list2.contains(s)) {
                    list2.add(s);
                }
            }
            int tot =0;
            for (Integer i: num){
                if (list2.contains(i)){
                    tot++;
                }
            }
            if (tot == 6){
                return valor;
            } else if (tot == 5){
                return valor * 0.2;
            } else if (tot == 4){
                return valor * 0.05;
            }
        }
        return 0.0;
    }
}
