package pl.jablonskanycz.bakery.database.exceptions;

public class OrderNotFoundException extends IllegalArgumentException{
    public OrderNotFoundException(String message) {
        super(message);
    }
}
