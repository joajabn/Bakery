package pl.jablonskanycz.bakery.products.bread;

import pl.jablonskanycz.bakery.products.bread.Bread;
import pl.jablonskanycz.bakery.products.bread.BreadFactory;

public class WholegrainBreadFactory implements BreadFactory {

    @Override
    public Bread bakeBread() {
        return new Bread("Wholegrain bread", 13);
    }
}
