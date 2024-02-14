package com.quicksilvarad.orderservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {

    private String orderId;
    private String name;

    private Long price;

    private int qty;
}
