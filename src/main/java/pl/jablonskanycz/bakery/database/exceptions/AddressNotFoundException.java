package pl.jablonskanycz.bakery.database.exceptions;

public class AddressNotFoundException extends IllegalArgumentException{
    public AddressNotFoundException(String message) {
        super(message);
    }
}
