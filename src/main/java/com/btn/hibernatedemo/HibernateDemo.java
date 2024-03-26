/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.btn.hibernatedemo;

import com.btn.pojo.Category;
import com.btn.pojo.Product;
import com.btn.repository.impl.CategoryRepositoryImpl;
import com.btn.repository.impl.ProductRepositoryImpl;
import com.btn.repository.impl.StatsRepositoryImpl;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author FShop
 */
public class HibernateDemo {

    public static void main(String[] args) {
        StatsRepositoryImpl s = new StatsRepositoryImpl();
        s.statsRevenueByMonth(2020).forEach(q -> System.out.printf("%d: %.1f\n\n", q[0], q[1]));

        s.statsRevenueByPeriod(2020,"QUARTER").forEach(q -> System.out.printf("%d: %.1f\n\n", q[0], q[1]));
//        CategoryRepositoryImpl s = new CategoryRepositoryImpl();
//        s.getCategories().forEach(c -> System.out.println(c.getName()));
//
//        ProductRepositoryImpl c = new ProductRepositoryImpl();
//        Product p = new Product();
//        p.setName("ABC");
//        p.setPrice(12000000);
//        p.setCategory(s.getCateById(1));
//        c.addOrUpdate(p);


//        try (Session s = HibernateUtils.getFactory().openSession()) {
//            ProductRepositoryImpl s = new ProductRepositoryImpl();
//
//            Map<String, String> params = new HashMap<>();
//
//            // tim kiem theo ten san pham, theo gia
//        params.put("fromPrice","18000000");
//        params.put("toPrice","25000000");
//        params.put("kw","Note");
//
//            s.getProducts(params).forEach(p -> System.out.printf("%d -%s - %1f - %s \n",p.getId(),p.getName(),p.getPrice(),p.getCategory().getName()));
//            Query q = s.createQuery("From Category");
//            List<Category> cates = q.getResultList();
//
//            cates.forEach(c -> System.out.println(c.getName()));

    }
}
