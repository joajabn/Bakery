package pl.jablonskanycz.bakery.products.bread;

public class BlackSeedBreadFactory implements BreadFactory {
    @Override
    public Bread bakeBread() {
        return new Bread("Bread with black seed", 14.5);
    }
}
