/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Student;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ACER
 */
public class StudentDBcontext extends DBcontext<Student> {

    public ArrayList<Student> getStudentBylession(int lesId) {
        ArrayList<Student> students = new ArrayList<>();
        try {
            String sql = "SELECT s.sid ,s.sname,s.dob,s.gender,s.mail,s.phone FROM Student s \n"
                    + "JOIN Enrollment e ON s.sid = e.sid\n"
                    + "JOIN StudentGroup sg ON e.gid = sg.gid\n"
                    + "JOIN Lession les ON les.gid = sg.gid\n"
                    + "WHERE les.leid=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, lesId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Student stu = new Student();
               stu.setId(rs.getInt("sid"));
               stu.setName(rs.getString("sname"));
               stu.setGender(rs.getBoolean("gender"));
               stu.setDob(rs.getDate("dob"));
               stu.setPhone(rs.getInt("phone"));
               stu.setMail(rs.getString("mail"));
               students.add(stu);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBcontext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }

    @Override
    public ArrayList<Student> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Student entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Student entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Student entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
