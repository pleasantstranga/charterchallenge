package com.charter.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
    @Column(name = "customer_id")
    private @Id @GeneratedValue Long id;
    private String name;
    @OneToMany(mappedBy = "customer")
    private Set<Transactions> transactions;

}