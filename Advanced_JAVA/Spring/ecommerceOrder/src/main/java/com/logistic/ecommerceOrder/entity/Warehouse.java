package com.logistic.ecommerceOrder.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private Integer capacity;

    @JsonIgnore
    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL)
    private List<Shipment> shipments;
}