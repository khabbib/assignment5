package wegotthis;

import todo.KitchenServer;
import todo.OrderClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {

        GenericRestaurantForm restaurant = new GenericRestaurantForm();
        restaurant.Start();

        try {
            // Start Server
            ServerSocket serverSocket = new ServerSocket(4334);
            KitchenServer kitchenServer = new KitchenServer(serverSocket);
            kitchenServer.startServer();

            // Start Client
            Socket socket = new Socket("localhost", 4334);
            OrderClient orderClient = new OrderClient(socket);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
