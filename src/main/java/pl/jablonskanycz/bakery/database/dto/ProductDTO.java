package pl.jablonskanycz.bakery.database.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProductDTO {
    private Long productId;
    private String productName;
    private Double price;
    private ProductTypeDTO productType;

    @Override
    public String toString() {
        return productId + " , " + productName + " , " + price + " , " + productType;
    }

}

