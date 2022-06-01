package server;

import client.Order;
import client.OrderStatus;
import view.GenericRestaurantForm;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class KitchenServer extends AbstractKitchenServer {

    private ServerSocket serverSocket;
    private ClientHandler clientHandler;

    Order order;
    public ArrayList<Order> orderArrayList = new ArrayList<>();

    CompletableFuture<OrderStatus> completableFuture = new CompletableFuture<>();

    public static void main(String[] args) {
        try {

            // Start Server
            ServerSocket serverSocket = new ServerSocket(4334);
            KitchenServer kitchenServer = new KitchenServer(serverSocket);
            kitchenServer.startServer();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public KitchenServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() {


        try {
            while (!serverSocket.isClosed()) {
                System.out.println("Hello");
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
        return null; //KitchenStatus.Received
    }

    @Override
    public CompletableFuture<OrderStatus> checkStatus(String orderID) throws InterruptedException {

        for(Order order : orderArrayList) {
            if(order.getOrderID().contains(orderID)) {
                order.setStatus(OrderStatus.Received);
            }
        }

        return completableFuture;
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

    public class ClientHandler implements Runnable {

        private ObjectOutputStream objectOutputStream;
        private ObjectInputStream objectInputStream;
        private KitchenServer kitchenServer;

        public ClientHandler(Socket socket, KitchenServer kitchenServer) {
            try {
                this.kitchenServer = kitchenServer;
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectInputStream = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                // TO DO!
                try {
                    Order order = (Order) objectInputStream.readObject();
                    orderArrayList.add(order);
                    kitchenServer.receiveOrder(order);
                    kitchenServer.checkStatus(order.getOrderID());

                    System.out.println(order.getOrderID());

                } catch (IOException | ClassNotFoundException | InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("KitchenServer runs!");
            }
        }
    }
}
