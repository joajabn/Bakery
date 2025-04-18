package pl.jablonskanycz.bakery.database.mapper;

import org.springframework.stereotype.Component;
import pl.jablonskanycz.bakery.database.domain.OrderEntity;
import pl.jablonskanycz.bakery.database.models.OrderModel;

import java.util.stream.Collectors;


@Component
public class OrderMapper {
    private ClientMapper clientMapper;
    private ProductMapper productMapper;

    public OrderModel map(OrderEntity orderEntity) {
        if (orderEntity == null) {
            return null;
        }
        OrderModel orderModel = new OrderModel();
        return orderModel.builder()
                .orderId(orderEntity.getOrderId())
                .orderDate(orderEntity.getOrderDate())
                .client(clientMapper.map(orderEntity.getClient()))
                .productsInThisOrder(orderEntity.getProductsInThisOrder().stream()
                        .map(productMapper::map)
                        .collect(Collectors.toList()))
                .build();
    }

    public OrderEntity map(OrderModel orderModel) {
        if (orderModel == null) {
            return null;
        }
        OrderEntity orderEntity = new OrderEntity();
        return orderEntity.builder().build();
    }
}
