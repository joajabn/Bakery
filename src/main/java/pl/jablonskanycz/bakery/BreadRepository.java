package pl.jablonskanycz.bakery;

import java.util.List;

public interface BreadRepository {
    List<Bread> findAll();
    Bread findByName(String name);
    void addBread(Bread breadToAdd);
    void deleteBread(Bread breadToRemove);


}
