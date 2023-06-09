import RMI.IServicoRifa;
import model.Cliente;
import model.Sorteio;

import java.io.*;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

class ClienteRifa {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String enderecoServer = "localhost";
        int portaServer = 12345;

        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            IServicoRifa servicoRifa = (IServicoRifa) registry.lookup("ServicoRifa");

            Socket socket = new Socket(enderecoServer, portaServer);
            System.out.println("Conectado ao servidor em " + enderecoServer + ":" + portaServer);

            Thread clientThread = new Thread(new ClienteHandler(socket));
            clientThread.start();

            System.out.println("Digite seu nome: ");
            Cliente cliente = new Cliente(scan.next());

            while (true) {
                System.out.println("oque quer fazer: 1- ver rifas | 2- adquirir rifa ");
                int aux = scan.nextInt();

                switch (aux){
                    case 1:
                        List<Sorteio> sorteios  = servicoRifa.verSorteiosDisponiveis();
                        System.out.println("++++++++RIFAS++++++++++++");
                        sorteios.forEach(s -> System.out.println(s.getName()));
                        System.out.println("+++++++++++++++++++++++++");
                        break;
                    case 2:
                        System.out.println("Digite o nome da rifa a participar: ");
                        String nomeSorteio = scan.next();
                        try{
                            servicoRifa.adquirirCartela(cliente, nomeSorteio);
                        }catch (NullPointerException e){
                            System.out.println(" ");
                            System.out.println("!! A rifa não existe. !!");
                            System.out.println(" ");
                        }
                        break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class ClienteHandler implements Runnable {

        private Socket socket;

        public ClienteHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println("Mensagem recebida do servidor: " + message);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}