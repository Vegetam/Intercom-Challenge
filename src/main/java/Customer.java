
public class Customer implements Comparable<Customer>{

    private String name;
    private int userId;

    public Customer(String name, int userId) {
        this.name = name;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }

    public int compareTo(Customer customer) {
        int compareUserId = customer.getUserId();
        return this.userId - compareUserId;
    }
}
