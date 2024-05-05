package pl.jablonskanycz.bakery.products;

public class FruitBun extends Bun {
    private String fruit;
    private Bun bun;

    public FruitBun(String fruit, Bun bun) {
        super(bun.getName(), bun.getPrice());
        this.fruit = fruit;
    }

    @Override
    public void baking() {
        System.out.println("Adding " + fruit);
        System.out.println("Preparing " + getName());
        this.bun.baking();
    }
}
