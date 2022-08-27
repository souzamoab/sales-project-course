package com.msdev.sales.project.course.api.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDataDTO {

    private Integer orderId;
    private String cpf;
    private String clientName;
    private BigDecimal total;
    private String orderDate;
    private String orderStatus;
    private List<OrderItemDataDTO> items;

}
