package com.btn.repository.impl;

import com.btn.hibernatedemo.HibernateUtils;
import com.btn.pojo.Product;
import org.hibernate.Session;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// TRUY VAN
public class ProductRepositoryImpl {
    public List<Product> getProducts(Map<String, String> params) {
        try (Session s = HibernateUtils.getFactory().openSession()) {
            CriteriaBuilder cb = s.getCriteriaBuilder();
            CriteriaQuery<Product> cq = cb.createQuery(Product.class);
            Root<Product> root = cq.from(Product.class);
            cq.select(root);

        // Thêm điều kiện tùy thuộc vào params nếu cần
        List<Predicate> predicates = new ArrayList<>();
        String kw = params.get("kw");
        if (kw != null && !kw.isEmpty())
            predicates.add(cb.like(root.get("name"), String.format("%%%s%%", kw)));

        // Tim san pham theo khoang gia
        String fromPrice = params.get("fromPrice");
        if(fromPrice!= null && !fromPrice.isEmpty())
            predicates.add(cb.greaterThanOrEqualTo(root.get("price"), Double.parseDouble(fromPrice)));

        String toPrice = params.get("toPrice");
        if(toPrice!= null && !toPrice.isEmpty())
            predicates.add(cb.lessThanOrEqualTo(root.get("price"), Double.parseDouble(toPrice)));



        // loc theo danh muc
        String cateId = params.get("cateId");
        if(cateId!= null && !cateId.isEmpty())
            predicates.add(cb.equal(root.get("category").as(Integer.class), Integer.parseInt(cateId)));
        // Sử dụng orderBy để sắp xếp kết quả

        // parse thanh tung phan tu predicate roi
        cq.where(predicates.toArray(Predicate[]::new));
        cq.orderBy(cb.desc(root.get("id")));

            // Tạo một truy vấn từ CriteriaQuery
            Query query = s.createQuery(cq);

            // Thực hiện truy vấn và trả về kết quả
            List<Product> products = query.getResultList();
            return products;
        }
    }
}