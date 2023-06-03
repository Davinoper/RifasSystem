import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

class ServicoRifaImpl extends UnicastRemoteObject implements IServicoRifa {
    private static final long serialVersionUID = 1L;
    private List<Cliente> clientes;
    private boolean sorteioRealizado;

    public ServicoRifaImpl() throws RemoteException {
        clientes = new ArrayList<>();
        sorteioRealizado = false;
    }

    public void adquirirCartela(Cliente cliente) throws RemoteException {
        if (!sorteioRealizado) {
            clientes.add(cliente);
            System.out.println("Cliente " + cliente.getNome() + " adquiriu uma cartela.");
        } else {
            System.out.println("Desculpe, o sorteio já foi realizado.");
        }
    }

    public void realizarSorteio() throws RemoteException {
        if (!sorteioRealizado) {
            if (clientes.size() > 0) {
                int indiceVencedor = (int) (Math.random() * clientes.size());
                Cliente vencedor = clientes.get(indiceVencedor);
                System.out.println("O vencedor da rifa é: " + vencedor.getNome());
                sorteioRealizado = true;
            } else {
                System.out.println("Não há clientes suficientes para realizar o sorteio.");
            }
        } else {
            System.out.println("O sorteio já foi realizado anteriormente.");
        }
    }
}