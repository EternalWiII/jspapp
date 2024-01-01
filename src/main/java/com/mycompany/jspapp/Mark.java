package com.mycompany.jspapp;

public class Mark {
    int markid;
    int studentID;
    String title;
    String mark_let;
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
