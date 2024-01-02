package com.mycompany.jspapp;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Repository("StudentDao")
@EnableTransactionManagement
public class ContentDAO {
    @Autowired
    private SessionFactory sessionFactory;
    
    @Transactional
    public List<Mark> getScores(int studentid){
        Session session = sessionFactory.getCurrentSession();
        List<Mark> cont = session.createQuery("from marks where studentid=:studentid").setParameter("studentid", studentid).list();
        return cont;
    }
}
