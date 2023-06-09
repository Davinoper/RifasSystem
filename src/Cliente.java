import java.io.Serializable;

class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nome;

    public Cliente(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}