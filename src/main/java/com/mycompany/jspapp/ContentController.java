package com.mycompany.jspapp;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContentController {
    @RequestMapping(value = "MarkCheck", method=RequestMethod.GET)
    public ModelAndView formContent(@RequestParam("id2") String id2,Model m,HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException{
        ModelAndView modelNview = new ModelAndView();
        modelNview.setViewName("score");
        ApplicationContext factory = new ClassPathXmlApplicationContext("/spring.xml");
        
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
            if(rs.next()){
                student = (Student)factory.getBean("student");
                student.setId(rs.getInt(1));
                student.setName(rs.getString(2));
                student.setSurname(rs.getString(3));
                student.setEmail(rs.getString(4));
                student.setGroup(rs.getString(5));
                student.setFaculty(rs.getString(6));
                modelNview.addObject("user", student);
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
            modelNview.addObject("scores", cont);
            rs.close();
            ps.close();
        } catch(SQLException ex){
             System.out.println("Problems with sql");
             Logger.getLogger(ContentController.class.getName()).log(Level.SEVERE, null,ex);
        }
        
        return modelNview;
    }
}
