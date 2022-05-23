package todo;

import wegotthis.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;

public class KitchenServer extends AbstractKitchenServer {

    private ServerSocket serverSocket;
    private ClientHandler clientHandler;

    public KitchenServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() {

        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                clientHandler = new ClientHandler(socket, this);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CompletableFuture<KitchenStatus> receiveOrder(Order order) throws InterruptedException {
        // TO DO!
        return null;
    }

    @Override
    public CompletableFuture<OrderStatus> checkStatus(String orderID) throws InterruptedException {
        // TO DO!
        return null;
    }

    @Override
    public CompletableFuture<KitchenStatus> serveOrder(String orderID) throws InterruptedException {
        // TO DO!
        return null;
    }

    @Override
    protected void cook(Order order) {
        // TO DO!
    }

    public static class ClientHandler implements Runnable {

        private ObjectOutputStream objectOutputStream;
        private ObjectInputStream objectInputStream;
        private KitchenServer kitchenServer;

        public ClientHandler(Socket socket, KitchenServer kitchenServer) {
            try {
                this.kitchenServer = kitchenServer;

                objectInputStream = new ObjectInputStream(socket.getInputStream());
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                // TO DO!

                System.out.println("KitchenServer runs!");
            }
        }
    }
}
