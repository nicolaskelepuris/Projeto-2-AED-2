package cenario_unico.estruturas_dados;

import cenario_unico.logging.Logger;

public class ListaDuplamenteEncadeada<T> {
    private No<T> primeiro;
    private No<T> ultimo;
    final private int capacidade;
    private int quantidade;
    final private Logger logger;

    public ListaDuplamenteEncadeada(int capacidade, Logger logger) {
        this.capacidade = capacidade;
        this.logger = logger;
    }

    
    /**
     * Adiciona o item na ultima posicao da lista, caso nao esteja cheia
     * @param item
     */
    public void add(T item) {
        logger.logComando("Adicionar o item no final da lista: " + item.toString(), countToString());
        if (isFull()) {
            logger.logErro("Lista cheia, nao foi possivel adicionar o item", countToString());
            return;
        }

        quantidade++;

        No<T> no = new No<T>(item);

        if (primeiro == null) {
            initialize(no);
        } else {
            if (primeiro == ultimo) {
                ultimo = no;
                primeiro.proximo = ultimo;
                primeiro.anterior = ultimo;
                ultimo.proximo = primeiro;
                ultimo.anterior = primeiro;
            } else {
                No<T> antigoUltimo = ultimo;
                ultimo = no;
                ultimo.anterior = antigoUltimo;
                ultimo.proximo = primeiro;
                antigoUltimo.proximo = ultimo;
            }
        }
        logger.logSucesso("Item adicionado: " + item.toString(), countToString());
    }

    /**
     * Insere o item no index informado
     * @param index - precisa ser um index valido (maior/igual a 0 e menor/igual a quantidade de itens na lista)
     * @param item - item a ser inserido
     */
    public void inserir(int index, T item) {
        logger.logComando("Insere o item: " + item.toString() + " no index: " + index + " da lista", countToString());
        if (!isValidIndexToInsert(index)) {
            logger.logErro("Index fora do range, nao foi possivel inserir o item", countToString());
            return;
        }

        if (isFull()) {
            logger.logErro("Lista cheia, nao foi possivel inserir o item", countToString());
            return;
        }

        logger.logSucesso("Item inserido: " + item.toString(), countToString());

        if (isLastPossiblePositionToInsert(index)) {
            add(item);
            return;
        }

        if (isFirstIndex(index)) {
            insertPrimeiro(item);
            return;
        }

        No<T> noASerInserido = new No<T>(item);
        No<T> noNoIndexRequerido = nodeAt(index);

        insertNode(noASerInserido, noNoIndexRequerido);
    }
    /**
     * Remove o item no index informado
     * @param index - precisa ser um index valido (maior/igual a 0 e menor/igual a quantidade de itens - 1)
     */
    public void remover(int index) {
        logger.logComando("Remove o item no index: " + index + " da lista", countToString());
        if (!indexExists(index)) {
            logger.logErro("Index fora do range, nao foi possivel remover o item", countToString());
            return;
        }

        if (isEmpty()) {
            logger.logErro("Lista vazia, nao foi possivel remover o item", countToString());
            return;
        }

        if (quantidade == 1) {
            logger.logSucesso("Item removido: " + primeiro.valor.toString(), countToString());
            clear();
            return;
        }

        if (isFirstIndex(index)) {
            logger.logSucesso("Item removido: " + primeiro.valor.toString(), countToString());
            removeFirstItem();
            return;
        }

        if (isLastIndex(index)) {
            logger.logSucesso("Item removido: " + ultimo.valor.toString(), countToString());
            removeLastItem();
            return;
        }

        No<T> noASerRemovido = nodeAt(index);

        removeNode(noASerRemovido);
        logger.logSucesso("Item removido: " + noASerRemovido.valor.toString(), countToString());
    }

    /**
     * Busca o elemento no index informado
     * @param index
     * @return o elemento no index caso exista, null caso nao exista
     */
    public T elementAt(int index) {
        No<T> no = nodeAt(index);

        return no != null ? no.valor : null;
    }

    /**
     * Busca o primeiro item da lista
     * @return o primeiro elemento da lista caso exista, null caso nao exista
     */
    public T getFirst() {        
        return primeiro != null ? primeiro.valor : null;
    }

    /**
     * Busca o ultimo item da lista
     * @return o ultimo elemento da lista caso exista, null caso nao exista
     */
    public T getLast() {        
        return ultimo != null ? ultimo.valor : null;
    }

    /**
     * Busca a quantidade de itens presentes na lista
     * @return a quantidade de itens presentes na lista
     */
    public int count() {
        return quantidade;
    }

    /**
     * Checa se a lista esta vazia
     * @return true se a lista estiver vazia, false se nao estiver vazia
     */
    public Boolean isEmpty() {
        return quantidade == 0;
    }

    /**
     * Checa se a lista esta cheia
     * @return true se a lista estiver cheia, false se nao estiver cheia
     */
    public Boolean isFull() {
        return capacidade == quantidade;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Nao existem itens adicionados";
        }

        String str = primeiro.valor.toString();
        
        No<T> noAtual = primeiro.proximo;
        for (int i = 1; i < quantidade; i++) {
            str = String.join("\n\t", str, noAtual.valor.toString());
            noAtual = noAtual.proximo;
        }
        
        return str;
    }

    private Boolean indexExists(int index) {
        return index == 0 || (index > 0 && index < quantidade);
    }

    private Boolean isValidIndexToInsert(int index) {
        return index == 0 || (index > 0 && index <= quantidade);
    }

    private Boolean isLastPossiblePositionToInsert(int index) {
        return (index == 0 && isEmpty()) || index == quantidade;
    }

    private Boolean isFirstIndex(int index) {
        return index == 0;
    }

    private Boolean isLastIndex(int index) {
        return index == quantidade - 1;
    }

    private void insertPrimeiro(T item) {
        quantidade++;

        No<T> antigoPrimeiro = primeiro;
        primeiro = new No<T>(item);
        primeiro.proximo = antigoPrimeiro;
        primeiro.anterior = ultimo;
        antigoPrimeiro.anterior = primeiro;
        ultimo.proximo = primeiro;
    }

    private void removeFirstItem() {
        if (quantidade == 2) {
            initialize(ultimo);
        } else {
            primeiro = primeiro.proximo;
            primeiro.anterior = ultimo;
            ultimo.proximo = primeiro;
        }

        quantidade--;
    }

    private void removeLastItem() {
        if (quantidade == 2) {
            initialize(primeiro);
        } else {
            ultimo = ultimo.anterior;
            ultimo.proximo = primeiro;
            primeiro.anterior = ultimo;
        }

        quantidade--;
    }

    private void clear() {
        quantidade = 0;

        primeiro = null;
        ultimo = null;
    }

    private void initialize(No<T> no) {
        primeiro = no;
        ultimo = primeiro;
        ultimo.proximo = null;
        ultimo.anterior = null;
    }

    private No<T> nodeAt(int index) {
        if (!indexExists(index)) {
            return null;
        }

        No<T> noAtual = primeiro;

        for (int i = 0; i < quantidade; i++) {
            if (i == index) {
                return noAtual;
            }

            noAtual = noAtual.proximo;
        }

        return null;
    }

    private void removeNode(No<T> no) {
        quantidade--;

        if (no.proximo != null) {
            no.proximo.anterior = no.anterior;
        }

        if (no.anterior != null) {
            no.anterior.proximo = no.proximo;
        }
    }

    private void insertNode(No<T> noASerInserido, No<T> noNoIndexRequerido) {
        quantidade++;

        noASerInserido.proximo = noNoIndexRequerido;
        noASerInserido.anterior = noNoIndexRequerido.anterior;
        noNoIndexRequerido.anterior.proximo = noASerInserido;
        noNoIndexRequerido.anterior = noASerInserido;
    }

    private String countToString() {
        return "Lista: Quantidade -> " + quantidade;
    }
}
