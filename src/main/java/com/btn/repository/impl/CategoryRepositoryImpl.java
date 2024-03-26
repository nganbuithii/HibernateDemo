package com.btn.repository.impl;

import com.btn.hibernatedemo.HibernateUtils;
import com.btn.pojo.Category;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;

public class CategoryRepositoryImpl {
    public List<Category> getCategories(){
        try(Session s = HibernateUtils.getFactory().openSession()){
            Query q = s.createNamedQuery("Category.findAll");

            return q.getResultList();
        }
    }
}
