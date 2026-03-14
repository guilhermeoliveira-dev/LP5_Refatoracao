package org.example;

import org.example.exception.JogadaInvalidaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

class LoteriaTest {

    private static final double premioPadrao = 1000.0;
    private static final Set<Integer> jogadaPadrao = Set.of(1,2,3,4,5,6);

    @Test
    void validarJogada_caminhoFeliz(){
        try{
            Set<Integer> jogadaValidada = Loteria.validarJogada(jogadaPadrao, 1, 60);
            Assertions.assertEquals(6, jogadaValidada.size(), "Tamanho deveria ser 6.");
            for(Integer i: jogadaValidada){
                Assertions.assertFalse(i < 1, "Número menor que 1");
                Assertions.assertFalse(i > 60, "Número maior que 60");
            }
        }catch(JogadaInvalidaException e){
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void validarJogada_deveCausarErroTamanhoAbaixoMinimo(){
        Set<Integer> jogada = Set.of(1,2,3,4,5);
        try{
            Loteria.validarJogada(jogada, 1, 60);
            Assertions.fail("Deveria ter causado excecao");
        }catch(JogadaInvalidaException e){
            Assertions.assertEquals("A jogada passada é inválida: \"o tamanho de 5 é inválido\".", e.getMessage(), "Mensagem de erro incorreta");
        }
    }

    @Test
    void validarJogada_deveCausarErroTamanhoAcimaMaximo(){
        Set<Integer> jogada = Set.of(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16);
        try{
            Loteria.validarJogada(jogada, 1, 60);
            Assertions.fail("Deveria ter causado excecao");
        }catch(JogadaInvalidaException e){
            Assertions.assertEquals("A jogada passada é inválida: \"o tamanho de 16 é inválido\".", e.getMessage(), "Mensagem de erro incorreta");
        }
    }

    @Test
    void validarJogada_deveCausarErroValorAbaixoMinimo(){
        Set<Integer> jogada = Set.of(0,1,2,3,4,5,6);
        try{
            Loteria.validarJogada(jogada, 1, 60);
            Assertions.fail("Deveria ter causado excecao");
        }catch(JogadaInvalidaException e){
            Assertions.assertEquals("A jogada passada é inválida: \"0 está fora do intervalo (1 > X > 60)\".", e.getMessage(), "Mensagem de erro incorreta");
        }
    }

    @Test
    void validarJogada_deveCausarErroValorAcimaMaximo(){
        Set<Integer> jogada = Set.of(61,1,2,3,4,5,6);
        try{
            Loteria.validarJogada(jogada, 1, 60);
            Assertions.fail("Deveria ter causado excecao");
        }catch(JogadaInvalidaException e){
            Assertions.assertEquals("A jogada passada é inválida: \"61 está fora do intervalo (1 > X > 60)\".", e.getMessage(), "Mensagem de erro incorreta");
        }
    }

    @Test
    void sortearNumeros_DeveRetornarNumerosNoIntervalo() {
        Set<Integer> sorteados = Loteria.sortearNumeros(60, 1, 60);
        for (Integer numero: sorteados){
            Assertions.assertFalse(numero < 1, "Número menor que 1");
            Assertions.assertFalse(numero > 60, "Número maior que 60");
        }
    }

    @Test
    void sortearNumeros_RetornoDeveTerTamanhoCorreto(){
        Set<Integer> sorteados = Loteria.sortearNumeros(6, 1, 60);
        Assertions.assertEquals(6, sorteados.size(), "Tamanho inválido");
    }

    @Test
    void jogar_DeveRetornarAcertoSena(){
        Loteria loteria = new Loteria(jogadaPadrao, premioPadrao);
        Set<Integer> jogada = Set.of(1,2,3,4,5,6);
        try{
            double resultado = loteria.jogar(jogada);
            Assertions.assertEquals(1000.0, resultado, "Valor de prêmio incorreto");
        }catch(JogadaInvalidaException e){
            Assertions.fail("Não deveria dar exceção");
        }
    }

    @Test
    void jogar_DeveRetornarAcertoQuina(){
        Loteria loteria = new Loteria(jogadaPadrao, premioPadrao);
        Set<Integer> jogada = Set.of(1,2,3,4,5,10);
        try{
            double resultado = loteria.jogar(jogada);
            Assertions.assertEquals(200.0, resultado, "Valor de prêmio incorreto");
        }catch(JogadaInvalidaException e){
            Assertions.fail("Não deveria dar exceção");
        }
    }

    @Test
    void jogar_DeveRetornarAcertoQuadra(){
        Loteria loteria = new Loteria(jogadaPadrao, premioPadrao);
        Set<Integer> jogada = Set.of(1,2,3,4,10,11);
        try{
            double resultado = loteria.jogar(jogada);
            Assertions.assertEquals(50.0, resultado, "Valor de prêmio incorreto");
        }catch(JogadaInvalidaException e){
            Assertions.fail("Não deveria dar exceção");
        }
    }

    @Test
    void jogar_DeveRetornarZero(){
        Loteria loteria = new Loteria(jogadaPadrao, premioPadrao);
        Set<Integer> jogada = Set.of(10,11,12,13,14,15);
        try{
            double resultado = loteria.jogar(jogada);
            Assertions.assertEquals(0.0, resultado, "Valor de prêmio incorreto");
        }catch(JogadaInvalidaException e){
            Assertions.fail("Não deveria dar exceção");
        }
    }

    @Test
    void loteria_ConstrutorDeveSerValido(){
        Loteria loteria = new Loteria(premioPadrao);
        Assertions.assertEquals(1000.0, loteria.getPremio(), "Valor de prêmio incorreto");
        Assertions.assertEquals(6, loteria.getNumerosSorteados().size(), "Tamanho da lista incorreta");
        for(Integer numero: loteria.getNumerosSorteados()){
            Assertions.assertFalse(numero < 1, "Número menor que 1");
            Assertions.assertFalse(numero > 60, "Número maior que 60");
        }
    }

}