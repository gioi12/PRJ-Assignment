/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author ACER
 */
public class Lecture {
    private int lid;
    private String lname;
    private int phone;
    private String mail;
    public void setLid(int lid) {
        this.lid = lid;
    }

  

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getLid() {
        return lid;
    }

    public String getLname() {
        return lname;
    }

    public int getPhone() {
        return phone;
    }

    public String getMail() {
        return mail;
    }
    
}
