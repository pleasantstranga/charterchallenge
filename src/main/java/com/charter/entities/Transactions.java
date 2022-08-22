package com.charter.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transactions {
    @Column(nullable=false)
    private @Id @GeneratedValue Long id;
    private Integer amount;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false,insertable = false, updatable = false)
    private Customer customer;

}