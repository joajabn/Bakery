package pl.jablonskanycz.bakery.products.bun;

public class AsparagusBun implements BunFactory {

    @Override
    public Bun bakeBun() {
        return new Bun("Asparagus bun", 13.5);
    }
}
