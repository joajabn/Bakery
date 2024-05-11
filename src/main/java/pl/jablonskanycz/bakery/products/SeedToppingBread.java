package pl.jablonskanycz.bakery.products;

public class SeedToppingBread extends Bread {
    private String seedTopping;
    private Bread bread;

    public SeedToppingBread(String seedTopping, Bread bread) {
        super(bread.getName(), bread.getPrice());
        this.seedTopping = seedTopping;
        this.bread = bread;
    }

    @Override
    public void baking() {
        System.out.println("Adding " + seedTopping);
        System.out.println("Preparing " + getName());
        this.bread.baking();
    }
}
