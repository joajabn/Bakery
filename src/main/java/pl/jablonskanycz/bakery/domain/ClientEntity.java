package pl.jablonskanycz.bakery.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clients")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ClientEntity {

    @Id
    @SequenceGenerator(name = "clients_seq", sequenceName = "clients_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_seq")
    @Column(name = "client_id")
    private Long clientId;

    @OneToOne
    @JoinColumn(name = "client_id", referencedColumnName = "client_id")
    private PersonEntity person;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private AddressEntity address;

}
