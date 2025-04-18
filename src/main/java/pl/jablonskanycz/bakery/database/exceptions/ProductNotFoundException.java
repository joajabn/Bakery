package pl.jablonskanycz.bakery.database.exceptions;

public class ProductNotFoundException extends IllegalArgumentException{
    public ProductNotFoundException(String message) {
        super(message);
    }
}
