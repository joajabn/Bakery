package pl.jablonskanycz.bakery.database.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import pl.jablonskanycz.bakery.database.domain.ProductEntity;
import pl.jablonskanycz.bakery.database.dto.ProductDTO;
import pl.jablonskanycz.bakery.database.models.ProductModel;

@Component
@Mapper(componentModel = "spring")
public interface ProductMapper {
   ProductModel toModel(ProductEntity productEntity);
   ProductEntity toEntity(ProductModel productModel);
   ProductModel toModel(ProductDTO productDTO);
   ProductDTO toDTO(ProductModel productModel);

}
