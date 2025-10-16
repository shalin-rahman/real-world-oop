// Interface for notification capability
interface Notifiable {
    void sendNotification(String message);
    void receiveOrderUpdate(Order order);
}