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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String home() {
            return "regform.jsp";
    }
    
    @RequestMapping("StudentAdd")
     public String addStudent(HttpServletRequest request,HttpServletResponse response,Model m) throws IOException, SQLException{
        ApplicationContext factory = new ClassPathXmlApplicationContext("/spring.xml");
        List<Student> students;
        PrintWriter pw=null;
        
        try{
            pw = response.getWriter();
            Class.forName("org.postgresql.Driver");
        } catch(ClassNotFoundException ex){
            ex.printStackTrace(pw);
            pw.print(ex.getMessage());
        }
        
        try{
            Connection conn = null;
            conn = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/jbdc_test", "postgres", "12345");

            if(request.getParameter("name") != "" || request.getParameter("surname") != ""){
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(
                        "INSERT INTO Student(studentName, studentSurname, studentEmail, studentGroup, studentFaculty) "
                                + "VALUES(?, ?, ?, ?, ?); ");
                ps.setString(1, request.getParameter("name"));
                ps.setString(2, request.getParameter("surname"));
                ps.setString(3, request.getParameter("email"));
                ps.setString(4, request.getParameter("group"));
                ps.setString(5, request.getParameter("faculty"));
                ps.executeUpdate();
            }
            
            Statement s = (Statement) conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM Student");
            students = new LinkedList<Student>();
            Student student =null;
            while(rs.next()){
                student = (Student)factory.getBean("student");
                student.setId(rs.getInt(1));
                student.setName(rs.getString(2));
                student.setSurname(rs.getString(3));
                student.setEmail(rs.getString(4));
                student.setGroup(rs.getString(5));
                student.setFaculty(rs.getString(6));
                students.add(student);
            }
            m.addAttribute("students", students);
        } catch(SQLException ex){
             System.out.println("Problems with sql");
             Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null,ex);
        }
        return "/regform.jsp";
     }
}
