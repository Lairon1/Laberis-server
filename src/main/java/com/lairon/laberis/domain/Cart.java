package com.lairon.laberis.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Cart {

    @Id
    @NonNull
    private String ownerLogin;
    private double finalCost;
    @OneToMany
    private List<Product> products;



}
