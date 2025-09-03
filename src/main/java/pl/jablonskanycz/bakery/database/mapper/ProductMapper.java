package pl.jablonskanycz.bakery.database.mapper;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jablonskanycz.bakery.database.domain.ProductEntity;
import pl.jablonskanycz.bakery.database.dto.ProductDTO;
import pl.jablonskanycz.bakery.database.models.ProductModel;

@Component
//@Mapper(componentModel = "spring")
public class ProductMapper {
//   ProductModel toModel(ProductEntity productEntity);
//   ProductEntity toEntity(ProductModel productModel);
//   ProductModel toModel(ProductDTO productDTO);
//   ProductDTO toDTO(ProductModel productModel);
   @Autowired
   private ProductTypeMapper productTypeMapper;

   public ProductModel toModel(ProductEntity productEntity) {
      if(productEntity == null) {
         return null;
      }

      ProductModel productModel = new ProductModel();
      return productModel.builder()
              .productId(productEntity.getProductId())
              .productName(productEntity.getProductName())
              .price(productEntity.getPrice())
              .productTypeModel(productTypeMapper.toModel(productEntity.getProductType()))
              .build();
   }

   public ProductEntity toEntity(ProductModel productModel){
      if(productModel == null){
         return null;
      }

      ProductEntity productEntity = new ProductEntity();
      return productEntity.toBuilder()
              .productId(productModel.getProductId())
              .productName(productModel.getProductName())
              .price(productModel.getPrice())
              .productType(productTypeMapper.toEntity(productModel.getProductTypeModel()))
              .build();
   }

   public ProductModel toModel(ProductDTO productDTO){
      if(productDTO == null){
         return null;
      }

      ProductModel productModel = new ProductModel();
      return productModel.builder()
              .productId(productDTO.getProductId())
              .productName(productDTO.getProductName())
              .price(productDTO.getPrice())
              .productTypeModel(productTypeMapper.toModel(productDTO.getProductType()))
              .build();
   }

   public ProductDTO toDTO(ProductModel productModel){
      if(productModel == null){
         return null;
      }

      ProductDTO productDTO = new ProductDTO();
      return productDTO.builder()
              .productId(productModel.getProductId())
              .productName(productModel.getProductName())
              .price(productModel.getPrice())
              .productType(productTypeMapper.toDTO(productModel.getProductTypeModel()))
              .build();
   }

}
