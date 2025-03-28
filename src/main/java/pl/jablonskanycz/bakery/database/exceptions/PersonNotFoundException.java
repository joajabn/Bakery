package pl.jablonskanycz.bakery.database.exceptions;

public class PersonNotFoundException extends IllegalArgumentException {
    public PersonNotFoundException(String message) {
        super(message);
    }
}
