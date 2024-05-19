package pl.jablonskanycz.bakery.products.bun;

public class SesameBunFactory implements BunFactory {

    @Override
    public Bun bakeBun() {
        return new Bun("Sesame bun", 8);
    }
}
