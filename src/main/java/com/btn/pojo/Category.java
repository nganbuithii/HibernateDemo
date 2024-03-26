package com.btn.pojo;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="category")
@Data
@NamedQueries({
        @NamedQuery(name ="Category.findAll", query = "SELECT C FROM Category C"),
})
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;

    // Cau hinh nguoc
    @OneToMany(mappedBy = "category")
    private Set<Product> products;
}
