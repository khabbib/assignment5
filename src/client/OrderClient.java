package client;

import view.GenericRestaurantForm;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class OrderClient extends AbstractOrderClient {

    static GenericRestaurantForm restaurant;

    private Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public static void main(String[] args) {
        try {

            restaurant = new GenericRestaurantForm();
            restaurant.Start(false);

            // Start Client
            Socket socket = new Socket("localhost", 4334);
            OrderClient orderClient = new OrderClient(socket);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public OrderClient(Socket socket) {
        this.socket = socket;
        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            new Read().start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void submitOrder() {
        // TO DO!
    }

    @Override
    protected void startPollingServer(String orderId) {
        // TO DO!
    }

    @Override
    protected void pickUpOrder() {
        // TO DO!
    }

    private static class Read extends Thread {

        @Override
        public void run() {
            while(!Thread.interrupted()) {
                // TO DO!
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("OrderClient runs!");
            }
        }
    }
}
