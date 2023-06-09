package RMI;

import model.Cliente;
import model.Sorteio;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ServicoRifaImpl extends UnicastRemoteObject implements IServicoRifa {
    private static final long serialVersionUID = 1L;
    private List<Sorteio> sorteios;

    public ServicoRifaImpl() throws RemoteException {
        sorteios = new ArrayList<>();
    }

    public void criarSorteio(String nome) throws RemoteException {
        Sorteio sorteio = new Sorteio(nome);
        sorteios.add(sorteio);
    }

    public void adquirirCartela(Cliente cliente, String nomeSorteio) throws RemoteException{
        Sorteio sorteio = achaSorteio(nomeSorteio);

        if (!sorteio.isSorteioRealizado()) {
            sorteio.getRifas().add(cliente);
            System.out.println("Cliente " + cliente.getNome() + " adquiriu uma rifa para " + sorteio.getName());
        } else {
            System.out.println("Desculpe, o sorteio já foi realizado.");
        }
    }

    public void realizarSorteio(String nomeSorteio) throws RemoteException {
        Sorteio sorteio = achaSorteio(nomeSorteio);
        if (!sorteio.isSorteioRealizado()) {
            if (sorteio.getRifas().size() > 0) {
                int indiceVencedor = (int) (Math.random() * sorteio.getRifas().size());
                Cliente vencedor = sorteio.getRifas().get(indiceVencedor);
                System.out.println("O vencedor da rifa é: " + vencedor.getNome());
                sorteio.setSorteioRealizado(true);
                sorteio.setVencedor(vencedor.getNome());
            } else {
                System.out.println("Não há clientes suficientes para realizar o sorteio.");
            }
        } else {
            System.out.println("O sorteio já foi realizado anteriormente.");
        }
    }

    public Sorteio achaSorteio(String nomeSorteio) throws RemoteException{
        for(Sorteio sorteio : sorteios){
            if(sorteio.getName().equals(nomeSorteio)){
                return sorteio;
            }
        }
        return null;
    }

    public List<Sorteio> verSorteiosDisponiveis(){
        return this.sorteios;
    }

    public void infoSorteio(String nomeSorteio) throws RemoteException{
        Sorteio sorteio = achaSorteio(nomeSorteio);

        System.out.println(" ");
        System.out.println("======Informações sorteiro========");
        System.out.println("Nome: " + sorteio.getName());
        System.out.println("Finalizado: " + sorteio.isSorteioRealizado());
        System.out.println("Vencedor: " + sorteio.getVencedor());
        System.out.println("==================================");
        System.out.println(" ");
    }

}