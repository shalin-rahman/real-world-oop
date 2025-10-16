import java.util.Arrays;

public class FoodTigerSystem {
    public static void main(String[] args) {
        System.out.println(" FoodTiger Delivery System Starting...\n");

        // Create objects
        Customer customer1 = new Customer("Mr. Rahman", "9876543210", "CUST001");
        Customer customer2 = new Customer("Ms. Sultana", "8765432109", "CUST002");

        Restaurant restaurant1 = new Restaurant("Spice Garden", "45 Commercial Street", "080-23456789");
        Restaurant restaurant2 = new Restaurant("Pizza Palace", "67 Mall Road", "080-34567890");

        Rider rider1 = new Rider("Abdul", "7654321098", "Motorcycle");
        Rider rider2 = new Rider("Akmol", "6543210987", "Scooter");

        // Display info
        customer1.displayInfo();
        restaurant1.displayInfo();
        rider1.displayInfo();
        System.out.println();

        // Customer 1 places order
        System.out.println("=== ORDER 1 ===");
        customer1.placeOrder(restaurant1, Arrays.asList("Butter Chicken", "Garlic Naan", "Rice"));

        // Rider 1 delivers order
        Order order1 = customer1.getOrderHistory().get(0);
        rider1.deliver(order1);

        System.out.println("\n=== ORDER 2 ===");
        // Customer 2 places order
        customer2.placeOrder(restaurant2, Arrays.asList("Pepperoni Pizza", "Garlic Bread", "Coke"));

        // Rider 2 delivers order
        Order order2 = customer2.getOrderHistory().get(0);
        rider2.deliver(order2);

        System.out.println("\n=== SYSTEM STATUS ===");
        // Check system status
        System.out.println("Order 1 Status: " + order1.getStatus());
        System.out.println("Order 1 Rider: " + (order1.getRider() != null ? order1.getRider().getName() : "None"));

        System.out.println("Order 2 Status: " + order2.getStatus());
        System.out.println("Order 2 Rider: " + (order2.getRider() != null ? order2.getRider().getName() : "None"));

        System.out.println("Rider 1 Available: " + rider1.isAvailable());
        System.out.println("Rider 2 Available: " + rider2.isAvailable());

        System.out.println("\n=== ROLE INFORMATION ===");
        customer1.roleInfo();
        rider1.roleInfo();

        System.out.println("\n FoodTiger System Completed Successfully!");
    }
}