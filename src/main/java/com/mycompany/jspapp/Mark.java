package com.mycompany.jspapp;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="marks")
public class Mark implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="markid")
    int markid;
    @Column(name="studentid")
    int studentID;
    @Column(name="title")
    String title;
    @Column(name="mark_let")
    String mark_let;
    @Column(name="mark_num")
    int mark_num;

    public Mark() {}

    public Mark(int markid, int studentID, String title, String mark_let, int mark_num) {
        this.markid = markid;
        this.studentID = studentID;
        this.title = title;
        this.mark_let = mark_let;
        this.mark_num = mark_num;
    }

    public void setMarkid(int markid) {
        this.markid = markid;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMark_let(String mark_let) {
        this.mark_let = mark_let;
    }

    public void setMark_num(int mark_num) {
        this.mark_num = mark_num;
    }

    public int getMarkid() {
        return markid;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getTitle() {
        return title;
    }

    public String getMark_let() {
        return mark_let;
    }

    public int getMark_num() {
        return mark_num;
    }
}
