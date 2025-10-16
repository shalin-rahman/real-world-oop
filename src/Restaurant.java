import java.util.ArrayList;
import java.util.List;

// --- Restaurant Class ---
class Restaurant extends Business implements Notifiable {
    private List<Order> currentOrders;

    public Restaurant(String name, String address, String phone) {
        super(name, address, phone);
        this.currentOrders = new ArrayList<>();
    }

    public void prepareOrder(Order order) {
        currentOrders.add(order);
        System.out.println("👨‍🍳 " + name + " is preparing order: " + order.getItems());
        order.updateStatus("preparing");

        // Simulate preparation time
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        order.updateStatus("ready");
        currentOrders.remove(order);
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("🏪 Restaurant " + name + " notification: " + message);
    }

    @Override
    public void receiveOrderUpdate(Order order) {
        if (order.getStatus().equals("confirmed")) {
            sendNotification("New order received: " + order.getOrderId());
            prepareOrder(order);
        } else if (order.getStatus().equals("ready")) {
            sendNotification("Order " + order.getOrderId() + " is ready for pickup");
        }
    }

    @Override
    public void displayInfo() {
        System.out.println("🍽️  Restaurant: " + name + " | " + address + " | " + phone);
    }
}
