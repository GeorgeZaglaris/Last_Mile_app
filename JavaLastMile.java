import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class Buyer {
    String name;
    String surname;
    String address;
    String email;

    public Buyer(String name, String surname, String address, String email) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.email = email;
    }
}

class Driver {
    String name;
    String surname;
    String address;
    String email;
    int AFM;
    String vehicleNumber;
    boolean toLocker;

    public Driver(String name, String surname, String address, String email, int AFM, String vehicleNumber, boolean toLocker) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.email = email;
        this.AFM = AFM;
        this.vehicleNumber = vehicleNumber;
        this.toLocker = toLocker;
    }
}

class Locker {
    String address;
    int quantity;
    boolean available;

    public Locker(String address, int quantity, boolean available) {
        this.address = address;
        this.quantity = quantity;
        this.available = available;
    }
}

class Item {
    String barcode;
    String name;
    String category;
    String brand;

    public Item(String barcode, String name, String category, String brand) {
        this.barcode = barcode;
        this.name = name;
        this.category = category;
        this.brand = brand;
    }
}

public class JavaLastMile {
    private static List<Item> items = new ArrayList<>();
    private static List<Buyer> buyers = new ArrayList<>();
    private static List<Driver> drivers = new ArrayList<>();
    private static List<Locker> lockers = new ArrayList<>();
    private static List<Order> orders = new ArrayList<>();


    public static void main(String[] args) {
        initializeSampleData();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Katachorisi paraggelias");
            System.out.println("2. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createOrder(scanner);
                    break;
                case 2:
                    System.out.println("Exodos apo to programma.");
                    return;
                default:
                    System.out.println("Lathos epilogi.");
            }
        }
    }

    private static void initializeSampleData() {
        items.add(new Item("1234", "Smartwatch", "Electronics", "Samsung"));
        items.add(new Item("5678", "Bluetooth Speaker", "Electronics", "JBL"));
        items.add(new Item("9012", "Wireless Mouse", "Electronics", "Logitech"));


        buyers.add(new Buyer("John", "Doe", "123 Main St", "john.doe@example.com"));
        buyers.add(new Buyer("Alice", "Smith", "456 Elm St", "alice.smith@example.com"));

        drivers.add(new Driver("Mike", "Johnson", "789 Oak St", "mike.johnson@example.com", 123456789, "ABC123", false));
        drivers.add(new Driver("Andreas", "Gewrgiou", "Platwnos 12", "geowAn@gmail.com", 987654321, "ZYX987", true));

        lockers.add(new Locker("1st Avenue", 10, true));
        lockers.add(new Locker("2nd Street", 15, true));
    }

    private static void createOrder(Scanner scanner) {
        Buyer buyer = selectOrCreateBuyer(scanner);
        List<Item> orderItems = selectItems(scanner);
        Driver driver = selectDriver(scanner);
        String deliveryAddress = "";
        Locker locker = null;

        System.out.println("Epilogi methodou paradosis (1 gia dieuthinsi, 2 gia locker):");
        int deliveryChoice = scanner.nextInt();
        scanner.nextLine();
        if (deliveryChoice == 1) {
            System.out.print("Eisagete dieuthinsi paradosis: ");
            deliveryAddress = scanner.nextLine();
        } else {
            locker = selectLocker(scanner);
            if (locker == null) {
                System.out.println("Den yparxoun diathesima lockers. H paraggelia apetixe.");
                return;
            }
        }

        Order order = new Order(buyer, new Date(), orderItems, deliveryAddress, driver, "ekkrempei", locker);
        orders.add(order);
        System.out.println("H paraggelia katagrafike epituxos me ID: " + order.orderId);
        System.out.println("Pelatis: " + order.buyer.name + " " + order.buyer.surname);
        System.out.println("Odigos: " + order.driver.name + " " + order.driver.surname);
        if (order.locker != null) {
            System.out.println("Locker dieuthinsi: " + order.locker.address);
        }
    }

    private static Buyer selectOrCreateBuyer(Scanner scanner) {
        System.out.println("Epilogi pelati (eisagete ton arithmo) i dimiourgia neou pelati (0):");
        for (int i = 0; i < buyers.size(); i++) {
            System.out.println((i + 1) + ". " + buyers.get(i).name + " " + buyers.get(i).surname);
        }
        int buyerChoice = scanner.nextInt();
        scanner.nextLine();
        if (buyerChoice == 0) {
            System.out.println("Eisagete stoixeia neou pelati:");
            System.out.print("Onoma: ");
            String name = scanner.nextLine();
            System.out.print("Epwnimo: ");
            String surname = scanner.nextLine();
            System.out.print("Dieuthinsi: ");
            String address = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();
            Buyer buyer = new Buyer(name, surname, address, email);
            buyers.add(buyer);
            return buyer;
        } else {
            return buyers.get(buyerChoice - 1);
        }
    }

    private static List<Item> selectItems(Scanner scanner) {
        List<Item> orderItems = new ArrayList<>();
        int itemChoice;
    
        do {
            System.out.println("Epilogi proion (eisagete ton arithmo) i oloklirwste tin epilogi proionton (0):");
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ". " + items.get(i).name + " (" + items.get(i).brand + ")");
            }
            itemChoice = scanner.nextInt();
            scanner.nextLine();
            if (itemChoice != 0) {
                orderItems.add(items.get(itemChoice - 1));
            }
        } while (itemChoice != 0);
    
        return orderItems;
    }
    

    private static Driver selectDriver(Scanner scanner) {
        System.out.println("Epilogi odigou (eisagete ton arithmo):");
        for (int i = 0; i < drivers.size(); i++) {
            System.out.println((i + 1) + ". " + drivers.get(i).name + " " + drivers.get(i).surname);
        }
        int driverChoice = scanner.nextInt();
        scanner.nextLine();
        return drivers.get(driverChoice - 1);
    }

    private static Locker selectLocker(Scanner scanner) {
        System.out.println("Epilogi locker (eisagete ton arithmo):");
        for (int i = 0; i < lockers.size(); i++) {
            if (lockers.get(i).available) {
                System.out.println((i + 1) + ". " + lockers.get(i).address);
            }
        }
        int lockerChoice = scanner.nextInt();
        scanner.nextLine();
        if (lockers.get(lockerChoice - 1).available) {
            lockers.get(lockerChoice - 1).available = false;
            return lockers.get(lockerChoice - 1);
        } else {
            return null;
        }
    }
}
