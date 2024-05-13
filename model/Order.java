package kz.medet.onlineshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "t_orders")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp orderDate;
    private String status;

    @ManyToOne
    private User buyer;

    @ManyToOne
    private User seller;

    @ManyToOne
    private Product product;

    private int quantity;
}
