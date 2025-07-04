package pl.jablonskanycz.bakery.database.dto;

import lombok.*;
import pl.jablonskanycz.bakery.database.domain.ProductType;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProductDTO {
    private Long productId;
    private String productName;
    private Double price;
    private ProductType productType;

    @Override
    public String toString() {
        return productId + " , " + productName + " , " + price + " , " + productType;
    }

}
