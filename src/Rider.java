import java.util.ArrayList;
import java.util.List;

class Rider extends Person implements Deliverable, Notifiable {
    private String vehicle;
    private boolean isAvailable;
    private List<Order> deliveryHistory;

    public Rider(String name, String contact, String vehicle) {
        super(name, contact);
        this.vehicle = vehicle;
        this.isAvailable = true;
        this.deliveryHistory = new ArrayList<>();
    }

    @Override
    public void deliver(Order order) {
        if (isAvailable) {
            System.out.println("🚗 " + name + " is delivering order on " + vehicle);
            order.assignRider(this);
            deliveryHistory.add(order);
            isAvailable = false;

            order.updateStatus("picked_up");

            // Simulate delivery time
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            order.updateStatus("delivered");
            order.updateStatus("completed");
            isAvailable = true;

            System.out.println("✅ " + name + " completed delivery of order " + order.getOrderId());
        } else {
            System.out.println("❌ " + name + " is not available for delivery");
        }
    }

    public Order getCurrentDelivery(List<Order> allOrders) {
        for (Order order : allOrders) {
            if (order.getRider() == this &&
                    !order.getStatus().equals("completed") &&
                    !order.getStatus().equals("cancelled")) {
                return order;
            }
        }
        return null;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("🛵 Rider " + name + " notification: " + message);
    }

    @Override
    public void receiveOrderUpdate(Order order) {
        if (order.getStatus().equals("ready") && order.getRider() == null) {
            sendNotification("Order " + order.getOrderId() + " is ready for pickup at " + order.getRestaurant().getName());
        }
    }

    @Override
    public void roleInfo() {
        System.out.println("Rider " + name + " - delivers orders using " + vehicle);
    }
}
