package cenario_unico.estruturas_dados;

import cenario_unico.logging.Logger;

public class Fila<T> {
    final private ListaDuplamenteEncadeada<T> lista;
    final private Logger logger;

    public Fila(int capacidade, Logger logger) {
        this.logger = logger;
        this.lista = new ListaDuplamenteEncadeada<>(capacidade, new Logger());
    }

    /**
     * Adiciona o item no final da fila
     * @param item
     */
    public void enqueue(T item) {
        logger.logComando("Adicionar o item no final da fila: " + item.toString(), countToString());

        if (isFull()) {
            logger.logErro("Fila cheia, nao foi possivel adicionar o item", countToString());
            return;
        }

        lista.add(item);
        logger.logSucesso("Item adicionado: " + item.toString(), countToString());
    }

    /**
     * Busca e remove o primeiro item da fila
     * @return primeiro item da fila caso exista, null caso nao exista
     */
    public T dequeue() {
        logger.logComando("Buscar e remover o primeiro item da fila", countToString());

        if (isEmpty()) {
            logger.logErro("Fila vazia, nao foi possivel buscar e remover o primeiro item da fila", countToString());
            return null;
        }

        T item = lista.getFirst();
        lista.remover(0);
        logger.logSucesso("Foi buscado e removido o primeiro item da fila: " + item.toString(), countToString());

        return item;
    }

    /**
     * Busca o primeiro item da fila
     * @return primeiro item da fila caso exista, null caso nao exista
     */
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

    /**
     * Checa se a fila esta vazia
     * @return true se a fila estiver vazia, false se nao estiver vazia
     */
    public Boolean isEmpty() {
        return lista.isEmpty();
    }

    /**
     * Checa se a fila esta cheia
     * @return true se a fila estiver cheia, false se nao estiver cheia
     */
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
