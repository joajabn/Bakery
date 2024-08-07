package pl.jablonskanycz.bakery.database.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.jablonskanycz.bakery.database.domain.AddressEntity;
import pl.jablonskanycz.bakery.database.mapper.AddressMapper;
import pl.jablonskanycz.bakery.database.models.AddressModel;
import pl.jablonskanycz.bakery.database.repositories.AddressRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class AddressServiceTest {
    @Autowired
    private AddressService addressService;

    @MockBean
    private AddressRepository addressRepository;

    @MockBean
    private AddressMapper addressMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnAllAddresses() {
        //given
        AddressEntity address1 = AddressEntity.builder().addressId(1L).latitude(51.20).longitude(15.80).build();
        AddressEntity address2 = AddressEntity.builder().addressId(2L).latitude(50.02).longitude(18.30).build();
        when(addressRepository.findAll()).thenReturn(Arrays.asList(address1, address2));

        AddressModel addressModel1 = AddressModel.builder().addressId(1L).latitude(51.20).longitude(15.80).build();
        AddressModel addressModel2 = AddressModel.builder().addressId(2L).latitude(50.02).longitude(18.30).build();
        when(addressMapper.map(address1)).thenReturn(addressModel1);
        when(addressMapper.map(address2)).thenReturn(addressModel2);

        //when
        List<AddressModel> addresses = addressService.getAllAddresses();

        //then
        assertNotNull(addresses);
        assertEquals(2, addresses.size());
        assertEquals(addressModel1, addresses.get(0));
        assertEquals(addressModel2, addresses.get(1));
//        assertEquals(address1.getAddressId(), addresses.get(0).getAddressId());
//        assertEquals(address2.getAddressId(), addresses.getLast().getAddressId());
//        assertEquals(address1.getLatitude(), addresses.getFirst().getLatitude());
//        assertEquals(address2.getLatitude(), addresses.getLast().getLatitude());
//        assertEquals(address1.getLongitude(), addresses.getFirst().getLongitude());
//        assertEquals(address2.getLongitude(), addresses.getLast().getLongitude());
    }

    @Test
    public void shouldAddAddress() {
        //given
        AddressEntity address = AddressEntity.builder().latitude(51.20).longitude(15.80).build();
        when(addressRepository.save(any(AddressEntity.class))).thenReturn(address);

        //when
        addressService.addAddress(51.20, 15.80);

        //then
        verify(addressRepository).save(any(AddressEntity.class));
    }

    @Test
    public void shouldUpdateAddress() {
        //given
        AddressModel addressModel = AddressModel.builder().addressId(1L).latitude(51.20).longitude(15.80).build();
        AddressEntity addressEntity = AddressEntity.builder().addressId(1L).latitude(51.20).longitude(15.80).build();

        when(addressMapper.map(addressModel)).thenReturn(addressEntity);
        when(addressRepository.findById(1L)).thenReturn(Optional.of(addressEntity));

        // when
        addressService.updateAddress(addressModel, 52.83, 16.34);

        // then
        verify(addressRepository).save(any(AddressEntity.class));
        assertEquals(52.83, addressEntity.getLatitude());
        assertEquals(16.34, addressEntity.getLongitude());
    }


    @Test
    public void shouldNotUpdateNonExistentAddress() {
        //given
        AddressModel addressModel = AddressModel.builder().addressId(1L).latitude(51.20).longitude(15.80).build();
        AddressEntity addressEntity = AddressEntity.builder().addressId(1L).latitude(51.20).longitude(15.80).build();
        when(addressMapper.map(addressModel)).thenReturn(addressEntity);
        when(addressRepository.findById(1L)).thenReturn(Optional.empty());

        //when
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            addressService.updateAddress(addressModel, 52.83, 16.34);
        });

        //then
        assertEquals("Given address does not exist", exception.getMessage());

    }

    @Test
    public void shouldDeleteAddress() {
        //given
        AddressEntity addressEntity = AddressEntity.builder().addressId(1L).latitude(51.20).longitude(15.80).build();
        AddressModel addressModel = AddressModel.builder().addressId(1L).latitude(51.20).longitude(15.80).build();

        when(addressMapper.map(addressModel)).thenReturn(addressEntity);
        when(addressRepository.findById(1L)).thenReturn(Optional.of(addressEntity));
        doNothing().when(addressRepository).deleteById(1L);

        //when
        addressService.deleteAddress(addressModel);

        //then
        verify(addressRepository).deleteById(1L);
    }
}