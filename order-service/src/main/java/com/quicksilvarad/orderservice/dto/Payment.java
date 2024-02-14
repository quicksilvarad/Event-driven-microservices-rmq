package com.quicksilvarad.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Payment {
private String name;
private String email;
private String payment_type;
private double amount;
}
