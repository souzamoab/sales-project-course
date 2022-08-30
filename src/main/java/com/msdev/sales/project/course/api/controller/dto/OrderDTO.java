package com.msdev.sales.project.course.api.controller.dto;

import com.msdev.sales.project.course.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    @NotNull(message = "{field.client.id.not.null}")
    private Integer clientId;

    @NotNull(message = "{field.total.not.null}")
    private BigDecimal total;

    @NotEmptyList(message = "{order.must.have.items}")
    private List<OrderItemDTO> items;

}
