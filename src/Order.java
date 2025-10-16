import java.util.*;

class Order {
    private String orderId;
    private Customer customer;
    private Restaurant restaurant;
    private Rider rider;
    private String status;
    private List<String> items;
    private Date createdAt;

    public Order(String orderId, Customer customer, Restaurant restaurant, List<String> items) {
        this.orderId = orderId;
        this.customer = customer;
        this.restaurant = restaurant;
        this.items = items;
        this.status = "placed";
        this.createdAt = new Date();
    }

    // GETTER METHODS
    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Rider getRider() {
        return rider;
    }

    public String getStatus() {
        return status;
    }

    public List<String> getItems() {
        return new ArrayList<>(items);
    }

    public String getItemName() {
        return String.join(", ", items);
    }

    // STATUS MANAGEMENT
    public void updateStatus(String newStatus) {
        if (isValidStatusChange(this.status, newStatus)) {
            String oldStatus = this.status;
            this.status = newStatus;
            System.out.println("Order " + orderId + " status changed: " + oldStatus + " → " + newStatus);
            notifyParties();
        } else {
            System.out.println("Invalid status change: " + this.status + " → " + newStatus);
        }
    }

    private boolean isValidStatusChange(String currentStatus, String newStatus) {
        Map<String, List<String>> validTransitions = new HashMap<>();
        validTransitions.put("placed", Arrays.asList("confirmed", "preparing", "cancelled"));
        validTransitions.put("confirmed", Arrays.asList("preparing", "cancelled"));
        validTransitions.put("preparing", Arrays.asList("ready", "cancelled"));
        validTransitions.put("ready", Arrays.asList("picked_up", "cancelled"));
        validTransitions.put("picked_up", Arrays.asList("out_for_delivery", "delivered"));
        validTransitions.put("out_for_delivery", Arrays.asList("delivered"));
        validTransitions.put("delivered", Arrays.asList("completed"));
        validTransitions.put("cancelled", Arrays.asList());

        return validTransitions.getOrDefault(currentStatus, new ArrayList<>()).contains(newStatus);
    }

    public void assignRider(Rider rider) {
        this.rider = rider;
        updateStatus("assigned_to_rider");
    }

    private void notifyParties() {
        if (customer != null) {
            customer.receiveOrderUpdate(this);
        }
        if (restaurant != null) {
            restaurant.receiveOrderUpdate(this);
        }
        if (rider != null) {
            rider.receiveOrderUpdate(this);
        }
    }
}