package com.mycompany.jspapp;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="studentid")
    private int id;
    @Column(name="studentname")
    private String name;
    @Column(name="studentsurname")
    private String surname;
    @Column(name="studentemail")
    private String email;
    @Column(name="studentgroup")
    private String group;
    @Column(name="studentfaculty")
    private String faculty;

    public Student(int id, String name, String surname, String email, String group, String faculty) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.group = group;
        this.faculty = faculty;
    }

    public Student() {
        name = "";
        surname = "";
        email = "";
        group= "";
        faculty = "";
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }
    
    public String getGroup(){
        return group;
    }
    
    public String getFaculty(){
        return faculty;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setGroup(String group) {
        this.group = group;
    }
    
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
