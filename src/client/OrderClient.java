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

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 4334);
        OrderClient orderClient = new OrderClient(socket);
    }

    public OrderClient(Socket socket) {

        this.socket = socket;

        restaurant = new GenericRestaurantForm();
        restaurant.Start(this);

        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            new Read(socket).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void submitOrder() {
        try {
            OrderItem orderItem = new OrderItem("Apple", "Just an apple!", 20);
            Order order = new Order();
            order.addOrderItem(orderItem);
            //System.out.println(order.getOrderID());

            objectOutputStream.writeObject(order);
            objectOutputStream.flush();

        } catch(Exception e) {
            e.printStackTrace();
        }
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

    @Override
    public void submitOrder(String s) {

    }

    private static class Read extends Thread {

        private ObjectInputStream objectInputStream;
        private ObjectOutputStream objectOutputStream;
        private Socket socket;

        public Read(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {

            try {
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectInputStream = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            while(!Thread.interrupted()) {
                // TO DO!
                try {

                    Object obj = objectInputStream.readObject();
                    System.out.println(obj);

                    Thread.sleep(1000);
                    System.out.println("OderClient runs!");

                } catch (InterruptedException | ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
