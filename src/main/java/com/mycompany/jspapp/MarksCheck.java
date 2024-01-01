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
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@WebServlet(name = "MarksCheck", urlPatterns = {"/MarksCheck"})
public class MarksCheck extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ApplicationContext factory = new ClassPathXmlApplicationContext("/spring.xml");
        HttpSession session = request.getSession();
        
        PrintWriter pw = null;
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
            
            PreparedStatement ps= (PreparedStatement) conn.prepareStatement("SELECT * FROM student where studentid=?;");
                ps.setInt(1, Integer.parseInt(request.getParameter("id2")));
            ResultSet rs=ps.executeQuery();
            Student student =null;
            while(rs.next()){
                student = (Student)factory.getBean("student");
                student.setId(rs.getInt(1));
                student.setName(rs.getString(2));
                student.setSurname(rs.getString(3));
                student.setEmail(rs.getString(4));
                student.setGroup(rs.getString(5));
                student.setFaculty(rs.getString(6));
                session.setAttribute("user", student);
            }
            
            ps= (PreparedStatement) conn.prepareStatement("select * from marks where studentid =?;");
                ps.setInt(1, Integer.parseInt(request.getParameter("id2")));
                rs=ps.executeQuery();
           List<Mark> cont = new LinkedList<Mark>();
            while(rs.next()){
                Mark ctn = (Mark)factory.getBean("mark");
                ctn.setMarkid(Integer.parseInt(rs.getString(1)));
                ctn.setStudentID(Integer.parseInt(rs.getString(2)));
                ctn.setTitle(rs.getString(3));
                ctn.setMark_num(Integer.parseInt(rs.getString(5)));
                ctn.setMark_let(rs.getString(4));

                cont.add(ctn);
            }
            rs.close();
            ps.close();
            session.setAttribute("scores", cont);
            response.sendRedirect("/jspapp/score.jsp");
        } catch(SQLException ex){
             System.out.println("Problems with sql");
             Logger.getLogger(StudentAdd.class.getName()).log(Level.SEVERE, null,ex);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
