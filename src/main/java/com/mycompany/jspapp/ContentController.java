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
import org.springframework.beans.factory.annotation.Autowired;
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
    List<Mark> scores;
    ApplicationContext factory;
    
    @Autowired
    private StudentDAO sdao;
    @Autowired
    private ContentDAO cdao;
    
    
    @RequestMapping(value = "MarkCheck", method=RequestMethod.GET)
    public ModelAndView formContent(@RequestParam("id2") String id2,HttpServletRequest request,HttpServletResponse response) throws IOException, SQLException{
        ModelAndView modelNview = new ModelAndView();
        modelNview.setViewName("score");
        ApplicationContext factory = new ClassPathXmlApplicationContext("/spring.xml");
        Student student = sdao.getStudent(Integer.parseInt(id2));
        modelNview.addObject("user", student);
        scores = cdao.getScores(student.getId());
        modelNview.addObject("scores", scores);
        return modelNview;
    }
}
