package HW4.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Product implements Serializable {
    private int id;
    private static int counter;
    private String name;
    private float price;

    private static List<Product> productList = new ArrayList<>(Arrays.asList(
            new Product("Milk", 56.5f), new Product("Bread", 36.7f),
            new Product("Chocolate", 77.9f), new Product("Pasta", 66.7f),
            new Product("Salt", 44.1f), new Product("Water", 35.5f)));

    public Product(String name, float price) {
        this.name = name;
        this.price = price;
        id = ++counter;
    }

    public float getPrice() {return price;}

    public static List<Product> getProductList() {return productList;}

    public static void printProductList() {
        productList.forEach(el -> System.out.println(String.format("Product #%d: %s - %.2f", el.id, el.name, el.price)));
    }

    @Override
    public String toString() {
        return "Product: " +
                "id: " + id +
                "; name: " + name +
                "; price: " + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Float.compare(product.price, price) == 0 && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }
}
