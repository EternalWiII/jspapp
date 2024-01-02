package com.mycompany.jspapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {
    List<Student> students;
    ApplicationContext factory;
    Student student;
    
    @RequestMapping(value = "/")
    public String home(Model m) {
        students = dao.getStudents();
        m.addAttribute("students", students);
        return "regform";
    }
    
    @Autowired
    private StudentDAO dao;
    
    @ModelAttribute
    public void modelData(Model m){
        if(students == null){
            students = new LinkedList<Student>();
        }
        factory = new ClassPathXmlApplicationContext("/spring.xml");
    }
    
    @RequestMapping("StudentAdd")
     public String addStudent(HttpServletRequest request,HttpServletResponse response,Model m) throws IOException, SQLException{
        try{
            Student student = (Student)factory.getBean("student");
            
            student.setId(Integer.parseInt(request.getParameter("studentid")));
            student.setName(request.getParameter("studentname"));
            student.setSurname(request.getParameter("studentsurname"));
            student.setGroup(request.getParameter("studentgroup"));
            student.setFaculty(request.getParameter("studentfaculty"));
            student.setEmail(request.getParameter("studentemail"));
            
            dao.addStudent(student);
            students = dao.getStudents();
            m.addAttribute("students", students);
        } catch(Exception ex){
             Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null,ex);
        }
        return "regform";
     }
}
