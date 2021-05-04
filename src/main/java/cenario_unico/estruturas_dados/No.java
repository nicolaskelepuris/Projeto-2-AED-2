package cenario_unico.estruturas_dados;

public class No<T> {
    public T valor;
    public No<T> proximo;
    public No<T> anterior;

    public No(T valor) {
        this.valor = valor;
    }
}
