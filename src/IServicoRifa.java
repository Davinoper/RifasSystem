import java.rmi.Remote;
import java.rmi.RemoteException;

interface IServicoRifa extends Remote {
    void adquirirCartela(Cliente cliente, String nomeSorteio) throws RemoteException, InterruptedException;
    void realizarSorteio(String nomeSorteio) throws RemoteException;
    void criarSorteio(String nome) throws RemoteException;
    void infoSorteio(String nomeSorteio) throws RemoteException;
}
