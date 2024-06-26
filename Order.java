import java.util.Date;
import java.util.List;

class Order {
    static int orderIdCounter = 1;

    int orderId;
    Buyer buyer;
    Date orderDate;
    List<Item> items;
    String deliveryAddress;
    Driver driver;
    String status;
    Locker locker;

    public Order(Buyer buyer, Date orderDate, List<Item> items, String deliveryAddress, Driver driver, String status, Locker locker) {
        this.orderId = orderIdCounter++;
        this.buyer = buyer;
        this.orderDate = orderDate;
        this.items = items;
        this.deliveryAddress = deliveryAddress;
        this.driver = driver;
        this.status = status;
        this.locker = locker;
    }
}
