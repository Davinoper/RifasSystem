package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Sorteio implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String vencedor;
    private List<Cliente> rifas;
    private boolean sorteioRealizado;

    public Sorteio(String name)  {
        this.name = name;
        this.vencedor =null;
        this.sorteioRealizado =false;
        this.rifas = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Cliente> getRifas() {
        return rifas;
    }

    public void setRifas(List<Cliente> rifas) {
        this.rifas = rifas;
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
