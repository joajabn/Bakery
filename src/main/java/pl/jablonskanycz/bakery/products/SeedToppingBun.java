package pl.jablonskanycz.bakery.products;

public class SeedToppingBun extends Bun {
    private String seedTopping;
    private Bun bun;

    public SeedToppingBun(String topping, Bun bun) {
        super(bun.getName(), bun.getPrice());
        this.seedTopping = topping;
        this.bun = bun;
    }

    @Override
    public void baking() {
        System.out.println("Adding " + seedTopping);
        System.out.println("Preparing " + getName());
        this.bun.baking();
    }
}
