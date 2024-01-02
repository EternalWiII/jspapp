package com.mycompany.jspapp;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;


@Repository("StudentDao")
@EnableTransactionManagement
public class StudentDAO {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Transactional
    public void addStudent(Student a){
        Session session;
        try{
            session = sessionFactory.getCurrentSession();
        } catch(HibernateException e){
            session = sessionFactory.openSession();
        }
        session.save(a);
    }
    
    @Transactional
    public List<Student> getStudents(){
        Session session = sessionFactory.getCurrentSession();
        List<Student> stud = session.createQuery("FROM student").list();
        return stud;
    }
    
    @Transactional
    public Student getStudent(int id){
       Session session = sessionFactory.getCurrentSession();
       return (Student) session.get(Student.class, id);
    }
}
