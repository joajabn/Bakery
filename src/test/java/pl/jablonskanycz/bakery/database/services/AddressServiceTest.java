package pl.jablonskanycz.bakery.database.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.jablonskanycz.bakery.database.domain.AddressEntity;
import pl.jablonskanycz.bakery.database.exceptions.AddressNotFoundException;
import pl.jablonskanycz.bakery.database.mapper.AddressMapper;
import pl.jablonskanycz.bakery.database.models.AddressModel;
import pl.jablonskanycz.bakery.database.repositories.AddressRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class AddressServiceTest {
    @Autowired
    private AddressService addressService;

    @MockBean
    private AddressRepository addressRepository;

    @MockBean
    private AddressMapper addressMapper;

    private final AddressEntity addressEntity1 = createAddressEntity(1L);
    private final AddressEntity addressEntity2 = createAddressEntity(2L);
    private final AddressModel addressModel1 = createAddressModel(1L);
    private final AddressModel addressModel2 = createAddressModel(2L);

    @Test
    public void shouldReturnAllAddresses() {
        //given
        when(addressRepository.findAll()).thenReturn(List.of(addressEntity1, addressEntity2));
        when(addressMapper.map(addressEntity1)).thenReturn(addressModel1);
        when(addressMapper.map(addressEntity2)).thenReturn(addressModel2);

        //when
        List<AddressModel> addresses = addressService.getAllAddresses();

        //then
        assertNotNull(addresses);
        assertEquals(2, addresses.size());
        assertEquals(addressModel1, addresses.get(0));
        assertEquals(addressModel2, addresses.get(1));
    }

    private static AddressModel createAddressModel(long addressId) {
        return AddressModel.builder().addressId(addressId).latitude(51.20).longitude(15.80).build();
    }

    private static AddressEntity createAddressEntity(long addressId) {
        return AddressEntity.builder().addressId(addressId).latitude(51.20).longitude(15.80).build();
    }

    @Test
    public void shouldFindAddressById(){
        //given
        long addressId = 1L;
        when(addressRepository.findById(addressId)).thenReturn(Optional.of(addressEntity1));
        when(addressMapper.map(addressEntity1)).thenReturn(addressModel1);

        //when
        AddressModel addressFound = addressService.findAddressById(addressId);

        //then
        assertNotNull(addressFound);
        assertEquals(addressModel1.getAddressId(), addressEntity1.getAddressId());
        assertEquals(addressModel1.getLatitude(), addressEntity1.getLatitude());
        assertEquals(addressModel1.getLongitude(), addressEntity1.getLongitude());
    }
    @Test
    public void shouldAddAddress() {
        //given
        when(addressRepository.save(any(AddressEntity.class))).thenReturn(addressEntity1);
        when(addressMapper.map(addressModel1)).thenReturn(addressEntity1);

        //when
        addressService.addAddress(addressModel1);

        //then
        verify(addressRepository).save(any(AddressEntity.class));
    }

    @Test
    public void shouldUpdateAddress() {
        //given
        long addressId = 1L;
        when(addressRepository.findById(addressId)).thenReturn(Optional.of(addressEntity1));
        when(addressMapper.map(addressModel1)).thenReturn(addressEntity1);

        // when
        addressService.updateAddress(addressModel1, 52.83, 16.34);

        // then
        verify(addressRepository).save(any(AddressEntity.class));
        assertEquals(52.83, addressEntity1.getLatitude());
        assertEquals(16.34, addressEntity1.getLongitude());
    }


    @Test
    public void shouldIgnoreNonExistentAddress() {
        //given
        long addressId = 1L;
        when(addressRepository.findById(addressId)).thenReturn(Optional.empty());
        when(addressMapper.map(addressModel1)).thenReturn(addressEntity1);

        //when
        Exception exception = assertThrows(AddressNotFoundException.class, () -> {
            addressService.updateAddress(addressModel1, 52.83, 16.34);
        });

        //then
        assertEquals("Address not found", exception.getMessage());

    }

    @Test
    public void shouldDeleteAddress() {
        //given
        long addressIdToDelete = 1L;

        //when
        addressService.deleteAddress(addressIdToDelete);

        //then
        verify(addressRepository).deleteById(addressIdToDelete);
    }
}