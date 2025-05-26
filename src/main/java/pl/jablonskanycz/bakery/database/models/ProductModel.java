package pl.jablonskanycz.bakery.database.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.jablonskanycz.bakery.database.domain.ProductType;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductModel {
    private Long productId;
    private String productName;
    private Double price;
    private ProductType productType;

    @Override
    public String toString() {
        return productId + " , " + productName + " , " + price + " , " + productType;
    }
}
