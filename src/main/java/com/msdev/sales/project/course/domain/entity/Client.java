package com.msdev.sales.project.course.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", length = 100)
    private String name;

    @OneToMany(mappedBy = "client")
    private Set<Order> orders;

    public Client(String name) {
        this.name = name;
    }

}
