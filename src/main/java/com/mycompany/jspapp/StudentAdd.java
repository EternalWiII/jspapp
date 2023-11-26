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

@WebServlet(name = "StudentAdd", urlPatterns = {"/StudentAdd"})
public class StudentAdd extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Student> students;
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
            while(rs.next()){
                Student student = new Student(rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6));
                students.add(student);
            }
            session.setAttribute("students",students);
            response.sendRedirect("/jspapp/regform.jsp");
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
