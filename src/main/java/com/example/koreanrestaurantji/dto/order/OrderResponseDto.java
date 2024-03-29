package com.example.koreanrestaurantji.dto.order;

import com.example.koreanrestaurantji.domain.Orders;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@NoArgsConstructor
public class OrderResponseDto {
    private long orderNumber;
    private String createdDate;
    List<OrderDishDetailResponse> orderDishList;
    private String orderStatus;
    private int orderPrice;

    public OrderResponseDto(Orders orders, List<OrderDishDetailResponse> orderDishList){
        this.orderNumber = orders.getOrderNumber();
        this.createdDate = orders.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.orderDishList = orderDishList;
        this.orderStatus = orders.getOrderStatus();

        int totalPrice = 0;
        for(OrderDishDetailResponse dish : orderDishList){
            totalPrice += dish.getOrderDishPrice();
        }
        this.orderPrice = totalPrice;

    }
}
