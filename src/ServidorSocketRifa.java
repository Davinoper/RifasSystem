import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServidorSocketRifa {

    private List<Socket> clientSockets;

    public ServidorSocketRifa() {
        clientSockets = new ArrayList<>();
    }

    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Servidor iniciado na porta " + port);

            Thread clientThread = new Thread(new ServerHandler());
            clientThread.start();

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Novo cliente conectado: " + clientSocket.getInetAddress().getHostAddress());
                clientSockets.add(clientSocket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcast(String message) {
        for (Socket clientSocket : clientSockets) {
            try {
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                writer.println(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class ServerHandler implements Runnable {

        public ServerHandler() {

        }

        @Override
        public void run() {
            Scanner scan = new Scanner(System.in);
            try {
                Registry registry = LocateRegistry.getRegistry("localhost", 1099);
                IServicoRifa servidorRifa = (IServicoRifa) registry.lookup("ServicoRifa");

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
                            Sorteio sorteado = servidorRifa.achaSorteio(sorteio);
                            broadcast(sorteado.getVencedor() + " Foi o vencedor da rifa " + sorteado.getName());
                            break;
                        case 3:
                            System.out.println("Nome do sorteio:");
                            String sor = scan.next();
                            servidorRifa.infoSorteio(sor);
                    }

                }
            }catch (IOException e){
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            }

        }
    }
}
