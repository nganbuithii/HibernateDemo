package com.btn.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="product")
@Data
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private double price;
    private String image;

    @Column(name="created_date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    private boolean active;
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
}
