package pl.jablonskanycz.bakery;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ListBasedBreadRepository implements BreadRepository {
    private List<Bread> breads = new ArrayList<>();

    @Override
    public List<Bread> findAll() {
        return breads;
    }

    @Override
    public Bread findByName(String name) {
        return breads.stream()
                .filter(bread -> name.equals(bread.getName()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No such bread"));
    }

    @Override
    public void addBread(Bread breadToAdd) {
        breads.add(breadToAdd);
    }

    @Override
    public void deleteBread(Bread bread) {
        if (!breads.isEmpty()) {
            breads.remove(bread);
        } else {
            throw new NoSuchElementException("Nothing to delete - list of breads is empty!");
        }
    }
}
