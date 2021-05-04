package cenario_unico.estruturas_dados;

import java.lang.reflect.Array;

import cenario_unico.logging.Logger;

public class Pilha<T> {
    final private T[] pilha;
    final private int capacidade;
    private int quantidade;
    final private Logger logger;

    public Pilha(Class<T> classe, int capacidade, Logger logger) {
        this.capacidade = capacidade;
        this.logger = logger;

        // gambiarra para ser possivel criar array generico em java
        @SuppressWarnings("unchecked")
        final T[] array = (T[]) Array.newInstance(classe, capacidade);
        pilha = array;
    }

    public void push(T item) {
        logger.logComando("Adicionar o item no topo da pilha: " + item.toString(), countToString());
        if (isFull()) {
            logger.logErro("Pilha cheia, nao foi possivel adicionar o item", countToString());
            return;
        }

        int index = nextAvailableIndexToPush();

        pilha[index] = item;
        quantidade++;
        logger.logSucesso("Item adicionado: " + item.toString(), countToString());
    }

    public T pop() {
        logger.logComando("Buscar e remover o item no topo da pilha", countToString());
        if (isEmpty()) {
            logger.logErro("Pilha vazia, nao foi possivel buscar e remover o item no topo da pilha", countToString());
            return null;
        }

        int index = lastIndexPushed();
        T item = pilha[index];
        pilha[index] = null;
        quantidade--;
        logger.logSucesso("Foi buscado e removido o item no topo da pilha: " + item.toString(), countToString());

        return item;
    }

    public T peek() {
        logger.logComando("Buscar o item no topo da pilha", countToString());
        if (isEmpty()) {
            logger.logErro("Pilha vazia, nao foi possivel buscar o item no topo da pilha", countToString());
            return null;
        }

        int index = lastIndexPushed();
        T item = pilha[index];
        logger.logSucesso("Foi buscado o item no topo da pilha: " + item.toString(), countToString());

        return item;
    }

    public Boolean isEmpty() {
        return quantidade == 0;
    }

    public int count(){
        return quantidade;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Nao existem itens adicionados";
        }

        String str = pilha[0].toString();

        for (int i = 1; i < quantidade; i++) {
            str = String.join(" - ", str, pilha[i].toString());
        }

        return str;
    }

    private Boolean isFull() {
        return quantidade == capacidade;
    }

    private int nextAvailableIndexToPush() {
        return quantidade;
    }

    private int lastIndexPushed() {
        return quantidade - 1;
    }

    private String countToString() {
        return "Pilha: Quantidade -> " + quantidade;
    }
}
