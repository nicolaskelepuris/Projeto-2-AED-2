package cenario_unico;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import cenario_unico.entidades.Ingresso;
import cenario_unico.entidades.Pessoa;
import cenario_unico.estruturas_dados.Fila;
import cenario_unico.estruturas_dados.Pilha;
import cenario_unico.logging.Logger;

public class Seed {
    public static Fila<Pessoa> seedPessoas(int quantidadePessoas, Logger logger){
        Fila<Pessoa> pessoas = new Fila<Pessoa>(quantidadePessoas, logger);

        Pessoa pessoa;
        for (int i = 1; i <= quantidadePessoas; i++) {
            pessoa = new Pessoa("Pessoa " + i, "Celular da pessoa " + i, "Email da pessoa " + i);
            pessoas.enqueue(pessoa);
        }

        return pessoas;
    }

    public static Pilha<Ingresso> seedIngressos(int quantidadeSalas, int quantidadeMinimaIngressosPorSala, int quantidadeMaximaIngressosPorSala, Logger logger) {
        Random random = new Random();
        Ingresso ingresso;
        Date data = createDate(3, 5, 2021);
        int[] quantidadeIngressosPorSala = new int[quantidadeSalas];

        int quantidadeMaximaIngressos = 0;
        for (int i = 0; i < quantidadeSalas; i++) {
            int quantidadeIngressos = random.nextInt(quantidadeMaximaIngressosPorSala - quantidadeMinimaIngressosPorSala + 1) + quantidadeMinimaIngressosPorSala;
            quantidadeIngressosPorSala[i] = quantidadeIngressos;
            quantidadeMaximaIngressos += quantidadeIngressos;
        }

        Pilha<Ingresso> ingressos = new Pilha<Ingresso>(Ingresso.class,
            quantidadeMaximaIngressos, logger);

        for (int i = 1; i <= quantidadeSalas; i++) {
            for (int j = 0; j < quantidadeIngressosPorSala[i - 1]; j++) {
                ingresso = new Ingresso(i, data, "Conteudo da sala " + i);
                ingressos.push(ingresso);
            }
        }

        return ingressos;
    }

    private static Date createDate(int dia, int mes, int ano) {
        Calendar calendario = new GregorianCalendar(ano, mes, dia);
        return calendario.getTime();
    }
}
