import java.io.Serializable;

public class Sorteio implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String vencedor;
    private boolean sorteioRealizado;

    public Sorteio(String name)  {
        this.name = name;
        this.vencedor =null;
        this.sorteioRealizado =false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVencedor() {
        return vencedor;
    }

    public void setVencedor(String vencedor) {
        this.vencedor = vencedor;
    }

    public boolean isSorteioRealizado() {
        return sorteioRealizado;
    }

    public void setSorteioRealizado(boolean sorteioRealizado) {
        this.sorteioRealizado = sorteioRealizado;
    }
}
