import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


class Servidor {
    public static void main(String[] args) {
        try{
            ServicoRifaImpl servidorRifa = new ServicoRifaImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("ServicoRifa", servidorRifa);
            System.out.println("Servidor RMI iniciado.");

            ServidorSocketRifa servidorSocketRifa = new ServidorSocketRifa();
            servidorSocketRifa.start(12345);
            System.out.println("Servidor socket iniciado.");
        }catch (IOException e){
            e.printStackTrace();
        }


    }


}