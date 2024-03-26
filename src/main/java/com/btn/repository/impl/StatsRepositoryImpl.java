package com.btn.repository.impl;

import com.btn.hibernatedemo.HibernateUtils;
import com.btn.pojo.OrderDetail;
import com.btn.pojo.Product;
import com.btn.pojo.SaleOrder;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class StatsRepositoryImpl {
    public List<Object[]> statsRevenueByProduct() {
        try (Session s = HibernateUtils.getFactory().openSession()) {
            CriteriaBuilder cb = s.getCriteriaBuilder();
            CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
            Root<OrderDetail> rootOrderDetail = cq.from(OrderDetail.class);
            Join<OrderDetail, Product> productJoin = rootOrderDetail.join("product");

            cq.multiselect(
                    productJoin.get("id"),
                    productJoin.get("name"),
                    cb.sum(cb.prod(rootOrderDetail.get("quantity"), rootOrderDetail.get("unitPrice")))
            );

            cq.groupBy(productJoin.get("id"), productJoin.get("name"));

            Query query = s.createQuery(cq);
            return query.getResultList();
        }
    }

    public List<Object[]> statsRevenueByMonth(int year) {
        try (Session s = HibernateUtils.getFactory().openSession()) {
            CriteriaBuilder cb = s.getCriteriaBuilder();
            CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
            Root<OrderDetail> rootOrderDetail = cq.from(OrderDetail.class);
            Join<OrderDetail, SaleOrder> saleOrderJoin = rootOrderDetail.join("order");

            cq.multiselect(
                    cb.function("MONTH", Integer.class, saleOrderJoin.get("createdDate")),
                    cb.sum(cb.prod(rootOrderDetail.get("quantity"), rootOrderDetail.get("unitPrice")))
            );

            Predicate yearPredicate = cb.equal(cb.function("YEAR", Integer.class, saleOrderJoin.get("createdDate")), year);
            cq.where(yearPredicate);

            cq.groupBy(cb.function("MONTH", Integer.class, saleOrderJoin.get("createdDate")));

            Query query = s.createQuery(cq);
            return query.getResultList();
        }
    }
    public List<Object[]> statsRevenueByPeriod(int year, String period) {
        try (Session s = HibernateUtils.getFactory().openSession()) {
            CriteriaBuilder cb = s.getCriteriaBuilder();
            CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
            Root<OrderDetail> rootOrderDetail = cq.from(OrderDetail.class);
            Join<OrderDetail, SaleOrder> saleOrderJoin = rootOrderDetail.join("order");

            cq.multiselect(
                    cb.function(period, Integer.class, saleOrderJoin.get("createdDate")),
                    cb.sum(cb.prod(rootOrderDetail.get("quantity"), rootOrderDetail.get("unitPrice")))
            );

            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(cb.function("YEAR", Integer.class, saleOrderJoin.get("createdDate")), year));

            cq.where(predicates.toArray(new Predicate[0]));

            cq.groupBy(cb.function(period, Integer.class, saleOrderJoin.get("createdDate")));

            Query query = s.createQuery(cq);
            return query.getResultList();
        }
    }

}
