package pl.jablonskanycz.bakery.database.mapper;

import org.springframework.stereotype.Component;
import pl.jablonskanycz.bakery.database.domain.AddressEntity;
import pl.jablonskanycz.bakery.database.models.AddressModel;


@Component
public class AddressMapper {

    public AddressModel map(AddressEntity addressEntity){
        if (addressEntity == null){
            return null;
        }
        return AddressModel.builder()
                .addressId(addressEntity.getAddressId())
                .latitude(addressEntity.getLatitude())
                .longitude(addressEntity.getLongitude())
                .build();
    }

    public AddressEntity map(AddressModel addressModel){
        if (addressModel == null){
            return null;
        }
        return AddressEntity.builder()
//                .addressId(addressModel.getAddressId())
                .latitude(addressModel.getLatitude())
                .longitude(addressModel.getLongitude())
                .build();
    }
}
