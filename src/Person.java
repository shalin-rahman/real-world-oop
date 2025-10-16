// Common foundation for people in the system
// --- Abstract Class ---
abstract class Person {
    protected String name;
    protected String contact;

    public Person(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }

    public void displayInfo() {
        System.out.println("Name: " + name + ", Contact: " + contact);
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public abstract void roleInfo();
}