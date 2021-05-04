package cenario_unico.estruturas_dados;

import cenario_unico.logging.Logger;

public class Fila<T> {
    final private ListaDuplamenteEncadeada<T> lista;
    final private Logger logger;

    public Fila(int capacidade, Logger logger) {
        this.logger = logger;
        this.lista = new ListaDuplamenteEncadeada<>(capacidade, new Logger());
    }

    public void enqueue(T item) {
        logger.logComando("Adicionar o item no final da fila: " + item.toString(), countToString());

        if (isFull()) {
            logger.logErro("Fila cheia, nao foi possivel adicionar o item", countToString());
            return;
        }

        lista.add(item);
        logger.logSucesso("Item adicionado: " + item.toString(), countToString());
    }

    public T dequeue() {
        logger.logComando("Busca e remove o primeiro item da fila", countToString());

        if (isEmpty()) {
            logger.logErro("Fila vazia, nao foi possivel buscar e remover o primeiro item da fila", countToString());
            return null;
        }

        T item = lista.getFirst();
        lista.remover(0);
        logger.logSucesso("Foi buscado e removido o primeiro item da fila: " + item.toString(), countToString());

        return item;
    }

    public T peek() {
        logger.logComando("Buscar o primeiro item da fila", countToString());

        if (isEmpty()) {
            logger.logErro("Fila vazia, nao foi possivel buscar o primeiro item da fila", countToString());
            return null;
        }

        T item = lista.getFirst();

        logger.logSucesso("Foi buscado o primeiro item da fila: " + item.toString(), countToString());
        return item;
    }

    public Boolean isEmpty() {
        return lista.isEmpty();
    }

    public Boolean isFull() {
        return lista.isFull();
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Fila: vazia";
        }

        return "Fila: Inicio -> " + lista.toString();
    }

    private String countToString() {
        return "Fila: Quantidade -> " + lista.count();
    }
}
