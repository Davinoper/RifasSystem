import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

interface IServicoRifa extends Remote {
    void adquirirCartela(Cliente cliente, String nomeSorteio) throws RemoteException, InterruptedException;
    void realizarSorteio(String nomeSorteio) throws RemoteException;
    void criarSorteio(String nome) throws RemoteException;
    void infoSorteio(String nomeSorteio) throws RemoteException;
    List<Sorteio> verSorteiosDisponiveis() throws RemoteException;
    Sorteio achaSorteio(String nomeSorteio) throws RemoteException;
}
