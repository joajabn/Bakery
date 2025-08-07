package pl.jablonskanycz.bakery.database.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductTypeModel {

    private Long productTypeId;
    private String productType;

    @Override
    public String toString() {
        return productTypeId + ", " + productType;
    }
}
