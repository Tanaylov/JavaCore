package HW4.Exception;

public class ProductException extends Exception {
    private String message = "";
    public ProductException(String message) {
        super(message);
    }
}
