package pl.jablonskanycz.bakery.database.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;
import pl.jablonskanycz.bakery.database.domain.ProductTypeEntity;
import pl.jablonskanycz.bakery.database.dto.ProductDTO;
import pl.jablonskanycz.bakery.database.dto.ProductTypeDTO;
import pl.jablonskanycz.bakery.database.models.ProductTypeModel;

@Component
//@Mapper(componentModel = "spring")
public class ProductTypeMapper {

//    ProductTypeEntity toEntity(ProductTypeModel productTypeModel);
//
//    ProductTypeModel toModel(ProductTypeEntity productTypeEntity);
//
//    ProductTypeModel toModel(ProductTypeDTO productTypeDTO);
//
//    ProductTypeDTO toDTO(ProductTypeModel productTypeModel);

    public ProductTypeEntity toEntity(ProductTypeModel productTypeModel) {
        if (productTypeModel == null) {
            return null;
        }

        ProductTypeEntity productTypeEntity = new ProductTypeEntity();
        return productTypeEntity.toBuilder()
                .productTypeId(productTypeModel.getProductTypeId())
                .productType(productTypeModel.getProductType())
                .build();
    }

    public ProductTypeModel toModel(ProductTypeEntity productTypeEntity){
        if (productTypeEntity == null) {
            return null;
        }

        ProductTypeModel productTypeModel = new ProductTypeModel();
        return productTypeModel.builder()
                .productTypeId(productTypeEntity.getProductTypeId())
                .productType(productTypeEntity.getProductType())
                .build();
    }

    public ProductTypeModel toModel(ProductTypeDTO productTypeDTO){
        if(productTypeDTO == null){
            return null;
        }

        ProductTypeModel productTypeModel = new ProductTypeModel();
        return productTypeModel.builder()
                .productTypeId(productTypeDTO.getProductTypeId())
                .productType(productTypeDTO.getProductType())
                .build();
    }

    public ProductTypeDTO toDTO(ProductTypeModel productTypeModel){
        if(productTypeModel == null){
            return null;
        }

        ProductTypeDTO productTypeDTO = new ProductTypeDTO();
        return productTypeDTO.builder()
                .productTypeId(productTypeModel.getProductTypeId())
                .productType(productTypeModel.getProductType())
                .build();
    }
}
