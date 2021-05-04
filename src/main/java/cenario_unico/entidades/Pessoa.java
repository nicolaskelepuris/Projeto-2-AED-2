package cenario_unico.entidades;

public class Pessoa {
    private String nome;
    private String celular;
    private String email;

    public Pessoa(String nome, String celular, String email) {
        this.nome = nome;
        this.celular = celular;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getCelular() {
        return celular;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Pessoa: Nome: " + nome + ", Celular: " + celular + ", Email: " + email;
    }
}
