package cenario_unico;

import cenario_unico.entidades.Ingresso;
import cenario_unico.entidades.Pessoa;
import cenario_unico.estruturas_dados.Fila;
import cenario_unico.estruturas_dados.ListaDuplamenteEncadeada;
import cenario_unico.estruturas_dados.Pilha;
import cenario_unico.logging.Logger;

public final class App {
    final static int quantidadePessoas = 93;
    final static int quantidadeSalas = 5;
    final static int quantidadeMinimaIngressosPorSala = 10;
    final static int quantidadeMaximaIngressosPorSala = 15;

    private App() {
    }

    public static void main(String[] args) {

        final Logger logger = new Logger();
        Fila<Pessoa> pessoas = Seed.seedPessoas(quantidadePessoas, logger);
        Pilha<Ingresso> ingressos = Seed.seedIngressos(quantidadeSalas, quantidadeMinimaIngressosPorSala,
                quantidadeMaximaIngressosPorSala, logger);

        ListaDuplamenteEncadeada<Ingresso> ingressosDistribuidos = DistribuirIngressos(ingressos, pessoas, logger);

        logger.log("\n\n\nIngressos distribuidos:\n\t" + ingressosDistribuidos.toString());

        logger.generateFile();
    }

    private static ListaDuplamenteEncadeada<Ingresso> DistribuirIngressos(Pilha<Ingresso> ingressos, Fila<Pessoa> pessoas,
            Logger logger) {
        ListaDuplamenteEncadeada<Ingresso> ingressosDistribuidos = new ListaDuplamenteEncadeada<Ingresso>(
                ingressos.count(), logger);

        while (!ingressos.isEmpty() && !pessoas.isEmpty()) {
            Ingresso ingresso = ingressos.pop();
            Pessoa pessoa = pessoas.dequeue();

            ingresso.setDono(pessoa);

            ingressosDistribuidos.add(ingresso);
        }

        return ingressosDistribuidos;
    }
}
