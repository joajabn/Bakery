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
@Mapper(componentModel = "spring")
public interface ProductTypeMapper {

    @Mappings(
            {
                    @Mapping(source = "productTypeId", target = "productTypeId"),
                    @Mapping(source = "productType", target = "productType")
            })
    ProductTypeEntity toEntity(ProductTypeModel productTypeModel);

    ProductTypeModel toModel(ProductTypeEntity productTypeEntity);

    ProductTypeModel toModel(ProductTypeDTO productTypeDTO);

    ProductTypeDTO toDTO(ProductTypeModel productTypeModel);
}
