package pl.jablonskanycz.bakery.products.bun;

public class RhubarbBunFactory implements BunFactory {

    @Override
    public Bun bakeBun() {
        return new Bun("Rhubarb bun", 10.5);
    }
}
