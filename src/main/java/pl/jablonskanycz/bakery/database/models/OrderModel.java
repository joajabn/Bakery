package pl.jablonskanycz.bakery.database.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.jablonskanycz.bakery.database.domain.ClientEntity;

import java.time.Instant;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderModel {
    private Long orderId;
    private Instant orderDate;
    private ClientModel client;
    private List<ProductModel> productsInThisOrder;

    @Override
    public String toString() {
        return orderId + " , " + orderDate + " , " + client + " , " + productsInThisOrder;
    }
}
