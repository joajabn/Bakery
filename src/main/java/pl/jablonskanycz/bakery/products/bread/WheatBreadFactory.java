package pl.jablonskanycz.bakery.products.bread;

public class WheatBreadFactory implements BreadFactory {
    @Override
    public Bread bakeBread() {
        return new Bread("Wheat bread", 12);
    }
}
