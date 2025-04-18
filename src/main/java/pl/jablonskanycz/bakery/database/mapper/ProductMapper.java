package pl.jablonskanycz.bakery.database.mapper;

import org.springframework.stereotype.Component;
import pl.jablonskanycz.bakery.database.domain.ProductEntity;
import pl.jablonskanycz.bakery.database.models.ProductModel;


@Component
public class ProductMapper {
    public ProductModel map(ProductEntity productEntity){
        if (productEntity == null){
            return null;
        }
        ProductModel productModel = new ProductModel();
        return productModel.builder()
                .productId(productEntity.getProductId())
                .productName(productEntity.getProductName())
                .price(productEntity.getPrice())
                .productType(productEntity.getProductType())
                .build();
    }

    public ProductEntity map(ProductModel productModel){
        if (productModel == null){
            return null;
        }
        ProductEntity productEntity = new ProductEntity();
        return productEntity.builder()
                .productId(productModel.getProductId())
                .productName(productModel.getProductName())
                .price(productModel.getPrice())
                .productType(productModel.getProductType())
                .build();
    }
}
