import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

class ClienteRifa {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            IServicoRifa servicoRifa = (IServicoRifa) registry.lookup("ServicoRifa");
            System.out.println("Digite seu nome: ");

            Cliente cliente = new Cliente(scan.next());

            while(true){
                System.out.println("Digite o nome da rifa a participar: ");
                String nomeSorteio = scan.next();

                servicoRifa.adquirirCartela(cliente, nomeSorteio);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}