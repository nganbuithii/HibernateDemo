package com.btn.pojo;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="category")
@Data
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
}
