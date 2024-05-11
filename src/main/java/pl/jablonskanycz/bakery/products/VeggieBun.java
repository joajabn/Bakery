package pl.jablonskanycz.bakery.products;

public class VeggieBun extends Bun {
    private String vegetable;
    private Bun bun;

    public VeggieBun(String vegetable, Bun bun) {
        super(bun.getName(), bun.getPrice());
        this.vegetable = vegetable;
        this.bun = bun;
    }

    @Override
    public void baking() {
        System.out.println("Adding " + vegetable);
        System.out.println("Preparing " + getName());
        this.bun.baking();
    }
}
