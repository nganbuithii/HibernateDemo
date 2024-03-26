package com.btn.repository.impl;

import com.btn.hibernatedemo.HibernateUtils;
import com.btn.pojo.Product;
import org.hibernate.Session;
import javax.persistence.criteria.Root;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Map;

// TRUY VAN
 public class ProductRepositoryImpl {
    public List<Product> getProducts(Map<String,String> params){
        try(Session s = HibernateUtils.getFactory().openSession()){
            CriteriaBuilder cb = s.getCriteriaBuilder();
            CriteriaQuery<Product> cq = cb.createQuery(Product.class);
            Root root = cq.from(Product.class);
            cq.select(root);

            // Thêm điều kiện tùy thuộc vào params nếu cần

            // Sử dụng orderBy để sắp xếp kết quả
            cq.orderBy(cb.desc(root.get("id")));

            // Tạo một truy vấn từ CriteriaQuery
            Query query = s.createQuery(cq);

            // Thực hiện truy vấn và trả về kết quả
            List<Product> products = query.getResultList();
            return products;
        }
    }
}