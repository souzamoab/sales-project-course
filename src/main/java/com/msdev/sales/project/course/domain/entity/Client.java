package com.msdev.sales.project.course.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    private Integer id;
    private String name;

    public Client(String name) {
        this.name = name;
    }

}
