import java.util.ArrayList;
import java.util.List;

// --- Customer Class ---
class Customer extends Person implements Notifiable {
    private String customerId;
    private List<Order> orderHistory;

    public Customer(String name, String contact, String customerId) {
        super(name, contact);
        this.customerId = customerId;
        this.orderHistory = new ArrayList<>();
    }

    public void placeOrder(Restaurant restaurant, List<String> items) {
        String orderId = "ORD-" + System.currentTimeMillis();
        Order order = new Order(orderId, this, restaurant, items);
        orderHistory.add(order);

        System.out.println(name + " placed order at " + restaurant.getName() + ": " + items);
        order.updateStatus("confirmed");

        // Notify restaurant
        restaurant.receiveOrderUpdate(order);
    }

    public List<Order> getOrderHistory() {
        return new ArrayList<>(orderHistory);
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("📱 SMS to " + name + " (" + contact + "): " + message);
    }

    @Override
    public void receiveOrderUpdate(Order order) {
        String message = "Your order " + order.getOrderId() + " is now: " + order.getStatus();
        sendNotification(message);
    }

    @Override
    public void roleInfo() {
        System.out.println("Customer " + name + " - can place orders and track deliveries");
    }
}