import java.rmi.Remote;
import java.rmi.RemoteException;

interface IServicoRifa extends Remote {
    void adquirirCartela(Cliente cliente) throws RemoteException;
    void realizarSorteio() throws RemoteException;
}
