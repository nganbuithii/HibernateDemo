/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.btn.hibernatedemo;

import com.btn.pojo.Category;
import com.btn.repository.impl.ProductRepositoryImpl;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;

/**
 * @author FShop
 */
public class HibernateDemo {

    public static void main(String[] args) {
//        try (Session s = HibernateUtils.getFactory().openSession()) {
            ProductRepositoryImpl s = new ProductRepositoryImpl();
            s.getProducts(null).forEach(p -> System.out.printf("%d -%s - %1f - %s \n",p.getId(),p.getName(),p.getPrice(),p.getCategory().getName()));
//            Query q = s.createQuery("From Category");
//            List<Category> cates = q.getResultList();
//
//            cates.forEach(c -> System.out.println(c.getName()));

    }
}
