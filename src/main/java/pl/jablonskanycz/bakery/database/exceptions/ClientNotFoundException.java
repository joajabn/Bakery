package pl.jablonskanycz.bakery.database.exceptions;

public class ClientNotFoundException extends IllegalArgumentException{
    public ClientNotFoundException(String message) {
        super(message);
    }
}
