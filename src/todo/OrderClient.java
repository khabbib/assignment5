package todo;

import wegotthis.AbstractOrderClient;
import wegotthis.GenericRestaurantForm;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class OrderClient extends AbstractOrderClient {

    private Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;


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

    private class Read extends Thread {

        @Override
        public void run() {
            while(true) {
                // TO DO!

                System.out.println("OrderClient runs!");
            }
        }
    }
}
