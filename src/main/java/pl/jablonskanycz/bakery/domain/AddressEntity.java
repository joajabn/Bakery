package pl.jablonskanycz.bakery.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddressEntity {
    @Id
    @SequenceGenerator(name = "address_seq", sequenceName = "address_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

}
