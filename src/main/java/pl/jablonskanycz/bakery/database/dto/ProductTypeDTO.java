package pl.jablonskanycz.bakery.database.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProductTypeDTO {
    private Long productTypeId;
    private String productType;

    @Override
    public String toString() {
        return productTypeId + ", " + productType;
    }
}
