// Restaurant as a business entity
class Business {
    protected String name;
    protected String address;
    protected String phone;

    public Business(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void displayInfo() {
        System.out.println("Business: " + name + ", Address: " + address + ", Phone: " + phone);
    }
}