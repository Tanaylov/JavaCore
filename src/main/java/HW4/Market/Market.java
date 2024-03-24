package HW4.Market;

import HW4.Customer.Customer;
import HW4.Product.Product;
import HW4.SaveLoad.Load.LoadFromFile;
import HW4.SaveLoad.Save.SaveToFile;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Market {
    private static final float MINIMUM_DISCOUNT = 0.90f;
    private static final float MEDIUM_DISCOUNT = 0.85f;
    private static final float MAX_DISCOUNT = 0.80f;
    private static boolean customerIsExist = false;

    private static HashMap<Customer, HashSet<Order>> customersAndTheirOrders = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void start() {
        int choice;
        if (LoadFromFile.load() != null)
            customersAndTheirOrders = (HashMap<Customer, HashSet<Order>>) LoadFromFile.load();
        do {
            choice = choice();
            if (choice == 1) {
                Customer currentCustomer = getCustomer();

                List<Product> currentProductList = getProducts();

                Order currentOrder = getOrder(currentCustomer, currentProductList);

                if (customerIsExist) {
                    customersAndTheirOrders.get(currentCustomer).add(currentOrder);
                } else {
                    HashSet<Order> orders = new HashSet<>(10);
                    orders.add(currentOrder);
                    customersAndTheirOrders.put(currentCustomer, orders);
                }

            }
        }
        while (choice != 0);
        SaveToFile.save(customersAndTheirOrders);
    }

    //region get parameter for customer

    private static String getSurname() {
        System.out.println("Enter  your surname (enter at least three characters):");
        String surname = scanner.nextLine();
        while (surname.length() < 3) {
            System.out.println("You enter too short surname, try again");
            surname = scanner.nextLine();
        }
        return surname;
    }

    private static long getTelephone() {
        System.out.println("Enter your telephone number (xxx-xxx-xx-xx - only numbers):");
        while (true) {
            try {
                long telephone = scanner.nextLong();
                if (telephone / 1_000_000_000 == 0 || telephone / 1_000_000_000 > 10) {
                    System.out.println("You enter incorrect number, your number must be 10 digits:");
                    continue;
                }
                return telephone;
            } catch (NumberFormatException e) {
                System.out.println("You must enter only numbers");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Your number too long");
                scanner.nextLine();
            }
        }
    }

    private static Customer.Gender getGender() {
        System.out.println("Enter your gender (f or m):");
        String gender;
        while (true) {
            gender = scanner.nextLine();
            if (gender.equals("f") || gender.equals("m")) break;
            System.out.println("Enter correct data:");
        }
        if (gender.equals("f")) return Customer.Gender.FEMALE;
        return Customer.Gender.MALE;
    }

    private static LocalDate getDate() {
        System.out.println("Enter your birthday(dd.mm.yyyy):");
        LocalDate result;
        while (true) {
            try {
                String[] birthday = scanner.nextLine().split("\\.");
                result = LocalDate.of(Integer.parseInt(birthday[2]),
                        Integer.parseInt(birthday[1]),
                        Integer.parseInt(birthday[0]));
                if (result.getYear() > LocalDate.now().getYear()
                        || result.getYear() < LocalDate.now().getYear() - 100) {
                    System.out.println("You enter incorrect year of birth");
                    continue;
                }
                return result;
            } catch (Exception e) {
                System.out.println("You enter incorrect data");
            }
        }
    }
    //endregion get parameter for customer

    //region get parameters for market
    private static List<Product> getProducts() {
        System.out.println("Add item to your order using item ID or enter 0 - to finish: ");
        Product.printProductList();
        List<Product> customersProduct = new ArrayList<>();
        int productID = Product.getProductList().size();
        while (productID != 0) {
            try {
                productID = scanner.nextInt();
                if (productID <= Product.getProductList().size() && productID > 0) {
                    customersProduct.add(Product.getProductList().get(productID - 1));
                    System.out.println("You add next item: ");
                    System.out.println(Product.getProductList().get(productID - 1));
                } else if (productID != 0) System.out.println("You try to add non-existent item.");
            } catch (NumberFormatException e) {
                System.out.println("You enter not a number");
            }

        }
        return customersProduct;
    }

    private static Order getOrder(Customer customer, List<Product> products) {
        float sum = 0;
        for (Product product : products) sum += product.getPrice();
        sum = Math.round(checkDiscount(sum, customer) * 100f) / 100f;
        Order order = new Order(sum, LocalDate.now(), customer, products);
//        orders.add(order);
        return order;
    }

    private static Customer getCustomer() {
        String surname = getSurname();
        int count = 0;
        for (Customer customer : customersAndTheirOrders.keySet()) {
            if (customer.getSurname().equals(surname)) {
                count++;
                System.out.println(customer);
            }
        }
        if (count != 0) {
            System.out.println("Select yourself from the customer list by entering ID or enter 0 to complete the data");
            String id = scanner.nextLine();
            while (!id.equals("0")) {
                    for (Customer customer : customersAndTheirOrders.keySet()) {
                        if (customer.getId().equals(id)) {
                            customerIsExist = true;
                            return customer;
                        }
                    }
                    System.out.println("You enter incorrect ID. Try again or enter 0");
                    id = scanner.nextLine();
                }
        }
        return new Customer(surname, getGender(), getDate(), getTelephone());
    }

    //endregion get parameters for market
    private static float checkDiscount(float sum, Customer customer) {
        for (Holiday value : Holiday.values()) {
            switch (value) {
                case NEW_YEAR:
                    for (LocalDate date : value.getDates()) {
                        if (date.isEqual(LocalDate.now())) sum *= MAX_DISCOUNT;
                    }
                    break;
                case DAY8:
                    if (customer.getGender().equals(Customer.Gender.FEMALE)
                            && LocalDate.now().isEqual(Holiday.DAY8.getDate())) sum *= MEDIUM_DISCOUNT;
                    break;
                case DAY23:
                    if (customer.getGender().equals(Customer.Gender.MALE)
                            && LocalDate.now().isEqual(Holiday.DAY23.getDate())) sum *= MEDIUM_DISCOUNT;
                    break;
                default:
                    if (customer.getBirthDate().getDayOfMonth() == LocalDate.now().getDayOfMonth()
                            && customer.getBirthDate().getMonthValue() == LocalDate.now().getMonthValue())
                        sum *= MINIMUM_DISCOUNT;
            }
        }
        return sum;
    }

    private static int choice() {
        System.out.println("You want to make an order (enter '1' to YES '0' to EXIT)?");
        while (true)
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                if (choice == 0 || choice == 1) return choice;
                System.out.println("You need to choose '1' or '0'");
            } catch (NumberFormatException e) {
                System.out.println("You enter not a number");
            }
    }


    private static final class Order implements Serializable {

        private String identifier;
        private float sum;
        private LocalDate orderDate;
        private Customer customer;
        private List<Product> products;
        private short productQuantity;

        private Order(float sum, LocalDate orderDate, Customer customer, List<Product> products) {
            this.identifier = customer.getId() + orderDate.getDayOfMonth() + orderDate.getMonthValue() + sum;
            this.sum = sum;
            this.orderDate = orderDate;
            this.customer = customer;
            this.products = products;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Order order = (Order) o;
            return Float.compare(order.sum, sum) == 0
                    && Objects.equals(identifier, order.identifier)
                    && Objects.equals(orderDate, order.orderDate)
                    && Objects.equals(customer, order.customer)
                    && Objects.equals(products, order.products);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(identifier, sum, orderDate, customer);
            result = 31 * result + Objects.hashCode(products);
            return result;
        }

        @Override
        public String toString() {
            return "Order ID: " + identifier +
                    "\nsum: " + sum +
                    "\norderDate:" + orderDate +
                    "\ncustomer: " + customer +
                    "\nproducts=" + products;
        }
    }
}
