package pl.jablonskanycz.bakery.database.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.jablonskanycz.bakery.database.domain.AddressEntity;
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

    @Test
    public void shouldReturnAllAddresses() {
        //given
        AddressEntity address1 = AddressEntity.builder().addressId(1L).latitude(51.20).longitude(15.80).build();
        AddressEntity address2 = AddressEntity.builder().addressId(2L).latitude(50.02).longitude(18.30).build();
        when(addressRepository.findAll()).thenReturn(Arrays.asList(address1, address2));

        //when
        List<AddressEntity> addresses = addressService.getAllAddresses();

        //then
        assertNotNull(addresses);
        assertEquals(2, addresses.size());
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
        AddressEntity address = AddressEntity.builder().addressId(1L).latitude(51.20).longitude(15.80).build();
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));

        //when
        addressService.updateAddress(1L, 52.83, 16.34);

        //then
        verify(addressRepository).save(any(AddressEntity.class));
        assertEquals(52.83, address.getLatitude());
        assertEquals(16.34, address.getLongitude());
    }

    @Test
    public void shouldNotUpdateNonExistentAddress() {
        //given
        when(addressRepository.findById(1L)).thenReturn(Optional.empty());

        //when
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            addressService.updateAddress(1L, 52.83, 16.34);
        });

        //then
        assertEquals("Address with given id does not exist", exception.getMessage());

    }

    @Test
    public void shouldDeleteAddress() {
        //given
        doNothing().when(addressRepository).deleteById(1L);

        //when
        addressService.deleteAddress(1L);

        //then
        verify(addressRepository).deleteById(1L);
    }
}