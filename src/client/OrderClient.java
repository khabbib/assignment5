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
            new Read().start();

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

        @Override
        public void run() {
            while(!Thread.interrupted()) {
                // TO DO!
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("OrderClient runs!");
            }
        }
    }
}
