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

    /**
     * Adiciona o item no topo da pilha, caso nao esteja cheia
     * @param item
     */
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

    /**
     * Busca e remove o item no topo da pilha
     * @return o item no topo da pilha caso exista, null caso nao exista
     */
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

    /**
     * Busca o item no topo da pilha
     * @return o item no topo da pilha caso exista, null caso nao exista
     */
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

    /**
     * Checa se a pilha esta vazia
     * @return true se a pilha estiver vazia, false se nao estiver vazia
     */
    public Boolean isEmpty() {
        return quantidade == 0;
    }

    /**
     * Checa se a pilha esta cheia
     * @return true se a pilha estiver cheia, false se nao estiver cheia
     */
    public Boolean isFull() {
        return quantidade == capacidade;
    }

    /**
     * Busca a quantidade de itens presentes na pilha
     * @return a quantidade de itens presentes na pilha
     */
    public int count(){
        return quantidade;
    }
    
    @Override
    public String toString() {
        if (isEmpty()) {
            return "Nao existem itens adicionados";
        }

        String str = "Pilha: base da pilha -> " + pilha[0].toString();

        for (int i = 1; i < quantidade; i++) {
            str = String.join(" - ", str, pilha[i].toString());
        }

        return str;
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
