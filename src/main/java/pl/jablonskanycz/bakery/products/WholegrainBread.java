package pl.jablonskanycz.bakery.products;

public class WholegrainBread extends Bread{
    private String grain;
    private Bread bread;

    public WholegrainBread(String grain, Bread bread) {
        super(bread.getName(), bread.getPrice());
        this.grain = grain;
    }

    @Override
    public void baking() {
        System.out.println("Adding " + grain);
        System.out.println("Preparing " + getName());
        this.bread.baking();
    }
}
