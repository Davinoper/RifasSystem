import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

class Servidor {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        try {
            ServicoRifaImpl servidorRifa = new ServicoRifaImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("ServicoRifa", servidorRifa);

            System.out.println("Servidor RMI iniciado.");


            while (true) {
                System.out.println("Sorteio: 1 - criar | 2 - realizar sorteio | 3 - informação sorteio");

                int aux = scan.nextInt();

                switch (aux){
                    case 1:
                        System.out.println("Nome do novo sorteio:");
                        String sort = scan.next();
                        servidorRifa.criarSorteio(sort);
                        break;
                    case 2:
                        System.out.println("Nome do sorteio:");
                        String sorteio = scan.next();
                        servidorRifa.realizarSorteio(sorteio);
                        break;
                    case 3:
                        System.out.println("Nome do sorteio:");
                        String sor = scan.next();
                        servidorRifa.infoSorteio(sor);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}