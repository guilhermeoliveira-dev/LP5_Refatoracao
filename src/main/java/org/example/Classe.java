package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Classe {

    // A análise do metodo revela uma funcionalidade de loteria, onde a função recebe uma lista de valores que
    // representam a aposta, e verifica se ela bate com os números, que são gerados aleatoriamente.

    // Problemas incluem a falta de modularização, com todas as funcionalidades sendo implementadas em um único metodo,
    // bem como nomes e variáveis que não facilitam a compreensão.

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
