package cenario_unico;

import cenario_unico.estruturas_dados.Fila;
import cenario_unico.estruturas_dados.ListaDuplamenteEncadeada;
import cenario_unico.estruturas_dados.Pilha;
import cenario_unico.logging.Logger;

public class Testes {
    public static void testarEstrututrasDeDados() {
        testarPilha();
        testarFila();
        testarLista();
    }

    private static void testarPilha() {
        final Logger logger = new Logger();
        final int capacidade = 5;
        final Pilha<Integer> pilha = new Pilha<Integer>(Integer.class, capacidade, logger);

        pilha.pop();
        pilha.push(1);
        pilha.peek();
        pilha.pop();
        pilha.peek();
        pilha.push(1);
        pilha.push(2);
        pilha.push(3);
        pilha.pop();
        pilha.peek();
        pilha.pop();
        pilha.pop();
        pilha.pop();
        pilha.push(1);
        pilha.push(2);
        pilha.push(3);
        pilha.push(4);
        pilha.push(5);
        pilha.push(6);
        pilha.push(7);
        pilha.pop();
        pilha.pop();
        pilha.pop();
        pilha.pop();
        pilha.pop();
        pilha.push(1);
        pilha.push(1);
        pilha.push(2);
        pilha.push(3);

        logger.log("\n\nFim do teste\n" + pilha.toString());
        logger.generateFile("log-teste-pilha");
    }

    private static void testarFila() {
        final Logger logger = new Logger();
        final int capacidade = 5;
        final Fila<Integer> fila = new Fila<Integer>(capacidade, logger);

        fila.dequeue();
        fila.enqueue(1);
        fila.peek();
        fila.dequeue();
        fila.peek();
        fila.enqueue(1);
        fila.enqueue(2);
        fila.enqueue(3);
        fila.dequeue();
        fila.peek();
        fila.dequeue();
        fila.dequeue();
        fila.dequeue();
        fila.enqueue(1);
        fila.enqueue(2);
        fila.enqueue(3);
        fila.enqueue(4);
        fila.enqueue(5);
        fila.enqueue(6);
        fila.enqueue(7);
        fila.dequeue();
        fila.dequeue();
        fila.dequeue();
        fila.dequeue();
        fila.dequeue();
        fila.enqueue(1);
        fila.enqueue(1);
        fila.enqueue(2);
        fila.enqueue(3);

        logger.log("\n\nFim do teste\n" + fila.toString());
        logger.generateFile("log-teste-fila");
    }

    private static void testarLista() {
        final Logger logger = new Logger();
        final int capacidade = 5;
        final ListaDuplamenteEncadeada<Integer> lista = new ListaDuplamenteEncadeada<Integer>(capacidade, logger);

        lista.remover(0);
        lista.remover(1);
        lista.remover(2);
        lista.getFirst();
        lista.getLast();
        lista.add(1);
        lista.elementAt(0);
        lista.elementAt(1);
        lista.getFirst();
        lista.getLast();
        lista.remover(0);
        lista.elementAt(0);
        lista.add(1);
        lista.getFirst();
        lista.getLast();
        lista.add(2);
        lista.getFirst();
        lista.getLast();
        lista.add(3);
        lista.getFirst();
        lista.getLast();
        lista.remover(0);
        lista.remover(0);
        lista.elementAt(0);
        lista.getFirst();
        lista.getLast();
        lista.remover(0);
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);
        lista.add(5);
        lista.add(6);
        lista.add(7);
        lista.elementAt(3);
        lista.elementAt(4);
        lista.remover(3);
        lista.elementAt(3);
        lista.elementAt(4);
        lista.remover(0);
        lista.remover(0);
        lista.remover(0);
        lista.remover(0);
        lista.remover(0);
        lista.add(1);
        lista.add(3);
        lista.add(4);
        lista.inserir(1, 2);
        lista.inserir(4, 5);

        logger.log("\n\nFim do teste\n" + lista.toString());
        logger.generateFile("log-teste-lista");
    }
}
